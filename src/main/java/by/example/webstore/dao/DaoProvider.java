package by.example.webstore.dao;

import by.example.webstore.dao.impl.AddressDaoImpl;
import by.example.webstore.dao.impl.ProductDaoImpl;
import by.example.webstore.dao.impl.UserDaoImpl;


public class DaoProvider {
    private UserDao userDao = new UserDaoImpl();
    private AddressDao addressDao = new AddressDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
   // private OrderDao orderDao = new OrderDaoImpl();

    private DaoProvider() {
    }

    private static class DaoProviderHolder {
        private static final DaoProvider instance = new DaoProvider();
    }

    public static DaoProvider getInstance() {
        return DaoProviderHolder.instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }
/*
    public OrderDao getOrderDao() {
        return orderDao;
    }*/
}
