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
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductDao productDao = DaoProvider.getInstance().getProductDao();

    public long insertNewProduct(Map<String, String> productData, InputStream image) throws ServiceException {
        long result = 0;

        if (ProductValidator.getInstance().checkProductData(productData, image)) {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(productData.get(ParameterAndAttribute.PRICE)));
            Product product = new Product(productData.get(ParameterAndAttribute.TITLE), productData.get(ParameterAndAttribute.DESCRIPTION), price, productData.get(ParameterAndAttribute.MANUFACTURE), new ProductType(productData.get(ParameterAndAttribute.TYPE)));
            try {
                result = productDao.insertNewProduct(product, image) ;
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


    public List<Product> findAllProducts(int offset, int limit) throws ServiceException {
        try {
            return productDao.findAllEntities(offset, limit);
        } catch (DaoException e) {
            logger.error("product cannot be found:", e);
            throw new ServiceException("Products cannot be found:", e);
        }
    }


    public List<Product> findTypeOfProducts(int productTypeId) throws ServiceException {
        try {
            return productDao.findProductsByType(productTypeId);
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
                if(!productDao.isTypeExist(newProductType)){
                result = productDao.modifyProductType(oldProductType, newProductType);}
                else{

                }
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


    public int  getTotalProductNumber () throws ServiceException{
        try {
            return productDao.findTotalProductsNumber();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Product> getProductById (long id) throws ServiceException{
        try {
            return productDao.findProductById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    public boolean  updateProduct(Map<String, String> productData, InputStream image) throws ServiceException {
        boolean result = false;
        if (ProductValidator.getInstance().checkProductData(productData, image)) {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(productData.get(ParameterAndAttribute.PRICE)));
            long id=Long.parseLong(productData.get(ParameterAndAttribute.PRODUCT_ID));
            boolean status=Boolean.valueOf(productData.get(ParameterAndAttribute.ACTIVE));
            Product product = new Product(id,productData.get(ParameterAndAttribute.TITLE),
                    productData.get(ParameterAndAttribute.MANUFACTURE),
                    productData.get(ParameterAndAttribute.DESCRIPTION),
                    price,
                    new ProductType(productData.get(ParameterAndAttribute.TYPE)),
                    status);
            try {
                result = productDao.updateProduct(product, image) ;
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

    public boolean  updateProduct(Map<String, String> productData) throws ServiceException {
        boolean result = false;
        if (ProductValidator.getInstance().checkProductData(productData)) {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(productData.get(ParameterAndAttribute.PRICE)));
            long id=Long.parseLong(productData.get(ParameterAndAttribute.PRODUCT_ID));
            boolean status=Boolean.valueOf(productData.get(ParameterAndAttribute.ACTIVE));
            Product product = new Product(id,productData.get(ParameterAndAttribute.TITLE),
                    productData.get(ParameterAndAttribute.MANUFACTURE),
                    productData.get(ParameterAndAttribute.DESCRIPTION),
                    price,
                    new ProductType(productData.get(ParameterAndAttribute.TYPE)),
                    status);
            try {
                result = productDao.updateProduct(product) ;
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


    public boolean UpdateProductPicture(long id,InputStream image) throws ServiceException {
    boolean result = false;
        try {
            result = productDao.updateProductPicture(id, image) ;
        } catch (DaoException e) {
            logger.error("Product cannot be added:", e);
            throw new ServiceException("Product cannot be added:", e);
        } catch (NumberFormatException e) {
            logger.warn("Price parameter doesn't contain number");
        } catch (IllegalArgumentException e) {
            logger.warn("This enum type has no constant with the specified name");
    }
        return result;
}
    public boolean isTypeExist(String type) throws ServiceException {
        try {
            boolean foundEmail = productDao.isTypeExist(type);
            return foundEmail;
        } catch (DaoException exception) {
            logger.error("Error has occurred while checking product type availability: " + exception);
            throw new ServiceException("Error has occurred while checking product type availability: " + exception);
        }
    }
}
