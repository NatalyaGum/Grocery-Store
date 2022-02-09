package by.edu.webstore.service;

import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.ProductType;
import by.edu.webstore.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ProductService {
    boolean insertNewProduct(Map<String, String> productData, InputStream image) throws ServiceException;
    List<Product> findAllProducts() throws ServiceException;
    List<ProductType> findAllProductTypes() throws ServiceException;
    boolean insertNewProductType(String productTypeData) throws ServiceException;
    boolean modifyProductType(String oldProductType, String newProductType) throws ServiceException;
    boolean deleteProductType(String oldProductType) throws ServiceException;

}
