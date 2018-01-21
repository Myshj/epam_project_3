<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 08.01.2018
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../util/localized.jsp" %>
<html>
<head>
    <%--<%@include file="../util/bootstrap.jsp" %>--%>
    <%--<title><fmt:message key="exposition"/></title>--%>
    <%@include file="../util/header.jsp" %>
</head>
<body>
<div class="container">
    <%@ include file="../util/navbar.jsp" %>
    <c:choose>
        <c:when test="${not empty orders}">
            <h2><fmt:message key="myOrders"/></h2>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="exposition"/></th>
                    <th><fmt:message key="ticket"/></th>
                    <th><fmt:message key="price"/></th>
                    <th><fmt:message key="purchased"/></th>
                    <th><fmt:message key="status"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}" varStatus="status">
                    <c:set var="ticket" value="${order.ticket.value}"/>
                    <c:set var="ticketType" value="${ticket.type.value}"/>
                    <c:set var="exposition" value="${ticket.exposition.value}"/>
                    <tr>
                        <td>${status.index + 1}</td>
                        <td><a href="/common/search_exposition?id=${exposition.id}">${exposition.name}</a></td>
                        <td><a href="/common/search_ticket?id=${ticket.id}">${ticket.type.value.name}</a></td>
                        <td>${ticket.currency}, ${ticket.price}</td>
                        <td>${order.made.asLocalDate()}</td>
                        <td>${order.state.value.name}</td>
                        <c:if test="${order.state.value.cancellable.value}">
                            <td>
                                <a href="/user/cancel_order?id=${order.id}">
                                    <span class="glyphicon glyphicon-remove-circle"></span>
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                <fmt:message key="noExpositionsFound"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>