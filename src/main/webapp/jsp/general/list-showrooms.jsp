<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 06.01.2018
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
    <style>
        th {
            text-align: center;
        }
    </style>
</head>

<body>
<div class="container">
    <h2>Showrooms</h2>
    <c:choose>
        <c:when test="${not empty showrooms}">
            <table class="table table-striped table-bordered">
                <thead style="text-align: center">

                <tr>
                    <th rowspan="2">#</th>
                    <th rowspan="2">Name</th>
                    <th rowspan="2">Country</th>
                    <th rowspan="2">City</th>
                    <th rowspan="2">Street</th>
                    <th rowspan="2">Building</th>
                    <th colspan="3">Expositions</th>
                </tr>
                <tr>
                    <th>Active</th>
                    <th>Old</th>
                    <th>Planned</th>
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
                        <td>${countOfActiveExpositions}</td>
                        <td>${countOfOldExpositions}</td>
                        <td>${countOfPlannedExpositions}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                No showrooms found matching your search criteria
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
