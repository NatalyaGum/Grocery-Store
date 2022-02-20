<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

        <div class="col-sm-4 offset-md-1 py-4">
            <h4 class="text-black"><fmt:message key="maintenance"/></h4>
            <ul class="list-unstyled">
                <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=product_maintenance" class= "text-muted">&divonx;<fmt:message key="product.maintenance"/></a></li>
                <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=go_to_product_add" class= "text-muted">&divonx;<fmt:message key="add_product"/></a></li>
                <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=user_maintenance" class= "text-muted">&divonx;<fmt:message key="user.maintenance"/></a></li>
                <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=order_maintenance" class= "text-muted">&divonx;<fmt:message key="order.maintenance"/></a></li>
                <br>
                <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=edit_profile" class="text-muted">&divonx;<fmt:message key="user.edit"/></a></li>
                <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=sign_out" class= "text-muted">&divonx;<fmt:message key="user.sign_out"/></a></li>
                </ul>
        </div>