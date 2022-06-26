package by.edu.webstore.util.validator;


import by.edu.webstore.controller.command.ParameterAndAttribute;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class UserValidatorTest {

    @DataProvider(name = "userDataProvider")
    public Object[][] createData() {
        Map<String, String> map1 = new HashMap<>();
        map1.put(ParameterAndAttribute.PASSWORD, "qwe123");
        map1.put(ParameterAndAttribute.REPEATED_PASSWORD, "qwe123");
        map1.put(ParameterAndAttribute.SURNAME, "Ivanov");
        map1.put(ParameterAndAttribute.NAME, "Ivan");
        map1.put(ParameterAndAttribute.EMAIL, "noname@mail.ru");
        map1.put(ParameterAndAttribute.PHONE_NUMBER, "+375251234567");

        Map<String, String> map2 = new HashMap<>();
        map2.put(ParameterAndAttribute.PASSWORD, "qwe123");
        map2.put(ParameterAndAttribute.REPEATED_PASSWORD, "qwe123y");
        map2.put(ParameterAndAttribute.SURNAME, "Ivanov");
        map2.put(ParameterAndAttribute.NAME, "Ivan!");
        map2.put(ParameterAndAttribute.EMAIL, "noname@mail.ru");
        map2.put(ParameterAndAttribute.PHONE_NUMBER, "+375251234567");


        return new Object[][]{
                {map1, true},
                {map2, false},

        };
    }

    @Test(dataProvider = "userDataProvider")
    public void testCheckUserPersonalData(Map<String, String> userData, boolean expected) {
        boolean actual = UserValidator.getInstance().checkUserPersonalData(userData);
        Assert.assertEquals(actual, expected);
    }


    @DataProvider(name = "emailProvider")
    public Object[][] createData1() {
        return new Object[][]{
                {"ivanov@gmail.com", true},
                {"ivanov_33.5-rr@gmail.com", true},
                {"ivanov", false},
                {"ivanov@gmail.com.com", false},
        };
    }
    @Test(dataProvider = "emailProvider")
    public void testCheckEmail(String email, boolean expected) {
        boolean actual = UserValidator.getInstance().checkEmail(email);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "passwordProvider")
    public Object[][] createData2() {
        return new Object[][]{
                {"ivanov123", true},
                {"123456", true},
                {"123", false},
                {"<///>", false},
                {"11111111111111111111111111111111111111111111111111111111", false},
        };
    }
    @Test(dataProvider = "passwordProvider")
    public void testCheckPassword(String password, boolean expected) {
        boolean actual =UserValidator.getInstance().checkPassword(password);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "nameProvider")
    public Object[][] createData3() {
        return new Object[][]{
                {"Ivanov", true},
                {"Petr Swonson", true},
                {"Ivan!!!", false},
                {"УРУРУ", true},
                {"<УРУРУ>", false},
                {"11111111111111111111111111111111111111111111111111111111", false},
                {"", false},
        };
    }
    @Test(dataProvider = "nameProvider")
    public void testCheckName(String name, boolean expected) {
        boolean actual = UserValidator.getInstance().checkName(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "phoneNumberProvider")
    public Object[][] createData4() {
        return new Object[][]{
                {"+375291111111", true},
                {"+372291111", false},
                {"+3752911111111111", false},
                {"+375991111111", false},
                {"<УРУРУ>", false},
                {"", false},
        };
    }
    @Test(dataProvider = "phoneNumberProvider")
    public void testcheckNumber(String phoneNumber, boolean expected) {
        boolean actual =UserValidator.getInstance().checkNumber(phoneNumber);
        Assert.assertEquals(actual, expected);
    }
}