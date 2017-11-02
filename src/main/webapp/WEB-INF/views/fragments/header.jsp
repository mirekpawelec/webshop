<%-- 
    Document   : header
    Created on : 2017-09-14, 17:45:45
    Author     : mirek
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<spring:url value="/resource/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resource/css/style.css" var="styleCss" />
<spring:url value="/home" var="homePage"/>
<spring:url value="#contact" var="contact" />
<spring:url value="/cart" var="cartUrl"/>
<spring:url value="/login" var="logInUrl"/>
<spring:url value="/logout" var="logOutUrl"/>
<spring:url value="${lastRequestUrl}language=pl" var="polishLanguageUrl"/>
<spring:url value="${lastRequestUrl}language=en" var="englishLanguageUrl"/>

<spring:message code="header.navi.nameHomePage.label" var="homepageLbl"/>
<spring:message code="header.navi.home.label" var="homeLbl"/> 
<spring:message code="header.navi.contact.label" var="contactLbl"/>
<spring:message code="header.navi.cart.label" var="cartLbl" />
<spring:message code="header.navi.logIn.label" var="logInLbl" />
<spring:message code="header.navi.logOut.label" var="logOutLbl" />
<spring:message code="navi.button.selectLanguages.label" var="selectLanguageLbl"/>
<spring:message code="navi.button.polishLanguage.label" var="polishLbl" />
<spring:message code="navi.button.englishLanguage.label" var="englishLbl" />
            
<!DOCTYPE html>
<html lang="pl" ng-app="cartApp">
    <head>
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
    </head>
    <body>
        <header>            
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#me-menu">
                            <span class="sr-only">Nawigacja</span> 
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        
                        <a class="navbar-brand" href="${homePage}"> ${homepageLbl} </a>
                    </div>
                    
                    <div class="collapse navbar-collapse" id="me-menu">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active"><a href="${homePage}"> ${homeLbl} </a></li>
                            <li><a href="${contact}"> ${contactLbl} </a></li>
                            <li>
                                <a href="${cartUrl}"> 
                                    <span class="glyphicon glyphicon-shopping-cart"></span> 
                                    <span id="itemCounterOfCart" class="badge" ng-bind="numberOfItems"></span>
                                </a>
                            </li>
                            
                        <security:authorize access="isAuthenticated()"> 
                            <li>
                                <a>
                                    <span class="glyphicon glyphicon-user nav-sign"></span> 
                                    <span class="badge nav-text"> 
                                        <security:authentication property="principal.fullName" />
                                    </span> 
                                </a>
                            </li>
                        </security:authorize>
                            
                        <security:authorize access="isAnonymous()">
                            <li>
                                <a href="${logInUrl}">
                                    <span class="glyphicon glyphicon-log-in nav-sign"></span> 
                                    <span class="badge nav-text"> ${logInLbl} </span>
                                </a>
                            </li>
                        </security:authorize>
                            
                        <security:authorize access="isAuthenticated()">  
                            <li>
                                <a href="${logOutUrl}">
                                    <span class="glyphicon glyphicon-log-out nav-sign"></span> 
                                    <span class="badge nav-text"> ${logOutLbl} </span> 
                                </a>
                            </li>
                        </security:authorize>
                            
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                     ${selectLanguageLbl} <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${polishLanguageUrl}"> ${polishLbl} </a></li>
                                    <li><a href="${englishLanguageUrl}"> ${englishLbl} </a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>    
        </header>
