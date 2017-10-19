<%-- 
    Document   : collectCustomerInfo
    Created on : 2017-10-13, 13:55:33
    Author     : mirek
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <jsp:include page="../../views/fragments/header.jsp" />
        
        <section class="main">
            <div class="container pull-down">
                <form:form modelAttribute="customer" class="form-horizontal">
                    
                    <spring:bind path="*">
                        <c:if test="${status.error}">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4> Popraw zaznaczone pola. </h4>
                            </div>
                        </c:if>
                    </spring:bind>
                    
                    <fieldset>
                        <div class="row">
                            <div class="well text-left">
                                <h3> Dane kupującego </h3>
                                <p> Tutaj wpisz dane osoby, która zawiera transakcje </p>
                                <p> Adres do wysyłki podasz w nastepnym kroku </p>
                            </div>
                        </div>
                        
                        <div class="row"> 
                            <spring:bind path="email">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label class="control-label" for="email"> e-mail </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="email"> e-mail </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="email" id="email" path="email" class="form-control" placeholder="e-mail"/>
                                        <form:errors path="email" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <spring:bind path="firstName">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="firstName"> imię </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="firstName"> imię </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="firstName" path="firstName" class="form-control" placeholder="imię"/>
                                        <form:errors path="firstName" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row"> 
                            <spring:bind path="lastName">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="firstName"> nazwisko </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="firstName"> nazwisko </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="lastName" path="lastName" class="form-control" placeholder="nazwisko"/>
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
                                    <label class="control-label" for="streetName"> ulica i numer domu </label>
                                </div>

                                <div class="visible-xs">
                                    <div class="${isErrorStreerName ? 'has-error' : ''} col-xs-offset-1 col-xs-7 text-left">
                                        <label class="control-label" for="streetName"> ulica </label>
                                    </div>

                                    <div class="${isErrorDoorNo ? 'has-error' : ''} col-xs-4 text-left">
                                        <label class="control-label" for="doorNo"> numer domu </label>
                                    </div>
                                </div>

                                <div class="${isErrorStreerName ? 'has-error' : ''} col-xs-offset-1 col-xs-7 col-sm-6 col-md-4 col-lg-4">
                                    <form:input type="text" id="streetName" path="address.streetName" class="form-control" placeholder="ulica"/>
                                    <form:errors path="address.streetName" class="text-danger"/>
                                </div>

                                <div class="${isErrorDoorNo ? 'has-error' : ''} col-xs-4 col-sm-2 col-md-2 col-lg-2">
                                    <form:input type="text" id="doorNo" path="address.doorNo" class="form-control" placeholder="numer"/>
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
                                    <label class="control-label" for="zipCode"> kod pocztowy i miasto</label>
                                </div>

                                <div class="visible-xs">
                                    <div class="${isErrorZipCode ? 'has-error' : ''} col-xs-offset-1 col-xs-5 text-left">
                                        <label class="control-label" for="zipCode"> kod pocztowy </label>
                                    </div>

                                    <div class="${isErrorAreaName ? 'has-error' : ''} col-xs-6 text-left">
                                        <label class="control-label" for="areaName"> miasto</label>
                                    </div>
                                </div>

                                <div class="${isErrorZipCode ? 'has-error' : ''} col-xs-offset-1 col-xs-5 col-sm-3 col-md-2 col-lg-2">
                                    <form:input type="text" id="zipCode" path="address.zipCode" class="form-control" placeholder="kod pocztowy"/>
                                    <form:errors path="address.zipCode" class="text-danger"/>
                                </div>

                                <div class="${isErrorAreaName ? 'has-error' : ''} col-xs-6 col-sm-5 col-md-4 col-lg-4">
                                    <form:input type="text" id="areaName" path="address.areaName" class="form-control" placeholder="miasto"/>
                                    <form:errors path="address.areaName" class="text-danger"/>
                                </div>  
                            </div>
                        </div>
                                
                        <div class="row"> 
                            <spring:bind path="address.state">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="state"> województwo </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="state"> województwo </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="state" path="address.state" class="form-control" placeholder="województwo"/>
                                        <form:errors path="address.state" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row"> 
                            <spring:bind path="address.country">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="country"> kraj </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="country"> kraj </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="country" path="address.country" class="form-control" placeholder="kraj"/>
                                        <form:errors path="address.country" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row"> 
                            <spring:bind path="phoneNumber">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-offset-1 col-lg-2 text-right">
                                        <label class="control-label" for="phoneNumber"> numer telefonu</label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-11 text-left">
                                        <label class="control-label" for="phoneNumber"> numer telefonu</label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-11 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="phoneNumber" path="phoneNumber" class="form-control" placeholder="+__ ___ ___ ___"/>
                                        <form:errors path="phoneNumber" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
                        
                        <div class="row">
                            <div class="form-group">
                                <a href="/webshop/cart/${sessionId}" class="btn btn-primary"><span class="glyphicon glyphicon-menu-left"></span> Wróć </a>
                                <button name="_eventId_cancel" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Anuluj </button>
                                <button type="submit" name="_eventId_keepGoing" class="btn btn-primary"> Dalej <span class="glyphicon glyphicon-menu-right"></span></button>
                            </div>
                        </div>  
                    </fieldset>
                </form:form>
            </div>
        </section>
        
        <jsp:include page="../../views/fragments/footer.jsp" />
    </body>
</html>
