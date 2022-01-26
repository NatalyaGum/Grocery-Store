package by.example.webstore.util.validator;

import java.util.Map;
import by.example.webstore.controller.command.ParameterAndAttribute.*;

import static by.example.webstore.controller.command.ParameterAndAttribute.*;

public class UserValidator {
    private static final String INCORRECT_VALUE_PARAMETER = "incorrect";
    private static final String EMAIL_REGEX = "(([\\p{Alpha}\\d._]+){5,25}@([\\p{Lower}]+){3,7}\\.([\\p{Lower}]+){2,3})";
    private static final String PASSPORT_REGEX = "[\\d\\p{Alpha}\\p{Punct}]+";
    private static final String NAME_REGEX = "[A-Za-zА-Я-а-я-]{2,20}";
    private static final String MOBILE_NUMBER_REGEX = "\\+375\\(25|29|33|44)\\d{7}";

    private static final UserValidator instance = new UserValidator();
    private UserValidator() {
    }
    public static UserValidator getInstance() {
        return instance;
    }

    public static boolean checkPassword(String password) {
        return password != null && password.matches(PASSPORT_REGEX);
    }


    public boolean checkSurname(String surname) {
        return surname != null && surname.matches(NAME_REGEX);
    }


    public boolean checkName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }


    public boolean checkEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }


    public boolean checkNumber(String number) {
        return number != null && number.matches(MOBILE_NUMBER_REGEX);
    }


   /* public boolean checkUserData(Map<String, String> userData) {
        boolean isValid = true;
        if (!checkEmail(userData.get(EMAIL))) {
            userData.put(EMAIL, userData.get(EMAIL) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkPassword(userData.get(PASSWORD))) {
            userData.put(PASSWORD, userData.get(EMAIL) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        return isValid;
    }*/


    public boolean checkUserPersonalData(Map<String, String> userData) {
        boolean isValid = true;
        if (!checkSurname(userData.get(SURNAME))) {
            userData.put(SURNAME, userData.get(SURNAME) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkName(userData.get(NAME))) {
            userData.put(NAME, userData.get(NAME) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkEmail(userData.get(EMAIL))) {
            userData.put(EMAIL, userData.get(EMAIL) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkNumber(userData.get(PHONE_NUMBER))) {
            userData.put(PHONE_NUMBER, userData.get(PHONE_NUMBER) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkPassword(userData.get(PASSWORD))) {
            userData.put(PASSWORD, userData.get(PASSWORD) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkPassword(userData.get(REPEATED_PASSWORD))) {
            userData.put(REPEATED_PASSWORD, userData.get(REPEATED_PASSWORD) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (userData.get(REPEATED_PASSWORD)==userData.get(PASSWORD)) {
            userData.put(PASSWORD, userData.get(PASSWORD) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        return isValid;
    }
}
