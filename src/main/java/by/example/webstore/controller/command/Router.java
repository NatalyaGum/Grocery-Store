package by.example.webstore.controller.command;

public final class Router {
    public enum RouterType {
        FORWARD, REDIRECT
    }

    private final String pagePath;
    private final RouterType routeType;


    public Router(String pagePath, RouterType routeType) {
        this.pagePath = pagePath;
        this.routeType = routeType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouteType() {
        return routeType;
    }

}
