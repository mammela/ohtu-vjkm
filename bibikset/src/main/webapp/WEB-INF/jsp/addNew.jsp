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
        <jsp:include page="includeCss.jsp" />
        <jsp:include page="includeJS.jsp" />
    </head>
    <body onload="init();">
	<div class="container">
        <jsp:include page="includeMenu.jsp" />
        
        <h2>Add new reference</h2>
        <div id="formData" class="inputData">
            <select id="typeSelect" onchange="changeValue(value);">
                <option value="inproceeding">Inproceeding</option>
                <option value="book">Book</option>
                <option value="article">Article</option>
                <option value="manual">Manual</option>
          </select> <br>
        <form name="addForm" id="addForm" method="POST" action="add">

          
         
        </form>
        </div>
        
        </div>
    </body>
</html>
