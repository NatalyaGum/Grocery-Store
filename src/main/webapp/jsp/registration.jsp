<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <meta charset="utf-8">

  <title>WebShop</title>


  <style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }
  </style>


</head>
<body>

<header>

  <div class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container">
      <a href="${pageContext.request.contextPath}/controller?command=go_to_main_page" class="navbar-brand d-flex align-items-center">
        <strong>&divonx;WebStore</strong>
      </a>
      <div class="col-sm-8 col-md-7 py-4">
        <h6 class="text-white"><fmt:message key="help"/></h6>
        <p class="text-muted">+375170000000</p>
      </div>
      <div>
        <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=EN">EN</a>
        </p><p>
        <a href="${pageContext.request.contextPath}/controller?command=change_locale&language=RU">RU</a></p>
      </div>
    </div>
  </div>

</header>

<main>

  <div class="py-5 text-center container">

          <div class="card shadow-sm">
            <h1 class="fw-light"><fmt:message key="sign_up.title"/></h1>
            <fieldset>
              <form action="${pageContext.request.contextPath}/controller?command=sign_up" method="post">
                <input type="hidden" name="command" value="sign_up"/><br>
                <input type="text" name="name" value="<c:out value="${requestScope.user.name}"/>" required placeholder=<fmt:message key="sign_up.name.placeholder"/> pattern="[a-zA-Zа-яА-Я-\s]{1,45}"/><br><br>
                <input type="text" name="surname" value="<c:out value="${requestScope.user.surname}"/>" required placeholder=<fmt:message key="sign_up.surname.placeholder"/> pattern="[a-zA-Zа-яА-Я-\s]{1,45}"/><br><br>
                <input type="tel" name="phone" value="<c:out value="${requestScope.user.number}"/>" required placeholder="+375*********" pattern="^\+375\d{9}$"/><br><br>
                <input type="email" name="email" value="<c:out value="${requestScope.user.email}"/>" required placeholder=<fmt:message key="sign_up.email.placeholder"/> pattern="(([A-Za-z\d._]+){5,25}@([A-Za-z]+){3,10}\.([a-z]+){2,3})"/><br><br>
                <input type="password" name="password" value="<c:out value="${requestScope.user.password}"/>" required placeholder=<fmt:message key="sign_in.password.title"/> pattern="\S{6,20}"/><br><br>
                <input type="password" name="repeated_password" value="<c:out value="${requestScope.user.repeated_password}"/>" required placeholder=<fmt:message key="sign_up.repeat_password.placeholder"/> pattern="\S{6,20}"/><br><br>
                <c:if test="${not empty message}"> <p><fmt:message key="${message}"/></p>  </c:if>
                <input type="submit"  value=<fmt:message key="sign_up.submit"/> >
              </form>
            </fieldset>

      </div>

  </div>

</main>

<footer class="text-muted py-5">
  <div class="container">
    <p class="float-end mb-1">
      <a href="#"><fmt:message key="top"/></a>
    </p>
    <p class="mb-1"> &copy; WebStore</p>

  </div>
</footer>

</body>
</html>

