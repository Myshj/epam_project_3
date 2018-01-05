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
    <h2>Tickets</h2>
    <!--Search Form -->
    <form action="/ticket" method="get" id="searchTicketForm" role="form">
        <input type="hidden" id="getAction" name="getAction" value="searchByExpositionName">
        <div class="form-group col-xs-5">
            <input type="text"
                   name="expositionName"
                   id="expositionName"
                   class="form-control"
                   placeholder="Type the Name of the exposition"
            />
        </div>

        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br>
        <br>
    </form>

    <!--Tickets List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/ticket" method="post" id="ticketForm" role="form">
        <input type="hidden" id="idTicket" name="id">
        <input type="hidden" id="postAction" name="postAction">
        <c:choose>
            <c:when test="${not empty tickets}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Exposition</th>
                        <th>Type</th>
                        <th>Price</th>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="ticket" items="${tickets}">
                        <c:set var="classSucess" value=""/>
                        <c:choose>
                            <c:when test="${id == ticket.id.value}">
                                <c:set var="classSuccess" value="info"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="classSuccess" value="none"/>
                            </c:otherwise>
                        </c:choose>
                        <tr class="${classSuccess}">
                            <td>
                                <a href="/ticket?id=${ticket.id}&getAction=searchById">${ticket.id}</a>
                            </td>
                            <td>
                                <a href="/exposition?id=${ticket.exposition.value.id}&getAction=searchById"
                                >${ticket.exposition.value.name}</a>
                            </td>
                            <td>
                                <a href="/ticket-type?id=${ticket.type.value.id}&getAction=searchById"
                                   >${ticket.type.value.name}</a>
                            </td>
                            <td>${ticket.price}</td>

                            <td><a href="#" id="remove"
                                   onclick="
                                           document.getElementById('postAction').value = 'remove';
                                           document.getElementById('idTicket').value = '${ticket.id}';

                                           document.getElementById('ticketForm').submit();
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
                    No tickets found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="/ticket">
        <input type="hidden" name="getAction" value="new">
        <br>
        <button type="submit" class="btn btn-primary  btn-md">New ticket</button>
    </form>
</div>
</body>
</html>