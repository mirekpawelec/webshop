<%-- 
    Document   : addItemToDelivery
    Created on : 2017-09-25, 22:04:08
    Author     : mirek
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <jsp:include page="../../views/fragments/header.jsp" />
        
    <jsp:include page="../../views/fragments/navi.jsp" />
    
        <section class="main">
            <div class="container">
                <br/>
                
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><strong> Nr dostawy: </strong></span>
                            <input type="text" class="form-control" aria-describedby="basic-addon1" readonly="true" value="${delivery.deliveryId}"/>
                        </div>
                    </div>

                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><strong> Miejsce: </strong></span>
                            <input type="text" class="form-control" aria-describedby="basic-addon1" readonly="true" value="${delivery.place.placeNo}"/>
                        </div>
                    </div>
                </div>
                        
                <br/>
                
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1"><strong> Info: </strong></span>
                            <textarea type="text" class="form-control" aria-describedby="basic-addon1" readonly="true">${delivery.description}</textarea>
                        </div>
                    </div>
                </div>

                <br/>

                <form:form modelAttribute="deliveryItem" class="form-horizontal" autocomplete="off">
                    <div class="row text-danger">
                        <form:errors path="*" class="alert alert-danger" element="div"/>
                    </div>
                    <fieldset>      
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"> Dodaj jednostkę </h3>
                            </div>
                            <div class="panel-body">
                                
                                <form:input path="delivery.deliveryId" value="${delivery.deliveryId}" type="hidden" />
                                
                                <div class="form-group"> 
                                    <label for="loadunitNo" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Identyfikator </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                        <form:input id="loadunitNo" type="text" path="loadunitNo" class="form-control" placeholder="Identyfikator"/>
                                        <form:errors path="loadunitNo" class="text-danger"/>
                                    </div>
                                </div>
                                    
                                <div class="form-group"> 
                                    <label for="productId" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Numer artykułu </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                        <form:select id="productId" path="product.productId" class="form-control">
                                            <option value="0"> -------------------- </option> 
                                            <c:forEach items="${articles}" var="article">
                                                <option value="${article.productId}" <c:if test="${article.productId==deliveryItem.product.productId}"> selected="true" </c:if> > ${article.productNo} - ${article.name} </option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors path="product.productId" class="text-danger"/>
                                    </div>
                                </div>
                                    
                                <div class="form-group"> 
                                    <label for="quantity" class="col-xs-12 col-sm-3 col-md-3 col-lg-2 control-label"> Ilość sztuk </label>
                                    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-9">
                                        <form:input id="quantity" type="text" path="quantity" class="form-control" placeholder="Ilość"/>
                                        <form:errors path="quantity" class="text-danger"/>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
                                        <button type="submit" name="_eventId_add" class="btn btn-primary pull-right"> 
                                            ${deliveryItem.itemId==null?'Dodaj <span class="glyphicon glyphicon-plus"></span>':'Aktualizuj <span class="glyphicon glyphicon-ok"></span>'}
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                                    
                        <div class="panel panel-primary">        
                            <div class="panel-heading">
                                <h3 class="panel-title"> Lista pozycji </h3>
                            </div>
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
                                            <th></th>
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
                                            <td>
                                                <form:form action="${flowExecutionUrl}">
                                                    <input type="hidden" name="editItemId" value="${item.itemId}" />
                                                    
                                                    <div class="form-group btn-group-sm"> 
                                                        <button type="submit" name="_eventId_edit" class="btn btn-primary"> 
                                                            <span class="glyphicon glyphicon-edit"></span> Edytuj
                                                        </button> 

                                                        <button type="submit" name="_eventId_delete" class="btn btn-danger">
                                                            <span class="glyphicon glyphicon-remove"></span> Usuń 
                                                        </button> 
                                                    </div>
                                                </form:form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

                        <div class="row">
                            <div class="form-group">
                                <button type="submit" name="_eventId_back" class="btn btn-primary"><span class="glyphicon glyphicon-menu-left"></span> Wstecz </button> 
                                <button type="submit" name="_eventId_summary" class="btn btn-primary" ${fn:length(delivery.deliveryItemSet)==0?'disabled':''} > 
                                    Podsumowanie <span class="glyphicon glyphicon-check"></span>
                                </button>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
            </div>
        </section>
                        
    <jsp:include page="../../views/fragments/footer.jsp" />
    
    </body>
</html>