<%-- 
    Document   : login
    Created on : 2017-10-23, 13:45:25
    Author     : mirek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url value="/login" var="loginUrl"/>
<c:url value="/user/add" var="registerUrl"/>
<spring:message code="login.form.panelTitle.message" var="panelTitleMsg"/>
<spring:message code="login.form.invalidLogin.message" var="invalidLoginMsg"/>
<spring:message code="login.form.loggedOut.message" var="loggedOutMsg"/>
<spring:message code="login.form.accessDenied.message" var="accessDeniedMsg"/>
<spring:message code="login.form.input.username.label" var="usernameLbl"/>
<spring:message code="login.form.input.password.label" var="passwordLbl"/>
<spring:message code="login.form.button.login.lebel" var="loginBtn"/> 
<spring:message code="login.form.link.forgotPassword.lebel" var="forgotPasswordLbl"/>
<spring:message code="login.form.link.addAccount.lebel" var="addAccountLbl"/>

<jsp:include page="./fragments/header.jsp"/>      

    <section class="main">
        <jsp:include page="./fragments/navi.jsp"/>
        <hr>

        <div class="container">
            <br><br><br><br><br><br>
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"> ${panelTitleMsg} </h3>
                        </div>
                        <div class="panel-body">                            
                            <form action="${loginUrl}" method="post" class="form-horizontal">
                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger">
                                        <p> ${invalidLoginMsg} </p>
                                    </div>
                                </c:if>
                            
                                <c:if test="${param.logout != null}">
                                    <div class="alert alert-success">
                                        <p> ${loggedOutMsg} </p>
                                    </div>
                                </c:if>
                            
                                <c:if test="${param.accessDenied != null}">
                                    <div class="alert alert-danger">
                                        <p> ${accessDeniedMsg} </p>
                                    </div>
                                </c:if>

                                <div class="input-group input-lg">
                                    <label class="input-group-addon" for="username"><span class="glyphicon glyphicon-user"></span></label>
                                    <input type="text" class="form-control" id="username" name="username" placeholder="${usernameLbl}" required>
                                </div>
                            
                                <div class="input-group input-lg">
                                    <label class="input-group-addon" for="password"><span class="glyphicon glyphicon-pencil"></span></label>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="${passwordLbl}" required>
                                </div>

                                <hr>
                                <div class="form-actions">
                                    <button type="submit" class="btn btn-block btn-primary btn-default">
                                        <span class="glyphicon glyphicon-lock"></span> ${loginBtn}
                                    </button>
                                </div>
                                
                                <security:authorize access="isAnonymous()">
                                    <br>
                                    <div class="small">
                                        <a href="" class="pull-left"> ${forgotPasswordLbl} </a>
                                        <a href="${registerUrl}" class="pull-right"> ${addAccountLbl} </a>
                                    </div>
                                </security:authorize>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="./fragments/footer.jsp"/>
    
    </body>
</html>
