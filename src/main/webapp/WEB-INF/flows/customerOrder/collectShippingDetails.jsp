<%-- 
    Document   : collectShipmentDetails
    Created on : 2017-10-20, 22:52:17
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="customerOrderFlow.collectShippingDetails.headerPart1.info" var="headerPart1Info"/>
<spring:message code="customerOrderFlow.collectShippingDetails.headerPart2.info" var="headerPart2Info"/>
<spring:message code="customerOrderFlow.collectShippingDetails.validationError.message" var="validationErrorMsg"/>
<spring:message code="customerOrderFlow.collectShippingDetails.paymentMethod.label" var="paymentMethodLbl"/>
<spring:message code="customerOrderFlow.collectShippingDetails.delivaryMethod.label" var="delivaryMethodLbl"/>
<spring:message code="customerOrderFlow.collectShippingDetails.cancel.button.label" var="cancelBtnLbl"/>
<spring:message code="customerOrderFlow.collectShippingDetails.back.button.label" var="backBtnLbl"/>
<spring:message code="customerOrderFlow.collectShippingDetails.forward.button.label" var="forwardBtnLbl"/>

        <jsp:include page="../../views/fragments/header.jsp" />
        
        <section class="main">
            <div class="container pull-down">
                
                <div class="row">
                    <div class="well text-left">
                        <h2> ${headerPart1Info} </h2>
                        <p> ${headerPart2Info} </p>
                    </div>
                </div>
                
                <form:form modelAttribute="shippingDetails" class="form-horizontal">
                    <spring:bind path="*">
                        <c:if test="${status.error}">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4> ${validationErrorMsg} </h4>
                            </div>
                        </c:if>
                    </spring:bind>
                    
                    <fildset>
                        <div class="row">
                            <spring:bind path="paymentMethod">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="paymentMethod"> ${paymentMethodLbl} </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="paymentMethod"> ${paymentMethodLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:select id="paymentMethod" path="paymentMethod" class="form-control">
                                                <option value="NONE"> -------------------- </option> 
                                            <c:forEach items="${paymentMethod}" var="payment">
                                                <option value="${payment.name}" <c:if test="${payment.name==order.shippingDetails.paymentMethod}"> selected="true" </c:if> > 
                                                    ${payment.name} - ${payment.value} PLN 
                                                </option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors path="paymentMethod" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>

                        <div class="row">
                            <spring:bind path="deliveryMethod">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="deliveryMethod"> ${delivaryMethodLbl} </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="deliveryMethod"> ${delivaryMethodLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:select id="deliveryMethod" path="deliveryMethod" class="form-control">
                                                <option value="NONE"> -------------------- </option> 
                                            <c:forEach items="${deliveryMethod}" var="delivery">
                                                <option value="${delivery.name}" <c:if test="${delivery.name==order.shippingDetails.deliveryMethod}"> selected="true" </c:if> > 
                                                    ${delivery.name} - ${delivery.value} PLN
                                                </option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors path="deliveryMethod" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>

                        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
                        <div class="row">
                            <div class="form-group">
                                <button class="btn btn-primary" name="_eventId_backToCollectCustomerInfo"><span class="glyphicon glyphicon-menu-left"></span> ${backBtnLbl} </button>
                                <button name="_eventId_cancel" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> ${cancelBtnLbl} </button>
                                <button type="submit" class="btn btn-primary" name="_eventId_goToOrderConfirmation"> ${forwardBtnLbl} <span class="glyphicon glyphicon-menu-right"></span></button>
                            </div>
                        </div>
                    </fildset>
                </form:form>
                    
            </div>
        </section>
        
        <jsp:include page="../../views/fragments/footer.jsp" />
    </body>
</html>