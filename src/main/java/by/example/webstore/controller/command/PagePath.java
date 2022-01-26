package by.example.webstore.controller.command;

public class PagePath {

    public static final String MAIN_PAGE = "index.jsp";
    //public static final String HOME_PAGE = "jsp/users.jsp";
    public static final String ERROR = "jsp/error500.jsp";
    public static final String REGISTRATION_PAGE = "jsp/registration.jsp";
    public static final String SHOW_USERS_PAGE = "jsp/users1.jsp";
    public static final String FIND_USER_BY_ID= "controller?command=find_user_by_id&userId=";
    public static final String ERROR_404="jsp/error500.jsp";
  //  public static final String GO_TO_MAIN_PAGE = "controller?command=go_to_main_page";

    private PagePath() {
    }
}
