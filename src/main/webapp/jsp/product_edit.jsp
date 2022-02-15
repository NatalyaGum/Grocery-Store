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
        <p><a href="${pageContext.request.contextPath}/controller?command=change_locale&language=EN" class="text-muted">EN</a>
        </p><p>
        <a href="${pageContext.request.contextPath}/controller?command=change_locale&language=RU" class="text-muted">RU</a></p>
      </div>
    </div>
  </div>

</header>

<main>

  <div class="py-5 text-center container">
          <div class="card shadow-sm">
            <h3 class="fw-light"><fmt:message key="product.edit2"/> # <c:out value="${requestScope.product.productId}"/></h3>
            <table width="20%"> <tr><td>
              <img class="bd-placeholder-img card-img-top" src="${product.picture}" class="img-thumbnail" alt="${product.title}"  width="98%"/>
            </td></tr> </table>

            <fieldset>
              <form action="${pageContext.request.contextPath}/controller?command=edit_product&productId=${requestScope.product.productId}" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="edit_product"/><br>
                <input type="text" name="title" value="<c:out value="${requestScope.product.title}"/>" required title=<fmt:message key="add_product.title.placeholder"/> pattern="[A-Za-zА-Яа-я-,.!?""%()\s]{2,75}" class="form-control input-lg"/><br>
                <TEXTAREA name="description" value="<c:out value="${requestScope.product.description}"/>" required title=<fmt:message key="add_product.description.placeholder"/> pattern=".{10,500}" class="form-control input-lg"/><c:out value="${requestScope.product.description}"/></TEXTAREA><br>
                <input type="text" name="manufacture" value="<c:out value="${requestScope.product.manufacture}"/>" required title=<fmt:message key="add_product.manufacture.placeholder"/> pattern="[A-Za-zА-Яа-я-,.!?""%()\s]{3,100}" class="form-control input-lg"/><br>
                <fmt:message key="add_product.type.placeholder"/><br>
                <select name="type"  class="form-select" required>
                  <option value="${requestScope.product.productType}" selected>${requestScope.product.productType}</option>
                  <c:forEach var="element" items="${product_types_list}">
                    <option value="${element.productTypeName}">${element.productTypeName}</option>
                  </c:forEach>
                </select><br>
                <input type="text" name="price" value="<c:out value="${requestScope.product.price}"/>" required title="<fmt:message key="add_product.price.placeholder"/>" pattern="\d{1,5}\.\d{1,2}" class="form-control input-lg"/><br>
                <fmt:message key="product.status"/><br>
                <select name="active"  class="form-select" required title="Status">
                  <option value="${requestScope.product.active}" selected>${requestScope.product.active}</option>
                  <option value="true">true</option>
                  <option value="true">false</option>
                </select><br>
                <input type="file" name="image"  title=<fmt:message key="add_product.image.placeholder"/> pattern="([^s]+(.(?i)(jpg|png|gif|bmp))$)" class="form-control input-lg"/><br>
                <c:if test="${not empty message}"> <p><fmt:message key="${message}"/></p> </c:if>
                <input type="submit"  value=<fmt:message key="product.edit"/> >
              </form>
            </fieldset>


      </div>

    </div>

  </div>

</main>

<footer class="text-muted py-5">
  <div class="container">
    <p class="float-end mb-1">
      <a href="#" class="text-muted"><fmt:message key="top"/></a>
    </p>
    <p class="mb-1"> &copy; WebStore</p>

  </div>
</footer>

</body>
</html>

