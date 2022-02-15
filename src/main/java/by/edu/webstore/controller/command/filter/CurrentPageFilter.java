package by.edu.webstore.controller.command.filter;

import by.edu.webstore.controller.command.CommandType;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"})
public class CurrentPageFilter implements Filter {
    private static final String COMMAND_DELIMITER = "?";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if (httpServletRequest.getParameter(ParameterAndAttribute.COMMAND) != null) {
            String commandType = httpServletRequest.getParameter(ParameterAndAttribute.COMMAND);
            if (CommandType.CHANGE_LOCALE != CommandType.valueOf(commandType.toUpperCase())) {
                String currentPage = httpServletRequest.getServletPath() + COMMAND_DELIMITER + httpServletRequest.getQueryString();
                session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, currentPage);
            }
        } else {
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, httpServletRequest.getServletPath());
        }
        chain.doFilter(request, response);
    }
   /* @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String currentPage = httpRequest.getRequestURL().toString();

        if (currentPage.contains("jsp/")) {
            int index = currentPage.indexOf("jsp/");
            currentPage = currentPage.substring(index);
            httpRequest.getSession().setAttribute(ParameterAndAttribute.CURRENT_PAGE, currentPage);
        } else if (currentPage.contains("controller") && !httpRequest.getParameterMap().isEmpty()
                && httpRequest.getQueryString() != null && !httpRequest.getQueryString().contains("command=change_locale")) {
            int index = currentPage.indexOf("controller");
            currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();
            httpRequest.getSession().setAttribute(ParameterAndAttribute.CURRENT_PAGE, currentPage);
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }*/
}
