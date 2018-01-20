<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 08.01.2018
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../localized.jsp" %>
<html>
<head>
    <%@include file="../bootstrap.jsp" %>
    <title><fmt:message key="exposition"/></title>
</head>
<body>
<div class="container">
    <%@ include file="../navbar.jsp" %>
    <c:choose>
        <c:when test="${not empty exposition}">
            <h2>${exposition.name}</h2>
            <table class="table table-bordered">
                <tr>
                    <th><fmt:message key="name"/>:</th>
                    <td>${exposition.name}</td>
                </tr>
                <tr>
                    <th><fmt:message key="showroom"/>:</th>
                    <td>
                        <a href="/common/search_showroom?id=${exposition.place.value.id}"
                        >${exposition.place.value.name}</a>
                    </td>
                </tr>
                <tr>
                    <th><fmt:message key="begins"/>:</th>
                    <td>${exposition.begins.asLocalDate()}</td>
                </tr>
                <tr>
                    <th><fmt:message key="ends"/>:</th>
                    <td>${exposition.ends.asLocalDate()}</td>
                </tr>

                <tr>
                    <th><fmt:message key="tickets"/>:</th>
                    <td>
                        <ul class="list-group">
                            <c:forEach var="ticket" items="${tickets}" varStatus="status">
                                <li class="list-group-item">
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
                <fmt:message key="noExpositionsFound"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>