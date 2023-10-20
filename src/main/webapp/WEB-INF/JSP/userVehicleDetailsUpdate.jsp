<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Upload Details</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<h2>Upload Details</h2>
<div class="container">
	<div class="row">
		<div class="col-md-12 text-right">
			<button onclick="javascript:history.go(-1)" class="btn btn-info">Go Back</button>
		</div>
	</div>
	
	<!-- update Vehicle Details Form -->
	
		<form:form action="${contextPath}/user/updateVehicleDetails" method="post" modelAttribute="vehicleDetail">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<table class="table">
					<tr>
						<td>Type of Vehicle:</td>
						<td>
							<form:select path="category">
								<form:option value="CAR">Car</form:option>
								<form:option value="BIKE">Bike</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Brand:</td>
						<td><form:input path="brand" size="30" /></td>
					</tr>
					<tr>
						<td>Model:</td>
						<td><form:input path="model" size="30" /></td>
					</tr>
					<tr>
						<td>Year of Manufacturing:</td>
						<td><form:input path="yearOfManufacture" size="30" /></td>
					</tr>
					<tr>
						<td>Mileage:</td>
						<td><form:input path="mileage" size="30" /></td>
					</tr>
					<tr>
						<td>Ask Price (in dollars):</td>
						<td><form:input path="askPrice" size="30" /></td>
					</tr>
					<tr>
						<td>Color:</td>
						<td><form:input path="color" size="30" /></td>
					</tr>
					<tr>
						<td>State:</td>
						<td>
							<form:select path="state">
								<form:option value="AL">Alabama</form:option>
								<!-- Add other states here -->
							</form:select>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<input type="submit" class="btn btn-primary" name=action value="Update" />
				<input type="submit" name=action value="Delete" class="btn btn-danger"/>
				<input type="hidden" name="vehicleDetailID" value="${vehicleDetail.getVehicleID()}">
			</div>
		</div>
	</form:form>

</div>

</body>
</html>	