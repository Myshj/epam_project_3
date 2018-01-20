<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 28.12.2017
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../localized.jsp" %>
<html>
<head>
    <%@include file="../bootstrap.jsp" %>
    <title><fmt:message key="adminZone"/></title>
</head>

<body>
<div class="container">
    <%@ include file="../navbar.jsp" %>
    <h2><fmt:message key="entities"/></h2>

    <%--<!--Entity List-->--%>
    <%--<c:if test="${not empty message}">--%>
    <%--<div class="alert alert-success">--%>
    <%--${message}--%>
    <%--</div>--%>
    <%--</c:if>--%>
    <input type="hidden" id="idOrder" name="id">
    <input type="hidden" id="postAction" name="postAction">
    <ul class="list-group">
        <c:forEach var="info" items="${metaInfos}">
            <li class="list-group-item">
                <a href="/admin/${info.names.singular}/show_all">${info.names.plural}</a>
            </li>
        </c:forEach>

    </ul>
</div>
</body>
</html>