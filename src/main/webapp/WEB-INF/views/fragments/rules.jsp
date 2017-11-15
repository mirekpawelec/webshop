<%-- 
    Document   : faq
    Created on : 2017-11-14, 22:49:03
    Author     : mirek
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<spring:message code="rules.header.message" var="headerRuleMsg"/>


<div class="container">                    
    <div class="row">
        <h3> ${headerRuleMsg} </h3>
        
        <div class="well-lg">
            ${contentRule}
        </div>
    </div>
</div>

