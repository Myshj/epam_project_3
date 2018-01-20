<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 05.01.2018
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../localized.jsp" %>
<html>
<head>
    <title><fmt:message key="pageNotFound"/></title>
    <%@ include file="../bootstrap.jsp" %>
</head>
<body>
<div class="container">
    <%@ include file="../navbar.jsp" %>
    <h1><fmt:message key="pageNotExist"/></h1>
    <h2><fmt:message key="pleaseTryAgain"/></h2>
</div>
</body>
</html>
