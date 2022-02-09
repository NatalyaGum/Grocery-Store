package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;

import jakarta.servlet.http.HttpServletRequest;

public class GoToMainPageCommand  implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        //HttpSession session = request.getSession();
        //session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, CurrentPageExtractor.extract(request));
        return new Router(PagePath.MAIN_PAGE, Router.RouterType.FORWARD);
    }
}
