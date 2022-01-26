<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>JSP - Hello World</title>
</head>
<body>
<jsp:forward page="jsp/main.jsp"></jsp:forward>

<h1>"Hello World!"
</h1>
<form action="controller" method="get">
    <input type="text" name="input_n" value="0"/>
<br/>
    <input type="submit" value="push"/>
    <div class="row">

    </div>
</form>
</body>
</html>