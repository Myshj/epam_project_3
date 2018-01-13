<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 08.01.2018
  Time: 10:20
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
        <c:when test="${not empty exposition}">
            <h2>${exposition.name}</h2>
            <table class="table table-bordered">
                <tr>
                    <th>Name:</th>
                    <td>${exposition.name}</td>
                </tr>
                <tr>
                    <th>Showroom:</th>
                    <td>
                        <a href="/common/search_showroom?id=${exposition.place.value.id}"
                        >${exposition.place.value.name}</a>
                    </td>
                </tr>
                <tr>
                    <th>Begins:</th>
                    <td>${exposition.begins.asLocalDate()}</td>
                </tr>
                <tr>
                    <th>Ends:</th>
                    <td>${exposition.ends.asLocalDate()}</td>
                </tr>

                <tr>
                    <th>Tickets:</th>
                    <td>
                        <ul>
                            <c:forEach var="ticket" items="${tickets}" varStatus="status">
                                <li>
                                    <a href="/common/search_ticket?id=${ticket.id}"
                                    >${ticket.type.value.name}, ${ticket.currency}, ${ticket.price}</a>
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
                No exposition found matching your search criteria
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>