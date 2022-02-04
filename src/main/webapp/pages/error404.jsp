<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>


<html lang="ru">
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <meta charset="utf-8">

  <title>WebShop</title>



</head>
<body>

<header>

  <div class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container">
      <a href="${pageContext.request.contextPath}/controller?command=go_to_main_page" class="navbar-brand d-flex align-items-center">
        <H1> <strong>&divonx;WebStore</strong></H1>
      </a>
      <div class="col-sm-8 col-md-7 py-4">
        <h6 class="text-white">Служба поддерки круглосуточно. </h6>
        <p class="text-muted">+375170000000</p>
      </div>
    </div>
  </div>

</header>

<main>

  <div class="py-5 text-center container">

          <div class="card shadow-sm">
            <h1 class="fw-light">Регистрация</h1>
            <fieldset>
              <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="sign_up"/><br>
                <input type="text" name="userName" required placeholder="${name}" pattern="[a-zA-Zа-яА-Я-\s]{1,45}"/><br><br>
                <input type="tel" name="phone" required placeholder="${phone} +375*********" pattern="\+375\d{9}"/><br><br>
                <input type="email" name="login" required placeholder="${email}" maxlength="45"/><br><br>
                <input type="password" name="password" required placeholder="${password} ${password_rules}" pattern="[A-Za-z\d]{5,15}"/><br><br>
                <input type="submit"  value="Sing-up"/>
              </form>
            </fieldset>

      </div>

  </div>

  <div>
    Request from ${pageContext.errorData.requestURI} is failed<br/>
    Servlet name: ${pageContext.errorData.servletName}<br/>
    Status code: ${pageContext.errorData.statusCode}<br/>
    Exception: ${pageContext.exception}<br/>
    Message from exception: ${pageContext.exception.message}<br/>
    stack trace: ${pageContext.exception.stackTrace}<br/>

    </div>
</main>

<footer class="text-muted py-5">
  <div class="container">
    <p class="float-end mb-1">
      <a href="#">Вернуться наверх</a>
    </p>
    <p class="mb-1"> &copy; WebShop</p>

  </div>
</footer>





</body>
</html>

