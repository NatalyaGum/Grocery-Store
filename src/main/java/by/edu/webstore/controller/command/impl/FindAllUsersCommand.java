package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.entity.User;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ServiceProvider;
import by.edu.webstore.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

import static by.edu.webstore.controller.command.Router.RouterType.FORWARD;

public class FindAllUsersCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {

        UserService service = ServiceProvider.getInstance().getUserService();

        Router router = null;
        try {
            List<User> users = service.findAllEntities();
            request.setAttribute(ParameterAndAttribute.USERS_LIST, users);
            router = new Router(PagePath.SHOW_USERS_PAGE, FORWARD);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllRoomsCommand was failed " + e);
            router = new Router(PagePath.ERROR, FORWARD);
        }
        return router;
    }
}
