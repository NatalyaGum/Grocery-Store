package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.ProductType;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static by.edu.webstore.controller.command.ParameterAndAttribute.MESSAGE;
import static by.edu.webstore.controller.command.ParameterAndAttribute.MESSAGE_PICTURE;

public class UpdatePictureCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    private static final String PRODUCT_ERROR_MESSAGE_KEY = "error.update_picture";
    private static final String PRODUCT_CONFIRM_MESSAGE_KEY = "confirm.update_picture";
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        session.removeAttribute(MESSAGE_PICTURE);
        Long product_id = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));
        try {
            List<ProductType> productTypes = productService.findAllProductTypes();
            request.setAttribute(ParameterAndAttribute.PRODUCT_TYPES_LIST, productTypes);
            Part imagePart = request.getPart(ParameterAndAttribute.IMAGE);
            InputStream imageInputStream = imagePart.getInputStream();
            if (productService.UpdateProductPicture(product_id,imageInputStream)) {
                Optional<Product> optionalProduct=productService.getProductById (product_id) ;
                request.setAttribute(ParameterAndAttribute.PRODUCT, optionalProduct.get());
                session.setAttribute(ParameterAndAttribute.MESSAGE_PICTURE,PRODUCT_CONFIRM_MESSAGE_KEY);
                return new Router(PagePath.PRODUCT_EDIT_PAGE,Router.RouterType.FORWARD);
            } else {
                Optional<Product> optionalProduct=productService.getProductById (product_id) ;
                request.setAttribute(ParameterAndAttribute.PRODUCT, optionalProduct.get());
                request.setAttribute(ParameterAndAttribute.MESSAGE_PICTURE,PRODUCT_ERROR_MESSAGE_KEY);
                // request.setAttribute(PRODUCT_CREATION_RESULT, INVALID);
                return new Router(PagePath.PRODUCT_EDIT_PAGE,Router.RouterType.FORWARD);
            }
            //List<Product> products = productService.findAllProducts();
            // session.setAttribute(PRODUCT_LIST, PRODUCTS);

        } catch (ServiceException e) {
            logger.error( "Impossible to update product:", e);
            throw new CommandException("Impossible to update product:", e);
        } catch (ServletException | IOException e) {
            logger.error( "Impossible to update image of product", e);
            throw new CommandException("Impossible to update image of product:", e);
        }

    }
}
