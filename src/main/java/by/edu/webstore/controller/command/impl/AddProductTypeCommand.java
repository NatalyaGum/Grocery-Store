package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.ProductType;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class AddProductTypeCommand implements Command {

    static Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    private static final String ADD_PRODUCT_ERROR_MESSAGE_KEY = "error.add_product_type";
    private static final String ADD_PRODUCT_TYPE_CONFIRM_MESSAGE_KEY = "confirm.product_type_add";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        session.removeAttribute(MESSAGE_TYPE);
        session.removeAttribute(MESSAGE_TYPE_PRODUCT);
        String productTypeData = request.getParameter(PRODUCT_TYPE);
        try {

            if (productService.isTypeExist(productTypeData)) {
                request.setAttribute(PRODUCT_TYPE_ADD, productTypeData);
                request.setAttribute(MESSAGE_TYPE, ADD_PRODUCT_ERROR_MESSAGE_KEY);
                return new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.FORWARD);
            }
            if (productService.insertNewProductType(productTypeData)) {
                List<ProductType> productTypes = productService.findAllProductTypes();
                session.setAttribute(PRODUCT_TYPES_LIST, productTypes);
                session.setAttribute(MESSAGE_TYPE, ADD_PRODUCT_TYPE_CONFIRM_MESSAGE_KEY);
                return new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(PRODUCT_TYPE, productTypeData);
                request.setAttribute(MESSAGE_TYPE, ADD_PRODUCT_ERROR_MESSAGE_KEY);
                return new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.FORWARD);
            }

        } catch (ServiceException e) {
            logger.error("Impossible to create new product type:", e);
            throw new CommandException("Impossible to create product type:", e);
        }
    }
}
