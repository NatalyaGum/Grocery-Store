package by.edu.webstore.controller.command;

public enum CommandType {

   // DEFAULT ("default"),
    FIND_ALL_USERS("find_all_users"),
    FIND_USER_BY_ID("find_user_by_id"),
    GO_TO_MAIN_PAGE("go_to_main_page"),
    CHANGE_LOCALE ("change_local"),
    GO_TO_REGISTRATION("go_to_registration"),
    SIGN_UP("sign_up"),
    SIGN_IN("sign_in"),
    ADD_PRODUCT ("product_add"),
    GO_TO_PRODUCT_ADD("go_to_product_add"),
    MODIFY_PRODUCT_TYPE("modify_product_type"),
    ADD_PRODUCT_TYPE ("add_product_type");
    private String webCommandName;

    private CommandType(String webCommandName) {
        this.webCommandName = webCommandName;
    }

}
