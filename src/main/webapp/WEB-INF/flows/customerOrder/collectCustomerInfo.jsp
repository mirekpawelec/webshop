<%-- 
    Document   : collectCustomerInfo
    Created on : 2017-10-13, 13:55:33
    Author     : mirek
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="customerOrderFlow.collectCustomerInfo.headerPart1.info" var="headerPart1Info"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.headerPart2.info" var="headerPart2Info"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.headerPart3.info" var="headerPart3Info"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.validationError.message" var="validationErrorMsg"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.email.label" var="emailLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.firstName.label" var="firstNameLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.lastName.label" var="lastNameLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.doorNo.label" var="doorNoLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.streetName.label" var="streetNameLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.streetNameAndDoorNo.label" var="streetNameAndDoorNoLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.zipCode.label" var="zipCodeLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.areaName.label" var="areaNameLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.zipCodeAndAreaName.label" var="zipCodeAndAreaNameLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.state.label" var="stateLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.country.label" var="countryLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.phoneNumber.label" var="phoneNumberLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.cancel.button.label" var="cancelBtnLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.back.button.label" var="backBtnLbl"/>
<spring:message code="customerOrderFlow.collectCustomerInfo.forward.button.label" var="forwardBtnLbl"/>

        <jsp:include page="../../views/fragments/header.jsp" />
        
        <section class="main">
            <div class="container pull-down">
                <form:form modelAttribute="customer" class="form-horizontal">
                    <div class="row">
                        <div class="well text-left">
                            <h2> ${headerPart1Info} </h2>
                            <p> ${headerPart2Info} </p>
                            <p> ${headerPart3Info} </p>
                        </div>
                    </div>

                    <spring:bind path="*">
                        <c:if test="${status.error}">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4> ${validationErrorMsg} </h4>
                            </div>
                        </c:if>
                    </spring:bind>
                    
                    <fieldset>    
                        <div class="row"> 
                            <spring:bind path="email">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label class="control-label" for="email"> ${emailLbl} </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="email"> ${emailLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="email" id="email" path="email" class="form-control" placeholder="${emailLbl}"/>
                                        <form:errors path="email" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <spring:bind path="firstName">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="firstName"> ${firstNameLbl} </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="firstName"> ${firstNameLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="firstName" path="firstName" class="form-control" placeholder="${firstNameLbl}"/>
                                        <form:errors path="firstName" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row"> 
                            <spring:bind path="lastName">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="firstName"> ${lastNameLbl} </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="firstName"> ${lastNameLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="lastName" path="lastName" class="form-control" placeholder="${lastNameLbl}"/>
                                        <form:errors path="lastName" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <spring:bind path="address.streetName">
                                    <c:if test="${status.error}"> <c:set value="${true}" var="isErrorStreerName"/> </c:if>
                                </spring:bind>

                                <spring:bind path="address.doorNo">
                                    <c:if test="${status.error}"> <c:set value="${true}" var="isErrorDoorNo"/> </c:if>
                                </spring:bind>

                                <div class="${isErrorStreerName || isErrorDoorNo ? 'has-error' : ''} hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                    <label class="control-label" for="streetName"> ${streetNameAndDoorNoLbl} </label>
                                </div>

                                <div class="visible-xs">
                                    <div class="${isErrorStreerName ? 'has-error' : ''} col-xs-offset-1 col-xs-7 text-left">
                                        <label class="control-label" for="streetName"> ${doorNoLbl} </label>
                                    </div>

                                    <div class="${isErrorDoorNo ? 'has-error' : ''} col-xs-4 text-left">
                                        <label class="control-label" for="doorNo"> ${streetNameLbl} </label>
                                    </div>
                                </div>

                                <div class="${isErrorStreerName ? 'has-error' : ''} col-xs-offset-1 col-xs-7 col-sm-offset-0 col-sm-6 col-md-4 col-lg-4">
                                    <form:input type="text" id="streetName" path="address.streetName" class="form-control" placeholder="${streetNameLbl}"/>
                                    <form:errors path="address.streetName" class="text-danger"/>
                                </div>

                                <div class="${isErrorDoorNo ? 'has-error' : ''} col-xs-4 col-sm-2 col-md-2 col-lg-2">
                                    <form:input type="text" id="doorNo" path="address.doorNo" class="form-control" placeholder="${doorNoLbl}"/>
                                    <form:errors path="address.doorNo" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row"> 
                            <div class="form-group">
                                <spring:bind path="address.zipCode">
                                    <c:if test="${status.error}"> <c:set value="${true}" var="isErrorZipCode"/> </c:if>
                                </spring:bind>

                                <spring:bind path="address.areaName">
                                    <c:if test="${status.error}"> <c:set value="${true}" var="isErrorAreaName"/> </c:if>
                                </spring:bind>
                                
                                <div class="${isErrorZipCode || isErrorAreaName ? 'has-error' : ''} hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                    <label class="control-label" for="zipCode"> ${zipCodeAndAreaNameLbl} </label>
                                </div>

                                <div class="visible-xs">
                                    <div class="${isErrorZipCode ? 'has-error' : ''} col-xs-offset-1 col-xs-5 text-left">
                                        <label class="control-label" for="zipCode"> ${zipCodeLbl} </label>
                                    </div>

                                    <div class="${isErrorAreaName ? 'has-error' : ''} col-xs-6 text-left">
                                        <label class="control-label" for="areaName"> ${areaNameLbl} </label>
                                    </div>
                                </div>

                                <div class="${isErrorZipCode ? 'has-error' : ''} col-xs-offset-1 col-xs-5 col-sm-offset-0 col-sm-3 col-md-2 col-lg-2">
                                    <form:input type="text" id="zipCode" path="address.zipCode" class="form-control" placeholder="${zipCodeLbl}"/>
                                    <form:errors path="address.zipCode" class="text-danger"/>
                                </div>

                                <div class="${isErrorAreaName ? 'has-error' : ''} col-xs-6 col-sm-5 col-md-4 col-lg-4">
                                    <form:input type="text" id="areaName" path="address.areaName" class="form-control" placeholder="${areaNameLbl}"/>
                                    <form:errors path="address.areaName" class="text-danger"/>
                                </div>  
                            </div>
                        </div>
                                
                        <div class="row"> 
                            <spring:bind path="address.state">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="state"> ${stateLbl} </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="state"> ${stateLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="state" path="address.state" class="form-control" placeholder="${stateLbl}"/>
                                        <form:errors path="address.state" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row"> 
                            <spring:bind path="address.country">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="country"> ${countryLbl} </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="country"> ${countryLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="country" path="address.country" class="form-control" placeholder="${countryLbl}"/>
                                        <form:errors path="address.country" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row"> 
                            <spring:bind path="phoneNumber">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="phoneNumber"> ${phoneNumberLbl} </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="phoneNumber"> ${phoneNumberLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="phoneNumber" path="phoneNumber" class="form-control" placeholder="+__ ___ ___ ___"/>
                                        <form:errors path="phoneNumber" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
                        
                        <div class="row">
                            <div class="form-group">
                                <a href="/webshop/cart/${sessionId}" class="btn btn-primary"><span class="glyphicon glyphicon-menu-left"></span> ${backBtnLbl} </a>
                                <button name="_eventId_cancel" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> ${cancelBtnLbl} </button>
                                <button type="submit" name="_eventId_keepGoing" class="btn btn-primary"> ${forwardBtnLbl} <span class="glyphicon glyphicon-menu-right"></span> </button>
                            </div>
                        </div>  
                    </fieldset>
                </form:form>
            </div>
        </section>
        
        <jsp:include page="../../views/fragments/footer.jsp" />
    </body>
</html>
