package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class GoToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        session.removeAttribute(ADD_ORDER_MESSAGE);
        return new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
    }
}
