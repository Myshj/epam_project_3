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
    <h2>Orders</h2>
    <!--Search Form -->
    <%--<form action="/order" method="get" id="searchOrderForm" role="form">--%>
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

    <!--Orders List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/order" method="post" id="orderForm" role="form">
        <input type="hidden" id="idOrder" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty orders}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Made</th>
                        <th>User</th>
                        <th>State</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="order" items="${orders}">
                        <c:set var="classSucess" value=""/>
                        <c:choose>
                            <c:when test="${id == order.id.value}">
                                <c:set var="classSuccess" value="info"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="classSuccess" value="none"/>
                            </c:otherwise>
                        </c:choose>
                        <tr class="${classSuccess}">
                            <td>
                                <a href="/order?id=${order.id}&getAction=searchById">${order.id}</a>
                            </td>
                            <td>${order.made}</td>
                            <td>
                                <a href="/user?id=${order.user.value.id}&getAction=searchById"
                                >${order.user.value.email}</a>
                            </td>
                            <td>
                                <a href="/order-state?id=${order.state.value.id}&getAction=searchById"
                                >${order.state.value.name}</a>
                            </td>


                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idOrder').value = '${order.id}';

                                           document.getElementById('orderForm').submit();
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
                    No orders found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/order">
        <input type="hidden" name="getAction" value="new">
        <br>
        <button type="submit" class="btn btn-primary  btn-md">New order</button>
    </form>
</div>
</body>
</html>