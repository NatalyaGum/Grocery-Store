package by.edu.webstore.controller.command.listener;

import by.edu.webstore.controller.command.PagePath;
import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

import by.edu.webstore.entity.ProductType;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.service.ServiceProvider;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    static Logger logger = LogManager.getLogger();
    private static final String DEFAULT_LOCALE = "en_US";
    private static final String DEFAULT_LANGUAGE = "EN";

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        final ProductService productService = ServiceProvider.getInstance().getProductService();
        List<ProductType> productTypes = null;
        try {
            productTypes = productService.findAllProductTypes();
        } catch (ServiceException e) {
            logger.error("Impossible to find product types:", e);
        }
        session.setAttribute(PRODUCT_TYPES_LIST, productTypes);
        session.setAttribute(CURRENT_PAGE, PagePath.MAIN_PAGE);
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        session.setAttribute(LANGUAGE, DEFAULT_LANGUAGE);
        session.setAttribute(ROLE, User.Role.GUEST.toString());
        LocalDate today = LocalDate.now();
        session.setAttribute(TODAY, today.toString());
    }
}
