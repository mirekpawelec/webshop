<%-- 
    Document   : InvalidCartWarningjsp
    Created on : 2017-09-25, 21:26:27
    Author     : mirek
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <jsp:include page="../../views/fragments/header.jsp" />
        
        <section class="main">
            <%--<jsp:include page="../../views/fragments/navi.jsp" />--%>
            <!--<hr>-->
            <div class="jumbotron pull-down">
                <div class="container"> 
                    <h3 class="alert alert-danger"> Wystąpił błąd w procesie realizacji dostawy! </h3>
                </div>
            </div>
            
            <div class="container">
                <p>
                    <a href="<spring:url value="/admin/deliveries" />" class="btn btn-primary"> <span class="glyphicon-hand-left glyphicon"></span> Dostawy </a>
                </p>
            </div>
	</section>
    </body>
</html>
