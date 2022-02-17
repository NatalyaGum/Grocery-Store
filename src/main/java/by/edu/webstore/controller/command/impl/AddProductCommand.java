package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.service.ServiceProvider;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class AddProductCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    private static final String ADD_PRODUCT_ERROR_MESSAGE_KEY = "error.add_product";
    private static final String ADD_PRODUCT_CONFIRM_MESSAGE_KEY = "confirm.product_add";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        session.removeAttribute(ParameterAndAttribute.MESSAGE_TYPE_PRODUCT);
        session.removeAttribute(ParameterAndAttribute.MESSAGE_TYPE);
        session.removeAttribute(ParameterAndAttribute.MESSAGE);
        Map<String, String> productData = new HashMap<>();
        productData.put(ParameterAndAttribute.TITLE, request.getParameter(ParameterAndAttribute.TITLE));
        productData.put(ParameterAndAttribute.DESCRIPTION, request.getParameter(ParameterAndAttribute.DESCRIPTION));
        productData.put(ParameterAndAttribute.MANUFACTURE, request.getParameter(ParameterAndAttribute.MANUFACTURE));
        productData.put(ParameterAndAttribute.PRICE, request.getParameter(ParameterAndAttribute.PRICE));
        productData.put(ParameterAndAttribute.TYPE, request.getParameter(ParameterAndAttribute.TYPE));
        long id;
        try {
            Part imagePart = request.getPart(ParameterAndAttribute.IMAGE);
            InputStream imageInputStream = imagePart.getInputStream();
            id=productService.insertNewProduct(productData, imageInputStream);
            if (id>0) {
                session.setAttribute(ParameterAndAttribute.MESSAGE,ADD_PRODUCT_CONFIRM_MESSAGE_KEY);
                session.setAttribute(ParameterAndAttribute.PRODUCT_ID,id);
                return new Router(PagePath.PRODUCT_ADD_PAGE,Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(ParameterAndAttribute.PRODUCT,productData);
                request.setAttribute(ParameterAndAttribute.MESSAGE,ADD_PRODUCT_ERROR_MESSAGE_KEY);
                // request.setAttribute(PRODUCT_CREATION_RESULT, INVALID);
                return new Router(PagePath.PRODUCT_ADD_PAGE,Router.RouterType.FORWARD);
            }
            //List<Product> products = productService.findAllProducts();
           // session.setAttribute(PRODUCT_LIST, PRODUCTS);

        } catch (ServiceException e) {
            logger.error( "Impossible to create new product:", e);
            throw new CommandException("Impossible to create product:", e);
        } catch (ServletException | IOException e) {
            logger.error( "Impossible to get image of product", e);
            throw new CommandException("Impossible to get image of product:", e);
        }
    }
}

