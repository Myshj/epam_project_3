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
            <c:if test="${user != null}">
                <li><a href="/user/orders"><fmt:message key="myOrders"/></a></li>
                <c:if test="${user.role.value.hasAccessToAdminSite.value}">
                    <li><a href="/admin/"><fmt:message key="adminZone"/></a></li>
                </c:if>
            </c:if>
            <li><a href="/settings/"><fmt:message key="settings"/></a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <c:if test="${user == null}">
                <li><a href="/registration/show_registration_form"><span class="glyphicon glyphicon-user"></span> Sign
                    Up</a></li>
            </c:if>

            <c:choose>
                <c:when test="${user == null}">
                    <li><a href="/login/"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/login/logout/"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
