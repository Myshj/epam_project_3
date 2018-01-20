<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 19.01.2018
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../localized.jsp" %>
<html lang="${language}">
<head>
    <%@include file="../bootstrap.jsp" %>
    <title><fmt:message key="settings"/></title>
</head>
<body>
<div class="container">
    <%@ include file="../navbar.jsp" %>
    <form action="/settings/confirm" role="form" method="post">
        <div class="form-group col-xs-4">
            <label for="languageControl"
                   class="control-label col-xs-4"
            ><fmt:message key="language"/></label>
            <select class="form-control" id="languageControl" name="language">
                <option value="en"
                        <c:choose>
                            <c:when test="${language == 'en'}">selected</c:when>
                            <c:when test="${language == 'en_EN'}">selected</c:when>
                        </c:choose>
                >English
                </option>
                <option value="ru"
                        <c:choose>
                            <c:when test="${language == 'ru'}">selected</c:when>
                            <c:when test="${language == 'ru_RU'}">selected</c:when>
                        </c:choose>
                >Русский
                </option>
            </select>
            <input class="btn btn-primary  btn-md" type="submit" value="<fmt:message key="submit"/>">
        </div>
    </form>
</div>
</body>
</html>

