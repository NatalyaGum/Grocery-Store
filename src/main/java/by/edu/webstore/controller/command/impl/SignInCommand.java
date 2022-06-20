package by.edu.webstore.controller.command.impl;


import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ServiceProvider;
import by.edu.webstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class SignInCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final String SIGN_IN_ERROR_MESSAGE_KEY = "error.sign_in";

    @Override
    public Router execute(HttpServletRequest request) {
        UserService service = ServiceProvider.getInstance().getUserService();
        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        try {
            Optional<User> optionalUser = service.findUser(email, password);
            if (optionalUser.isPresent() && optionalUser.get().getStatus() != User.Status.BLOCKED) {
                session.setAttribute(USER, optionalUser.get());
                session.setAttribute(ROLE, optionalUser.get().getRole().toString());
                session.removeAttribute(MESSAGE);
                return new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(USER_EMAIL, email);
                request.setAttribute(MESSAGE, SIGN_IN_ERROR_MESSAGE_KEY);
                return new Router(PagePath.MAIN_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException exception) {
            logger.error("Error has occurred while signing in: " + exception);
            return new Router(PagePath.ERROR_404, Router.RouterType.REDIRECT);
        }
    }
}

