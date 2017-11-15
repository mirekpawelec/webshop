<%-- 
    Document   : order
    Created on : 2017-11-11, 01:09:14
    Author     : mirek
--%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:url value="/orders/admin" var="ordersListUrl"/>
<spring:url value="/orders/user/" var="userOrdersListUrl"/>
<spring:url value="/homepage" var="homepageUrl"/>
<spring:url value="/user/edit/" var="userEditUrl"/>
<spring:message code="order.pageHeader.oneOrder.label" var="headerOneOrderLbl" />
<spring:message code="order.pageHeader.manyOrders.label" var="headerManyOrdersLbl" />
<spring:message code="order.table1.subtotal.label" var="subtotalTab1Lbl"/>
<spring:message code="order.table1.deliveryAndPaument.label" var="deliveryAndPaumentTab1Lbl"/>
<spring:message code="order.table1.grandTotal.label" var="grandTotalTab1Lbl"/>
<spring:message code="order.orderStatus.edit.message" var="orderStatusEditMsg"/>
<spring:message code="order.orderStatus.wait.message" var="orderStatusWaitMsg"/>
<spring:message code="order.orderStatus.action.message" var="orderStatusActionMsg"/>
<spring:message code="order.orderStatus.realization.message" var="orderStatusRealizationMsg"/>
<spring:message code="order.orderStatus.canceled.message" var="orderStatusCanceledMsg"/>
<spring:message code="order.orderStatus.completed.message" var="orderStatusCompletedMsg"/>
<spring:message code="order.orderStatus.undefined.message" var="orderStatusUndefinedMsg"/>
<spring:message code="order.paymentStatus.paid.message" var="paymentStatusPaidMsg"/>
<spring:message code="order.paymentStatus.notPaid.message" var="paymentStatusNotPaidMsg"/>
<spring:message code="order.paymentStatus.undefined.message" var="paymentStatusUndefinedMsg"/>
<spring:message code="order.info.customerDetails.label" var="customerDetailsLbl"/>
<spring:message code="order.info.shippingAddress.label" var="shippingAddressLbl"/>
<spring:message code="order.info.editData.button.label" var="editDataBtnLbl"/>
<spring:message code="order.backToShop.button.label" var="backToShopBtnLbl"/>
<spring:message code="order.backToList.button.label" var="backToListBtnLbl"/>
<spring:message code="order.active.button.label" var="activeBtnLbl"/>


    <jsp:include page="./fragments/header.jsp"/>
    
    <section class="main">
        
        <jsp:include page="./fragments/navi.jsp"/>
        
        <div class="container">    
            <br><br><br><br>
            <div class="row">
                <div class="col-xs-12 col-sm-8 order-col-no-1">
                    <div class="table-responsive">
                        <c:choose>
                            <c:when test="${empty userOrdersList}">
                                <h2><span class="pull-left"> ${headerOneOrderLbl} #${order.orderId}</span><span class="pull-right color-date">${fn:replace(fn:substring(order.createDate, 0, 10),'-','/')}</span></h2>
                                <table class="table table-striped table-hover text-right">                       
                                    <tbody>
                                        <c:forEach items="${order.cart.cartItemSet}" var="item" varStatus="counter">
                                            <tr>
                                                <td class="text-left">${item.product.manufacturer} ${item.product.name}</td>
                                                <td></td>
                                                <td class="text-bold">${item.quantity} X ${item.totalPrice/item.quantity} PLN</td>
                                            </tr>
                                        </c:forEach>
                                            <tr> 
                                                <td class="text-left">${order.shippingDetails.deliveryMethod}</td>
                                                <td></td>
                                                <td class="text-bold">${order.shippingDetails.deliveryCost} PLN</td> 
                                            </tr>
                                            <tr> 
                                                <td class="text-left">${order.shippingDetails.paymentMethod}</td>
                                                <td></td>
                                                <td class="text-bold">${order.shippingDetails.paymentCost} PLN</td> 
                                            </tr>
                                            <tr><td colspan="3"><br></td></tr>
                                            <tr>
                                                <td></td>
                                                <td><h5 > ${subtotalTab1Lbl} </h5></td>
                                                <td><h5 class="text-bold"> ${order.cart.costOfAllItems} PLN </h5></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><h5> ${deliveryAndPaumentTab1Lbl} </h5></td>
                                                <td><h5 class="text-bold"> ${order.shippingDetails.deliveryCost + order.shippingDetails.paymentCost} PLN </h5></td>
                                            </tr>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th colspan="2" class="text-right"><h4 class="text-bold"> ${grandTotalTab1Lbl} </h4></th>
                                            <th class="text-right"><h4 class="text-bold"> ${order.shippingDetails.deliveryCost + order.shippingDetails.paymentCost + order.cart.costOfAllItems} PLN </h4></th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <h2><span class="pull-left"> ${headerManyOrdersLbl}</span></h2>
                                <table class="table table-striped table-hover text-right">
                                    <tbody>
                                        <c:forEach items="${orders}" var="item">
                                            <spring:url value="/orders/user/order/${item.orderId}" var="oneOrderUrl"/>
                                            <tr>                                            
                                                <td> 
                                                    <a href="${oneOrderUrl}"> <strong> #${item.orderId} </strong> </a>
                                                </td>
                                                <td> 
                                                    <a href="${oneOrderUrl}"> 
                                                        ${fn:replace(fn:substring(item.createDate, 0, 10),'-','/')}
                                                    </a>
                                                </td>
                                                <td> 
                                                    <a href="${oneOrderUrl}">
                                                        <span class="status">
                                                            <c:choose>
                                                                <c:when test="${item.status=='ED'}">
                                                                    ${orderStatusEditMsg}
                                                                </c:when>
                                                                <c:when test="${item.status=='WT'}">
                                                                    ${orderStatusWaitMsg}
                                                                </c:when>
                                                                <c:when test="${item.status=='AC'}">
                                                                    ${orderStatusActionMsg}
                                                                </c:when>
                                                                <c:when test="${item.status=='RE'}">
                                                                    ${orderStatusRealizationMsg}
                                                                </c:when>
                                                                <c:when test="${item.status=='CA'}">
                                                                    ${orderStatusCanceledMsg}
                                                                </c:when>
                                                                <c:when test="${item.status=='FI'}">
                                                                    ${orderStatusCompletedMsg}
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ${orderStatusUndefinedMsg}
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="${oneOrderUrl}"> 
                                                        <%--<c:choose>--%>
                                                            <%--<c:when test="${}">--%>
                                                                <!--$ {paymentStatusPaidMsg}-->
                                                            <%--</c:when>--%>
                                                            <%--<c:when test="${}">--%>
                                                                <!--$ {paymentStatusNotPaidMsg}-->
                                                            <%--</c:when>--%>
                                                            <%--<c:otherwise>--%>
                                                                <!--$ {paymentStatusUndefinedMsg}-->
                                                            <%--</c:otherwise>--%>
                                                        <%--</c:choose>--%>
                                                        ${paymentStatusNotPaidMsg}
                                                    </a>
                                                </td>
                                                <td> 
                                                    <a href="${oneOrderUrl}"> 
                                                        <strong> ${item.shippingDetails.deliveryCost + item.shippingDetails.paymentCost + item.cart.costOfAllItems} PLN </strong>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div> 
                </div>

                <div class="col-xs-12 col-sm-3 text-left order-col-no-2" style="border-left: solid 2px #cccccc">
                    <h2> ${customerDetailsLbl} </h2>
                    <c:choose>
                        <c:when test="${not empty user}">
                            
                            <c:choose>
                                <c:when test="${empty user.customer}">
                                    <br>
                                    <div call="row">
                                        <span class="customer-name"><strong>${user.firstName} ${user.lastName}</strong></span><br>
                                        <span class="glyphicon glyphicon-envelope"></span> ${user.email} <br>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <br>
                                    <div call="row">
                                        <span class="customer-name"><strong>${user.firstName} ${user.lastName}</strong></span><br>
                                        <span class="glyphicon glyphicon-envelope"></span> ${user.email} <br>
                                        <span class="glyphicon glyphicon-phone-alt"></span> ${user.customer.phoneNumber}
                                    </div>

                                    <br>
                                    <div call="row">
                                        ${user.customer.address.streetName} ${user.customer.address.doorNo} <br>
                                        ${user.customer.address.zipCode}, ${user.customer.address.areaName}, <br>
                                        ${user.customer.address.state} , ${user.customer.address.country} <br>            
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            
                            <br><br>
                            <div call="row">
                                <a href="${userEditUrl}<security:authentication property="principal.username"/>" class="btn btn-default"> ${editDataBtnLbl} </a>
                            </div>
                            
                            <br><br><br><br>
                        </c:when>
                        <c:otherwise>
                            <br>
                            <div call="row">
                                <span class="customer-name"><strong>${order.customer.firstName} ${order.customer.lastName}</strong></span><br>
                                <span class="glyphicon glyphicon-envelope"></span> ${order.customer.email} <br>
                                <span class="glyphicon glyphicon-phone-alt"></span> ${order.customer.phoneNumber}
                            </div>
                            
                            <br>
                            <div call="row">
                                ${order.customer.address.streetName} ${order.customer.address.doorNo} <br>
                                ${order.customer.address.zipCode}, ${order.customer.address.areaName}, <br>
                                ${order.customer.address.state} , ${order.customer.address.country} <br>            
                            </div>

                            <c:if test="${empty orders}">
                                <br>
                                <div call="row">
                                    <h4> ${shippingAddressLbl} </h4>
                                    ${order.shippingAddress.name} <br>
                                    ${order.shippingAddress.address.streetName} ${order.shippingAddress.address.doorNo} <br>
                                    ${order.shippingAddress.address.zipCode}, ${order.shippingAddress.address.areaName}, <br>
                                    ${order.shippingAddress.address.state} , ${order.shippingAddress.address.country} <br>    
                                    <span class="glyphicon glyphicon-phone-alt"></span> ${order.shippingAddress.phoneNumber}
                                </div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
          
            <br><br>

            <div class="row">
                <c:choose>
                    <c:when test="${not empty user || userOrder}">
                        <c:choose>
                            <c:when test="${userOrder}">
                                <a href="${userOrdersListUrl}<security:authentication property="principal.username"/>" class="btn btn-default pull-left"> 
                                    <span class="glyphicon glyphicon-hand-left"></span> ${backToListBtnLbl}
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${homepageUrl}" class="btn btn-default pull-left"> <span class="glyphicon glyphicon-hand-left"></span> ${backToShopBtnLbl} </a>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <div class="col-xs-11">
                            <a href="${ordersListUrl}" class="btn btn-default pull-left"> <span class="glyphicon glyphicon-hand-left"></span> ${backToListBtnLbl} </a>
                            <a href="" class="btn btn-primary pull-right"> ${activeBtnLbl} </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
    </section>
        
    <jsp:include page="./fragments/footer.jsp"/>
    
    </body>
</html>