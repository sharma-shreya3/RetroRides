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
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<c:set var="prevBidPrice" value="${bidDetail.getBidPrice()}"/>
<div class="container">
	<div class="row">
		<div class="col-md-12 text-right">
			<button onclick="javascript:history.go(-1)" class="btn btn-info">Go Back</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 text-center">
			<h2>Bid Details</h2>
		</div>
	</div>

	<form:form action="${contextPath}/user/uploadBidDetails" method="post" modelAttribute="bidDetail">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<table class="table">
					<tr>
						<td>Place Bid:</td>
						<td>
							<form:input path="bidPrice" size="10"/>
						</td>
					</tr>
				
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<c:if test="${prevBidPrice <= 0}">
				<input type="submit" name="action" value="Place Bid" class="btn btn-info" />
				</c:if>
				<c:if test="${prevBidPrice > 0}">
				<input type="submit" name="action" value="Update Bid" class="btn btn-info" />
				</c:if>
				<input type="hidden" name="userID" value="${user.getUserId()}"/>
				<input type="hidden" name="vehicleID" value="${vehicleDetail.getVehicleID()}"/>
			</div>
		</div>
	</form:form>

</div>

</body>
</html>	