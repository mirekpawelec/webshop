<%-- 
    Document   : showMessage
    Created on : 2017-11-01, 20:17:19
    Author     : mirek
--%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="showMessage.table.subject.label" var="subjectLbl" />
<spring:message code="showMessage.table.content.label" var="contentLbl" />

<div class="row">
    <div class="form-group">
        <div class="col-xs-offset-1 col-xs-10">
            <label class="control-label"><strong> ${subjectLbl} </strong></label>
        </div>
        <div class="col-xs-offset-1 col-xs-10">
            <input type="text" class="form-control" readonly value="${param.subject}"/>
        </div>
    </div>
</div>

<br>

<div class="row">
    <div class="form-group">
        <div class="col-xs-offset-1 col-xs-10">
            <label class="control-label"><strong> ${contentLbl} </strong></label>
        </div>
        <div class="col-xs-offset-1 col-xs-10">
            <textarea type="text" class="form-control" rows="6" readonly="true">${param.content}</textarea>
        </div>
    </div>
</div>
