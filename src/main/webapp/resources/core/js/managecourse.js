$(document).ready(function() {
	//$("#result").hide();
});

function searchCurriculumName()
{
	var year = $("#txtYear").val();
	var curriculum = $("#curriculum").val();
	

	$("#result").show();

	/*if(year == "" && curriculum == 0 )
	{
		alert("กรุณาระบุ ปี และ หลักสูตร");
	}
	
	else if(year == "")
	{
		alert("กรุณาระบุ ปี");
	}
	
	else if(curriculum == 0)
	{
		alert("กรุณาระบุ หลักสูตร");
	}
	
	else
	{
		document.getElementById("form").submit();
	}*/
	
	document.getElementById("form").submit();
}