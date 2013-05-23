<%-- Ishag Alexanian The PageDoesNotExitPage JSP --%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wiki</title>

<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>



<div id="container">
	<div id="course-question">
		The page &nbsp;
		<i>${path}</i> &nbsp; does not exist. Do you want to create it?
	
		<a href="${pageContext.request.contextPath}/AddEntry?path=${path}">Yes</a> |
		<a href="${pageContext.request.contextPath}/wiki/index">No</a>
	</div>
</div>



</body>
</html>