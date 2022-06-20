package by.edu.webstore.dao;

import by.edu.webstore.entity.User;
import by.edu.webstore.exception.DaoException;

import java.util.Optional;

/**
 * {@code UserDao} class implements functional of {@link BaseDao}
 */
public interface UserDao extends BaseDao<User> {

    boolean updateUserName(long userId, String firstName) throws DaoException;

    boolean updateUserSurname(long userId, String lastName) throws DaoException;

    boolean updateUserPhone(long userId, String mobileNumber) throws DaoException;

    boolean updateUserEmail(long userId, String email) throws DaoException;

    boolean updateUserPassword(long userId, String password) throws DaoException;

    boolean updateUserStatusById(User.Status status, Long userId) throws DaoException;

    boolean isEmailExist(String email) throws DaoException;

    boolean isMobileNumberExist(String mobileNumber) throws DaoException;

    boolean isUserExist(long userId, String passwordHash) throws DaoException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException;

    Optional<User> findUserById(long id) throws DaoException;

    long insertNewUser(User user) throws DaoException;

    boolean updateUser(User user) throws DaoException;

    boolean updateUserStatusByEmail(String email) throws DaoException;

    boolean makeAdmin(String email) throws DaoException;
}
