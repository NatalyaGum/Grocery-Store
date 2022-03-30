<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<div class="col-sm-4 offset-md-1 py-4" >
    <a href="${pageContext.request.contextPath}/controller?command=go_to_card"><h4><fmt:message key="order.cart"/> ${card}</h4></a>
    <a href="${pageContext.request.contextPath}/controller?command=go_to_catalog"><h4><fmt:message key="catalog"/></h4></a>
    <ul class="list-unstyled">
        <c:forEach var="element" items="${product_types_list}">
        <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=go_to_product_type&type=${element.productTypeId}" class= "text-muted">${element.productTypeName}</a></li>
        <hr >
        </c:forEach>
    <ul class="list-unstyled">
        <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=go_to_orders" class= "text-muted">&divonx;<fmt:message key="orders"/></a></li>
        <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=go_to_update_profile" class="text-muted">&divonx;<fmt:message key="user.edit"/></a></li>
        <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=sign_out" class= "text-muted">&divonx;<fmt:message key="user.sign_out"/></a></li>
    </ul>
</div>



