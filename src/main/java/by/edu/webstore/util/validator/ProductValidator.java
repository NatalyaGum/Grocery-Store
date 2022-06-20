package by.edu.webstore.util.validator;

import by.edu.webstore.controller.command.ParameterAndAttribute;

import java.io.InputStream;
import java.util.Map;

/**
 * {@code ProductValidator} class implements functional to validate input data
 * for work with class {@link by.edu.webstore.entity.Product}
 */
public class ProductValidator {
    private static final String INCORRECT_VALUE_PARAMETER = " incorrect";
    private static final String TITLE_REGEX = "[A-Za-zА-Яа-я-,.!?\"%()\\s]{2,100}";
    private static final String DESCRIPTION_REGEX = ".{10,500}";
    private static final String PRICE_REGEX = "\\d{1,5}\\.\\d{1,2}";
    private static final String MANUFACTURE_REGEX = "[A-Za-zА-Яа-я-,.!?\"%()\\s]{3,45}";
    private static final String PRODUCT_TYPE_REGEX = "[A-Za-zА-Яа-я-,.!?\"\"%()\\s]{3,45}";

    private static final ProductValidator instance = new ProductValidator();

    private ProductValidator() {
    }

    public static ProductValidator getInstance() {
        return instance;
    }


    public boolean checkProductData(Map<String, String> productData, InputStream image) {
        boolean isValid = true;
        if (!productData.get(ParameterAndAttribute.TITLE).matches(TITLE_REGEX)) {
            productData.put(ParameterAndAttribute.TITLE, productData.get(ParameterAndAttribute.TITLE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(ParameterAndAttribute.DESCRIPTION).matches(DESCRIPTION_REGEX)) {
            productData.put(ParameterAndAttribute.DESCRIPTION, productData.get(ParameterAndAttribute.DESCRIPTION) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(ParameterAndAttribute.PRICE).matches(PRICE_REGEX)) {
            productData.put(ParameterAndAttribute.PRICE, productData.get(ParameterAndAttribute.PRICE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(ParameterAndAttribute.MANUFACTURE).matches(MANUFACTURE_REGEX)) {
            productData.put(ParameterAndAttribute.MANUFACTURE, productData.get(ParameterAndAttribute.MANUFACTURE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (image == null) {
            isValid = false;
        }
        return isValid;
    }

    public boolean checkProductData(Map<String, String> productData) {
        boolean isValid = true;
        if (!productData.get(ParameterAndAttribute.TITLE).matches(TITLE_REGEX)) {
            productData.put(ParameterAndAttribute.TITLE, productData.get(ParameterAndAttribute.TITLE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(ParameterAndAttribute.DESCRIPTION).matches(DESCRIPTION_REGEX)) {
            productData.put(ParameterAndAttribute.DESCRIPTION, productData.get(ParameterAndAttribute.DESCRIPTION) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(ParameterAndAttribute.PRICE).matches(PRICE_REGEX)) {
            productData.put(ParameterAndAttribute.PRICE, productData.get(ParameterAndAttribute.PRICE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(ParameterAndAttribute.MANUFACTURE).matches(MANUFACTURE_REGEX)) {
            productData.put(ParameterAndAttribute.MANUFACTURE, productData.get(ParameterAndAttribute.MANUFACTURE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        return isValid;
    }

    public boolean checkProductType(String productType) {
        boolean result = (productType.matches(PRODUCT_TYPE_REGEX));
        return result;
    }

    public boolean checkProductValue(String productData) {
        boolean isValid = true;
        if (!productData.matches(TITLE_REGEX)) {
            isValid = false;
        }
        return isValid;
    }

}
