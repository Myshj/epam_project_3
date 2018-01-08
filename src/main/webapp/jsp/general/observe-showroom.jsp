<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 07.01.2018
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${not empty showroom}">
            <h2>${showroom.name}</h2>
            <table class="table table-bordered">
                <tr>
                    <th>Name:</th>
                    <td>${showroom.name}</td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>${address}</td>
                </tr>
                <tr>
                    <th>Expositions:</th>
                    <td>
                        <ul>
                            <c:forEach var="exposition" items="${expositions}" varStatus="status">
                                <li>
                                    <a href="/general?getAction=searchExpositionById&id=${exposition.id}">${exposition.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                No showroom found matching your search criteria
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
