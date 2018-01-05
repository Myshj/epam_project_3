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
    <form action="/admin/order" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="createNew"/>
        </c:if>
        <input type="hidden" id="action" name="postAction" value="${action}"/>
        <input type="hidden" id="idOrder" name="id" value="${order.id}"/>
        <h2>Order</h2>
        <div class="form-group col-xs-4">

            <label for="made"
                   class="control-label col-xs-4"
            >Made:</label>

            <input type="date" name="made" id="made" class="form-control"
                   value="${order.made.asLocalDate()}" required="true"
            />

            <label for="ticket"
                   class="control-label col-xs-4"
            >Ticket:</label>
            <select name="ticket_id"
                    id="ticket"
                    class="form-control"
                    value="${order.ticket}"
                    required="true"
            >
                <c:forEach var="ticket" items="${tickets}">
                    <option value="${ticket.id}"
                            <c:if test="${ticket.id.value == order.ticket.value.id.value}">selected</c:if>
                    >${ticket.exposition.value.name}, ${ticket.type.value.name}</option>
                </c:forEach>
            </select>
            <label for="user"
                   class="control-label col-xs-4"
            >User:</label>
            <select id="user"
                    name="user_id"
                    class="form-control"
            >
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}"
                            <c:if test="${user.id.value == order.user.value.id.value}">selected</c:if>
                    >${user.email}</option>
                </c:forEach>
            </select>

            <label for="state"
                   class="control-label col-xs-4"
            >State:</label>
            <select id="state"
                    name="state_id"
                    class="form-control"
            >
                <c:forEach var="orderState" items="${orderStates}">
                    <option value="${orderState.id}"
                            <c:if test="${orderState.id.value == order.state.value.id.value}">selected</c:if>
                    >${orderState.name}</option>
                </c:forEach>
            </select>

            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>
