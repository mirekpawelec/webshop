<%-- 
    Document   : deliveries
    Created on : 2017-09-28, 22:31:57
    Author     : mirek
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

    <jsp:include page="./fragments/header.jsp"/>      
        
    <section class="main">

        <jsp:include page="./fragments/navi.jsp"/>

        <div class="container">

            <spring:message code="deliveries.pageHeader.label" var="headerInfo"/>

            <div class="row">
                <div class="page-header">
                    <h2> ${headerInfo} </h2>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="table-responsive text-left">
                    <table id="sortTable" class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th> <spring:message code="deliveries.table.deliveryId.label" /> </th>
                                <th> <spring:message code="deliveries.table.placeNo.label" /> </th>
                                <th> <spring:message code="deliveries.table.driverFirstName.label" /> </th>
                                <th> <spring:message code="deliveries.table.driverLastName.label" /> </th>
                                <th> <spring:message code="deliveries.table.driverPhoneNo.label" /> </th>
                                <th> <spring:message code="deliveries.table.truckType.label" /> </th>
                                <th> <spring:message code="deliveries.table.truckNumber.label" /> </th>
                                <th> <spring:message code="deliveries.table.trailerOrCaravanNumber.label" /> </th>
                                <!--<th> <spring :message code="deliveries.table.createUser.label" /> </th>-->
                                <th> <spring:message code="deliveries.table.status.label" /> </th>
                                <th> <spring:message code="deliveries.table.countItems.label" /> </th> 
                                <th> <spring:message code="deliveries.table.createDate.label" /> </th>
                                <th> <spring:message code="deliveries.table.finishDate.label" /> </th>
                                <th> </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${deliveries}">
                                <tr>
                                    <td>${item.deliveryId}</td>
                                    <td>${item.place.placeNo}</td>
                                    <td>${item.driverFirstName}</td>
                                    <td>${item.driverLastName}</td>
                                    <td>${item.driverPhoneNo}</td>
                                    <td>${item.truckType}</td>
                                    <td>${item.truckNumber}</td>
                                    <td>${item.trailerOrCaravanNumber}</td>
                                    <!--<td>$ {item.createUser}</td>-->
                                    <td>${item.status}</td>
                                    <td>${fn:length(item.deliveryItemSet)}</td>
                                    <td>${item.createDate}</td>
                                    <td>${item.finishDate}</td>
                                    <td>
                                        <spring:url value="/admin/delivery?deliveryId=${item.deliveryId}" var="deliveryUrl" />                                        
                                        <spring:message code="deliveries.button.selectDelivery.label" var="selectLbl" /> 
                                        <a href="${deliveryUrl}" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-check"> </span> ${selectLbl} </a>
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
    
    </body>
</html>
