package by.example.webstore.controller.command.listener;

import by.example.webstore.controller.command.PagePath;
import by.example.webstore.entity.User;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebListener;



import static by.example.webstore.controller.command.ParameterAndAttribute.*;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    private static final String DEFAULT_LOCALE = "en_US";
    private static final String DEFAULT_LANGUAGE = "EN";

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(CURRENT_PAGE, PagePath.MAIN_PAGE);
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        session.setAttribute(LANGUAGE, DEFAULT_LANGUAGE);
        session.setAttribute(ROLE, User.Role.GUEST.toString().toLowerCase());
    }
}
