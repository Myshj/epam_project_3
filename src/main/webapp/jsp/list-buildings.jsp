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
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h2>buildings</h2>
    <!--Search Form -->
    <form action="/building" method="get" id="searchBuildingForm" role="form">
        <input type="hidden" id="getAction" name="getAction" value="searchByNameAndStreetNameAndCityNameAndCountryName">
        <div class="form-group col-xs-5">
            <input type="text"
                   name="name"
                   id="buildingName"
                   class="form-control"
                   placeholder="Type the Name of the building"
            />
            <input type="text"
                   name="streetName"
                   id="streetName"
                   class="form-control"
                   placeholder="Type the Name of the street"
            />
            <input type="text"
                   name="cityName"
                   id="cityName"
                   class="form-control"
                   placeholder="Type the Name of the city"
            />
            <input type="text"
                   name="countryName"
                   id="countryName"
                   class="form-control"
                   placeholder="Type the Name of the country"
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
    <form action="/building" method="post" id="buildingForm" role="form">
        <input type="hidden" id="idBuilding" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty buildings}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Street</th>
                        <th>City</th>
                        <th>Country</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="building" items="${buildings}">
                        <c:set var="classSucess" value=""/>
                        <c:choose>
                            <c:when test="${id == building.id.value}">
                                <c:set var="classSuccess" value="info"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="classSuccess" value="none"/>
                            </c:otherwise>
                        </c:choose>
                        <tr class="${classSuccess}">
                            <td>
                                <a href="/building?id=${building.id}&getAction=searchById">${building.id}</a>
                            </td>
                            <td>${building.name}</td>
                            <td>
                                <a href="/street?id=${building.street.value.id}&getAction=searchById"
                                >${building.street.value.name}</a>
                            </td>
                            <td>
                                <a href="/city?id=${building.street.value.city.value.id}&getAction=searchById"
                                >${building.street.value.city.value.name}</a>
                            </td>
                            <td>
                                <a href="/country?id=${building.street.value.city.value.country.value.id}&getAction=searchById"
                                >${building.street.value.city.value.country.value.name}</a>
                            </td>
                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idBuilding').value = '${building.id}';

                                           document.getElementById('buildingForm').submit();
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
                    No buildings found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/building">
        <input type="hidden" name="getAction" value="new">
        <br>
        <button type="submit" class="btn btn-primary  btn-md">New building</button>
    </form>
</div>
</body>
</html>