<%-- 
    Document   : clientMessages
    Created on : 2017-11-01, 19:12:26
    Author     : mirek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="clientMessage.pageHeader.label" var="headerLbl" />
<spring:message code="clientMessage.table.name.label" var="nameLbl" />
<spring:message code="clientMessage.table.email.label" var="emailLbl" />
<spring:message code="clientMessage.table.subject.label" var="subjectLbl" />
<spring:message code="clientMessage.table.content.label" var="contentLbl" />
<spring:message code="clientMessage.table.status.label" var="statusLbl" />
<spring:message code="clientMessage.table.createDate.label" var="createDateLbl" />
<spring:message code="clientMessage.table.action.label" var="actionLbl" /> 
<spring:message code="clientMessage.table.button.show.label" var="showTabBtn" />
<spring:message code="clientMessage.table.button.delete.label" var="deleteTabBtn" />
<spring:message code="clientMessage.delete.message" arguments="${infoDeletedMessage}" var="deleteMsg" />
<spring:message code="clientMessage.modal.header.label" var="modalHeaderLbl" />
<spring:message code="clientMessage.modal.button.delete.label" var="modalDeleteBtn" />
<spring:message code="clientMessage.modal.button.answer.label" var="modalAnswerBtn" />
<spring:message code="clientMessage.modal.button.read.label" var="modalReadBtn" />
<spring:message code="clientMessage.modal.button.forLater.label" var="modalForLaterBtn" />
<spring:message code="clientMessage.modal.button.close.label" var="modalCloseBtn" />
<spring:message code="clientMessage.modal.deleteItem.header.label" var="headerDeleteModalLbl" />
<spring:message code="clientMessage.modal.deleteItem.question.part1.message" var="questionDeleteModalMsg_1" />
<spring:message code="clientMessage.modal.deleteItem.question.part2.message" var="questionDeleteModalMsg_2" />
<spring:message code="clientMessage.modal.deleteItem.confirmYes.label" var="confirmYesDeleteModalLbl" />
<spring:message code="clientMessage.modal.deleteItem.confirmNo.label" var="confirmNoDeleteModalLbl" />

    <jsp:include page="./fragments/header.jsp" />
        
        <section class="main">       
            <jsp:include page="./fragments/navi.jsp"/>
            
            <hr>
            
            <div class="container">
                <div class="row">
                    <div class="page-header">
                        <h2> ${headerLbl} </h2>
                    </div>
                </div>
                <div class="row">
                    <c:if test="${not empty deletedMessage}">
                        <div class="alert alert-${deletedMessage} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${deleteMsg} </strong>
                        </div>  
                    </c:if>
                </div>
                <div class="row">                    
                    <div class="table-responsive text-left">
                        <table id="sortTable" class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Lp.</th>
                                    <th>${nameLbl}</th>
                                    <th>${emailLbl}</th>
                                    <th>${subjectLbl}</th>
                                    <th>${statusLbl}</th>
                                    <th>${createDateLbl}</th>
                                    <th>${actionLbl}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${messages}" var="item" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${item.name}</td>
                                        <td>${item.email}</td>
                                        <td>${item.subject}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.status=='NE'}">
                                                    <spring:message code="clientMessage.table.status.new.label"/>
                                                </c:when>
                                                <c:when test="${item.status=='RE'}">
                                                    <spring:message code="clientMessage.table.status.read.label"/>
                                                </c:when>
                                                <c:when test="${item.status=='WT'}">
                                                    <spring:message code="clientMessage.table.status.wait.label"/>
                                                </c:when>
                                                <c:when test="${item.status=='OK'}">
                                                    <spring:message code="clientMessage.table.status.answered.label"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <spring:message code="clientMessage.table.status.undefined.label"/>
                                                </c:otherwise>  
                                            </c:choose>
                                        </td>
                                        <td>${fn:replace(item.createDate, 'T' , ' ')}</td>
                                        
                                        <td>
                                            <a href="#message${item.messageId}" data-toggle="modal" class="btn btn-primary btn-xs"> ${showTabBtn} </a>
                                            <a href="#delete${item.messageId}" data-toggle="modal" class="btn btn-danger btn-xs"> ${deleteTabBtn} </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
    
        </section>
    
    <jsp:include page="./fragments/footer.jsp" />
    
    <c:forEach items="${messages}" var="item">
        <div id="message${item.messageId}" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4> ${modalHeaderLbl} <strong> ${item.name} </strong> ( <i> ${item.email} </i> )</h4>
                </div>
                <div class="modal-body">
                    <jsp:include page="./fragments/showMessage.jsp">
                        <jsp:param name="subject" value="${item.subject}"/>
                        <jsp:param name="content" value="${item.content}"/>
                    </jsp:include>
                </div>
                <div class="modal-footer">
                        <c:url value="" var="answerUrl"/>
                        <c:url value="/admin/messages/${item.messageId}/RE/modify" var="reeadUrl"/>
                        <c:url value="/admin/messages/${item.messageId}/WT/modify" var="waitUrl"/>
                        <a href="#delete${item.messageId}" class="btn btn-danger btn-sm pull-left" data-toggle="modal"> ${modalDeleteBtn} </a>
                        <a href="${answerUrl}" class="btn btn-default btn-sm"> ${modalAnswerBtn} </a>
                        <a href="${reeadUrl}" class="btn btn-default btn-sm"> ${modalReadBtn} </a>
                        <a href="${waitUrl}" class="btn btn-default btn-sm"> ${modalForLaterBtn} </a>
                        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" > ${modalCloseBtn} </button>
                </div>
              </div>
            </div>
        </div>
                
        <div id="delete${item.messageId}" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4><strong> ${headerDeleteModalLbl} </strong></h4>
                </div>
                <div class="modal-body">
                    <h4> ${questionDeleteModalMsg_1} </h4>
                    <p> ${questionDeleteModalMsg_2} </p>
                </div>
                <div class="modal-footer">
                        <c:url value="/admin/messages/${item.messageId}/delete" var="deleteItemUrl"/>
                        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" > ${confirmNoDeleteModalLbl} </button>
                        <a href="${deleteItemUrl}" class="btn btn-danger btn-sm"> ${confirmYesDeleteModalLbl} </a>
                </div>
              </div>
            </div>
        </div>
    </c:forEach>
    
    </body>
</html>
