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
	

<script type="text/javascript" src="${root}/resources/core/js/addCourse.js"></script>
<script type="text/javascript" src="${root}/resources/core/js/jquery.isloading.min.js"></script>
<title>Student Course Planner</title>
</head>
<body>
      <!-- Main content -->
      <div class="box-header">
        <h3 class="box-title">Edit Course</h3>
      </div><!-- /.box-header -->
     <nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				
			</div>
		</div>
	</nav>


	<div class="container">
		<form id="form"  method="post" action="${root}/courseofferring/editCourse">
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">รหัสหลักสูตร ::</div>
				<div class="col-sm-2">
					<select disabled="disabled">
						<option value="0">--- เลือกหลักสูตร---</option>
						<c:forEach items="${OatCurriculumList}" var="element"> 
							<c:if test="${curriculumSub.get(0)[0].curriculum.id == element.id }">
								<option value="${element.id}" selected>${element.name} ${element.startYear} </option>
							</c:if>
							<c:if test="${curriculumSub.get(0)[0].curriculum.id != element.id }">
								<option value="${element.id}">${element.name} ${element.startYear} </option>
							</c:if>
						</c:forEach>
						
					</select>
					
					<input type="hidden" name="curriculum" value="${curriculumSub.get(0)[0].curriculum.id}">
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">รหัสวิชา ::</div>
				<div class="col-sm-6">
					<c:forEach items="${subjectList}" var="element">
						<c:set var="chkSet" value="0"></c:set>
						<c:forEach items="${curriculumSub}" var="elementSub">
							<c:if test="${elementSub[0].subject.id == element.id }">
								<c:set var="chkSet" value="1"></c:set>
							</c:if>
						</c:forEach>
						<c:if test="${chkSet==1}">
							<input type="checkbox" name="subject" value="${element.id}" checked="checked"> ${element.subjectCode} ${element.nameEng}<br>
						</c:if>
						<c:if test="${chkSet==0}">
							<input type="checkbox" name="subject" value="${element.id}">  ${element.subjectCode} ${element.nameEng}<br>
						</c:if>
					</c:forEach>
				</div>
<!-- 				<div class="col-sm-4"></div> -->
			</div>
			

			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2"></div>
				<div class="col-sm-2">
					<button onclick="resetFunction()" id="btnReset" type="button" class="btn btn-warning btn-sm">
						&nbsp;<i class="fa fa-undo fa-lg"></i>&nbsp;Reset&nbsp;
					</button>
					<button onclick="saveFunction()" id="btnSave" type="button" class="btn btn-primary btn-sm">
						&nbsp;<i class="fa fa-floppy-o fa-lg"></i>&nbsp;Save&nbsp;
					</button>
				</div>
				<div class="col-sm-4"></div>
			</div>
		
		</form>
		
		
	</div>
      
</body>
</html>