<%-- 
    Document   : logon
    Created on : Dec 7, 2013, 9:50:42 AM
    Author     : federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2 style="color:red;">Welcome to the CoPhiProofReader</h2>
<br><br>
<form action="j_security_check" method=post>
    <p><strong>User Name: </strong>
    <input type="text" name="j_username" size="25">
    <p><p><strong>Password: </strong>
    <input type="password" size="15" name="j_password">
    <p><p>
    <input type="submit" value="Submit">
    <input type="reset" value="Reset">
</form>
</body>
</html>
