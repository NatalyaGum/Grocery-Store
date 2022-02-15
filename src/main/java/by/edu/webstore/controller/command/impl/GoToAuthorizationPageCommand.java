package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAuthorizationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        return new Router(PagePath.AUTHORIZATION, Router.RouterType.FORWARD);
    }
}
