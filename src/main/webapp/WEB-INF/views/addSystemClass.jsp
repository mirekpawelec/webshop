<%-- 
    Document   : addSystemClass
    Created on : 2017-10-19, 14:24:08
    Author     : mirek
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:url value="/admin/classes" var="classesUrl"/>

<spring:message code="addSystemClass.pageHeader.label" var="headerLbl" /> 
<spring:message code="addSystemClass.form.infoValidationError.label" var="infoValidationErrorLbl"/>
<spring:message code="addSystemClass.form.symbol.label" var="symbolLbl"/>
<spring:message code="addSystemClass.form.name.label" var="nameLbl"/>
<spring:message code="addSystemClass.form.value.label" var="valueLbl"/>
<spring:message code="addSystemClass.form.description.label" var="descriptionLbl"/>
<spring:message code="addSystemClass.linkToClasses.button.label" var="listOfClasses"/>
<spring:message code="addSystemClass.form.submitForm.button.label" var="submitFormLbl" />

    <jsp:include page="./fragments/header.jsp"/>
    
    <section class="main">
        
        <jsp:include page="./fragments/navi.jsp"/>
      
        <div class="container">
            <div class="row">
                <div class="page-header">
                    <h2> ${headerLbl} </h2>
                </div>
            </div>
            <div class="row">
                <form:form modelAttribute="newSystemClass" class="form-horizontal" type="post">
                    <spring:bind path="*">
                        <c:if test="${status.error}">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4> ${infoValidationErrorLbl} </h4>
                            </div>
                        </c:if>
                    </spring:bind>
                    
                    <div class="row">
                        <spring:bind path="symbol">
                            <div class="form-group ${status.error?'has-error':''}">
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                    <label for="symbol" class="control-label"> ${symbolLbl} </label>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                    <form:input type="text" id="symbol" path="symbol" class="form-control"/>
                                    <form:errors path="symbol" class="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                    </div>
                    
                    <div class="row">
                        <spring:bind path="name">
                            <div class="form-group ${status.error?'has-error':''}">
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                    <label for="name" class="control-label"> ${nameLbl} </label>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                    <form:input type="text" id="name" path="name" class="form-control"/>
                                    <form:errors path="name" class="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                    </div>
                    
                    <div class="row">
                        <spring:bind path="value">
                            <div class="form-group ${status.error?'has-error':''}">
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                    <label for="value" class="control-label"> ${valueLbl} </label>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                    <form:input type="text" id="value" path="value" class="form-control"/>
                                    <form:errors path="value" class="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                    </div>
                    
                    <div class="row">
                        <spring:bind path="description">
                            <div class="form-group ${status.error?'has-error':''}">
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 text-right">
                                    <label for="description" class="control-label"> ${descriptionLbl} </label>
                                </div>
                                <div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
                                    <form:textarea type="text" rows="3" id="description" path="description" class="form-control"/>
                                    <form:errors path="description" class="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                    </div>
                    
                    <hr>
                    <div class="row">
                        <div class="col-xs-11 col-sm-11 col-md-10 col-lg-10 text-right">
                            <a href="${classesUrl}" class="btn btn-default"><span class="glyphicon glyphicon-hand-left"></span> ${listOfClasses}</a>
                            <button type="submit" class="btn btn-primary"> ${submitFormLbl} </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
        
    <jsp:include page="./fragments/footer.jsp"/>
        
    </body>
</html>