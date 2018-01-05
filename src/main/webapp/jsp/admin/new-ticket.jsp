<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 29.12.2017
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/bootstrap.min.css"/>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form action="/ticket" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="createNew"/>
        </c:if>
        <input type="hidden" id="action" name="postAction" value="${action}"/>
        <input type="hidden" id="idTicket" name="id" value="${ticket.id}"/>
        <h2>Ticket</h2>
        <div class="form-group col-xs-4">
            <label for="exposition"
                   class="control-label col-xs-4"
            >Exposition:</label>
            <select name="exposition_id"
                    id="exposition"
                    class="form-control"
                    value="${exposition.name}"
                    required="true"
            >
                <c:forEach var="exposition" items="${expositions}">
                    <option value="${exposition.id}"
                            <c:if test="${exposition.id.value == ticket.exposition.value.id.value}">selected</c:if>
                    >${exposition.name}</option>
                </c:forEach>
            </select>
            <label for="type"
                   class="control-label col-xs-4"
            >Type:</label>
            <select id="type"
                    name="type_id"
                    class="form-control"
            >
                <c:forEach var="type" items="${types}">
                    <option value="${type.id}"
                            <c:if test="${type.id.value == ticket.type.value.id.value}">selected</c:if>
                    >${type.name}</option>
                </c:forEach>
            </select>

            <label for="price"
                   class="control-label col-xs-4"
            >Price:</label>

            <input type="number" name="price" id="price" class="form-control"
                   value="${ticket.price}" required="true"
            />

            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>
