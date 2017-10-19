<%-- 
    Document   : collectCustomerInfo
    Created on : 2017-08-14, 20:26:25
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="../../views/fragments/header.jsp" />
    
        <section class="main">
            <div class="container pull-down">
                <div class="row">
                    <div class="well">
                        <h3> #${order.cart.cartId} Podsumowanie zamówienia </h3>
                    </div>
                </div>
 
                <div class="row">
                    <div id="summary" class="table-responsive">
                        <table id="sortTable" class="table table-striped table-hover text-right">
                            <thead>
                                    <tr class="primary-background">
                                        <th> Lp.</th>
                                        <th> Nazwa artykułu </th>
                                        <th class="text-right"> Ilość </th>
                                        <th class="text-right"> Cena</th>
                                    </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${order.cart.cartItemSet}" var="item" varStatus="counter">
                                    <tr>
                                        <td class="text-left"> ${counter.count}</td>
                                        <td class="text-left"> ${item.product.manufacturer} ${item.product.name} </td>
                                        <td> ${item.quantity} </td>
                                        <td> ${item.totalPrice} </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                    <tr>
                                        <td> </td>
                                        <td colspan="2"><h4>Łączna wartość zamówienia:</h4></td>
                                        <td><h4><strong>${order.cart.costOfAllItems}</strong></h4></td>
                                    </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>

                <hr/>
                        
                <form:form modelAttribute="shippingDetail" class="form-horizontal">
                    <div class="row">
                        <div class="col-xs-12 col-sm-6 col-md-7 col-lg-8"> 
                            <div class="panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Informacje </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table style="width: 100%; text-align: left">
                                            <tr>
                                                <td>Aktualny status:</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${order.status=='AC'}">
                                                            Przyjęte do realizacji
                                                        </c:when>
                                                        <c:when test="${order.status=='RE'}">
                                                            Kompletacja
                                                        </c:when>
                                                        <c:when test="${order.status=='FI'}">
                                                            Produkty zostały wysłane
                                                        </c:when>
                                                        <c:otherwise>
                                                            Oczekiwanie na akceptacje
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>
                                                    <fmt:parseDate value="${fn:replace(order.lastModyficationDate,'T',' ')}" 
                                                                   pattern="yyyy-MM-dd HH:mm:ss" var="parsedLmDateTime" type="both" />
                                                    ( od <em><fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedLmDateTime}"/> </em> )
                                                </td>
                                            </tr>
                                            <tr><td colspan="2"><br></td></tr>
                                            <tr>
                                                <td>Numer zamówienia:</td>
                                                <td>${order.cart.cartId}</td>
                                            </tr>
                                            <tr>
                                                <c:choose>
                                                    <c:when test="${order.status!='ED'}">
                                                        <td>Data sprzedaży:</td>
                                                        <td>
                                                            <fmt:parseDate value="${fn:replace(order.createDate,'T',' ')}" 
                                                                           pattern="yyyy-MM-dd HH:mm:ss" var="parsedCdDateTime" type="both" />
                                                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedCdDateTime}"/> 
                                                        </td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td colspan="2"><br></td>
                                                    </c:otherwise>
                                                </c:choose>
                                            </tr>
                                            <tr><td colspan="2"><br></td></tr>
                                            <tr>
                                                <td>Przesyłka:</td>
                                                <td>Kurier na terenie Polski (0.00 PLN)</td>
                                            </tr>
                                            <tr>
                                                <td>Paczki:</td>
                                                <td>6231168440832 (FedEx.pl)</td>
                                            </tr>
                                            <tr><td colspan="2"><br></td></tr>
                                            <tr>
                                                <td>Sposób płatności:</td>
                                                <td>System PayPal</td>
                                            </tr>
                                            <tr>
                                                <td>Płatność:</td>
                                                <td>Opłacone (218.00 PLN z 218.00 PLN)</td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-6 col-md-5 col-lg-4">   
                            <div class="panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Adres dostawy </h3>
                                </div>
                                <div class="panel-body text-left">
                                    <spring:bind path="*">
                                        <c:if test="${status.error}">
                                            <div class="alert alert-danger alert-dismissible" role="alert">
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                <h4> Nieprawidłowe dane!</h4>
                                                <p> Popraw zaznaczone pola. </p>
                                            </div>
                                        </c:if>
                                    </spring:bind>
                                    <address>
                                        ${order.shippingDetail.name} <br>
                                        ${order.shippingDetail.shippingAddress.streetName} ${order.shippingDetail.shippingAddress.doorNo} <br>
                                        ${order.shippingDetail.shippingAddress.zipCode}, ${order.shippingDetail.shippingAddress.areaName}, 
                                        ${order.shippingDetail.shippingAddress.state} , ${order.shippingDetail.shippingAddress.country} <br>
                                    </address>
                                    <button type="button" class="btn btn-default btn-block btn-sm" data-toggle="modal" data-target="#otherAddress">
                                        Zmień adres
                                    </button>
                                </div>
                            </div>

                            <div class="panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Dane płatnika </h3>
                                </div>
                                <div class="panel-body text-left">
                                    <address>
                                        ${order.customer.firstName} ${order.customer.lastName} <br>
                                        ${order.customer.address.streetName} ${order.customer.address.doorNo} <br>
                                        ${order.customer.address.zipCode}, ${order.customer.address.areaName}, 
                                        ${order.customer.address.state} , ${order.customer.address.country} <br>
                                        <span class="glyphicon glyphicon-envelope"></span> ${order.customer.email} <br>
                                        <span class="glyphicon glyphicon-phone-alt"></span> ${order.customer.phoneNumber}
                                    </address>
                                </div>
                            </div>         
                        </div>
                    </div>

                    <hr/>

                    <div class="modal fade bs-example-modal-lg" id="otherAddress" tabindex="-1" role="dialog">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h3 class="modal-title"> Inny adres </h3>
                                </div>
                                <div class="modal-body">
                                    <div class="row"> 
                                        <spring:bind path="name">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                    <label class="control-label" for="name"> nazwa odbiorcy </label>
                                                </div>

                                                <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                                                    <form:input type="text" id="name" path="name" class="form-control" placeholder="nazwa odbiorcy"/>
                                                    <form:errors path="name" class="text-danger"/>
                                                </div>
                                            </div>
                                        </spring:bind>
                                    </div>

                                    <div class="row">
                                        <spring:bind path="shippingAddress.streetName">
                                            <c:if test="${status.error}"> <c:set value="${true}" var="isErrorStreerName"/> </c:if>
                                        </spring:bind>
                                        
                                        <spring:bind path="shippingAddress.doorNo">
                                            <c:if test="${status.error}"> <c:set value="${true}" var="isErrorDoorNo"/> </c:if>
                                        </spring:bind>
                                        
                                        <div class="form-group">
                                            <div class="${isErrorStreerName || isErrorDoorNo ? 'has-error' : ''} col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                <label class="control-label" for="streetName"> ulica i numer domu </label>
                                            </div>

                                            <div class="${isErrorStreerName? 'has-error' : ''} col-xs-5 col-sm-5 col-md-5 col-lg-5">
                                                <form:input type="text" id="streetName" path="shippingAddress.streetName" class="form-control" placeholder="ulica"/>
                                                <form:errors path="shippingAddress.streetName" class="text-danger"/>
                                            </div>

                                            <div class="${isErrorDoorNo ? 'has-error' : ''} col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                                <form:input type="text" id="doorNo" path="shippingAddress.doorNo" class="form-control" placeholder="numer"/>
                                                <form:errors path="shippingAddress.doorNo" class="text-danger"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row"> 
                                        <spring:bind path="shippingAddress.zipCode">
                                            <c:if test="${status.error}"> <c:set value="${true}" var="isErrorzZipCode"/> </c:if>
                                        </spring:bind>
                                        
                                        <spring:bind path="shippingAddress.areaName">
                                            <c:if test="${status.error}"> <c:set value="${true}" var="isErrorAreaName"/> </c:if>
                                        </spring:bind>
                                        
                                        <div class="form-group">
                                            <div class="${isErrorzZipCode || isErrorAreaName ? 'has-error' : ''} col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                <label class="control-label" for="zipCode"> kod pocztowy i miasto</label>
                                            </div>

                                            <div class="${isErrorzZipCode ? 'has-error' : ''} col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                                <form:input type="text" id="zipCode" path="shippingAddress.zipCode" class="form-control" placeholder="kod pocztowy"/>
                                                <form:errors path="shippingAddress.zipCode" class="text-danger"/>
                                            </div>

                                            <div class="${isErrorAreaName ? 'has-error' : ''} col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                                <form:input type="text" id="areaName" path="shippingAddress.areaName" class="form-control" placeholder="miasto"/>
                                                <form:errors path="shippingAddress.areaName" class="text-danger"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row"> 
                                        <spring:bind path="shippingAddress.state">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                    <label class="control-label" for="state"> województwo </label>
                                                </div>

                                                <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                                                    <form:input type="text" id="state" path="shippingAddress.state" class="form-control" placeholder="województwo"/>
                                                    <form:errors path="shippingAddress.state" class="text-danger"/>
                                                </div>
                                            </div>
                                        </spring:bind>
                                    </div>

                                    <div class="row"> 
                                        <spring:bind path="shippingAddress.country">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 text-right">
                                                    <label class="control-label" for="country"> kraj </label>
                                                </div>

                                                <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                                                    <form:input type="text" id="country" path="shippingAddress.country" class="form-control" placeholder="kraj"/>
                                                    <form:errors path="shippingAddress.country" class="text-danger"/>
                                                </div>
                                            </div>
                                        </spring:bind>
                                    </div>
                                    
                                    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
                                </div>
                                
                                <div class="modal-footer">
                                    <div class="col-sm-offset-3 col-sm-6 col-md-offset-3 col-md-6 col-lg-offset-3 col-lg-6">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"> Zamknij </button>
                                        <button type="submit" name="_eventId_changeAddress" class="btn btn-primary"> Użyj tego adresu </button>
                                    </div>
                                </div>            
                            </div>
                        </div>
                    </div>
                </form:form>   

                <div class="row">
                    <a class="btn btn-primary" href="${flowExecutionUrl}&_eventId=backToCollectCustomerInfo"><span class="glyphicon glyphicon-chevron-left"></span> Wstecz </a>
                    <a class="btn btn-success" href="${flowExecutionUrl}&_eventId=orderConfirmed"> <span class="glyphicon glyphicon-ok"></span> Zatwierdź </a>
                    <a class="btn btn-danger" href="${flowExecutionUrl}&_eventId=cancel"><span class="glyphicon glyphicon-remove"></span> Anuluj </a>
                </div>
            </div>
        </section>
    
    <jsp:include page="../../views/fragments/footer.jsp" />
    
    </body>
</html>
