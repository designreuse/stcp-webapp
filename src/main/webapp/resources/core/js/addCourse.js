$(document).ready(function() {


	$("#btnSave").click(function() {
		swal({
			title : "Add Cousre",
			text : "Are you want to save this Cousre?",
			type : "warning",
			showCancelButton : true,
			confirmButtonClass : 'btn btn-info',
			confirmButtonText : "Yes",
			cancelButtonText : "No",
			closeOnConfirm : true,
			showLoaderOnConfirm : true
			
		}, function(isConfirm) {
			
			if (isConfirm) {
				
				document.getElementById("form").submit();
				$.isLoading({ text: "Loading" });
		        
		      
			}
		});
		
	});

	$("#btnReset").click(function() {
		
		swal({
			title : "Add Cousre",
			text : "Are you want to reset this Cousre?",
			type : "warning",
			showCancelButton : true,
			confirmButtonClass : 'btn btn-danger',
			confirmButtonText : "Reset",
			cancelButtonText : "No",
			closeOnConfirm : false
		}, function(isConfirm) {
			if (isConfirm) {
				window.location.href = window.location.href;
			}
		});
	});
});