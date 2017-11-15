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
    

<spring:url value="/filter" var="filterActionUrl" />
<spring:message code="homepage.jumbotron.promotion.label" var="promotionLbl" />
<spring:message code="homepage.jumbotron.button.details.label" var="buttonDetailsLbl" />
<spring:message code="homepage.jumbotron.button.buyNow.label" var="buttonBuyNowLbl" />                          
<spring:message code="homepage.form.browsingOptions.label" var="browsingOptionsLbl" />
<spring:message code="homepage.form.manufacturer.label" var="manufacturerLbl" />
<spring:message code="homepage.form.category.label" var="categoryLbl" />
<spring:message code="homepage.form.unitPrice.label" var="priceLbl" />
<spring:message code="homepage.form.unitPriceFrom.label" var="priceFromLbl" />
<spring:message code="homepage.form.unitPriceTo.label" var="priceToLbl" />
<spring:message code="homepage.form.inStock.label" var="inStockLbl" />
<spring:message code="homepage.form.button.filter.label" var="buttonFilterLbl" />
<spring:message code="homepage.form.button.reset.label" var="buttonResetLbl" />
<spring:message code="homepage.list.currency.label" var="currencyLabel"/> 
<spring:message code="homepage.list.details.label" var="detailsLabel"/>
<spring:message code="homepage.list.addToCart.label" var="addToCartLabel"/>  
<spring:message code="homepage.headerMap.partOne.label" var="headerMapOneLbl"/>  
<spring:message code="homepage.headerMap.partTwo.label" var="headerMapTwoLbl"/>  
<spring:message code="homepage.headerContact.partOne.label" var="headerContactOneLbl"/> 
<spring:message code="homepage.headerContact.partTwo.label" var="headerContactTwoLbl"/> 
<spring:message code="homepage.contactForm.name.label" var="contacFormNameLbl"/>  
<spring:message code="homepage.contactForm.email.label" var="contacFormEmailLbl"/>  
<spring:message code="homepage.contactForm.subject.label" var="contacFormSubjectLbl"/>  
<spring:message code="homepage.contactForm.message.label" var="contacFormMessageLbl"/>  
<spring:message code="homepage.contactForm.button.submit.label" var="contacFormSubmitLbl"/>  
<spring:message code="homepage.contactForm.info.message" var="infoMsg"/>  
<spring:message code="homepage.contactForm.validationError.message" var="validationMsg"/>

    <jsp:include page="./fragments/header.jsp"/>
    
    <section class="main">
        
        <jsp:include page="./fragments/navi.jsp"/>
            
        <div class="container">
            <div class="row">
                <c:choose>
                    <c:when test="${not empty successMsgSent}">
                        <div class="alert alert-${successMsgSent} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${infoMsg} </strong>
                        </div> 
                    </c:when>
                    <c:when test="${not empty validationError}">
                        <div class="alert alert-${validationError} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${validationMsg} </strong>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
        
    <!--CAROUSEL-->
        <div id="carousel-promotion-products" class="carousel slide" data-ride="carousel" ng-controller="cartController" ng-init="refreshCart('${sessionId}')">
            <ol class="carousel-indicators">
                <c:forEach items="${promotionProducts}" var="item" varStatus="counter">
                    <li data-target="#carousel-promotion-products" data-slide-to="${counter.count-1}" class="${counter.count-1==0?'active':''}"></li>
                </c:forEach>
            </ol>
            <div class="carousel-inner" role="listbox">
                <c:forEach items="${promotionProducts}" var="item" varStatus="counter">
                    <div class="item ${counter.count==1?'active':''}">
                        <div class="container">
                            <div class="well-lg">
                                <div class="row min-height">
                                    <div class="col-xs-12 col-sm-7 col-md-8 col-lg-9 text-left">
                                        <h3> ${item.manufacturer} ${item.name} <span class="label label-warning"> ${promotionLbl} </span> </h3>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Exercitationem distinctio sapiente nemo odio amet alias.</p>

                                        <div class="btn-group btn-group-sm">
                                            <spring:url value="/home/product?productNo=${item.productNo}" var="detailsUrl"/>
                                            <a href="${detailsUrl}" class="btn btn-default"> ${buttonDetailsLbl} <span class="glyphicon glyphicon-chevron-right"></span></a>
                                            <a class="btn btn-primary" ng-click="addItemToCart('${item.productId}')"> ${buttonBuyNowLbl} <span class="glyphicon glyphicon-shopping-cart"></span></a>
                                        </div>
                                    </div> 

                                    <div class="hidden-xs col-sm-5 col-md-4 col-lg-3">
                                        <spring:url value="/resource/img/${item.productNo}.jpg" var="jumbotroneImageUrl"/>
                                        <img class="img-carousel" src="${jumbotroneImageUrl}" alt="image">                                
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!--<a class="left carousel-control" href="#carousel-promotion-products" role="button" data-slide="prev">-->
                <!--<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>-->
                <!--<span class="sr-only">Previous</span>-->
            <!--</a>-->
            <!--<a class="right carousel-control" href="#carousel-promotion-products" role="button" data-slide="next">-->
                <!--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>-->
                <!--<span class="sr-only">Next</span>-->
            <!--</a>-->
        </div>
        <br>
        
    <!--MAIN PANEL-->
        <c:choose>
            <c:when test="${not empty faq}">
                
                <jsp:include page="./fragments/faq.jsp">
                    <jsp:param name="faq" value="${faq}"/>
                </jsp:include>
                
            </c:when>
            <c:when test="${not empty contentRule}">
                
                <jsp:include page="./fragments/rules.jsp">
                    <jsp:param name="contentRule" value="${contentRule}"/>
                </jsp:include>
                
            </c:when>
            <c:otherwise>
                
                <div class="container min-width">
                    <div class="row">
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">

                        <!--SEARCH PANEL-->
                            <div class="panel panel-primary panel-min-height">
                                <div class="panel-heading">
                                    <h6 class="panel-title"> ${browsingOptionsLbl} </h6>
                                </div>

                                <div class="panel-body">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <form:form modelAttribute="filterProducts" class="form-horizontal">
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

                    <!--PRODUCTS-->
                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9" ng-controller="cartController" ng-init="refreshCart('${sessionId}')">    
                            <c:forEach items="${allProducts}" var="product">
                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">                                

                                    <c:set value="${fn:length(product.repositorySet)}" var="repositorySetSize" />
                                    <spring:url value="/resource/img/${product.productNo}.jpg" var="imageUrl"/>
                                    <spring:url value="/home/product?productNo=${product.productNo}" var="detailsUrl"/>

                                    <div class="thumbnail">
                                        <a href="${detailsUrl}">
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
                                        </a>

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
                                                <a class="btn btn-primary ${repositorySetSize==0?'disabled':''}" ng-click="addItemToCart('${product.productId}')"> ${addToCartLabel} 
                                                    <span class="glyphicon glyphicon-shopping-cart"></span> </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach> 
                        </div>
                    </div>      
                </div>     
                            
            </c:otherwise>
        </c:choose>
    </section>
                    
    <!--OUR LOCATION - MAP--> 
    <div class="container">
        <div class="row">
            <div class="page-header text-left">
                    <h2> ${headerMapOneLbl} <small> ${headerMapTwoLbl} </small></h2>
            </div>
        </div>  
    </div>
    
    <div class="container-fluid">
        <div class="embed-responsive embed-responsive-map">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2470.020527413678!2d18.065658115949617!3d51.75094820068725!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x471ac5f2cc3fd67b%3A0x8892487ababd10c2!2sAleja+Wojska+Polskiego%2C+Kalisz!5e0!3m2!1spl!2spl!4v1509458640432" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
    </div>

    <!--FORM CONTACT-->
    <div class="container">
        <div class="row">
            <div class="page-header">
                <h2> ${headerContactOneLbl} <small> ${headerContactTwoLbl} </small></h2>
            </div>
        </div>

        <div class="row">
            <form:form modelAttribute="messageForm">
                <div class="col-xs-12 col-sm-6">
                    <spring:bind path="name">
                        <div class="form-group ${status.error && not empty validationError?'has-error':''}">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </div>

                                <form:input path="name" class="form-control" type="text" placeholder="${contacFormNameLbl}" />
                            </div>
                            <c:if test="${status.error && not empty validationError}">
                                <form:errors path="name" class="text-danger"/>
                            </c:if>
                        </div>
                    </spring:bind>
                    
                    <spring:bind path="email">
                        <div class="form-group ${status.error && not empty validationError?'has-error':''}">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    @
                                </div>

                                <form:input path="email" class="form-control" type="email" placeholder="${contacFormEmailLbl}" />
                            </div>
                            <c:if test="${status.error && not empty validationError}">
                                <form:errors path="email" class="text-danger"/>
                            </c:if>
                        </div>
                    </spring:bind>
                    
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="glyphicon glyphicon-flag"></i>
                            </div>

                            <form:select path="subject" class="form-control">
                                    <option value="NONE"> ${contacFormSubjectLbl}  </option>
                                <c:forEach items="${subjectMessage}" var="item">
                                    <option value="${item.value}"> ${item.name}  </option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                </div>
                
                <div class="col-xs-12 col-sm-6">
                    <spring:bind path="content">
                        <div class="form-group ${status.error && not empty validationError?'has-error':''}">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                </div>

                                <form:textarea path="content" class="form-control testarea-height" placeholder="${contacFormMessageLbl}"></form:textarea>
                            </div>
                            <c:if test="${status.error && not empty validationError}">
                                <form:errors path="content" class="text-danger"/>
                            </c:if>
                        </div>
                    </spring:bind>
                </div>
                <div class="col-xs-12 form-actions">
                    <button type="submit" class="btn btn-primary pull-right"> ${contacFormSubmitLbl} </button>
                </div>
            </form:form>
        </div>
    </div>
        
    <jsp:include page="./fragments/footer.jsp"/>
    
    </body>
</html>
