//$(document).ready(function(){
//
//
//
//});
$("#searchReportBtn").click(searchReport);

$("#reportFilterText").keyup(function(e) {
    if($(this).val().length >= 2 || e.which == 13) {
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

    $("#loadingModal").modal("show");
    setTimeout(function(){
        $.ajax({
            url:"reportCenterGenerator",
            method:"POST",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            data:JSON.stringify({"reportId":reportId})
        }).done(function(data){
            $("#loadingModal").modal("hide");
    //        console.log(data);
            if(data.errorMsg) {
                $("#reportModalTitle").text("ข้อความแจ้งเตือน");
                $("#reportModalBody").text(data.errorMsg);
                $("#msgModal").modal('show');
            } else {
                window.location="reportCenterGenerator/pdf?reportId="+reportId;
            }
        }).fail(function(jqXHR, textStatus, errorThrown){
            $("#loadingModal").modal("hide");
            console.log(errorThrown);
        });

    },1000);
});