package by.edu.webstore.service;

import by.edu.webstore.entity.Address;
import by.edu.webstore.entity.Order;
import by.edu.webstore.entity.Product;
import by.edu.webstore.exception.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code OrderService} interface represent functional business logic
 * for work with class {@link by.edu.webstore.entity.Order}
 */
public interface OrderService {
    long insertNewAddress(Map<String, String> addressData) throws ServiceException;

    List<Address> findUserAddresses(long userId) throws ServiceException;

    long insertNewOrder(Map<String, Object> orderData, HashMap<Product, Integer> productMap) throws ServiceException;

    List<Order> findAllOrdersOfUser(long user_id, int offset, int limit) throws ServiceException;

    int findTotalOrdersNumberOfUser(long user_id) throws ServiceException;

    List<Order> findAllOrders(int offset, int limit) throws ServiceException;

    int findTotalOrdersNumber() throws ServiceException;

    boolean updateOrderStatus(long orderId, String status) throws ServiceException;
}
