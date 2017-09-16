<%-- 
    Document   : footer
    Created on : 2017-09-14, 17:45:55
    Author     : mirek
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container-fluid">
    <footer class="container-fluid">
        <spring:message code="footer.footer.copyright.label"/>
    </footer>
</div>

<spring:url value="/resource/js/jquery.min.js" var="jQueryJs" />
<spring:url value="/resource/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${jQueryJs}"></script>
<script src="${bootstrapJs}"></script>
