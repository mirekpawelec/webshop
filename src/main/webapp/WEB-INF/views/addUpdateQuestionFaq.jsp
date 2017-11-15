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


<spring:url value="/admin/faq" var="questionListUrl"/>
<spring:url value="/admin/faq/save" var="saveQuestionUrl"/>
<%--<spring:url value="/admin/faq/update" var="updateQuestionUrl"/>--%>

<spring:message code="addUpdateQuestionFaq.pageHeader.create.label" var="headerCreateQuestionLbl"/>
<spring:message code="addUpdateQuestionFaq.pageHeader.update.label" var="headerUpdateQuestionLbl"/>
<spring:message code="addUpdateQuestionFaq.form.infoValidationError.label" var="infoValidationErrorLbl"/>
<spring:message code="addUpdateQuestionFaq.form.question.label" var="questionLbl"/>
<spring:message code="addUpdateQuestionFaq.form.answer.label" var="answerLbl"/>
<spring:message code="addUpdateQuestionFaq.form.status.label" var="statusLbl"/>
<spring:message code="addUpdateQuestionFaq.form.status.ok.message" var="statusOkMsg"/>
<spring:message code="addUpdateQuestionFaq.form.status.block.message" var="statusBlockMsg"/>
<spring:message code="addUpdateQuestionFaq.form.status.canceled.message" var="statusCanceledMsg"/>
<spring:message code="addUpdateQuestionFaq.form.status.undefined.message" var="statusUndefinedMsg"/>
<spring:message code="addUpdateQuestionFaq.linkToQuestionList.button.label" var="backToQuestionListBtnLbl"/>
<spring:message code="addUpdateQuestionFaq.form.submitForm.button.create.label" var="submitFormCreateBtnLbl" />
<spring:message code="addUpdateQuestionFaq.form.submitForm.button.update.label" var="submitFormUpdateBtnLbl" />


<jsp:include page="./fragments/header.jsp" /> 

        <section class="main">
            
            
            <jsp:include page="./fragments/navi.jsp"/>
            
            <div class="container">
                <div class="row">
                    <div class="page-header text-center">
                        <h2> ${modelFaq['new']? headerCreateQuestionLbl : headerUpdateQuestionLbl} </h2>
                    </div>
                </div>
                    <!--action="$ {modelFaq['new']? addQuestionUrl : updaterQuestionUrl}"-->
                <form:form modelAttribute="modelFaq" action="${saveQuestionUrl}" method="post" class="form-horizontal">
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
                        
                        <c:if test="${!modelFaq['new']}">
                            <form:hidden path="faqId"/>
                        </c:if>
                        
                        <div class="row">
                            <spring:bind path="question">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="question" class="control-label"> ${questionLbl}  </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="question" class="control-label"> ${questionLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:textarea type="text" id="question" path="question" class="form-control" rows="4" placeholder="${fn:toLowerCase(questionLbl)}"/>
                                        <form:errors path="question" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>
                        
                        <div class="row">
                            <spring:bind path="answer">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="answer" class="control-label"> ${answerLbl}  </label>
                                    </div>
                                    
                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="answer" class="control-label"> ${answerLbl} </label>
                                    </div>
                                    
                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:textarea type="text" id="answer" path="answer" class="form-control" rows="12" placeholder="${fn:toLowerCase(answerLbl)}"/>
                                        <form:errors path="answer" class="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                        </div>

                        <div class="row">
                            <spring:bind path="status">
                                <div class="form-group ${status.error?'has-error':''}">
                                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3 text-right">
                                        <label for="status" class="control-label"> ${statusLbl}  </label>
                                    </div>

                                    <div class="visible-xs col-xs-offset-1 col-xs-10 text-left">
                                        <label for="status" class="control-label"> ${statusLbl} </label>
                                    </div>

                                    <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-8 col-md-6 col-lg-6">
                                        <form:select id="status" path="status" class="form-control">
                                                <option value="----------"></option>
                                            <c:forEach items="${statuses}" var="item">
                                                <option value="${item.name}" ${item.name==modelFaq.status?'selected':''}>
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
                            <div class="col-xs-11 col-sm-11 col-md-9 col-lg-9 text-right">
                                <a href="${questionListUrl}" class="btn btn-default"><span class="glyphicon glyphicon-hand-left"></span> ${backToQuestionListBtnLbl} </a>        
                                <button type="submit" class="btn btn-primary"> ${modelFaq['new']?submitFormCreateBtnLbl:submitFormUpdateBtnLbl} </button>
                            </div>
                        </div>
                    </fildset>
                </form:form>
            </div>
        </section>
    
    <jsp:include page="./fragments/footer.jsp"/>
    </body>
</html>
