<%-- 
    Document   : navi
    Created on : 2017-09-15, 19:17:20
    Author     : mirek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/admin/products/add" var="addNewProductUrl"/>

<div class="container">
    <div class="row">
        <div class="btn-group pull-right" role="group" aria-label="...">
            <button type="button" class="btn btn-default" onclick="location.href='${addNewProductUrl}'"> <spring:message code="navi.button.newProduct.label"/> </button> 
        </div>
    </div>
</div>