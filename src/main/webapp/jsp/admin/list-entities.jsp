<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 28.12.2017
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../util/localized.jsp" %>
<html>
<head>
    <%--<%@include file="../util/bootstrap.jsp" %>--%>
    <%--<title><fmt:message key="adminZone"/></title>--%>
    <%@include file="../util/header.jsp" %>
</head>

<body>
<div class="container">
    <%@ include file="../util/navbar.jsp" %>
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
                    <a href="/admin/${meta.names.singular}/show_update_form?id=${entity.id}">
                        <span class="glyphicon glyphicon-edit"></span>
                    </a>
                    <a href="/admin/${meta.names.singular}/remove?id=${entity.id}">
                        <span class="glyphicon glyphicon-trash"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <ul class="pagination">
        <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link"
                   href="/admin/${meta.names.singular}/show_all?page=${currentPage - 1}&pageSize=${pageSize}">Previous</a>
            </li>
        </c:if>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page--%>
        <c:forEach begin="1" end="${countOfPages}" var="i">
            <li class="page-item <c:if test="${currentPage == i}">active</c:if>">
                <a class="page-link"
                   href="/admin/${meta.names.singular}/show_all?page=${i}&pageSize=${pageSize}"
                >${i}</a>
            </li>
        </c:forEach>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt countOfPages}">
            <li><a href="/admin/${meta.names.singular}/show_all?page=${currentPage + 1}&pageSize=${pageSize}">Next</a>
            </li>
        </c:if>
        <li class="page-item dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Items per page
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="/admin/${meta.names.singular}/show_all?pageSize=1">1</a></li>
                <li><a href="/admin/${meta.names.singular}/show_all?pageSize=5">5</a></li>
                <li><a href="/admin/${meta.names.singular}/show_all?pageSize=10">10</a></li>
                <li><a href="/admin/${meta.names.singular}/show_all?pageSize=20">20</a></li>
            </ul>
            <%--</div>--%>
        </li>
    </ul>

    <form action="/admin/${meta.names.singular}/show_create_form">
        <button type="submit" class="btn btn-primary  btn-md"><fmt:message key="create"/></button>
    </form>
</div>
</body>
</html>