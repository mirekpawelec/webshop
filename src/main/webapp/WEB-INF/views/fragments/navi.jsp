<%-- 
    Document   : navi
    Created on : 2017-09-15, 19:17:20
    Author     : mirek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/admin/repository" var="warehouseUrl"/>
<spring:url value="/admin/deliveries" var="deliveriesUrl"/>
<spring:url value="/delivery" var="newDeliveryUrl"/>
<spring:url value="/admin/products" var="allProductUrl"/>
<spring:url value="/admin/products/add" var="addNewProductUrl"/>
<spring:url value="/home?language=pl" var="polishLanguageUrl"/>
<spring:url value="/home?language=en" var="englishLanguageUrl"/>


<spring:message code="navi.button.newDelivery.label" var="newDeliveryLbl" />
<spring:message code="navi.button.warehouse.label" var="warehouseLbl" />
<spring:message code="navi.button.deliveries.label" var="deliveriesLbl" />
<spring:message code="navi.button.allProduct.label" var="allProductLbl" />
<spring:message code="navi.button.newProduct.label" var="newProductLbl" />
<spring:message code="navi.button.selectLanguages.label" var="selectLanguageLbl"/>
<spring:message code="navi.button.polishLanguage.label" var="polishLbl" />
<spring:message code="navi.button.englishLanguage.label" var="englishLbl" />
    
<div class="container" ng-controller="cartController" ng-init="getNumberOfItemsFromCart('${sessionId}')">
    <div class="row" style="padding-top: 100px;">
        <div class="col-xs-9 col-sm-9 col-md-10 col-lg-10">
            <div class="btn-group pull-right" role="group" >
                <button type="button" class="btn btn-default" onclick="location.href='${warehouseUrl}'"> ${warehouseLbl} </button>
                <button type="button" class="btn btn-default" onclick="location.href='${deliveriesUrl}'"> ${deliveriesLbl} </button>
                <button type="button" class="btn btn-default" onclick="location.href='${newDeliveryUrl}'"> ${newDeliveryLbl} </button>
                <button type="button" class="btn btn-default" onclick="location.href='${allProductUrl}'"> ${allProductLbl} </button>
                <button type="button" class="btn btn-default" onclick="location.href='${addNewProductUrl}'"> ${newProductLbl} </button> 
            </div>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-2 col-lg-2">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${selectLanguageLbl}
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                  <li><a href="${polishLanguageUrl}"> ${polishLbl} </a></li>
                  <li><a href="${englishLanguageUrl}"> ${englishLbl} </a></li>
                </ul>
            </div>
        </div>
    </div>
</div>