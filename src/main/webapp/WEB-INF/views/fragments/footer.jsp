<%-- 
    Document   : footer
    Created on : 2017-09-14, 17:45:55
    Author     : mirek
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resource/js/script.js" var="ownJs"/> 
<spring:url value="/resource/js/angular.min.js" var="angularJs"/>    
<spring:url value="/resource/js/main-angular.js" var="mainAngularJs" />
<spring:url value="/resource/js/table_sorting.js" var="tableSortingJs" />
<spring:url value="/resource/js/jquery.min.js" var="jQueryJs" />
<spring:url value="/resource/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/faq" var="faqUrl"/>
<spring:url value="/rule" var="ruleUrl"/>  
<spring:message code="footer.footer.copyright.label" var="copyrightLbl"/>
<spring:message code="footer.contact.contactetails.label" var="contactDetailLbl"/>
<spring:message code="footer.contact.links.label" var="linksLbl"/>
<spring:message code="footer.contact.rulesOfProcedure.label" var="rulesOfProcedureLbl"/>

<footer>
    <div id="contact" class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-4 text-left">
                <h4> ${contactDetailLbl} </h4>
                <address>
                    <strong>WebSHOP</strong><br>
                    al. Wojska Polskiego 0<br>
                    00-000 Kalisz<br>
                    <abbr title="Phone">tel:</abbr> +48 000-000-000
                </address>

                <address>
                    <strong>Jan Nowak</strong><br>
                    <a href="mailto:szkolenia@eduweb.pl">jan.nowak@webshop.pl</a>
                </address>
            </div>

            <div class="col-xs-12 col-sm-4 col-sm-offset-4 text-left">
                <h4> ${linksLbl} </h4>

                <ul class="list-unstyled">
                    <li><a href="${faqUrl}">FAQ</a></li>
                    <li><a href="${ruleUrl}">${rulesOfProcedureLbl}</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <hr>
        <div class="row">
            ${copyrightLbl}
        </div>
    </div>
</footer>
    
<script src="${ownJs}" ></script>
<script src="${angularJs}" ></script>
<script src="${mainAngularJs}"></script>
<script src="${tableSortingJs}"></script>
<script src="${jQueryJs}"></script>
<script src="${bootstrapJs}"></script>

