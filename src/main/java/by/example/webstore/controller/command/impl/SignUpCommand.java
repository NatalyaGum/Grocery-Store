package by.example.webstore.controller.command.impl;

import by.example.webstore.controller.command.Command;
import by.example.webstore.controller.command.PagePath;
import by.example.webstore.controller.command.Router;
import by.example.webstore.exception.ServiceException;
import by.example.webstore.service.ServiceProvider;
import by.example.webstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.example.webstore.controller.command.ParameterAndAttribute.*;
import java.util.HashMap;
import java.util.Map;

public class SignUpCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final String SIGN_UP_CONFIRM_MESSAGE_KEY = "confirm.sign_up";
    private static final String SIGN_UP_ERROR_MESSAGE_KEY = "error.sign_up";
    private static final String EMAIL_AVAILABILITY_ERROR_MESSAGE_KEY = "error.email_availability";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService service = ServiceProvider.getInstance().getUserService();
        Map<String, String> userData = new HashMap<>();
        userData.put(PASSWORD, request.getParameter(PASSWORD));
        userData.put(REPEATED_PASSWORD, request.getParameter(REPEATED_PASSWORD));
        userData.put(SURNAME, request.getParameter(SURNAME));
        userData.put(NAME, request.getParameter(NAME));
        userData.put(EMAIL, request.getParameter(EMAIL));
        userData.put(PHONE_NUMBER, request.getParameter(PHONE_NUMBER));

        try {
            if (service.isEmailAvailable(userData)) {
                request.setAttribute(USER, userData);
                request.setAttribute(MESSAGE, EMAIL_AVAILABILITY_ERROR_MESSAGE_KEY);
                return new Router(PagePath.REGISTRATION_PAGE, Router.RouterType.FORWARD);
            }
            if (service.registerUser(userData)) {
                session.setAttribute(MESSAGE, SIGN_UP_CONFIRM_MESSAGE_KEY);
                return new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(USER, userData);
                request.setAttribute(MESSAGE, SIGN_UP_ERROR_MESSAGE_KEY);
                return new Router(PagePath.REGISTRATION_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Error has occurred while signing up: " + e);
            return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
        }
    }
}
