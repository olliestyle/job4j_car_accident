<%--
  Created by IntelliJ IDEA.
  User: murat
  Date: 04.03.2022
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
<div>
    Login as : ${user.username}
</div>
Все Заявления
<br>
<a href="<c:url value='/create'/>">Добавить инцидент</a>
    <div class="card-body">
        <table class="table">
            <tbody>
            <th>ID</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Адрес</th>
            <th>Тип происшествия</th>
            <c:forEach items="${accidents}" var="accident">
                <tr>
                    <td><c:out value="${accident.id}"/></td>
                    <td><c:out value="${accident.name}"/></td>
                    <td><c:out value="${accident.text}"/></td>
                    <td><c:out value="${accident.address}"/></td>
                    <td><c:out value="${accident.accidentType.name}"/></td>
                    <td>
                        <c:forEach items="${accident.rules}" var="rule">
                            <c:out value="${rule.name}"/>
                        </c:forEach>
                    </td>
                    <td>
                        <form action="<c:url value='/edit'/>" method='GET'>
                            <td colspan='2'>
                                <input type="hidden" name="id" value="${accident.id}">
                                <input type="hidden" name="name" value="${accident.name}">
                                <input type="hidden" name="text" value="${accident.text}">
                                <input type="hidden" name="address" value="${accident.address}">
                                <input type="hidden" name="accidentType.id" value="${accident.accidentType.id}">
                                <c:forEach items="${accident.rules}" var="rule">
                                    <input type="hidden" name="rIds" value="${rule.id}">
                                </c:forEach>
                                <input name="submit" type="submit" value="Редактировать" />
                            </td>
                        </form>
                    </td>
                    <td>
                        <a href="<c:url value='/update?id=${accident.id}'/>">Обновить название</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
