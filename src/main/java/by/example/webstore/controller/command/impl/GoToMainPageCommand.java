package by.example.webstore.controller.command.impl;

import by.example.webstore.controller.command.Command;
import by.example.webstore.controller.command.PagePath;
import by.example.webstore.controller.command.ParameterAndAttribute;
import by.example.webstore.controller.command.Router;
import by.example.webstore.util.CurrentPageExtractor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToMainPageCommand  implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, CurrentPageExtractor.extract(request));
        return new Router(PagePath.MAIN_PAGE, Router.RouteType.FORWARD);
    }
}
