package by.edu.webstore.controller.command;


import by.edu.webstore.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * {@code Command} interface represent functional of command
 */
public interface Command {
    /**
     * Execute command
     *
     * @param request - request from controller, type {@link Router}
     * @throws CommandException - if service method throw {@link by.edu.webstore.exception.CommandException}
     */
    Router execute(HttpServletRequest request) throws CommandException;
}