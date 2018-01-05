<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 28.12.2017
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <script src="../../js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h2>Users</h2>
    <!--Search Form -->
    <%--<form action="/user" method="get" id="searchUserForm" role="form">--%>
    <%--<input type="hidden" id="getAction" name="getAction" value="searchByExpositionName">--%>
    <%--<div class="form-group col-xs-5">--%>
    <%--<input type="text"--%>
    <%--name="expositionName"--%>
    <%--id="expositionName"--%>
    <%--class="form-control"--%>
    <%--placeholder="Type the Name of the exposition"--%>
    <%--/>--%>
    <%--</div>--%>

    <%--<button type="submit" class="btn btn-info">--%>
    <%--<span class="glyphicon glyphicon-search"></span> Search--%>
    <%--</button>--%>
    <%--<br>--%>
    <%--<br>--%>
    <%--</form>--%>

    <!--Users List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/user" method="post" id="userForm" role="form">
        <input type="hidden" id="idUser" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty users}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Email</th>
                        <th>Role</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="user" items="${users}">
                        <c:set var="classSucess" value=""/>
                        <c:choose>
                            <c:when test="${id == user.id.value}">
                                <c:set var="classSuccess" value="info"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="classSuccess" value="none"/>
                            </c:otherwise>
                        </c:choose>
                        <tr class="${classSuccess}">
                            <td>
                                <a href="/user?id=${user.id}&getAction=searchById">${user.id}</a>
                            </td>
                            <td>${user.email}</td>
                            <td>
                                <a href="/user-role?id=${user.role.value.id}&getAction=searchById"
                                >${user.role.value.name}</a>
                            </td>

                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idUser').value = '${user.id}';

                                           document.getElementById('userForm').submit();
                                           "
                            >
                                <span class="glyphicon glyphicon-trash"></span>
                            </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No users found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/user">
        <input type="hidden" name="getAction" value="new">
        <br>
        <button type="submit" class="btn btn-primary  btn-md">New user</button>
    </form>
</div>
</body>
</html>