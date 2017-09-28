<%-- 
    Document   : addDetailsDelivery
    Created on : 2017-09-25, 21:33:33
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    
    <jsp:include page="../../views/fragments/header.jsp" />
        
    <jsp:include page="../../views/fragments/navi.jsp" />
    
        <section class="main">
            <div class="container">
                <br/>
                
                <form:form modelAttribute="deliveryOrder.delivery" class="form-horizontal">
                    <div class="row text-danger">
                        <form:errors path="*" class="text-danger"/>
                    </div>
                    <fieldset>
                        <div class="panel panel-primary">
                            <div class="panel-heading"> 
                                <h3 class="panel-title"> Dane dostawy </h3>
                            </div>
                            <div class="panel-body">
                                
                                <div class="form-group">
                                    <label for="deliveryId" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Numer </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                        <form:input id="deliveryId" type="text" path="deliveryId" class="form-control" readonly="true"/>
                                        <form:errors path="deliveryId" class="text-danger"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="place" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Miejsce </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                        <form:select id="place" path="place.placeNo" class="form-control">
                                            <form:option value="NONE" label="----------" /> 
                                            <form:options items="${places}" itemValue="placeNo" itemLabel="placeNo" /> 
                                        </form:select>
                                        <form:errors path="place.placeId" class="text-danger"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="description" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Opis </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                         <form:textarea id="description" type="text" rows="3" path="description" class="form-control" placeholder="Dodatkowe informacje"/>
                                         <form:errors path="description" class="text-danger"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                                    
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"> Dane kierowcy </h3>
                            </div>
                            <div class="panel-body">
                                
                                <div class="form-group">
                                    <label for="driverFirstName" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label">Imię i Nazwisko </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9 input-group">
                                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                            <form:input id="driverFirstName" type="text" path="driverFirstName" class="form-control" placeholder="Imię"/>
                                            <form:errors path="driverFirstName" class="text-danger"/>
                                        </div>
                                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                            <form:input id="driverLastName" type="text" path="driverLastName" class="form-control" placeholder="Nazwisko"/>
                                            <form:errors path="driverLastName" class="text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label for="driverPhoneNo" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Nr telefonu </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                        <form:input id="driverPhoneNo" type="text" path="driverPhoneNo" class="form-control" placeholder="+__ ___ ___ ___" />
                                        <form:errors path="driverPhoneNo" class="text-danger"/>
                                    </div>
                                </div> 
                                    
                            </div>
                        </div>
                                
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"> Dane środka transportowego </h3>
                            </div>
                            <div class="panel-body">
                                
                                <div class="form-group">
                                    <label for="truckNumber" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Numer rejestracyjny </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9 input-group">
                                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                            <form:input id="truckNumber" type="text" path="truckNumber" class="form-control" placeholder="Ciągnik"/>
                                            <form:errors path="truckNumber" class="text-danger"/>
                                        </div>
                                        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                            <form:input id="trailerOrCaravanNumber" type="text" path="trailerOrCaravanNumber" class="form-control" placeholder="Przyczepa" />
                                            <form:errors path="truckNumber" class="text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="truckType" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Typ </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                        <form:input id="truckType" type="text" path="truckType" class="form-control" placeholder="Typ"/>
                                        <form:errors path="truckType" class="text-danger"/>
                                    </div>
                                </div>
                                    
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${deliveryOrder.delivery.status=='OK'}">
                                <c:set var="status" value="Utworzona" />
                            </c:when>
                            <c:when test="${deliveryOrder.delivery.status=='RE'}">
                                <c:set var="status" value="Realizacja" />
                            </c:when>
                            <c:when test="${deliveryOrder.delivery.status=='FI'}">
                                <c:set var="status" value="Zamknięta" />
                            </c:when>
                        </c:choose>
                                    
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                    <label for="createUser" class="control-label small"> Użytkownik </label>
                                    <form:input id="createUser" type="text" path="createUser" class="form-control input-sm" readonly="true" placeholder="Login" />
                            </div>
                            
                            <div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3">
                                    <label for="status" class="control-label small"> Status </label>
                                    <form:input id="status" type="text" path="status" value="${status}" class="form-control input-sm" readonly="true" 
                                                                                                                                           placeholder="Status" />
                            </div>

                            <div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3 pull-right">
                                    <label for="createDate" class="control-label small"> Data utworzenia </label>
                                    <form:input id="createDate" type="text" path="createDate" class="form-control input-sm" readonly="true" 
                                                                                                                              placeholder="__-__-____ __:__:__" />
                            </div>
                            
                            <div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3 pull-right">
                                    <label for="finishDate" class="control-label small"> Data zamknięcia </label>
                                    <form:input id="finishDate" type="text" path="finishDate" class="form-control input-sm" readonly="true" 
                                                                                                                              placeholder="__-__-____ __:__:__" />                                     
                            </div>
                        </div>

                        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
                        <div class="row">
                            <div class="form-group">
                                <button type="submit" name="_eventId_toDeliveries" class="btn btn-primary"><span class="glyphicon glyphicon-menu-left"></span> Powrót</button>
                                <button type="submit" name="_eventId_keepGoing" class="btn btn-primary">Dalej <span class="glyphicon glyphicon-menu-right"></span></button>                                
                                <button type="submit" name="_eventId_stop" class="btn btn-danger">Usuń <span class="glyphicon glyphicon-remove"></span></button>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
            </div>
        </section>
    <jsp:include page="../../views/fragments/footer.jsp" />
    </body>
</html>
