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
    <form action="/admin/street" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="createNew"/>
        </c:if>
        <input type="hidden" id="action" name="postAction" value="${action}"/>
        <input type="hidden" id="idStreet" name="id" value="${street.id}"/>
        <h2>Street</h2>
        <div class="form-group col-xs-4">
            <label for="name"
                   class="control-label col-xs-4"
            >Name:</label>
            <input type="text"
                   name="name"
                   id="name"
                   class="form-control"
                   value="${street.name}"
                   required="true"
            />
            <label for="city"
                   class="control-label col-xs-4"
            >City:</label>
            <select id="city"
                    name="city_id"
                    class="form-control"
            >
                <c:forEach var="city" items="${cities}">
                    <option value="${city.id}"
                            <c:if test="${city.id.value == street.city.value.id.value}">selected</c:if>
                    >${city.name}</option>
                </c:forEach>
            </select>
            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>
