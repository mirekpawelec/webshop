<%-- 
    Document   : product
    Created on : 2017-09-05, 19:45:24
    Author     : mirek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section>
            <h1>Dodawanie nowego produktu:</h1>
        </section>
        <section>
            <form:form modelAttribute="newProduct">
                <fildset>
                    <form:errors path="*" />
                    <label for="productNo"><spring:message code="addProduct.form.productNo.label"/></label>
                    <form:input type="text" id="productNo" path="productNo" />
                    <form:errors path="productNo" />
                    <br/>
                    <label for="productName"><spring:message code="addProduct.form.name.label"/></label>
                    <form:input type="text" id="name" path="name" />
                    <form:errors path="name" />
                    <br/>
                    <label for="manufacturer"><spring:message code="addProduct.form.manufacturer.label"/></label>
                    <form:input type="text" id="manufacturer" path="manufacturer" />
                    <form:errors path="manufacturer" />
                    <br/>
                    <label for="category"><spring:message code="addProduct.form.category.label"/></label>
                    <form:input type="text" id="category" path="category" />
                    <form:errors path="category" />
                    <br/>
                    <label for="description"><spring:message code="addProduct.form.description.label"/></label>
                    <form:textarea id="description" path="description" />
                    <form:errors path="description" />
                    <br/>
                    <label for="unitPrice"><spring:message code="addProduct.form.unitPrice.label"/></label>
                    <form:input type="number" step="any" id="unitPrice" path="unitPrice" />
                    <form:errors path="unitPrice" />
                    <br/>
                    <label for="quantityInBox"><spring:message code="addProduct.form.quantityInBox.label"/></label>
                    <form:input type="number" id="quantityInBox" path="quantityInBox" />
                    <form:errors path="quantityInBox" />
                    <br/>
                    <input type="submit" name="Dodaj"
                </fildset>
            </form:form>
        </section>
    </body>
</html>
