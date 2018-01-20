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
    <h3><a href="/admin/"><fmt:message key="back"/></a></h3>
    <h2><fmt:message key="table"/></h2>

    <%--<!--Entity List-->--%>
    <%--<c:if test="${not empty message}">--%>
    <%--<div class="alert alert-success">--%>
    <%--${message}--%>
    <%--</div>--%>
    <%--</c:if>--%>
    <input type="hidden" id="idOrder" name="id">
    <input type="hidden" id="postAction" name="postAction">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>id</th>
            <c:forEach var="field" items="${meta.fields.keySet()}">
                <th>${field}</th>
            </c:forEach>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entity" items="${entities}">
            <tr>
                <td>
                    <a href="/admin/${meta.names.singular}/show_update_form?id=${entity.id}"
                    >${entity.id}</a>
                </td>
                <c:forEach var="field" items="${meta.fields.keySet()}">
                    <td>
                        <c:choose>
                            <c:when test="${meta.fields[field].stringType == 'foreign'}">
                                ${entity[field].value.displayName}
                            </c:when>
                            <c:otherwise>
                                ${entity[field]}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:forEach>
                <td>
                    <a href="/admin/${meta.names.singular}/remove?id=${entity.id}">
                        <span class="glyphicon glyphicon-trash"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="/admin/${meta.names.singular}/show_create_form">
        <button type="submit" class="btn btn-primary  btn-md"><fmt:message key="create"/></button>
    </form>
</div>
</body>
</html>