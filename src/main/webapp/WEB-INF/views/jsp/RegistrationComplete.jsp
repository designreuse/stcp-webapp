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
    <spring:url value="/resources/core/css/main.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    
  	<link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
</head>
<body>
	<div class="jumbotron" style="background-color:#dd4b39">
		<div class="container" style="color:white;">
			<h1>Student Course Planer</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-4 col-md-8 col-md-4-offset">
				<div class="row">
					<div class="col-xs-12 col-md-12">
						<h2 class="form-register-heading">Registration Complete</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-md-6">
						<h4>We've just sent you an email to the address you entered. Please follow the link in the email to confirm your account.</h4>
					</div>
				</div>
				
				
			</div>
		</div>
	</div>
	
	<!-- for include script -->
	<spring:url value="/resources/core/css/main.js" var="coreJs"/>
	<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs"/>
	
	<script src="${coreJs}"></script>
	<script src="${bootstrapJs}"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/security.js" defer></script>
</body>
</html>