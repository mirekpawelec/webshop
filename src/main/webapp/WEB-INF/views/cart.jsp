<%-- 
    Document   : cart
    Created on : 2017-10-07, 03:57:17
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="cart.pageHeaderPart1.label" var="headerPart1Info"/>
<spring:message code="cart.pageHeaderPart2.label" var="headerPart2Info"/>
<spring:message code="cart.refresh.label" var="refreshBtnLbl"/>
<spring:message code="cart.checkOut.label" var="checkOutBtnLbl"/>
<spring:message code="cart.clearCart.label" var="clearCartBtnLbl"/>
<spring:message code="cart.continueShopping.label" var="continueShoppingBtnLbl"/>
<spring:message code="cart.table.product.label" var="productTabLbl"/>
<spring:message code="cart.table.quantity.label" var="quantityTabLbl"/>
<spring:message code="cart.table.unit.label" var="unitTabLbl"/>
<spring:message code="cart.table.unit.info.label" var="unitInfoTabLbl"/>
<spring:message code="cart.table.price.label" var="priceTabLbl"/>
<spring:message code="cart.table.currency.label" var="currencyTabLbl"/>
<spring:message code="cart.table.action.label" var="actionTabLbl"/>
<spring:message code="cart.table.summary.label" var="summaryTabLbl"/>
<spring:message code="cart.table.remove.button.label" var="removeTabLbl"/>


    <jsp:include page="./fragments/header.jsp"/>

    <section class="main">

        <jsp:include page="./fragments/navi.jsp"/>

        <div class="container" ng-controller="cartController" ng-init="refreshCart('${sessionId}')">
            <div class="row">
                <div class="page-header text-left">
                    <h2> ${headerPart1Info} </h2>
                    <p> ${headerPart2Info} </p>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <button class="btn btn-primary pull-left" ng-click="refreshCart('${sessionId}')"><span class="glyphicon glyphicon-refresh"></span> ${refreshBtnLbl} </button>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <button ng-if="totalQuantity" class="btn btn-danger pull-right" ng-click="removeCart(cart.cartId)"><span class="glyphicon glyphicon-remove"></span> ${clearCartBtnLbl} </button>
                </div>
            </div>

            <hr>

            <div class="row">
                <div class="table-responsive">
                    <table class="table table-striped table-hover text-left">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>${productTabLbl}</th>
                                <!--<th>Numer produktu</th>-->
                                <th>${quantityTabLbl}</th>
                                <th>${unitTabLbl}</th>
                                <th>${priceTabLbl}</th>
                                <th>${currencyTabLbl}</th>
                                <th>${actionTabLbl}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="(id, item) in cart.cartItemSet">
                                <td>{{ id + 1 }}</td>
                                <td>{{ item.manufacturer }} {{ item.productName }}</td> 
                                <!--<td>{{ item.productNo }}</td>--> 
                                <td>{{ item.quantity }}</td> 
                                <td> ${unitInfoTabLbl} </td>
                                <td>{{ item.totalPrice }}</td>
                                <td> PLN </td>
                                <td> 
                                    <buttom class="btn btn-danger btn-sm" ng-click="removeItemFromCart(item.productId)">
                                        <span class="glyphicon glyphicon-remove"></span> ${removeTabLbl}
                                    </buttom> 
                                </td> 
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr ng-if="cart.costOfAllItems">
                                <th colspan="2">${summaryTabLbl}</th>
                                <!--<th></th>-->
                                <th>{{ totalQuantity }}</th>
                                <th> sztuk </th>
                                <th>{{ cart.costOfAllItems }}</th>
                                <th> PLN </th>
                                <th></th>
                            </tr>
                        </tfoot>
                    </table>

                </div> 
            </div>
            <hr>
            <div class="row">                    
                <spring:url value="/home" var="homeUrl" />

                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <button class="btn btn-default pull-left" onclick="location.href='${homeUrl}'">
                        <span class="glyphicon glyphicon-hand-left"></span> ${continueShoppingBtnLbl} 
                    </button>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <a ng-if="totalQuantity" class="btn btn-success pull-right" href='/webshop/user/customerOrder?cartId={{cart.cartId}}'" >
                        <span class="glyphicon glyphicon-shopping-cart"></span> ${checkOutBtnLbl} 
                    </a>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="./fragments/footer.jsp"/>
    
    </body>
</html>
