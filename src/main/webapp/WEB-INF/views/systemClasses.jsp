<%-- 
    Document   : systemClasses
    Created on : 2017-10-19, 12:56:15
    Author     : mirek
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="systemClasses.pageHeader.label" var="headerLbl" />
<spring:message code="systemClasses.table.processResult.create.message" arguments="${createInfo}" var="createMessage"/>
<spring:message code="systemClasses.table.processResult.update.message" arguments="${updateInfo}" var="updateMessage"/>
<spring:message code="systemClasses.table.processResult.delete.message" arguments="${deleteInfo}" var="deleteMessage"/>
<spring:message code="systemClasses.table.symbol.label" var="tabSymbolLbl"/>
<spring:message code="systemClasses.table.name.label" var="tabNameLbl"/>
<spring:message code="systemClasses.table.value.label" var="tabValueLbl"/>
<spring:message code="systemClasses.table.description.label" var="tabDescriptionLbl"/>
<spring:message code="systemClasses.table.lastModificationDate.label" var="tabLastModificationDateLbl"/>
<spring:message code="systemClasses.table.createDate.label" var="tabCreateDateLbl"/>
<spring:message code="systemClasses.table.action.label" var="tabActionLbl"/>
<spring:message code="systemClasses.table.update.button.label" var="tabBtnUpdateLbl"/>
<spring:message code="systemClasses.table.delete.button.label" var="tabBtnDeleteLbl"/>

    <jsp:include page="./fragments/header.jsp"/>
    
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
                            <c:forEach items="${systemClasses}" var="item" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.symbol}</td>
                                    <td>${item.name}</td>
                                    <td>${item.value}</td>
                                    <td>${item.description}</td>
                                    <td>${fn:replace(item.lastModificationDate,'T',' ')}</td>
                                    <td>${fn:replace(item.createDate,'T',' ')}</td>
                                    <td>           
                                        <spring:url value="/admin/classes/${item.classId}/update" var="updateUrl"/>
                                        <spring:url value="/admin/classes/${item.classId}/delete" var="deleteUrl"/> 

                                        <a href="${updateUrl}" class="btn btn-primary btn-xs">
                                            <span class="glyphicon glyphicon-edit"> </span> ${tabBtnUpdateLbl} 
                                        </a> 
                                        <a href="${deleteUrl}" class="btn btn-danger btn-xs">
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
        
    </body>
</html>
