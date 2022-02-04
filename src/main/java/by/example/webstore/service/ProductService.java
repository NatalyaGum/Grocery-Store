package by.example.webstore.service;

import by.example.webstore.entity.Product;
import by.example.webstore.entity.ProductType;
import by.example.webstore.exception.ServiceException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ProductService {
    boolean insertNewProduct(Map<String, String> productData, InputStream image) throws ServiceException;
    List<Product> findAllProducts() throws ServiceException;
    List<ProductType> findAllProductTypes() throws ServiceException;
}
