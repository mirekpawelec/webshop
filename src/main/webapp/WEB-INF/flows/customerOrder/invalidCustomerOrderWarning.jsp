<%-- 
    Document   : invalidOrderWarning
    Created on : 2017-10-13, 12:54:02
    Author     : mirek
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <jsp:include page="../../views/fragments/header.jsp" />
        
        <spring:url value="/home" var="homeUrl" />
        <spring:message code="invalidOrderWarning.button.backToShop.label" var="backToShopLbl" />
        
        <section class="main">
            <div class="container pull-down">
                <div class="jumbotron">
                    <div class="row">
                        <h3> Nie znalazliśmy Twojego koszyka :( </h3>
                    </div>
                </div>
                <div class="row">
                    <div class="alert alert-danger">
                        <p> Proces realizacji zamówienia został przerwany. </p>
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
