<%-- 
    Document   : products
    Created on : 2017-09-05, 19:31:57
    Author     : mirek
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="products.pageHeaderPart1.label" var="headerPart1Lbl"/> 
<spring:message code="products.pageHeaderPart2.label" var="headerPart2Lbl"/> 
<spring:message code="products.table.processResult.message" arguments="${deletedProductNo}" var="processResultMsg"/>
<spring:message code="products.table.productNo.label" var="tabProductNoLbl"/>
<spring:message code="products.table.name.label" var="tabNameLbl"/>
<spring:message code="products.table.manufacturer.label" var="tabManufacturerLbl"/>
<spring:message code="products.table.category.label" var="tabCategoryLbl"/>
<spring:message code="products.table.price.label" var="tabPriceLbl"/>
<spring:message code="products.table.currency.label" var="currencyLbl"/>
<spring:message code="products.table.promotion.label" var="promotionLbl"/> 
<spring:message code="products.table.promotion.yes.message" var="promotionYesMsg"/>
<spring:message code="products.table.promotion.no.message" var="promotionNoMsg"/>
<spring:message code="products.table.discount.label" var="discountLbl"/>
<spring:message code="products.table.status.label" var="tabStatusLbl"/>
<spring:message code="products.table.createDate.label" var="tabCreateDateLbl"/>
<spring:message code="products.table.action.label" var="tabActionLbl"/>
<spring:message code="products.button.selectProduct.label" var="showTabBtnLbl"/>
<spring:message code="products.button.updateProduct.label" var="updateTabBtnLbl"/>
<spring:message code="products.button.deleteProduct.label" var="deleteTabBtnLbl"/>


    <jsp:include page="./fragments/header.jsp"/>      
        
        <section class="main">      
            <jsp:include page="./fragments/navi.jsp"/>
            <hr>
            <div class="container">
                <div class="row">
                    <div class="page-header">
                        <h2> ${headerPart1Lbl} <small> ${headerPart2Lbl} </small> </h2>
                    </div>
                </div>
                <div class="row">
                    <c:if test="${success}">
                        <div class="alert alert-${css} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${processResultMsg} </strong>
                        </div>                        
                    </c:if>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="table-responsive text-left">
                        <table id="sortTable" class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th> # </th>
                                    <th> ${tabProductNoLbl} </th>
                                    <th> ${tabNameLbl} </th>
                                    <th> ${tabManufacturerLbl} </th>
                                    <th> ${tabCategoryLbl} </th>
                                    <th> ${tabPriceLbl} </th>
                                    <th> ${promotionLbl} </th>
                                    <th> ${discountLbl} </th>
                                    <th> ${tabStatusLbl} </th>
                                    <th> ${tabCreateDateLbl} </th>
                                    <th> ${tabActionLbl} </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${products}" varStatus="counter">
                                    <tr>
                                        <td> ${counter.count} </td>
                                        <td> ${product.productNo} </td>
                                        <td> ${product.name} </td>
                                        <td> ${product.manufacturer} </td>
                                        <td> ${product.category} </td>
                                        <td> ${product.unitPrice} </td>
                                        <td> 
                                            <c:choose>
                                                <c:when test="${product.promotion=='T'}">
                                                    ${promotionYesMsg}
                                                </c:when>
                                                <c:otherwise>
                                                    ${promotionNoMsg}
                                                </c:otherwise>
                                            </c:choose> 
                                        </td>
                                        <td> ${product.discount} </td>
                                        <td> ${product.status} </td>
                                             <%--<c:set var="createDate" value="${fn:replace(product.createDate, 'T' , ' ')}" />--%>
                                             <%--<fmt:parseDate value="${createDate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both" />--%>
                                        <!--<td> <fmt :formatDate pattern="dd.MM.yyyy HH:mm:ss" value="$ {parsedDateTime}" /> </td>-->
                                        <td>${fn:replace(product.createDate, 'T' , ' ')}</td>
                                        <td>
                                            <a href="#product${product.productId}" data-toggle="modal" class="btn btn-primary btn-xs">
                                                <span class="glyphicon glyphicon-check"> </span> ${showTabBtnLbl}
                                            </a>
                                            <spring:url value="/admin/products/${product.productId}/update" var="updateUrl"/>
                                            <a href="${updateUrl}" class="btn btn-primary btn-xs">
                                                <span class="glyphicon glyphicon-edit"> </span> ${updateTabBtnLbl}
                                            </a>
                                            <a href="#delete${product.productId}" data-toggle="modal" class="btn btn-danger btn-xs"> 
                                                <span class="glyphicon glyphicon-remove"> </span> ${deleteTabBtnLbl} 
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>

    <jsp:include page="./fragments/footer.jsp"/>
    
    
    <spring:message code="products.modal.product.close.button.label" var="closeProductModalBtnLbl"/>
    <spring:message code="products.modal.deleteItem.header.label" var="headerDeleteModalLbl" />
    <spring:message code="products.modal.deleteItem.question.part1.message" var="questionDeleteModalMsg_1" />
    <spring:message code="products.modal.deleteItem.question.part2.message" var="questionDeleteModalMsg_2" />
    <spring:message code="products.modal.deleteItem.confirmYes.label" var="confirmYesDeleteModalLbl" />
    <spring:message code="products.modal.deleteItem.confirmNo.label" var="confirmNoDeleteModalLbl" />
    
    <c:forEach var="item" items="${products}">
        
        <div id="product${item.productId}" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content modal-lg">
                <div class="modal-header text-center">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h2> ${item.manufacturer} <small>${item.name}</small> </h2>
                </div>
                <div class="modal-body">
                    <jsp:include page="fragments/showProduct.jsp">
                        <jsp:param name="productNo" value="${item.productNo}"/>
                        <jsp:param name="name" value="${item.name}"/>
                        <jsp:param name="manufacturer" value="${item.manufacturer}"/>
                        <jsp:param name="category" value="${item.category}"/>
                        <jsp:param name="description" value="${item.description}"/>
                        <jsp:param name="unitPrice" value="${item.unitPrice}"/>
                        <jsp:param name="promotion" value="${item.promotion}"/>
                        <jsp:param name="discount" value="${item.discount}"/>
                        <jsp:param name="quantityInBox" value="${item.quantityInBox}"/> 
                        <jsp:param name="status" value="${item.status}"/> 
                        <jsp:param name="createDate" value="${fn:replace(item.createDate, 'T' , ' ')}"/>
                    </jsp:include>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" > ${closeProductModalBtnLbl} </button>
                </div>
              </div>
            </div>
        </div>

        <div id="delete${item.productId}" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4><strong> ${headerDeleteModalLbl} </strong></h4>
                </div>
                <div class="modal-body">
                    <h4> ${item.manufacturer} ${item.name} [${item.productNo}] </h4> <br>
                    <p> ${questionDeleteModalMsg_1} </p>
                    <p> ${questionDeleteModalMsg_2} </p>
                </div>
                <div class="modal-footer">
                        <spring:url value="/admin/products/param;id=${item.productId};productNo=${item.productNo}/delete" var="deleteItemUrl"/>  
                        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" > ${confirmNoDeleteModalLbl} </button>
                        <a href="${deleteItemUrl}" class="btn btn-danger btn-sm"> ${confirmYesDeleteModalLbl} </a>
                </div>
              </div>
            </div>
        </div>
                
    </c:forEach>

    </body>
</html>
