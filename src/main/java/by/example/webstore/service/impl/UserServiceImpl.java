package by.example.webstore.service.impl;

import by.example.webstore.dao.UserDao;
import by.example.webstore.dao.impl.UserDaoImpl;
import by.example.webstore.entity.User;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;
import by.example.webstore.exception.ServiceException;
import by.example.webstore.service.UserService;
import by.example.webstore.util.PasswordEncoder;
import by.example.webstore.util.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.example.webstore.controller.command.ParameterAndAttribute.*;

public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    private static final String INCORRECT_VALUE_PARAMETER = "incorrect";
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



    public boolean registerUser(Map<String, String> userData) throws ServiceException {
        if (UserValidator.getInstance().checkUserPersonalData(userData)){
            String password = PasswordEncoder.pasEncode(userData.get(PASSWORD));
            User user=new User(userData.get(EMAIL), password, userData.get(NAME),userData.get(SURNAME),userData.get(PHONE_NUMBER), User.Role.USER,User.Status.ACTIVE);
            try {
                userDao.insertNewEntity(user);
                return true;
            } catch (DaoException |ConnectionPoolException e) {
                logger.error("Error has occurred while registering user: " + e);
                throw new ServiceException("Error has occurred while registering user: ", e);
        }
    }
        return false;
    }

    @Override
    public boolean isEmailAvailable(Map<String, String> userData) throws ServiceException {
        try {
            boolean foundEmail = userDao.isEmailExist(userData.get(EMAIL));
            return foundEmail;
        } catch (DaoException exception) {
            logger.error("Error has occurred while checking email availability: " + exception);
            throw new ServiceException("Error has occurred while checking email availability: " + exception);
        }
    }
}
