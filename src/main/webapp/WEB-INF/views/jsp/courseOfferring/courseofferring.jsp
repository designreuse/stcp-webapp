<%@page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Course Planner</title>
</head>
<body>
      <!-- Main content -->
      <div class="box-header">
        <h3 class="box-title">Course Offerring</h3>
      </div><!-- /.box-header -->
      <!-- <div class="box-body">
		<a href="http://google.com" class="btn btn-default">Manage Subject</a>
		
      </div>/.box-body -->
    	<nav class="navbar navbar-default">
	  		<div class="container-fluid">
	    		<div class="navbar-header">
					<ul class="nav nav-pills">
				        <li><a href="${root}/courseofferring/managesubject">Manage Subject</a></li>
				        <li><a href="${root}/courseofferring/managecurriculum">Manage Curriculum</a></li>

				        <li><a href="${root}/courseofferring/managecourse">Manage Course</a></li>
				    

				    </ul>
			    </div>
			</div>
		</nav>
      
     <script type="text/javascript" src="${root}/resources/core/js/addCourse.js"></script>
      
</body>
</html>