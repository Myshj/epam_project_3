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
    <form action="/exposition" method="post" role="form" data-toggle="validator">
        <c:if test="${empty action}">
            <c:set var="action" value="createNew"/>
        </c:if>
        <input type="hidden" id="action" name="postAction" value="${action}"/>
        <input type="hidden" id="idExposition" name="id" value="${exposition.id}"/>
        <h2>Exposition</h2>
        <div class="form-group col-xs-4">
            <label for="name"
                   class="control-label col-xs-4"
            >Name:</label>
            <input type="text"
                   name="name"
                   id="name"
                   class="form-control"
                   value="${exposition.name}"
                   required="true"
            />
            <label for="showroom"
                   class="control-label col-xs-4"
            >Showroom:</label>
            <select id="showroom"
                    name="showroom_id"
                    class="form-control"
            >
                <c:forEach var="showroom" items="${showrooms}">
                    <option value="${showroom.id}"
                            <c:if test="${showroom.id.value == exposition.place.value.id.value}">selected</c:if>
                    >${showroom.name}</option>
                </c:forEach>
            </select>

            <label for="begins"
                   class="control-label col-xs-4"
            >Begins:</label>

            <input type="date" name="begins" id="begins" class="form-control"
                   value="${exposition.begins.asLocalDate()}" required="true"
            />

            <label for="ends"
                   class="control-label col-xs-4"
            >Ends:</label>

            <input type="date" name="ends" id="ends" class="form-control"
                   value="${exposition.ends.asLocalDate()}" required="true"
            />
            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>
