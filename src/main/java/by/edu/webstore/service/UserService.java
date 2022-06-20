package by.edu.webstore.service;

import by.edu.webstore.entity.User;
import by.edu.webstore.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code UserService} interface represent functional business logic
 * for work with class {@link by.edu.webstore.entity.User}
 */
public interface UserService {
    Optional<User> findUserById(long userId) throws ServiceException;

    List<User> findAllEntities() throws ServiceException;

    Optional<User> registerUser(Map<String, String> userData) throws ServiceException;

    boolean isEmailExist(Map<String, String> userData) throws ServiceException;

    Optional<User> findUser(String email, String password) throws ServiceException;

    Optional<User> updateUser(Map<String, String> userData) throws ServiceException;

    boolean blockUser(String email) throws ServiceException;

    boolean makeAdmin(String email) throws ServiceException;
}
