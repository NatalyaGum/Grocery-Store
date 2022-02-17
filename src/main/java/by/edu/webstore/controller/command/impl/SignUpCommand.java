package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ServiceProvider;
import by.edu.webstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class SignUpCommand implements Command {
    static Logger logger = LogManager.getLogger();
    //private static final String SIGN_UP_CONFIRM_MESSAGE_KEY = "confirm.sign_up";
    private static final String SIGN_UP_ERROR_MESSAGE_KEY = "error.sign_up";
    private static final String EMAIL_AVAILABILITY_ERROR_MESSAGE_KEY = "error.email_availability";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService service = ServiceProvider.getInstance().getUserService();
        Map<String, String> userData = new HashMap<>();
        userData.put(ParameterAndAttribute.PASSWORD, request.getParameter(ParameterAndAttribute.PASSWORD));
        userData.put(ParameterAndAttribute.REPEATED_PASSWORD, request.getParameter(ParameterAndAttribute.REPEATED_PASSWORD));
        userData.put(ParameterAndAttribute.SURNAME, request.getParameter(ParameterAndAttribute.SURNAME));
        userData.put(ParameterAndAttribute.NAME, request.getParameter(ParameterAndAttribute.NAME));
        userData.put(ParameterAndAttribute.EMAIL, request.getParameter(ParameterAndAttribute.EMAIL));
        userData.put(ParameterAndAttribute.PHONE_NUMBER, request.getParameter(ParameterAndAttribute.PHONE_NUMBER));

        try {
            if (service.isEmailAvailable(userData)) {
                request.setAttribute(ParameterAndAttribute.USER, userData);
                request.setAttribute(ParameterAndAttribute.MESSAGE, EMAIL_AVAILABILITY_ERROR_MESSAGE_KEY);
                return new Router(PagePath.REGISTRATION_PAGE, Router.RouterType.FORWARD);
            }Optional<User> optionalUser =service.registerUser(userData);
            if (optionalUser.isPresent()) {
                //session.setAttribute(ParameterAndAttribute.MESSAGE, SIGN_UP_CONFIRM_MESSAGE_KEY);
                session.setAttribute(USER, optionalUser.get());
                session.setAttribute(ROLE, optionalUser.get().getRole().toString());
                return new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(ParameterAndAttribute.USER, userData);
                request.setAttribute(ParameterAndAttribute.MESSAGE, SIGN_UP_ERROR_MESSAGE_KEY);
                return new Router(PagePath.REGISTRATION_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Error has occurred while signing up: " + e);
            return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
        }
    }
}
