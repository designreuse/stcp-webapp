/**
 * for CoursePlan Module
 * 
 */

var currentSemester = "";
var touchtime = 0;

$(document).ready(function() {
	

	$("#btnSave").click(function() {
		swal({
			title : "Add Subject",
			text : "Are you want to save this subject?",
			type : "warning",
			showCancelButton : true,
			confirmButtonClass : 'btn btn-info',
			confirmButtonText : "Yes",
			cancelButtonText : "No",
			closeOnConfirm : false,
			showLoaderOnConfirm : true
			
		}, function(isConfirm) {
			
			if (isConfirm) {
				document.getElementById("subjectForm").submit();
			}
		});
	});

	$("#btnReset").click(function() {
		$("#preSubjectId").select2();
		swal({
			title : "Add Subject",
			text : "Are you want to reset this subject?",
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
	
	
	$("#chkPre").click(function() {
		
		if($("#chkPre").prop('checked') == false){
			$("#preSubSpan").hide();
		}else{
			$("#preSubSpan").show();
		}
	});

});


