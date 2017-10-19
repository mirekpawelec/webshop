<%-- 
    Document   : cart
    Created on : 2017-10-07, 03:57:17
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="./fragments/header.jsp"/>
    
        <section class="main">
            <jsp:include page="./fragments/navi.jsp"/>
            <hr>
            <div class="container" ng-controller="cartController" ng-init="refreshCart('${sessionId}')">
                <div class="row">
                    
                    <spring:message code="cart.pageHeader.label" var="headerInfo"/>
                    
                    <div class="page-header">
                        <h2> ${headerInfo} </h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                        <button class="btn btn-primary pull-left" ng-click="refreshCart('${sessionId}')"><span class="glyphicon glyphicon-refresh"></span> Odśwież </button>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                        <button ng-if="totalQuantity" class="btn btn-danger pull-right" ng-click="removeCart(cart.cartId)"><span class="glyphicon glyphicon-remove"></span> Wyczyść koszyk </button>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover text-left">
                            <thead>
                                <tr>
                                    <th>Lp.</th>
                                    <th>Nazwa produktu</th>
                                    <th>Numer produktu</th>
                                    <th>Ilość</th>
                                    <th>Jednostka</th>
                                    <th>Cena</th>
                                    <th>Waluta</th>
                                    <th>Akcja</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="(id, item) in cart.cartItemSet">
                                    <td>{{ id + 1 }}</td>
                                    <td>{{ item.manufacturer }} {{ item.productName }}</td> 
                                    <td>{{ item.productNo }}</td> 
                                    <td>{{ item.quantity }}</td> 
                                    <td> sztuk </td>
                                    <td>{{ item.totalPrice }}</td>
                                    <td> PLN </td>
                                    <td> 
                                        <buttom class="btn btn-danger btn-sm" ng-click="removeItemFromCart(item.productId)">
                                            <span class="glyphicon glyphicon-remove"></span> Usuń
                                        </buttom> 
                                    </td> 
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr ng-if="cart.costOfAllItems">
                                    <th colspan="2">Podsumowanie:</th>
                                    <th></th>
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
                            <span class="glyphicon glyphicon-hand-left"></span> Powrót 
                        </button>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                        <a ng-if="totalQuantity" class="btn btn-success pull-right" href='/webshop/customerOrder?cartId={{cart.cartId}}'" >
                            <span class="glyphicon glyphicon-shopping-cart"></span> Kupuję 
                        </a>
                    </div>
                </div>
            </div>
        </section>
         
        <jsp:include page="./fragments/footer.jsp"/>
    </body>
</html>
