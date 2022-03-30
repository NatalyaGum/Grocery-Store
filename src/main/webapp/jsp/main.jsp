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
            <a href="${pageContext.request.contextPath}/controller?command=go_to_main_page"
               class="navbar-brand d-flex align-items-center">
                <H1><strong>&divonx;WebStore</strong></H1>
            </a>


            <c:if test="${sessionScope.role eq 'guest'}">
            <div>
                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" method="post"
                      action="${pageContext.request.contextPath}/controller?command=sign_in">
                    <input class="form-control form-control-dark" type="email" name="email"
                           value="<c:out value="${requestScope.user_email}"/>" required placeholder=
                               <fmt:message
                                       key="sign_up.email.placeholder"/> pattern="(([A-Za-z\d._]+){5,25}@([A-Za-z]+){3,10}\.([a-z]+){2,3})"/>
                    <input class="form-control form-control-dark" type="password" name="password"
                           value="<c:out value="${requestScope.user.password}"/>" required placeholder=
                               <fmt:message key="sign_in.password.title"/> title="<fmt:message key="sign_in.password.title"/>" pattern="\S{6,20}"/>
                    <div class="text-end">
                        <button type="submit" class="btn btn-outline-light me-2"><fmt:message
                                key="sign_in.title"/></button>

                        <a href="${pageContext.request.contextPath}/controller?command=go_to_registration">
                            <button type="button" class="btn btn-outline-light me-2"><fmt:message
                                    key="sign_up.submit"/></button>
                        </a>
                    </div>
                </form>

            </div>
        </div>
        </c:if>

        <c:if test="${not empty sessionScope.user}">
            <div class="text-white"><b><c:out value="${sessionScope.user.role}: ${sessionScope.user.email} "/></b><br>
                <a href="${pageContext.request.contextPath}/controller?command=go_to_update_profile"
                   class="text-muted">  <fmt:message key="user.edit"/></a>
                <p><a href="${pageContext.request.contextPath}/controller?command=sign_out"
                      class="text-muted"><fmt:message key="user.sign_out"/></a></p></div>
        </c:if>

        <div>
            <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=EN"
                  class="text-muted">EN</a>&nbsp; </p>
            <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=RU"
                  class="text-muted">RU</a>&nbsp;</p>
        </div>
        <c:if test="${not empty message}"><p class="text-muted"><fmt:message key="${message}"/></p></c:if>
    </div>
    </div>


</header>
<main>


                <section class="py-5 text-center container">
                    <table width="100%">
                        <tr>
                            <td width=40%>
                                <jsp:include page="${sessionScope.role}.jsp"/>
                            </td>
                            <td align="center">
                                <c:if test="${not empty add_order_message}"><p class="text-muted"><fmt:message key="${add_order_message}"/></p><br></c:if>
                    <h1 class="fw-light"><fmt:message key="title2"/></h1>
                    <p class="lead text-muted"><fmt:message key="title3"/></p>
                    <p>
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_catalog"
                           class="btn btn-primary my-2"><fmt:message key="catalog.open"/></a>
                    </p>
                            </td>
                        </tr>
                    </table>
                </section>



</main>
<br><br><br><br>   <br><br><br><br>
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

