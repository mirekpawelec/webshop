<%-- 
    Document   : products
    Created on : 2017-09-05, 19:31:57
    Author     : mirek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Wszystkie produkty</h1>
        <a href="<spring:url value="/products/add"/>">addProduct</a>
    </body>
</html>
