<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link href="http://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Student Course Planner</title>
</head>
<style>
h1 {
	text-align: center;
}

.navbar {
	padding-top: 15px;
	padding-bottom: 15px;
	border: 0;
	border-radius: 0;
	margin-bottom: 0;
	font-size: 12px;
	letter-spacing: 5px;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}

dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #f1f1f1
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #3e8e41;
}
</style>
<body>
	<div class="box-header">
		<h3 class="box-title">Manage Subject</h3>
	</div>
	<!-- /.box-header -->
	<!-- Navigator -->
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
			<ul class="nav nav-pills">
		        <li><a href="#">Search</a></li>
		        <li><a href="${root}/courseofferring/DetailCurriculum">New</a></li>
		        <li><a href="#">Cancel</a></li>
		        <li><a href="#">Close</a></li>
		    </ul>
	    </div>
	  </div>
	</nav>
	<!-- End Navigator -->
	
	<div class="container-fluid">
		<div class="row" style="margin-bottom:10px; text-align;left;">			
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">Course ID ::</div>
			<div class="col-xs-12 col-md-2"><input type="text" class="form-control" id="txtYear"  size="20"></div>
			<div class="col-xs-12 col-md-8"></div>
		</div>
		<div class="row" style="margin-bottom:10px; text-align;left;">			
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">Subject Type ::</div>
			<div class="col-xs-12 col-md-2"><input type="text" class="form-control" id="txtCuriID" width="20"></div>
			<div class="col-xs-12 col-md-8"></div>
		</div>	
		<div class="row" style="margin-bottom:10px; text-align;left;">	
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">Subject Status ::</div>
			<div class="col-xs-12 col-md-2"><input type="text" class="form-control" id="txtCuriID" width="20"></div>
			<div class="col-xs-12 col-md-8"></div>
		</div>	
		<div class="row" style="margin-bottom:10px; text-align;left;">	
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">Subject ID ::</div>
			<div class="col-xs-12 col-md-2"><input type="text" class="form-control" id="txtCuriID" width="20"></div>
			<div class="col-xs-12 col-md-8"></div>
		</div>			
		<div style="margin-bottom:20px;"></div>	<!-- empty -->
		<div class="panel panel-default">
    		<div class="panel-heading">Search Result</div>
    		<div class="panel-body">
     			<table class="table table-condensed">
 		 			<tr>
 		 				<th></th>
						<th>Subject ID</th>
						<th>Subject Name(Thai).</th>
						<th>Subject Name(Eng).</th>
						<th>Credit.</th>
						<th>Subject Type.</th>
  					</tr>
 		 			<tr>
 		 				<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
  					</tr> 					
  				</table>
    		</div>
  		</div>
  	</div>


</body>
</html>