<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 07.01.2018
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <c:choose>
        <c:when test="${not empty showroom}">
            <h2>${showroom.name}</h2>
            <table class="table table-bordered">
                <tr>
                    <th>Name:</th>
                    <td>${showroom.name}</td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>${address}</td>
                </tr>
                <tr>
                    <th>Expositions:</th>
                    <td>
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"
                                        >Active <span class="badge">${activeExpositions.size()}</span></a>

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
                                        >Planned <span class="badge">${plannedExpositions.size()}</span></a>
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
                                        >Old <span class="badge">${oldExpositions.size()}</span></a>
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
                No showroom found matching your search criteria
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
