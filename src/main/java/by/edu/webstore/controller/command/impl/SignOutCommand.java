package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.exception.CommandException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignOutCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
       // HttpSession session =request.getSession();
       //  session.removeAttribute(USER);
       //  session.setAttribute(ROLE, User.Role.GUEST.toString());

        HttpSession session = request.getSession(false);
        if (session!=null) {
            session.invalidate();
        }
        request.getSession(true);
        logger.info("Session was ended.");
        return new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
    }
}
