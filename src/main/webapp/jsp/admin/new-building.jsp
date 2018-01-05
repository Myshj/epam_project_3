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
    <form action="/building" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="createNew"/>
        </c:if>
        <input type="hidden" id="action" name="postAction" value="${action}"/>
        <input type="hidden" id="idBuilding" name="id" value="${building.id}"/>
        <h2>Building</h2>
        <div class="form-group col-xs-4">
            <label for="name"
                   class="control-label col-xs-4"
            >Name:</label>
            <input type="text"
                   name="name"
                   id="name"
                   class="form-control"
                   value="${building.name}"
                   required="true"
            />
            <label for="street"
                   class="control-label col-xs-4"
            >Street:</label>
            <select id="street"
                    name="street_id"
                    class="form-control"
            >
                <c:forEach var="street" items="${streets}">
                    <option value="${street.id}"
                            <c:if test="${street.id.value == building.street.value.id.value}">selected</c:if>
                    >${street.name}</option>
                </c:forEach>
            </select>
            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>
