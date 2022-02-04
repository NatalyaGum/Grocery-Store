package by.example.webstore.controller.command;


import by.example.webstore.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;


public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}