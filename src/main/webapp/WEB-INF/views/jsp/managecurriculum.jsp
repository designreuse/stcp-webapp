<%@page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  
<title>Student Course Planner</title>
</head>
 <style>
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

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}
 </style>
<body>

      <!-- Main content -->
      <div class="box-header">
      Manage Curriculum
      </div><!-- /.box-header -->
     
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>     
         <span class="icon-bar"></span>                                  
      </button>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Search</a></li>
        <li><a href="${root}/courseofferring/DetailCurriculum">New</a></li>
        <li><a href="#">Cancel</a></li>
         <li><a href="#">Close</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">
<div class="row" style="margin-bottom:10px;">
<div class="col-sm-1"></div>
  <div class="col-sm-2" style="text-align:middle;" >ปีการศึกษา  ::</div>
  <div class="col-sm-2"> 
  <input type="text" class="form-control" id="txtYear">
</div>
</div>
  <div class="row" >
  <div class="col-sm-1"></div>
  <div class="col-sm-2">รหัสหลักสูตร  ::</div>
  <div class="col-sm-2"> 
  <input type="text" class="form-control" id="txtCuriID">
</div>
  </div>
</div>
<div class="row">
</div>
  <br>

<div >
  <div class="panel panel-default">
    <div class="panel-heading">Search Result</div>
    <div class="panel-body">
     <table  class="table table-bordered">
 		 <tr>
    <th>Curriculum ID</th>
    <th>Curriculum Name.</th>
    <th>Curriculum Name(Eng).</th>
     <th>Total credit.</th>
      <th>Teacher course.</th>
  </tr>
    </div>
  </div>
</div>


 
</table>

      
</body>
</html>