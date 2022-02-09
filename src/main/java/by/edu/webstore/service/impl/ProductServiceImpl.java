package by.edu.webstore.service.impl;

import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.dao.DaoProvider;
import by.edu.webstore.dao.ProductDao;
import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.ProductType;
import by.edu.webstore.exception.DaoException;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.util.validator.ProductValidator;
import by.edu.webstore.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(productData.get(ParameterAndAttribute.PRICE)));
            Product product = new Product(productData.get(ParameterAndAttribute.TITLE), productData.get(ParameterAndAttribute.DESCRIPTION), price, productData.get(ParameterAndAttribute.MANUFACTURE), new ProductType(productData.get(ParameterAndAttribute.TYPE)));
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

    public boolean insertNewProductType(String productTypeData) throws ServiceException {

        boolean result = false;
        if (ProductValidator.getInstance().checkProductType(productTypeData)) {
            try {
                result = productDao.insertNewProductType(productTypeData) > 0;
            } catch (DaoException e) {
                logger.error("Product types cannot be added:", e);
                throw new ServiceException("Product types cannot be added:", e);
            }
        }
        return result;
    }

    public boolean modifyProductType(String oldProductType, String newProductType) throws ServiceException {
        boolean result = false;
        if (ProductValidator.getInstance().checkProductType(newProductType)) {
            try {
                result = productDao.modifyProductType(oldProductType, newProductType);
            } catch (DaoException e) {
                logger.error("Product types cannot be modified:", e);
                throw new ServiceException("Product types cannot be modified:", e);
            }
        }
        return result;
    }


    public boolean deleteProductType(String oldProductType) throws ServiceException {
        boolean result = false;
        try {
            if (productDao.isEmptyType(oldProductType)) {
                result = productDao.deleteProductType(oldProductType);
            } else {

            }
        } catch (DaoException e) {
            logger.error("Product types cannot be modified:", e);
            throw new ServiceException("Product types cannot be modified:", e);
        }
        return result;
    }
}
