package by.edu.webstore.controller.command.impl;


import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.Product;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class Search implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(PAGES_NUMBER);
        session.removeAttribute(PAGE_NUMBER);
        session.removeAttribute(TYPE_ID);
        if (request.getParameter(PRODUCT_ID) != null) {
            addToCardCommand(request);
        }
        String search_value = request.getParameter(SEARCH_VALUE);

        session.setAttribute(SEARCH_VALUE, search_value);
        try {
            List<Product> products = productService.searchProducts(search_value);
            session.setAttribute(PRODUCTS_LIST, products);
            return new Router(PagePath.SEARCH_BY_TITLE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Impossible to find products:", e);
            throw new CommandException("Impossible to find products:", e);
        }

    }


    void addToCardCommand(HttpServletRequest request) {
        HttpSession session = request.getSession();
        HashMap<Long, Integer> orderMap;
        int countProductsInCard = 0;
        Integer count = Integer.parseInt(request.getParameter(ParameterAndAttribute.PRODUCT_COUNT));
        Long product_id = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));
        if (session.getAttribute(ParameterAndAttribute.ORDER_MAP) == null) {
            orderMap = new HashMap<>();
        } else {
            orderMap = (HashMap<Long, Integer>) session.getAttribute(ParameterAndAttribute.ORDER_MAP);
        }
        if (orderMap.containsKey(product_id)) {
            count += orderMap.get(product_id);
            orderMap.put(product_id, count);
        } else {
            orderMap.put(product_id, count);
        }
        for (Map.Entry entry : orderMap.entrySet()) {
            countProductsInCard += (int) entry.getValue();
        }
        session.setAttribute(ParameterAndAttribute.ORDER_MAP, orderMap);
        session.setAttribute(ParameterAndAttribute.CARD, countProductsInCard);
    }
}


