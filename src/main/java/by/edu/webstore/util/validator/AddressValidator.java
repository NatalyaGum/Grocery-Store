package by.edu.webstore.util.validator;

import by.edu.webstore.controller.command.ParameterAndAttribute;

import java.util.Map;

/**
 * {@code AddressValidator} class implements functional to validate input data
 * for work with class {@link by.edu.webstore.entity.Address}
 */
public class AddressValidator {
    private static final String INCORRECT_VALUE_PARAMETER = " incorrect";
    private static final String STREET_REGEX = "[A-Za-zА-Яа-я-,.\\s]{3,45}";
    private static final String BUILDING_REGEX = "[A-Za-zА-Яа-я-,./\\d\\s]{1,10}";
    private static final String APARTMENT_REGEX = "\\d{0,6}";
    private static final String COMMENT_REGEX = ".{0,500}";


    private static final AddressValidator instance = new AddressValidator();

    private AddressValidator() {
    }

    public static AddressValidator getInstance() {
        return instance;
    }


    public boolean checkAddressData(Map<String, String> addressData) {
        boolean isValid = true;
        if (!addressData.get(ParameterAndAttribute.STREET).matches(STREET_REGEX)) {
            addressData.put(ParameterAndAttribute.STREET, addressData.get(ParameterAndAttribute.STREET) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!addressData.get(ParameterAndAttribute.BUILDING).matches(BUILDING_REGEX)) {
            addressData.put(ParameterAndAttribute.BUILDING, addressData.get(ParameterAndAttribute.BUILDING) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (addressData.containsKey(ParameterAndAttribute.APARTMENT)) {
            if (!addressData.get(ParameterAndAttribute.APARTMENT).matches(APARTMENT_REGEX)) {
                addressData.put(ParameterAndAttribute.APARTMENT, addressData.get(ParameterAndAttribute.APARTMENT) + INCORRECT_VALUE_PARAMETER);
                isValid = false;
            }
        }
        if (addressData.containsKey(ParameterAndAttribute.COMMENT)) {
            if (!addressData.get(ParameterAndAttribute.COMMENT).matches(COMMENT_REGEX)) {
                addressData.put(ParameterAndAttribute.COMMENT, addressData.get(ParameterAndAttribute.COMMENT) + INCORRECT_VALUE_PARAMETER);
                isValid = false;
            }
        }
        return isValid;
    }
}
