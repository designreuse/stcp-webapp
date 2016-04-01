<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Course Planner</title>

<c:set var="root" value="${pageContext.request.contextPath}" />

<%-- <link rel="stylesheet" href="${root}/resources/core/css/bootstrap.min.css"> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="${root}/resources/core/js/bootstrap.min.js"></script>

<!-- SweetAlert -->
<script src="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.js"></script>
<link rel="stylesheet" href="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.css">

<style type="text/css">
/*body{  
    margin:0;  
    padding:0;    
    font-family:Tahoma, Geneva, sans-serif;  
    font-size:12px;  
}*/
div#i_containTab {
	position: relative;
	display: block;
	width: 540px; /* กำหนดความกว้างทั้งหมด   */
	border: 1px solid #CCC;
}

ul#navi_containTab {
	list-style: none;
	padding: 0;
	margin: 0;
	width: 100%;
	background-color: #FCF;
}

ul#navi_containTab li {
	display: block;
	float: left;
	height: 30px;
	width: 100px; /* กำหนดความกว้างทั้งหมด  หารด้วยจำนวนเมนูแท็บ  */
	border: 0px solid #CCC;
	line-height: 25px;
	cursor: pointer;
	text-align: center;
	/*border:1px solid black;*/
}
/* class รูปแบบของแต่ละเมนู */
.tabNavi1 {
	background-color: #848484;
	color: #ffffff;
}

.tabNavi2 {
	background-color: #A4A4A4;
	color: #ffffff;
}

ul#detail_containTab {
	list-style: none;
	padding: 0;
	margin: 0;
	width: 100%;
}

ul#detail_containTab li {
	float: left;
	width: 100%;
	height: 250px;
}
/* class รูปแบบของแต่ละเนื้อหา */
.detailContent1 {
	background-color: white;
	border: 1px solid black;
	display: block;
}

.detailContent2 {
	background-color: white;
	border: 1px solid black;
	display: none;
}

.table-subject {
	border: 1px solid black;
	width: 100%;
}

.td-subject {
	border: 1px solid black;
	width: 40%;
}

.td-credit {
	border: 1px solid black;
	width: 30%;
	text-align: center;
}

.td-delete {
	border: 1px solid black;
	width: 30%;
}

.colorgraph {
	height: 5px;
	border-top: 0;
	background: #c4e17f;
	border-radius: 5px;
	background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%,
		#f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%,
		#db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%,
		#669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%,
		#f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%,
		#db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%,
		#669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca
		25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe
		50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1
		87.5%, #62c2e4 87.5%, #62c2e4);
	background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca
		25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe
		50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1
		87.5%, #62c2e4 87.5%, #62c2e4);
}
</style>


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
			<select id="subjectListID" name="sometext" size="10" class="form-control" style="width: 100%; height: 350px">
				<c:forEach var="course" items="${courselist}">
					<option id="${course.code}" value="${course.id}" title="${course.name}" class="hasPop">${course.code}&nbsp;${course.name}</option>
				</c:forEach>
			</select>
			<%-- <div class="list-group" style="height: 350px; overflow-x: auto; overflow-y: auto;">
				<c:forEach var="course" items="${courselist}">
					<button type="button" id="${course.code}_2" class="list-group-item" value="${course}" title="${course.name}">
						<span class="glyphicon glyphicon-question-sign pull-right"></span>
						<h4 class="list-group-item-heading">${course.code}</h4>
						<p class="list-group-item-text">${course.name}</p>
					</button>
				</c:forEach>
			</div> --%>
		</div>

		<div class="col-xs-12 col-sm-8 col-md-7 col-lg-6">
			<div class="form-inline">
				<label for="selectYear">ปีการศึกษา</label>
				<select id="selectYear" class="form-control" style="width: 100px">
					<option value="1">ปี1</option>
					<option value="2">ปี2</option>
					<option value="3">ปี3</option>
					<option value="4">ปี4</option>
				</select>
				<button id="btnReset" type="button" class="btn btn-warning btn-sm">Reset</button>
				<button id="btnSave" type="button" class="btn btn-primary btn-sm">Save</button>
			</div>
			<br />
			<ul id="navi_containTab">
				<li class="tabNavi1">เทอม 1</li>
				<li class="tabNavi2">เทอม 2</li>
			</ul>
			<ul id="detail_containTab">
				<li class="detailContent1">
					<div id="tab1" style="height: 100%">
						<div id="subjectPlan1" style="height: 80%">
							<table id="tableSubject1" class="table-subject">
								<tr>
									<td class="td-subject" style="background-color: #F2F2F2; text-align: center;"><b>Subject</b></td>
									<td class="td-credit" style="background-color: #F2F2F2"><b>Credit</b></td>
									<td class="td-delete" style="background-color: #F2F2F2"></td>
								</tr>
							</table>
						</div>
						<div id="detailPlan1" style="height: 20%; padding-left: 10px">
							<span>Total Credits : </span>
							<span id="totalCredits1"> 0 </span>
							<br>
							<span>All Years Credits : </span>
							<span> 36 </span>
						</div>
					</div>
				</li>
				<li class="detailContent2">
					<div id="tab2" style="height: 100%">
						<div id="subjectPlan2" style="height: 80%">
							<table id="tableSubject2" class="table-subject">
								<tr>
									<td class="td-subject" style="background-color: #F2F2F2; text-align: center;"><b>Subject</b></td>
									<td class="td-credit" style="background-color: #F2F2F2"><b>Credit</b></td>
									<td class="td-delete" style="background-color: #F2F2F2"></td>
								</tr>
							</table>
						</div>
						<div id="detailPlan2" style="height: 20%; padding-left: 10px">
							<span>Total Credits : </span>
							<span id="totalCredits2"> 0 </span>
							<br>
							<span>All Years Credits : </span>
							<span> 36 </span>
						</div>
					</div>
				</li>
			</ul>

			<br />
			<span>
				Warning!! <br> -Normal status allows 9 credits per semester
			</span>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<hr class="colorgraph" />
			<input type="hidden" id="currentYear" value="hhh" />
		</div>
	</div>

	<script>
		var term = "0"; //เทอม1

		/* $("body")
				.popover(
						{
							trigger : "hover",
							container : 'body',
							placement : 'right auto',
							delay : {
								"show" : 800,
								"hide" : 100
							},
							template : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title" style="white-space: nowrap;"></h3><div class="popover-content"></div></div>',
							title : "Dismissible popover",
							content : "And here's some amazing content. It's very engaging. Right?",
							selector : ".hasPop"
						}); */

		/* $('.hasPop')
				.popover(
						{
							trigger : "hover",
							container : 'body',
							placement : 'right auto',
							delay : {
								"show" : 800,
								"hide" : 100
							},
							template : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title" style="white-space: nowrap;"></h3><div class="popover-content"></div></div>',
							title : "Dismissible popover",
							content : "And here's some amazing content. It's very engaging. Right?"
						}); */

		//$("#subjectListID > option").popover(function(){
		//show popup;
		//})
		$('#subjectListID').dblclick(function() {

			var subject = $(this).children("option:selected");

			subject.attr("disabled", "true");
			insertSubjectToTable(subject.attr("id"), "3");

		});

		function insertSubjectToTable(subject, credit) {
			var table = null;
			if (term == 0) {
				table = document.getElementById("tableSubject1");

				var oldCredits = parseInt(totalCredits1.textContent);
				var credits = oldCredits + parseInt(credit);
				totalCredits1.innerHTML = credits;
			} else {
				table = document.getElementById("tableSubject2");

				var oldCredits = parseInt(totalCredits2.textContent);
				var credits = oldCredits + parseInt(credit);
				totalCredits2.innerHTML = credits;
			}

			var btnDelete = document.createElement("BUTTON");
			var text = document.createTextNode("delete");
			btnDelete.appendChild(text);
			btnDelete.className += "btn btn-warning btn-sm";
			btnDelete.addEventListener("click", function() {
				btnDeleteSubject_Click(subject, credit);
			});

			//var table = document.getElementById("tableSubject");
			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);

			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);

			cell1.innerHTML = subject;
			cell2.innerHTML = credit; //หน่วยกิต
			//cell3.innerHTML = "Delete" //ปุ่มลบ
			cell3.appendChild(btnDelete);

			cell1.className = "td-subject";
			cell2.className = "td-credit";
			cell3.className = "td-delete";
		}

		function btnDeleteSubject_Click(subject, credit) {
			var table = null;
			if (term == 0) {
				table = document.getElementById("tableSubject1");
			} else {
				table = document.getElementById("tableSubject2");
			}
			//var table = document.getElementById('tableSubject');
			var rowCount = table.rows.length;
			for (var i = 0; i < rowCount; i++) {
				var row = table.rows[i];
				var rowObj = row.cells[0].childNodes[0];

				if (rowObj.textContent == subject) {
					table.deleteRow(i);
					document.getElementById(subject).disabled = false;
					rowCount--;

					if (term == 0) {
						var oldCredits = parseInt(totalCredits1.textContent);
						var credits = oldCredits - parseInt(credit);
						totalCredits1.innerHTML = credits;
					} else {

						var oldCredits = parseInt(totalCredits2.textContent);
						var credits = oldCredits - parseInt(credit);
						totalCredits2.innerHTML = credits;
					}
				}
			}

		}

		/* $("ul#navi_containTab").click(function() {
			var listt = document.getElementById('navi_containTab');
		}); */

		$("#selectYear").change(function() {

		});

		$("ul#navi_containTab > li").click(function(event) {

			var menuIndex = $(this).index();
			term = menuIndex.toString();

			$("ul#detail_containTab > li:visible").hide();
			$("ul#detail_containTab > li").eq(menuIndex).show();

		});

		$("#btnSave").click(function() {
			swal({
				title : "Course Planner",
				text : "Are you want to save this plan?",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : 'btn btn-info',
				confirmButtonText : "Yes",
				cancelButtonText : "No",
				closeOnConfirm : false,
				showLoaderOnConfirm : true
			}, function(isConfirm) {
				if (isConfirm) {
					setTimeout(function() {
						swal({
							title : "Course Planner",
							text : "This plan has been saved",
							type : "success",
							confirmButtonClass : 'btn btn-success',
							confirmButtonText : 'OK'
						});
					}, 2000);
				}
			});
		});

		$("#btnReset").click(function() {
			swal({
				title : "Course Planner",
				text : "Are you want to reset this plan?",
				type : "warning",
				showCancelButton : true,
				confirmButtonClass : 'btn btn-danger',
				confirmButtonText : "Reset",
				cancelButtonText : "No",
				closeOnConfirm : false
			}, function(isConfirm) {
				if (isConfirm) {
					window.location.href = "${root}/coursePlanner/";
				}
			});
		});
	</script>
</body>
</html>