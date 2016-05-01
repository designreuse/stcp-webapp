<%@page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.js"></script>
<link rel="stylesheet" href="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.css">
<script type="text/javascript" src="${root}/resources/core/js/managecourse.js"></script>

<title>Student Course Planner</title>
</head>
<style>
 .navbar {
 	left: 0px;
    padding-top: 15px;
    padding-bottom: 15px;
    border: 0;
    border-radius: 0;
    margin-bottom: 0;
    font-size: 12px;
    
  }
  table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}
 </style>
<body>

	<!-- Main content -->
	<div class="box-header">
		<h3 class="box-title">Manage Course</h3>
	</div><!-- /.box-header -->
	<!-- Navigator -->
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
			<ul class="nav nav-pills">
		        <li><a href="#">Search</a></li>
		        <li><a href="${root}/courseofferring/addCourse">Add</a></li>
		       
		        <li><a href="${root}/courseofferring/courseofferring">Close</a></li>
		    </ul>
	    </div>
	  </div>
	</nav>
	<!-- End Navigator -->
	
	<!-- Merge -->
	<div class="container-fluid">
		<form id="form"  method="post" action="${root}/courseofferring/managecourse">
			<div class="row" style="margin-bottom:10px; text-align;left;">			
				<div class="col-xs-12 col-md-2" style="padding-left: 50px;">ปีการศึกษา  ::</div>
				<div class="col-xs-12 col-md-2"><input type="text" class="form-control" id="txtYear"  size="20" name = "year" ></div>
				<div class="col-xs-12 col-md-8"></div>
			</div>
			<div class="row" >			
				<div class="col-xs-12 col-md-2" style="padding-left: 50px;">ชื่อหลักสูตร  ::</div>
				<div class="col-xs-12 col-md-2">
					<select name="curriculum" id = "curriculum">
							<option value="">--- เลือกหลักสูตร---</option>
							<c:forEach items="${OatCurriculumList}" var="element"> 
								<option value="${element.id}">${element.name}</option>
							</c:forEach>
							
					</select>
				
				</div>
				<div class="col-xs-12 col-md-8"></div>
			</div>	
			
			
			
			<div style="margin-bottom:20px;"></div>	
				<div style="padding-left: 180px; margin-bottom:20px;">
					<button type="button" id="search" onclick="searchCurriculumName()">Search</button>			
		  		</div>
			
				<div id="result" class="panel panel-default">
		    		<div class="panel-heading">Search Result</div>
		    		<div class="panel-body">
		     			<table class="table table-condensed">
		 		 			<tr>
								<th>Year</th>
								<th>Curriculum Name</th>
								<th>Subject Code</th>
								<th>Subject Name</th>
								<th>Edit</th>
								
		  					</tr>
		 		 			<!-- <tr>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
		  					</tr> 	 -->
		  					<c:if test="${ courseSearchList != null}">
		  					<c:forEach var="entry" items="${courseSearchList}">
							  	
							  		<tr>
			 		 					<td>
											${entry.key.startYear} 
			 		 					</td>
			 		 					<td>
			 		 						${entry.key.name}
			 		 					</td>
			 		 					<td>
			 		 						<c:forEach items="${entry.value}" var="sub" varStatus="loop" >
			 		 							<%-- <c:if test="${loop.index == 0}">
			 		 								${sub.subjectCode} 
			 		 							</c:if>
			 		 							
			 		 							<c:if test="${loop.index != 0}">
			 		 								, ${sub.subjectCode} 
			 		 							</c:if> --%>
			 		 							${sub.subjectCode} </br>
			 		 							
			 		 						</c:forEach>
			 		 					</td>
			 		 					<td>
			 		 						<c:forEach items="${entry.value}" var="sub" varStatus="loop" >
			 		 							<%-- <c:if test="${loop.index == 0}">
			 		 								${sub.nameEng} 
			 		 							</c:if>
			 		 							
			 		 							<c:if test="${loop.index != 0}">
			 		 								, ${sub.nameEng} 
			 		 							</c:if> --%>
			 		 							${sub.nameEng} </br> 
			 		 						</c:forEach>
			 		 					</td>
			 		 						
			 		 					<td>
			 		 						<a href="${root}/courseofferring/editCourse?courseId=${entry.key.id}">Edit</a>
			 		 					</td>
			 		 				</tr>
							  	
								</c:forEach>
		 		 				<%-- <c:forEach items="${courseSearchList}" var="curSub"  >
			 		 				<tr>
			 		 					<td>
											${curSub.curriculum.startYear} 
			 		 					</td>
			 		 					<td>
			 		 						${curSub.curriculum.name}
			 		 					</td>
			 		 					<td>
			 		 						${curSub.subject.subjectCode}
			 		 					</td>
			 		 					<td>
			 		 						${curSub.subject.nameEng}
			 		 					</td>
			 		 				</tr>
								</c:forEach> --%>
		 		 			</c:if>				
		  				</table>
		    		</div>
		  	</div>
		  	
		  
		  	
		  	
  		</form>
  	</div>
    
    <script>
		var cur = "${curriculumName}";
		var year = "${curriculumYear}";
		if(cur != "" )
		{
			$("#curriculum").val(cur);
		}
		if(year != "")
		{
			$("#txtYear").val(year);
		}
		
	</script>
      
</body>
</html>