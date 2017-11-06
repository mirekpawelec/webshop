<%-- 
    Document   : appParameters
    Created on : 2017-10-19, 12:56:15
    Author     : mirek
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="appParameters.pageHeader.label" var="headerLbl" />
<spring:message code="appParameters.table.processResult.create.message" arguments="${createInfo}" var="createMessage"/>
<spring:message code="appParameters.table.processResult.update.message" arguments="${updateInfo}" var="updateMessage"/>
<spring:message code="appParameters.table.processResult.delete.message" arguments="${deleteInfo}" var="deleteMessage"/>
<spring:message code="appParameters.table.symbol.label" var="tabSymbolLbl"/>
<spring:message code="appParameters.table.name.label" var="tabNameLbl"/>
<spring:message code="appParameters.table.value.label" var="tabValueLbl"/>
<spring:message code="appParameters.table.description.label" var="tabDescriptionLbl"/>
<spring:message code="appParameters.table.lastModificationDate.label" var="tabLastModificationDateLbl"/>
<spring:message code="appParameters.table.createDate.label" var="tabCreateDateLbl"/>
<spring:message code="appParameters.table.action.label" var="tabActionLbl"/>
<spring:message code="appParameters.table.update.button.label" var="tabBtnUpdateLbl"/>
<spring:message code="appParameters.table.delete.button.label" var="tabBtnDeleteLbl"/>

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
                    <c:when test="${create}">
                        <div class="alert alert-${css} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${createMessage} </strong>
                        </div>  
                    </c:when>
                    <c:when test="${update}">
                        <div class="alert alert-${css} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${updateMessage} </strong>
                        </div>  
                    </c:when>
                    <c:when test="${delete}">
                        <div class="alert alert-${css} alert-dismissable" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong> ${deleteMessage} </strong>
                        </div>                        
                    </c:when>
                </c:choose>
            </div>
            <div class="row">
                <div class="table-responsive text-left">
                    <table class="table table-striped table-hover">
                        <thead>
                                <tr>
                                    <th>#</th>
                                    <th>${tabSymbolLbl}</th>
                                    <th>${tabNameLbl}</th>
                                    <th>${tabValueLbl}</th>
                                    <th>${tabDescriptionLbl}</th>
                                    <th>${tabLastModificationDateLbl}</th>
                                    <th>${tabCreateDateLbl}</th>
                                    <th>${tabActionLbl}</th>
                                </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${allParameters}" var="item" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.symbol}</td>
                                    <td>${item.name}</td>
                                    <td>${item.value}</td>
                                    <td>${item.description}</td>
                                    <td>${fn:replace(item.lastModificationDate,'T',' ')}</td>
                                    <td>${fn:replace(item.createDate,'T',' ')}</td>
                                    <td>           
                                        <spring:url value="/admin/parameters/${item.parameterId}/update" var="updateUrl"/>
                                        <spring:url value="/admin/parameters/${item.parameterId}/delete" var="deleteUrl"/> 

                                        <a href="${updateUrl}" class="btn btn-primary btn-xs">
                                            <span class="glyphicon glyphicon-edit"> </span> ${tabBtnUpdateLbl} 
                                        </a> 
                                        <a href="#delete${item.parameterId}" class="btn btn-danger btn-xs" data-toggle="modal">
                                            <span class="glyphicon glyphicon-remove"> </span> ${tabBtnDeleteLbl} 
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
        
    <spring:message code="appParameters.modal.deleteItem.header.label" var="headerDeleteModalLbl" />
    <spring:message code="appParameters.modal.deleteItem.question.part1.message" var="questionDeleteModalMsg_1" />
    <spring:message code="appParameters.modal.deleteItem.question.part2.message" var="questionDeleteModalMsg_2" />
    <spring:message code="appParameters.modal.deleteItem.confirmYes.label" var="confirmYesDeleteModalLbl" />
    <spring:message code="appParameters.modal.deleteItem.confirmNo.label" var="confirmNoDeleteModalLbl" />
    
    <c:forEach items="${allParameters}" var="item">
        <div id="delete${item.parameterId}" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4><strong> ${headerDeleteModalLbl} </strong></h4>
                </div>
                <div class="modal-body">
                    <h4> ${item.symbol} - ${item.name} </h4> <br>
                    <p> ${questionDeleteModalMsg_1} </p>
                    <p> ${questionDeleteModalMsg_2} </p>
                </div>
                <div class="modal-footer">
                        <spring:url value="/admin/parameters/${item.parameterId}/delete" var="deleteUrl"/>  
                        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" > ${confirmNoDeleteModalLbl} </button>
                        <a href="${deleteUrl}" class="btn btn-danger btn-sm"> ${confirmYesDeleteModalLbl} </a>
                </div>
              </div>
            </div>
        </div>            
    </c:forEach>
    
    </body>
</html>
