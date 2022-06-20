package by.edu.webstore.controller.command;

/**
 * {@code Router} class represent complex form response of {@link Command}
 * It includes the page to which the transition should be made and sending type.
 */
public final class Router {

    /**
     * {@code RouterType} enum represent a sending type
     */
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
