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
    <h2>Showrooms</h2>
    <!--Search Form -->
    <form action="/admin/showroom" method="get" id="searchShowroomForm" role="form">
        <input type="hidden" id="getAction" name="getAction" value="searchByNameAndCityName">
        <div class="form-group col-xs-5">
            <input type="text"
                   name="name"
                   id="showroomName"
                   class="form-control"
                   placeholder="Type the Name of the showroom"
            />
            <input type="text"
                   name="cityName"
                   id="cityName"
                   class="form-control"
                   placeholder="Type the Name of the city"
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
    <form action="/admin/showroom" method="post" id="showroomForm" role="form">
        <input type="hidden" id="idShowroom" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty showrooms}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Building</th>
                        <th>Street</th>
                        <th>City</th>
                        <th>Country</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="showroom" items="${showrooms}">
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
                            <td>
                                <a href="/admin/showroom?id=${showroom.id}&getAction=searchById">${showroom.id}</a>
                            </td>
                            <td>${showroom.name}</td>
                            <td>
                                <a href="/admin/building?id=${showroom.building.value.id}&getAction=searchById"
                                >${showroom.building.value.name}</a>
                            </td>
                            <td>
                                <a href="/admin/street?id=${showroom.building.value.street.value.id}&getAction=searchById"
                                >${showroom.building.value.street.value.name}</a>
                            </td>
                            <td>
                                <a href="/admin/city?id=${showroom.building.value.street.value.city.value.id}&getAction=searchById"
                                >${showroom.building.value.street.value.city.value.name}</a>
                            </td>
                            <td>
                                <a href="/admin/country?id=${showroom.building.value.street.value.city.value.country.value.id}&getAction=searchById"
                                >${showroom.building.value.street.value.city.value.country.value.name}</a>
                            </td>
                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idShowroom').value = '${showroom.id}';

                                           document.getElementById('showroomForm').submit();
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
                    No showrooms found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/admin/showroom">
        <input type="hidden" name="getAction" value="new">
        <br>
        <button type="submit" class="btn btn-primary  btn-md">New showroom</button>
    </form>
</div>
</body>
</html>