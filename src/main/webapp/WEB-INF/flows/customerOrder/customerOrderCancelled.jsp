<%-- 
    Document   : customerOrderCancelled
    Created on : 2017-10-13, 14:12:25
    Author     : mirek
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <jsp:include page="../../views/fragments/header.jsp" />
        
        <spring:url value="/home" var="homeUrl" />
        <spring:message code="customerOrderCancelled.button.backToShop.label" var="backToShopLbl" />
        
        <section class="main">
            <div class="container pull-down">
                <div class="jumbotron">
                    <div class="row">
                        <h3> Składanie zamówienia anulowano. </h1></h3>
                    </div>
                </div>
                <div class="row">
                    <a href="${homeUrl}" class="btn btn-primary"> ${backToShopLbl} </a>
                </div>
            </div>
        </section>
    
        <jsp:include page="../../views/fragments/footer.jsp" />
    </body>
</html>
