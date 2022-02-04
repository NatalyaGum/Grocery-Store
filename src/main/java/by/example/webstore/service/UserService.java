package by.example.webstore.service;

import by.example.webstore.entity.User;
import by.example.webstore.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(long userId) throws ServiceException;
    List<User> findAllEntities() throws ServiceException;
    boolean registerUser(Map<String, String> userData) throws ServiceException;
    boolean isEmailAvailable(Map<String, String> userData) throws ServiceException;
    Optional<User> findUser(String email, String password) throws ServiceException;
}
