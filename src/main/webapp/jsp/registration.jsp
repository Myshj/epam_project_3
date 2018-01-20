<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 20.01.2018
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="localized.jsp" %>
<html>
<head>
    <%@include file="bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <%@ include file="navbar.jsp" %>

    <form action="/registration/register" method="post" role="form" data-toggle="validator">
        <h2>Registration:</h2>
        <div class="form-group col-xs-4">
            <label for="email"
                   class="control-label col-xs-4"
            >Email:</label>
            <input type="email"
                   name="email"
                   id="email"
                   class="form-control"
                   required="true"
                   placeholder="example@example.com"
            />

            <label for="password"
                   class="control-label col-xs-4"
            >Password:</label>
            <input type="password"
                   name="password"
                   id="password"
                   class="form-control"
                   required="true"
                   onchange="{
                       if(this.checkValidity())
                           form.confirmPassword.pattern = this.value;
                   }"

            />

            <label for="confirmPassword"
                   class="control-label col-xs-4"
            >Confirm password:</label>
            <input type="password"
                   name="confirmPassword"
                   id="confirmPassword"
                   class="form-control"
                   value="${user.password}"
                   required="true"
                   onchange="{
                       this.setCustomValidity(
                           this.validity.patternMismatch ? 'Please enter the same password as above' : ''
                           );
                   }"
            />

            <br>
            <button type="submit" class="btn btn-primary  btn-md">Sign Up</button>
        </div>
    </form>

</div>
</body>
</html>
