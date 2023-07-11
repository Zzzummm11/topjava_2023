<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>

<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form method="post" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${meal.id}">
    <dl class="description-list">
        <dt>DateTime:</dt>
        <dd><input type="datetime-local" name="dateTime" size=30"
                   value="${meal.dateTime}" required></dd>
    </dl>
    <dl class="description-list">
        <dt>Description:</dt>
        <dd><input type="text" name="description" size=30"
                   value="${meal.description}" required></dd>
    </dl>
    <dl class="description-list">
        <dt>Calories:</dt>
        <dd><input type="text" name="calories" size=30"
                   value="${meal.calories}" pattern="[0-9]+" title="Please, enter numbers" required></dd>
    </dl>
    <button type="submit">Save</button>
    <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</table>
</body>
</html>
