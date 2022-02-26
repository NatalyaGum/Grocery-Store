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
                <fmt:message key="user.edit"/>
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


                <h3 class="fw-light"><fmt:message key="order.checkout"/></h3><br><br>
                <h4 class="fw-light"><fmt:message key="order.products_in"/></h4><br>
                <table ><tr><th> </th><th><fmt:message key="order.title"/></th><th><fmt:message key="order.price"/></th><th><fmt:message key="order.qty"/></th></tr>
                <c:forEach var="entry" items="${product_map}">
                    <tr><td width="20%"/>
                        <img class="bd-placeholder-img card-img-top" src="${entry.key.picture}" class="img-thumbnail" alt="${entry.key.title}" width="98%"/>
                        </td><td>
                              ${entry.key.title}
                          </td><td>
                            ${entry.key.price} <fmt:message key="rub"/>
                          </td><td>
                            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" method="post"
                                  action="${pageContext.request.contextPath}/controller?command=go_to_card&productId=${entry.key.productId}">
                                <input type="number" name="product_count" min="0" max="20" value=${entry.value} step="1" class="btn btn-sm btn-outline-secondary" >
                                <button type="submit" class="btn btn-sm btn-outline-secondary"><fmt:message key="order.update_qty"/>
                            </form>
                             </td> </tr>
                </c:forEach>
                    </table>
                <hr>
                <div align="right">${total} <fmt:message key="rub"/></div>




                    <p>
                        <form action="${pageContext.request.contextPath}/controller?command=create_order" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="create_order"/>
                <br>  <h4 class="fw-light"><fmt:message key="order.recipient"/></h4>
                <c:out value="${sessionScope.user.name} ${sessionScope.user.surname} ${sessionScope.user.phone}"/>

                <br><br><h4 class="fw-light"><fmt:message key="order.address"/></h4>
                <c:forEach var="element" items="${addresses_list}">
                    <input type="radio" name="address" value=${element.addressId} required/> Г. Минск , ул./просп.${element.streetName}, дом ${element.buildingNumber}
                    <c:if test="${element.apartmentNumber ne '0'}"> кв. ${element.apartmentNumber}. </c:if>
                     <c:if test="${element.comment ne 'NO COMMENTS'}"> Примечание: ${element.comment} </c:if>
                    <br>
                </c:forEach>

                <br><a href="${pageContext.request.contextPath}/controller?command=go_to_add_address">
                   <h6> <fmt:message key="order.add_address"/> </h6></a>

                        <br><h4 class="fw-light"><fmt:message key="order.payment_method"/></h4>
                            <input type="radio" name="payment_method" value="cash" checked /><fmt:message key="order.method_cash"/>
                           &nbsp;<input type="radio" name="payment_method" value="card" /><fmt:message key="order.method_card"/><br>
                <c:if test="${not empty message}"><p class="text-muted"><fmt:message key="${message}"/></p></c:if>
<c:if test="${not empty sessionScope.product_map and not empty addresses_list}">   <br><input type="submit"  value=<fmt:message key="order.create"/> ></c:if>
                    </form></p>

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

