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
        <h3 class="box-title">Cousre Offerring</h3>
      </div><!-- /.box-header -->
      <!-- <div class="box-body">
		<a href="http://google.com" class="btn btn-default">Manage Subject</a>
		
      </div>/.box-body -->
      
      <table>
      <tr>
      	<td>
      		<div class="box-body">
				<a href="http://google.com" class="btn btn-default">Manage Subject</a>
      		</div><!-- /.box-body -->
      	</td>
      	
      	<td>
      		<div class="box-body">
				<a href="http://google.com" class="btn btn-default">Manage Curriculum</a>
      		</div><!-- /.box-body -->
      	</td>
      	
      	<td>
      		<div class="box-body">
				<a href="http://google.com" class="btn btn-default">Merge Subject && Curriculum</a>
      		</div><!-- /.box-body -->
      	</td>
      	
      </tr>
      </table>
      
</body>
</html>