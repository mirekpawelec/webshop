<%-- 
    Document   : showProduct
    Created on : 2017-11-02, 16:31:43
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:url value="/resource/img/${param.productNo}.jpg" var="imageUrl"/>
<spring:message code="product.list.productNo.label" var="productNoLbl"/> 
<spring:message code="product.list.name.label" var="nameLbl"/> 
<spring:message code="product.list.manufacturer.label" var="manufacturerLbl"/> 
<spring:message code="product.list.category.label" var="categoryLbl"/> 
<spring:message code="product.list.unitPrice.label" var="unitPriceLbl"/> 
<spring:message code="product.list.promotion.label" var="promotionLbl"/> 
<spring:message code="product.list.promotion.yes.message" var="promotionYesMsg"/> 
<spring:message code="product.list.promotion.no.message" var="promotionNoMsg"/> 
<spring:message code="product.list.discount.label" var="discountLbl"/> 
<spring:message code="product.list.quantityInBox.label" var="quantityInBoxLbl"/> 
<spring:message code="product.list.description.label" var="descriptionLbl"/> 
<spring:message code="product.list.status.label" var="statusLbl"/>
<spring:message code="product.list.createDate.label" var="createDateLbl"/> 

<div class="form-horizontal">
    <div class="row ">
        <div class="col-xs-12 col-sm-5">
            <img src="${imageUrl}" class="img-responsive center-block" alt="Image"/>   
        </div>

        <div class="col-xs-12 col-sm-6">
            <div class="form-group">
                <label class="col-xs-12 col-sm-4"> 
                    ${productNoLbl}
                </label>
                <div class="col-xs-12 col-sm-8">
                    <span class="label label-warning" style="font-size: 16px;">${param.productNo}</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-4"> 
                    ${nameLbl}
                </label>
                <div class="col-xs-12 col-sm-8">
                    ${param.name}
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-4"> 
                    ${manufacturerLbl}
                </label>
                <div class="col-xs-12 col-sm-8">
                    ${param.manufacturer}
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-4"> 
                    ${categoryLbl}
                </label>
                <div class="col-xs-12 col-sm-8">
                    ${param.category}
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-4">  
                    ${unitPriceLbl}
                </label>
                <div class="col-xs-12 col-sm-8">
                    <strong>${param.unitPrice}</strong> PLN
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-4"> 
                    ${quantityInBoxLbl}
                </label>
                <div class="col-xs-12 col-sm-8">
                    ${param.quantityInBox}
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-4"> 
                    ${promotionLbl}
                </label>
                <div class="col-xs-12 col-sm-8"> 
                    <c:choose>
                        <c:when test="${param.promotion == 'T'}">
                            - ${param.discount}%
                        </c:when>
                        <c:otherwise>
                            ${promotionNoMsg}
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div> 
    </div>
                
    <div class="row">
        <div class="form-group">
            <label class="col-xs-12 col-sm-offset-1 col-sm-1">  
                ${descriptionLbl}
            </label>
            <div class="col-xs-12 col-sm-9">
                ${param.description}
            </div>
        </div>
    </div>
            
    <div class="row">    
        <div class="form-group">   
            <label class="col-xs-12 col-sm-offset-1 col-sm-1"> 
                ${statusLbl}
            </label>
            <div class="col-xs-12 col-sm-3">
                ${param.status}
            </div>

            <label class="col-xs-12 col-sm-2"> 
                ${createDateLbl}
            </label>
            <div class="col-xs-12 col-sm-3">
                ${param.createDate}
            </div>
        </div>
    </div>
</div>
