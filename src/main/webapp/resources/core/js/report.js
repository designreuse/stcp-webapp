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
        console.log(data);
    }).fail(function(jqXHR, textStatus, errorThrown){
        console.log(errorThrown);
    });
});