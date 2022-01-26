
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>WebShop</title>
    <%@ include file="/jsp/header.jsp" %>

</head>
<body>
<table width="100%">
    <tbody>
    <tr class="c5"><td class="c8" colspan="1" rowspan="1">img<p ></p></td>
        <td class="c6" colspan="1" rowspan="1"><H1>Доставка продуктов </h1></td>
        <td colspan="1" rowspan="1"><p class="c1"><span class="c4">Логин</span></p><p ><span class="c4">Пароль</span></p><p class="c1"><span class="c4">Регистрация</span></p></td>
    </tr>
    <tr><td colspan="1" rowspan="1"><p class="c1">
        <span class="c4">Каталог</span></p><p class="c1 c2"></p></td>
        <td class="c6" colspan="1" rowspan="1">
            <table class="c9"><tbody>
            <tr class="c5">
                <td class="c0" colspan="1" rowspan="1"> <p class="c1"><span class="c4">Продукт</span></p></td>
                <td class="c0" colspan="1" rowspan="1"><p class="c1"><span class="c4">Продукт</span></p></td>
                <td class="c0" colspan="1" rowspan="1"><p class="c1"><span class="c4">Продукт</span></p></td></tr>
            <tr class="c5"><td class="c0" colspan="1" rowspan="1"><p class="c1"><span class="c4">Продукт</span></p></td>
                <td class="c0" colspan="1" rowspan="1"><p class="c1"><span class="c4">Продукт</span></p></td>
                <td class="c0" colspan="1" rowspan="1"><p class="c1"><span class="c4">Продукт</span></p></td></tr>
            <tr class="c5"><td class="c0" colspan="1" rowspan="1"><p class="c1"><span class="c4">Продукт</span></p></td>
                <td class="c0" colspan="1" rowspan="1"><p class="c1"><span class="c4">Продукт</span></p></td>
                <td class="c0" colspan="1" rowspan="1"><p class="c1"><span class="c4">Продукт</span></p></td></tr>
            </tbody></table>
        </td><td class="c7" colspan="1" rowspan="1"><p class="c1 c2"><span class="c4"></span></p></td></tr>
    <tr class="c5"><td class="c8" colspan="1" rowspan="1"><p class="c1 c2"><span class="c4"></span></p></td>
        <td class="c6" colspan="1" rowspan="1"><p class="c1 c2"><span class="c4"></span></p></td>
        <td class="c7" colspan="1" rowspan="1"><p class="c1 c2"><span class="c4"></span></p></td>
    </tr></tbody></table><p class="c3 c2"><span class="c4"></span></p>


<div >
    <h4>
        <a  href="${pageContext.request.contextPath}/controller?command=find_all_users">
            Найти
        </a>
    </h4>
</div>

    <a  href="${pageContext.request.contextPath}/jsp/registration.jsp">
        Зарегистрироваться
    </a>
    <br>
    <a  href="${pageContext.request.contextPath}/jsp/main.jsp">
        Главная
    </a>
<br/>
<div>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="find_all_users">

        <input type="submit" value="find_all_users"/>
    </form>
</div>
</body>
</html>
