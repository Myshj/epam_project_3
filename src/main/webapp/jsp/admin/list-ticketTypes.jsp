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
    <h2>Ticket types</h2>
    <!--Search Form -->
    <form action="/admin/ticket-type" method="get" id="searchTicketTypeForm" role="form">
        <input type="hidden" id="getAction" name="getAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text"
                   name="name"
                   id="ticketTypeName"
                   class="form-control"
                   placeholder="Type the Name of the ticket type"
            />
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br>
        <br>
    </form>

    <!--Countries List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/admin/ticket-type" method="post" id="ticketTypeForm" role="form">
        <input type="hidden" id="idTicketType" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty ticketTypes}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="ticketType" items="${ticketTypes}">
                        <c:set var="classSucess" value=""/>
                        <c:choose>
                            <c:when test="${id == ticketType.id.value}">
                                <c:set var="classSuccess" value="info"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="classSuccess" value="none"/>
                            </c:otherwise>
                        </c:choose>
                        <tr class="${classSuccess}">
                            <td>
                                <a href="/admin/ticket-type?id=${ticketType.id}&getAction=searchById">${ticketType.id}</a>
                            </td>
                            <td>${ticketType.name}</td>
                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idTicketType').value = '${ticketType.id}';

                                           document.getElementById('ticketTypeForm').submit();
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
                    No ticket types found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/admin/ticket-type">
        <input type="hidden" name="getAction" value="new"/>
        <button type="submit" class="btn btn-primary  btn-md">New ticket type</button>
    </form>
</div>
</body>
</html>