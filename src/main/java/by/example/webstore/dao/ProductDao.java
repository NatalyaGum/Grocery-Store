package by.example.webstore.dao;


import by.example.webstore.entity.Product;
import by.example.webstore.entity.ProductType;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;


public interface ProductDao extends BaseDao<Product> {
    Optional<Product> findEntityById(long id) throws DaoException;

    List<Product> findAllEntities() throws DaoException;

    long insertNewEntity(Product entity) throws DaoException, ConnectionPoolException;

    long insertNewEntity(Product product, InputStream image) throws DaoException;

    boolean isProductExist(String title) throws DaoException;

    List<Product> findProductsByType(ProductType type) throws DaoException;

    boolean updateProductStatusById(boolean active, Long productId) throws DaoException;
    List<ProductType> findAllProductTypes() throws DaoException;
}




