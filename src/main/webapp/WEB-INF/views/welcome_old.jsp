<%-- 
    Document   : welcome
    Created on : 2017-09-14, 19:14:16
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    
    <jsp:include page="./fragments/header.jsp"/>
    
        <section class="main">
            <jsp:include page="./fragments/navi.jsp"/>
            
            <div class="container">
                <div class="jumbotron min-width">
                    <div class="row">
                        <div class="hidden-xs hidden-sm col-md-9 col-lg-9 text-left">
                            <h2> SAMSUNG NOTE 8 <span class="label label-warning"> PROMOCJA </span> </h2> 
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem distinctio sapiente nemo odio amet alias.</p>
                            
                            <div class="btn-group btn-group-lg">
                                <a href="#" class="btn btn-default"> Zobacz więcej <span class="glyphicon glyphicon-chevron-right"></span></a>
                                <a href="#" class="btn btn-primary"> Kup teraz <span class="glyphicon glyphicon-shopping-cart"></span></a>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 col-sm-8 hidden-md hidden-lg text-left">
                            <h3> SAMSUNG NOTE 8 <span class="label label-warning"> PROMOCJA </span> </h3> 
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem distinctio sapiente nemo odio amet alias.</p>
                            
                            <div class="btn-group btn-group-sm">
                                <a href="#" class="btn btn-default"> Zobacz więcej <span class="glyphicon glyphicon-chevron-right"></span></a>
                                <a href="#" class="btn btn-primary"> Kup teraz <span class="glyphicon glyphicon-shopping-cart"></span></a>
                            </div>
                        </div>
                        
                        <div class="hidden-xs col-sm-4 col-md-3 col-lg-3">
                            <spring:url value="/resource/img/111.555.08.jpg" var="jumbotroneImageUrl"/>
                            <img class="img-reponsive img-thumbnail" src="${jumbotroneImageUrl}" alt="image">                                
                        </div>
                    </div>
                </div>
                        
                <div class="row min-width">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <div class="panel panel-primary panel-height">
                            <div class="panel-heading">
                                <h6 class="panel-title"> Opcje przeglądania </h6>
                            </div>
                            
                            <div class="panel-body">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <spring:url value="/filter" var="filterActionUrl" />
                                    
                                    <form:form modelAttribute="filterProducts" method="post" class="form-horizontal">
                                        <fieldset>
                                            <div class="form-group form-group-sm">
                                                <label class="control-label">Producent</label>
                                                
                                                <form:select path="manufacturer" class="form-control">
                                                    <option value="NONE"> --------------- </option>
                                                    <c:forEach items="${manufacturers}" var="itemManufacturer">
                                                        <option value="${itemManufacturer}" <c:if test="${manufacturerSelected==itemManufacturer}">selected</c:if> > 
                                                            ${itemManufacturer}
                                                        </option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                            
                                            <div class="form-group form-group-sm">
                                                <label class="control-label">Kategoria</label>
                                                
                                                <form:select path="category" class="form-control">
                                                    <option value="NONE"> --------------- </option>
                                                    <c:forEach items="${categories}" var="itemCategories">
                                                        <option value="${itemCategories}" <c:if test="${categoriesSelected==itemCategories}">selected</c:if> > 
                                                            ${itemCategories}
                                                        </option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                            
                                            <div class="form-group form-group-sm">
                                                <label class="control-label">Cena</label>
                                                
                                                <div class="row">
                                                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                                        <label class="control-label">od</label>
                                                        <form:input type="number" path="minUnitPrice" class="form-control" value="${minPriceEntered}"/>
                                                    </div>
                                                    
                                                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                                        <label class="control-label">do</label>
                                                        <form:input type="number" path="maxUnitPrice" class="form-control" value="${maxPriceEntered}"/>
                                                    </div>
                                                </div>
                                            </div>
                                                        
                                            <div class="form-group">
                                                <input type="submit" value="Filtruj" class="btn btn-primary btn-block btn-xs"/>
                                                <input type="reset" value="Wyczyść" class="btn btn-primary btn-block btn-xs"/>
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
                                            <h4>
                                                <strong> ${product.unitPrice} </strong> ${currencyLabel}
                                            </h4>
                                        </div>
                                            
                                        <br>
                                        <div class="btn-group btn-group btn-group-sm">
                                            <a href="${detailsUrl}" class="btn btn-default"> ${detailsLabel} <span class="glyphicon glyphicon-chevron-right"></span></a>
                                            <a href="#" class="btn btn-primary"> ${addToCartLabel} <span class="glyphicon glyphicon-shopping-cart"></span></a>
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
