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


            <h3 class="fw-light"><fmt:message key="add_product"/></h3>
            <fieldset>
              <form action="${pageContext.request.contextPath}/controller?command=add_product" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="add_product"/><br>
                <input type="text" name="title" title=<fmt:message key="add_product.title.placeholder"/> value="<c:out value="${requestScope.product.title}"/>" required placeholder=<fmt:message key="add_product.title.placeholder"/> pattern="[A-Za-zА-Яа-я-,.!?""%()\s]{2,100}" class="form-control input-lg"/><br>
                <TEXTAREA name="description" value="<c:out value="${requestScope.product.description}"/>" required title=<fmt:message key="add_product.description.placeholder"/> pattern=".{10,500}" class="form-control input-lg"/><fmt:message key="add_product.description.placeholder"/></TEXTAREA><br>
                <input type="text" name="manufacture" value="<c:out value="${requestScope.product.manufacture}"/>" required title=<fmt:message key="add_product.manufacture.placeholder"/> placeholder=<fmt:message key="add_product.manufacture.placeholder"/> pattern="[A-Za-zА-Яа-я-,.!?%()\s]{3,45}" class="form-control input-lg"/><br>
                <fmt:message key="add_product.type.placeholder"/><br>
                <select name="type"  class="form-select" required placeholder=<fmt:message key="add_product.type.placeholder"/>>
                  <c:forEach var="element" items="${product_types_list}">
                    <option value="${element.productTypeName}">${element.productTypeName}</option>
                  </c:forEach>
                </select><br><br>
                <input type="text" name="price" title="<fmt:message key="add_product.price.placeholder"/>" value="<c:out value="${requestScope.product.price}"/>" required placeholder="<fmt:message key="add_product.price.placeholder"/>" pattern="\d{1,5}\.\d{1,2}" class="form-control input-lg"/><br>
                <input type="file" name="image" title=<fmt:message key="add_product.image.placeholder"/> value="<c:out value="${requestScope.product.image}"/>" required placeholder=<fmt:message key="add_product.image.placeholder"/> pattern="([^s]+(.(?i)(jpg|png|gif|bmp))$)" class="form-control input-lg"/><br>
                <c:if test="${not empty message}"> <p><fmt:message key="${message}"/> ${productId}</p>  </c:if>
                <input type="submit"  value=<fmt:message key="add_product.add"/> >
              </form>
            </fieldset>
            <h3 class="fw-light"><fmt:message key="add_product.find_product_title"/></h3>
            <fieldset>
              <form action="${pageContext.request.contextPath}/controller?command=go_to_edit_product" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="go_to_edit_product"/><br>
                <input type="text" name="product_id" value="${productId}" required placeholder=<fmt:message key="add_product.edit_product.placeholder"/> title=<fmt:message key="add_product.edit_product.placeholder"/>  pattern="\d{1,6}\" class="form-control input-lg"/><br>
                <input type="submit"  value=<fmt:message key="add_product.find"/> >
              </form>
            </fieldset>


    <br><br>


      <h3 class="fw-light"><fmt:message key="add_product_type"/></h3>
      <fieldset>
        <form action="${pageContext.request.contextPath}/controller?command=add_product_type" method="post" enctype="multipart/form-data">
          <input type="hidden" name="command" value="add_product_type"/><br>
          <input type="text" name="product_type" value="<c:out value="${requestScope.product_type_add}"/>"  required placeholder=<fmt:message key="add_product_type.placeholder"/>  title= <fmt:message key="add_product_type.placeholder"/> pattern="[A-Za-zА-Яа-я-,.!?%()\s]{3,45}" class="form-control input-lg"/><br>
          <c:if test="${not empty message_product_type}"> <p><fmt:message key="${message_product_type}"/></p>  </c:if>
          <input type="submit"  value=<fmt:message key="add_product.add"/> >
        </form>
      </fieldset>


      <fieldset>
        <form action="${pageContext.request.contextPath}/controller?command=modify_product_type" method="post" enctype="multipart/form-data">
          <input type="hidden" name="command" value="modify_product_type"/>
          <fmt:message key="add_product.type.placeholder"/><br>
          <select name="delete_type_name"  class="form-select" required placeholder=<fmt:message key="add_product.type.placeholder"/>>
            <c:forEach var="element" items="${product_types_list}">
              <option value="${element.productTypeName}">${element.productTypeName}</option>
            </c:forEach>
          </select><br>
          <input type="text" name="modify_type_name" value="<c:out value="${requestScope.product_type_modify}"/>" placeholder=<fmt:message key="product_type.placeholder"/> title=<fmt:message key="product_type.placeholder"/> pattern="[A-Za-zА-Яа-я-,.!?%()\s]{3,45}" class="form-control input-lg"/>
          <c:if test="${not empty message_type_product}"> <p><fmt:message key="${message_type_product}"/></p>  </c:if><br>
          <input type="submit"  value=<fmt:message key="product_type.modify"/> >
        </form>
      </fieldset>

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

