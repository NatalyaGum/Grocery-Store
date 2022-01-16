package by.example.webstore.service.impl;

import by.example.webstore.dao.UserDao;
import by.example.webstore.dao.impl.UserDaoImpl;
import by.example.webstore.entity.User;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;
import by.example.webstore.exception.ServiceException;
import by.example.webstore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    private UserDao userDao = new UserDaoImpl();

    public List<User> findAllEntities() throws ServiceException {
        List<User> users = new ArrayList();
        try {
            users = userDao.findAllEntities();
        } catch (DaoException | ConnectionPoolException e) {
            logger.error("Try to find all users was failed.", e);
            throw new ServiceException("Try to find all users was failed. ", e);

        }
        return users;
    }

    @Override
    public Optional<User> findUserById(long userId) throws ServiceException {
      /*  if (!IdValidator.isValidId(userId)) {
            return Optional.empty();
        }*/
        Optional<User> userOptional;
        try {
            userOptional = userDao.findEntityById(userId);
        } catch (DaoException e) {
            throw new ServiceException("user search error", e);
        }
        return userOptional;

    }
}
