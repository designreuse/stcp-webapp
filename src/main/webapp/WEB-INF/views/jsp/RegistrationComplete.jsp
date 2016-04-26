<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Student Course Planer</title>
	
	<!-- for include csss -->
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    
  	<link href="${bootstrapCss}" rel="stylesheet"/>
    
    <!-- SweetAlert (message alert -->
	<script src="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.js"></script>
	<link rel="stylesheet" href="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.css">
    
    <!-- for include script -->
	<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
	<spring:url value="/resources/core/js/security.js" var="securityJs"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${bootstrapJs}"></script>
	<script src="${securityJs}"></script>
</head>
<body>
	<div class="jumbotron" style="background-color:#dd4b39">
		<div class="container" style="color:white;">
			<h1>Student Course Planer</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-xs-offset-2 col-md-12 col-md-2-offset">
				<div class="row">
					<div class="col-xs-12 col-md-12">
						<h2 class="form-register-heading">Registration Complete</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-10 col-md-10">
						<h4>We've just sent you an email to the address you entered. Please follow the link in the email to confirm your account.</h4>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
</body>
</html>