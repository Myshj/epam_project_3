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
    <h2>Countries</h2>
    <!--Search Form -->
    <form action="/country" method="get" id="searchCountryForm" role="form">
        <input type="hidden" id="getAction" name="getAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text"
                   name="name"
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

    <!--Countries List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/country" method="post" id="countryForm" role="form" >
        <input type="hidden" id="idCountry" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty countries}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="country" items="${countries}">
                        <c:set var="classSucess" value=""/>
                        <c:choose>
                            <c:when test="${id == country.id.value}">
                                <c:set var="classSuccess" value="info"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="classSuccess" value="none"/>
                            </c:otherwise>
                        </c:choose>
                        <tr class="${classSuccess}">
                            <td>
                                <a href="/country?id=${country.id}&getAction=searchById">${country.id}</a>
                            </td>
                            <td>${country.name}</td>
                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idCountry').value = '${country.id}';

                                           document.getElementById('countryForm').submit();
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
                    No countries found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action ="/country">
        <input type="hidden" name="getAction" value="new"/>
        <button type="submit" class="btn btn-primary  btn-md">New country</button>
    </form>
</div>
</body>
</html>