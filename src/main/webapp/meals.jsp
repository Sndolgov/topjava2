<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://topjava/functions" prefix="func" %>

<html>
<head>
    <link rel="stylesheet" href="resourses/style.css" type="text/css" />

    <title>Meals</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table border="3" bordercolor="grey">
    <tr>
        <th>Дата/Время</th>
        <th>Id</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Редактировать</th>
        <th>Удалить</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr class="${meal.exceed?'red':'green'}">
            <td>${meal.id}</td>
            <td>${func:formatLocalDateTime(meal.dateTime)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="index.html">Edit</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>