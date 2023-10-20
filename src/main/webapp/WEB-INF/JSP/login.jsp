<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h3>${successMsg}</h3>
	<a href="/RetroRides/user/register.htm" class="btn btn-info" role="button">Register a new User</a><br/>
	<h2>Login</h2>
	
		<form:form action="${contextPath}/user/login" modelAttribute="user"
		method="post">
	
		<table class="table">
		<tr>
		    <td>User Email:</td>
		    <td><input name="email" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30 required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" class="btn btn-info" value="Login" /></td>
		</tr>
				
		</table>
		
		</form:form>
		
		
</body>
</html>