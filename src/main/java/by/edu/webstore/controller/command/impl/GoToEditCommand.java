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

import static by.edu.webstore.controller.command.Router.RouterType.FORWARD;

public class GoToEditCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Long product_id = Long.parseLong(request.getParameter("product_id"));
        try {  Optional<Product> optionalProduct=productService.getProductById (product_id) ;
            request.setAttribute(ParameterAndAttribute.PRODUCT, optionalProduct.get());
            List<ProductType> productTypes = productService.findAllProductTypes();
            request.setAttribute(ParameterAndAttribute.PRODUCT_TYPES_LIST, productTypes);
        }  catch (ServiceException e) {
            logger.error( "Impossible to find products:", e);
            throw new CommandException("Impossible to find products:", e);
        }
        return new Router(PagePath.PRODUCT_EDIT, FORWARD);
    }
}
