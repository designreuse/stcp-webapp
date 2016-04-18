//$(document).ready(function(){
//
//
//
//});
$("#searchReportBtn").click(function(){
    $.ajax({
        url:"searchReport",
        method:"post",
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        data:JSON.stringify({"reportId":"1"})
    }).done(function(data){
        console.log(data);
    }).fail(function(jqXHR, textStatus, errorThrown ){
        console.log(errMsg);
    });
});