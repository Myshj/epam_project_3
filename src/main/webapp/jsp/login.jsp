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
    <form action="/login/confirm" method="post" role="form" data-toggle="validator">
        <input type="hidden" id="idUser" name="id" value="${user.id}"/>
        <h2>Login</h2>
        <div class="form-group col-xs-4">
            <label for="email"
                   class="control-label col-xs-4"
            >Name:</label>
            <input type="email"
                   name="email"
                   id="email"
                   class="form-control"
                   value="${user.email}"
                   required="true"
            />

            <label for="password"
                   class="control-label col-xs-4"
            >Password:</label>
            <input type="password"
                   name="password"
                   id="password"
                   class="form-control"
                   value="${user.password}"
                   required="true"
            />
            <br>
            <button type="submit" class="btn btn-primary  btn-md">Login</button>
        </div>
    </form>
</div>
</body>
</html>
