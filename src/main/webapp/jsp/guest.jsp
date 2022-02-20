<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>


<div class="col-sm-4 offset-md-1 py-4">
    <a href="${pageContext.request.contextPath}/controller?command=go_to_velcome" > <h4><fmt:message key="order.cart"/> ${card}</h4></a>
    <ul class="list-unstyled">
        <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=go_to_authorization" class= "text-muted">&divonx;<fmt:message key="orders"/></a></li>
        <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=go_to_authorization" class="text-muted">&divonx;<fmt:message key="user.edit"/></a></li>
        <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=go_to_authorization" class= "text-muted">&divonx;<fmt:message key="user.sign_out"/></a></li>
    </ul>
</div>
<div class="col-sm-4 offset-md-1 py-4">
    <a href="${pageContext.request.contextPath}/controller?command=go_to_catalog" > <h4><fmt:message key="catalog"/></h4></a>
    <ul class="list-unstyled">
        <c:forEach var="element" items="${product_types_list}">
            <li><a class="col-10 mb-1 small" href="${pageContext.request.contextPath}/controller?command=go_to_product_type&type=${element.productTypeName}" class= "text-muted">${element.productTypeName}</a><hr></li>

        </c:forEach>
    </ul>
</div>


