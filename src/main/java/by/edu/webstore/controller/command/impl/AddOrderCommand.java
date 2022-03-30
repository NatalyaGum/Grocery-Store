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
import by.edu.webstore.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static by.edu.webstore.controller.command.ParameterAndAttribute.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddOrderCommand implements Command {

        static Logger logger = LogManager.getLogger();
        private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
        private static final String ADD_ADDRESS_ERROR_MESSAGE_KEY = "error.add_address";
        private static final String ADD_ORDER_CONFIRM_MESSAGE_KEY = "confirm.order_add";

        @Override
        public Router execute(HttpServletRequest request) throws CommandException {
            HttpSession session = request.getSession();
            HashMap<Product, Integer> productMap = (HashMap)session.getAttribute(PRODUCT_MAP);
            User user = (User) session.getAttribute(ParameterAndAttribute.USER);
            Map<String, Object> orderData = new HashMap<>();
            orderData.put(USER, session.getAttribute(ParameterAndAttribute.USER));
            orderData.put(TOTAL, session.getAttribute(TOTAL));
            orderData.put(PAYMENT_METHOD, request.getParameter(PAYMENT_METHOD));
            orderData.put(ADDRESS_ID,Long.parseLong(request.getParameter(ADDRESS)));
            long orderId;
            Router router;
            try {
                orderId = orderService.insertNewOrder(orderData, productMap);
                session.removeAttribute(PRODUCT_MAP);
                session.removeAttribute(CARD);
                request.setAttribute(ParameterAndAttribute.ADD_ORDER_MESSAGE,ADD_ORDER_CONFIRM_MESSAGE_KEY);
                router= new Router(PagePath.MAIN_PAGE, Router.RouterType.FORWARD);
            } catch (ServiceException e) {
                logger.error("Impossible to add new address:", e);
                throw new CommandException("Impossible to add new address:", e);
            }
            /*try {
                List<Address> addresses = orderService.findUserAddresses(user.getUserId());
                if (addresses.size() > 0) {
                   session.setAttribute(ParameterAndAttribute.ADDRESSES_LIST, addresses);
                }
            } catch (ServiceException e) {
                logger.error("Impossible to find addresses:", e);
                throw new CommandException("Impossible to find addresses:", e);
            }*/return router;
        }
    }