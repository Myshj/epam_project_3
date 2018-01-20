<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 08.01.2018
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../localized.jsp" %>
<html>
<head>
    <%@include file="../bootstrap.jsp" %>
    <title><fmt:message key="purchaseTicket"/></title>
</head>
<body>
<div class="container">
    <%@ include file="../navbar.jsp" %>
    <c:choose>
        <c:when test="${not empty ticket}">
            <h2><fmt:message key="purchaseTicket"/></h2>
            <table class="table table-bordered">
                <tr>
                    <th><fmt:message key="exposition"/>:</th>
                    <td>
                        <a href="/common/search_exposition?id=${ticket.exposition.value.id}"
                        >${ticket.exposition.value.name}</a>
                    </td>
                </tr>
                <tr>
                    <th><fmt:message key="type"/>:</th>
                    <td>${ticket.type.value.name}</td>
                </tr>
                <tr>
                    <th><fmt:message key="price"/>:</th>
                    <td>${ticket.currency}, ${ticket.price}</td>
                </tr>
            </table>

            <form action="/common/confirm_purchase" method="post" role="form">

                <input type="hidden" name="id" value="${ticket.id}">
                <div class="form-group col-xs-4">
                    <label for="userEmail"
                           class="control-label col-xs-4"
                    ><fmt:message key="yourEmail"/>*:</label>
                    <input type="email"
                           id="userEmail"
                           name="email"
                           placeholder="example@example.com"
                           class="form-control"
                           required
                    >
                    <label for="userName"
                           class="control-label col-xs-4"
                    ><fmt:message key="yourName"/>:</label>
                    <input type="text"
                           id="userName"
                           name="name"
                           class="form-control"
                    >
                    <br>
                    <button type="submit" class="btn btn-primary  btn-md"><fmt:message key="confirm"/></button>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                <fmt:message key="noTicketsFound"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>