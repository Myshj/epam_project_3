<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 08.01.2018
  Time: 13:11
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
            <h2>Purchase ticket</h2>
            <table class="table table-bordered">
                <tr>
                    <th>Exposition:</th>
                    <td>
                        <a href="/common/search_exposition?id=${ticket.exposition.value.id}"
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

            <form action="/common/confirm_purchase" method="post" role="form">

                <input type="hidden" name="id" value="${ticket.id}">
                <div class="form-group col-xs-4">
                    <label for="userEmail"
                           class="control-label col-xs-4"
                    >Your email*:</label>
                    <input type="email"
                           id="userEmail"
                           name="email"
                           placeholder="example@example.com"
                           class="form-control"
                           required
                    >
                    <label for="userName"
                           class="control-label col-xs-4"
                    >Your name:</label>
                    <input type="text"
                           id="userName"
                           name="name"
                           class="form-control"
                    >
                    <br>
                    <button type="submit" class="btn btn-primary  btn-md">Confirm</button>
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