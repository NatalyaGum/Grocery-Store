package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.ProductType;
import by.edu.webstore.service.ServiceProvider;

import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class GoToProductAddCommand implements Command {
    static Logger logger = LogManager.getLogger();
    ProductService service = ServiceProvider.getInstance().getProductService();
    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        session.removeAttribute(MESSAGE_TYPE_PRODUCT);
        session.removeAttribute(MESSAGE_TYPE);
        Router commandResult = null;
        try {
            List<ProductType> productTypes = service.findAllProductTypes();
            session.setAttribute(ParameterAndAttribute.PRODUCT_TYPES_LIST, productTypes);
            // session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, CurrentPageExtractor.extract(request));
            commandResult = new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllRoomsCommand was failed " + e);
            commandResult = new Router(PagePath.ERROR, Router.RouterType.FORWARD);
        }
        return commandResult;


    }
}
