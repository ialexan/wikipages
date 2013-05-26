<%-- Ishag Alexanian The Login JSP --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<header>
	<h7>Wikipages</h7>
</header> 

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
				<input type="text" name="username" required="required"/>
			</li>
			<li>
				<label>Password: </label>
				<input type="password" name="password" required="required"/>
			</li>
			
			<li>
				<input type="submit" name="login" value="Login" />
			</li>
			
	</ul>		
		
	</form>

</div>


</body>
</html>