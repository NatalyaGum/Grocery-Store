package by.edu.webstore.dao.impl;

import by.edu.webstore.exception.DaoException;
import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.ProductType;
import by.edu.webstore.util.ImageEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.edu.webstore.dao.ColumnName.*;


public class ProductCreator {

    private static ProductCreator instance;

    public static ProductCreator getInstance() {
        if (instance == null) {
            instance = new ProductCreator();
        }
        return instance;
    }

    Product create(ResultSet resultSet) throws SQLException, DaoException {
        Product product = new Product();
        product.setProductId(resultSet.getLong(PRODUCTS_ID));
        product.setTitle(resultSet.getString(PRODUCTS_TITLE));
        product.setDescription(resultSet.getString(PRODUCTS_DESCRIPTION));
        product.setPrice(resultSet.getBigDecimal(PRODUCTS_PRICE));
        product.setPicture(ImageEncoder.encodeBlob(resultSet.getBlob(PRODUCTS_IMAGE)));
        product.setManufacture(resultSet.getString(PRODUCTS_MANUFACTURE));
        product.setProductType(new ProductType(resultSet.getString(PRODUCT_TYPE)));
        product.setActive(resultSet.getBoolean(PRODUCTS_ACTIVE));
        return product;
    }
}
