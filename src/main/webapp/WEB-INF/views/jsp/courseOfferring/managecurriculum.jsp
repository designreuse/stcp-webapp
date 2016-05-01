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
<script>
$(document).ready(function() {
	
	if("${addSuccess}"=="Y"){
		alert("Add Curriculum success.!");
	}else if("${addSuccess}"=="N"){
		alert("Add Curriculum is failed.!");		
	}
	
	if("${editSuccess}"=="Y"){
		alert("Edit Curriculum success.!");
	}else if("${editSuccess}"=="N"){
		alert("Edit Curriculum failed.!");
	}
	
});
</script>
      <!-- Main content -->
      <div class="box-header">
      <h3 class="box-title">Manage Curriculum</h3>
      </div><!-- /.box-header -->
     

	<!-- Navigator -->
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
			<ul class="nav nav-pills">
		        <li><a href="#">Search</a></li>
		        <li><a href="${root}/courseofferring/addCurriculum">Add</a></li>
		        <li><a href="${root}/courseofferring/courseofferring">Close</a></li>
		       
		    </ul>
	    </div>
	  </div>
	</nav>

	<div class="container-fluid">
		<div class="row" style="margin-bottom:10px; text-align;left;">			
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">ปีการศึกษา  ::</div>
			<div class="col-xs-12 col-md-2"><input type="text" class="form-control" id="searchYear"  size="20"></div>
			<div class="col-xs-12 col-md-8"></div>
		</div>
		<div class="row" >			
			<div class="col-xs-12 col-md-2" style="padding-left: 50px;">รหัสหลักสูตร  ::</div>
			<div class="col-xs-12 col-md-2"><input type="text" class="form-control" id="accId" width="20"></div>
			<div class="col-xs-12 col-md-8"></div>
		</div>
		<div style="margin-bottom:20px;"></div>	<!-- empty -->
		<div class="panel panel-default">
    		<div class="panel-heading">Search Result</div>
    		<div class="panel-body">
     			<table class="table table-condensed">
 		 			<tr>
 		 				<th></th>
						<th>Curriculum ID</th>
						<th>Curriculum Name.</th>
						<th>Curriculum Name(Eng).</th>
						<th>Total credit.</th>
						<th>Teacher course.</th>
						<th>Manage</th>
  					</tr>
  					<tbody id="searchResult">
 		 			<tr>
 		 				<!-- <td colspan="6" style="text-align:center;padding-top:10px">No Result</td> -->
 		 				<td colspan="7" style="text-align:center">No Result</td>
						
  					</tr>
  					</tbody>
  							
  				</table>
    		</div>
  		</div>	
  		<script>
  					$(document).ready(function() {
  						
  						/* $("#delBtn").onclick(function(){
  							if(confirm("Are you sure to delete this ?")){
  								href = document.getElementById("delBtn").href;
  								window.location = href;
  							}else{
  								return false;
  							}
  						}) */
  						
  						$("#searchYear").keyup(function(){
  							var searchYear = $("#searchYear").val() == "" ? "none":$("#searchYear").val();
  							var accId = $("#accId").val() == "" ? "none":$("#accId").val();
  							var dataString = "searchYear="+searchYear+"&searchAccId="+accId;
  							
  							$.ajax({
  				                type: "POST",
  				                url: "searchcurriculum",
  				                data: dataString,
  				                dataType: "json",
  				               
  				                //if received a response from the server
  				                success: function( data, textStatus, jqXHR) {
  				                    //our country code was correct so we have some information to display
  				                     if(data.success){
  				                    	 $("#searchResult").html(data);
  				                     } 
  				                     //display error message
  				                     else {
  				                         $("#searchResult").html("<td>No Result</td>");
  				                     }
  				                },
  				               
  				                //If there was no resonse from the server
  				                error: function(jqXHR, textStatus, errorThrown){
  				                     console.log("Something really bad happened " + textStatus);
  				                      $("#searchResult").html(jqXHR.responseText);
  				                }
  				               
  				                /* //capture the request before it was sent to server
  				                beforeSend: function(jqXHR, settings){
  				                    //adding some Dummy data to the request
  				                    settings.data += "&dummyData=whatever";
  				                    //disable the button until we get the response
  				                    $('#myButton').attr("disabled", true);
  				                },
  				               
  				                //this is called after the response or error functions are finsihed
  				                //so that we can take some action
  				                complete: function(jqXHR, textStatus){
  				                    //enable the button 
  				                    $('#myButton').attr("disabled", false);
  				                } */
  						})
  						})
  						
  						$("#accId").keyup(function(){
  							var searchYear = $("#searchYear").val() == "" ? "none":$("#searchYear").val();
  							var accId = $("#accId").val() == "" ? "none":$("#accId").val();
  							var dataString = "searchYear="+searchYear+"&searchAccId="+accId;
  							$.ajax({
  				                type: "POST",
  				                url: "searchcurriculum",
  				                data: dataString,
  				                dataType: "json",
  				               
  				                //if received a response from the server
  				                success: function( data, textStatus, jqXHR) {
  				                    //our country code was correct so we have some information to display
  				                     if(data.success){
  				                    	 $("#searchResult").html(data);
  				                     } 
  				                     //display error message
  				                     else {
  				                         $("#searchResult").html("<td colspan='6' style='text-align:center'>No Result</td>");
  				                     }
  				                },
  				               
  				                //If there was no resonse from the server
  				                error: function(jqXHR, textStatus, errorThrown){
  				                     console.log("Something really bad happened " + textStatus);
  				                      $("#searchResult").html(jqXHR.responseText);
  				                }
  				               
  				                /* //capture the request before it was sent to server
  				                beforeSend: function(jqXHR, settings){
  				                    //adding some Dummy data to the request
  				                    settings.data += "&dummyData=whatever";
  				                    //disable the button until we get the response
  				                    $('#myButton').attr("disabled", true);
  				                },
  				               
  				                //this is called after the response or error functions are finsihed
  				                //so that we can take some action
  				                complete: function(jqXHR, textStatus){
  				                    //enable the button 
  				                    $('#myButton').attr("disabled", false);
  				                } */
  						})
  						})
  					});
  		</script>
	</div>
    
</body>
</html>