<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body calss="ml-2">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand">${user.getFirstName()}</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/logout" id="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Vehicle Type</b></td>
			<td><b>Brand</b></td>
			<td><b>Model</b></td>
			<td><b>Year of Manufacturing</b></td>
			<td><b>Ask Price</b></td>
			<td><b>ACTION</b></td>
		</tr>
		<c:forEach var="vehicleDetail" items="${vehicleList}">
			<tr>
				<td>${vehicleDetail.getCategory()}</td>
				<td>${vehicleDetail.getBrand()}</td>
				<td>${vehicleDetail.getModel()}</td>
                <td><img height="150" width="150" src="/external-images/${vehicleDetail.getFileName()}" /></td>
                <td>${vehicleDetail.getAskPrice()}</td>
                 <td><a class="btn btn-info" href="${contextPath}/admin/adminVehicleDetails.htm?VehicleId=${vehicleDetail.getVehicleID()}">View Details</a></td>  
			</tr>
		</c:forEach>
	</table>
</body>
</html>