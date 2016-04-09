/**
 * for CoursePlan Module
 * 
 */

var currentSemester = "";

$(document).ready(function() {
	
	 $("body").popover( {
		 trigger : "hover",
		 container : 'body',
		 placement : 'auto bottom',
		 delay : { "show" : 800, "hide" : 200 },
		 template : '<div class="popover" role="tooltip" style="width:100%;"><div class="arrow"></div><h3 class="popover-title optionEllipsis"></h3><div class="popover-content"></div></div>',
		 selector : ".hasPop"
	 });

	/*
	 * $('.hasPop').popover( { trigger : "hover", container : 'body', placement :
	 * 'right auto', delay : { "show" : 800, "hide" : 100 }, template : '<div
	 * class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title" style="white-space: nowrap;"></h3><div
	 * class="popover-content"></div></div>', title : "Dismissible popover",
	 * content : "And here's some amazing content. It's very engaging. Right?"
	 * });
	 */
	 
	$('#selectSemesterYear').change(function() {

		var year = $('#selectSemesterYear').val();

		$("#tabSemester > li").addClass('hidden');
		$("#tabSemester > li[tag='" + year + "']").removeClass('hidden');

		$("#tabSemester > li[tag='" + year + "'] > a:first").tab('show');

	});

	$("#tabSemester a").click(function(e) {

		e.preventDefault();
		$(this).tab('show');

	});

	$('#tabSemester a').on('shown.bs.tab', function(event) {

		currentSemester = $(event.target).attr("tag"); // active tab

		calculateCredits(currentSemester);
		calculateAllCredits();

	});

	var touchtime = 0;
	
	$('#subjectListID option').on('click', function(){
		
		if(touchtime == 0) {
	        //set first click
	        touchtime = new Date().getTime();
	    } else {
	        //compare first click to this click and see if they occurred within double click threshold
	        if(((new Date().getTime())-touchtime) < 800) {
	           
	        	//double click occurred
	            touchtime = 0;
	            
	        	if($(this).attr("isdisabled") == "true"){
	    			
	    			swal({
	    				title : "Course Planner",
	    				text : "This course has been selected.",
	    				type : "warning"
	    			});
	    			
	    			return;
	    			
	    		}
	    	
	    		if(calculateCredits(currentSemester) <= 6) {
	    			
	    			$(this).attr("isdisabled", "true");
	    			$(this).addClass('optionDisabled');
	    	
	    			var dataSubject = new Object();
	    			dataSubject.id = $(this).attr('id');
	    			dataSubject.code = $(this).data('subjectcode');
	    			dataSubject.credit = $(this).data('credit');
	    			dataSubject.subjectname = $(this).html();
	    	
	    			createAddSubject($('div#' + currentSemester + ' tbody'), dataSubject);
	    	
	    			calculateCredits(currentSemester);
	    			calculateAllCredits();
	    			
	    			$('.hasPop').popover('hide');
	    		}
	    		else {
	    			
	    			swal({
	    				title : "Course Planner",
	    				text : "your credits exceeded.",
	    				type : "warning"
	    			});
	    			
	    		}
	    		
	        } else {
	            //not a double click so set as a new first click
	            touchtime = new Date().getTime();
	        }
	    } 
		
	});
	
	/*$('#subjectListID option').dblclick(function() {

		if($(this).attr("isdisabled") == "true"){
			
			swal({
				title : "Course Planner",
				text : "This course has been selected.",
				type : "warning"
			});
			
			return;
			
		}
	
		if(calculateCredits(currentSemester) <= 6) {
			
			$(this).attr("isdisabled", "true");
			$(this).addClass('optionDisabled');
	
			var dataSubject = new Object();
			dataSubject.id = $(this).attr('id');
			dataSubject.code = $(this).data('subjectcode');
			dataSubject.subjectname = $(this).html();
	
			createAddSubject($('div#' + currentSemester + ' tbody'), dataSubject);
	
			calculateCredits(currentSemester);
			calculateAllCredits();
			
			$('.hasPop').popover('hide');
		}
		else {
			
			swal({
				title : "Course Planner",
				text : "your credits exceeded.",
				type : "warning"
			});
			
		}
		
	});*/

	$("ul#navi_containTab > li").click(function(event) {

		var tabIndex = $(this).index();
		//term = tabIndex.toString();

		$("ul#detail_containTab > li:visible").hide();
		$("ul#detail_containTab > li").eq(tabIndex).show();

	});
	
	$('#btnSearchCourse').on('click', function(e){
		
		e.preventDefault();
		var txtSearch = $('#tbxSearchCourse').val();
		
		//call Ajax to search course information.
		//and Bind course results to select option elements.
		
	});

	$("#btnSave").click(function() {
		swal({
			title : "Course Planner",
			text : "Are you want to save this plan?",
			type : "warning",
			showCancelButton : true,
			confirmButtonClass : 'btn btn-info',
			confirmButtonText : "Yes",
			cancelButtonText : "No",
			closeOnConfirm : false,
			showLoaderOnConfirm : true
			
		}, function(isConfirm) {
			
			if (isConfirm) {
				
				var semesterList = buildPlanJson();
				
				$.ajax({
                    type: "POST",
                    url: "http://localhost:8080/stcp/coursePlanner/saveplan",
                    dataType: "json",
                    contentType: "application/json; charset=UTF-8",
                    data: semesterList,
                    success: function (xml) {
                        swal('success');
                    },
                    error: function (xml, status, errMsg) {
                    	swal(errMsg);
                    },
                    done: function() {
                    	swal('done');
                    }
                });
				
				setTimeout(function() {
					swal({
						title : "Course Planner",
						text : "This plan has been saved",
						type : "success",
						confirmButtonClass : 'btn btn-success',
						confirmButtonText : 'OK'
					});
				}, 2000);
				
			}
		});
	});

	$("#btnReset").click(function() {
		swal({
			title : "Course Planner",
			text : "Are you want to reset this plan?",
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
	
	onLoadPageInit();

});

function onLoadPageInit(){
	
	//Bind delete handler on Deleted Button
	$('.courseBtnDelete').on('click', removeSubject);	
	
	//calculate Total credits.
	var year = $('#selectSemesterYear').val();	
	currentSemester = $("#tabSemester > li[tag='" + year + "'] > a:first").attr("tag"); // active tab
	calculateCredits(currentSemester);
	calculateAllCredits();
	
	//hide tab which is not current display.
	var year = $('#selectSemesterYear').val();
	$("#tabSemester > li").addClass('hidden');
	$("#tabSemester > li[tag='" + year + "']").removeClass('hidden');
	$("#tabSemester > li[tag='" + year + "'] > a:first").tab('show');
	
}


function calculateCredits(semester) {

	var totalCredit = 0;

	try {
		
		$('div#' + semester + ' tbody tr').each(function(index) {

			totalCredit += parseInt($(this).children('td').eq(1).text());

		});

		$('#totalCredits').html(totalCredit);
		
	}
	catch(e) {
		
		totalCredit = -1;
		$('#totalCredits').html('credits can not calculate');
		
	}
	
	return totalCredit;
	
}

function calculateAllCredits(){
	
	var totalCredits = 0;
	
	try{

		$('div[role=tabpanel]').each(function(i){
			
			$(this).find('tbody tr').each(function(j){
				
				totalCredits += parseInt($(this).children('td').eq(1).text());
				
			});
			
		});
		
		$('#totalAllCredits').html(totalCredits);
		
	}
	catch(e) {
		
		totalCredits = -1;
		$('#totalAllCredits').html('credits can not calculate');
		
	}
	
	return totalCredits;
	
}

function createAddSubject(tableTBody, dataObj) {

	if (tableTBody != null) {

		var btnDelete = $('<button class="courseBtnDelete"><span class="fa fa-times">&nbsp;</span></button>');
		btnDelete.addClass('btn btn-danger btn-sm');
		btnDelete.on('click', removeSubject);

		var trNew = $('<tr><td class="text-left">' + dataObj.subjectname
				+ '</td><td>' + dataObj.credit + '</td><td></td></tr>');

		trNew.data(dataObj);
		trNew.children('td:last').append(btnDelete);

		tableTBody.append(trNew);

	}
}

function removeSubject() {

	if ($(this) != null) {

		var trSubj = $(this).parents('tr');

		$('#subjectListID').children('[id=' + trSubj.data('id') + ']')
				.removeClass('optionDisabled').removeAttr('isdisabled');
		$(trSubj).remove();
		
		calculateCredits(currentSemester);
		calculateAllCredits();

	}
}

function buildPlanJson(){
	
	
	var semesterList = [];
	
	try {

		$('div[role=tabpanel]').each(function(i){
			
			var courseList = [];
			
			$(this).find('tbody tr').each(function(j){
				
				var courseObj = { courseid: $(this).data('id') };
				courseList.push(courseObj);
				
			});
			
			var semesterObj = { semesterid: $(this).attr('id'), courselist: courseList };
			semesterList.push(semesterObj);
			
		});
		
	}
	catch (e) {
		
		semesterList = [];
		
	}
	
	return semesterList;	
	
}

