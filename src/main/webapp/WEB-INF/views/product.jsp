<%-- 
    Document   : product
    Created on : 2017-09-05, 20:52:13
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
        <header>
            <h1>Szczegóły produktu:</h1>
        </header>
        <section>
            <table>
                <tr><td>Numer produktu</td><td>${product.productNo}</td></tr>
                <tr><td>Nazwa produktu</td><td>${product.name}</td></tr>
                <tr><td>Producent</td><td>${product.manufacturer}</td></tr>
                <tr><td>Rodzaj sprzętu</td><td>${product.category}</td></tr>
                <tr><td>Opis</td><td>${product.description}</td></tr>
                <tr><td>Cena</td><td>${product.unitPrice}</td></tr>
                <tr><td>Sztuk</td><td>${product.quantityInBox}</td></tr>
            </table>
            <br/>
            <br/>
            <a href="<spring:url value="/products"/>"><button>Wróć</button></a>
        </section>
        <footer>
            @Copyright by Miro
        </footer>
    </body>
</html>
