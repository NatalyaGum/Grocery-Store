package by.example.webstore.service;

import by.example.webstore.service.impl.UserServiceImpl;

public final class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final UserService userService = new UserServiceImpl();

    private ServiceProvider() {
    }
    public static ServiceProvider getInstance() {
        return instance;
    }
    public UserService getUserService() {
        return userService;
    }
}
