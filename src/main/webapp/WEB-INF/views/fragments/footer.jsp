<%-- 
    Document   : footer
    Created on : 2017-09-14, 17:45:55
    Author     : mirek
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<spring:url value="/resource/js/table_sorting.js" var="tableSortingJs" />
<spring:url value="/resource/js/jquery.min.js" var="jQueryJs" />
<spring:url value="/resource/js/bootstrap.min.js" var="bootstrapJs" />
<spring:message code="footer.footer.copyright.label" var="copyright"/>

<div class="container-fluid">
    <footer class="container-fluid">
        ${copyright}
    </footer>
</div>
    
<script src="${tableSortingJs}"></script>
<script src="${jQueryJs}"></script>
<script src="${bootstrapJs}"></script>
