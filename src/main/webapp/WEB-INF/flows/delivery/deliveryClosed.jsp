<%-- 
    Document   : deliveryClosed
    Created on : 2017-09-25, 21:46:12
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Zamknięto dostawę!</h1>
    
        <c:url value="/home" var="home2"/>
        <a href="${home2}"> Home </a>
    </body>
</html>
