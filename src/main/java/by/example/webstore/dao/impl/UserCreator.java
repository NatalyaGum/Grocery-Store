package by.example.webstore.dao.impl;

import by.example.webstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import static by.example.webstore.dao.ColumnName.*;

class UserCreator {
    private static UserCreator instance;

    public static UserCreator getInstance() {
        if (instance == null) {
            instance = new UserCreator();
        }
        return instance;
    }

     User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong(USER_ID));
        user.setName(resultSet.getString(USER_NAME));
        user.setSurname(resultSet.getString(USER_SURNAME));
        user.setEmail(resultSet.getString(USER_EMAIL));
        user.setPassword(resultSet.getString(USER_PASSWORD));
        user.setPhone(resultSet.getString(USER_PHONE_NUMBER));
        User.Role role = User.Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase());
        User.Status status = User.Status.valueOf(resultSet.getString(USER_STATUS).toUpperCase());
        return user;
    }
}

