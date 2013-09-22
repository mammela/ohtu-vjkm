<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <form method="POST" action="add">
          <label>key: <input type="text" name="key" /></label>
          <label>author: <input type="text" name="author" /></label>
          <input type="submit" />
        </form>
        <p>${message}</p>
        
        <pre>
        <c:forEach var="item" items="${referencelist}">
            ${item}
        </c:forEach>
        </pre>
        
    </body>
</html>