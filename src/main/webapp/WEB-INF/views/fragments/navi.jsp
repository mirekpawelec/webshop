<%-- 
    Document   : navi
    Created on : 2017-09-15, 19:17:20
    Author     : mirek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/admin/products/add" var="addNewProductUrl"/>
<spring:url value="/admin/products?language=pl" var="polishLanguageUrl"/>
<spring:url value="/admin/products?language=en" var="englishLanguageUrl"/>

<div class="container">
    <div class="row">
        <div class="col-xs-9 col-sm-9 col-md-10 col-lg-10">
            <div class="btn-group pull-right" role="group">
                <button type="button" class="btn btn-default" onclick="location.href='${addNewProductUrl}'"> <spring:message code="navi.button.newProduct.label"/> </button> 
            </div>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-2 col-lg-2">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <spring:message code="navi.button.selectLanguages.label" />
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                  <li><a href="${polishLanguageUrl}"> <spring:message code="navi.button.polishLanguage.label"/> </a></li>
                  <li><a href="${englishLanguageUrl}"> <spring:message code="navi.button.englishLanguage.label"/> </a></li>
                </ul>
            </div>
            
<!--            <div class="btn-group pull-right" role="group">
                <button type="button" class="btn btn-default btn-sm" onclick="location.href='${polishLanguageUrl}'"> <spring:message code="navi.button.polishLanguage.label"/> </button> 
                <button type="button" class="btn btn-default btn-sm" onclick="location.href='${englishLanguageUrl}'"> <spring:message code="navi.button.englishLanguage.label"/> </button> 
            </div>-->
        </div>
    </div>
</div>