package by.example.webstore.controller.command.impl;

import by.example.webstore.controller.command.Command;
import by.example.webstore.controller.command.PagePath;
import by.example.webstore.controller.command.ParameterAndAttribute;
import by.example.webstore.controller.command.Router;
import by.example.webstore.entity.ProductType;

import by.example.webstore.exception.CommandException;
import by.example.webstore.exception.ServiceException;
import by.example.webstore.service.ProductService;
import by.example.webstore.service.ServiceProvider;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.example.webstore.controller.command.Router.RouterType.FORWARD;

public class GoToProductAddCommand implements Command{
    static Logger logger = LogManager.getLogger();
    ProductService service = ServiceProvider.getInstance().getProductService();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router commandResult = null;
        try {
            List<ProductType> productTypes = service.findAllProductTypes();
            request.setAttribute(ParameterAndAttribute.PRODUCT_TYPES_LIST, productTypes);
            // session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, CurrentPageExtractor.extract(request));
            commandResult = new Router(PagePath.PRODUCT_MANAGEMENT, FORWARD);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllRoomsCommand was failed " + e);
            commandResult = new Router(PagePath.ERROR, FORWARD);
        }
        return commandResult;


    }
}
