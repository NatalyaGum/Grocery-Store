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
        <p><a class="text-muted" href="${pageContext.request.contextPath}/controller?command=change_locale&language=EN">EN</a>
        </p><p>
        <a class="text-muted" href="${pageContext.request.contextPath}/controller?command=change_locale&language=RU">RU</a></p>
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

        <c:if test="${not empty message}"> <b> <h2><fmt:message key="${message}"/></h2></b><br>  </c:if>

            </fieldset>
            <h3 class="fw-light"><fmt:message key="user.block_by_email"/></h3>
            <fieldset>
              <form action="${pageContext.request.contextPath}/controller?command=go_to_user_maintenance" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="block_user"/><br>
                <input type="email" name="block_email" required  title="<fmt:message key="sign_up.email.title"/>: *****@***.**"  pattern="(([A-Za-z\d._]+){5,25}@([A-Za-z]+){3,10}\.([a-z]+){2,3})"/><br><br>
                <input type="submit"  value=<fmt:message key="user.block_by_email"/> >
              </form>
            </fieldset>


    <br><br>

      <h3 class="fw-light"><fmt:message key="user.make_admin"/></h3>
      <fieldset>
        <form action="${pageContext.request.contextPath}/controller?command=go_to_user_maintenance" method="post" enctype="multipart/form-data">
          <input type="hidden" name="command" value="make_admin"/><br>
          <input type="email" name="admin_email" required  title="<fmt:message key="sign_up.email.title"/>: *****@***.**"  pattern="(([A-Za-z\d._]+){5,25}@([A-Za-z]+){3,10}\.([a-z]+){2,3})"/><br><br>

          <input type="submit"  value=<fmt:message key="user.grant_permission"/> >
        </form>
      </fieldset>

        <br><br>
        <h3 class="fw-light"><fmt:message key="user.list"/></h3>
        <c:forEach var="element" items="${users_list}">
        ID:${element.userId} Name: ${element.name} Surname: ${element.surname} Phone: ${element.phone} Email: ${element.email}
         <br> ${element.role} ${element.status}
          <hr>
        </c:forEach>
      </td></tr> </table>




  </div>

</main>

<footer class="text-muted py-5">
  <div class="container">
    <p class="float-end mb-1">
      <a class="text-muted" href="#"><fmt:message key="top"/></a>
    </p>
    <p class="mb-1"> &copy; WebStore</p>

  </div>
</footer>

</body>
</html>

