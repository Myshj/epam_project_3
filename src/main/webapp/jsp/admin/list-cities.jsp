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
    <h2>Cities</h2>
    <!--Search Form -->
    <form action="/city" method="get" id="searchCityForm" role="form">
        <input type="hidden" id="getAction" name="getAction" value="searchByNameAndCountryName">
        <div class="form-group col-xs-5">
            <input type="text"
                   name="name"
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
    <form action="/city" method="post" id="cityForm" role="form">
        <input type="hidden" id="idCity" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty cities}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Country</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="city" items="${cities}">
                        <c:set var="classSucess" value=""/>
                        <c:choose>
                            <c:when test="${id == city.id.value}">
                                <c:set var="classSuccess" value="info"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="classSuccess" value="none"/>
                            </c:otherwise>
                        </c:choose>
                        <tr class="${classSuccess}">
                            <td>
                                <a href="/city?id=${city.id}&getAction=searchById">${city.id}</a>
                            </td>
                            <td>${city.name}</td>
                            <td>
                                <a href="/country?id=${city.country.value.id}&getAction=searchById"
                                >${city.country.value.name}</a>
                            </td>
                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idCity').value = '${city.id}';

                                           document.getElementById('cityForm').submit();
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
                    No cities found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/city">
        <input type="hidden" name="getAction" value="new">
        <br>
        <button type="submit" class="btn btn-primary  btn-md">New city</button>
    </form>
</div>
</body>
</html>