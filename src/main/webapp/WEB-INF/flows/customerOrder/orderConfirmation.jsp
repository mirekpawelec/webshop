<%-- 
    Document   : collectCustomerInfo
    Created on : 2017-08-14, 20:26:25
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<spring:message code="customerOrderFlow.orderConfirmation.headerPage.info" var="headerInfo"/>
<spring:message code="customerOrderFlow.orderConfirmation.validationErrorPart1.message" var="validationErrorPart1Msg"/>
<spring:message code="customerOrderFlow.orderConfirmation.validationErrorPart2.message" var="validationErrorPart2Msg"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.header.info" var="modalHeaderInfo"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.customerName.label" var="modalCustomerNameLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.streetName.label" var="modalStreetNameLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.doorNo.label" var="modalDoorNoLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.streetNameAndDoorNo.label" var="modalStreetNameAndDoorNoLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.zipCode.label" var="modalZipCodeLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.areaName.label" var="modalAreaNameLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.zipCodeAndAreaName.label" var="modalZipCodeAndAreaNameLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.state.label" var="modalStateLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.country.label" var="modalCountryLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.phoneNumber.label" var="modalPhoneNoLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.close.button.label" var="modalCloseBtnLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.modal.form.confirm.button.label" var="modalConfirmBtnLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.cancel.button.label" var="cancelBtnLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.back.button.label" var="backBtnLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.confirmOrder.button.label" var="confirmOrderBtnLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.table.productName.label" var="productNameTabLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.table.item.label" var="itemTabLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.table.price.label" var="priceTabLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.table.totalOrderValue.label" var="totalOrderValueTabLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.information.label" var="informationLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.currentStatus.label" var="currentStatusLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.orderNumber.label" var="orderNumberLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.salesDate.label" var="salesDateLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.consignment.label" var="consignmentLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.pack.label" var="packLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.paymentMethod.label" var="paymentMethodLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.payment.label" var="paymentLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.shippingAddress.label" var="shippingAddressLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.changeAddress.button.label" var="changeAddressBtnLbl"/>
<spring:message code="customerOrderFlow.orderConfirmation.panel.billingAddress.label" var="billingAddressLbl"/>


    <jsp:include page="../../views/fragments/header.jsp" />

    <section class="main">
        <div class="container pull-down">
            <div class="row">
                <div class="well">
                    <h3> #${order.cart.cartId} ${headerInfo} </h3>
                </div>
            </div>

            <div class="row">
                <div id="summary" class="table-responsive">
                    <table id="sortTable" class="table table-striped table-hover text-right">
                        <thead>
                                <tr class="primary-background">
                                    <th> # </th>
                                    <th> ${productNameTabLbl} </th>
                                    <th class="text-right"> ${itemTabLbl} </th>
                                    <th class="text-right"> ${priceTabLbl}</th>
                                </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${order.cart.cartItemSet}" var="item" varStatus="counter">
                                <tr>
                                    <td class="text-left"> ${counter.count}</td>
                                    <td class="text-left"> ${item.product.manufacturer} ${item.product.name} </td>
                                    <td> ${item.quantity} </td>
                                    <td> ${item.totalPrice} PLN </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                                <tr>
                                    <td> </td>
                                    <td colspan="2"><h4> ${totalOrderValueTabLbl} </h4></td>
                                    <td><h4><strong>${order.cart.costOfAllItems} PLN </strong></h4></td>
                                </tr>
                        </tfoot>
                    </table>
                </div>
            </div>

            <hr/>

            <form:form modelAttribute="shippingAddress" class="form-horizontal">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-7 col-lg-8"> 
                        <div class="panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"> ${informationLbl} </h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table style="width: 100%; text-align: left">
                                        <tr>
                                            <td> ${currentStatusLbl}: </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.status=='AC'}">
                                                        Przyjęte do realizacji
                                                    </c:when>
                                                    <c:when test="${order.status=='RE'}">
                                                        Kompletacja
                                                    </c:when>
                                                    <c:when test="${order.status=='FI'}">
                                                        Produkty zostały wysłane
                                                    </c:when>
                                                    <c:otherwise>
                                                        Oczekiwanie na akceptacje
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <fmt:parseDate value="${fn:replace(order.lastModyficationDate,'T',' ')}" 
                                                               pattern="yyyy-MM-dd HH:mm:ss" var="parsedLmDateTime" type="both" />
                                                ( od <em><fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedLmDateTime}"/> </em> )
                                            </td>
                                        </tr>
                                        <tr><td colspan="2"><br></td></tr>
                                        <tr>
                                            <td> ${orderNumberLbl}:</td>
                                            <td>${order.cart.cartId}</td>
                                        </tr>
                                        <tr>
                                            <c:choose>
                                                <c:when test="${order.status!='ED'}">
                                                    <td> ${salesDateLbl}:</td> 
                                                    <td>
                                                        <fmt:parseDate value="${fn:replace(order.createDate,'T',' ')}" 
                                                                       pattern="yyyy-MM-dd HH:mm:ss" var="parsedCdDateTime" type="both" />
                                                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedCdDateTime}"/> 
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td colspan="2"><br></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>
                                        <tr><td colspan="2"><br></td></tr>
                                        <tr>
                                            <td> ${consignmentLbl}:</td> 
                                            <td>${order.shippingDetails.deliveryMethod} (${order.shippingDetails.deliveryCost} PLN)</td>
                                        </tr>
                                        <tr>
                                            <td>${packLbl}:</td> 
                                            <td></td>
                                            <!--<td>6231168440832 (FedEx.pl)</td>-->
                                        </tr>
                                        <tr><td colspan="2"><br></td></tr>
                                        <tr>
                                            <td> ${paymentMethodLbl}:</td> 
                                            <td>${order.shippingDetails.paymentMethod} (${order.shippingDetails.paymentCost} PLN)</td>
                                        </tr>
                                        <tr>
                                            <td> ${paymentLbl}:</td> 
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.status!='ED'}">
                                                        Opłacone (${order.cart.costOfAllItems + order.shippingDetails.totalCost} PLN)
                                                    </c:when>
                                                    <c:otherwise>
                                                        Do zapłaty (${order.cart.costOfAllItems + order.shippingDetails.totalCost} PLN)
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">   
                        <div class="panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"> ${shippingAddressLbl} </h3>
                            </div>
                            <div class="panel-body text-left">
                                <spring:bind path="*">
                                    <c:if test="${status.error}">
                                        <c:set value="${true}" var="isValidationError"/>
                                        <div class="alert alert-danger alert-dismissible" role="alert">
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4> ${validationErrorPart1Msg} </h4>
                                            <p> ${validationErrorPart2Msg} </p>
                                        </div>
                                    </c:if>
                                </spring:bind>
                                <address>
                                    ${order.shippingAddress.name} <br>
                                    ${order.shippingAddress.address.streetName} ${order.shippingAddress.address.doorNo} <br>
                                    ${order.shippingAddress.address.zipCode}, ${order.shippingAddress.address.areaName}, 
                                    ${order.shippingAddress.address.state} , ${order.shippingAddress.address.country} <br>
                                    <span class="glyphicon glyphicon-phone-alt"></span> ${order.shippingAddress.phoneNumber} </br>
                                </address>
                                <button type="button" data-toggle="modal" data-target="#otherAddress"
                                        class="btn btn-block btn-sm ${isValidationError ? 'btn-danger' : 'btn-default'}" >
                                    ${changeAddressBtnLbl}
                                </button>
                            </div>
                        </div>

                        <div class="panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"> ${billingAddressLbl} </h3>
                            </div>
                            <div class="panel-body text-left">
                                <address>
                                    ${order.customer.firstName} ${order.customer.lastName} <br>
                                    ${order.customer.address.streetName} ${order.customer.address.doorNo} <br>
                                    ${order.customer.address.zipCode}, ${order.customer.address.areaName}, 
                                    ${order.customer.address.state} , ${order.customer.address.country} <br>
                                    <span class="glyphicon glyphicon-envelope"></span> ${order.customer.email} <br>
                                    <span class="glyphicon glyphicon-phone-alt"></span> ${order.customer.phoneNumber}
                                </address>
                            </div>
                        </div>         
                    </div>
                </div>

                <hr/>

                <div class="modal fade bs-example-modal-lg" id="otherAddress" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title"> ${modalHeaderInfo} </h3>
                            </div>
                            <div class="modal-body">
                                <div class="row"> 
                                    <spring:bind path="name">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                <label class="control-label" for="name"> ${modalCustomerNameLbl} </label>
                                            </div>

                                            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                                                <form:input type="text" id="name" path="name" class="form-control" placeholder="${modalCustomerNameLbl}"/>
                                                <form:errors path="name" class="text-danger"/>
                                            </div>
                                        </div>
                                    </spring:bind>
                                </div>

                                <div class="row">
                                    <spring:bind path="address.streetName">
                                        <c:if test="${status.error}"> <c:set value="${true}" var="isErrorStreerName"/> </c:if>
                                    </spring:bind>

                                    <spring:bind path="address.doorNo">
                                        <c:if test="${status.error}"> <c:set value="${true}" var="isErrorDoorNo"/> </c:if>
                                    </spring:bind>

                                    <div class="form-group">
                                        <div class="${isErrorStreerName || isErrorDoorNo ? 'has-error' : ''} col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                            <label class="control-label" for="streetName"> ${modalStreetNameAndDoorNoLbl} </label>
                                        </div>

                                        <div class="${isErrorStreerName? 'has-error' : ''} col-xs-5 col-sm-5 col-md-5 col-lg-5">
                                            <form:input type="text" id="streetName" path="address.streetName" class="form-control" placeholder="${modalStreetNameLbl}"/>
                                            <form:errors path="address.streetName" class="text-danger"/>
                                        </div>

                                        <div class="${isErrorDoorNo ? 'has-error' : ''} col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                            <form:input type="text" id="doorNo" path="address.doorNo" class="form-control" placeholder="${modalDoorNoLbl}"/>
                                            <form:errors path="address.doorNo" class="text-danger"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row"> 
                                    <spring:bind path="address.zipCode">
                                        <c:if test="${status.error}"> <c:set value="${true}" var="isErrorzZipCode"/> </c:if>
                                    </spring:bind>

                                    <spring:bind path="address.areaName">
                                        <c:if test="${status.error}"> <c:set value="${true}" var="isErrorAreaName"/> </c:if>
                                    </spring:bind>

                                    <div class="form-group">
                                        <div class="${isErrorzZipCode || isErrorAreaName ? 'has-error' : ''} col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                            <label class="control-label" for="zipCode"> ${modalZipCodeAndAreaNameLbl} </label>
                                        </div>

                                        <div class="${isErrorzZipCode ? 'has-error' : ''} col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                            <form:input type="text" id="zipCode" path="address.zipCode" class="form-control" placeholder="${modalZipCodeLbl}"/>
                                            <form:errors path="address.zipCode" class="text-danger"/>
                                        </div>

                                        <div class="${isErrorAreaName ? 'has-error' : ''} col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                            <form:input type="text" id="areaName" path="address.areaName" class="form-control" placeholder="${modalAreaNameLbl}"/>
                                            <form:errors path="address.areaName" class="text-danger"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row"> 
                                    <spring:bind path="address.state">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                <label class="control-label" for="state"> ${modalStateLbl} </label>
                                            </div>

                                            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                                                <form:input type="text" id="state" path="address.state" class="form-control" placeholder="${modalStateLbl}"/>
                                                <form:errors path="address.state" class="text-danger"/>
                                            </div>
                                        </div>
                                    </spring:bind>
                                </div>

                                <div class="row"> 
                                    <spring:bind path="address.country">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                <label class="control-label" for="country"> ${modalCountryLbl} </label>
                                            </div>

                                            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                                                <form:input type="text" id="country" path="address.country" class="form-control" placeholder="${modalCountryLbl}"/>
                                                <form:errors path="address.country" class="text-danger"/>
                                            </div>
                                        </div>
                                    </spring:bind>
                                </div>

                                <div class="row">
                                    <spring:bind path="phoneNumber">
                                        <div class="form-group ${status.error ? 'has-error' : ''}">
                                            <div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                <label class="control-label" for="phoneNumber"> ${modalPhoneNoLbl} </label>
                                            </div>

                                            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                                                <form:input type="text" id="phoneNumber" path="phoneNumber" class="form-control" placeholder="+__ ___ ___ ___"/>
                                                <form:errors path="phoneNumber" class="text-danger"/>
                                            </div>
                                        </div>
                                    </spring:bind>
                                </div>

                                <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
                            </div>

                            <div class="modal-footer">
                                <div class="col-sm-offset-3 col-sm-6 col-md-offset-3 col-md-6 col-lg-offset-3 col-lg-6">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"> ${modalCloseBtnLbl} </button>
                                    <button type="submit" name="_eventId_changeAddress" class="btn btn-primary"> ${modalConfirmBtnLbl} </button>
                                </div>
                            </div>            
                        </div>
                    </div>
                </div>
            </form:form>   

            <div class="row">
                <a class="btn btn-primary" href="${flowExecutionUrl}&_eventId=backToCollectShippingDetails"><span class="glyphicon glyphicon-chevron-left"></span> ${backBtnLbl} </a>
                <a class="btn btn-success" href="${flowExecutionUrl}&_eventId=orderConfirmed"> <span class="glyphicon glyphicon-ok"></span> ${confirmOrderBtnLbl} </a>
                <a class="btn btn-danger" href="${flowExecutionUrl}&_eventId=cancel"><span class="glyphicon glyphicon-remove"></span> ${cancelBtnLbl} </a>
            </div>
        </div>
    </section>
    
    <jsp:include page="../../views/fragments/footer.jsp" />
    
    </body>
</html>
