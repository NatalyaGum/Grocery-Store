package by.edu.webstore.controller.command.listener;

import by.edu.webstore.connection.ConnectionPool;
import by.edu.webstore.exception.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    static Logger logger = LogManager.getLogger();
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error("Error closing connection", e);
        }
    }
}