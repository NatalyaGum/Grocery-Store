
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body><h1>
${users_list[1].name}
</h1>
<c:forEach var="element" items="${users_list}">

                <div>
                    <h5 >

                            Email ${element.email}

                    </h5>
                    <p >
                        Name: ${element.name}</br>
                        Phone: ${element.phone}
                    </p>
                </div>

</c:forEach>
</body>
</html>
