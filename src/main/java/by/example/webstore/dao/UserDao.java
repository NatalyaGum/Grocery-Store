package by.example.webstore.dao;

import by.example.webstore.entity.User;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    boolean updateUserName(long userId, String firstName) throws ConnectionPoolException, DaoException;

    boolean updateUserSurname(long userId, String lastName) throws ConnectionPoolException, DaoException;

    boolean updateUserPhone(long userId, String mobileNumber) throws ConnectionPoolException, DaoException;

    boolean updateUserEmail(long userId, String email) throws ConnectionPoolException, DaoException;

    boolean updateUserPassword(long userId, String password) throws ConnectionPoolException, DaoException;

    boolean updateUserStatusById(User.Status status, Long userId) throws ConnectionPoolException, DaoException;

    boolean isEmailExist(String email) throws ConnectionPoolException, DaoException;

    boolean isMobileNumberExist(String mobileNumber) throws ConnectionPoolException, DaoException;

    boolean isUserExist(long userId, String passwordHash) throws ConnectionPoolException, DaoException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws ConnectionPoolException, DaoException;

}
