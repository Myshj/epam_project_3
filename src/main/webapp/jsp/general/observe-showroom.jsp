<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 07.01.2018
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../localized.jsp" %>
<html>
<head>
    <%@include file="../bootstrap.jsp" %>
    <title><fmt:message key="showroom"/></title>
</head>
<body>
<div class="container">
    <%@ include file="../navbar.jsp" %>
    <c:choose>
        <c:when test="${not empty showroom}">
            <h2>${showroom.name}</h2>
            <table class="table table-bordered">
                <tr>
                    <th><fmt:message key="name"/>:</th>
                    <td>${showroom.name}</td>
                </tr>
                <tr>
                    <th><fmt:message key="address"/>:</th>
                    <td>${address}</td>
                </tr>
                <tr>
                    <th><fmt:message key="expositions"/>:</th>
                    <td>
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"
                                        ><fmt:message key="active"/> <span
                                                class="badge">${activeExpositions.size()}</span></a>

                                    </h4>
                                </div>
                                <div id="collapse1" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <c:forEach var="exposition" items="${activeExpositions}" varStatus="status">
                                                <li class="list-group-item">
                                                    <a href="/common/search_exposition?id=${exposition.id}">${exposition.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2"
                                        ><fmt:message key="planned"/> <span
                                                class="badge">${plannedExpositions.size()}</span></a>
                                    </h4>
                                </div>
                                <div id="collapse2" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <c:forEach var="exposition" items="${plannedExpositions}"
                                                       varStatus="status">
                                                <li class="list-group-item">
                                                    <a href="/common/search_exposition?id=${exposition.id}">${exposition.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3"
                                        ><fmt:message key="old"/> <span
                                                class="badge">${oldExpositions.size()}</span></a>
                                    </h4>
                                </div>
                                <div id="collapse3" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="list-group">
                                            <c:forEach var="exposition" items="${oldExpositions}" varStatus="status">
                                                <li class="list-group-item">
                                                    <a href="/common/search_exposition?id=${exposition.id}">${exposition.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                <fmt:message key="noShowroomsFound"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
