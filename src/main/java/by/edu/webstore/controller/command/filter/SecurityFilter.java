


package by.edu.webstore.controller.command.filter;

import by.edu.webstore.controller.command.CommandType;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

import java.io.IOException;
import java.util.*;

import static by.edu.webstore.controller.command.CommandType.*;
import static by.edu.webstore.entity.User.Role.*;


@WebFilter(urlPatterns = {"/controller"})
public class SecurityFilter implements Filter {


   /* private static final String DEFAULT_COMMAND = "go_to_main_page";
    private EnumMap<User.Role, List<CommandType>> accessibleCommands;
    @Override
    public void init(FilterConfig filterConfig) {
        accessibleCommands = new EnumMap<>(User.Role.class);
        accessibleCommands.put(ADMIN, List.of(CHANGE_LOCALE,
                GO_TO_MAIN_PAGE,
                ADD_PRODUCT,
                MODIFY_PRODUCT_TYPE,
                ADD_PRODUCT_TYPE,
                GO_TO_PRODUCT_ADD));
        accessibleCommands.put(CLIENT, List.of(CHANGE_LOCALE,
                GO_TO_MAIN_PAGE));
        accessibleCommands.put(GUEST, List.of(CHANGE_LOCALE,
                GO_TO_MAIN_PAGE,
                GO_TO_REGISTRATION,
                SIGN_IN,
                SIGN_UP));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
       // User.Role role=(User.Role)session.getAttribute(ROLE);
           User.Role role= User.Role.valueOf(session.getAttribute(ROLE).toString().toUpperCase());
        // User.Role role=User.Role.valueOf(String.valueOf(session.getAttribute(ROLE)).toUpperCase());
       *//* User.Role role = session.getAttribute(ROLE) == null
                ? User.Role.GUEST
                : (User.Role)session.getAttribute(ROLE);*//*
        String command = request.getParameter(COMMAND);
        if (command == null) {
            command = DEFAULT_COMMAND;
        }

        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        Optional<CommandType> foundCommandType = accessibleCommands.get(role)
                .stream()
                .filter(c -> c == commandType)
                .findFirst();
        if (foundCommandType.isEmpty()) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + PagePath.ERROR_404);
            return;
        }
        chain.doFilter(request, response);

    }*/
   private static final String DEFAULT_COMMAND = "go_to_main_page";
    private final EnumMap<User.Role, EnumSet<CommandType>> roleMap = new EnumMap<>(User.Role.class);
    private final EnumSet<CommandType> guestCommands = EnumSet.of(
            CHANGE_LOCALE,
            GO_TO_CATALOG,
            GO_TO_MAIN_PAGE,
            GO_TO_REGISTRATION,
            GO_TO_AUTHORIZATION,
            SIGN_IN,
            SIGN_UP);

    private final EnumSet<CommandType> adminCommands = EnumSet.of(
            CHANGE_LOCALE,
            SIGN_OUT,
            GO_TO_MAIN_PAGE,
            GO_TO_CATALOG,
            ADD_PRODUCT,
            PRODUCT_MAINTENANCE,
            GO_TO_EDIT_PRODUCT,
            EDIT_PRODUCT,
            UPDATE_PICTURE,
            MODIFY_PRODUCT_TYPE,
            ADD_PRODUCT_TYPE,
            GO_TO_PRODUCT_ADD);

    private final EnumSet<CommandType> clientCommands = EnumSet.of(
            CHANGE_LOCALE,
            SIGN_OUT,
            GO_TO_CATALOG,
            GO_TO_MAIN_PAGE);

    public void init(FilterConfig config) throws ServletException {

        roleMap.put(GUEST, guestCommands);
        roleMap.put(ADMIN, adminCommands);
        roleMap.put(CLIENT, clientCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
       // User.Role role= User.Role.valueOf(session.getAttribute(ROLE).toString().toUpperCase());
        User.Role role = session.getAttribute(ROLE) == null
                ? User.Role.GUEST
                : User.Role.valueOf(session.getAttribute(ROLE).toString().toUpperCase());
        String command = request.getParameter(COMMAND);
        if (command == null) {
            command = DEFAULT_COMMAND;
        }
        CommandType commandType = CommandType.valueOf(command.toUpperCase());

        boolean hasAccess = roleMap.get(role)
                .stream()
                .anyMatch(c -> c == commandType);

        if (!hasAccess) {
            response.sendRedirect(request.getContextPath() + "/" + PagePath.MAIN_PAGE);
        } else {
            filterChain.doFilter(request, response);
        }

}}