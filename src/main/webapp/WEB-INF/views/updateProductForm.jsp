<%-- 
    Document   : product
    Created on : 2017-09-05, 19:45:24
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <jsp:include page="./fragments/header.jsp" /> 

        <section class="main">         
            <jsp:include page="./fragments/navi.jsp"/>
            <hr>
            <div class="container text-left">
                <div class="row">
                    <div class="page-header">
                        <h2> 
                            <spring:message code="updateProductForm.pageHeader.label" arguments="${sessionScope.productNumber}"/>
                        </h2>
                    </div>
                </div>
               
                <spring:url value="/admin/products/update" var="updateUrl" />
                 
                <form:form modelAttribute="updateProductForm" method="post" action="${updateUrl}" class="form-horizontal" enctype="multipart/form-data">
                    <fildset>
                        <div class="row text-danger">
                            <form:errors path="*" class="alert alert-danger" element="div" />
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="productName" class="col-xs-12 col-sm-3 control-label"><spring:message code="updateProductForm.form.name.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="text" id="productName" path="name" class="form-control"/>
                                    <form:errors path="name" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="manufacturer" class="col-xs-12 col-sm-3 control-label"><spring:message code="updateProductForm.form.manufacturer.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="text" id="manufacturer" path="manufacturer" class="form-control" />
                                    <form:errors path="manufacturer" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="category" class="col-xs-12 col-sm-3 control-label"><spring:message code="updateProductForm.form.category.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="text" id="category" path="category" class="form-control"/>
                                    <form:errors path="category" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="description" class="col-xs-12 col-sm-3 control-label"><spring:message code="updateProductForm.form.description.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:textarea id="description" path="description" rows="5" class="form-control"/>
                                    <form:errors path="description" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="unitPrice" class="col-xs-12 col-sm-3 control-label"><spring:message code="updateProductForm.form.unitPrice.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="number" step="any" id="unitPrice" path="unitPrice" class="form-control"/>
                                    <form:errors path="unitPrice" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="quantityInBox" class="col-xs-12 col-sm-3 control-label"><spring:message code="updateProductForm.form.quantityInBox.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:input type="number" id="quantityInBox" path="quantityInBox" class="form-control"/>
                                    <form:errors path="quantityInBox" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                                
                        <div class="row">
                            <div class="form-group">
                                <label for="status" class="col-xs-12 col-sm-3 control-label"><spring:message code="updateProductForm.form.status.label"/></label>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <form:select path="status" items="${productStatus}" itemValue="name" itemLabel="description" class="form-control"/>
                                    <form:errors path="status" class="text-danger"/>
                                </div>
                            </div>
                        </div>      
                        
                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-12 col-sm-3"></div>
                                <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                    <spring:message code="updateProductForm.form.button.label" var="buttonForm" />
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
