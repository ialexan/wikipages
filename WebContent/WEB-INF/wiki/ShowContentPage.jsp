<%-- Ishag Alexanian The ShowContentPage JSP --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wiki</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<header>
	<h7>Wikipages</h7>
	<form method="POST" action="${pageContext.request.contextPath}/wiki/index">
		<label for="newpage">Your page:</label>
		<input type="text" name="newpage">
		<input type="submit" value="Search">
	</form>
</header> 
<div id="container">
<h1>${wikiEntry.path}</h1>


<ul id="nav">
        <li><a href="${pageContext.request.contextPath}/EditPage?path=${wikiEntry.path}">Edit</a></li>
        <li> <a href="${pageContext.request.contextPath}/listRevisions?path=${wikiEntry.path}">Revisions</a>  </li>
        <li><a href="${pageContext.request.contextPath}/${loginout}">${loginout}</a> </li>

	<c:if test="${revertRevision=='RevertRevision'}">
	<li><a href="${pageContext.request.contextPath}/RevertRevision?wikiId=${wikiEntry.id}&revisionId=${revisionEntry.id}">Revert to this version</a> </li>
	</c:if>
	
		<li><a href="${pageContext.request.contextPath}/wiki/${wikiEntry.path}">Page</a> </li>

</ul>




${revisionEntry.content}



<br />
<br />
<hr/><i>This page has been viewed ${wikiEntry.viewCounter} times. &nbsp;&nbsp; Last Edited by ${wikiEntry.lastRevisionEntry.author} at <fmt:formatDate value="${wikiEntry.lastRevisionEntry.timestamp}" pattern="yyyy-MM-dd hh:mm aaa" />.</i>

</div>

</body>
</html>

