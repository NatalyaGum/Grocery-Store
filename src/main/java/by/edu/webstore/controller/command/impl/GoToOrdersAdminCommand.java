package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.Order;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.OrderService;
import by.edu.webstore.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.edu.webstore.controller.command.ParameterAndAttribute.*;

public class GoToOrdersAdminCommand implements Command{

        private static final Logger logger = LogManager.getLogger();
        private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();

        @Override
        public Router execute(HttpServletRequest request) throws CommandException {
            HttpSession session = request.getSession();
            User user= (User)session.getAttribute(USER);
            int page = 1;
            int recordsPerPage = 3;
            if (request.getParameter(ORDERID) != null) {
                changeOrderStatusCommand(request);
            }
            if (request.getParameter(PAGE) != null) {
                page = Integer.parseInt(request.getParameter(PAGE));
            }
            try {
                List<Order> orders = orderService.findAllOrders((page - 1) * recordsPerPage, recordsPerPage);
                int totalProductNumber = orderService.findTotalOrdersNumber();
                int pagesNumber = (int) Math.ceil(totalProductNumber * 1.0 / recordsPerPage);
                session.setAttribute(ORDERS, orders);
                session.setAttribute(PAGES_NUMBER, pagesNumber);
                session.setAttribute(PAGE_NUMBER, page);
                session.setAttribute(PRODUCTS_LIST, orders);
                return new Router(PagePath.ADMIN_ORDERS, Router.RouterType.FORWARD);
            } catch (ServiceException e) {
                logger.error("Impossible to find orders:", e);
                throw new CommandException("Impossible to find orders:", e);
            }

        }
    void changeOrderStatusCommand(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long orderId=Long.parseLong(request.getParameter(ORDERID));
        String status=request.getParameter(ORDERSTATUS);
        try {
            orderService.updateOrderStatus(orderId,status);
        } catch (ServiceException e) {
            logger.error("Impossible to update order:", e);
            throw new CommandException("Impossible to update order:", e);
        }

    }
    }


