
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed<br/>
Servlet name: ${pageContext.errorData.servletName}<br/>
Status code: ${pageContext.errorData.statusCode}<br/>
Exception: ${pageContext.exception}<br/>
Message from exception: ${pageContext.exception.message}<br/>
stack trace: ${pageContext.exception.stackTrace}<br/>
<a href="index.jsp"> Back to index</a>
</body>
</html>
