package by.example.webstore.util.validator;

import java.io.InputStream;
import java.util.Map;

import static by.example.webstore.controller.command.ParameterAndAttribute.*;


public class ProductValidator {
    private static final String INCORRECT_VALUE_PARAMETER = " incorrect";
    private static final String TITLE_REGEX = "[A-Za-zА-Яа-я-,.!?\"%()\\s]{2,75}";
    private static final String DESCRIPTION_REGEX = ".{10,500}";
    private static final String PRICE_REGEX = "\\d{1,5}\\.\\d{1,2}";
    private static final String MANUFACTURE_REGEX = "[A-Za-zА-Яа-я-,.!?\"%()\\s]{3,100}";

    private static final ProductValidator instance = new ProductValidator();

    private ProductValidator() {
    }
    public static ProductValidator getInstance() {
        return instance;
    }


    public boolean checkProductData(Map<String, String> productData, InputStream image) {
        boolean isValid = true;
        if (!productData.get(TITLE).matches(TITLE_REGEX)) {
            productData.put(TITLE, productData.get(TITLE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(DESCRIPTION).matches(DESCRIPTION_REGEX)) {
            productData.put(DESCRIPTION, productData.get(DESCRIPTION) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(PRICE).matches(PRICE_REGEX)) {
            productData.put(PRICE, productData.get(PRICE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!productData.get(MANUFACTURE).matches(MANUFACTURE_REGEX)) {
            productData.put(MANUFACTURE, productData.get(MANUFACTURE) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (image == null) {
            isValid = false;
        }
        return isValid;
    }
}
