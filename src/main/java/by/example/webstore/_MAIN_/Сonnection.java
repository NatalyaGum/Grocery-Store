package by.example.webstore._MAIN_;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Сonnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         final String BUNDLE_NAME = "db";
        final String DB_DRIVER = "db.driver";
         final String DB_URL = "db.url";
         final String DB_USER = "db.user";
         final String DB_PASSWORD = "db.password";
         final String DATABASE_URL;
         final String DATABASE_USER_NAME;
         final String DATABASE_PASSWORD;
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        String driverName = bundle.getString(DB_DRIVER);
        DATABASE_URL = bundle.getString(DB_URL);
        DATABASE_USER_NAME = bundle.getString(DB_USER);
        DATABASE_PASSWORD = bundle.getString(DB_PASSWORD);
        Class.forName(driverName);
        // Class.forName("com.mysql.cj.jdbc.Driver");

        // DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

      //  Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "nbg222");
        System.out.println("ok");

       // Сonnection connection3 =DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
    }
}

