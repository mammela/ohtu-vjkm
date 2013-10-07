<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New</title>
        <jsp:include page="includeCss.jsp" />
        <script>var usedKeys = [<c:forEach var="key" items="${usedkeys}">"${key}",</c:forEach>"-1"];</script>
        <jsp:include page="includeJS.jsp" />
    </head>
    <body onload="init();">
	<div class="container">
        <jsp:include page="includeMenu.jsp" />
        
        <p><b>
        <c:if test="${adderror==-1}">Reference with a key=${savedkey} added successfully!</c:if>
        <c:if test="${adderror==1}">ERROR: bibtex key was not unique; reference was not added!</c:if>
        <c:if test="${adderror==2}">ERROR: bibtex key was empty; reference was not added!</c:if>
        <c:if test="${adderror==3}">ERROR: reference type was incorrect; reference was not added!</c:if>
        </b></p>

        <h2>Add new reference</h2>
        <div id="formData" class="inputData">

            <form name="form" id="form" method="POST" action="add" onsubmit="return isKeyValid();">
              <select name="entryType" onchange="changeValue(value);">
                <c:forEach var="ent" items="${entrytypes}">
                    <option value="${ent}">${ent}</option>
                </c:forEach>
              </select>
                <input name='Add' class='submit' type='submit' value='Add'/> <br>

            <div id="addForm">
            </div>
            </form>
        </div>       
        </div>
    </body>
</html>
