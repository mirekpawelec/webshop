<%-- 
    Document   : warehouse.jsp
    Created on : 2017-09-24, 18:30:11
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="warehouse.pageHeader.label" var="headerLbl" />
<spring:message code="warehouse.table.loadunitNo.label" var="loadunitNoLbl" />
<spring:message code="warehouse.table.productNo.label" var="productNoLbl" />
<spring:message code="warehouse.table.manufacturer.label" var="manufacturerLbl" />
<spring:message code="warehouse.table.productName.label" var="productNameLbl" />
<spring:message code="warehouse.table.quantity.label" var="quantityLbl" />
<spring:message code="warehouse.table.placeNo.label" var="placeIdLbl" />
<spring:message code="warehouse.table.state.label" var="stateLbl" />
<spring:message code="warehouse.table.qualityStatus.label" var="qualityStatusLbl" />
<spring:message code="warehouse.table.status.label" var="statusLbl" />
<spring:message code="warehouse.table.lastModificationDate.label" var="lastModifikationDateLbl" />
<spring:message code="warehouse.table.createDate.label" var="createDateLbl" />
<spring:message code="warehouse.table.noDataFound.message" var="noDataFoundMsg"/>

    <jsp:include page="./fragments/header.jsp" />
        
    <section class="main"> 

        <jsp:include page="./fragments/navi.jsp"/>

        <div class="container-fluid">
            <div class="row" style="min-width: 400px">
                <div class="page-header">
                    <h2> ${headerLbl} </h2>
                </div>
    
                <form method="post" action="<c:url value="/admin/repository"/>" >
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="loadunitNo">${loadunitNoLbl}</label>
                        <input class="form-control" id="loadunitNo" type="text" name="loadunitNo" value="${loadunitNo}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="productNo">${productNoLbl}</label>
                        <input class="form-control" id="productNo" type="text" name="productPLUSproductNo" value="${productPLUSproductNo}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="manufacturer">${manufacturerLbl}</label>
                        <input class="form-control" id="manufacturer" type="text" name="productPLUSmanufacturer" value="${productPLUSmanufacturer}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="name">${productNameLbl}</label>
                        <input class="form-control" id="name" type="text" name="productPLUSname" value="${productPLUSname}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="quantity">${quantityLbl}</label>
                        <input class="form-control" id="quantity" type="number" name="quantity" value="${quantity}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="place">${placeIdLbl}</label>
                        <input class="form-control" id="place" type="text" name="placePLUSplaceNo" value="${placePLUSplaceNo}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="state">${stateLbl}</label>
                        <input class="form-control" id="state" type="text" name="state" value="${state}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="quality">${qualityStatusLbl}</label>
                        <input class="form-control" id="quality" type="number" name="qualityStatus" value="${qualityStatus}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="status">${statusLbl}</label>
                        <input class="form-control" id="status" type="text" name="status" value="${status}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="modificationDate">${lastModifikationDateLbl}</label>
                        <input class="form-control" id="modificationDate" type="text" name="lastModificationDate" value="${lastModificationDate}">
                    </div>
                    <div class="form-group-sm col-xs-6 col-sm-4 col-md-3 col-lg-2">
                        <label class="control-label" for="createDate">${createDateLbl}</label>
                        <input class="form-control" id="createDate" type="text" name="createDate" value="${createDate}">
                    </div>
                    <div class="form-group-sm col-xs-12 col-sm-4 col-md-3 col-lg-2">
                        <button class="btn btn-primary btn-block btn-sm" id="szukaj" type="submit">Szukaj</button>
                    </div>
                </form>
            </div>    
            <br>
                    
            <div class="row">                    
                <div class="table-responsive text-left">
                    <table id="sortTable" class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>${loadunitNoLbl}</th>
                                <th>${productNoLbl}</th>
                                <th>${manufacturerLbl}</th>
                                <th>${productNameLbl}</th>
                                <th>${quantityLbl}</th>
                                <th>${placeIdLbl}</th>
                                <th>${stateLbl}</th>
                                <th>${qualityStatusLbl}</th>
                                <th>${statusLbl}</th>
                                <th>${lastModifikationDateLbl}</th>
                                <th>${createDateLbl}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${wholeStock}" var="loadunit" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${loadunit.loadunitNo}</td>
                                    <td>${loadunit.product.productNo}</td>
                                    <td>${loadunit.product.manufacturer}</td>
                                    <td>${loadunit.product.name}</td>
                                    <td>${loadunit.quantity}</td>
                                    <td>${loadunit.place.placeNo}</td>
                                    <td>${loadunit.state}</td>
                                    <td>${loadunit.qualityStatus}</td>
                                    <td>${loadunit.status}</td>                              
                                         <%--<c:set var="cleanedDateTime" value="${fn:replace(loadunit.lastModificationDate, 'T' , ' ')}" />--%>
                                         <%--<fmt:parseDate value="${cleanedDateTime}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both" />--%>
                                    <!--<td> <fmt :formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}" /> </td>-->
                                    <td>${fn:replace(loadunit.lastModificationDate, 'T' , ' ')}</td>
                                         <%--<c:set var="cleanedDateTime" value="${fn:replace(loadunit.createDate, 'T' , ' ')}" />--%>
                                         <%--<fmt:parseDate value="$ {cleanedDateTime}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both" />--%>
                                    <!--<td> <fmt :formatDate pattern="dd.MM.yyyy HH:mm:ss" value="$ {parsedDateTime}" /> </td>-->
                                    <td>${fn:replace(loadunit.createDate, 'T' , ' ')}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>                            
                </div>
            </div>
        </div>
        
        <br><br><br>
                            
        <div class="container">
            <div class="row text-center">
                <c:if test="${not empty cssNoDataFound}">
                    <div class="alert alert-${cssNoDataFound} alert-dismissable" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong> ${noDataFoundMsg} </strong>
                    </div>                        
                </c:if>
            </div>
        </div>

    </section>
    
    <jsp:include page="./fragments/footer.jsp" />
            
    </body>
</html>
