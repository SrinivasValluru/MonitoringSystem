<%--
  Created by IntelliJ IDEA.
  User: Srinivas.V
  Date: 26/03/2016
  Time: 12:39 PM
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
        $(".overlayloadWindow").fadeOut("fast");
        $('.overlayloadWindow').css("display", "none");
        $('.overlay').remove();
        window.location = "home";
    }
</script>
<body>
<div class="bg">
    <div class="overlay"></div>
    <div class="overlayloadWindow">
        <form:form method="POST" action="loadIpAddress" commandName="loadIpCommandName">
            <div class="loadIpAddress" id="loadIpAddress">
                <spring:bind path="loadIpAddress">
                <span class="form-group ${status.error ? 'has-error' : ''}">
                      <form:input path="loadIpAddress" id="loadIpAddress" class="form-control"
                                  placeholder="Find IP Address Details"/>
                      <form:errors path="loadIpAddress" cssClass="error"/>
               </spring:bind>
              <input type="submit" id="sendId" value="Enter"/>
            </div>
        </form:form>
        <span id="loginCloseIcon" onclick="loginOverLay()" class="btn_Close" title="Close Window" alt="close"></span>
    </div>
</div>
</body>
</html>