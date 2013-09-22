<%-- 
    Document   : addNew
    Created on : 22.9.2013, 21:01:36
    Author     : joonasmammela
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New</title>
    </head>
    <body>
        <jsp:include page="titlebar.jsp"  />
        <h2>Lisää uusi</h2>
        <form method="POST" action="add">
           
          <label>Key:    <input type="text" name="key" /></label><br>
          <label>Author: <input type="text" name="author" /></label><br>
          
          
          <input style="margin-top:20px" type="submit" value="Lisää uusi"/>
        </form>
        
    </body>
</html>
