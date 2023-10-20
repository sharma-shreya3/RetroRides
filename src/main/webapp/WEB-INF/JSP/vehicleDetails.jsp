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
<c:set var="loggedInUserID" value="${user.getUserId()}" />
<c:set var="vehicleDetailsUserID" value="${vehicleDetail.getUser().getUserId()}" />
<c:set var="status" value="${vehicleDetail.getStatus()}"/>
<c:set var="active" value="ACTIVE"/>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand">${user.getFirstName()} ${user.getLastName()}</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <c:if test="${loggedInUserID == vehicleDetailsUserID}">
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/user/updateVehicleDetails.htm" title="Update or Delete Vehicle Details">Update/Delete Vehicle Details</a>
                    </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/user/login" title="User Home Page" name="homePage">Home Page</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>




<h3 class="text-success mt-2">${successMessage}</h3>
<div class="container mt-3">
        <div class="row">

            <!-- Product Details -->
            <div class="col-md-6">
                <h2>Vehicle Details</h2>
                <ul class="list-group">
                    <li class="list-group-item">Type of Vehicle: <span id="vehicleType">${vehicleDetail.getCategory()}</span></li>
                    <li class="list-group-item">Brand: <span id="brand">${vehicleDetail.getBrand()}</span></li>
                    <li class="list-group-item">Model: <span id="model">${vehicleDetail.getModel()}</span></li>
                    <li class="list-group-item">Year Of Manufacturing: <span id="year">${vehicleDetail.getYearOfManufacture()}</span></li>
                    <li class="list-group-item">Mileage: <span id="mileage">${vehicleDetail.getMileage()}</span></li>
                    <li class="list-group-item">Ask Price: <span id="askPrice">${vehicleDetail.getAskPrice()}</span></li>
                    <li class="list-group-item">Color: <span id="color">${vehicleDetail.getColor()}</span></li>
                    <li class="list-group-item">State: <span id="state">${vehicleDetail.getState()}</span></li>
                    <c:if test = "${loggedInUserID == vehicleDetailsUserID}">
                    <li class="list-group-item">Status: <span id="status">${vehicleDetail.getStatus()}</span></li>
                    </c:if>
                </ul>
                <c:if test = "${loggedInUserID == vehicleDetailsUserID}">
                   <a class="btn btn-primary" href="${contextPath}/user/viewBidDetails.htm"> View Bid Details </a>
                </c:if>
                  <c:if test = "${loggedInUserID == vehicleDetailsUserID && status == active}">
                   <a class="btn btn-success" href="${contextPath}/user/markAsSold.htm"> Mark Sold </a>
                </c:if>
                
                <c:if test = "${loggedInUserID != vehicleDetailsUserID}">
                   <a class="btn btn-primary" href="${contextPath}/user/uploadBidDetails.htm"> Place/Update Bid </a>
                </c:if>
            </div>
            
            
        <!-- Product Image -->
            <div class="col-md-6">
            ${vd.getFileName}
                <img src="C:/Shreya Sharma/NEU coursework/Sem-2/web tools/Retro Rides/RetroRides/RetroRides/src/main/webapp/images/${vd.getFileName()}" alt="Product Image" class="img-fluid">
            </div>
        </div>
        
        
</body>
</html>