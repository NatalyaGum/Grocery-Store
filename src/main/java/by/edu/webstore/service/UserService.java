package by.edu.webstore.service;

import by.edu.webstore.entity.User;
import by.edu.webstore.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(long userId) throws ServiceException;
    List<User> findAllEntities() throws ServiceException;
    Optional<User> registerUser(Map<String, String> userData) throws ServiceException;
    boolean isEmailExist(Map<String, String> userData) throws ServiceException;
    Optional<User> findUser(String email, String password) throws ServiceException;
}
