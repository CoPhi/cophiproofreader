<%-- 
    Document   : logonError
    Created on : Dec 7, 2013, 9:50:58 AM
    Author     : federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Error</title>
</head>
<body>
    <c:url var="url" value="/index.jsp"/>
    <h2>Invalid user name or password.</h2>

    <p>Please enter a user name or password that is authorized to access this 
    application. For this application, this means a user that has been created in the 
    <code>file</code> realm and has been assigned to the <em>group</em> of 
    <code>user</code>.  Click here to <a href="logon.jsp">Try Again</a></p>
</body>
</html>
