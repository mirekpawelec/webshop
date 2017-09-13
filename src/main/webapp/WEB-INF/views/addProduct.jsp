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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add product</title>
        <link href="<spring:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet" />
        <style>
            footer{
                font-size: 17px;
                padding: 25px 0;
                margin: 10px 0;
                text-align: center;
                color: #FFFFFF;
                background-color: #000000;
            }
        </style>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-inverse navbar-static-top">
                <div class="container">
                    <div class="navbar-header">
                        <a href="#" class="navbar-brand">Web Shop</a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="<c:url value="/products"/>">All Products</a></li>
                        <li><a href="#">Add Products</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                </div>
            </nav>
        </header>

        <section class="container">
            <div class="row text-center">
                <div class="page-header">
                    <h2>Dodawanie nowego produktu:</h2>
                </div>
            </div>
            
            <form:form modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
                <fildset>
                    <div class="row text-danger">
                        <form:errors path="*" class="alert alert-danger" element="div" />
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="productNo" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.productNo.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:input type="text" id="productNo" path="productNo" class="form-control" />
                                <form:errors path="productNo" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="productName" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.name.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:input type="text" id="productName" path="name" class="form-control"/>
                                <form:errors path="name" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="manufacturer" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.manufacturer.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:input type="text" id="manufacturer" path="manufacturer" class="form-control" />
                                <form:errors path="manufacturer" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="category" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.category.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:input type="text" id="category" path="category" class="form-control"/>
                                <form:errors path="category" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="description" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.description.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:textarea id="description" path="description" rows="5" class="form-control"/>
                                <form:errors path="description" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="unitPrice" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.unitPrice.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:input type="number" step="any" id="unitPrice" path="unitPrice" class="form-control"/>
                                <form:errors path="unitPrice" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="quantityInBox" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.quantityInBox.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:input type="number" id="quantityInBox" path="quantityInBox" class="form-control"/>
                                <form:errors path="quantityInBox" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="status" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.status.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:select path="status" items="${productStatus}" itemValue="productStatusType" itemLabel="productStatusDescription" class="form-control"/>
                                <form:errors path="status" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="productImage" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.productImage.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:input type="file" id="productImage" path="productImage"/>
                                <p class="help-block"><spring:message code="addProduct.form.productImageTextInfo.label"/></p>
                                <form:errors path="productImage" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="productUserManual" class="col-xs-12 col-sm-3 control-label"><spring:message code="addProduct.form.productUserManual.label"/></label>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <form:input type="file" id="productUserManual" path="productUserManual"/>
                                <p class="help-block"><spring:message code="addProduct.form.productUserManualTextInfo.label"/></p>
                                <form:errors path="productUserManual" class="text-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-xs-12 col-sm-3"></div>
                            <div class="col-xs-12 col-sm-9 col-md-8 col-lg-7">
                                <input type="submit" value="Dodaj" class="btn btn-primary btn-lg pull-right"/>
                            </div>
                        </div>
                    </div>
                    
                </fildset>
            </form:form>
        </section>
        <hr />
        <footer class="container-fluid">
            &copy; Copyright 2017 by Miro
        </footer>
    </body>
</html>
