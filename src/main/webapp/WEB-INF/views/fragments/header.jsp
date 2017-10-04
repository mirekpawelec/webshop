<%-- 
    Document   : header
    Created on : 2017-09-14, 17:45:45
    Author     : mirek
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    
    <spring:url value="/resource/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resource/css/style.css" var="styleCss" />
    
    <c:choose>
        <c:when test="${not empty jspFile}">
            <title>
                <spring:message code="${jspFile}.titlePage.label"/>
            </title>
        </c:when>
        <c:otherwise>
            <title>unknown</title>
        </c:otherwise>
    </c:choose>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${bootstrapCss}" />
    <link rel="stylesheet" href="${styleCss}" />
    <style>
        section.main{
            min-height: 850px;
            text-align: center;
        }
        hr.gray{
            margin: 15px;
            border-top: 1px solid #ccc;
            width: 100%;
        }
        footer{
            font-size: 17px;
            padding: 25px 0;
            margin: 10px 0;
            text-align: center;
            color: #FFFFFF;
            background-color: #000000;
        }
        
    </style>
</head>
<body>
    <header>
        
        <spring:url value="/home" var="homePage"/>
        <spring:url value="" var="contact" />
        
        <spring:message code="header.navi.nameHomePage.label" var="homepageLbl"/>
        <spring:message code="header.navi.home.label" var="homeLbl"/> 
        <spring:message code="header.navi.contact.label" var="contactLbl"/>
        
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${homePage}"> ${homepageLbl} </a>
                </div>
                <div class="navbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="${homePage}"> ${homeLbl} </a></li>
                        <li><a href="${contact}"> ${contactLbl} </a></li>
                    </ul>
                </div>
            </div>
        </nav>    
                    
    </header>