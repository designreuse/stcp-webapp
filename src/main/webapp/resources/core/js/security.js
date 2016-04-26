/**
 * 
 */

$(document).ready(function() {
	$("#btnSignIn").on('click', function(e){
		e.preventDefault();
		
		var txtUserName = $('#tbxUserName').val();
		var txtPassword = $('#tbxPassword').val();
		
		$.ajax({
            type: "GET",
            url: "http://localhost:8080/stcp/Login",
            dataType: "json",
            data: { Usr: txtUserName, Pwd: txtPassword },
            success: function (data) {
            	if(data != null){
            		if(data.msg == "success"){
            			window.location.href = "http://localhost:8080/stcp/RegistrationComplete"
            		}
            		else{
            			swal({
                			title : "Student Course Planner",
                			text : data.msg,
                			type : "error",
       					   	showCancelButton: false 
                		});
            		}
            	}
            	else{
            		swal({
            			title : "Student Course Planner",
            			text : "System Error, Please contact system admin",
            			type : "error",
   					   	showCancelButton: false 
            		});
            	}
            },
            error: function (xml, status, errMsg) {
            	swal({
        			title : "Student Course Planner",
        			text : "System Error, Please contact system admin",
        			type : "error",
					   	showCancelButton: false 
        		});
            }
        });
	});
	
	$("#btnRegister").on('click', function(e){
		e.preventDefault();
		
		var txtEmail = $('#tbxRegisterEmail').val();
		
		$("#loadingModal").modal("show");
	
		$.ajax({
            type: "GET",
            url: "http://localhost:8080/stcp/RegisterUser",
            dataType: "json",
            data: { Email: txtEmail },
            success: function (data) {
            	$("#loadingModal").modal("hide");
            	
            	if(data != null){
            		if(data.msg == "success"){
            			window.location.href = "http://localhost:8080/stcp/RegistrationComplete"
            		}
            		else{
            			swal({
                			title : "Student Course Planner",
                			text : data.msg,
                			type : "error",
       					   	showCancelButton: false 
                		});
            		}
            	}
            	else{
            		swal({
            			title : "Student Course Planner",
            			text : "System Error, Please contact system admin",
            			type : "error",
   					   	showCancelButton: false 
            		});
            	}
        	},
            error: function (xml, status, errMsg) {
            	$("#loadingModal").modal("hide");
            	
            	swal({
        			title : "Student Course Planner",
        			text : errMsg,
        			type : "error",
					   	showCancelButton: false 
        		});
            }
        });
		
	});
	
	$("#btnCreateUser").on('click', function(e){
		e.preventDefault();
		
		var txtEmail = $('#tbxEmail').val();
		var txtPassword = $('#tbxPassword').val();
		
		$("#loadingModal").modal("show");
		$.ajax({
            type: "GET",
            url: "http://localhost:8080/stcp/CreateUser",
            dataType: "json",
            data: { Email: txtEmail, Password: txtPassword },
            success: function (data) {
            	$("#loadingModal").modal("hide");
            	
            	if(data != null){
            		if(data.msg == "success"){
            			window.location.href = "http://localhost:8080/stcp/RegistrationComplete"
            		}
            		else{
            			swal({
                			title : "Student Course Planner",
                			text : data.msg,
                			type : "error",
       					   	showCancelButton: false 
                		});
            		}
            	}
            	else{
            		swal({
            			title : "Student Course Planner",
            			text : "System Error, Please contact system admin",
            			type : "error",
   					   	showCancelButton: false 
            		});
            	}
        	},
            error: function (xml, status, errMsg) {
            	$("#loadingModal").modal("hide");
            	
            	swal({
        			title : "Student Course Planner",
        			text : errMsg,
        			type : "error",
					   	showCancelButton: false 
        		});
            }
        });
		
	});
});