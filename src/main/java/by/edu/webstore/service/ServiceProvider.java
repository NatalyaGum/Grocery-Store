package by.edu.webstore.service;


import by.edu.webstore.service.impl.OrderServiceImpl;
import by.edu.webstore.service.impl.UserServiceImpl;
import by.edu.webstore.service.impl.ProductServiceImpl;

/**
 * {@code ServiceProvider} class hold and provide instance of all service classes
 */
public final class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final UserService userService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
