package by.edu.webstore.dao;


import by.edu.webstore.exception.DaoException;
import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.ProductType;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * {@code ProductDao} class implements functional of {@link BaseDao}
 */
public interface ProductDao extends BaseDao<Product> {
    Optional<Product> findProductById(long id) throws DaoException;

    List<Product> findAllEntities() throws DaoException;

    long insertNewProduct(Product product, InputStream image) throws DaoException;

    List<ProductType> findAllProductTypes() throws DaoException;

    long insertNewProductType(String productTypeData) throws DaoException;

    boolean modifyProductType(String oldProductType, String newProductType) throws DaoException;

    boolean deleteProductType(String oldProductType) throws DaoException;

    boolean isEmptyType(String oldProductType) throws DaoException;

    int findTotalProductsNumber() throws DaoException;

    List<Product> findAllEntities(int offset, int limit) throws DaoException;

    boolean updateProduct(Product product, InputStream image) throws DaoException;

    boolean updateProduct(Product product) throws DaoException;

    boolean updateProductPicture(long id, InputStream image) throws DaoException;

    boolean isTypeExist(String productType) throws DaoException;

    List<Product> findProductsByType(int productTypeId) throws DaoException;

    List<Product> searchProducts(String nameProduct) throws DaoException;
}




