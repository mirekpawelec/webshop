<%-- 
    Document   : faq
    Created on : 2017-11-14, 22:49:03
    Author     : mirek
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">                    
    <div class="panel-group" id="accordion">
        <c:forEach items="${faq}" var="item" varStatus="counter">
            <div class="panel panel-primary text-left">
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne${item.faqId}">
                      <h4>${item.question}</h4>
                  </a>
                </h4>
              </div>
              <div id="collapseOne${item.faqId}" class="panel-collapse collapse ${counter.count==1?'in':''}">
                <div class="panel-body">
                    ${item.answer}
                </div>
              </div>
            </div>
        </c:forEach>
    </div>
</div>

