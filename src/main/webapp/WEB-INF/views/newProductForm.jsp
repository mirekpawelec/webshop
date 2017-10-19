<%-- 
    Document   : product
    Created on : 2017-09-05, 19:45:24
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/admin/products" var="productsUrl"/>
 
<spring:message code="newProductForm.pageHeader.label" var="headerLbl"/>
<spring:message code="newProductForm.form.infoValidationError.label" var="infoValidationErrorLbl"/>
<spring:message code="newProductForm.form.productNo.label" var="productNoLbl"/>
<spring:message code="newProductForm.form.name.label" var="nameLbl"/>
<spring:message code="newProductForm.form.manufacturer.label" var="manufacturerLbl"/>
<spring:message code="newProductForm.form.category.label" var="categoryLbl"/>
<spring:message code="newProductForm.form.description.label" var="descriptionLbl"/>
<spring:message code="newProductForm.form.unitPrice.label" var="unitPriceLbl"/>
<spring:message code="newProductForm.form.quantityInBox.label" var="quantityInBoxLbl"/>
<spring:message code="newProductForm.form.status.label" var="statusLbl"/>
<spring:message code="newProductForm.form.productImage.label" var="productImageLbl"/>
<spring:message code="newProductForm.form.productUserManual.label" var="productUserManualLbl"/>
<spring:message code="newProductForm.form.productImageTextInfo.label" var="productImageTextInfoLbl"/>
<spring:message code="newProductForm.form.productUserManualTextInfo.label" var="productUserManualTextInfoLbl"/>
<spring:message code="newProductForm.linkToProducts.button.label" var="backToProducts"/>
<spring:message code="newProductForm.form.submitForm.button.label" var="submitFormLbl" />
 
 
    <jsp:include page="./fragments/header.jsp" /> 

        <section class="main">
            <jsp:include page="./fragments/navi.jsp"/>
            <hr>
            <div class="container">
                <div class="row">
                    <div class="page-header text-center">
                        <h2> ${headerLbl} </h2>
                    </div>
                </div>
                        
                <form:form modelAttribute="newProductForm" class="form-horizontal" enctype="multipart/form-data">
                    <fildset>
                        <spring:bind path="*">
                            <c:if test="${status.error}">
                                <div class="alert alert-danger alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4> ${infoValidationErrorLbl} </h4>
                                </div>
                            </c:if>
                        </spring:bind>
                        
                        <div class="row">
                            <spring:bind path="productNo">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="productNo" class="control-label"> ${productNoLbl} </label>
                                    </div>
                                    <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                        <form:input type="text" id="productNo" path="productNo" class="form-control" />
                                        <form:errors path="productNo" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="name">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="productName" class="control-label"> ${nameLbl} </label>
                                    </div>
                                    <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                        <form:input type="text" id="productName" path="name" class="form-control"/>
                                        <form:errors path="name" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <spring:bind path="manufacturer">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="manufacturer" class="control-label"> ${manufacturerLbl} </label>
                                    </div>
                                    <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                        <form:input type="text" id="manufacturer" path="manufacturer" class="form-control" />
                                        <form:errors path="manufacturer" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <spring:bind path="category">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="category" class="control-label"> ${categoryLbl} </label>
                                    </div>
                                    <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                        <form:input type="text" id="category" path="category" class="form-control"/>
                                        <form:errors path="category" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <spring:bind path="description">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="description" class="control-label"> ${descriptionLbl} </label>
                                    </div>
                                    <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                        <form:textarea id="description" path="description" rows="3" class="form-control"/>
                                        <form:errors path="description" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <spring:bind path="unitPrice">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="unitPrice" class="control-label"> ${unitPriceLbl} </label>
                                    </div>
                                    <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                        <form:input type="number" step="any" id="unitPrice" path="unitPrice" class="form-control"/>
                                        <form:errors path="unitPrice" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <spring:bind path="quantityInBox">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="quantityInBox" class="control-label"> ${quantityInBoxLbl} </label>
                                    </div>
                                    <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                        <form:input type="number" id="quantityInBox" path="quantityInBox" class="form-control"/>
                                        <form:errors path="quantityInBox" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                                
                        <div class="row">
                            <spring:bind path="status">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="status" class="control-label"> ${statusLbl}</label>
                                    </div>
                                    <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                        <form:select path="status" items="${productStatus}" itemValue="name" itemLabel="description" class="form-control"/>
                                        <form:errors path="status" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                    <label for="productImage" class="control-label"> ${productImageLbl} </label>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                    <div class="row">
                                        <form:input type="file" id="productImage" path="productImage"/>
                                        <form:errors path="productImage" class="text-danger"/>
                                    </div>
                                    <div class="row help-block pull-left">
                                        ${productImageTextInfoLbl}
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                    <label for="productUserManual" class="control-label"> ${productUserManualLbl} </label>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                    <div class="row">
                                        <form:input type="file" id="productUserManual" path="productUserManual"/>
                                        <form:errors path="productUserManual" class="text-danger"/>
                                    </div>
                                    <div class="row help-block pull-left">
                                        ${productUserManualTextInfoLbl}
                                    </div>
                                </div>
                            </div>
                        </div>                            
                        <hr>
                        
                        <div class="row">
                            <div class="col-xs-11 col-sm-11 col-md-10 col-lg-10 text-right">
                                <a href="${productsUrl}" class="btn btn-default"><span class="glyphicon glyphicon-hand-left"></span> ${backToProducts}</a>
                                <button type="submit" class="btn btn-primary"> ${submitFormLbl} </button>
                            </div>
                        </div>
                    </fildset>
                </form:form>
            </div>
        </section>
    
    <jsp:include page="./fragments/footer.jsp"/>
    
    </body>
</html>
