<%-- 
    Document   : product
    Created on : 2017-09-05, 19:45:24
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/admin/products/update" var="updateUrl" />
<spring:url value="/admin/products" var="toArticlesUrl"/>

<spring:message code="updateProductForm.pageHeader.label" arguments="${sessionScope.productNumber}" var="headerLbl"/>
<spring:message code="updateProductForm.form.button.submit.label" var="buttonLbl" />
<spring:message code="updateProductForm.form.link.toArticles.label" var="toArticlesLbl"/>
<spring:message code="updateProductForm.form.infoValidationError.label" var="infoValidationErrorLbl"/>
<spring:message code="updateProductForm.form.name.label" var="nameTabLbl"/>
<spring:message code="updateProductForm.form.manufacturer.label" var="manufacturerTabLbl"/>
<spring:message code="updateProductForm.form.category.label" var="categoryTabLbl"/>
<spring:message code="updateProductForm.form.description.label" var="discriptionTabLbl"/>
<spring:message code="updateProductForm.form.unitPrice.label" var="priceTabLbl"/> 
<spring:message code="updateProductForm.form.quantityInBox.label" var="quantityTabLbl"/>
<spring:message code="updateProductForm.form.promotion.label" var="promotionLbl"/>   
<spring:message code="updateProductForm.form.select.promotion.yes.message" var="promotionYesMsg"/>
<spring:message code="updateProductForm.form.select.promotion.no.message" var="promotionNoMsg"/>
<spring:message code="updateProductForm.form.status.label" var="statusTabLbl" />


    <jsp:include page="./fragments/header.jsp" /> 

        <section class="main">         
            <jsp:include page="./fragments/navi.jsp"/>
            <hr>
            <div class="container text-left">
                <div class="row">
                    <div class="page-header text-center">
                        <h2> ${headerLbl} </h2>
                    </div>
                </div>
               
                <form:form modelAttribute="updateProductForm" method="post" action="${updateUrl}" class="form-horizontal" enctype="multipart/form-data">
                    <fildset>
                        <spring:bind path="*">
                            <c:if test="${status.error}">
                                <div class="alert alert-danger alert-dismissible text-center" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4> ${infoValidationErrorLbl} </h4>
                                </div>
                            </c:if>
                        </spring:bind>

                        <spring:bind path="name">
                            <div class="row">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <label for="productName" class="col-xs-12 col-sm-3 control-label"> ${nameTabLbl} </label>
                                    <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                        <form:input type="text" id="productName" path="name" class="form-control"/>
                                        <form:errors path="name" class="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="manufacturer">  
                            <div class="row">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <label for="manufacturer" class="col-xs-12 col-sm-3 control-label"> ${manufacturerTabLbl}</label>
                                    <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                        <form:input type="text" id="manufacturer" path="manufacturer" class="form-control" />
                                        <form:errors path="manufacturer" class="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="category">   
                            <div class="row">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <label for="category" class="col-xs-12 col-sm-3 control-label"> ${categoryTabLbl} </label>
                                    <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                        <form:input type="text" id="category" path="category" class="form-control"/>
                                        <form:errors path="category" class="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="description">    
                            <div class="row">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <label for="description" class="col-xs-12 col-sm-3 control-label"> ${discriptionTabLbl} </label>
                                    <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                        <form:textarea id="description" path="description" rows="5" class="form-control"/>
                                        <form:errors path="description" class="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="unitPrice">   
                            <div class="row">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <label for="unitPrice" class="col-xs-12 col-sm-3 control-label"> ${priceTabLbl} </label>
                                    <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                        <div class="input-group">
                                            <form:input type="number" step="any" id="unitPrice" path="unitPrice" class="form-control"/>
                                            <span class="input-group-addon">PLN</span>
                                        </div>
                                        <form:errors path="unitPrice" class="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="quantityInBox">
                            <div class="row">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <label for="quantityInBox" class="col-xs-12 col-sm-3 control-label"> ${quantityTabLbl}</label>
                                    <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                        <form:input type="number" id="quantityInBox" path="quantityInBox" class="form-control"/>
                                        <form:errors path="quantityInBox" class="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </spring:bind>
   
                        <div class="row">
                            <div class="form-group">
                                <spring:bind path="discount">
                                    <c:if test="${status.error}"> <c:set value="${true}" var="isDiscountError"/> </c:if>
                                </spring:bind>
                                
                                <div class="col-xs-3 ${isDiscountError?'has-error':''} text-right">
                                    <label for="promotion" class="control-label"> ${promotionLbl}</label>
                                </div>
                                
                                <div class="col-xs-2">
                                    <form:select id="promotion" path="promotion" class="form-control">
                                        <option value="N" ${updateProductForm.promotion=='N' || updateProductForm.promotion==null?'selected':''}> ${promotionNoMsg} </option>
                                        <option value="T" ${updateProductForm.promotion=='T'?'selected':''}> ${promotionYesMsg} </option>
                                    </form:select>
                                    <form:errors path="promotion" class="text-danger"/>
                                </div>
                                
                                <div class="${isDiscountError?'has-error':''}">
                                    <div class="col-xs-6 col-sm-6 col-md-5 col-lg-5">
                                        <div class="input-group">
                                            <form:input type="number" id="discount" path="discount" class="form-control"/>
                                            <span class="input-group-addon">%</span>
                                        </div>
                                        <form:errors path="discount" class="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <spring:bind path="status">
                            <div class="row">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <label for="status" class="col-xs-12 col-sm-3 control-label"> ${statusTabLbl} </label>
                                    <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                        <form:select path="status" items="${productStatus}" itemValue="name" itemLabel="description" class="form-control"/>
                                        <form:errors path="status" class="text-danger"/>
                                    </div>
                                </div>
                            </div> 
                        </spring:bind>
                        <hr> 
                        
                        <div class="row">
                            <div class="col-xs-11 col-sm-12 col-md-11 col-lg-10 text-right">
                                <a href="${toArticlesUrl}" class="btn btn-default"><span class="glyphicon glyphicon-hand-left"></span> ${toArticlesLbl}</a>
                                <button type="submit" class="btn btn-primary"> ${buttonLbl} </button>
                            </div>
                        </div>
         
                    </fildset>
                </form:form>
            </div>
        </section>
    
    <jsp:include page="./fragments/footer.jsp"/>
    
    </body>
</html>
