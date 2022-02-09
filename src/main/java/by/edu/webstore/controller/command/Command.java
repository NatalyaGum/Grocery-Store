package by.edu.webstore.controller.command;


import by.edu.webstore.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;


public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}