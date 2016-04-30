<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
		<h3 class="box-title">Edit Subject</h3>
	</div>
	<!-- /.box-header -->
	<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			
		</div>
	</nav>


	<div class="container">
		<form:form method="post" modelAttribute="subjectForm">
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">รหัสวิชา ::</div>
				<div class="col-sm-2">
					<form:hidden path="id"/>
					<form:hidden path="status"/>
					<form:input path="subjectCode" cssClass="form-control"  cssStyle="width:300px;"/>
					<i class="form-control-feedback fa fa-asterisk" data-fv-icon-for="name" style="left:300px;padding-left: 20px;"></i>
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">ประเภทวิชา ::</div>
				<div class="col-sm-2">
					<form:select path="subjectType" cssClass="form-control" cssStyle="width:300px;">
						<form:option value="" label="--- เลือกประเภทวิชา ---"/>
						<form:options items="${subjectTypeList}" />
					</form:select>
					<i class="form-control-feedback fa fa-asterisk" data-fv-icon-for="name" style="left:300px;padding-left: 20px;"></i>
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">ชื่อวิชา(ไทย) ::</div>
				<div class="col-sm-2">
					<form:input path="nameThai" cssClass="form-control"  cssStyle="width:300px;"/>
					<i class="form-control-feedback fa fa-asterisk" data-fv-icon-for="name" style="left:300px;padding-left: 20px;"></i>
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">ชื่อวิชา(อังกฤษ) ::</div>
				<div class="col-sm-2">
					<form:input path="nameEng" cssClass="form-control"  cssStyle="width:300px;"/>
					<i class="form-control-feedback fa fa-asterisk" data-fv-icon-for="name" style="left:300px;padding-left: 20px;"></i>
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">รายละเอียด(ไทย) ::</div>
				<div class="col-sm-2">
					<form:input path="detailThai" cssClass="form-control" cssStyle="width:300px;" />
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">รายละเอียด(อังกฤษ) ::</div>
				<div class="col-sm-2">
					<form:input path="detailEng" cssClass="form-control" cssStyle="width:300px;"/>
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">วิชายังคับก่อน ::</div>
				<div class="col-sm-2">

					<c:if test="${prerequisite != null }">
						<input type="hidden" name="preRequisiteId" id="preRequisiteId" value="${prerequisite[0].id}">
						<input type="hidden"  id="preSubjectTmp" value="${prerequisite[0].subjectByPresubjectId.id}">
						
						<input type="checkbox" id="chkPre" name="chkPre" onclick="setDefaultPreSub()" checked> วิชาบังคับก่อน
						&nbsp;
						<span id="preSubSpan">
							<select id="preSubjectId" name="preSubjectId" style="width:300px;">
								<option value="">--- เลือกรายวิชาบังคับก่อน---</option>
								<c:forEach items="${subjectList}" var="item">
									<c:if test="${item.key!=subjectForm.id }">
										<c:if test="${item.key == prerequisite[0].subjectByPresubjectId.id}">
											<option value="${item.key}" selected>${item.value}</option>
										</c:if>
										<c:if test="${item.key != prerequisite[0].subjectByPresubjectId.id}">
											<option value="${item.key}">${item.value}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</select>
						
					</c:if> 
					<c:if test="${prerequisite == null }">
						<input type="hidden"  id="preRequisiteId" name="preRequisiteId" value="">
						<input type="checkbox" id="chkPre" name="chkPre" onclick="setDefaultPreSub()"> วิชาบังคับก่อน
						&nbsp;
						<span id="preSubSpan" style="display:none;">
							<select id="preSubjectId" name="preSubjectId" style="width:300px;">
								<option value="">--- เลือกรายวิชาบังคับก่อน---</option>
								<c:forEach items="${subjectList}" var="item">
									<c:if test="${item.key!=subjectForm.id }">
										<option value="${item.key}">${item.value}</option>
									</c:if>
								</c:forEach>
							</select>
					</c:if> 

					
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">หน่่วยกิต ::</div>
				<div class="col-sm-2">
					<form:select path="credit"  cssClass="form-control" cssStyle="width:300px;">
						<form:option value="0" label="--- เลือกหน่วยกิต---"/>
						<form:options items="${creditList}" />
					</form:select>
					<i class="form-control-feedback fa fa-asterisk" data-fv-icon-for="name" style="left:300px;padding-left: 20px;"></i>
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="row" style="margin-bottom: 10px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-2"></div>
				<div class="col-sm-2">
					<button id="btnReset" type="button" class="btn btn-warning btn-sm">
						&nbsp;<i class="fa fa-undo fa-lg"></i>&nbsp;Reset&nbsp;
					</button>
					<button id="btnSave" type="button" class="btn btn-primary btn-sm">
						&nbsp;<i class="fa fa-floppy-o fa-lg"></i>&nbsp;Save&nbsp;
					</button>
				</div>
				<div class="col-sm-4"></div>
			</div>
		
		</form:form>



		
		
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/addSubject.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#preSubjectId').select2();
	});

	function setDefaultPreSub(){
		
		if($("#chkPre").prop('checked') == false){
			if($("#preRequisiteId").val()!=""){
				$("#preSubjectId").val("0");
			}
			
			$("#preSubSpan").hide();
		}else{
			if($("#preRequisiteId").val()!=""){
				$("#preSubjectId").val($("#preSubjectTmp").val());
			}
			
			$("#preSubSpan").show();
		}
	}
</script>
</body>
</html>