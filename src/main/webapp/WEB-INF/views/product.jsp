<%-- 
    Document   : product
    Created on : 2017-09-05, 20:52:13
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
    <jsp:include page="./fragments/header.jsp" />
        
        <section class="main">
            
            <jsp:include page="./fragments/navi.jsp"/>
            <hr>
            <div class="container">
            
                <hr class="gray">
                
                <div class="row">
                    <c:if test="${not empty css}">
                        <div class="alert alert-${css} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <c:choose> 
                                    <c:when test="${typeProcess == 'create'}">
                                        <strong> <spring:message code="product.alert.afterAddUser.message"/> </strong>
                                    </c:when>
                                    <c:when test="${typeProcess == 'update'}">
                                        <strong> <spring:message code="product.alert.afterUpdateUser.message"/> </strong>
                                    </c:when>
                                </c:choose>
                        </div>                        
                    </c:if>
                </div>
                
                <div class="row">
                        <div class="page-header">
                            <h2> <spring:message code="product.pageHeader.label"/> ${product.manufacturer} <small>${product.name}</small> </h2>
                        </div>
                </div>
                        
                <div class="row">
                    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-5">
                        <spring:url value="/resource/img/${product.productNo}.jpg" var="imageUrl"/>
                        <img src="${imageUrl}" class="img-responsive center-block" alt="Image"/>   
                    </div>
                    
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-7 text-left">
                        <div class="row">
                            <label class="col-xs-12 col-sm-4 col-md-3 col-lg-3"> 
                                <spring:message code="product.list.productNo.label"/>
                            </label>
                            <div class="col-xs-12 col-sm-7 col-md-8 col-lg-8">
                                <span class="label label-warning" style="font-size: 20px;">${product.productNo}</span>
                            </div>
                        </div>
                            <br>
                        <div class="row">
                            <label class="col-xs-12 col-sm-4 col-md-3 col-lg-3"> 
                                <spring:message code="product.list.name.label"/>
                            </label>
                            <div class="col-xs-12 col-sm-7 col-md-8 col-lg-8">
                                ${product.name}
                            </div>
                        </div>
                            <br>
                        <div class="row">
                            <label class="col-xs-12 col-sm-4 col-md-3 col-lg-3"> 
                                <spring:message code="product.list.manufacturer.label"/>
                            </label>
                            <div class="col-xs-12 col-sm-7 col-md-8 col-lg-8">
                                ${product.manufacturer}
                            </div>
                        </div>
                            <br>
                        <div class="row">
                            <label class="col-xs-12 col-sm-4 col-md-3 col-lg-3"> 
                                <spring:message code="product.list.category.label"/>
                            </label>
                            <div class="col-xs-12 col-sm-7 col-md-8 col-lg-8">
                                ${product.category}
                            </div>
                        </div>
                            <br>
                        <div class="row">
                            <label class="col-xs-12 col-sm-4 col-md-3 col-lg-3"> 
                                <spring:message code="product.list.description.label"/>
                            </label>
                            <div class="col-xs-12 col-sm-7 col-md-8 col-lg-8">
                                ${product.description}
                            </div>
                        </div>
                            <br>
                        <div class="row">
                            <label class="col-xs-12 col-sm-4 col-md-3 col-lg-3"> 
                                <spring:message code="product.list.unitPrice.label"/>
                            </label>
                            <div class="col-xs-12 col-sm-7 col-md-8 col-lg-8">
                                <span class="label label-default" style="font-size: 20px;">${product.unitPrice}</span>
                            </div>
                        </div>
                            <br>
                        <div class="row">
                            <label class="col-xs-12 col-sm-4 col-md-3 col-lg-3"> 
                                <spring:message code="product.list.quantityInBox.label"/>
                            </label>
                            <div class="col-xs-12 col-sm-7 col-md-8 col-lg-8">
                                ${product.quantityInBox}
                            </div>
                        </div>
                            <br>    
                        <div class="row">   
                            <label class="col-xs-12 col-sm-4 col-md-3 col-lg-3"> 
                                <spring:message code="product.list.status.label"/>
                            </label>
                            <div class="col-xs-12 col-sm-7 col-md-8 col-lg-8">
                                ${product.status}
                            </div>
                        </div> 
                    </div> 
                </div>
                            
                <hr class="gray">
                
                <div class="row" ng-controller="cartController" ng-init="refreshCart('${sessionId}')">
                        <spring:url value="/home" var="homePageUrl"/>
                        
                        <div class="pull-right">
                            <a href="${homePageUrl}" class="btn btn-default">
                                <span class="glyphicon glyphicon-hand-left"></span> <spring:message code="product.button.backHomePage.label"/> 
                            </a>
                            <a class="btn btn-primary" ng-click="addItemToCart('${product.productId}')">
                                <span class="glyphicon glyphicon-shopping-cart"></span> <spring:message code="product.button.addToCart.label"/> 
                            </a>
                        </div>
                </div>
            </div>
        </section>

    <jsp:include page="./fragments/footer.jsp" />
    
    </body>
</html>
