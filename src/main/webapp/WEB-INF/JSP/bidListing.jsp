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
<body class="container ml-2">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand">${user.getFirstName()} ${user.getLastName()}</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/user/login" title="goto Home">Home</a>
                    </li>
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
			<td><b>Ask Price</b></td>
			<td><b>Bid Price</b></td>
		</tr>
		<c:forEach var="bidDetail" items="${bidDetailList}">
			<tr>
				<td>${bidDetail.getVehicleDetail().getCategory()}</td>
				<td>${bidDetail.getVehicleDetail().getBrand()}</td>
				<td>${bidDetail.getVehicleDetail().getModel()}</td>
                <td>${bidDetail.getVehicleDetail().getAskPrice()}</td>
                <td>${bidDetail.getBidPrice()}</td> 
			</tr>
		</c:forEach>
	</table>
</body>
</html>
