package by.example.webstore.dao.impl;

import by.example.webstore.connection.ConnectionPool;
import by.example.webstore.dao.ProductDao;
import by.example.webstore.entity.Product;
import by.example.webstore.entity.ProductType;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.example.webstore.dao.ColumnName.*;
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
            SELECT id_product, title, description, price, picture, manufacture, product_type.product_type FROM products
            JOIN product_type ON product_type.id_product_type=products.product_type_id WHERE product.id=?""";
    private static final String FIND_PRODUCT_IN_ANY_ORDER = "SELECT id_order FROM orders WHERE id_product=? LIMIT 1";
    private static final String FIND_PRODUCT_BY_TITLE = "SELECT id_product FROM products WHERE name=?";
    private static final String FIND_ALL_PRODUCTS = """
            SELECT id_product, title, product_type.product_type, price, picture, manufacture FROM products
            JOIN product_type ON products.product_type_id=product_type.id_product_type""";
    private static final String FIND_ALL_PRODUCTS_BY_TYPE = """
            SELECT id_product, title, product_type.product_type, price, picture, description, manufacture FROM products
            JOIN product_type ON product_type.id_product_type=product_type_id WHERE active=1 AND product_type.product_type=?""";
    private static final String INSERT_NEW_PRODUCT = """
            INSERT INTO products (title, description, price, picture, manufacture, product_type_id)
            VALUES(?, ?, ?, ?, ?, (SELECT id_product_type FROM product_type WHERE product_type=?))""";
    private static final String UPDATE_PRODUCT_STATUS = "UPDATE products SET active=? WHERE id_product=?";
    private static final String FIND_ALL_PRODUCT_TYPES = "SELECT id_product_type, product_type FROM product_type";

    public Optional<Product> findEntityById(long id) throws DaoException {
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
            logger.error("Impossible to find product by id. Database access error:", e);
            throw new DaoException("Impossible to find product by id. Database access error:", e);
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
            logger.error("Impossible to find all products. Database access error:", e);
            throw new DaoException("Impossible to find all products. Database access error:", e);
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
            logger.error("Impossible to insert product into database. Database access error:", e);
            throw new DaoException("Impossible to insert product into database. Database access error:", e);
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
            logger.error("Impossible to check existence of product. Database access error:", e);
            throw new DaoException("Impossible to check existence of product. Database access error:", e);
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
            logger.error("Impossible to find Products by type. Database access error:", e);
            throw new DaoException("Impossible to find Products by type. Database access error:", e);
        }
    }


    public List<ProductType> findAllProductTypes() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_PRODUCT_TYPES)) {
            List<ProductType> productTypes = new ArrayList<>();
            while (resultSet.next()) {
                ProductType productType = new ProductType(resultSet.getInt(ID_PRODUCT_TYPE),resultSet.getString(PRODUCT_TYPE));
                productTypes.add(productType);
            }
            logger.info("findAllProductTypes method was completed successfully. " + productTypes.size() + " All product types were found");
            return productTypes;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find product types. Database access error:", e);
            throw new DaoException("Impossible to find product types. Database access error:", e);
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
            logger.error("Impossible to update Product statuses. Database access error:", e);
            throw new DaoException("Impossible to update Product statuses. Database access error:", e);
        }
    }
}
