<%-- Ishag Alexanian The ShowContentPage JSP --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wiki</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

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

