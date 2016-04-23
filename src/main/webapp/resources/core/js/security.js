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
            data: { textsearch: "" },
            success: function (data) {
            	
            	if(data != null){
            		alert("Pass");
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
});