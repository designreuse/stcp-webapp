/**
 * for CoursePlan Module
 * 
 */

var currentSemester = "";
var touchtime = 0;

$(document).ready(function() {






});

function searchSubject(){
	swal({
		title : "Add Subject",
		text : "Are you want to search this subject?",
		type : "warning",
		showCancelButton : true,
		confirmButtonClass : 'btn btn-info',
		confirmButtonText : "Yes",
		cancelButtonText : "No",
		closeOnConfirm : false,
		showLoaderOnConfirm : true
		
	}, function(isConfirm) {
		
		if (isConfirm) {
			document.getElementById("searchSubject").submit();
		}
	});
}
