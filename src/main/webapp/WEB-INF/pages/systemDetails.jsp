<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 01-03-2017
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="css/styles.css"/>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" href="css/login.css">
<html lang="en" class="no-js">
<script>
    function loginOverLay() {
        $(".overlay").fadeOut("fast");
        $(".overlayWindow").fadeOut("fast");
        $('.overlayWindow').css("display", "none");
        $('.overlay').remove();
    }
</script>
<body>
<div class="bg">
    <div class="overlay"></div>
    <div class="overlayWindow">
        <form:form method="POST" action="systemDetailsBean" commandName="systemCommandName">
            <div class="systemDev" id="systemDev">
                <spring:bind path="ipAddress">
                    <span class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="ipAddress" id="ipAddress" class="form-control" placeholder="Enter the IP address"/>
                    <form:errors path="ipAddress" id="error" cssClass="error"/>
                    </span>
                </spring:bind>
                <spring:bind path="macAddress">
                    <span class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="macAddress" id="macAddress" class="form-control"
                                placeholder="Enter the MAC address"/>
                    <form:errors path="macAddress" id="error" cssClass="error"/>
                    </span>
                </spring:bind>
                <spring:bind path="gateway">
                    <span class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="gateway" id="gateway" class="form-control"
                                placeholder="Enter the Gateway"/>
                    <form:errors path="gateway" id="error" cssClass="error"/>
                    </span>
                </spring:bind>
                <spring:bind path="subnet">
                    <span class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="subnet" id="subnet" class="form-control"
                                placeholder="Enter the Subnet"/>
                    <form:errors path="subnet" id="error" cssClass="error"/>
                    </span>
                </spring:bind>
                <input type="submit" id="system" value="Save"/>
            </div>
        </form:form>
        <span id="loginCloseIcon" onclick="loginOverLay()" class="btn_Close" title="Close Window" alt="close"></span>
    </div>
</div>
</body>
</html>