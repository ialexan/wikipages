<%-- Ishag Alexanian The Login JSP --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="container">

<h1>Login Page</h1>

	<ul id="nav">
		<li><a href="${pageContext.request.contextPath}/PageList">Page List</a></li>
		<li><a href="${pageContext.request.contextPath}/NewUserAccount">Create New Account</a></li>
		<li><a href="${pageContext.request.contextPath}/Login">Login</a></li>
	</ul>


	<form method="post">
	
	<ul>

			<li>
				<label>Username: </label>
				<input type="text" name="username" />
			</li>
			<li>
				<label>Password: </label>
				<input type="password" name="password" />
			</li>
			
			<li>
				<input type="submit" name="login" value="Login" />
			</li>
			
	</ul>		
		
	</form>

</div>


</body>
</html>