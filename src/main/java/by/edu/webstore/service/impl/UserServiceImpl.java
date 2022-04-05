package by.edu.webstore.service.impl;

import by.edu.webstore.dao.UserDao;
import by.edu.webstore.exception.DaoException;
import by.edu.webstore.util.validator.UserValidator;
import by.edu.webstore.dao.DaoProvider;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.ConnectionPoolException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.UserService;
import by.edu.webstore.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    private static final String INCORRECT_VALUE_PARAMETER = "incorrect";
    private static final UserDao userDao = DaoProvider.getInstance().getUserDao();

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
            userOptional = userDao.findUserById(userId);
        } catch (DaoException e) {
            throw new ServiceException("user search error", e);
        }
        return userOptional;

    }



    public Optional<User> registerUser(Map<String, String> userData) throws ServiceException {
        Optional<User> userOptional=null;
        if (UserValidator.getInstance().checkUserPersonalData(userData)){
            String password = PasswordEncoder.pasEncode(userData.get(PASSWORD));
            User user=new User(userData.get(EMAIL), password, userData.get(NAME),userData.get(SURNAME),
                                    userData.get(PHONE_NUMBER), User.Role.CLIENT,User.Status.ACTIVE);

            try {
                long userId=userDao.insertNewUser(user);
                userOptional = userDao.findUserById(userId);
            } catch (DaoException e) {
                logger.error("Error has occurred while registering user: " + e);
                throw new ServiceException("Error has occurred while registering user: ", e);
        }
    }
        return userOptional;
    }

    @Override
    public boolean isEmailExist(Map<String, String> userData) throws ServiceException {
        try {
            boolean foundEmail = userDao.isEmailExist(userData.get(EMAIL));
            return foundEmail;
        } catch (DaoException exception) {
            logger.error("Error has occurred while checking email availability: " + exception);
            throw new ServiceException("Error has occurred while checking email availability: " + exception);
        }
    }

    public Optional<User> findUser(String email, String password) throws ServiceException {
        try {
            if (UserValidator.getInstance().checkEmail(email) && UserValidator.getInstance().checkPassword(password)) {
                Optional<User> user = userDao.findUserByEmailAndPassword(email,PasswordEncoder.pasEncode(password));
               // String encodedPassword = PasswordEncoder.pasEncode(password);

                    return user;

            }
        } catch (DaoException exception) {
            logger.error("Error has occurred while searching for user with email \"{}\": {}", email, exception);
            throw new ServiceException("Error has occurred while searching for user with login \"" + email + "\": ", exception);
        }
        return Optional.empty();
    }


    public Optional<User> updateUser(Map<String, String> userData) throws ServiceException {
        Optional<User> userOptional=null;
        if (UserValidator.getInstance().checkUserPersonalData(userData)){
            String password = PasswordEncoder.pasEncode(userData.get(PASSWORD));
            User user=new User(Long.valueOf(userData.get(USER_ID)),userData.get(EMAIL), password, userData.get(NAME),userData.get(SURNAME),
                    userData.get(PHONE_NUMBER), User.Role.CLIENT,User.Status.ACTIVE);
            try {
                userDao.updateUser(user);
                userOptional = userDao.findUserById(Long.valueOf(userData.get(USER_ID)));

            } catch (DaoException e) {
                logger.error("Error has occurred while registering user: " + e);
                throw new ServiceException("Error has occurred while registering user: ", e);
            }
        }
        return userOptional;
    }



    public boolean blockUser(String email) throws ServiceException {
        boolean flag= false;
        try {
            if (UserValidator.getInstance().checkEmail(email)) {
               if(userDao.updateUserStatusByEmail(email)){
                   flag= true;}}
        } catch (DaoException exception) {
            logger.error("Error has occurred while blocking of user with email \"{}\": {}", email, exception);
            throw new ServiceException("Error has occurred while blocking of user with email \"" + email + "\": ", exception);
        }
       return flag;
    }


    public boolean makeAdmin(String email) throws ServiceException {
        boolean flag= false;
        try {
            if (UserValidator.getInstance().checkEmail(email)) {
                if(userDao.makeAdmin(email)){
                    flag= true;}}
        } catch (DaoException exception) {
            logger.error("Error has occurred while creating admin with email \"{}\": {}", email, exception);
            throw new ServiceException("Error has occurred while creating admin with email \"" + email + "\": ", exception);
        }
        return flag;
    }
}
