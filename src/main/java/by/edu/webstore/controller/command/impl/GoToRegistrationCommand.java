package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.edu.webstore.controller.command.ParameterAndAttribute.MESSAGE;

public class GoToRegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        //session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, CurrentPageExtractor.extract(request));
        return new Router(PagePath.REGISTRATION_PAGE, Router.RouterType.FORWARD);
    }
}
