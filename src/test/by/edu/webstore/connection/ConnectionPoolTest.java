package by.edu.webstore.connection;

import by.edu.webstore.exception.ConnectionPoolException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPoolTest {

    ConnectionPool connectionPool;

    @BeforeMethod
    public void init() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test
    public void testGetConnection() throws ConnectionPoolException {
        Connection connection = connectionPool.getConnection();
        Assert.assertTrue(connection != null);
    } Connection connection;




    @BeforeMethod
    public void before() throws ConnectionPoolException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }
    @Test
    public void testGetInstance() {
            Assert.assertNotNull(connection);
            connectionPool.releaseConnection(connection);
        }

    @Test
    public void testReleaseConnection() throws ConnectionPoolException {
        Connection connection = connectionPool.getConnection();
        connectionPool.releaseConnection(connection);
    }

    @Test(expectedExceptions = SQLException.class, expectedExceptionsMessageRegExp = ".*Access denied for user.*")
    private void testReleaseConnectionException() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop",
                "root", "pasS!671");
        connectionPool.releaseConnection(connection);
    }
    }

