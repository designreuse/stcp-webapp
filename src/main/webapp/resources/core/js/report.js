//$(document).ready(function(){
//
//
//
//});
$("#searchReportBtn").click(function(){
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
});
//$(this).children("td:first-child").html()
$(document).on("click","#reportCenterTable > tbody > tr",function(req){
    var reportId = $(this).find("[type='hidden']").val();

    $.ajax({
        url:"reportCenterGenerator",
        method:"POST",
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        data:JSON.stringify({"reportId":reportId})
    }).done(function(data){
//        console.log(data);
        if(data.errorMsg) {
            $("#reportModalTitle").text("ข้อความแจ้งเตือน");
            $("#reportModalBody").text(data.errorMsg);
            $('.bs-example-modal-sm').modal('show');
        } else {
            window.location="reportCenterGenerator/pdf?reportId="+reportId;
        }
    }).fail(function(jqXHR, textStatus, errorThrown){
        console.log(errorThrown);
    });
});