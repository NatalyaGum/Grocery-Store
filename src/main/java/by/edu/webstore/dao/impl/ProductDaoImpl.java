package by.edu.webstore.dao.impl;

import by.edu.webstore.dao.ProductDao;
import by.edu.webstore.exception.DaoException;
import by.edu.webstore.connection.ConnectionPool;
import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.ProductType;
import by.edu.webstore.exception.ConnectionPoolException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.edu.webstore.dao.ColumnName.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    static Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();


    private static final String FIND_PRODUCT_BY_ID = """
            SELECT id_product, title, description, price, picture, manufacture, product_type.product_type, active FROM products
            JOIN product_type ON product_type.id_product_type=products.product_type_id WHERE id_product=?""";
    private static final String FIND_PRODUCT_IN_ANY_ORDER = "SELECT id_order FROM orders WHERE id_product=? LIMIT 1";
    private static final String FIND_PRODUCT_BY_TITLE = "SELECT id_product FROM products WHERE name=?";
    private static final String FIND_ALL_PRODUCTS = """
            SELECT id_product, title, product_type.product_type, price, picture, manufacture, description, active FROM products
            JOIN product_type ON products.product_type_id=product_type.id_product_type""";
    private static final String FIND_ALL_PRODUCTS_PAGES = """
            SELECT id_product, title, product_type.product_type, price, picture, manufacture, description, active FROM products
            JOIN product_type ON products.product_type_id=product_type.id_product_type ORDER BY id_product  LIMIT ?,?""";
    private static final String FIND_ALL_PRODUCTS_BY_TYPE = """
            SELECT id_product, title, product_type.product_type, price, picture, description, manufacture FROM products
            JOIN product_type ON product_type.id_product_type=product_type_id WHERE active=1 AND product_type.product_type=?""";
    private static final String INSERT_NEW_PRODUCT = """
            INSERT INTO products (title, description, price, picture, manufacture, product_type_id)
            VALUES(?, ?, ?, ?, ?, (SELECT id_product_type FROM product_type WHERE product_type=?))""";
    private static final String UPDATE_PRODUCT_STATUS = "UPDATE products SET active=? WHERE id_product=?";
    private static final String UPDATE_PRODUCT_WITH_PICTURE = "UPDATE products SET title=?, description=?, price=?, picture=?, manufacture=?, product_type_id=(SELECT id_product_type FROM product_type WHERE product_type=?), active=? WHERE id_product=?";
    private static final String UPDATE_PRODUCT = "UPDATE products SET title=?, description=?, price=?, manufacture=?, product_type_id=(SELECT id_product_type FROM product_type WHERE product_type=?), active=? WHERE id_product=?";
    private static final String FIND_ALL_PRODUCT_TYPES = "SELECT id_product_type, product_type FROM product_type";
    private static final String INSERT_NEW_PRODUCT_TYPE = "INSERT INTO product_type (product_type) VALUES(?)";
    private static final String MODIFY_PRODUCT_TYPE = "UPDATE product_type SET product_type=? WHERE product_type=?";
    private static final String REMOVE_PRODUCT_TYPE = "DELETE FROM product_type WHERE product_type=?";
    private static final String EMPTY_PRODUCT_TYPE = """
    SELECT COUNT(product_type_id) FROM products JOIN product_type 
    ON product_type.id_product_type=products.product_type_id WHERE product_type=?""";
    private static final String FIND_TOTAL_PRODUCTS_NUMBER ="SELECT COUNT(id_product) FROM products";


    public Optional<Product> findProductById(long id) throws DaoException {
        Optional<Product> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = ProductCreator.getInstance().create(resultSet);
                productOptional = Optional.of(product);
            }
            logger.debug("findEntityById method was completed successfully."
                    + ((productOptional.isPresent()) ? " Product with id " + id + " was found" : " Product with id " + id + " don't exist"));
            return productOptional;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find product by id. Database error:", e);
            throw new DaoException("Impossible to find product by id. Database error:", e);
        }
    }


    public List<Product> findAllEntities() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_PRODUCTS)) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = ProductCreator.getInstance().create(resultSet);
                products.add(product);
            }
            logger.info("findAllEntities method was completed successfully. " + products.size() + " All products were found");
            return products;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find all products. Database error:", e);
            throw new DaoException("Impossible to find all products. Database error:", e);
        }
    }

    public List<Product> findAllEntities(int offset, int limit) throws DaoException {
     try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PRODUCTS_PAGES)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = ProductCreator.getInstance().create(resultSet);
                products.add(product);
            }
            logger.info("findAllEntities method was completed successfully. " + products.size() + " All products were found");
            return products;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find all products. Database error:", e);
            throw new DaoException("Impossible to find all products. Database error:", e);
        }
    }


    public long insertNewEntity(Product product) throws DaoException, ConnectionPoolException {
        return 0;
    }


    public long insertNewEntity(Product product, InputStream image) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_PRODUCT, RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getTitle());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setBlob(4, image);
            statement.setString(5, product.getManufacture());
            statement.setString(6, product.getProductType().toString());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            long productId = 0;
            if (resultSet.next()) {
                productId = resultSet.getLong(1);
                logger.info("insertNewEntity method was completed successfully. Product with id " + productId + " was added");
            }
            return productId;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to insert product into database. Database error:", e);
            throw new DaoException("Impossible to insert product into database. Database error:", e);
        }
    }


    public boolean isProductExist(String title) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT_BY_TITLE)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            boolean result = resultSet.isBeforeFirst();
            logger.debug("isProductExist method was completed successfully. Result: " + result);
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to check existence of product. Database error:", e);
            throw new DaoException("Impossible to check existence of product. Database error:", e);
        }
    }


    public List<Product> findProductsByType(ProductType type) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PRODUCTS_BY_TYPE)) {
            statement.setString(1, type.toString());
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = ProductCreator.getInstance().create(resultSet);
                products.add(product);
            }
            logger.debug("findProductsByType method was completed successfully. " + products.size()
                    + " products with type " + type + " were found");
            return products;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find Products by type. Database error:", e);
            throw new DaoException("Impossible to find Products by type. Database error:", e);
        }
    }


    public List<ProductType> findAllProductTypes() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_PRODUCT_TYPES)) {
            List<ProductType> productTypes = new ArrayList<>();
            while (resultSet.next()) {
                ProductType productType = new ProductType(resultSet.getInt(ID_PRODUCT_TYPE), resultSet.getString(PRODUCT_TYPE));
                productTypes.add(productType);
            }
            logger.info("findAllProductTypes method was completed successfully. " + productTypes.size() + " All product types were found");
            return productTypes;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find product types. Database error:", e);
            throw new DaoException("Impossible to find product types. Database error:", e);
        }
    }

    public boolean updateProductStatusById(boolean active, Long productId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_STATUS)) {
            statement.setBoolean(1, active);
            statement.setLong(2, productId);
            boolean result = statement.executeUpdate() == 1;
            logger.info("updateProductStatusById method was completed successfully. Product with id  " + productId + " were updated to " + active + " statuses");
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to update Product statuses. Database error:", e);
            throw new DaoException("Impossible to update Product statuses. Database error:", e);
        }
    }


    public long insertNewProductType(String productTypeData) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_PRODUCT_TYPE, RETURN_GENERATED_KEYS)) {
            statement.setString(1, productTypeData);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            long productId = 0;
            if (resultSet.next()) {
                productId = resultSet.getLong(1);
                logger.info("insertNewProductType method was completed successfully. Product with id " + productId + " was added");
            }
            return productId;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to insert product type into database. Database error:", e);
            throw new DaoException("Impossible to insert product type into database. Database error:", e);
        }

    }


    public boolean modifyProductType(String oldProductType, String newProductType) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(MODIFY_PRODUCT_TYPE)) {
            statement.setString(1, newProductType);
            statement.setString(2, oldProductType);
            boolean result = statement.executeUpdate() == 1;
            logger.info("ModifyProductType method was completed successfully.");
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to update ProductType. Database  error:", e);
            throw new DaoException("Impossible to update ProductType statuses. Database  error:", e);
        }
    }

    public boolean deleteProductType(String oldProductType) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT_TYPE)) {
            statement.setString(1, oldProductType);
            boolean result = statement.executeUpdate() == 1;
            logger.info("deleteProductType method was completed successfully.");
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to remove ProductType. Database  error:", e);
            throw new DaoException("Impossible to remove ProductType. Database  error:", e);
        }
    }

    public boolean isEmptyType(String oldProductType) throws DaoException {
        boolean result=false;
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(EMPTY_PRODUCT_TYPE)) {
            statement.setString(1, oldProductType);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
             result=resultSet.getInt(1)==0;}
            logger.info("isEmptyType method was completed successfully.");

        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to check ProductType. Database  error:", e);
            throw new DaoException("Impossible to check ProductType. Database  error:", e);
        }return result;
}
    public int findTotalProductsNumber () throws DaoException {
        int result = 0;
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_TOTAL_PRODUCTS_NUMBER)) {
            int  productsNumber = 0;
            while (resultSet.next()) {
                productsNumber=resultSet.getInt(1);
            }
            logger.info("findTotalProductsNumber method was completed successfully. " + productsNumber + " All products were found");
            return productsNumber;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find all products. Database error:", e);
            throw new DaoException("Impossible to find all products. Database error:", e);
        }
    }

    public boolean updateProduct(Product product, InputStream image) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_WITH_PICTURE)) {
            statement.setString(1, product.getTitle());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setBlob(4, image);
            statement.setString(5, product.getManufacture());
            statement.setString(6, product.getProductType().toString());
            statement.setBoolean(7, product.getActive());
            statement.setLong(8, product.getProductId());
             result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to insert product into database. Database error:", e);
            throw new DaoException("Impossible to insert product into database. Database error:", e);
        }return result;
    }

    public boolean updateProduct(Product product) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getTitle());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setString(4, product.getManufacture());
            statement.setString(5, product.getProductType().toString());
            statement.setBoolean(6, product.getActive());
            statement.setLong(7, product.getProductId());
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to insert product into database. Database error:", e);
            throw new DaoException("Impossible to insert product into database. Database error:", e);
        }return result;
    }
}


