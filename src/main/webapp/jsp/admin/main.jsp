<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 28.12.2017
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h2>Entities</h2>

    <%--<!--Entity List-->--%>
    <%--<c:if test="${not empty message}">--%>
    <%--<div class="alert alert-success">--%>
    <%--${message}--%>
    <%--</div>--%>
    <%--</c:if>--%>
    <input type="hidden" id="idOrder" name="id">
    <input type="hidden" id="postAction" name="postAction">
    <ul class="list-group">
        <c:forEach var="info" items="${metaInfos}">
            <li class="list-group-item">
                <a href="/admin/${info.names.singular}/show_all">${info.names.plural}</a>
            </li>
        </c:forEach>

    </ul>
</div>
</body>
</html>