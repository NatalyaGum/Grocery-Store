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

import java.util.List;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

import static by.edu.webstore.controller.command.Router.RouterType.FORWARD;

public class GoToUserMaintenanceCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final String USER_BLOCKED = "user.blocked";
    private static final String ADMIN_CRATED = "user.admin";
    private static final String ERROR_MESSAGE = "error";

    @Override
    public Router execute(HttpServletRequest request) {
        UserService service = ServiceProvider.getInstance().getUserService();
        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        if (request.getParameter(BLOCK_EMAIL) != null) {
            blockUserCommand(request);
        }
        if (request.getParameter(ADMIN_EMAIL) != null) {
            makeAdminCommand(request);
        }
        try {
            List<User> users = service.findAllEntities();
            request.setAttribute(ParameterAndAttribute.USERS_LIST, users);
            return new Router(PagePath.USER_MAINTENANCE, FORWARD);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllRoomsCommand was failed " + e);
            return new Router(PagePath.ERROR, FORWARD);
        }
    }

    void blockUserCommand(HttpServletRequest request) {
        UserService service = ServiceProvider.getInstance().getUserService();
        String email = request.getParameter(BLOCK_EMAIL);
        try {
            if (service.blockUser(email)) {
                request.setAttribute(MESSAGE, USER_BLOCKED);
            } else {
                request.setAttribute(MESSAGE, ERROR_MESSAGE);
            }
        } catch (ServiceException exception) {
            logger.error("Error has occurred while blocking user: " + exception);

        }
    }

    void makeAdminCommand(HttpServletRequest request) {
        UserService service = ServiceProvider.getInstance().getUserService();
        String email = request.getParameter(ADMIN_EMAIL);
        try {
            if (service.makeAdmin(email)) {
                request.setAttribute(MESSAGE, ADMIN_CRATED);
            } else {
                request.setAttribute(MESSAGE, ERROR_MESSAGE);
            }
        } catch (ServiceException exception) {
            logger.error("Error has occurred while admin created: " + exception);

        }

    }
}
