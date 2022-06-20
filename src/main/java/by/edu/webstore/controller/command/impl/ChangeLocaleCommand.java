package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    private static final String LANGUAGE_ENGLISH = "EN";
    private static final String LANGUAGE_RUSSIAN = "RU";
    private static final String LOCALE_ENGLISH = "en_US";
    private static final String LOCALE_RUSSIAN = "ru_RU";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String currentPage = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        String language = request.getParameter(ParameterAndAttribute.LANGUAGE);
        switch (language) {
            case LANGUAGE_ENGLISH -> session.setAttribute(ParameterAndAttribute.LOCALE, LOCALE_ENGLISH);
            case LANGUAGE_RUSSIAN -> session.setAttribute(ParameterAndAttribute.LOCALE, LOCALE_RUSSIAN);
        }
        session.setAttribute(ParameterAndAttribute.LANGUAGE, language);
        return new Router(currentPage, Router.RouterType.FORWARD);
    }


}