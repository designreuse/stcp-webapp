/**
 * for CoursePlan Module
 * 
 */

var currentSemester = "";
var touchtime = 0;


$(document).ready(function() {
	

	$("#btnSave").click(function() {
		var element = document.getElementsByName("preSubjectId");
		var tmpPreSub = "";
		var checkDup = true;

		if(element.length>1){
			for(var i=0; i<element.length; i++){
				if(tmpPreSub.indexOf(element[i].value)>(-1)){
					checkDup = false;
				}else{
					tmpPreSub += element[i].value;
				}
			}
		}
		

		swal({
			title : "Save Subject",
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
				
				var subjectCode = $("#subjectCode").val();
				var subjectType = $("#subjectType").val();
				var nameThai = $("#nameThai").val();
				var nameEng = $("#nameEng").val();
				var credit = $("#credit").val();
				
				if(subjectCode!="" && subjectType!="" 
					&& nameThai!="" && nameEng!="" && credit!="0" && checkDup){
					document.getElementById("subjectForm").submit();
				}else{
					if(checkDup){
						alertSubmitFail();
					}else{
						alertFail("Prerequisite can't Duplicate!");
					}
					
				}
				
				
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
	
	$("#btnAddPre").click(function() {
		num++;
		
		var row = "<div id=\"preSubSpan"+num+"\" class=\"row\" style=\"width:400px;padding-left:20px;\">"+
						"<select id=\"preSubjectId"+num+"\" name=\"preSubjectId\" style=\"width:300px;\">"+
						"</select> "+
						"<button id=\"btnDeletePre"+num+"\" onclick=\"removeSpan('preSubSpan"+num+"')\" type=\"button\" class=\"btn btn-default btn-sm\">"+
							"&nbsp;<i class=\"fa fa-minus fa-lg\"></i>"+
						"</button>"+
					"</div>";
		$("#preSubSpan").append(row);
		
		var $options = $("#preSubjectId0 > option").clone();

		$("#preSubjectId"+num).append($options);
		
		$("#preSubjectId"+num).select2();
	});
	

});

function alertSubmitFail(){
	
	swal("Please fill in all required information!", "", "error");
}

function alertFail(text){
	
	swal(text, "", "error");
}

function removeSpan(id){
	$("#"+id).remove();
	
}
