<%-- 
    Document   : welcome
    Created on : 2017-09-14, 19:14:16
    Author     : mirek
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <jsp:include page="./fragments/header.jsp"/>
    
        <section class="main">
            <jsp:include page="./fragments/navi.jsp"/>
            
            <div class="container">
                <div class="jumbotron min-width">
                    <div class="row">
                        <div class="hidden-xs hidden-sm col-md-9 col-lg-9 text-left">
                            
                            <spring:url value="/resource/img/111.555.08.jpg" var="jumbotroneImageUrl"/>
                            <spring:message code="welcome.jumbotron.promotion.label" var="promotionLbl" />
                            <spring:message code="welcome.jumbotron.button.details.label" var="buttonDetailsLbl" />
                            <spring:message code="welcome.jumbotron.button.buyNow.label" var="buttonBuyNowLbl" />

                            <h2> SAMSUNG NOTE 8 <span class="label label-warning"> ${promotionLbl} </span> </h2> 
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem distinctio sapiente nemo odio amet alias.</p>
                            
                            <div class="btn-group btn-group-lg">
                                <a href="#" class="btn btn-default"> ${buttonDetailsLbl} <span class="glyphicon glyphicon-chevron-right"></span></a>
                                <a href="#" class="btn btn-primary"> ${buttonBuyNowLbl} <span class="glyphicon glyphicon-shopping-cart"></span></a>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 col-sm-8 hidden-md hidden-lg text-left">
                            <h3> SAMSUNG NOTE 8 <span class="label label-warning"> ${promotionLbl} </span> </h3> 
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem distinctio sapiente nemo odio amet alias.</p>
                            
                            <div class="btn-group btn-group-sm">
                                <a href="#" class="btn btn-default"> ${buttonDetailsLbl} <span class="glyphicon glyphicon-chevron-right"></span></a>
                                <a href="#" class="btn btn-primary"> ${buttonBuyNowLbl} <span class="glyphicon glyphicon-shopping-cart"></span></a>
                            </div>
                        </div>
                        
                        <div class="hidden-xs col-sm-4 col-md-3 col-lg-3">
                            <img class="img-reponsive img-thumbnail" src="${jumbotroneImageUrl}" alt="image">                                
                        </div>
                    </div>
                </div>
                        
                <div class="row min-width">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <div class="panel panel-primary panel-min-height">
                            
                            <spring:url value="/filter" var="filterActionUrl" />
                            
                            <spring:message code="welcome.form.browsingOptions.label" var="browsingOptionsLbl" />
                            <spring:message code="welcome.form.manufacturer.label" var="manufacturerLbl" />
                            <spring:message code="welcome.form.category.label" var="categoryLbl" />
                            <spring:message code="welcome.form.unitPrice.label" var="priceLbl" />
                            <spring:message code="welcome.form.unitPriceFrom.label" var="priceFromLbl" />
                            <spring:message code="welcome.form.unitPriceTo.label" var="priceToLbl" />
                            <spring:message code="welcome.form.inStock.label" var="inStockLbl" />
                            <spring:message code="welcome.form.button.filter.label" var="buttonFilterLbl" />
                            <spring:message code="welcome.form.button.reset.label" var="buttonResetLbl" />
                            
                            <div class="panel-heading">
                                <h6 class="panel-title"> ${browsingOptionsLbl} </h6>
                            </div>
                            
                            <div class="panel-body">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    
                                    <form:form modelAttribute="filterOfProducts" class="form-horizontal">
                                        <fieldset>
                                            <div class="form-group form-group-sm">
                                                <label class="control-label">${manufacturerLbl}</label>
                                                
                                                <form:select id="manufacturer" path="manufacturer" class="form-control">
                                                    <form:option value="NONE" label="---------------"/>
                                                    <form:options items="${manufacturers}" />
                                                </form:select>
                                            </div>
                                            
                                            <div class="form-group form-group-sm">
                                                <label class="control-label">${categoryLbl}</label>
                                                
                                                <form:select id="category" path="category" class="form-control">
                                                    <form:option value="NONE" label="---------------"/>
                                                    <form:options items="${categories}" />
                                                </form:select>
                                            </div>
                                            
                                            <div class="form-group form-group-sm">
                                                <label class="control-label">${priceLbl}</label>
                                                
                                                <div class="row">
                                                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                                        <label class="control-label">${priceFromLbl}</label>
                                                        <form:input type="number" id="minUnitPrice" path="minUnitPrice" class="form-control"/>
                                                    </div>
                                                    
                                                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                                        <label class="control-label">${priceToLbl}</label>
                                                        <form:input type="number" id="maxUnitPrice" path="maxUnitPrice" class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                                    
                                            <div class="form-group form-group-sm">
                                                <div class="row">
                                                    <div class="col-xs-12 col-sm-7 col-md-6 col-lg-6">
                                                        <label class="control-label">${inStockLbl}</label>
                                                    </div>
                                                    <div class="col-xs-12 col-sm-5 col-md-6 col-lg-6">
                                                        <form:checkbox path="inStock" class="form-control"/>
                                                    </div>
                                                </div>
                                            </div> 
                                                    
                                            <div class="form-group">
                                                <input id="btnSubmit" type="submit" value="${buttonFilterLbl}" class="btn btn-primary btn-block btn-xs"/>
                                                <input id="btnReset" type="reset" value="${buttonResetLbl}" class="btn btn-primary btn-block btn-xs"/>
                                            </div>
                                        </fieldset>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                        
                        <c:forEach items="${allProducts}" var="product">
                            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">                                
                                
                                <c:set value="${fn:length(product.repositorySet)}" var="repositorySetSize" />
                                
                                <spring:url value="/resource/img/${product.productNo}.jpg" var="imageUrl"/>
                                <spring:url value="/home/product?productNo=${product.productNo}" var="detailsUrl"/>
                                
                                <spring:message code="welcome.list.currency.label" var="currencyLabel"/> 
                                <spring:message code="welcome.list.details.label" var="detailsLabel"/>
                                <spring:message code="welcome.list.addToCart.label" var="addToCartLabel"/>
                                
                                <div class="thumbnail">
                                   
                                    <div class="visible-sm visible-lg">
                                        <div class="box-img-lg">
                                            <img src="${imageUrl}" alt="image">
                                        </div>
                                    </div>  
                                        
                                    <div class="visible-xs visible-md">
                                        <div class="box-img-md">
                                            <img src="${imageUrl}" alt="image">
                                        </div>
                                    </div> 
                                        
                                    <div class="caption">
                                        <div class="caption-box">
                                            <h3> 
                                                ${product.manufacturer} 
                                                <small>${product.name}</small> 
                                            </h3>
                                        </div>
                                            
                                        <div class="price-box">
                                            <p>
                                                <span style="font-size: 20px; font-weight: bold"> ${product.unitPrice} </span> ${currencyLabel}
                                                <c:choose>
                                                    <c:when test="${repositorySetSize > 0}">
                                                        <span class="label label-success"> InStock </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-danger"> OutStock </span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                            
                                        <br>
                                        <div class="btn-group btn-group btn-group-sm">
                                            <a href="${detailsUrl}" class="btn btn-default"> ${detailsLabel} 
                                                <span class="glyphicon glyphicon-chevron-right"></span> </a>
                                            <a href="#" class="btn btn-primary ${repositorySetSize==0?'disabled':''}"> ${addToCartLabel} 
                                                <span class="glyphicon glyphicon-shopping-cart"></span> </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        
                    </div>
                </div>
            </div>

        </section>
        
        <jsp:include page="./fragments/footer.jsp"/>
    </body>
</html>
