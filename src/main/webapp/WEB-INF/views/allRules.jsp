<%-- 
    Document   : users
    Created on : 2017-10-19, 12:56:15
    Author     : mirek
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="allRules.pageHeader.label" var="headerLbl" />
<spring:message code="allRules.info.processResult.create.message" arguments="${createInfo}" var="createMessage"/>
<spring:message code="allRules.info.processResult.update.message" arguments="${updateInfo}" var="updateMessage"/>
<spring:message code="allRules.info.processResult.delete.message" arguments="${deleteInfo}" var="deleteMessage"/>
<spring:message code="allRules.table.name.label" var="nameTabLbl"/>
<spring:message code="allRules.table.contentRule.label" var="contentRuleTabLbl"/>
<spring:message code="allRules.table.description.label" var="descriptionTabLbl"/>
<spring:message code="allRules.table.status.label" var="statusTabLbl"/>
<spring:message code="allRules.table.status.ok.message" var="statusOkMsg"/>
<spring:message code="allRules.table.status.blocked.message" var="statusBlockMsg"/>
<spring:message code="allRules.table.status.canceled.message" var="statusCanceledMsg"/>
<spring:message code="allRules.table.status.undefined.message" var="statusUndefinedMsg"/>
<spring:message code="allRules.table.lastModificationDate.label" var="lastModificationDateTabLbl"/>
<spring:message code="allRules.table.createDate.label" var="createDateTabLbl"/>
<spring:message code="allRules.table.action.label" var="actionTabLbl"/>
<spring:message code="allRules.table.show.button.label" var="showTabBtnLbl"/>
<spring:message code="allRules.table.update.button.label" var="updateTabBtnLbl"/> 
<spring:message code="allRules.table.delete.button.label" var="deleteTabBtnLbl"/>


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
                <c:choose>
                    <c:when test="${not empty cssCreate}">
                        <div class="alert alert-${cssCreate} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${createMessage} </strong>
                        </div>  
                    </c:when>
                    <c:when test="${not empty cssUpdate}">
                        <div class="alert alert-${cssUpdate} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${updateMessage} </strong>
                        </div>  
                    </c:when>
                    <c:when test="${not empty cssDelete}">
                        <div class="alert alert-${cssDelete} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${deleteMessage} </strong>
                        </div>  
                    </c:when>
                </c:choose>
            </div>
            <div class="row faq">
                <div class="table-responsive text-left">
                    <table class="table table-striped table-hover">
                        <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>${nameTabLbl}</th>
                                    <th>${descriptionTabLbl}</th>
                                    <th>${statusTabLbl}</th>
                                    <th>${lastModificationDateTabLbl}</th>
                                    <th>${createDateTabLbl}</th>
                                    <th class="text-center">${actionTabLbl}</th>
                                </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${rules}" var="item" varStatus="counter">
                                <tr class="row-min-height">
                                    <td>${item.ruleId}</td>
                                    <td>${item.name}</td>
                                    <td>${item.description}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.status=='OK'}">
                                                ${statusOkMsg}
                                            </c:when>
                                            <c:when test="${item.status=='BL'}">
                                                ${statusBlockMsg}
                                            </c:when>
                                            <c:when test="${item.status=='FI'}">
                                                ${statusCanceledMsg}
                                            </c:when>
                                            <c:otherwise>
                                                ${statusUndefinedMsg}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="date-col-min-width">${fn:replace(item.lastModyficationDate,'T',' ')}</td>
                                    <td class="date-col-min-width">${fn:replace(item.createDate,'T',' ')}</td>
                                    <td class="but-col-min-width">     
                                        <a href="#rule${item.ruleId}" data-toggle="modal" class="btn btn-primary btn-xs">
                                                <span class="glyphicon glyphicon-check"> </span> ${showTabBtnLbl}
                                        </a>
                                        <spring:url value="/admin/rule/${item.ruleId}/update" var="updateUrl"/>
                                        <a href="${updateUrl}" class="btn btn-primary btn-xs">
                                            <span class="glyphicon glyphicon-edit"> </span> ${updateTabBtnLbl} 
                                        </a> 
                                        <a href="#delete${item.ruleId}" class="btn btn-danger btn-xs" data-toggle="modal">
                                            <span class="glyphicon glyphicon-remove"> </span> ${deleteTabBtnLbl} 
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
        
    <jsp:include page="./fragments/footer.jsp"/>
    
    
    <spring:message code="allRules.modal.close.button.label" var="closeProductModalBtnLbl"/>
    <spring:message code="allRules.modal.deleteItem.header.label" var="headerDeleteModalLbl" />
    <spring:message code="allRules.modal.deleteItem.question.part1.message" var="questionDeleteModalMsg_1" />
    <spring:message code="allRules.modal.deleteItem.question.part2.message" var="questionDeleteModalMsg_2" />
    <spring:message code="allRules.modal.deleteItem.confirmYes.label" var="confirmYesDeleteModalLbl" />
    <spring:message code="allRules.modal.deleteItem.confirmNo.label" var="confirmNoDeleteModalLbl" />
    
    
    <c:forEach var="item" items="${rules}">
        <div id="rule${item.ruleId}" class="modal fade modal-info" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content modal-lg">
                <div class="modal-header text-center">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h3> ${item.name} </h3>
                </div>
                <div class="modal-body">
                    <jsp:include page="fragments/showRule.jsp">
                        <jsp:param name="contentRule" value="${item.contentRule}"/>
                        <jsp:param name="status" value="${item.status}"/>
                        <jsp:param name="lastModificationDate" value="${fn:replace(item.lastModyficationDate,'T',' ')}"/>
                        <jsp:param name="createDate" value="${fn:replace(item.createDate,'T',' ')}"/>
                    </jsp:include>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" > ${closeProductModalBtnLbl} </button>
                </div>
              </div>
            </div>
        </div>
                
        <div id="delete${item.ruleId}" class="modal fade modal-delete" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4><strong> ${headerDeleteModalLbl} </strong></h4>
                </div>
                <div class="modal-body">
                    <h4> ${item.name} </h4> <br>
                    <p> ${questionDeleteModalMsg_1} </p>
                    <p> ${questionDeleteModalMsg_2} </p>
                </div>
                <div class="modal-footer">
                    <spring:url value="/admin/rule/${item.ruleId}/delete" var="deleteUrl"/> 
                    <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" > ${confirmNoDeleteModalLbl} </button>
                    <a href="${deleteUrl}" class="btn btn-danger btn-sm"> ${confirmYesDeleteModalLbl} </a>
                </div>
              </div>
            </div>
        </div>  
                
    </c:forEach>
    
    </body>
</html>
