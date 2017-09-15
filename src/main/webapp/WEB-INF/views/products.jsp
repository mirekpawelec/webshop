<%-- 
    Document   : products
    Created on : 2017-09-05, 19:31:57
    Author     : mirek
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="pl">

    <jsp:include page="./fragments/header.jsp"/>
        <br/>
        <spring:url value="/admin/products/add" var="addNewProductUrl"/>
        <button class="btn btn-default" onclick="location.href='${addNewProductUrl}'"> <spring:message code="products.button.newProduct.label"/> </button> 
        
        <section class="main">
            <div class="container">
                <div class="row">
                    <div class="page-header">
                        <h2> 
                            <spring:message code="products.pageHeaderPart1.label"/> 
                                <small> <spring:message code="products.pageHeaderPart2.label"/></small> 
                        </h2>
                    </div>
                </div>
                <div class="row">
                    <c:if test="${success}">
                        <div class="alert alert-${css} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> <spring:message code="products.table.processResult.message" arguments="${deletedProductNo}"/> </strong>
                        </div>                        
                    </c:if>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <tr>
                                <th> <spring:message code="products.table.productNo.label"/> </th>
                                <th> <spring:message code="products.table.name.label"/> </th>
                                <th> <spring:message code="products.table.manufacturer.label"/> </th>
                                <th> <spring:message code="products.table.category.label"/> </th>
                                <th> <spring:message code="products.table.price.label"/> </th>
                                <th> <spring:message code="products.table.status.label"/> </th>
                                <th> </th>
                            </tr>
                            <c:forEach var="item" items="${products}">
                                <tr>
                                    <td>${item.productNo}</td>
                                    <td>${item.name}</td>
                                    <td>${item.manufacturer}</td>
                                    <td>${item.category}</td>
                                    <td>${item.unitPrice}</td>
                                    <td>${item.status}</td>
                                    <td>
                                        <spring:url value="/admin/products/product?id=${item.productId}" var="productUrl"/>
                                        <spring:url value="/admin/products/${item.productId}/modify" var="updateUrl"/>
                                        <spring:url value="/admin/products/param;id=${item.productId};productNo=${item.productNo}/delete" var="deleteUrl"/> 

                                        <button class="btn btn-info btn-xs" onclick="location.href='${productUrl}'"> 
                                            <spring:message code="products.button.selectProduct.label"/> 
                                        </button>
                                        <button class="btn btn-warning btn-xs" onclick="location.href='${updateUrl}'">
                                            <spring:message code="products.button.updateProduct.label"/>
                                        </button>
                                            <button class="btn btn-danger btn-xs" onclick="location.href='${deleteUrl}'">
                                            <spring:message code="products.button.deleteProduct.label"/>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </section>

    <jsp:include page="./fragments/footer.jsp"/>
    
    </body>
</html>
