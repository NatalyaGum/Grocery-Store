package by.example.webstore.service;

import by.example.webstore.entity.User;
import by.example.webstore.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(long userId) throws ServiceException;
    List<User> findAllEntities() throws ServiceException;
}
