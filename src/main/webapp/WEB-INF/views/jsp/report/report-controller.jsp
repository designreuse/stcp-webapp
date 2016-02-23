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


            <div class="input-group">
              <span class="input-group-addon" id="studentId">Student ID</span>
              <input type="text" class="form-control" id="basic-url" aria-describedby="studentId" />
            </div>

			<!-- Table -->
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>Report Name</th>
						<th>Type</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>Courses</td>
						<td>Normal</td>
					</tr>
					<tr>
						<td>2</td>
						<td>Study Plan</td>
						<td>Normal</td>
					</tr>
					<tr>
						<td>3</td>
						<td>Summary</td>
						<td>Statistics</td>
					</tr>
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