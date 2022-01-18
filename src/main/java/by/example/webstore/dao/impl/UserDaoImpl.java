package by.example.webstore.dao.impl;

import by.example.webstore.connection.ConnectionPool;
import by.example.webstore.dao.UserDao;
import by.example.webstore.entity.User;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements UserDao {
    static Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_NEW_USER = """
            INSERT INTO users (name, surname, email, password, phone, role, status)
            VALUES(?, ?, ?, ?, ?, ?, ?)""";
    private static final String FIND_USER_BY_ID = """
            SELECT id_user, name, surname, email, password, phone, role, status
            FROM users         
            WHERE users.id=?""";
    private static final String FIND_USER_BY_EMAIL = "SELECT id_user FROM users WHERE email=?";
    private static final String FIND_USER_BY_PHONE = "SELECT id_user FROM users WHERE phone=?";
    private static final String FIND_USER_BY_ID_AND_PASSWORD = "SELECT id_user FROM users WHERE id=? AND password=?";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = """
            SELECT id_user, name, surname, email, password, phone, role, FROM users status WHERE email=? AND password=?""";
    private static final String FIND_ALL_USERS = """
            SELECT id_user, name, surname, email, password, phone, role, status FROM users""";

    private static final String UPDATE_USER_STATUS_BY_USER_ID = "UPDATE users SET status=? WHERE id_user=?";
    private static final String UPDATE_USER_NAME = "UPDATE users SET name=? WHERE id_user=?";
    private static final String UPDATE_USER_SURNAME = "UPDATE users SET surname=? WHERE id_user=?";
    private static final String UPDATE_USER_MOBILE_NUMBER = "UPDATE users SET phone=? WHERE id_user=?";
    private static final String UPDATE_USER_EMAIL = "UPDATE users SET email=? WHERE id_user=?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET password=? WHERE id_user=?";


    @Override
    public Optional<User> findEntityById(long id) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setLong(1, id);
           try (ResultSet resultSet = statement.executeQuery()){
            if (resultSet.next()) {
                User user = UserCreator.getInstance().createUser(resultSet);
                userOptional = Optional.of(user);
            }}
            logger.error("findEntityById method was completed successfully."
                    + ((userOptional.isPresent()) ? " User with id " + id + " was found" : " User with id " + id + " don't exist"));
            return userOptional;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find user by id. Database access error:", e);
            throw new DaoException("Impossible to find user by id. Database access error:", e);
        }
    }

    @Override
    public List<User> findAllEntities() throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS)){
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = UserCreator.getInstance().createUser(resultSet);
                users.add(user);
            }
            logger.debug("findAllEntities method was completed successfully. " + users.size() + " were found");
            return users;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to find users. Database access error:", e);
            throw new DaoException("Impossible to find users. Database access error:", e);
        }
    }


    @Override
    public long insertNewEntity(User user) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER, RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getRole().name().toLowerCase());
            statement.setString(7, user.getStatus().name().toLowerCase());
            statement.executeUpdate();
            try ( ResultSet resultSet = statement.getGeneratedKeys()){
            long userId = 0;
            if (resultSet.next()) {
                userId = resultSet.getLong(1);
                logger.info("insertNewEntity method was completed successfully. User with id " + userId + " was added");
            }
            return userId;
        }} catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to insert new user into database. Database access error:", e);
            throw new DaoException("Impossible to insert new user into database. Database access error:", e);
        }
    }


    @Override
    public boolean updateUserName(long userId, String firstName) throws  DaoException  {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_NAME)) {
            statement.setString(1, firstName);
            statement.setLong(2, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.debug("Result of user first name update for user with id " + userId + " is " + result);
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to update user first name. Database access error:", e);
            throw new DaoException("Impossible to update user first name. Database access error:", e);
        }
    }


    @Override
    public boolean updateUserSurname(long userId, String lastName) throws  DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SURNAME)) {
            statement.setString(1, lastName);
            statement.setLong(2, userId);
            boolean result = statement.executeUpdate() ==1;
            logger.debug("Result of user last name update for user with id " + userId + " is " + result);
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to update user last name. Database access error:", e);
            throw new DaoException("Impossible to update user last name. Database access error:", e);
        }
    }

    @Override
    public boolean updateUserPhone(long userId, String mobileNumber) throws  DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_MOBILE_NUMBER)) {
            statement.setString(1, mobileNumber);
            statement.setLong(2, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.info("Result of user mobile number update for user with id " + userId + " is " + result);
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to update user mobile number. Database access error:", e);
            throw new DaoException("Impossible to update user mobile number. Database access error:", e);
        }
    }

    @Override
    public boolean updateUserEmail(long userId, String email) throws  DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_EMAIL)) {
            statement.setString(1, email);
            statement.setLong(2, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.info("Result of user email update for user with id " + userId + " is " + result);
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to update user email. Database access error:", e);
            throw new DaoException("Impossible to update user email. Database access error:", e);
        }
    }

    @Override
    public boolean updateUserPassword(long userId, String password) throws  DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            statement.setString(1, password);
            statement.setLong(2, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.info("Result of user password update for user with id " + userId + " is " + result);
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to update user password. Database access error:", e);
            throw new DaoException("Impossible to update user password. Database access error:", e);
        }
    }

    @Override
    public boolean updateUserStatusById(User.Status status, Long userId) throws  DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_STATUS_BY_USER_ID)) {
                statement.setString(1, status.name());
                statement.setLong(2, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.info( "updateUserStatusesById method was completed successfully. User status with user id  "
                    + userId + " were updated to " + status + " status");
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to update users statuses. Database access error:", e);
            throw new DaoException("Impossible to update users statuses. Database access error:", e);
        }
    }

    @Override
    public boolean isEmailExist(String email) throws  DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            try (  ResultSet resultSet = statement.executeQuery()){
            boolean result = resultSet.isBeforeFirst();
            logger.debug( "isEmailExist method was completed successfully. Result: " + result);
            return result;
        }} catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to check existence of user email. Database access error:", e);
            throw new DaoException("Impossible to check existence of user email. Database access error:", e);
        }
    }

    @Override
    public boolean isMobileNumberExist(String mobileNumber) throws  DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_PHONE)) {
            statement.setString(1, mobileNumber);
            try (ResultSet resultSet = statement.executeQuery()){
            boolean result = resultSet.isBeforeFirst();
            logger.debug("isMobileNumberExist method was completed successfully. Result: " + result);
            return result;
        }} catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to check existence of user mobile number. Database access error:", e);
            throw new DaoException("Impossible to check existence of user mobile number. Database access error:", e);
        }
    }

    @Override
    public boolean isUserExist(long userId, String passwordHash) throws  DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID_AND_PASSWORD)) {
            statement.setLong(1, userId);
            statement.setString(2, passwordHash);
            try (  ResultSet resultSet = statement.executeQuery()){
            boolean result = resultSet.isBeforeFirst();
            logger.debug( "isUserExist method was completed successfully. Result: " + result);
            return result;
        }} catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to check existence of user. Database access error:", e);
            throw new DaoException("Impossible to check existence of user. Database access error:", e);
        }
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws  DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()){
            if (resultSet.next()) {
                User user = UserCreator.getInstance().createUser(resultSet);
                userOptional = Optional.of(user);
            }}
            logger.debug("findUserByLoginAndPassword method was completed successfully." +
                    (userOptional.map(user -> " User with id " + user.getUserId() + " was found").orElse(" User with these email and password don't exist")));
            return userOptional;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to find user in database. Database access error:", e);
            throw new DaoException("Impossible to find user in database. Database access error:", e);
        }
    }

}






