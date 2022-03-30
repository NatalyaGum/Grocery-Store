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
    SIGN_OUT("sign_out"),
    ADD_PRODUCT ("product_add"),
    GO_TO_PRODUCT_ADD("go_to_product_add"),
    MODIFY_PRODUCT_TYPE("modify_product_type"),
    GO_TO_CATALOG("go_to_catalog"),
    GO_TO_AUTHORIZATION("go_to_authorization"),
    PRODUCT_MAINTENANCE("product_maintenance"),
    EDIT_PRODUCT("edit_product"),
    GO_TO_EDIT_PRODUCT("go_to_edit_product"),
    UPDATE_PICTURE("update_picture"),
    ADD_TO_CARD("add_to_card"),
    GO_TO_CARD("go_to_card"),
    GO_TO_ADD_ADDRESS("go_to_add_address"),
    GO_TO_ORDERS("go_to_orders"),
    ADD_ADDRESS("add_address"),
    ADD_PRODUCT_TYPE ("add_product_type"),
    GO_TO_PRODUCT_TYPE ("go_to_product_type"),
    GO_TO_ORDERS_ADMIN("go_to_orders_admin"),
    GO_TO_UPDATE_PROFILE("go_to_update_profile"),
    UPDATE_PROFILE("update_profile"),
    CREATE_ORDER("create_order");

    private String webCommandName;

    private CommandType(String webCommandName) {
        this.webCommandName = webCommandName;
    }

}
