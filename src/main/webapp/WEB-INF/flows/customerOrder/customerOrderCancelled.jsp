<%-- 
    Document   : customerOrderCancelled
    Created on : 2017-10-13, 14:12:25
    Author     : mirek
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:url value="/home" var="homeUrl" />
<spring:message code="customerOrderFlow.customerOrderCancelled.validationError.message" var="canceledOrderMsg"/>
<spring:message code="customerOrderFlow.customerOrderCancelled.button.backToShop.label" var="backToShopLbl" />

<jsp:include page="../../views/fragments/header.jsp" />
    <section class="main">
        <div class="container pull-down">
            <div class="jumbotron">
                <div class="row">
                    <h3> ${canceledOrderMsg} </h3>
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
