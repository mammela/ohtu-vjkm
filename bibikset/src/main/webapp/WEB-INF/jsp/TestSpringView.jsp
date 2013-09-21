<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <p>${message}</p>
        
        <pre>
        <c:forEach var="item" items="${testlist}">
            ${item}
        </c:forEach>
        </pre>
        
    </body>
</html>
