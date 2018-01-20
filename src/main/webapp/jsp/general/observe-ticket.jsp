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
    <title><fmt:message key="ticket"/></title>
</head>
<body>
<div class="container">
    <%@ include file="../navbar.jsp" %>
    <c:choose>
        <c:when test="${not empty ticket}">
            <h2><fmt:message key="ticketInfo"/></h2>
            <table class="table table-bordered">
                <tr>
                    <th><fmt:message key="exposition"/>:</th>
                    <td>
                        <a href="/common/search_exposition?id=${ticket.exposition.value.id}"
                        >${ticket.exposition.value.name}</a>
                    </td>
                </tr>
                <tr>
                    <th><fmt:message key="address"/>:</th>
                    <td>${address}</td>
                </tr>
                <tr>
                    <th><fmt:message key="begins"/>:</th>
                    <td>${ticket.exposition.value.begins.asLocalDate()}</td>
                </tr>
                <tr>
                    <th><fmt:message key="ends"/></th>
                    <td>${ticket.exposition.value.ends.asLocalDate()}</td>
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
            <c:if test="${ticket.available.value}">
                <c:choose>
                    <c:when test="${not empty user}">
                        <form action="/user/purchase_ticket" role="form">
                            <input type="hidden" name="id" value="${ticket.id}">
                            <div class="form-group col-xs-4">
                                <button type="submit" class="btn btn-primary  btn-md"><fmt:message
                                        key="purchase"/></button>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-warning">
                            <fmt:message key="loginToPurchase"/>
                        </div>
                    </c:otherwise>
                </c:choose>

            </c:if>

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
