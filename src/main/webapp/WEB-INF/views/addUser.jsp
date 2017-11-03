<%-- 
    Document   : addUser
    Created on : 2017-10-24, 01:58:58
    Author     : mirek
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<spring:url value="/home" var="homeUrl"/>
<spring:message code="newUser.pageHeader.label" var="headerLbl"/>
<spring:message code="newUser.form.infoValidationError.label" var="infoValidationErrorLbl"/>
<spring:message code="newUser.form.login.label" var="loginLbl"/>
<spring:message code="newUser.form.password.label" var="passwordLbl"/>
<spring:message code="newUser.form.repeatPassword.label" var="repeatPasswordLbl"/>
<spring:message code="newUser.form.firstName.label" var="firstNameLbl"/>
<spring:message code="newUser.form.lastName.label" var="lastNameLbl"/>
<spring:message code="newUser.form.email.label" var="emailLbl"/>
<spring:message code="newUser.form.role.label" var="roleLbl"/>
<spring:message code="newUser.linkToHomepage.button.label" var="backToHomeLbl"/>
<spring:message code="newUser.form.submitForm.button.label" var="submitFormLbl" />


<jsp:include page="./fragments/header.jsp" /> 

        <section class="main">
            
            <jsp:include page="./fragments/navi.jsp"/>
            
            <div class="container">
                <div class="row">
                    <div class="page-header text-center">
                        <h2> ${headerLbl} </h2>
                    </div>
                </div>
                        
                <form:form modelAttribute="newUser" class="form-horizontal">
                    <fildset>
                        <spring:bind path="*">
                            <c:if test="${status.error}">
                                <div class="alert alert-danger alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4> ${infoValidationErrorLbl} </h4>
                                </div>
                            </c:if>
                        </spring:bind>
                        
                        <div class="row">
                            <spring:bind path="firstName">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="firstName" class="control-label"> ${firstNameLbl}  </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="firstName" class="control-label"> ${firstNameLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="firstName" path="firstName" class="form-control" placeholder="${fn:toLowerCase(firstNameLbl)}"/>
                                        <form:errors path="firstName" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="lastName">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="lastName" class="control-label"> ${lastNameLbl}  </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="lastName" class="control-label"> ${lastNameLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="lastName" path="lastName" class="form-control" placeholder="${fn:toLowerCase(lastNameLbl)}"/>
                                        <form:errors path="lastName" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="email">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="email" class="control-label"> ${emailLbl}  </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="email" class="control-label"> ${emailLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="email" id="email" path="email" class="form-control" placeholder="${fn:toLowerCase(emailLbl)}"/>
                                        <form:errors path="email" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="login">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="login" class="control-label"> ${loginLbl} </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="login" class="control-label"> ${loginLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:input type="text" id="login" path="login" class="form-control" placeholder="${fn:toLowerCase(loginLbl)}"/>
                                        <form:errors path="login" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="password">
                                <div class="form-group">
                                    <spring:bind path="password">
                                        <c:if test="${status.error}"> <c:set value="${true}" var="isErrorPassword"/> </c:if>
                                    </spring:bind>

                                    <spring:bind path="repeatPassword">
                                        <c:if test="${status.error}"> <c:set value="${true}" var="isErrorRepeatPassword"/> </c:if>
                                    </spring:bind>
                                    
                                    <div class="${isErrorPassword || isErrorRepeatPassword?'has-error':''}">
                                        <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                            <label for="password" class="control-label"> ${passwordLbl} </label>
                                        </div>

                                        <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                            <label for="password" class="control-label"> ${passwordLbl} </label>
                                        </div>
                                    </div>
                                    
                                    <div class="${isErrorPassword?'has-error':''} col-xs-offset-1 col-xs-5 col-sm-offset-0 col-sm-4 col-md-3 col-lg-3">
                                        <form:input type="password" id="password" path="password" class="form-control" 
                                                    placeholder="${fn:toLowerCase(passwordLbl)}"/>
                                        <form:errors path="password" class="text-danger"/>
                                    </div>
                                    
                                    <div class="${isErrorRepeatPassword?'has-error':''} col-xs-5 col-sm-4 col-md-3 col-lg-3">
                                        <form:input type="password" id="repeatPassword" path="repeatPassword" class="form-control" 
                                                    placeholder="${fn:toLowerCase(repeatPasswordLbl)}"/>
                                        <form:errors path="repeatPassword" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="role">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="role" class="control-label"> ${roleLbl}  </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="login" class="control-label"> ${roleLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <security:authorize access="isAnonymous() or hasRole('USER')">
                                            <form:select id="role" path="role" class="form-control">
                                                <option value="ROLE_USER">User</option>
                                            </form:select>
                                        </security:authorize>
                                        <security:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                            <form:select id="role" path="role" class="form-control" items="${role}" itemValue="name" itemLabel="description"/>
                                        </security:authorize>
                                        <form:errors path="role" class="text-danger"/>
                                    </div>
                                </div>  
                            </spring:bind>
                        </div>
                        <hr>
                        
                        <div class="row">
                            <div class="col-xs-11 col-sm-11 col-md-9 col-lg-9 text-right">
                                <a href="${homeUrl}" class="btn btn-default"><span class="glyphicon glyphicon-hand-left"></span> ${backToHomeLbl} </a>
                                <button type="submit" class="btn btn-primary"> ${submitFormLbl} </button>
                            </div>
                        </div>
                    </fildset>
                </form:form>
            </div>
        </section>
    
    <jsp:include page="./fragments/footer.jsp"/>
    </body>
</html>
