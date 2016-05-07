<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Course Planner</title>

<c:set var="root" value="${pageContext.request.contextPath}" />

<c:set var="currentYear" value="<%= java.time.LocalDateTime.now().getYear() %>" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="${root}/resources/core/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${root}/resources/core/css/sweetalert.css">
<link rel="stylesheet" href="${root}/resources/core/css/courseplan.css">

</head>
<body>

	<div class="row" style="margin-top: 10px;">
		<div class="col-sm-4">
			<div class="input-group" style="margin-bottom: 3px;">
				<input id="tbxSearchCourse" type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button id="btnSearchCourse" class="btn btn-default" type="button">Search</button>
				</span>
			</div>
			<select id="subjectListID" name="sometext" size="10" class="form-control" style="height: 350px">
				<c:forEach var="subject" items="${subjectlist}">
					<option id="${subject.id}" value="${subject.id}" class="hasPop${subject.status == 2 ? ' optionDisabled': ''}"
						data-subjectcode="${subject.nameThai}" data-credit="${subject.credit}"
						title="${subject.subjectCode} ${subject.nameThai}" data-content="${subject.detailThai}"
						${subject.status == 2 ? ' isdisabled="true"': ''}>${subject.subjectCode}&nbsp;${subject.nameThai}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-xs-12 col-sm-8 col-md-7 col-lg-6">
			<hr class="colorgraph visible-xs" />
			<div class="form-inline">
				<label for="selectSemesterYear">ปีการศึกษา</label>
				<select id="selectSemesterYear" class="form-control" style="display: inline-block; width: 150px">
					<c:forEach var="year" items="${semesterYearList}">
						<option value="${year}">ปีการศึกษา&nbsp;${year + 543}</option>
					</c:forEach>
				</select>
				<button id="btnReset" type="button" class="btn btn-warning btn-sm">
					&nbsp;<i class="fa fa-undo fa-lg"></i>&nbsp;Reset&nbsp;
				</button>
				<button id="btnSave" type="button" class="btn btn-primary btn-sm">
					&nbsp;<i class="fa fa-floppy-o fa-lg"></i>&nbsp;Save&nbsp;
				</button>
				<button id="btnAddYear" type="button" class="btn btn-success btn-sm">
					&nbsp;<i class="fa fa-plus fa-lg"></i>&nbsp;Add Year&nbsp;
				</button>
			</div>
			<br />
			<div class="row">
				<div class="col-xs-12">
					<!-- Tab Control[new] -->
					<ul id="tabSemester" class="nav nav-tabs" role="tablist">
						<c:forEach var="year" items="${semesterYearList}" varStatus="pos">
							<li class="text-center${pos.first? ' active' : ''}" tag="${year}">
								<a href="#${year}1" role="tab" tag="${year}1"><strong>&nbsp;ภาคการเรียนที่ 1&nbsp;</strong></a>
							</li>
							<li class="text-center" tag="${year}">
								<a href="#${year}2" role="tab" tag="${year}2"><strong>&nbsp;ภาคการเรียนที่ 2&nbsp;</strong></a>
							</li>
						</c:forEach>
					</ul>
					<!-- Tab panes -->
					<div id="courseplanlist" class="tab-content">
						<c:forEach var="year" items="${semesterYearList}" varStatus="pos">
						<div role="tabpanel" class="tab-pane fade${pos.first? ' in active' : ''}" id="${year}1" data-semesteryear="${year}" data-semesterterm="1">
							<table class="table table-bordered table-hover" style="max-height: 284px !important;">
								<thead class="thead-default">
									<tr>
										<th class="col-xs-8">Subject</th>
										<th class="col-xs-2">Credit</th>
										<th class="col-xs-2">&nbsp;</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="semester" items="${semesterList}">
									<c:if test="${semester.semesterYear == year && semester.semesterTerm == 1}">
										<tr data-id="${semester.subject.id}" data-subjectcode="${semester.subject.subjectCode}" data-semesterid="${semester.id}">
											<td class="text-left">${semester.subject.subjectCode}&nbsp;${semester.subject.nameThai}</td>
											<td>${semester.subject.credit}</td>
											<%-- <td><button class="btn btn-danger btn-sm courseBtnDelete" ${year < currentYear? 'disabled' : '' }><span class="fa fa-times">&nbsp;</span></button></td> --%>
											<td><button class="btn btn-danger btn-sm courseBtnDelete"><span class="fa fa-times">&nbsp;</span></button></td>
										</tr>
									</c:if>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="${year}2" data-semesteryear="${year}" data-semesterterm="2">
							<table class="table table-bordered table-hover">
								<thead class="thead-default">
									<tr>
										<th class="col-xs-8">Subject</th>
										<th class="col-xs-2">Credit</th>
										<th class="col-xs-2">&nbsp;</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="semester" items="${semesterList}">
									<c:if test="${semester.semesterYear == year && semester.semesterTerm == 2}">
										<tr data-id="${semester.subject.id}" data-subjectcode="${semester.subject.subjectCode}" data-semesterid="${semester.id}">
											<td class="text-left">${semester.subject.subjectCode}&nbsp;${semester.subject.nameThai}</td>
											<td>${semester.subject.credit}</td>
											<%-- <td><button class="btn btn-danger btn-sm courseBtnDelete" ${year < currentYear? 'disabled' : '' }><span class="fa fa-times">&nbsp;</span></button></td> --%>
											<td><button class="btn btn-danger btn-sm courseBtnDelete"><span class="fa fa-times">&nbsp;</span></button></td>
										</tr>
									</c:if>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class=" well well-sm">
						<div class="row">
							<div class="col-xs-6 col-sm-8 text-right">
								<span>Total Credits :</span>
							</div>
							<div class="col-xs-3 col-sm-2 text-center">
								<span id="totalCredits"></span>
							</div>
							<div class="col-xs-3 col-sm-2">
								<small>credits</small>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-8 text-right">
								<span class="">All Years Credits :</span>
							</div>
							<div class="col-xs-3 col-sm-2 text-center">
								<span id="totalAllCredits"></span>
							</div>
							<div class="col-xs-3 col-sm-2">
								<small>credits</small>
							</div>
						</div>
					</div>
				</div>
			</div>

			<span class="label label-warning">Warning!!!</span>
			<br />
			<ol class="text-warning">
				<li>
					Normal status allows <u class="text-danger">9</u> credits/semester
				</li>
				<li>
					Total credits of all semester is <u class="text-danger">36</u> credits
				</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<hr class="colorgraph" />
		</div>
	</div>

	<script type="text/javascript" src="${root}/resources/core/js/sweetalert.js" defer ></script>
	
	<script type="text/javascript" src="${root}/resources/core/js/courseplan.js" defer ></script>

</body>
</html>