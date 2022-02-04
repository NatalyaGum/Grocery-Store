package by.example.webstore.controller;



import by.example.webstore.exception.CommandException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import by.example.webstore.controller.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = { "/controller" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024,
        maxRequestSize = 1024 * 1024)
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            processRequest(req, resp);
        } catch (CommandException e) {
            logger.error( "Internal error has occurred:", e);
            resp.sendError(SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (CommandException e) {
            logger.error( "Internal error has occurred:", e);
            resp.sendError(SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException {
        String commandName = request.getParameter(ParameterAndAttribute.COMMAND);
        Command command = CommandProvider.defineCommand(commandName);
        Router router = command.execute(request);
        switch (router.getRouteType()) {
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                break;
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
                break;
            default:
                logger.error("incorrect route type " + router.getRouteType());
                response.sendRedirect(PagePath.ERROR);
        }
    }
}
