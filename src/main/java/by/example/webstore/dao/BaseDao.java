package by.example.webstore.dao;

import by.example.webstore.connection.ConnectionPool;
import by.example.webstore.entity.AbstractEntity;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity> {
    static Logger logger = LogManager.getLogger();

    Optional<T> findEntityById(long id) throws DaoException;

    List<T> findAllEntities() throws DaoException, ConnectionPoolException;

    long insertNewEntity(T entity) throws DaoException, ConnectionPoolException;


    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException exception) {
            logger.error("Error has occurred while closing statement: " + exception);
        }
    }


    default void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException exception) {
                logger.error("Error has occurred while closing ResultSet: " + exception);
            }
        }
    }

    default void close(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
