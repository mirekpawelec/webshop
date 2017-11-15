<%-- 
    Document   : showMessage
    Created on : 2017-11-01, 20:17:19
    Author     : mirek
--%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<spring:message code="showRule.contentRule.label" var="contentRuleLbl" />
<spring:message code="showRule.status.label" var="statusLbl" />
<spring:message code="showRule.lastModificationDate.label" var="lastModificationDateLbl" />
<spring:message code="showRule.createDate.label" var="createDateLbl" />

<div class="row">
    <div class="form-group">
        <div class="col-xs-12">
            <label class="control-label"><strong> ${contentRuleLbl} </strong></label>
        </div>
        <div class="col-xs-12">
            <textarea type="text" class="form-control" rows="20" readonly="true"> ${param.contentRule} </textarea>
        </div>
    </div>
</div>

<br>

<div class="row min-width">
    <div class="col-xs-12 col-sm-4">
        <div class="form-group">
            <div class="col-xs-12">
                <label class="control-label"><strong> ${statusLbl} </strong></label>
            </div>
            <div class="col-xs-12">
                <input type="text" class="form-control" value="${param.status}" readonly="true" />
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-4">
        <div class="form-group">
            <div class="col-xs-12">
                <label class="control-label"><strong> ${lastModificationDateLbl} </strong></label>
            </div>
            <div class="col-xs-12">
                <input type="text" class="form-control" value="${param.lastModificationDate}" readonly="true" />
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-4">
        <div class="form-group">
            <div class="col-xs-12">
                <label class="control-label"><strong> ${createDateLbl} </strong></label>
            </div>
            <div class="col-xs-12">
                <input type="text" class="form-control" value="${param.createDate}" readonly="true" />
            </div>
        </div>
    </div>
</div>
