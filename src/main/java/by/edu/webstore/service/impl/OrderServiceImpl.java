package by.edu.webstore.service.impl;

import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.dao.*;
import by.edu.webstore.entity.Address;
import by.edu.webstore.entity.Order;
import by.edu.webstore.entity.Product;
import by.edu.webstore.exception.DaoException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.OrderService;
import by.edu.webstore.util.validator.AddressValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    static Logger logger = LogManager.getLogger();
    private static final String INCORRECT_VALUE_PARAMETER = "incorrect";
    private static final UserDao userDao = DaoProvider.getInstance().getUserDao();
    private static final ProductDao productDao = DaoProvider.getInstance().getProductDao();
    private static final AddressDao addressDao = DaoProvider.getInstance().getAddressDao();
    private static final OrderDao orderDao = DaoProvider.getInstance().getOrderDao();

    public long insertNewAddress(Map<String, String> addressData) throws ServiceException {
        long result = 0;
        int apartmentNumber;
        String comment;
        if (AddressValidator.getInstance().checkAddressData(addressData)) {
            if(addressData.containsKey(ParameterAndAttribute.APARTMENT)) {
                apartmentNumber=Integer.parseInt(addressData.get(ParameterAndAttribute.APARTMENT));
            }else{apartmentNumber=0;}
            if(addressData.containsKey(ParameterAndAttribute.COMMENT)){
                comment=addressData.get(ParameterAndAttribute.COMMENT);
            }else{comment="NO COMMENTS";}
            Address address = new Address(addressData.get(ParameterAndAttribute.STREET),
                    addressData.get(ParameterAndAttribute.BUILDING),
                    apartmentNumber,
                    comment);
            try {
                result = addressDao.insertNewAddress(address,Long.valueOf(addressData.get(ParameterAndAttribute.USER_ID))) ;
            } catch (DaoException e) {
                logger.error("Address cannot be added:", e);
                throw new ServiceException("Address cannot be added:", e);
            }catch (NumberFormatException e) {
                logger.warn("Apartment parameter doesn't contain number");
        }
    }  return result;
    }

    public List<Address> findUserAddresses(long userId) throws ServiceException {
        try {
            return addressDao.findUserAddresses(userId);
        } catch (DaoException e) {
            logger.error("Addresses cannot be found:", e);
            throw new ServiceException("Addresses cannot be found:", e);
        }
    }

    public long  insertNewOrder(Map<String, Object> orderData, HashMap<Product, Integer> productMap)throws ServiceException {
        try {
            return orderDao.insertNewOrder(orderData, productMap);
           /* Order order;
            order.setProducts(productMap);
            order.setOrderDate((LocalDateTime)orderData.get(DATE));
            order.setCost(((BigDecimal)orderData.get(TOTAL)));
           // order.setMethod();*/
        } catch (DaoException e) {
            logger.error("Order cannot be added:", e);
            throw new ServiceException("Order cannot be added:", e);
        }
    }

    public List<Order> findAllOrdersOfUser(long user_id,int offset, int limit) throws ServiceException {
        try {
            return orderDao.findAllOrdersOfUser(user_id,offset, limit);
        } catch (DaoException e) {
            logger.error("product cannot be found:", e);
            throw new ServiceException("Products cannot be found:", e);

        }
    }

    public int findTotalOrdersNumberOfUser(long user_id) throws ServiceException {
        try {
            return orderDao.findTotalOrdersNumberOfUser(user_id);
        } catch (DaoException e) {
            logger.error("product cannot be found:", e);
            throw new ServiceException("Products cannot be found:", e);

        }
    }

    public List<Order> findAllOrders(int offset, int limit) throws ServiceException {
        try {
            return orderDao.findAllOrders(offset, limit);
        } catch (DaoException e) {
            logger.error("product cannot be found:", e);
            throw new ServiceException("Products cannot be found:", e);

        }
    }


    public int findTotalOrdersNumber() throws ServiceException {
        try {
            return orderDao.findTotalOrdersNumber();
        } catch (DaoException e) {
            logger.error("orders cannot be found:", e);
            throw new ServiceException("Orders cannot be found:", e);

        }
    }

    public boolean updateOrderStatus(long orderId, String status)throws ServiceException {
        try {
            return orderDao.updateOrderStatus(orderId,status);
        } catch (DaoException e) {
            logger.error("Order cannot be updated:", e);
            throw new ServiceException("Order cannot be updated:", e);

        }
    }
}



