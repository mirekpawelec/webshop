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

<spring:message code="users.pageHeader.label" var="headerLbl" />
<spring:message code="users.info.processResult.create.message" arguments="${createInfo}" var="createMessage"/>
<spring:message code="users.info.processResult.update.message" arguments="${updateInfo}" var="updateMessage"/>
<spring:message code="users.table.login.label" var="tabLoginLbl"/>
<spring:message code="users.table.firstName.label" var="tabFirstNameLbl"/>
<spring:message code="users.table.lastName.label" var="tabLastNameLbl"/>
<spring:message code="users.table.email.label" var="tabEmailLbl"/>
<spring:message code="users.table.role.label" var="tabRoleLbl"/>
<spring:message code="users.table.status.label" var="tabStatusLbl"/>
<spring:message code="users.table.status.info.ok.message" var="statusInfoOkMsg"/>
<spring:message code="users.table.status.info.blocked.message" var="statusInfoBlockMsg"/>
<spring:message code="users.table.status.info.canceled.message" var="statusInfoCancelMsg"/>
<spring:message code="users.table.status.info.undefined.message" var="statusInfoUndefinedMsg"/>
<spring:message code="users.table.lastLoginDate.label" var="tabLastLoginLbl"/>
<spring:message code="users.table.lastModificationDate.label" var="tabLastModificationDateLbl"/>
<spring:message code="users.table.createDate.label" var="tabCreateDateLbl"/>
<spring:message code="users.table.action.label" var="tabActionLbl"/>
<spring:message code="users.table.update.button.label" var="tabUpdateBtnLbl"/>


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
                </c:choose>
            </div>
            <div class="row">
                <div class="table-responsive text-left">
                    <table class="table table-striped table-hover">
                        <thead>
                                <tr>
                                    <th>#</th>
                                    <th>${tabLoginLbl}</th>
                                    <th>${tabFirstNameLbl}</th>
                                    <th>${tabLastNameLbl}</th>
                                    <th>${tabEmailLbl}</th>
                                    <th>${tabRoleLbl}</th>
                                    <th>${tabStatusLbl}</th>
                                    <th>${tabLastLoginLbl}</th>
                                    <th>${tabLastModificationDateLbl}</th>
                                    <th>${tabCreateDateLbl}</th>
                                    <th>${tabActionLbl}</th>
                                </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${users}" var="item" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.login}</td>
                                    <td>${item.firstName}</td>
                                    <td>${item.lastName}</td>
                                    <td>${item.email}</td>
                                    <td>${item.role}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.status=='OK'}">
                                                ${statusInfoOkMsg}
                                            </c:when>
                                            <c:when test="${item.status=='BL'}">
                                                ${statusInfoBlockMsg}
                                            </c:when>
                                            <c:when test="${item.status=='FI'}">
                                                ${statusInfoCancelMsg}
                                            </c:when>
                                            <c:otherwise>
                                                ${statusInfoUndefinedMsg}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${fn:replace(item.lastLoginDate,'T',' ')}</td>
                                    <td>${fn:replace(item.lastModificationDate,'T',' ')}</td>
                                    <td>${fn:replace(item.createDate,'T',' ')}</td>
                                    <td>           
                                        <spring:url value="/admin/users/${item.userId}/update" var="updateUrl"/>
                                        <a href="${updateUrl}" class="btn btn-primary btn-xs">
                                            <span class="glyphicon glyphicon-edit"> </span> ${tabUpdateBtnLbl} 
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
