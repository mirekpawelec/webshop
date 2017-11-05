<%-- 
    Document   : navi
    Created on : 2017-09-15, 19:17:20
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<spring:url value="/admin/repository" var="warehouseUrl"/>
<spring:url value="/admin/deliveries" var="deliveriesUrl"/>
<spring:url value="/admin/delivery" var="newDeliveryUrl"/>
<spring:url value="/admin/products" var="allProductUrl"/>
<spring:url value="/admin/products/add" var="addNewProductUrl"/>
<spring:url value="/admin/users" var="usersUrl"/>
<spring:url value="/user/add" var="userAddUrl"/>
<spring:url value="/admin/classes" var="systemClassUrl"/>
<spring:url value="/admin/classes/add" var="addSystemClassUrl"/>
<spring:url value="/admin/messages" var="clientMessagesUrl"/>

<spring:message code="navi.button.newDelivery.label" var="newDeliveryLbl" />
<spring:message code="navi.button.warehouse.label" var="warehouseLbl" />
<spring:message code="navi.button.deliveries.label" var="deliveriesLbl" />
<spring:message code="navi.button.allProduct.label" var="allProductLbl" />
<spring:message code="navi.button.newProduct.label" var="newProductLbl" />
<spring:message code="navi.button.users.label" var="usersLbl" />
<spring:message code="navi.button.userAdd.label" var="userAddLbl" />
<spring:message code="navi.button.systemClasses.label" var="systemClassLbl" />
<spring:message code="navi.button.addSystemClasses.label" var="addSystemClassLbl" />
<spring:message code="navi.button.clientMessages.label" var="clientMessagesLbl" />
    
<div class="container" ng-controller="cartController" ng-init="getNumberOfItemsFromCart('${sessionId}')">
    <div class="row" style="padding-top: 100px;">
        <div class="col-xs-11 col-sm-11 col-md-11 col-lg-11">
            <div class="btn-group pull-right" role="group" >
                <security:authorize access="hasRole('ADMIN')">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          ${warehouseLbl}
                          <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                          <li><a href="${warehouseUrl}">${warehouseLbl}</a></li>
                        </ul>
                    </div>
                </security:authorize>
                
                <security:authorize access="hasRole('ADMIN') or hasRole('USER')">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          ${deliveriesLbl}
                          <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                          <li><a href="${deliveriesUrl}">${deliveriesLbl}</a></li>
                          <li><a href="${newDeliveryUrl}">${newDeliveryLbl}</a></li>
                        </ul>
                    </div>
                </security:authorize>
                
                <security:authorize access="hasRole('ADMIN')">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          ${allProductLbl}
                          <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                          <li><a href="${allProductUrl}">${allProductLbl}</a></li>
                          <li><a href="${addNewProductUrl}">${newProductLbl}</a></li>
                        </ul>
                    </div>
                </security:authorize>
                
                <security:authorize access="hasRole('ADMIN')">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          ${usersLbl}
                          <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                          <li><a href="${usersUrl}">${usersLbl}</a></li>
                          <li><a href="${userAddUrl}">${userAddLbl}</a></li>
                        </ul>
                    </div>
                </security:authorize>
                
                <security:authorize access="hasRole('ADMIN')">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          ${systemClassLbl}
                          <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                          <li><a href="${systemClassUrl}">${systemClassLbl}</a></li>
                          <li><a href="${addSystemClassUrl}">${addSystemClassLbl}</a></li>
                        </ul>
                    </div>
                </security:authorize>
                
                <security:authorize access="hasRole('ADMIN')">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          ${clientMessagesLbl}
                          <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                          <li><a href="${clientMessagesUrl}">${clientMessagesLbl}</a></li>
                        </ul>
                    </div>
                </security:authorize>
            </div>
        </div>
    </div>
</div>
