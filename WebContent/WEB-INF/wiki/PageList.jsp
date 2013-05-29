<%-- Ishag Alexanian The PageList JSP --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wiki ages List</title>

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
<h1>Pages List</h1>

<ul id="nav">

        <li><a href="${pageContext.request.contextPath}/wiki/index">Wiki Index</a></li>

		<li><a href="${pageContext.request.contextPath}/PageList">Page List</a></li>
		
		

</ul>

<ul class="three-column-list">

		<li class="title-row">

			<span>Path</span>

			<span>Last Edited By</span>
			
			<span>Last Edited Time</span>

		</li>
              
	<c:forEach items="${entries}" var="wikientry" varStatus="status">
  		 <li><span><a href="${pageContext.request.contextPath}/wiki/${wikientry.path}">${wikientry.path}</a></span>
            <span>${wikientry.lastRevisionEntry.author}</span>   
            <span> <fmt:formatDate value="${wikientry.lastRevisionEntry.timestamp}" pattern="yyyy-MM-dd hh:mm aaa" /> </span>
        </li>
	</c:forEach>    
         
         

</ul>
</div>

</body>
</html>