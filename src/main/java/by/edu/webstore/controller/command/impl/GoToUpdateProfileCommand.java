package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.edu.webstore.controller.command.ParameterAndAttribute.MESSAGE;

public class GoToUpdateProfileCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        return new Router(PagePath.UPDATE_PROFILE, Router.RouterType.FORWARD);
    }
}
