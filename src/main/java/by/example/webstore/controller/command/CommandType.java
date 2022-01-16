package by.example.webstore.controller.command;

public enum CommandType {

    DEFAULT ("default"),
    FIND_ALL_USERS("find_all_users"),
    FIND_USER_BY_ID("find_user_by_id"),
    GO_TO_MAIN_PAGE("go_to_main_page");

    private String webCommandName;

    private CommandType(String webCommandName) {
        this.webCommandName = webCommandName;
    }

}
