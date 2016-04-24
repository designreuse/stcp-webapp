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
            		data.forEach(function (subject) {
            			alert(subject.detailThai);
            		});
            	} 
            	else{
            		alert("Fale");
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
		
		alert(txtEmail);
		
		$.ajax({
            type: "GET",
            url: "http://localhost:8080/stcp/SentMailConfirm",
            dataType: "json",
            data: { email: txtEmail },
            success: function (data) {
            	
            	if(data != null){
            		data.forEach(function (account) {
            			alert(account.username);
            		});
            	} 
            	else{
            		alert("Fale");
            	}
            },
            error: function (xml, status, errMsg) {
            	//alert(xml);
            	alert(status);
            	alert(errMsg);
            }
        });
	});
});