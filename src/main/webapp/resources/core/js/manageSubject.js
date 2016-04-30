/**
 * for CoursePlan Module
 * 
 */

var currentSemester = "";
var touchtime = 0;

$(document).ready(function() {






});

function searchSubject(){
	document.getElementById("searchSubject").submit();
	/*swal({
		title : "Search Subject",
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
	});*/
}


function delSubject(id){
	swal({
		title : "Delete Subject",
		text : "Are you want to delete this subjectId : "+id+" ?",
		type : "warning",
		showCancelButton : true,
		confirmButtonClass : 'btn btn-info',
		confirmButtonText : "Yes",
		cancelButtonText : "No",
		closeOnConfirm : false,
		showLoaderOnConfirm : true
		
	}, function(isConfirm) {
		
		if (isConfirm) {
			document.getElementById("delSubjForm"+id).submit();
		}
	});
	
}

