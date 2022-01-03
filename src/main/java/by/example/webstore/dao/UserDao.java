package by.example.webstore.dao;

import by.example.webstore.entity.User;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    boolean updateUserName(long userId, String firstName) throws  DaoException;

    boolean updateUserSurname(long userId, String lastName) throws  DaoException;

    boolean updateUserPhone(long userId, String mobileNumber) throws  DaoException;

    boolean updateUserEmail(long userId, String email) throws  DaoException;

    boolean updateUserPassword(long userId, String password) throws  DaoException;

    boolean updateUserStatusById(User.Status status, Long userId) throws  DaoException;

    boolean isEmailExist(String email) throws  DaoException;

    boolean isMobileNumberExist(String mobileNumber) throws  DaoException;

    boolean isUserExist(long userId, String passwordHash) throws  DaoException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws  DaoException;

}
