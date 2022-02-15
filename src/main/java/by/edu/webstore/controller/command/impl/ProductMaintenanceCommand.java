package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.Product;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class ProductMaintenanceCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        int page = 1;
        int recordsPerPage = 6;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));}
       // HttpSession session = request.getSession();
        try {
            List<Product> products=productService.findAllProducts((page-1) * recordsPerPage, recordsPerPage);
            int totalProductNumber = productService.getTotalProductNumber();
            int pagesNumber = (int) Math.ceil(totalProductNumber * 1.0 / recordsPerPage);
            request.setAttribute(PAGES_NUMBER, pagesNumber);
            request.setAttribute(PAGE_NUMBER, page);
            request.setAttribute(PRODUCTS_LIST, products);
            return new Router(PagePath.PRODUCT_MAINTENANCE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error( "Impossible to find products:", e);
            throw new CommandException("Impossible to find products:", e);
        }

    }
}
