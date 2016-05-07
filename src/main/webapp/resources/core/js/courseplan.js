/**
 * for CoursePlan Module
 * 
 */

var currentSemester = "";
var touchtime = 0;

$(document).ready(function() {

    $("body").popover({
        trigger: "hover",
        container: 'body',
        placement: 'auto bottom',
        delay: {"show": 800, "hide": 200},
        template: '<div class="popover" role="tooltip" style="width:100%;"><div class="arrow"></div><h3 class="popover-title optionEllipsis"></h3><div class="popover-content"></div></div>',
        selector: ".hasPop"
    });

	/*
	 * $('.hasPop').popover( { trigger : "hover", container : 'body', placement :
	 * 'right auto', delay : { "show" : 800, "hide" : 100 }, template : '<div
	 * class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title" style="white-space: nowrap;"></h3><div
	 * class="popover-content"></div></div>', title : "Dismissible popover",
	 * content : "And here's some amazing content. It's very engaging. Right?"
	 * });
	 */

    $('#selectSemesterYear').change(function () {

        var year = $('#selectSemesterYear').val();

        $("#tabSemester > li").addClass('hidden');
        $("#tabSemester > li[tag='" + year + "']").removeClass('hidden');

        $("#tabSemester > li[tag='" + year + "'] > a:first").tab('show');

    });

    $("body").on('click', "#tabSemester a", function (e) {

        e.preventDefault();
        $(this).tab('show');

    });

    $("body").on('shown.bs.tab', "#tabSemester a", function (event) {

        currentSemester = $(event.target).attr("tag"); // active tab

        calculateCredits(currentSemester);
        calculateAllCredits();

    });

    $('#subjectListID option').on('click', doubleClickSubject);

    $("ul#navi_containTab > li").click(function (event) {

        var tabIndex = $(this).index();
        //term = tabIndex.toString();

        $("ul#detail_containTab > li:visible").hide();
        $("ul#detail_containTab > li").eq(tabIndex).show();

    });

    $('#btnSearchCourse').on('click', function (e) {

        e.preventDefault();
        var txtSearch = $('#tbxSearchCourse').val();

        //call Ajax to search course information.
        //and Bind course results to select option elements.

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/stcp/coursePlanner/searchSubject",
            dataType: "json",
            data: {textsearch: txtSearch},
            success: function (data) {

                if (data != null) {

                    $('#subjectListID').empty();

                    data.forEach(function (subject) {

                        var optElement = $('<option></option>');
                        optElement.attr('id', subject.id);
                        optElement.attr('value', subject.id);
                        optElement.attr('title', subject.subjectCode + ' ' + subject.nameThai);

                        optElement.addClass('hasPop');

                        optElement.data('subjectcode', subject.nameThai);
                        optElement.data('credit', subject.credit);
                        optElement.data('content', subject.detailThai);

                        if (subject.status == 2) {
                            optElement.attr('isdisabled', 'true');
                            optElement.addClass('optionDisabled');
                        }

                        optElement.on('click', doubleClickSubject);

                        optElement.html(subject.subjectCode + '&nbsp;' + subject.nameThai);

                        $('#subjectListID').append(optElement);

                    });
                }
            },
            error: function (xml, status, errMsg) {
                swal({
                    title: "Course Planner",
                    text: "Can't search subject.",
                    type: "error",
                    showCancelButton: false
                });
            }
        });
    });

    $("#btnSave").click(function () {
        swal({
            title: "Course Planner",
            text: "Are you want to save this plan?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: 'btn btn-info',
            cancelButtonClass: "btn btn-default",
            confirmButtonText: "Yes",
            cancelButtonText: "No",
            closeOnConfirm: false,
            showLoaderOnConfirm: true

        }, function (isConfirm) {

            if (isConfirm) {

                var semesterList = buildPlanJson();

                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/stcp/coursePlanner/saveplan",
                    dataType: "json",
                    contentType: "application/json; charset=UTF-8",
                    data: JSON.stringify(semesterList),
                    success: function (data) {

                        var msg = 'can\'t save plans';
                        var msgType = 'error';

                        if (data != null) {

                            if (data.IsError) {
                                msg = data.ErrorDescription;
                            } else {
                                msg = "This plan has been saved";
                                msgType = 'success';
                            }

                        }

                        swal({
                                title: "Course Planner",
                                text: msg,
                                type: msgType,
                                showCancelButton: false
                            },
                            function () {
                                window.location.reload(true);
                            });

                    },
                    error: function (xml, status, errMsg) {
                        swal({
                            title: 'Course Planner',
                            text: errMsg != null || errMsg != "" ? errMsg : 'can\'t save plans',
                            type: 'error',
                            showCancelButton: false
                        });
                    },
                    done: function () {
                        swal({
                            title: "Course Planner",
                            text: 'done',
                            type: 'success',
                            showCancelButton: false
                        });
                    }
                });
            }
        });
    });

    $("#btnReset").click(function () {
        swal({
            title: "Course Planner",
            text: "Are you want to reset this plan?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: 'btn btn-danger',
            cancelButtonClass: "btn btn-default",
            confirmButtonText: "Reset",
            cancelButtonText: "No",
            closeOnConfirm: false
        }, function (isConfirm) {
            if (isConfirm) {
                //window.location.href = window.location.;
                window.location.reload(true);
            }
        });
    });

    $("#btnAddYear").on("click", function () {

        var currentYear = (new Date()).getFullYear();

        swal({
            title: "Please input semester's year",
            inputPlaceholder: "ex. " + (currentYear + 543) + ", " + (currentYear + 544) + " or " + (currentYear + 545),
            type: "input",
            confirmButtonClass: "btn btn-success",
            confirmButtonText: "Add",
            cancelButtonClass: "btn btn-default",
            showCancelButton: true,
            closeOnConfirm: false
        }, function (inputValue) {

            if (inputValue != false && inputValue != "") {

                var year = parseInt(inputValue);
                year = year - 543;

                if (year < (currentYear - 1) || year > 2100) {
                    swal.showInputError("Invalid year!");
                    return false;
                }

                var isFound = false;
                var sizeElements = $("#selectSemesterYear option").length;

                if (sizeElements > 0) {

                    $("#selectSemesterYear option").each(function (index) {

                        if (year == parseInt($(this).val())) {

                            isFound = true;

                        } else if (year < parseInt($(this).val())) {

                            $("<option value=\"" + year + "\">ปีการศึกษา&nbsp;" + (year + 543) + "</option>").before($(this));

                        } else if (index == sizeElements - 1) {

                            $("#selectSemesterYear").append($("<option value=\"" + year + "\">ปีการศึกษา&nbsp;" + (year + 543) + "</option>"));

                        }

                    });

                } else {

                    $("#selectSemesterYear").append($("<option value=\"" + year + "\">ปีการศึกษา&nbsp;" + (year + 543) + "</option>"));

                }

                if (!isFound) {
                    $("#tabSemester").append(
                        $("<li class=\"text-center\" tag=\"" + year + "\">" +
                            "<a href=\"#" + year + "1\" role=\"tab\" tag=\"" + year + "1\"><strong>&nbsp;ภาคการเรียนที่ 1&nbsp;</strong></a>" +
                            "</li>" +
                            "<li class=\"text-center\" tag=\"" + year + "\">" +
                            "<a href=\"#" + year + "2\" role=\"tab\" tag=\"" + year + "2\"><strong>&nbsp;ภาคการเรียนที่ 2&nbsp;</strong></a>" +
                            "</li>")
                    );

                    $("#courseplanlist").append(
                        $("<div role=\"tabpanel\" class=\"tab-pane fade\" id=\"" + year + "1\" data-semesteryear=\"" + year + "\" data-semesterterm=\"1\">" +
                            "<table class=\"table table-bordered table-hover\" style=\"max-height: 284px !important;\">" +
                            "<thead class=\"thead-default\">" +
                            "<tr><th class=\"col-xs-8\">Subject</th><th class=\"col-xs-2\">Credit</th><th class=\"col-xs-2\">&nbsp;</th></tr></thead>" +
                            "<tbody></tbody></table></div>" +
                            "<div role=\"tabpanel\" class=\"tab-pane fade\" id=\"" + year + "2\" data-semesteryear=\"" + year + "\" data-semesterterm=\"2\">" +
                            "<table class=\"table table-bordered table-hover\">" +
                            "<thead class=\"thead-default\"><tr><th class=\"col-xs-8\">Subject</th><th class=\"col-xs-2\">Credit</th><th class=\"col-xs-2\">&nbsp;</th></tr></thead>" +
                            "<tbody></tbody></table></div>")
                    );
                }

                $('#selectSemesterYear').val(year);
                $('#selectSemesterYear').trigger("change");

            }

            swal.close();

        });

    });
	
	onLoadPageInit();

});

function onLoadPageInit() {

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

        $('div#' + semester + ' tbody tr').each(function (index) {

            totalCredit += parseInt($(this).children('td').eq(1).text());

        });

        $('#totalCredits').html(totalCredit);

    }
    catch (e) {

        totalCredit = -1;
        $('#totalCredits').html('credits can not calculate');

    }

    return totalCredit;

}

function calculateAllCredits() {

    var totalCredits = 0;

    try {

        $('div[role=tabpanel]').each(function (i) {

            $(this).find('tbody tr').each(function (j) {

                totalCredits += parseInt($(this).children('td').eq(1).text());

            });

        });

        $('#totalAllCredits').html(totalCredits);

    }
    catch (e) {

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
        trNew.data('semesterid', '0');
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

function doubleClickSubject() {

    if (touchtime == 0) {
        //set first click
        touchtime = new Date().getTime();
    } else {
        //compare first click to this click and see if they occurred within double click threshold
        if (((new Date().getTime()) - touchtime) < 800) {

            //double click occurred
            touchtime = 0;

            if ($(this).attr("isdisabled") == "true") {

                swal({
                    title: "Course Planner",
                    text: "This course has been selected.",
                    type: "warning"
                });

                return;
            }

            if (calculateCredits(currentSemester) <= 6) {

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
                    title: "Course Planner",
                    text: "your credits exceeded.",
                    type: "warning"
                });
            }

        } else {
            //not a double click so set as a new first click
            touchtime = new Date().getTime();
        }
    }
}

function buildPlanJson() {

    var semesterList = [];

    try {

        $('div[role=tabpanel]').each(function (i) {

            var year = $(this).data('semesteryear');
            var term = $(this).data('semesterterm');

            $(this).find('tbody tr').each(function (j) {

                var semesterObj = {
                    semesterId: $(this).data('semesterid'),
                    semesterYear: year,
                    semesterTerm: term,
                    subjectId: $(this).data('id')
                };
                semesterList.push(semesterObj);

            });
        });

    }
    catch (e) {
        semesterList = [];
    }

    return semesterList;

}

/*swal({
	title : "An input!",
	text : "Write something interesting:",
	type : "input",
	showCancelButton : true,
	closeOnConfirm : false,
	animation : "slide-from-top",
	inputPlaceholder : "Write something"
}, function(inputValue) {
	if (inputValue === false)
		return false;
	if (inputValue === "") {
		swal.showInputError("You need to write something!");
		return false
	}
	swal("Nice!", "You wrote: " + inputValue, "success");
});
swal({
      title: "Are you sure you want to delete your account?", 
      text: "If you are sure, type in your password:", 
      type: "input",
      inputType: "password",
      showCancelButton: true,
      closeOnConfirm: false
    }, function(typedPassword) {
      console.log(typedPassword);
    });
*/