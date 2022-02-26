package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.Product;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UpdateQuantity implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(ParameterAndAttribute.MESSAGE);
        Integer count = Integer.parseInt(request.getParameter(ParameterAndAttribute.PRODUCT_COUNT));
        Long product_id = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));
       // User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        HashMap<Long, Integer> orderMap = (HashMap<Long, Integer>) session.getAttribute(ParameterAndAttribute.ORDER_MAP);
        orderMap.put(product_id,count);
        int countProductsInCard = 0;
            for (Map.Entry entry : orderMap.entrySet()) {
                countProductsInCard += (int) entry.getValue();
            }
            session.setAttribute(ParameterAndAttribute.ORDER_MAP, orderMap);
            session.setAttribute(ParameterAndAttribute.CARD, countProductsInCard);
            HashMap<Product, Integer> productMap = new HashMap<>();
            BigDecimal totalPrice = BigDecimal.valueOf(0.0);

        /*try {
            for (Map.Entry entry : orderMap.entrySet()) {
                long product_id = (Long) entry.getKey();
                Optional<Product> optionalProduct = productService.getProductById(product_id);
                if (optionalProduct.isPresent()) {
                    productMap.put(optionalProduct.get(), (int) entry.getValue());
                    BigDecimal price = optionalProduct.get().getPrice();
                    totalPrice = totalPrice.add(price);
                }
            }*/
            session.setAttribute(ParameterAndAttribute.PRODUCT_MAP, productMap);
            session.setAttribute(ParameterAndAttribute.TOTAL, totalPrice);
        /*} catch (ServiceException e) {
            logger.error("Impossible to find products:", e);
            throw new CommandException("Impossible to find products:", e);
        }*/
        return null;
    }
}
