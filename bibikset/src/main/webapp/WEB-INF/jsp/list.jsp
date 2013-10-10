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
        <c:if test="${not empty reflist}">
            <h2>Reference list</h2>
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
                <th>Note</th>
                <th>Trash</th>
            </tr>

            <c:forEach var="ref" items="${reflist}">
		<tr>                
                    <td>${ref.entryType}</td>
                    <td>${ref.key}</td>
                    <td>${ref.author}</td>
                    <td>${ref.title}</td>
                    <td>${ref.booktitle}</td>
                    <td>${ref.journal}</td>
                    <td>${ref.publisher}</td>
                    <td>${ref.year}</td>
                    <td>${ref.note}</td>
                    <td><a href="trash?id=${ref.id}">trash</a></td>
		</tr>
            </c:forEach>
            </table>
        </c:if>
	
        <c:if test="${empty reflist}">
            <p><b>No items in the reference list.</b></p>
	</c:if>

        <c:if test="${not empty trashlist}">
            <br/>
            <h2>Trash</h2>
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
                <th>Note</th>
                <th>Trash</th>
            </tr>
            <c:forEach var="ref" items="${trashlist}">
		<tr>
                    <td>${ref.entryType}</td>
                    <td>${ref.key}</td>
                    <td>${ref.author}</td>
                    <td>${ref.title}</td>
                    <td>${ref.booktitle}</td>
                    <td>${ref.journal}</td>
                    <td>${ref.publisher}</td>
                    <td>${ref.year}</td>
                    <td>${ref.note}</td>
                    <td><a href="untrash?id=${ref.id}">recover</a></td>
		</tr>
            </c:forEach>
            </table>
            <form name="form" id="form" method="POST" action="emptytrash">
                <input class="left" name="empty" type="submit" value="Empty trash"/>         
            </form>
        </c:if>
        </div>
    </body>
</html>