package by.edu.webstore.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurrentPageExtractor {
    static Logger logger = LogManager.getLogger();
    private static final String CONTROLLER_PART = "/controller?";

    public static String extract(HttpServletRequest request) {

        String commandPart = request.getQueryString();
        logger.info("getQueryString>> " + commandPart);
        System.out.println("getQueryString>> " + commandPart);

        String currentPage = CONTROLLER_PART + commandPart;
        logger.info("currentPage>> " + currentPage);
        System.out.println("currentPage>> " + currentPage);

        return currentPage;
    }
}