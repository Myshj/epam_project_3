<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 29.12.2017
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/bootstrap.min.css"/>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form action="/admin/city" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="createNew"/>
        </c:if>
        <input type="hidden" id="action" name="postAction" value="${action}"/>
        <input type="hidden" id="idCity" name="id" value="${city.id}"/>
        <h2>City</h2>
        <div class="form-group col-xs-4">
            <label for="name"
                   class="control-label col-xs-4"
            >Name:</label>
            <input type="text"
                   name="name"
                   id="name"
                   class="form-control"
                   value="${city.name}"
                   required="true"
            />
            <label for="country"
                   class="control-label col-xs-4"
            >Country:</label>
            <select id="country"
                    name="country_id"
                    class="form-control"
            >
                <c:forEach var="country" items="${countries}">
                    <option value="${country.id}"
                            <c:if test="${country.id.value == city.country.value.id.value}">selected</c:if>
                    >${country.name}</option>
                </c:forEach>
            </select>
            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>
