<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 08.01.2018
  Time: 12:40
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
        <c:when test="${not empty ticket}">
            <h2>${ticket.exposition.value.name}</h2>
            <table class="table table-bordered">
                <tr>
                    <th>Exposition:</th>
                    <td>
                        <a href="/general?getAction=searchExpositionById&id=${ticket.exposition.value.id}"
                        >${ticket.exposition.value.name}</a>
                    </td>
                </tr>
                <tr>
                    <th>Type:</th>
                    <td>${ticket.type.value.name}</td>
                </tr>
                <tr>
                    <th>Price:</th>
                    <td>${ticket.currency}, ${ticket.price}</td>
                </tr>
            </table>
            <form action="/general?getAction=purchaseTicketById&id=${ticket.id}" role="form">
                <div class="form-group col-xs-4">
                    <button type="submit" class="btn btn-primary  btn-md">Purchase</button>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                No ticket found matching your search criteria
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
