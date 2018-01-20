<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 20.01.2018
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="localized.jsp" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/"><fmt:message key="webSiteName"/></a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/"><fmt:message key="home"/></a></li>
            <li><a href="/settings/"><fmt:message key="settings"/></a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>
