<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 06.01.2018
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../util/localized.jsp" %>
<html lang="${language}">
<head>
    <%--<%@ include file="../util/bootstrap.jsp" %>--%>
    <%--<title><fmt:message key="showrooms"/></title>--%>
    <%@include file="../util/header.jsp" %>
    <style>
        th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <%@include file="../util/navbar.jsp" %>
    <%--<h3><a href="/settings/"><fmt:message key="settings"/></a></h3>--%>
    <h2><fmt:message key="showrooms"/></h2>
    <c:choose>
        <c:when test="${not empty showrooms}">
            <table class="table table-striped table-bordered">
                <thead style="text-align: center">

                <tr>
                    <th rowspan="2">#</th>
                    <th rowspan="2"><fmt:message key="name"/></th>
                    <th rowspan="2"><fmt:message key="country"/></th>
                    <th rowspan="2"><fmt:message key="city"/></th>
                    <th rowspan="2"><fmt:message key="street"/></th>
                    <th rowspan="2"><fmt:message key="building"/></th>
                    <th colspan="3"><fmt:message key="exposition"/></th>
                </tr>
                <tr>
                    <th><fmt:message key="active"/></th>
                    <th><fmt:message key="old"/></th>
                    <th><fmt:message key="planned"/></th>
                </tr>
                </thead>
                <c:forEach var="showroom" items="${showrooms}" varStatus="status">
                    <c:set var="classSucess" value=""/>
                    <c:choose>
                        <c:when test="${id == showroom.id.value}">
                            <c:set var="classSuccess" value="info"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="classSuccess" value="none"/>
                        </c:otherwise>
                    </c:choose>
                    <tr class="${classSuccess}">
                        <td>${status.index + 1}</td>
                        <td>
                            <a href="/common/search_showroom?id=${showroom.id}">${showroom.name}</a>
                        </td>
                        <td>${showroom.building.value.street.value.city.value.country.value.name}</td>
                        <td>${showroom.building.value.street.value.city.value.name}</td>
                        <td>${showroom.building.value.street.value.name}</td>
                        <td>${showroom.building.value.name}</td>
                        <td>${expositionTypes[showroom].active}</td>
                        <td>${expositionTypes[showroom].old}</td>
                        <td>${expositionTypes[showroom].planned}</td>
                    </tr>
                </c:forEach>
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
