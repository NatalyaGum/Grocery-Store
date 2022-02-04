package by.example.webstore.service;


import by.example.webstore.service.impl.ProductServiceImpl;
import by.example.webstore.service.impl.UserServiceImpl;

public final class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final UserService userService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

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
}
