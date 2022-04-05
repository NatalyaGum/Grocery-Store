package by.edu.webstore.dao.impl;

import by.edu.webstore.connection.ConnectionPool;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.dao.*;
import by.edu.webstore.entity.Address;
import by.edu.webstore.entity.Order;
import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.ConnectionPoolException;
import by.edu.webstore.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.edu.webstore.controller.command.ParameterAndAttribute.ADDRESS_ID;
import static by.edu.webstore.dao.ColumnName.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

import java.math.BigDecimal;
import java.sql.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

/**
 *  The {@link OrderDaoImpl} class provides access to
 *  orders table in the database
 */
public class OrderDaoImpl implements OrderDao {

    static Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    //private static final ProductDao productDao = DaoProvider.getInstance().getProductDao();
 //private static final AddressDao addressDao = DaoProvider.getInstance().getAddressDao();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
    AddressDao addressDao = new AddressDaoImpl();
    ProductDao productDao = new ProductDaoImpl();
    UserDao userDao=new UserDaoImpl();

    //private static final String FIND_ORDER_PAYMENT_TYPE = "SELECT cash FROM orders WHERE id=?";
    private static final String FIND_ORDERS_BY_STATUSES = """
            SELECT orders.id_order, cost, order_status,date, payment_method, orders.users_user_id, created, id_address, street, build_number, apartment, comment FROM orders
            JOIN  addresses ON addresses.id_address=orders.delivery_address_id          
            WHERE status_id IN ()""";
    //private static final String INDEX = "?, ";
    // private static final int STARTING_INPUT_INDEX = 326;
    // private static final String ORDER_BY_ORDER_ID_STATUSES = " ORDER BY order_statuses.id";
    private static final String FIND_ORDER_BY_STATUS = """
            SELECT orders.id_order, cost, order_status,date, payment_method, orders.users_user_id, created, id_address, street, build_number, apartment, comment FROM orders
            JOIN  addresses ON addresses.id_address=orders.delivery_address_id  ORDER by  order_status""";
    private static final String FIND_ORDERS_BY_USER_ID = """
            SELECT name, email, phone, id_order, cost, order_status,date, payment_method, orders.users_user_id, created, 
            id_address, street, build_number, apartment, comment 
            FROM orders
            JOIN  addresses ON addresses.id_address=orders.delivery_address_id 
            JOIN users ON orders.users_user_id=users.id_user  ORDER by order_status
            WHERE orders.users_user_id=?""";
    private static final String INSERT_NEW_ORDER = "INSERT INTO orders (cost, order_status, payment_method, users_user_id,delivery_address_id) VALUES (?, ?, ?, ?, ?)";
    private static final String ADD_PRODUCT_TO_ORDER = "INSERT INTO purchases (id_order, products_id_product, quantity) VALUE (?, ?, ?)";
    private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET order_status=? WHERE id_order=?";
    private static final String FIND_ORDERS_BY_USER_ID_PAGES = """
            SELECT name, email, phone, id_order, cost, order_status,date, payment_method, orders.users_user_id, 
            delivery_address_id, street, building, apartment, comment
            FROM orders
            JOIN  addresses ON addresses.id_address=orders.delivery_address_id 
            JOIN users ON orders.users_user_id=users.id_user  
            WHERE orders.users_user_id=?
            ORDER BY id_order DESC  LIMIT ?,?""";
    private static final String FIND_ORDERS_PAGES = """
            SELECT id_order, cost, order_status,date, payment_method, users_user_id, delivery_address_id
            FROM orders           
            ORDER BY id_order DESC  LIMIT ?,?""";
    private static final String FIND_PRODUCTS_OF_ORDER = "SELECT products_id_product, quantity FROM purchases WHERE id_order=?";
    private static final String FIND_TOTAL_ORDERS_NUMBER_USERS ="SELECT COUNT(id_order) FROM orders WHERE users_user_id=?";
    private static final String FIND_TOTAL_ORDERS_NUMBER ="SELECT COUNT(id_order) FROM orders";

    @Override
    public List<Order> findAllEntities() throws DaoException, ConnectionPoolException {
        return null;
    }

    public long insertNewOrder(Order order) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement orderStatement = connection.prepareStatement(INSERT_NEW_ORDER, RETURN_GENERATED_KEYS);
                 PreparedStatement mealStatement = connection.prepareStatement(ADD_PRODUCT_TO_ORDER))
                 {
                orderStatement.setBigDecimal(1, order.getCost());
                orderStatement.setString(2, order.getStatus().toString());
                orderStatement.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
              //  orderStatement.setString(4, order.getMethod().toString());
               orderStatement.setLong(5, order.getUser().getUserId());
                orderStatement.setLong(6, order.getAddress().getAddressId());
                orderStatement.executeUpdate();
                ResultSet resultSet = orderStatement.getGeneratedKeys();
                long orderId = 0;
                if (resultSet.next()) {
                    orderId = resultSet.getLong(1);
                    for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
                        //for (Map.Entry entry : orderMap.entrySet()) {
                        long productId = entry.getKey().getProductId();
                        int quantity = entry.getValue();
                        mealStatement.setLong(1, orderId);
                        mealStatement.setLong(2, productId);
                        mealStatement.setInt(3, quantity);
                        mealStatement.addBatch();
                    }
                    mealStatement.executeBatch();
                }
                connection.commit();
                logger.error( "insertNewOrder method was completed successfully. Order with id " + orderId + " was added");
                return orderId;
            }
        } catch (SQLException | ConnectionPoolException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                logger.error( "Change cancellation error in the current transaction:", throwables);
            }
            logger.error( "Impossible to insert order into database. Database access error:", e);
            throw new DaoException("Impossible to insert order into database. Database access error:", e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException throwables) {
                logger.error( "Database access error occurs:", throwables);
            }
        }
    }

    public long insertNewOrder(Map<String, Object> orderData, HashMap<Product, Integer> productMap) throws DaoException {
        Connection connection = null;
        User user=(User)orderData.get(USER);
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement orderStatement = connection.prepareStatement(INSERT_NEW_ORDER, RETURN_GENERATED_KEYS);
                 PreparedStatement purchasesStatement = connection.prepareStatement(ADD_PRODUCT_TO_ORDER))
            {
                orderStatement.setBigDecimal(1,(BigDecimal) orderData.get(TOTAL));
                orderStatement.setString(2, Order.OrderStatus.ORDERED.toString().toLowerCase());
               // orderStatement.setTime(3,//(Time)orderData.get(DATE));//Timestamp.valueOf(order.getOrderDate()));
                orderStatement.setString(3, orderData.get(PAYMENT_METHOD).toString().toLowerCase());
                orderStatement.setLong(4, user.getUserId());
                orderStatement.setLong(5, (Long)orderData.get(ADDRESS_ID));
                orderStatement.executeUpdate();
                ResultSet resultSet = orderStatement.getGeneratedKeys();
                long orderId = 0;
                if (resultSet.next()) {
                    orderId = resultSet.getLong(1);
                    for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
                        //for (Map.Entry entry : orderMap.entrySet()) {
                        long productId = entry.getKey().getProductId();
                        int quantity = entry.getValue();
                        purchasesStatement.setLong(1, orderId);
                        purchasesStatement.setLong(2, productId);
                        purchasesStatement.setInt(3, quantity);
                        purchasesStatement.addBatch();
                    }
                    purchasesStatement.executeBatch();
                }
                connection.commit();
                logger.error( "insertNewOrder method was completed successfully. Order with id " + orderId + " was added");
                return orderId;
            }
        } catch (SQLException | ConnectionPoolException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                logger.error( "Change cancellation error in the current transaction:", throwables);
            }
            logger.error( "Impossible to insert order into database. Database access error:", e);
            throw new DaoException("Impossible to insert order into database. Database access error:", e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException throwables) {
                logger.error( "Database access error occurs:", throwables);
            }
        }
    }


    public List<Order> findAllOrdersOfUser(long user_id,int offset, int limit) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID_PAGES)) {
            statement.setLong(1, user_id);
            statement.setInt(2, offset);
            statement.setInt(3, limit);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                HashMap<Product,Integer> productMap=new HashMap<>();
                long order_id=resultSet.getLong(ORDER_ID);
                PreparedStatement statementProducts = connection.prepareStatement(FIND_PRODUCTS_OF_ORDER);
                statementProducts.setLong(1, order_id);
                ResultSet resultSetProductId = statementProducts.executeQuery();
                while (resultSetProductId.next()) {
                        Optional<Product> optionalProduct=productDao.findProductById(resultSetProductId.getLong(PURCHASES_ID_PRODUCT));
                        productMap.put(optionalProduct.get(),resultSetProductId.getInt(PURCHASES_QUANTITY));
                    }
                long address_id=resultSet.getLong(ORDER_ADDRESS_ID);
                Optional<Address> addressOptional=addressDao.findAddressById(address_id);
                Order order = new Order();
                order.setOrderId(order_id);
                order.setOrderDate(resultSet.getTimestamp(ORDER_DATE).toLocalDateTime());
                order.setCost(resultSet.getBigDecimal(ORDER_COST));
                order.setStatus(Order.OrderStatus.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()));
                order.setMethod(Order.PaymentMethod.valueOf(resultSet.getString(ORDER_METHOD).toUpperCase()));
                order.setProducts(productMap);
                order.setAddress(addressOptional.get());
                orders.add(order);
            }
            logger.info("findAllEntities method was completed successfully. " + orders.size() + " All orders were found");
            return orders;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find all orders. Database error:", e);
            throw new DaoException("Impossible to find all orders. Database error:", e);
        }
    }

    public int findTotalOrdersNumberOfUser(long user_id) throws DaoException {
        int result = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TOTAL_ORDERS_NUMBER_USERS)) {
            statement.setLong(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            int  ordersNumber = 0;
            while (resultSet.next()) {
                ordersNumber=resultSet.getInt(1);
            }
            logger.info("findTotalProductsNumber method was completed successfully. " + ordersNumber + " All products were found");
            return ordersNumber;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find all orders. Database error:", e);
            throw new DaoException("Impossible to find all orders. Database error:", e);
        }
    }



    public List<Order> findAllOrders(int offset, int limit) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_PAGES)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                HashMap<Product,Integer> productMap=new HashMap<>();
                long order_id=resultSet.getLong(ORDER_ID);
                PreparedStatement statementProducts = connection.prepareStatement(FIND_PRODUCTS_OF_ORDER);
                statementProducts.setLong(1, order_id);
                ResultSet resultSetProductId = statementProducts.executeQuery();
                while (resultSetProductId.next()) {
                    Optional<Product> optionalProduct=productDao.findProductById(resultSetProductId.getLong(PURCHASES_ID_PRODUCT));
                    productMap.put(optionalProduct.get(),resultSetProductId.getInt(PURCHASES_QUANTITY));
                }
                long address_id=resultSet.getLong(ORDER_ADDRESS_ID);
                Optional<Address> addressOptional=addressDao.findAddressById(address_id);
                long userId=resultSet.getLong(ORDER_USER_ID);
                Optional<User> userOptional=userDao.findUserById(userId);
                Order order = new Order();
                order.setOrderId(order_id);
                order.setOrderDate(resultSet.getTimestamp(ORDER_DATE).toLocalDateTime());
                order.setCost(resultSet.getBigDecimal(ORDER_COST));
                order.setStatus(Order.OrderStatus.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()));
                order.setMethod(Order.PaymentMethod.valueOf(resultSet.getString(ORDER_METHOD).toUpperCase()));
                order.setProducts(productMap);
                order.setAddress(addressOptional.get());
                order.setUser(userOptional.get());
                orders.add(order);
            }
            logger.info("findAllEntities method was completed successfully. " + orders.size() + " All orders were found");
            return orders;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find all orders. Database error:", e);
            throw new DaoException("Impossible to find all orders. Database error:", e);
        }
    }
    public int findTotalOrdersNumber() throws DaoException {
       // int result = 0;
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_TOTAL_ORDERS_NUMBER)) {
            int  ordersNumber = 0;
            while (resultSet.next()) {
                ordersNumber=resultSet.getInt(1);
            }
            logger.info("findTotalProductsNumber method was completed successfully. " + ordersNumber + " All products were found");
            return ordersNumber;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to find all orders. Database error:", e);
            throw new DaoException("Impossible to find all orders. Database error:", e);
        }
    }

    public boolean updateOrderStatus(long orderId, String orderStatus ) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            statement.setString(1,orderStatus );
            statement.setLong(2, orderId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to update order into database. Database error:", e);
            throw new DaoException("Impossible to update order into database. Database error:", e);
        }return result;
    }
    }

