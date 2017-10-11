<%-- 
    Document   : addDetailsDelivery
    Created on : 2017-09-25, 21:33:33
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    
    <jsp:include page="../../views/fragments/header.jsp" />
        
    <jsp:include page="../../views/fragments/navi.jsp" />
    <hr>
        <section class="main">
            <div class="container">
                <br/>
                
                <form:form modelAttribute="delivery" class="form-horizontal">
                    
                    <ul class="nav nav-pills">
                        <li><a href="#dane" data-toggle="tab">Dane dostawy</a></li>
                        <li><a href="#pozycje" data-toggle="tab">Lista pozycji</a></li>
                    </ul>
                    
                    <div class="tab-content">  
                        <div class="tab-pane active" id="dane">
                            <div class="row text-danger">
                                <form:errors path="*" class="alert alert-danger" element="div"/>
                            </div>

                            <fieldset>
                                <div class="panel panel-primary">
                                    <div class="panel-body">

                                        <div class="form-group">
                                            <label for="deliveryId" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Nr dostawy </label>
                                            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                                <form:input id="deliveryId" type="text" path="deliveryId" class="form-control" readonly="true"/>
                                                <form:errors path="deliveryId" class="text-danger"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="place" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Miejsce </label>
                                            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                                <form:select id="place" path="place.placeNo" class="form-control" disabled="${delivery.status=='FI'?'true':'false'}">
                                                    <form:option value="NONE" label="----------" /> 
                                                    <form:options items="${places}" itemValue="placeNo" itemLabel="placeNo" /> 
                                                </form:select>
                                                <form:errors path="place.placeId" class="text-danger"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="description" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Opis </label>
                                            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                                <form:textarea id="description" type="text" rows="3" path="description" class="form-control" placeholder="Dodatkowe informacje" 
                                                                                                                 readonly="${delivery.status=='FI'?'true':'false'}"/>
                                                 <form:errors path="description" class="text-danger"/>
                                            </div> 
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"> Dane spedytora </h3>
                                    </div>
                                    <div class="panel-body">

                                        <div class="form-group">
                                            <label for="driverFirstName" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Kierowca </label>
                                            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9 input-group">
                                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                                    <form:input id="driverFirstName" type="text" path="driverFirstName" class="form-control" placeholder="Imię" 
                                                                                                                 readonly="${delivery.status=='FI'?'true':'false'}"/>
                                                    <form:errors path="driverFirstName" class="text-danger"/>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                                    <form:input id="driverLastName" type="text" path="driverLastName" class="form-control" placeholder="Nazwisko" 
                                                                                                                 readonly="${delivery.status=='FI'?'true':'false'}"/>
                                                    <form:errors path="driverLastName" class="text-danger"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="driverPhoneNo" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Nr telefonu </label>
                                            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                                <form:input id="driverPhoneNo" type="text" path="driverPhoneNo" class="form-control" placeholder="+__ ___ ___ ___" 
                                                                                                                 readonly="${delivery.status=='FI'?'true':'false'}"/>
                                                <form:errors path="driverPhoneNo" class="text-danger"/>
                                            </div>
                                        </div> 

                                        <div class="form-group">
                                            <label for="truckNumber" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Numer rejestracyjny </label>
                                            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9 input-group">
                                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                                    <form:input id="truckNumber" type="text" path="truckNumber" class="form-control" placeholder="Ciągnik"
                                                                                                                 readonly="${delivery.status=='FI'?'true':'false'}"/>
                                                    <form:errors path="truckNumber" class="text-danger"/>
                                                </div>
                                                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                                    <form:input id="trailerOrCaravanNumber" type="text" path="trailerOrCaravanNumber" class="form-control" placeholder="Przyczepa" 
                                                                                                                 readonly="${delivery.status=='FI'?'true':'false'}"/>
                                                    <form:errors path="trailerOrCaravanNumber" class="text-danger"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="truckType" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Typ </label>
                                            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                                <form:input id="truckType" type="text" path="truckType" class="form-control" placeholder="Typ" 
                                                                                                                 readonly="${delivery.status=='FI'?'true':'false'}"/>
                                                <form:errors path="truckType" class="text-danger"/>
                                            </div>
                                        </div>

                                    </div>
                                </div>            
                            </fieldset>            
                        </div>

                        <div class="tab-pane" id="pozycje">
                            <div class="panel panel-primary">        
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover text-left">
                                            <tr> 
                                                <th>Lp.</th>
                                                <th>Identyfikator</th>
                                                <th>Numer artykułu</th>
                                                <th>Nazwa artykułu</th>
                                                <th>Ilość</th>
                                                <th>Data dodania</th>
                                            </tr>
                                        <c:forEach items="${delivery.deliveryItemSet}" var="item" varStatus="licznik">
                                            <tr> 
                                                <td>${licznik.count}.</td>
                                                <td>${item.loadunitNo}</td>
                                                <td>${item.product.productNo}</td>
                                                <td>${item.product.name}</td>
                                                <td>${item.quantity}</td>
                                                <c:set var="createDate" value="${fn:replace(item.createDate, 'T', ' ')}" />
                                                <td>${createDate}</td>
                                            </tr>
                                        </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>  
                                            
                    <div class="row">
                        <c:choose>
                            <c:when test="${delivery.status=='OK'}">
                                <c:set var="status" value="Utworzona" />
                            </c:when>
                            <c:when test="${delivery.status=='RE'}">
                                <c:set var="status" value="Realizacja" />
                            </c:when>
                            <c:when test="${delivery.status=='FI'}">
                                <c:set var="status" value="Zamknięta" />
                            </c:when>
                        </c:choose>

                        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                <label for="createUser" class="control-label small"> Utworzył </label>
                                <form:input id="createUser" type="text" path="createUser" class="form-control input-sm" readonly="true" placeholder="Login" />
                        </div>
                        
                        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2">
                                <label for="closeUser" class="control-label small"> Zakończył </label>
                                <input id="closeUser" type="text" class="form-control input-sm" readonly="true" placeholder="Login" />
                        </div>
                        
                        <div class="col-xs-12 col-sm-2 col-sm-offset-1 col-md-2 col-md-offset-1 col-lg-2 col-lg-offset-1">
                                <label for="status" class="control-label small"> Status </label>
                                <input id="status" type="text" value="${status}" class="form-control input-sm" readonly="true" placeholder="Status" />
                        </div>

                        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 pull-right">
                                <label for="createDate" class="control-label small"> Data zamknięcia </label>
                                <form:input id="createDate" type="text" path="finishDate" class="form-control input-sm" readonly="true" 
                                                                                                                          placeholder="__-__-____ __:__:__" />
                        </div>

                        <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 pull-right">
                                <label for="finishDate" class="control-label small"> Data utworzenia </label>
                                <form:input id="finishDate" type="text" path="createDate" class="form-control input-sm" readonly="true" 
                                                                                                                          placeholder="__-__-____ __:__:__" />                                     
                        </div>
                    </div>
                        
                    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
                    <br/>
                    
                    <div class="row">
                        <div class="form-group">
                            <button type="submit" name="_eventId_toDeliveries" class="btn btn-primary">
                                <span class="glyphicon glyphicon-menu-left"></span> Wstecz
                            </button>
                            
                            <button type="submit" name="_eventId_saveChange" class="btn btn-primary" ${delivery.status=='FI'?'disabled':''} >
                                Zapisz <span class="glyphicon glyphicon-ok"></span>
                            </button>

                            <button type="submit" name="_eventId_addItems" class="btn btn-primary" ${delivery.status=='FI'?'disabled':''} >
                                Dodaj <span class="glyphicon glyphicon-plus"></span>
                            </button>     
                            
                            <button type="submit" name="_eventId_summary" class="btn btn-primary" 
                                                            ${fn:length(delivery.deliveryItemSet)==0||delivery.status=='FI'?'disabled':''} > 
                                Podsumowanie <span class="glyphicon glyphicon-check"></span>
                            </button>
                                    
                            <button type="submit" name="_eventId_delete" class="btn btn-danger" 
                                               ${delivery.status=='FI'||fn:length(delivery.deliveryItemSet)>0?'disabled':''}>
                                Usuń <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </section>
    <jsp:include page="../../views/fragments/footer.jsp" />
    
    <script>
        $(function(){
            $('.nav-pills a:first').tab('show');
        })
    </script>
    </body>
</html>
