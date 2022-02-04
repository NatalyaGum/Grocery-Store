package by.example.webstore.controller.command.impl;

import by.example.webstore.controller.command.Command;
import by.example.webstore.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class UploadCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
