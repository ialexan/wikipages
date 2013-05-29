<%-- Ishag Alexanian The ListRevisions JSP --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wiki</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<header>
	<h7>Wikipages</h7>
	<form method="POST" action="${pageContext.request.contextPath}/wiki/index">
		<label for="newpage">Your page:</label>
		<input type="search" name="newpage">
		<input type="submit" value="Search">
	</form>
</header> 

<div id="container">

<h1>Revisions For ${path}</h1>

<ul id="nav">
		<li><a href="${pageContext.request.contextPath}/PageList">Page List</a></li>
		<li><a href="${pageContext.request.contextPath}/listRevisions?path=${path}">Revision List</a></li>
</ul>




	<ul class="three-column-list">
		<c:forEach items="${revisions}" var="entry" varStatus="status">
			<li><span><a href="${pageContext.request.contextPath}/wiki/${path}?revision=${entry.id}">Revision ${status.index} </a></span></li>
		</c:forEach>
	</ul>

</div>

</body>
</html>