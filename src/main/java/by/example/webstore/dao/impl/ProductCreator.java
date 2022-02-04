package by.example.webstore.dao.impl;

import by.example.webstore.entity.Product;
import by.example.webstore.entity.ProductType;
import by.example.webstore.entity.User;
import by.example.webstore.exception.DaoException;
import by.example.webstore.util.ImageEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.example.webstore.dao.ColumnName.*;


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
       // product.setImage(resultSet.getString(PRODUCTS_IMAGE));
        product.setPicturePath(ImageEncoder.encodeBlob(resultSet.getBlob(PRODUCTS_IMAGE)));
        product.setManufacture(resultSet.getString(PRODUCTS_MANUFACTURE));
        product.setProductType( new ProductType(resultSet.getString(PRODUCT_TYPE)));
        return product;
    }
}
