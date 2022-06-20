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

public class UpdateProfileCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final String SIGN_IN_ERROR_MESSAGE_KEY = "error.sign_in";
    private static final String EMAIL_AVAILABILITY_ERROR_MESSAGE_KEY = "error.email_availability";
    private static final String UPDATE_PROFILE_MESSAGE_KEY = "confirm.update_account_data";


    @Override
    public Router execute(HttpServletRequest request) {
        if (checkPassword(request)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(USER);
            UserService service = ServiceProvider.getInstance().getUserService();
            Map<String, String> userData = new HashMap<>();
            userData.put(ParameterAndAttribute.USER_ID, Long.toString(user.getUserId()));
            userData.put(ParameterAndAttribute.PASSWORD, request.getParameter(ParameterAndAttribute.PASSWORD));
            userData.put(ParameterAndAttribute.REPEATED_PASSWORD, request.getParameter(ParameterAndAttribute.REPEATED_PASSWORD));
            userData.put(ParameterAndAttribute.SURNAME, request.getParameter(ParameterAndAttribute.SURNAME));
            userData.put(ParameterAndAttribute.NAME, request.getParameter(ParameterAndAttribute.NAME));
            userData.put(ParameterAndAttribute.EMAIL, request.getParameter(ParameterAndAttribute.EMAIL));
            userData.put(ParameterAndAttribute.PHONE_NUMBER, request.getParameter(ParameterAndAttribute.PHONE_NUMBER));
            try {
                if (!userData.get(EMAIL).equals(user.getEmail())) {
                    if (service.isEmailExist(userData)) {
                        request.setAttribute(ParameterAndAttribute.USER, userData);
                        request.setAttribute(ParameterAndAttribute.MESSAGE, EMAIL_AVAILABILITY_ERROR_MESSAGE_KEY);
                        return new Router(PagePath.UPDATE_PROFILE, Router.RouterType.FORWARD);
                    }
                }
                Optional<User> optionalUser = service.updateUser(userData);
                session.setAttribute(USER, optionalUser.get());
                session.setAttribute(ROLE, optionalUser.get().getRole().toString());
                session.setAttribute(ParameterAndAttribute.MESSAGE, UPDATE_PROFILE_MESSAGE_KEY);
                return new Router(PagePath.UPDATE_PROFILE, Router.RouterType.REDIRECT);
            } catch (ServiceException e) {
                logger.error("Error has occurred while signing up: " + e);
                return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
            }
        }
        return new Router(PagePath.UPDATE_PROFILE, Router.RouterType.FORWARD);
    }


    public boolean checkPassword(HttpServletRequest request) {
        UserService service = ServiceProvider.getInstance().getUserService();
        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        User user = (User) session.getAttribute(USER);
        String email = user.getEmail();
        String old_password = request.getParameter(OLD_PASSWORD);
        try {
            Optional<User> optionalUser = service.findUser(email, old_password);
            if (optionalUser.isPresent() && optionalUser.get().getStatus() != User.Status.BLOCKED) {
                return true;
            } else {
                request.setAttribute(MESSAGE, SIGN_IN_ERROR_MESSAGE_KEY);
                return false;
            }
        } catch (ServiceException exception) {
            logger.error("Error has occurred while signing in: " + exception);
        }
        return false;
    }
}
