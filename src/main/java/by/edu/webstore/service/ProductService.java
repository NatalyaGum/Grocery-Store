package by.edu.webstore.service;

import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.ProductType;
import by.edu.webstore.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code ProductService} interface represent functional business logic
 * for work with class {@link by.edu.webstore.entity.Product}
 */
public interface ProductService {
    /**
     * Find all products
     *
     * @return products list or empty list if products not found
     * @throws ServiceException - if dao method throw {@link by.edu.webstore.exception.DaoException}
     */
    List<Product> findAllProducts(int offset, int limit) throws ServiceException;

    long insertNewProduct(Map<String, String> productData, InputStream image) throws ServiceException;

    List<ProductType> findAllProductTypes() throws ServiceException;

    boolean insertNewProductType(String productTypeData) throws ServiceException;

    boolean modifyProductType(String oldProductType, String newProductType) throws ServiceException;

    boolean deleteProductType(String oldProductType) throws ServiceException;

    int getTotalProductNumber() throws ServiceException;

    Optional<Product> getProductById(long id) throws ServiceException;

    boolean updateProduct(Map<String, String> productData) throws ServiceException;

    boolean UpdateProductPicture(long id, InputStream image) throws ServiceException;

    boolean isTypeExist(String type) throws ServiceException;

    List<Product> findTypeOfProducts(int productTypeId) throws ServiceException;

    List<Product> searchProducts(String value) throws ServiceException;
}
