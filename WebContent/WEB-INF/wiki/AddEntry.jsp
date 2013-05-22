<%-- Ishag Alexanian The AddEntry JSP --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS320Wiki - NewPage</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="container">

<h1>Wiki - New Page - ${path}</h1>

<ul id="nav">
		<li><a href="${pageContext.request.contextPath}/PageList">Page List</a></li>
		<li><a href="${pageContext.request.contextPath}/AddEntry?path=${path}">Add</a></li>
</ul>


	<form method="post">
	<ul>
			<li>
				<label>Path: </label>
				<i> ${path} </i>
			</li>
			
			<li>
				<label>Author: </label>
				<i> ${author} </i>
			</li>

			<li>
				<label>Content: </label>
				<textarea name="content" rows="5" cols="60">${content}</textarea>
			</li>
			
			<li>
				<input type="submit" name="create" value="Create" />
			</li>
	
	</ul>
	</form>

</div>

</body>
</html>