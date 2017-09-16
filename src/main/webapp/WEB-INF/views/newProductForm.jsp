<%-- 
    Document   : product
    Created on : 2017-09-05, 19:45:24
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

    <jsp:include page="./fragments/header.jsp" /> 

        <section class="main">
        
            <div class="container text-left">
                <div class="row">
                    <div class="page-header">
                        <h2> 
                            <spring:message code="newProductForm.pageHeader.label"/>
                            <%--<spring:message code="updateProductForm.pageHeader.update.label" arguments="${productForm.productNo}"/>--%>
                        </h2>
                    </div>
                </div>
                        
                <form:form modelAttribute="newProductForm" class="form-horizontal" enctype="multipart/form-data">
                    <fildset>
                        <div class="row text-danger">
                            <form:errors path="*" class="alert alert-danger" element="div" />
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label for="productNo" class="col-xs-12 col-sm-3 control-label"> <spring:message code="newProductForm.form.productNo.label"/> </label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="text" id="productNo" path="productNo" class="form-control" />
                                    <form:errors path="productNo" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="form-group">
                                <label for="productName" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.name.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="text" id="productName" path="name" class="form-control"/>
                                    <form:errors path="name" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="manufacturer" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.manufacturer.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="text" id="manufacturer" path="manufacturer" class="form-control" />
                                    <form:errors path="manufacturer" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="category" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.category.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="text" id="category" path="category" class="form-control"/>
                                    <form:errors path="category" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="description" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.description.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:textarea id="description" path="description" rows="5" class="form-control"/>
                                    <form:errors path="description" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="unitPrice" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.unitPrice.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="number" step="any" id="unitPrice" path="unitPrice" class="form-control"/>
                                    <form:errors path="unitPrice" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="quantityInBox" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.quantityInBox.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="number" id="quantityInBox" path="quantityInBox" class="form-control"/>
                                    <form:errors path="quantityInBox" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="status" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.status.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:select path="status" items="${productStatus}" itemValue="productStatusType" itemLabel="productStatusDescription" class="form-control"/>
                                    <form:errors path="status" class="text-danger"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="productImage" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.productImage.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <div class="row">
                                        <form:input type="file" id="productImage" path="productImage"/>
                                        <form:errors path="productImage" class="text-danger"/>
                                    </div>
                                    <div class="row help-block pull-left">
                                        <spring:message code="newProductForm.form.productImageTextInfo.label"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="productUserManual" class="col-xs-12 col-sm-3 control-label"><spring:message code="newProductForm.form.productUserManual.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <div class="row">
                                        <form:input type="file" id="productUserManual" path="productUserManual"/>
                                        <form:errors path="productUserManual" class="text-danger"/>
                                    </div>
                                    <div class="row help-block pull-left">
                                        <spring:message code="newProductForm.form.productUserManualTextInfo.label"/>
                                    </div>
                                </div>
                            </div>
                        </div>        
                        
                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-12 col-sm-3"></div>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <spring:message code="newProductForm.form.button.label" var="buttonForm" />
                                    <input type="submit" value="${buttonForm}" class="btn btn-primary btn-lg pull-right"/>
                                </div>
                            </div>
                        </div>
         
                    </fildset>
                </form:form>
            </div>
        </section>
    
    <jsp:include page="./fragments/footer.jsp"/>
    
    </body>
</html>
