package by.example.webstore.service.impl;

import by.example.webstore.dao.DaoProvider;
import by.example.webstore.dao.ProductDao;
import by.example.webstore.exception.DaoException;
import by.example.webstore.entity.Product;
import by.example.webstore.entity.ProductType;
import by.example.webstore.exception.ServiceException;
import by.example.webstore.service.ProductService;
import by.example.webstore.util.validator.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.example.webstore.controller.command.ParameterAndAttribute.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductDao productDao = DaoProvider.getInstance().getProductDao();

    public boolean insertNewProduct(Map<String, String> productData, InputStream image) throws ServiceException {
        boolean result = false;

        if (ProductValidator.getInstance().checkProductData(productData, image)) {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(productData.get(PRICE)));
            Product product = new Product(productData.get(TITLE), productData.get(DESCRIPTION), price, productData.get(MANUFACTURE), new ProductType(productData.get(TYPE)));
            try {
                result = productDao.insertNewEntity(product, image) > 0;
            } catch (DaoException e) {
                logger.error("Product cannot be added:", e);
                throw new ServiceException("Product cannot be added:", e);
            } catch (NumberFormatException e) {
                logger.warn("Price parameter doesn't contain number");
            } catch (IllegalArgumentException e) {
                logger.warn("This enum type has no constant with the specified name");
            }

        }
        return result;
    }


    public List<Product> findAllProducts() throws ServiceException {
        try {
            return productDao.findAllEntities();
        } catch (DaoException e) {
            logger.error("product cannot be found:", e);
            throw new ServiceException("Products cannot be found:", e);
        }
    }

    public List<ProductType> findAllProductTypes() throws ServiceException {
        try {
            return productDao.findAllProductTypes();
        } catch (DaoException e) {
            logger.error("Product types cannot be found:", e);
            throw new ServiceException("Product types cannot be found:", e);
        }
    }
}
