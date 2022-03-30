<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="utf-8">

    <title><fmt:message key="title"/></title>

</head>
<body>
<header>

    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container">
            <a href="${pageContext.request.contextPath}/controller?command=go_to_main_page" class="navbar-brand d-flex align-items-center">
                <H1><strong>&divonx;WebStore</strong></H1>
            </a>


        <c:if test="${not empty sessionScope.user}">
            <div class="text-white"><b><c:out value="${sessionScope.user.role}: ${sessionScope.user.email} "/></b><br>
                <a href="${pageContext.request.contextPath}/controller?command=go_to_update_profile" class="text-muted"><fmt:message key="user.edit"/></a>
                <p> <a href="${pageContext.request.contextPath}/controller?command=sign_out" class="text-muted"><fmt:message key="user.sign_out"/></a></p></div>
        </c:if>

        <div>
            <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=EN" class="text-muted">EN</a>&nbsp; </p>
            <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=RU" class="text-muted">RU</a>&nbsp;</p>
        </div>

    </div>
    </div>
</header>

<main>


    <div class="py-5 text-center container">
        <table width="90%"align="center" > <tr border="2px"><td width="45%" align="left" valign="top">
            <jsp:include page="${sessionScope.role}.jsp"/>
        </td>
            <td>

<c:forEach var="order" items="${orders}">


    <p class="fw-light"> ${order.orderDate.format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))} #${order.orderId}
    <form action="${pageContext.request.contextPath}/controller?command=go_to_orders_admin&page=${page_number}&orderId=${order.orderId}" method="post" >
    <select name="order_status"   required title="Status">
        <option value="${order.status}" selected>${order.status}</option>
        <option value="REJECTED">REJECTED</option>
        <option value="PREPARING">PREPARING</option>
        <option value="ORDERED">ORDERED</option>
        <option value="DELIVERED">DELIVERED</option>
    </select>
    <input type="submit"  value=<fmt:message key="product.edit"/> >
</form>
    </p>

                <table ><tr><th> </th><th><fmt:message key="order.title"/></th><th><fmt:message key="order.price"/></th><th><fmt:message key="order.qty"/></th></tr>
                <c:forEach var="entry" items="${order.products}">
                    <tr><td width="20%"/>
                        <img class="bd-placeholder-img card-img-top" src="${entry.key.picture}" class="img-thumbnail" alt="${entry.key.title}" width="98%"/>
                        </td><td>
                              ${entry.key.title}
                          </td><td>
                            ${entry.key.price} <fmt:message key="rub"/>
                          </td><td>
                                ${entry.value}

                             </td> </tr>
                </c:forEach>
                    </table>
                <hr>
                <div align="right">${order.cost} <fmt:message key="rub"/></div>

    <p class="fw-light">
                <br><fmt:message key="order.recipient"/>:
                <c:out value="${order.user.name} ${order.user.surname} ${order.user.phone}"/>

                <br><fmt:message key="order.address"/>:

    Г. Минск , ул./просп.${order.address.streetName}, дом ${order.address.buildingNumber}
    <c:if test="${order.address.apartmentNumber ne '0'}"> кв. ${order.address.apartmentNumber}. </c:if>
    <c:if test="${order.address.comment ne 'NO COMMENTS'}"> Примечание: ${order.address.comment} </c:if>

    <br><fmt:message key="order.payment_method"/>: ${order.method}
    <hr>
    </p><br>
</c:forEach>

                <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${page_number != 1}">
                    <a href="?command=go_to_order_admins&page=${page_number - 1}" class="text-muted"> <fmt:message key="previous_page" > </fmt:message></a>&nbsp;
                </c:if>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <c:choose>
                    <c:when test="${products_list.size() != 0}">
                        <c:forEach begin="1" end="${pages_number}" var="i">
                            <c:choose>
                                <c:when test="${page_number eq i}">
                                    ${i}
                                </c:when>
                                <c:otherwise>
                                    &nbsp; <a href="?command=go_to_orders_admin&page=${i}" class="text-muted"> ${i} </a>&nbsp;
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                </c:choose>

                <%--For displaying Next link --%>
                <c:if test="${page_number < pages_number}">
                    &nbsp;<a href="?command=go_to_orders_admin&page=${page_number + 1}" class="text-muted"> <fmt:message key="next_page"></fmt:message></a>
                </c:if>

            </td></tr> </table>

    </div>
    </main>
<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a class="text-muted" href="#"><fmt:message key="top"/></a>
        </p>
        <p class="mb-1"> &copy; WebShop</p>

    </div>
</footer>


</body>
</html>

