<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register New Account </title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="container">

<h1>New Account Registration Form:</h1>

	<ul id="nav">

			<li><a href="${pageContext.request.contextPath}/PageList">Page List</a></li>
			
			<li><a href="${pageContext.request.contextPath}/NewUserAccount">New User Account</a></li>
		    
	</ul>
	


	<c:if test="${message!='n'}">
		<div id="error">${message}</div>
	</c:if>

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
				<label>Retype Password: </label>
				<input type="password" name="retypepassword" />
			</li>
			<li>
				<label>First Name: </label>
				<input type="text" name="firstname" />
			</li>
			<li>
				<label>Last Name: </label>
				<input type="text" name="lastname" />
			</li>
			<li>
				<label>Email: </label>
				<input type="text" name="email" />
			</li>
			

			<li>
				<input type="submit" name="submit" value="Submit" />
			</li>	

		</ul>

	</form>

</div>
</body>
</html>