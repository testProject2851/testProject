<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Все пользователи</title>
</head>
<body>
<h1>CRUD</h1>
<form method="post" action="<c:url value="/"/>">

    <input type="text" name="nameQuery" value="${search.name}" placeholder="введите имя"/><br/>

    <input type="number" name="id" value="${search.id}" placeholder="введите id" /><br/>
    <input type="date" pattern="[0-9]{2}\-[0-9]{2}\-[0-9]{4}"
           value="<fmt:formatDate value="${search.createdDate}" pattern="dd-MM-YYYY" />" placeholder="DD-MM-YYYY" name="createdDate"/><br/>
    <input type="number" value="${search.age}" name="age" placeholder="введите возраст" /><br/>
    Является администратором:
    <label for="admin_yes"><input type="radio" name="admin" value="true" id="admin_yes" /> Да</label>
    <label for="admin_no"><input type="radio" name="admin" value="false" id="admin_no" /> Нет</label>
    <br/>
    <input type="submit" value="искать!"/>
</form>

<h3>Все пользователи (<a href="<c:url value="/create" />">добавить</a>)</h3>

<table width="100%" border="1" cellpadding="0" cellspacing="0">
    <tr>
        <th>#ID</th>
        <th>Имя</th>
        <th>Возраст</th>
        <th>Администратор</th>
        <th>Дата создания</th>
        <th>Действия</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>
                <c:choose>
                    <c:when test="${user.admin}">
                        Да
                    </c:when>
                    <c:otherwise>
                        Нет
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <fmt:formatDate value="${user.createdDate}" pattern="dd MMM yy HH:mm"/>
            </td>
            <td>
                <a href="<c:url value="/edit/${user.id}" />">изменить</a>
                <a href="<c:url value="/delete/${user.id}" />">удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
Страницы:
<c:if test="${pages == 0}">
    <c:set var="pages" value="1"/>
</c:if>
<c:forEach begin="1" end="${pages}" var="i">
    <c:choose>
        <c:when test="${currentPage eq i}">
            <strong>${i}</strong>
        </c:when>
        <c:otherwise>
            <a href="<c:url value="/?page=${i}" />">${i}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
</body>
</html>