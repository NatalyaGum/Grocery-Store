package by.example.webstore.controller.command.impl;

import by.example.webstore.controller.command.Command;
import by.example.webstore.controller.command.PagePath;
import by.example.webstore.controller.command.ParameterAndAttribute;
import by.example.webstore.controller.command.Router;
import by.example.webstore.entity.User;
import by.example.webstore.exception.ServiceException;
import by.example.webstore.service.ServiceProvider;
import by.example.webstore.service.UserService;

import by.example.webstore.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

import static by.example.webstore.controller.command.Router.RouterType.FORWARD;

public class FindAllUsersCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserService service = ServiceProvider.getInstance().getUserService();

        Router commandResult = null;
        try {
            List<User> users = service.findAllEntities();
            request.setAttribute(ParameterAndAttribute.USERS_LIST, users);
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, CurrentPageExtractor.extract(request));
            commandResult = new Router(PagePath.SHOW_USERS_PAGE, FORWARD);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllRoomsCommand was failed " + e);
            commandResult = new Router(PagePath.ERROR, FORWARD);
        }
        return commandResult;
    }
    }
