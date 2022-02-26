package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.Address;
import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.OrderService;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.service.ServiceProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class GoToCardCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(ParameterAndAttribute.MESSAGE);
        if (request.getParameter(ParameterAndAttribute.PRODUCT_ID) != null) {
            updateQuantity(request);
        }
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        HashMap<Long, Integer> orderMap = new HashMap<>();
        HashMap<Product, Integer> productMap = new HashMap<>();
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);
        if (session.getAttribute(ParameterAndAttribute.ORDER_MAP) != null) {
            orderMap = (HashMap<Long, Integer>) session.getAttribute(ParameterAndAttribute.ORDER_MAP);
        }
        try {
            for (Map.Entry entry : orderMap.entrySet()) {
                long product_id = (Long) entry.getKey();
                Optional<Product> optionalProduct = productService.getProductById(product_id);
                if (optionalProduct.isPresent()) {
                    if((int) entry.getValue()!=0){
                    productMap.put(optionalProduct.get(), (int) entry.getValue());
                    BigDecimal price = optionalProduct.get().getPrice();
                    price=price.multiply(BigDecimal.valueOf(((int)entry.getValue())));
                    totalPrice = totalPrice.add(price);
                    }
                }
            }
            session.setAttribute(ParameterAndAttribute.PRODUCT_MAP, productMap);
            session.setAttribute(ParameterAndAttribute.TOTAL, totalPrice);
        } catch (ServiceException e) {
            logger.error("Impossible to find products:", e);
            throw new CommandException("Impossible to find products:", e);
        }
        try {
            List<Address> addresses = orderService.findUserAddresses(user.getUserId());
            if (addresses.size() > 0) {
                session.setAttribute(ParameterAndAttribute.ADDRESSES_LIST, addresses);
            }
        } catch (ServiceException e) {
            logger.error("Impossible to find addresses:", e);
            throw new CommandException("Impossible to find addresses:", e);
        }
        return new Router(PagePath.ORDER_PAGE, Router.RouterType.FORWARD);
    }

    void updateQuantity(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        HashMap<Long, Integer> orderMap = (HashMap<Long, Integer>) session.getAttribute(ParameterAndAttribute.ORDER_MAP);
        int countProductsInCard = 0;
        Integer count = Integer.parseInt(request.getParameter(ParameterAndAttribute.PRODUCT_COUNT));
        Long product_id = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));
        if (count==0) {
            orderMap.remove(product_id);
        } else {
            orderMap.put(product_id, count);
        }
        orderMap.put(product_id, count);
        for (Map.Entry entry : orderMap.entrySet()) {
            countProductsInCard += (int) entry.getValue();
        }
        session.setAttribute(ParameterAndAttribute.ORDER_MAP, orderMap);
        session.setAttribute(ParameterAndAttribute.CARD, countProductsInCard);
    }

}


