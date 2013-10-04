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

        <table>
	<tr>
	<th>Type</th>
	<th>Key</th>
	<th>Author</th>
	<th>Title</th>
	<th>Booktitle</th>
	<th>Journal</th>
	<th>Publisher</th>
	<th>Year</th>
	<th>Trash</th>
	</tr>
	
        <c:forEach var="ref" items="${reflist}">
		<tr>
		<td>${ref.type}</td>
		<td>${ref.key}</td>
		<td>${ref.author}</td>
		<td>${ref.title}</td>
		<td>${ref.booktitle}</td>
		<td>${ref.journal}</td>
		<td>${ref.publisher}</td>
		<td>${ref.year}</td>
		<td><a href="trash?id=${ref.id}">trash</a></td>
		</tr>
        </c:forEach>
        </table>
	
	<p>${msg_reflist}</p>
	
	<br/>
	<h2>Trash</h2>
	<table>
	<tr>
	<th>Type</th>
	<th>Key</th>
	<th>Author</th>
	<th>Title</th>
	<th>Year</th>
	</tr>
        <c:forEach var="ref" items="${trashlist}">
		<tr>
		<td>${ref.type}</td>
		<td>${ref.key}</td>
		<td>${ref.author}</td>
		<td>${ref.title}</td>
		<td>${ref.year}</td>
		</tr>
        </c:forEach>
        </table>
	
	<p>${msg_trashlist}</p>
        
	<form name="form" id="form" method="POST" action="trash">
	<input class="left" name="empty" type="submit" value="Empty trash"/>         
	</form>
	
        </div>
    </body>
</html>