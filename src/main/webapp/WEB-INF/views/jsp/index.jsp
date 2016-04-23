<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Student Course Planer</title>

    <spring:url value="/resources/core/css/main.css" var="coreCss"/>
    <spring:url value="/resources/core/js" var="coreJs"/>
    
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>

</head>

<div class="jumbotron" style="background-color:#dd4b39">
	<div class="container" style="color:white;">
		<h1>Student Course Planer</h1>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-xs-4 col-xs-offset-4 col-md-4 col-md-4-offset">
	        <h2 class="form-signin-heading">Sign in</h2>
	        <label for="inputEmail" class="sr-only">Email address</label>
	        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
	        <label for="inputPassword" class="sr-only">Password</label>
	        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
	        <div class="row">
	        	<div class="col-xs-12 col-md-12">
					<div class="pull-left">
						<label>Register</label>
					</div>
					<div class="pull-right">
						<label>Forgot Password</label>
					</div>
				</div>
			</div>
	        <button class="btn btn-lg btn-primary btn-block" id="btnSignIn">Sign in</button>
		</div>
	</div>
	
</div>

<%-- <spring:url value="/resources/core/css/main.js" var="coreJs"/>
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs"/>

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 --%>
 
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="${coreJs}/security.js" defer></script>


</body>
</html>