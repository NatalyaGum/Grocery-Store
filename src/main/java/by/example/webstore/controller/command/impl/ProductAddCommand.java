package by.example.webstore.controller.command.impl;

import by.example.webstore.controller.command.Command;
import by.example.webstore.controller.command.PagePath;
import by.example.webstore.controller.command.Router;
import by.example.webstore.entity.Product;
import by.example.webstore.exception.CommandException;
import by.example.webstore.exception.ServiceException;
import by.example.webstore.service.ProductService;
import by.example.webstore.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.example.webstore.controller.command.ParameterAndAttribute.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAddCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    private static final String ADD_PRODUCT_ERROR_MESSAGE_KEY = "error.add_product";
    private static final String ADD_PRODUCT_CONFIRM_MESSAGE_KEY = "confirm.product_add";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        Map<String, String> productData = new HashMap<>();
        productData.put(TITLE, request.getParameter(TITLE));
        productData.put(DESCRIPTION, request.getParameter(DESCRIPTION));
        productData.put(MANUFACTURE, request.getParameter(MANUFACTURE));
        productData.put(PRICE, request.getParameter(PRICE));
        productData.put(TYPE, request.getParameter(TYPE));
        try {
            Part imagePart = request.getPart(IMAGE);
            InputStream imageInputStream = imagePart.getInputStream();
            if (productService.insertNewProduct(productData, imageInputStream)) {
               // request.setAttribute(MESSAGE,ADD_PRODUCT_CONFIRM_MESSAGE_KEY);
                return new Router(PagePath.PRODUCT_MANAGEMENT,Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(PRODUCT,productData);
                request.setAttribute(MESSAGE,ADD_PRODUCT_ERROR_MESSAGE_KEY);
                // request.setAttribute(PRODUCT_CREATION_RESULT, INVALID);
                return new Router(PagePath.PRODUCT_MANAGEMENT,Router.RouterType.FORWARD);
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

