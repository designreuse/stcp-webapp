<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Student Course Planer</title>

	<spring:url value="/resources/core/css/main.css" var="coreCss" />
	<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${coreCss}" rel="stylesheet" />
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Student Course Planer</a>
			</div>
		</div>
	</nav>
	<hr>
	<div class="panel panel-default">
		<div class="panel-body">

			<div class="panel panel-default">

				<div class="panel-heading">
					Filter
				</div>

				<div class="input-group">
				  <span class="input-group-addon" id="studentId">Student ID</span>
				  <input type="text" class="form-control" id="basic-url" aria-describedby="studentId" />
				</div>

				<div class="dropup">
					<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Dropup
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul>
				</div>

				asdfdsaf

				<div class="panel-footer text-center">
					<p>
						<button type="button" class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
						</button>
						<button type="button" class="btn btn-default btn-sm">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Clear
						</button>
					</p>
				</div>
			</div>

			<!-- Table -->
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Report Name</th>
						<th>Type</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="master" varStatus="status" items="${records}" >
						<tr>
							<td>
								<c:out value="${status.count}" />
							</td>
							<td>
								<c:out value="${master.reportName}" />
							</td>
							<td>
								<c:out value="${master.reportType}" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		</div>
	</div>



	<footer>
		<p>Report & Statistics Module 2015</p>
	</footer>


	<spring:url value="/resources/core/css/main.js" var="coreJs" />
	<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

	<script src="${coreJs}"></script>
	<script src="${bootstrapJs}"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>

</html>