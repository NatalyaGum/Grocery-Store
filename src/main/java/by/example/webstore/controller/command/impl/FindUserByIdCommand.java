package by.example.webstore.controller.command.impl;

import by.example.webstore.controller.command.Command;
import by.example.webstore.controller.command.PagePath;
import by.example.webstore.controller.command.ParameterAndAttribute;
import by.example.webstore.controller.command.Router;
import by.example.webstore.entity.User;
import by.example.webstore.exception.ServiceException;
import by.example.webstore.service.ServiceProvider;
import by.example.webstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Arrays;
import java.util.Optional;

public class FindUserByIdCommand implements Command {

        private static final Logger logger = LogManager.getLogger();

        @Override
        public Router execute(HttpServletRequest request) {
            UserService userService = ServiceProvider.getInstance().getUserService();

            Router router;
         /*   if (!UserControl.isLoggedInUser(request) || !UserControl.isValidForRole(request, UserRole.ADMIN)) {
                router = new Router(PagePath.GO_TO_MAIN_PAGE, Router.RouteType.REDIRECT);
                return router;
            }*/
            HttpSession session = request.getSession(true);

            long userId = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));
            try {
                Optional<User> userOptional = userService.findUserById(userId);
                if (userOptional.isPresent()) {
                    request.setAttribute(ParameterAndAttribute.USERS, Arrays.asList(userOptional.get()));
                }
                /*else {
                    session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_NOTHING_FOUND_MESSAGE);
                }*/
                session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.FIND_USER_BY_ID + userId);
                router = new Router(PagePath.SHOW_USERS_PAGE, Router.RouterType.FORWARD);
            } catch (ServiceException e) {
                logger.error("user search error", e);
                router = new Router(PagePath.ERROR, Router.RouterType.REDIRECT);
            }
            return router;
        }
    }

