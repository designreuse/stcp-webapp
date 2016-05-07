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
	<script src="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.min.css">
    
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
			<h1>Student Course Planner</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-4 col-md-8 col-md-4-offset">
				<div class="row">
					<div class="col-xs-6 col-md-6">
						<label for="tbxForgotEmail" class="sr-only">Email address</label>
				        <input type="email" id="tbxForgotEmail" class="form-control" placeholder="Email address" required autofocus>
				        <div class="row">
				        	<div class="col-xs-12 col-md-12">
				        		<label></label>
				        	</div>
				        </div>
				        <button class="btn btn-lg btn-primary btn-block" id="btnForgotPassword">E-mail new password</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Loading Modal -->
	<div id="loadingModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="reportModalTitle">
	    <div class="modal-dialog modal-sm">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	                <h4 class="modal-title">Loading</h4>
	            </div>
	            <div class="modal-body">
	                <div class="progress">
	                    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
	                        <span class="sr-only">45% Complete</span>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>