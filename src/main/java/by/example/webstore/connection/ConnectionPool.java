package by.example.webstore.connection;

import by.example.webstore.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    static Logger logger = LogManager.getLogger();
    private static final String BUNDLE_NAME = "db";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DATABASE_URL;
    private static final String DATABASE_USER_NAME;
    private static final String DATABASE_PASSWORD;

    private static final int DEFAULT_POOL_SIZE = 4;

    private static ConnectionPool instance;
    private static AtomicBoolean instanceIsExist = new AtomicBoolean(false);
    private static Lock instanceLocker = new ReentrantLock();
    private BlockingQueue<ProxyConnection> freeConnections = new ArrayBlockingQueue<ProxyConnection>(DEFAULT_POOL_SIZE);
    private BlockingQueue<ProxyConnection> givenAwayConnections = new ArrayBlockingQueue<ProxyConnection>(DEFAULT_POOL_SIZE);

    static {

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
            String driverName = bundle.getString(DB_DRIVER);
            DATABASE_URL = bundle.getString(DB_URL);
            DATABASE_USER_NAME = bundle.getString(DB_USER);
            DATABASE_PASSWORD = bundle.getString(DB_PASSWORD);
            Class.forName(driverName);

        } catch (MissingResourceException e) {
            logger.fatal("Property file not found or has incorrect data " + BUNDLE_NAME, e);
            throw new RuntimeException("Property file not found or has incorrect data " + BUNDLE_NAME, e);
        } catch (ClassNotFoundException e) {
            logger.fatal("Driver don't found" + BUNDLE_NAME, e);
            throw new RuntimeException("Driver don't found" + BUNDLE_NAME, e);
        }
    }


    private ConnectionPool() {

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.error("Database access error, connection not received", e);
            }}
            if (freeConnections.isEmpty()|| freeConnections.size()<DEFAULT_POOL_SIZE) {
                logger.fatal("Error: no connections were created");
                throw new RuntimeException("Error: no connections were created");
            }
            logger.info( "Connection pool was created");
        }


    public static ConnectionPool getInstance() {
        if (!instanceIsExist.get()) {
            instanceLocker.lock();
            try {
                if (instanceIsExist.compareAndSet(false, true)) {
                    instance = new ConnectionPool();
                }
            } finally {
                instanceLocker.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Error getting connection", e);
            throw new ConnectionPoolException("Error getting connection", e);
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        boolean isReleased = false;
        if (givenAwayConnections.remove(connection)) {
            isReleased = freeConnections.offer((ProxyConnection) connection);
        }
        return isReleased;
    }

    public void destroyPool() throws ConnectionPoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread was interrupted while taking a free connection", e);
                throw new ConnectionPoolException("Thread was interrupted while taking a free connection", e);
            } catch (SQLException e) {
                logger.error("Error closing connection", e);
                throw new ConnectionPoolException("Error closing connection", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() throws ConnectionPoolException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Driver deregistration error", e);
                throw new ConnectionPoolException("Driver deregistration error", e);
            }
        }
    }

   private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
    }
}
