package by.edu.webstore.dao;

import by.edu.webstore.connection.ConnectionPool;
import by.edu.webstore.entity.AbstractEntity;
import by.edu.webstore.exception.ConnectionPoolException;
import by.edu.webstore.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * {@code BaseDao} interface represent common functional to dao classes
 */

public interface BaseDao<T extends AbstractEntity> {
    Logger logger = LogManager.getLogger();

    List<T> findAllEntities() throws DaoException, ConnectionPoolException;

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
