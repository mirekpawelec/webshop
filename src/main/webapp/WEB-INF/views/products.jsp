<%-- 
    Document   : products
    Created on : 2017-09-05, 19:31:57
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="pl">
    <head>
        <meta http-equiv="Content-Type" content="text/html" />
        <meta charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <h1>Wszystkie produkty</h1>
        </header>
        <section>
            <table>
                <tr>
                    <th>Numer produktu</th><th>Nazwa produktu</th><th>Producent</th><th>Rodzaj sprzętu</th><th>Opis</th><th>Cena</th><th>Ilość sztuk</th><th>Szczegóły / Usuń</th>
                </tr>
                <c:forEach var="item" items="${products}">
                    <tr>
                        <td>${item.productNo}</td>
                        <td>${item.name}</td>
                        <td>${item.manufacturer}</td>
                        <td>${item.category}</td>
                        <td>${item.description}</td>
                        <td>${item.unitPrice}</td>
                        <td>${item.quantityInBox}</td>
                        <td>
                            <a href="<spring:url value="/products/product?id=${item.productId}"/>"><button>Pokaż szczegóły</button></a>
                            <a href="<spring:url value="/products/delete?id=${item.productId}"/>"><button>Usuń</button></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
        <footer>
            <span>@Copyright by Miro</span>
            <br/>
            <br/>
            <a href="<spring:url value="/products/add"/>"><button>Dodaj nowy product</button></a> 
        </footer>
        
        
    </body>
</html>
