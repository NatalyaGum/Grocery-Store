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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static by.edu.webstore.controller.command.ParameterAndAttribute.MESSAGE;
import static by.edu.webstore.controller.command.ParameterAndAttribute.MESSAGE_PICTURE;
import static by.edu.webstore.controller.command.Router.RouterType.FORWARD;

public class GoToEditCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    private static final String PRODUCT_ERROR_MESSAGE_KEY = "error.find_product";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(MESSAGE);
        session.removeAttribute(MESSAGE_PICTURE);
        Long product_id = Long.parseLong(request.getParameter("product_id"));
        try {
            Optional<Product> optionalProduct = productService.getProductById(product_id);
            List<ProductType> productTypes = productService.findAllProductTypes();
            session.setAttribute(ParameterAndAttribute.PRODUCT_TYPES_LIST, productTypes);
            if (optionalProduct.isPresent()) {
                request.setAttribute(ParameterAndAttribute.PRODUCT, optionalProduct.get());
            } else {
                request.setAttribute(ParameterAndAttribute.MESSAGE, PRODUCT_ERROR_MESSAGE_KEY);
            }
        } catch (ServiceException e) {
            logger.error("Impossible to find products:", e);
            throw new CommandException("Impossible to find products:", e);
        }
        return new Router(PagePath.PRODUCT_EDIT_PAGE, FORWARD);
    }
}
