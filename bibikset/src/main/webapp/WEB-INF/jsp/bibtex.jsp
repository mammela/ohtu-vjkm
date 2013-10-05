<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List references</title>
	<jsp:include page="includeCss.jsp" />
    </head>
    <body>
    	<div class="container">
        <jsp:include page="includeMenu.jsp" />

        <c:if test="${not empty bibtex}">
            <pre>${bibtex}</pre>
        </c:if>
        <c:if test="${empty bibtex}">
            <p><b>No items in the reference list.</b></p>
        </c:if>
	</div>
    </body>
</html>