package by.edu.webstore.dao.impl;

import by.edu.webstore.dao.OrderDao;
import by.edu.webstore.entity.Order;
import by.edu.webstore.exception.ConnectionPoolException;
import by.edu.webstore.exception.DaoException;

import java.util.List;

/**
 *  The {@link OrderDaoImpl} class provides access to
 *  orders table in the database
 */
public class OrderDaoImpl implements OrderDao {



    @Override
    public List<Order> findAllEntities() throws DaoException, ConnectionPoolException {
        return null;
    }
}
