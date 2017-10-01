<%-- 
    Document   : deliveryClosing
    Created on : 2017-09-26, 21:24:29
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
                <br/><br/><br/>
                
                <div class="row">
                    <h3> <strong> Zawartosć dostawy nr ${delivery.deliveryId} </strong> </h3>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover text-left">
                            <tr>
                                <th> Numer artykułu </th>
                                <th> Nazwa artykułu </th>
                                <th> Ilość </th>
                                <th> Jednostka </th>
                            </tr>

                        <c:forEach items="${summary}" var="row">
                            <tr>
                                <td> ${row[1]} </td>
                                <td> ${row[2]} </td>
                                <td> ${row[3]} </td>
                                <td> sztuk </td>
                            </tr>
                        </c:forEach>

                        </table>
                    </div>
                </div>
                
                <br/>
                
                <div class="row">
                    <div class="alert alert-warning" role="alert">
                        <strong>
                            Potwierdzenie spowoduje zakończenie realizacji dostawy.
                            <br/>
                            Czy jesteś pewien, że chcesz to zrobić?
                        </strong>
                    </div>
                </div>
                     
                <form:form action="${flowExecutionUrl}">
                    <input type="hidden" name="whatView" value="${whereComeFrom}" />
                    <div class="row">
                        <button type="submit" name="_eventId_back" class="btn btn-primary"><span class="glyphicon glyphicon-chevron-left"></span> Wstecz </button>
                        <button type="submit" name="_eventId_close" class="btn btn-danger"> Zakończ <span class="glyphicon glyphicon-ok"></span> </button>                                
                    </div>
                </form:form>
                    
            </div>
        </section>
    <jsp:include page="../../views/fragments/footer.jsp" />
    </body>
</html>
