<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Register User Form</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<!-- <a href="javascript:history.go(-1)">Go Back</a><br/> -->

	<h2>Register a New User</h2>

	<form:form action="${contextPath}/user/register" modelAttribute="user"
		method="post">

		<table class="table">
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>
			<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30" type="email"/> 
						<font color="red"><form:errors
							path="email" /></font></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"/>
				 <font color="red"><form:errors
							path="password" /></font></td>
			</tr>
						<tr>
				<td>PhoneNumber:</td>
				<td><form:input path="phoneNumber" size="30"/>
				 <font color="red"><form:errors
							path="password" /></font></td>
			</tr>
			<tr>
				<td>State:</td>
				<td>
	<form:select path="state">
	<form:option value="AL">Alabama</form:option>
	<form:option value="AK">Alaska</form:option>
	<form:option value="AZ">Arizona</form:option>
	<form:option value="AR">Arkansas</form:option>
	<form:option value="CA">California</form:option>
	<form:option value="CO">Colorado</form:option>
	<form:option value="CT">Connecticut</form:option>
	<form:option value="DE">Delaware</form:option>
	<form:option value="DC">District Of Columbia</form:option>
	<form:option value="FL">Florida</form:option>
	<form:option value="GA">Georgia</form:option>
	<form:option value="HI">Hawaii</form:option>
</form:select> 
						<font color="red">
						<form:errors path="state" /></font></td>
			</tr>

			
			

			<tr>
				<td colspan="2"><input type="submit" value="Register User" class="btn btn-info"/></td>
			</tr>
		</table>
	<input type="hidden" name="role" value="User"/>
	</form:form>

</body>
</html>