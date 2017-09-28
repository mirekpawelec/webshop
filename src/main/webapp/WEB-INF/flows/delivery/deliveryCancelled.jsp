<%-- 
    Document   : deliveryOrderCancelled
    Created on : 2017-09-25, 21:45:09
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <jsp:include page="../../views/fragments/header.jsp" />
        
    <jsp:include page="../../views/fragments/navi.jsp" />
    
        <section class="main">
            <div class="container">
                <br/>
                
                <div class="row">
        <h1>Przerwano dostawę!</h1>
        <c:url value="/home" var="home"/>
        <a href="${home}"> Home </a>
    </body>
</html>
