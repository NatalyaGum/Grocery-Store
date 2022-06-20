package by.edu.webstore.controller.command.filter;


import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static by.edu.webstore.controller.command.PagePath.*;
import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

/**
 * {@code JspSecurityFilter} class implements functional of {@link Filter}
 * Restricts access to the page depending on the user's role.
 */
@WebFilter(urlPatterns = {"/jsp/*"})
public class JspSecurityFilter implements Filter {

    private Set<String> guestPages;
    private Set<String> clientPages;
    private Set<String> adminPages;
    private Set<String> allPages;

    public void init(FilterConfig config) throws ServletException {
        guestPages = Set.of(
                MAIN_PAGE,
                AUTHORIZATION,
                CATALOG,
                REGISTRATION_PAGE,
                CATALOG_BY_TYPE,
                ERROR,
                SEARCH_BY_TITLE,
                ERROR_404);

        clientPages = Set.of(
                MAIN_PAGE,
                ORDER_PAGE,
                CATALOG,
                ADDRESS_ADD_PAGE,
                USER_ORDERS,
                UPDATE_PROFILE,
                CATALOG_BY_TYPE,
                ERROR,
                SEARCH_BY_TITLE,
                ERROR_404);

        adminPages = Set.of(
                MAIN_PAGE,
                CATALOG,
                PRODUCT_ADD_PAGE,
                PRODUCT_MAINTENANCE,
                PRODUCT_EDIT_PAGE,
                ADMIN_ORDERS,
                UPDATE_PROFILE,
                USER_MAINTENANCE,
                CATALOG_BY_TYPE,
                ERROR,
                SEARCH_BY_TITLE,
                ERROR_404);

        allPages = new HashSet<>();
        allPages.addAll(guestPages);
        allPages.addAll(clientPages);
        allPages.addAll(adminPages);
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getServletPath();
        boolean isPageExist = allPages.stream().anyMatch(requestURI::contains);

        if (isPageExist) {
            HttpSession session = request.getSession();
            User.Role role = session.getAttribute(ROLE) == null
                    ? User.Role.GUEST
                    : User.Role.valueOf(session.getAttribute(ROLE).toString().toUpperCase());

            boolean isAccept =
                    switch (role) {
                        case CLIENT -> clientPages.stream().anyMatch(requestURI::contains);
                        case ADMIN -> adminPages.stream().anyMatch(requestURI::contains);
                        default -> guestPages.stream().anyMatch(requestURI::contains);
                    };
            if (!isAccept) {
                response.sendRedirect(request.getContextPath() + "/" + PagePath.MAIN_PAGE);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

