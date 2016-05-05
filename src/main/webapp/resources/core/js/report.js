//$(document).ready(function(){
//
//
//
//});
$("#searchReportBtn").click(searchReport);

$("#reportFilterText").keyup(function(e) {
    if($(this).val().length >= 2 || e.which == 13 || $(this).val().length==0) {
        searchReport();
    }
});

function searchReport() {
    var reportFilterText = $("#reportFilterText").val();
    reportFilterText = reportFilterText == "" || reportFilterText == undefined ? "" : $("#reportFilterText").val();

    $.ajax({
        url:"searchReport",
        method:"POST",
        dataType: "json",
        data:{"filterText": reportFilterText}
    }).done(function(data){
        $("#reportCenterTable > tbody").empty();
        $.each(data, function(idx, obj) {
        	var reportList = "<tr>"
                                +"<td>"+ (idx+1) +"</td>"
                                +"<td>"+obj.reportName+"<input type='hidden' value='"+obj.reportId+"'/></td>"
        	                 "</tr>";
        	$("#reportCenterTable > tbody").append(reportList);
        });
    }).fail(function(jqXHR, textStatus, errorThrown){
        console.log(errorThrown);
    });
}


//$(this).children("td:first-child").html()
$(document).on("click","#reportCenterTable > tbody > tr",function(req){
    var reportId = $(this).find("[type='hidden']").val();
    var reportYear = $("#reportYearOption").val();
    var reportNameOption = $("#reportNameOption").val();

    $("#loadingModal").modal("show");
    setTimeout(function(){
        $.ajax({
            url:"reportCenterGenerator",
            method:"POST",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            data:JSON.stringify({"reportId":reportId,"curriculumYear":reportYear,"curriculumName":reportNameOption})
        }).done(function(data){
            var curriculumId = data.curriculumId;
            $("#loadingModal").modal("hide");
    //        console.log(data);
            if(data.errorMsg) {
                swal({
                    title : "ข้อความแจ้งเตือน",
                    text : data.errorMsg,
                    type : "error",
                    showCancelButton: false
                });

            } else {
                if(curriculumId == null) curriculumId = -1;
                window.location="reportCenterGenerator/pdf?reportId="+reportId+"&curriculumId="+curriculumId;
            }
        }).fail(function(jqXHR, textStatus, errorThrown){
            $("#loadingModal").modal("hide");
            console.log(errorThrown);
        });

    },1000);
});