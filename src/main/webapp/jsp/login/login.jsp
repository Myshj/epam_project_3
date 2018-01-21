<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 29.12.2017
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../util/localized.jsp" %>
<html>
<head>
    <%--<%@include file="../util/bootstrap.jsp" %>--%>
    <%@include file="../util/header.jsp" %>
</head>
<body>
<div class="container">
    <%@ include file="../util/navbar.jsp" %>
    <form action="/login/confirm" method="post" role="form" data-toggle="validator">
        <input type="hidden" id="idUser" name="id" value="${user.id}"/>
        <h2><fmt:message key="login"/></h2>
        <div class="form-group col-xs-4">
            <label for="email"
                   class="control-label col-xs-4"
            ><fmt:message key="email"/>:</label>
            <input type="email"
                   name="email"
                   id="email"
                   class="form-control"
                   value="${user.email}"
                   required="true"
            />

            <label for="password"
                   class="control-label col-xs-4"
            ><fmt:message key="password"/>:</label>
            <input type="password"
                   name="password"
                   id="password"
                   class="form-control"
                   value="${user.password}"
                   required="true"
            />
            <br>
            <button type="submit" class="btn btn-primary  btn-md"><fmt:message key="login"/></button>
        </div>
    </form>
</div>
</body>
</html>
