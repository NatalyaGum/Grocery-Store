package by.edu.webstore.dao;

import by.edu.webstore.dao.impl.OrderDaoImpl;
import by.edu.webstore.dao.impl.ProductDaoImpl;
import by.edu.webstore.dao.impl.AddressDaoImpl;
import by.edu.webstore.dao.impl.UserDaoImpl;

/**
 * {@code DaoProvider} class hold and provide instance of all classes extends {@link BaseDao}
 */

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private UserDao userDao = new UserDaoImpl();
    private AddressDao addressDao = new AddressDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();

    private DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return DaoProvider.instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }
}
