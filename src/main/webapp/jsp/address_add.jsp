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
      <c:if test="${not empty message}"><p class="text-muted"><fmt:message key="${message}"/></p></c:if>
    </div>
  </div>
</header>

<main>

  <div class="py-5 text-center container">
    <table width="90%"align="center" > <tr border="2px"><td width="45%" align="left" valign="top">
      <jsp:include page="${sessionScope.role}.jsp"/>
      </td>
      <td>


            <h3 class="fw-light"><fmt:message key="add_address"/></h3>
            <fieldset>
              <div class="form-control input-lg">г. Минск</div>
              <form action="${pageContext.request.contextPath}/controller?command=add_address" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="add_address"/><br>
                <input type="text" name="street" title=<fmt:message key="add_address.street.placeholder"/> value="<c:out value="${requestScope.address.street}"/>" required placeholder=<fmt:message key="add_address.street.placeholder"/> pattern="[A-Za-zА-Яа-я-,.\s]{3,45}" class="form-control input-lg"/><br>
                <input type="text" name="building" value="<c:out value="${requestScope.address.building}"/>" required title=<fmt:message key="add_address.building.placeholder"/> placeholder=<fmt:message key="add_address.building.placeholder"/> pattern="[A-Za-zА-Яа-я-,./\d\s]{1,10}" class="form-control input-lg"/><br>
                <input type="text" name="apartment" value="<c:out value="${requestScope.address.apartment}"/>" title=<fmt:message key="add_address.apartment.placeholder"/> placeholder=<fmt:message key="add_address.apartment.placeholder"/> pattern="\d{0,6}"" class="form-control input-lg"/><br>

                <fmt:message key="add_address.comment.placeholder"/><br>
                <TEXTAREA name="comment" value="<c:out value="${requestScope.address.comment}"/>"  title=<fmt:message key="add_address.comment.placeholder"/> pattern=".{0,500}" class="form-control input-lg"/></TEXTAREA><br>
               <c:if test="${not empty message}"> <p><fmt:message key="${message}"/> </p>  </c:if>
                <input type="submit"  value=<fmt:message key="add_product.add"/> >
              </form>
              </fieldset>


    <br><br>

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

