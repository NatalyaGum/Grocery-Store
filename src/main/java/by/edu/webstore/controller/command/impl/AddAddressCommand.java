package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.PagePath;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.entity.Address;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.CommandException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.OrderService;
import by.edu.webstore.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAddressCommand implements Command {
    static Logger logger = LogManager.getLogger();
    private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
    private static final String ADD_ADDRESS_ERROR_MESSAGE_KEY = "error.add_address";
    private static final String ADD_PRODUCT_CONFIRM_MESSAGE_KEY = "confirm.product_add";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(ParameterAndAttribute.MESSAGE);
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        Map<String, String> addressData = new HashMap<>();
        addressData.put(ParameterAndAttribute.STREET, request.getParameter(ParameterAndAttribute.STREET));
        addressData.put(ParameterAndAttribute.BUILDING, request.getParameter(ParameterAndAttribute.BUILDING));
        if(!request.getParameter(ParameterAndAttribute.APARTMENT).equals("")){
        addressData.put(ParameterAndAttribute.APARTMENT, request.getParameter(ParameterAndAttribute.APARTMENT));}
        if(!request.getParameter(ParameterAndAttribute.COMMENT).equals("Notation")){
        addressData.put(ParameterAndAttribute.COMMENT, request.getParameter(ParameterAndAttribute.COMMENT));}
        long id;
        Router router;
        try {
            id = orderService.insertNewAddress(addressData);
            if (id > 0) {
                session.setAttribute(ParameterAndAttribute.PRODUCT_ID, id);
                router= new Router(PagePath.ORDER_PAGE, Router.RouterType.FORWARD);
            } else {
                request.setAttribute(ParameterAndAttribute.ADDRESS, addressData);
                request.setAttribute(ParameterAndAttribute.MESSAGE, ADD_ADDRESS_ERROR_MESSAGE_KEY);
                router= new Router(PagePath.ADDRESS_ADD_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Impossible to add new address:", e);
            throw new CommandException("Impossible to add new address:", e);
        }
        try {
            List<Address> addresses = orderService.findUserAddresses(user.getUserId());
            if (addresses.size() > 0) {
                session.setAttribute(ParameterAndAttribute.ADDRESSES_LIST, addresses);
            }
        } catch (ServiceException e) {
            logger.error("Impossible to find addresses:", e);
            throw new CommandException("Impossible to find addresses:", e);
        }return router;
    }
}
