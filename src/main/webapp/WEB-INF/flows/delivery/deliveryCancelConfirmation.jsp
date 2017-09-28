<%-- 
    Document   : deliveryCancelConfirmation
    Created on : 2017-09-27, 21:45:12
    Author     : mirek
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../../views/fragments/header.jsp" />
        
    <jsp:include page="../../views/fragments/navi.jsp" />
    
        <section class="main">
            <div class="container">
                <br/><br/><br/><br/>
                
                <div class="row">
                    <div class="alert alert-warning" role="alert">
                        <strong>
                            Operacja spowoduje przerwanie realizacji dostawy nr ${deliveryOrder.delivery.deliveryId} !
                            <br/>
                            Czy jesteś pewien, że chcesz ją usunąć?
                        </strong>
                    </div>
                </div>
                     
                <div class="row">
                    <a href="${flowExecutionUrl}&_eventId_no" class="btn btn-primary"><span class="glyphicon glyphicon-chevron-left"></span> Wróć </a>
                    <a href="${flowExecutionUrl}&_eventId_yes" class="btn btn-danger"> Tak <span class="glyphicon glyphicon-ok"></span> </a>                                
                </div>                
            </div>
        </section>
    <jsp:include page="../../views/fragments/footer.jsp" />
    </body>
</html>
