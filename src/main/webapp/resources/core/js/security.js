/**
 * 
 */

$(document).ready(function() {
	$("#btnSignIn").on('click', function(e){
		e.preventDefault();
		
		$.ajax({
            type: "GET",
            url: "http://localhost:8080/stcp/login",
            dataType: "json",
            data: { textsearch: "testinput" },
            success: function (data) {
            	if(data != null){
            		if(data[0].msg == "success"){
            			alert("success");
            		}
            		else{
            			swal({
                			title : "Student Course Planner",
                			text : "Error",
                			type : "error",
                			showCancelButton : false,
                			confirmButtonClass : 'btn btn-info',
                			confirmButtonText : "OK",
                			closeOnConfirm : false
                		}, function(isConfirm) {
                			if (isConfirm) {
                				window.location.href = window.location.href;
                			}
                		});
            		}
            	}
            	else{
            		swal({
            			title : "Student Course Planner",
            			text : "Error",
            			type : "error",
            			showCancelButton : false,
            			confirmButtonClass : 'btn btn-info',
            			confirmButtonText : "OK",
            			closeOnConfirm : false
            		}, function(isConfirm) {
            			if (isConfirm) {
            				window.location.href = window.location.href;
            			}
            		});
            	}
            },
            error: function (xml, status, errMsg) {
            	alert(xml);
            	alert(status);
            	alert(errMsg);
            }
        });
	});
	
	$("#btnRegister").on('click', function(e){
		e.preventDefault();
		
		var txtEmail = $('#tbxRegisterEmail').val();
		
		$("#loadingModal").modal("show");
		//RegisterUser
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
                			showCancelButton : false,
                			confirmButtonClass : 'btn btn-info',
                			confirmButtonText : "OK",
                			closeOnConfirm : false
                		}, function(isConfirm) {
                			if (isConfirm) {
                				window.location.href = window.location.href;
                			}
                		});
            		}
            	}
            	else{
            		swal({
            			title : "Student Course Planner",
            			text : "Error",
            			type : "error",
            			showCancelButton : false,
            			confirmButtonClass : 'btn btn-info',
            			confirmButtonText : "OK",
            			closeOnConfirm : false
            		}, function(isConfirm) {
            			if (isConfirm) {
            				window.location.href = window.location.href;
            			}
            		});
            	}
        	},
            error: function (xml, status, errMsg) {
            	$("#loadingModal").modal("hide");
            	//alert(xml);
            	alert(status);
            	alert(errMsg);
            }
        });
		/*$.ajax({
            type: "GET",
            url: "http://localhost:8080/stcp/SentMailConfirm",
            dataType: "json",
            data: { email: txtEmail },
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
                			showCancelButton : false,
                			confirmButtonClass : 'btn btn-info',
                			confirmButtonText : "OK",
                			closeOnConfirm : false
                		}, function(isConfirm) {
                			if (isConfirm) {
                				window.location.href = window.location.href;
                			}
                		});
            		}
            	}
            	else{
            		swal({
            			title : "Student Course Planner",
            			text : "Error",
            			type : "error",
            			showCancelButton : false,
            			confirmButtonClass : 'btn btn-info',
            			confirmButtonText : "OK",
            			closeOnConfirm : false
            		}, function(isConfirm) {
            			if (isConfirm) {
            				window.location.href = window.location.href;
            			}
            		});
            	}
        	},
            error: function (xml, status, errMsg) {
            	$("#loadingModal").modal("hide");
            	//alert(xml);
            	alert(status);
            	alert(errMsg);
            }
        });*/
	});
});