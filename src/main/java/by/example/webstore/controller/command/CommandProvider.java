package by.example.webstore.controller.command;

import by.example.webstore.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    static Logger logger = LogManager.getLogger();

    private static Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);
    static {
        commands.put(CommandType.FIND_ALL_USERS, new FindAllUsersCommand());
        commands.put(CommandType.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandType.GO_TO_REGISTRATION, new GoToRegistrationCommand());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.UPLOAD, new UploadCommand());
        commands.put(CommandType.ADD_PRODUCT, new ProductAddCommand());
        commands.put(CommandType.GO_TO_PRODUCT_ADD, new GoToProductAddCommand());
    }
    private CommandProvider() {
    }

    public static Command defineCommand(String commandName) {
        if (commandName == null) {
            logger.error("null command");
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("no such command name " + commandName, e);
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}
