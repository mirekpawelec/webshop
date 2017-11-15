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


<spring:url value="/admin/rules" var="rulesListUrl"/>
<spring:url value="/admin/rule/save" var="saveRuleUrl"/>

<spring:message code="addUpdateRule.pageHeader.create.label" var="headerCreateRuleLbl"/>
<spring:message code="addUpdateRule.pageHeader.update.label" var="headerUpdateRuleLbl"/>
<spring:message code="addUpdateRule.form.infoValidationError.label" var="infoValidationErrorLbl"/>
<spring:message code="addUpdateRule.form.name.label" var="nameLbl"/>
<spring:message code="addUpdateRule.form.contentRule.label" var="contentRuleLbl"/>
<spring:message code="addUpdateRule.form.description.label" var="descriptionLbl"/>
<spring:message code="addUpdateRule.form.status.label" var="statusLbl"/>
<spring:message code="addUpdateRule.form.status.ok.message" var="statusOkMsg"/>
<spring:message code="addUpdateRule.form.status.block.message" var="statusBlockMsg"/>
<spring:message code="addUpdateRule.form.status.canceled.message" var="statusCanceledMsg"/>
<spring:message code="addUpdateRule.form.status.undefined.message" var="statusUndefinedMsg"/>
<spring:message code="addUpdateRule.linkToRulesList.button.label" var="backToRulesListBtnLbl"/>
<spring:message code="addUpdateRule.form.submitForm.button.create.label" var="submitFormCreateBtnLbl" />
<spring:message code="addUpdateRule.form.submitForm.button.update.label" var="submitFormUpdateBtnLbl" />


<jsp:include page="./fragments/header.jsp" /> 

        <section class="main">
            
            
            <jsp:include page="./fragments/navi.jsp"/>
            
            <div class="container">
                <div class="row">
                    <div class="page-header text-center">
                        <h2> ${modelRule['new']? headerCreateRuleLbl : headerUpdateRuleLbl} </h2>
                    </div>
                </div>
                    
                <form:form modelAttribute="modelRule" action="${saveRuleUrl}" method="post" class="form-horizontal">
                    <fildset>    
                        <form:errors path="*"/>
                        <spring:bind path="*">
                            <c:if test="${status.error}">
                                <div class="alert alert-danger alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4> ${infoValidationErrorLbl} </h4>
                                </div>
                            </c:if>
                        </spring:bind>
                        
                        <c:if test="${!modelRule['new']}">
                            <form:hidden path="ruleId"/>
                        </c:if>
                        
                        <div class="row">
                            <spring:bind path="name">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-2 text-right">
                                        <label for="name" class="control-label"> ${nameLbl}  </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="name" class="control-label"> ${nameLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-9">
                                        <form:input type="text" id="name" path="name" class="form-control" placeholder="${fn:toLowerCase(nameLbl)}"/>
                                        <form:errors path="name" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="contentRule">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-2 text-right">
                                        <label for="contentRule" class="control-label"> ${contentRuleLbl}  </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="contentRule" class="control-label"> ${contentRuleLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-9">
                                        <form:textarea type="text" id="contentRule" path="contentRule" class="form-control" rows="20" placeholder="${fn:toLowerCase(contentRuleLbl)}"/>
                                        <form:errors path="contentRule" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="description">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-2 text-right">
                                        <label for="description" class="control-label"> ${descriptionLbl}  </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="description" class="control-label"> ${descriptionLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-9">
                                        <form:textarea type="text" id="description" path="description" class="form-control" rows="3" placeholder="${fn:toLowerCase(descriptionLbl)}"/>
                                        <form:errors path="description" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="status">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-2 text-right">
                                        <label for="status" class="control-label"> ${statusLbl}  </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="status" class="control-label"> ${statusLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-9">
                                        <form:select id="status" path="status" class="form-control">
                                                <option value="----------"></option>
                                            <c:forEach items="${statuses}" var="item">
                                                <option value="${item.name}" ${item.name==modelRule.status?'selected':''}>
                                                    <c:choose>
                                                        <c:when test="${item.name=='OK'}">
                                                            ${statusOkMsg}
                                                        </c:when>
                                                        <c:when test="${item.name=='BL'}">
                                                            ${statusBlockMsg}
                                                        </c:when>
                                                        <c:when test="${item.name=='FI'}">
                                                            ${statusCanceledMsg}
                                                        </c:when>
                                                        <c:otherwise>
                                                            ${statusUndefinedMsg}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors path="status" class="text-danger"/>
                                    </div>
                                </div>  
                            </spring:bind>
                        </div>
                        
                        <form:hidden path="lastModyficationDate" />
                        <form:hidden path="createDate" />
                        
                        <hr>
                        <div class="row">
                            <div class="col-xs-11 text-right">
                                <a href="${rulesListUrl}" class="btn btn-default"><span class="glyphicon glyphicon-hand-left"></span> ${backToRulesListBtnLbl} </a>        
                                <button type="submit" class="btn btn-primary"> ${modelRule['new']?submitFormCreateBtnLbl:submitFormUpdateBtnLbl} </button>
                            </div>
                        </div>
                    </fildset>
                </form:form>
            </div>
        </section>
    
    <jsp:include page="./fragments/footer.jsp"/>
    </body>
</html>
