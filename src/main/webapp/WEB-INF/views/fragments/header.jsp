<%-- 
    Document   : header
    Created on : 2017-09-14, 17:45:45
    Author     : mirek
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl" ng-app="cartApp">
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
            <spring:url value="/cart" var="cartUrl"/>
            <spring:url value="/login" var="logInUrl"/>
            <spring:url value="/logout" var="logOutUrl"/>
            <spring:message code="header.navi.nameHomePage.label" var="homepageLbl"/>
            <spring:message code="header.navi.home.label" var="homeLbl"/> 
            <spring:message code="header.navi.contact.label" var="contactLbl"/>
            <spring:message code="header.navi.cart.label" var="cartLbl" />
            <spring:message code="header.navi.logIn.label" var="logInLbl" />
            <spring:message code="header.navi.logOut.label" var="logOutLbl" />
            
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container" style="margin: 10px auto; font-size: 17px;">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="${homePage}"> ${homepageLbl} </a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="${homePage}"> ${homeLbl} </a></li>
                        <li><a href="${contact}"> ${contactLbl} </a></li>
                        <li>
                            <a href="${cartUrl}"> 
                                <span class="glyphicon glyphicon-shopping-cart" style="margin-top: -9px; font-size: 35px;" ></span> 
                                <span id="itemCounterOfCart" class="badge" style="margin-top: -22px; font-size: 20px;" ng-bind="numberOfItems"></span>
                            </a>
                        </li>
                        <security:authorize access="isAuthenticated()"> 
                            <li>
                                <a>
                                    <span class="glyphicon glyphicon-user" style="margin-top: -3px; font-size: 22px;"></span> 
                                    <span class="badge" style="margin-top: -10px; font-size: 14px;"> 
                                        <security:authentication property="principal.fullName" />
                                    </span> 
                                </a>
                            </li>
                        </security:authorize>
                        <security:authorize access="isAnonymous()">
                            <li>
                                <a href="${logInUrl}">
                                    <span class="glyphicon glyphicon-log-in" style="margin-top: -3px; font-size: 22px;"></span> 
                                    <span class="badge" style="margin-top: -10px; font-size: 14px;"> ${logInLbl} </span>
                                </a>
                            </li>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">  
                            <li>
                                <a href="${logOutUrl}">
                                    <span class="glyphicon glyphicon-log-out" style="margin-top: -3px; font-size: 22px;"></span> 
                                    <span class="badge" style="margin-top: -10px; font-size: 14px;"> ${logOutLbl} </span> 
                                </a>
                            </li>
                        </security:authorize>
                        <%--<security:authorize access="hasRole('USER')">--%>
                            <!--<i> USER </i>-->
                        <%--</security:authorize>--%>
                        <%--<security:authorize access="hasRole('ADMIN')">--%>
                            <!--<i> ADMIN </i>-->
                        <%--</security:authorize>--%>
                    </ul>
                </div>
            </nav>    
        </header>
