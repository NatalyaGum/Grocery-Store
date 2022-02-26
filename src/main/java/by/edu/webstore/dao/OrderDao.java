package by.edu.webstore.dao;


import by.edu.webstore.entity.Order;
import by.edu.webstore.entity.Product;
import by.edu.webstore.exception.DaoException;
import by.edu.webstore.exception.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderDao extends BaseDao<Order>{
    long insertNewOrder(Map<String, Object> orderData, HashMap<Product, Integer> productMap) throws DaoException;
    List<Order> findAllOrdersOfUser(long user_id, int offset, int limit) throws DaoException;
    int findTotalOrdersNumber(long user_id) throws DaoException;
}
