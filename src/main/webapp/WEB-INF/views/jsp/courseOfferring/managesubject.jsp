<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<!-- SweetAlert -->
<script src="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.js"></script>

<link rel="stylesheet" href="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.css">
<script type="text/javascript">
$(document).ready(function() {
	$('#dataTable').DataTable();
	
	if("${addSuccess}"=="Y"){
		setTimeout(function(){ 
			swal("Add Subject is success.!", "", "success");
		
		}, 100);
	}else if("${addSuccess}"="N"){
		setTimeout(function(){ 
			swal("Add Subject is fail.!", "", "error");
		
		}, 100);
	}
	
	if("${editSuccess}"=="Y"){
		setTimeout(function(){ 
			swal("Edit Subject is success.!", "", "success");
		
		}, 100);
	}else if("${editSuccess}"="N"){
		setTimeout(function(){ 
			swal("Edit Subject is fail.!", "", "error");
		
		}, 100);
	}
	
	if("${deleteSuccess}"=="Y"){
		setTimeout(function(){ 
			swal("Delete Subject is success.!", "", "success");
		
		}, 100);
	}else if("${deleteSuccess}"="N"){
		setTimeout(function(){ 
			swal("Delete Subject is fail.!", "", "error");
		
		}, 100);
	}
	
});
</script>
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
		        <li><a href="javascript:searchSubject();">Search</a></li>
		        <li><a href="${root}/courseofferring/addSubject">Add</a></li>
		       
		        <li><a href="${root}/courseofferring/courseofferring">Close</a></li>
		    </ul>
	    </div>
	  </div>
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<%-- <div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Search</a></li>
					<li><a href="${root}/courseofferring/addSubject">New</a></li>
					<li><a href="#">Cancel</a></li>
					<li><a href="#">Close</a></li>
				</ul>
			</div> --%>
		</div>
	</nav>
	<!-- End Navigator -->
	
	<form id="searchSubject" name="searchSubject" method="POST" action="${root}/courseofferring/managesubject">
	
	
	<div class="container-fluid">
		<div class="row" style="margin-bottom:10px; text-align;left;">			
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">Course ID ::</div>
			<div class="col-xs-12 col-md-2">
				<input type="text" class="form-control" id="curiID" name="curiID"  size="20">
			</div>
			<div class="col-xs-12 col-md-8"></div>
		</div>
		<div class="row" style="margin-bottom:10px; text-align;left;">			
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">Subject Type ::</div>
			<div class="col-xs-12 col-md-2">
				<select id="subjectType" name="subjectType" class="form-control">
					<option value="">--- Select Subject Type---</option>
					<c:forEach items="${subjectTypeList}" var="item">
						<option value="${item.key}">${item.value}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-xs-12 col-md-8"></div>
		</div>	
		<div class="row" style="margin-bottom:10px; text-align;left;">	
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">Subject Status ::</div>
			<div class="col-xs-12 col-md-2">
				<select id="status" name="status" class="form-control">
					<option value="">--- Select Subject Status---</option>
					<option value="1">Active</option>
					<option value="0">Inactive</option>
				</select>
			</div>
			<div class="col-xs-12 col-md-8"></div>
		</div>	
		<div class="row" style="margin-bottom:10px; text-align;left;">	
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">Subject Code ::</div>
			<div class="col-xs-12 col-md-2">
				<input type="text" class="form-control" id="subjectCode" name="subjectCode" width="20">
			</div>
			<div class="col-xs-12 col-md-8"></div>
		</div>		
		
	</form>

		<div style="margin-bottom:20px;"></div>	<!-- empty -->
		<div class="panel panel-default">
		<c:if test="${ subjectSearchList != null}">
    		<div class="panel-heading">Search Result</div>
    		<div class="panel-body">
     			<table class="display" id="dataTable">
     				<thead>
	 		 			<tr>
	 		 				<th></th>
							<th>Subject ID</th>
							<th>Subject Name(Thai).</th>
							<th>Subject Name(Eng).</th>
							<th>Credit.</th>
							<th>Subject Type.</th>
							<th>Manage</th>
	  					</tr>
  					</thead>
 		 			<tbody>
 		 				<c:forEach items="${subjectSearchList}" var="item"  varStatus="loop">
	 		 				<tr>
	 		 					<td>
		 		 					${loop.index+1}
		 		 				</td>
	 		 					<c:if test="${entity == 'subject' }">
		 		 					<td>
		 		 						${item.subjectCode}
		 		 					</td>
		 		 					<td>
		 		 						${item.nameThai}
		 		 					</td>
		 		 					<td>
		 		 						${item.nameEng}
		 		 					</td>
		 		 					<td>
		 		 						${item.credit}
		 		 					</td>
		 		 					<td>
		 		 						${item.subjectType}
		 		 					</td>
		 		 					
		 		 					<td>
	 		 					
									    <a class="btn btn-success btn-sm" href="${root}/courseofferring/editSubject?subjectId=${item.id}"><span class="fa fa-edit">&nbsp;</span></a>
										<!-- <a class="btn btn-danger btn-sm" href="${root}/courseofferring/deleteSubject"><span class="fa fa-times">&nbsp;</span></a> -->
										
	
										<a class="btn btn-danger btn-sm" href="javascript:delSubject('${item.id}','${item.subjectCode}','${item.nameEng}');"><span class="fa fa-times">&nbsp;</span></a>
										
										<form id="delSubjForm${item.id}" method="post" action="${root}/courseofferring/deleteSubject">
											<input type="hidden" name="subjectId" value="${item.id}">
										</form>
		 		 					</td>
	 		 					</c:if>
	 		 					<c:if test="${entity == 'crriculumSubject' }">
		 		 					<td>
		 		 						${item[0].subject.subjectCode}
		 		 					</td>
		 		 					<td>
		 		 						${item[0].subject.nameThai}
		 		 					</td>
		 		 					<td>
		 		 						${item[0].subject.nameEng}
		 		 					</td>
		 		 					<td>
		 		 						${item[0].subject.credit}
		 		 					</td>
		 		 					<td>
		 		 						${item[0].subject.subjectType}
		 		 					</td>
		 		 					
		 		 					<td>
	 		 					
									    <a class="btn btn-success btn-sm" href="${root}/courseofferring/editSubject?subjectId=${item[0].id}"><span class="fa fa-edit">&nbsp;</span></a>
										<!-- <a class="btn btn-danger btn-sm" href="${root}/courseofferring/deleteSubject"><span class="fa fa-times">&nbsp;</span></a> -->
										
	
										<a class="btn btn-danger btn-sm" href="javascript:delSubject('${item[0].id}','${item[0].subject.subjectCode}','${item[0].subject.nameThai}');"><span class="fa fa-times">&nbsp;</span></a>
										
										<form id="delSubjForm${item[0].id}" method="post" action="${root}/courseofferring/deleteSubject">
											<input type="hidden" name="subjectId" value="${item[0].id}">
										</form>
		 		 					</td>
	 		 					</c:if>
	 		 					
	 		 				</tr>
						</c:forEach>
 		 			
	 		 			</tbody>
  										
  				</table>
    		</div>
    	</c:if>
  		</div>
  	</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/manageSubject.js"></script>
</body>
</html>