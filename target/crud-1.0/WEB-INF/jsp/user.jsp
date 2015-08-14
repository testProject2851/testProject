<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${user.id eq null}">
        <c:set var="title" value="Создание нового"/>
        <c:set var="buttonValue" value="Создать!"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Редактирование"/>
        <c:set var="buttonValue" value="Изменить!"/>
    </c:otherwise>
</c:choose>
<html>
    <head>
        <title></title>
    </head>
    <body>
    <h1>CRUD</h1>
        <h3>${title} пользователя (<a href="<c:url value="/"/>">вернуться</a>)</h3>

        <form:form commandName="user" method="post" action="/processUser">
            <form:hidden path="id"/>
            Имя: <form:input path="name"/><br/>
            Возраст: <form:input pattern="^\d+$" path="age"/><br/>
            <label for="admin"><input:checkbox path="admin"/> Является администратором</label><br/><br/>
            <input type="submit" value="${buttonValue}"/>
        </form:form>
    </body>
</html>
