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
        <h3 class="box-title">Header Content</h3>
      </div><!-- /.box-header -->
      <div class="box-body">
		<p>Content</p>
		<h3>วิธีการตั้งค่า template ให้ Module ตัวเอง</h3>
		<p>ตั้งค่าที่ไฟล์ WEB-INF/decorator.xml ดูตัวอย่างจากในไฟล์ /test/*(/url ของ controller/*)</p>
      </div><!-- /.box-body -->
</body>
</html>