<%-- 
    Document   : deliveryClosing
    Created on : 2017-09-26, 21:24:29
    Author     : mirek
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Podsumowanie dostawy i pytanie o zamknięcie! </h1>
        
        
        <form:form modelAttribute="deliveryOrder">

            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
            <input type="submit" value="Wstecz" name="_eventId_back" />
            <input type="submit" value="Zamknij" name="_eventId_close" />
        </form:form>
    </body>
</html>
