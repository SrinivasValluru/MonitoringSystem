<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 06-03-2017
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of IP Address Details</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>IP Address Details</h2></caption>
        <tr>
            <th>ID</th>
            <th>Ip Address</th>
            <th>Active Time</th>
            <th>DeActive Time</th>
        </tr>
        <c:forEach var="activeIPDetails" items="${activeIPDetailsList}">
                <tr>
                    <td><c:out value="${activeIPDetails.id}" /></td>
                    <td><c:out value="${activeIPDetails.activeIpAddress}" /></td>
                    <td><c:out value="${activeIPDetails.activeTime}" /></td>
                    <td><c:out value="${activeIPDetails.deActiveTime}" /></td>
                </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>