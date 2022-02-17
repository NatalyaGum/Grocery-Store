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
            <br>
            <a href="${pageContext.request.contextPath}/controller?command=go_to_main_page" class="navbar-brand d-flex align-items-center">
                <H1><strong>&divonx;WebStore</strong></H1>
            </a>


        <c:if test="${not empty sessionScope.user}">
            <div class="text-white"><b><c:out value="${sessionScope.user.role}: ${sessionScope.user.email} "/></b><br>
                <a href="${pageContext.request.contextPath}/controller?command=edit_profile"
                   class="text-muted">  <fmt:message key="user.edit"/></a>
                <p><a href="${pageContext.request.contextPath}/controller?command=sign_out"
                      class="text-muted"><fmt:message key="user.logout"/></a></p></div>
        </c:if>


        <div>
            <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=EN"
                  class="text-muted">EN</a>&nbsp; </p>
            <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=RU"
                  class="text-muted">RU</a>&nbsp;</p>
        </div>
    </div>
    </div>


</header>
<main>

    <jsp:include page="${sessionScope.role}.jsp"/>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                <c:forEach var="product" items="${products_list}">
                    <div class="col">
                        <div class="card shadow-sm">

                            <img class="bd-placeholder-img card-img-top" src="${product.picture}" class="img-thumbnail" alt="${product.title}"  width="98%"/>

                            <div class="card-body">
                                <p class="card-text">${product.title} <br>${product.description} <br>${product.manufacture}</p>
                                    ${product.price} <fmt:message key="rub"/><br>
                                <fmt:message key="product.status"/>: ${product.active}
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_product&product_id=${product.productId}">
                                            <button type="button" class="btn btn-sm btn-outline-secondary"><fmt:message key="product.edit"/></button></a>
                                    </div>
                                    <small class="text-muted">#${product.productId}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>


            <%--For displaying Previous link except for the 1st page --%>
            <c:if test="${page_number != 1}">
                <a href="?command=product_maintenance&page=${page_number - 1}" class="text-muted"> <fmt:message key="previous_page" > </fmt:message></a>&nbsp;
            </c:if>

            <%--For displaying Page numbers.
            The when condition does not display a link for the current page--%>
            <c:choose>
                <c:when test="${products_list.size() != 0}">

                    <c:forEach begin="1" end="${pages_number}" var="i">
                        <c:choose>
                            <c:when test="${page_number eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                &nbsp; <a href="?command=product_maintenance&page=${i}" class="text-muted"> ${i} </a>&nbsp;
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                </c:when>
            </c:choose>

            <%--For displaying Next link --%>
            <c:if test="${page_number < pages_number}">
                &nbsp;<a href="?command=product_maintenance&page=${page_number + 1}" class="text-muted"> <fmt:message key="next_page"></fmt:message></a>
            </c:if>
        </div>


    </div>


</main>
<br>
<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#" class="text-muted"><fmt:message key="top"/></a>
        </p>
        <p class="mb-1"> &copy; WebShop</p>
        <h6 class="text-muted"><fmt:message key="help"/></h6>
        <p class="text-muted">+375170000000</p>

    </div>
</footer>


</body>
</html>

