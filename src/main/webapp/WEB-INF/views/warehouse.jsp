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

<!DOCTYPE html>
<html>
    <jsp:include page="./fragments/header.jsp" />
        
        <section class="main">
            
            <jsp:include page="./fragments/navi.jsp"/>
            
            <spring:message code="warehouse.pageHeader.label" var="headerLbl" />
            <spring:message code="warehouse.table.loadunitNo.label" var="loadunitNoLbl" />
            <spring:message code="warehouse.table.productNo.label" var="productNoLbl" />
            <spring:message code="warehouse.table.manufacturer.label" var="manufacturerLbl" />
            <spring:message code="warehouse.table.productName.label" var="productNameLbl" />
            <spring:message code="warehouse.table.quantity.label" var="quantityLbl" />
            <spring:message code="warehouse.table.placeNo.label" var="placeIdLbl" />
            <spring:message code="warehouse.table.conditions.label" var="conditionsLbl" />
            <spring:message code="warehouse.table.qualityStatus.label" var="qualityStatusLbl" />
            <spring:message code="warehouse.table.status.label" var="statusLbl" />
            <spring:message code="warehouse.table.lastModifikationDate.label" var="lastModifikationDateLbl" />
            <spring:message code="warehouse.table.createDate.label" var="createDateLbl" />
            
            <div class="container">
                <div class="row">
                    <div class="page-header">
                        <h2> ${headerLbl} </h2>
                    </div>
                </div>
                <div class="row">
                        
                </div>
                <div class="row">                    
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <tr>
                                <th>${loadunitNoLbl}</th>
                                <th>${productNoLbl}</th>
                                <th>${manufacturerLbl}</th>
                                <th>${productNameLbl}</th>
                                <th>${quantityLbl}</th>
                                <th>${placeIdLbl}</th>
                                <th>${conditionsLbl}</th>
                                <th>${qualityStatusLbl}</th>
                                <th>${statusLbl}</th>
                                <th>${lastModifikationDateLbl}</th>
                                <th>${createDateLbl}</th>
                            </tr>
                            <c:forEach items="${wholeStock}" var="loadunit">
                                <tr>
                                    <td>${loadunit.loadunitNo}</td>
                                    <td>${loadunit.productId.productNo}</td>
                                    <td>${loadunit.productId.manufacturer}</td>
                                    <td>${loadunit.productId.name}</td>
                                    <td>${loadunit.quantity}</td>
                                    <td>${loadunit.placeId.placeNo}</td>
                                    <td>${loadunit.conditions}</td>
                                    <td>${loadunit.qualityStatus}</td>
                                    <td>${loadunit.status}</td>                              
                                         <c:set var="cleanedDateTime" value="${fn:replace(loadunit.lastModifikationDate, 'T' , ' ')}" />
                                         <fmt:parseDate value="${cleanedDateTime}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both" />
                                    <td> <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}" /> </td>
                                         <c:set var="cleanedDateTime" value="${fn:replace(loadunit.createDate, 'T' , ' ')}" />
                                         <fmt:parseDate value="${cleanedDateTime}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDateTime" type="both" />
                                    <td> <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedDateTime}" /> </td>
                                    
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
    
        </section>
    
    <jsp:include page="./fragments/footer.jsp" />
            
    </body>
</html>
