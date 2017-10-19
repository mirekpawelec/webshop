<%-- 
    Document   : collectCustomerInfo
    Created on : 2017-08-14, 21:21:11
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:url value="/home" var="homeUrl" />
<spring:url value="/home" var="printUrl" />
<spring:message code="thankCustomer.button.toShop.label" var="toShopLbl" />
<spring:message code="thankCustomer.button.print.label" var="printLbl" />
<spring:message code="thankCustomer.info.currency.label" var="currencyLbl" />

    <jsp:include page="../../views/fragments/header.jsp" />
    
        <section class="main">
            <div class="container pull-down">
                <div class="row">
                    <div class="jumbotron" style="color: green;">
                        <h1> <span class="glyphicon glyphicon-ok-sign"></span> </h1>
                        <h3> Dziękujemy za złożenie zamówienia </h3>
                    </div>
                </div> 
                <hr>
                <div class="row">
                    <p> Dziękujemy za złożenie zamówienia <strong> #${order.orderId} </strong> w sklepie <strong> WebShop </strong>! </p>
                    <p> Potwierdzenie zostało wysłane na adres: ${order.customer.email} </p>
                    <p> Wartość zamówienia: ${order.cart.costOfAllItems} ${currencyLbl}</p>
                    <br>
                    <p> O zmianie statusu Twojego zamówienia będziemy Cię informować mailowo. </p>
                    <br>
                    <p> Pozdrawiamy,</p>
                    <h5> WebShop </h5>
                </div>
                <hr>
                <div class="row">
                    <a href="${homeUrl}" class="btn btn-default btn-xs pull-left"> <span class="glyphicon-hand-left glyphicon"></span> ${toShopLbl} </a>
                    <a href="${printUrl}" class="btn btn-default btn-xs pull-right"> <span class="glyphicon glyphicon-print"></span> ${printLbl} </a>
                </div>
            </div>
        </section>
    
    <jsp:include page="../../views/fragments/footer.jsp" />
    
    </body>
</html>
