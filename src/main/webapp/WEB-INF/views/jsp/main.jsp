<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Course Planer</title>

    <!-- for include csss -->
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    
  	<link href="${bootstrapCss}" rel="stylesheet"/>
    
    <!-- SweetAlert (message alert -->
	<script src="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.min.css">
    
    <!-- for include script -->
	<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${bootstrapJs}"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<fieldset class="userprofile-border">
					<legend class="userprofile-border">User Profile</legend>
					<div class="control-userprofile">
						<div class="row">
							<div class="col-xs-11 col-xs-offset-1 col-md-11 col-md-offset-1">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<th style="width:10%">First name :</th>
											<td style="width:90%"><c:out value="${loginusr.firstname}"/></td>
										</tr>
										<tr>
											<th style="width:10%">Last name :</th>
											<td style="width:90%"><c:out value="${loginusr.lastname}"/></td>
										</tr>
										<tr>
											<th style="width:10%">Citizen id :</th>
											<td style="width:90%"> </td><%-- <c:out value="${loginusr.citizen_id}"/></td> --%> 
										</tr>
										<tr>
											<th style="width:10%">Birth day :</th>
											<td style="width:90%"><c:out value="${loginusr.birthday}"/></td>
										</tr>
										<tr>
											<th style="width:10%">Gender :</th>
											<td style="width:90%"><c:out value="${loginusr.gender}"/></td>
										</tr>
										<tr>
											<th style="width:10%">Address :</th>
											<td style="width:90%"><c:out value="${loginusr.address}"/></td>
										</tr>
										<tr>
											<th style="width:10%">E-mail :</th>
											<td style="width:90%"><c:out value="${loginusr.email}"/></td>
										</tr>
										<tr>
											<th style="width:10%">Mobile :</th>
											<td style="width:90%"><c:out value="${loginusr.mobile}"/></td>
										</tr>
										<tr>
											<th style="width:10%">Faculty :</th>
											<td style="width:90%"><c:out value="${loginusr.faculty}"/></td>
										</tr>
										<tr>
											<th style="width:10%">Major :</th>
											<td style="width:90%"><c:out value="${loginusr.major}"/></td>
										</tr>
										<tr>
											<th style="width:10%">Curriculum :</th>
											<td style="width:90%"><c:out value="${curricul.name}"/></td> 
										</tr>
										<tr>
											<th style="width:10%">Semester :</th>
											<td style="width:90%"><c:out value="${loginusr.semester}"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>