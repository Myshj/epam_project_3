<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 28.12.2017
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h2>Expositions</h2>
    <!--Search Form -->
    <form action="/exposition" method="get" id="searchExpositionForm" role="form">
        <input type="hidden" id="getAction" name="getAction" value="searchByNameAndShowroomName">
        <div class="form-group col-xs-5">
            <input type="text"
                   name="name"
                   id="expositionName"
                   class="form-control"
                   placeholder="Type the Name of the exposition"
            />
            <input type="text"
                   name="showroomName"
                   id="showroomName"
                   class="form-control"
                   placeholder="Type the Name of the showroom"
            />
        </div>

        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br>
        <br>
    </form>

    <!--Cities List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/exposition" method="post" id="expositionForm" role="form">
        <input type="hidden" id="idExposition" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty expositions}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Place</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="exposition" items="${expositions}">
                        <c:set var="classSucess" value=""/>
                        <c:choose>
                            <c:when test="${id == exposition.id.value}">
                                <c:set var="classSuccess" value="info"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="classSuccess" value="none"/>
                            </c:otherwise>
                        </c:choose>
                        <tr class="${classSuccess}">
                            <td>
                                <a href="/exposition?id=${exposition.id}&getAction=searchById">${exposition.id}</a>
                            </td>
                            <td>${exposition.name}</td>
                            <td>
                                <a href="/showroom?id=${exposition.place.value.id}&getAction=searchById"
                                >${exposition.place.value.name}</a>
                            </td>

                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idExposition').value = '${exposition.id}';

                                           document.getElementById('expositionForm').submit();
                                           "
                            >
                                <span class="glyphicon glyphicon-trash"></span>
                            </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No expositions found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/exposition">
        <input type="hidden" name="getAction" value="new">
        <br>
        <button type="submit" class="btn btn-primary  btn-md">New exposition</button>
    </form>
</div>
</body>
</html>