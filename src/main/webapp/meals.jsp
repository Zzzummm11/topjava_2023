<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="?action=add">Add meal</a>
<p>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${mealToList}" var="mealTo">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
        <c:set var="font_color" value="${mealTo.excess ? 'red' : 'green'}"/>
        <tr class="${font_color}">
            <td>${fn:replace(mealTo.dateTime, 'T', ' ')}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="?id=${mealTo.id}&action=update">Update</a></td>
            <td><a href="?id=${mealTo.id}&action=delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
