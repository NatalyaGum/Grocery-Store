
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="element" items="${users_list}">

                <div>
                    <h5 class="card-title card-text">

                            Email ${element.email}

                    </h5>
                    <p class="card-text">
                        Name: ${element.Name}</br>
                        Phone: ${element.phone}
                    </p>
                </div>

</c:forEach>
</body>
</html>
