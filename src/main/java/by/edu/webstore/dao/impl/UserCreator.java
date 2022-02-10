package by.edu.webstore.dao.impl;

import by.edu.webstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.edu.webstore.dao.ColumnName.*;

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
        user.setRole(User.Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
         user.setStatus(User.Status.valueOf(resultSet.getString(USER_STATUS).toUpperCase()));
        return user;
    }
}

