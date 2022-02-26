package by.edu.webstore.controller.command.impl;

import by.edu.webstore.controller.command.Command;
import by.edu.webstore.controller.command.CommandProvider;
import by.edu.webstore.controller.command.ParameterAndAttribute;
import by.edu.webstore.controller.command.Router;
import by.edu.webstore.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;

import java.util.Map;


public class AddToCardCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
      /*  HttpSession session=request.getSession();
        HashMap<Long,Integer> orderMap;
        int countProductsInCard=0;
        Integer count=Integer.parseInt(request.getParameter(ParameterAndAttribute.PRODUCT_COUNT));
        Long product_id = Long.parseLong(request.getParameter(ParameterAndAttribute.PRODUCT_ID));
        if (session.getAttribute(ParameterAndAttribute.ORDER_MAP)==null){
            orderMap=new HashMap<>();
        }else{
            orderMap=(HashMap<Long, Integer>) session.getAttribute(ParameterAndAttribute.ORDER_MAP);
        }
        orderMap.put(product_id,count);
        for(Map.Entry entry: orderMap.entrySet()){
            countProductsInCard+=(int)entry.getValue();
        }
        session.setAttribute(ParameterAndAttribute.ORDER_MAP,orderMap);
        session.setAttribute(ParameterAndAttribute.CARD,countProductsInCard);



        Command command = CommandProvider.defineCommand("go_to_catalog");
        Router router = command.execute(request);*/
        return null;
    }
}
