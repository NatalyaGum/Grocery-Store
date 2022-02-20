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

public class UpdateProductTypeCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    private static final String ERROR_MESSAGE_KEY = "error.update_product_type";
    private static final String MODIFY_PRODUCT_TYPE_CONFIRM_MESSAGE_KEY = "confirm.product_type_modify";
    private static final String DELETE_PRODUCT_TYPE_CONFIRM_MESSAGE_KEY = "confirm.product_type_delete";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        session.removeAttribute(MESSAGE_TYPE);
        session.removeAttribute(MESSAGE_TYPE_PRODUCT);
        Router commandResult = null;
        String oldProductType = request.getParameter(DELETE_TYPE);
        String newProductType = request.getParameter(MODIFY_TYPE);
        if (!request.getParameter(MODIFY_TYPE).isEmpty()) {
            try {
                if(productService.isTypeExist(newProductType)) {
                    request.setAttribute(PRODUCT_TYPE_MODIFY, newProductType);
                    request.setAttribute(MESSAGE_TYPE_PRODUCT, ERROR_MESSAGE_KEY);
                    return new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.FORWARD);}
                if (productService.modifyProductType(oldProductType, newProductType)) {
                    List<ProductType> productTypes = productService.findAllProductTypes();
                    session.setAttribute(PRODUCT_TYPES_LIST, productTypes);
                    session.setAttribute(MESSAGE_TYPE_PRODUCT, MODIFY_PRODUCT_TYPE_CONFIRM_MESSAGE_KEY);
                    commandResult = new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.REDIRECT);
                } else {
                    request.setAttribute(MODIFY_TYPE, newProductType);
                    request.setAttribute(MESSAGE_TYPE_PRODUCT, ERROR_MESSAGE_KEY);
                    commandResult = new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.FORWARD);
                }
            } catch (ServiceException e) {
                logger.error("Impossible to update product type:", e);
                throw new CommandException("Impossible to update product type:", e);
            }

        } else {
            try {
                if (productService.deleteProductType(oldProductType)) {
                    List<ProductType> productTypes = productService.findAllProductTypes();
                    session.setAttribute(PRODUCT_TYPES_LIST, productTypes);
                    session.setAttribute(MESSAGE_TYPE_PRODUCT, DELETE_PRODUCT_TYPE_CONFIRM_MESSAGE_KEY);
                    commandResult = new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.REDIRECT);
                } else {
                    request.setAttribute(MESSAGE_TYPE_PRODUCT, ERROR_MESSAGE_KEY);
                    commandResult = new Router(PagePath.PRODUCT_ADD_PAGE, Router.RouterType.FORWARD);
                }

            } catch (ServiceException e) {
                logger.error("Impossible to remove product type:", e);
                throw new CommandException("Impossible to remove product type:", e);
            }
        }
        return commandResult;
    }
}
