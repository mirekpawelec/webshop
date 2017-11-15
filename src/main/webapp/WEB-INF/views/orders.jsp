<%-- 
    Document   : orders
    Created on : 2017-11-11, 01:07:45
    Author     : mirek
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<spring:message code="orders.pageHeader.label" var="headerLbl" />
<spring:message code="orders.table.orderNo.label" var="orderNoTabLbl"/>
<spring:message code="orders.table.customerLogin.label" var="customerLoginTabLbl"/>
<spring:message code="orders.table.customerFirstName.label" var="customerFirstNameTabLbl"/>
<spring:message code="orders.table.customerLastName.label" var="customerLastNameTabLbl"/>
<spring:message code="orders.table.status.label" var="statusTabLbl"/>
<spring:message code="orders.table.status.info.edit.message" var="statusInfoEditMsg"/>
<spring:message code="orders.table.status.info.wait.message" var="statusInfoWaitMsg"/>
<spring:message code="orders.table.status.info.action.message" var="statusInfoActionMsg"/>
<spring:message code="orders.table.status.info.realization.message" var="statusInfoRealizationMsg"/>
<spring:message code="orders.table.status.info.canceled.message" var="statusInfoCanceledMsg"/>
<spring:message code="orders.table.status.info.completed.message" var="statusInfoCompletedMsg"/>
<spring:message code="orders.table.status.info.undefined.message" var="statusInfoUndefinedMsg"/>
<spring:message code="orders.table.lastModificationDate.label" var="lastModificationDateTabLbl"/>
<spring:message code="orders.table.createDate.label" var="createDateTabLbl"/>
<spring:message code="orders.table.action.label" var="actionTabLbl"/>
<spring:message code="orders.table.show.button.label" var="showTabBtnLbl"/>
<spring:message code="orders.table.active.button.label" var="activeTabBtnLbl"/>


    <jsp:include page="./fragments/header.jsp"/>
    
    <section class="main">
        
        <jsp:include page="./fragments/navi.jsp"/>
        
        <div class="container">
            <div class="row">
                <div class="page-header">
                    <h2> ${headerLbl} </h2>
                </div>
            </div>
            
            <div class="row">
                <div class="table-responsive text-left">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr> 
                                <th> # </th>
                                <th> ${orderNoTabLbl} </th>
                                <th> ${customerLoginTabLbl} </th>
                                <th> ${customerFirstNameTabLbl} </th>
                                <th> ${customerLastNameTabLbl} </th>
                                <th> ${statusTabLbl} </th>
                                <th> ${lastModificationDateTabLbl} </th>
                                <th> ${createDateTabLbl} </th>
                                <th> ${actionTabLbl} </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${orders}" var="item" varStatus="counter">
                                <tr> 
                                    <td> ${counter.count} </td>
                                    <td> ${item.orderId} </td>
                                    <td> ${empty item.cart.user.login?'----------':item.cart.user.login} </td>
                                    <td> ${item.customer.firstName} </td>
                                    <td> ${item.customer.lastName} </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.status=='ED'}">
                                                ${statusInfoEditMsg}
                                            </c:when>
                                            <c:when test="${item.status=='WT'}">
                                                ${statusInfoWaitMsg}
                                            </c:when>
                                            <c:when test="${item.status=='AC'}">
                                                ${statusInfoActionMsg}
                                            </c:when>
                                            <c:when test="${item.status=='RE'}">
                                                ${statusInfoRealizationMsg}
                                            </c:when>
                                            <c:when test="${item.status=='CA'}">
                                                ${statusInfoCanceledMsg}
                                            </c:when>
                                            <c:when test="${item.status=='FI'}">
                                                ${statusInfoCompletedMsg}
                                            </c:when>
                                            <c:otherwise>
                                                ${statusInfoUndefinedMsg}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td> ${fn:replace(item.lastModyficationDate,'T',' ')} </td>
                                    <td> ${fn:replace(item.createDate,'T',' ')} </td>
                                    <td> 
                                        <spring:url value="/orders/admin/order/${item.orderId}" var="showOrderUrl"/>
                                        <a href="${showOrderUrl}" class="btn btn-primary btn-xs">
                                            <span class="glyphicon glyphicon-check"> </span> ${showTabBtnLbl}
                                        </a> 
                                        <a href="" class="btn btn-primary btn-xs">
                                            <span class="glyphicon glyphicon-cog"> </span> ${activeTabBtnLbl}
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
    
    </body>
</html>
