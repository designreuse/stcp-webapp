<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
    <title></title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <style type="text/css">
        /*body{  
    margin:0;  
    padding:0;    
    font-family:Tahoma, Geneva, sans-serif;  
    font-size:12px;  
}*/
        div#i_containTab {
            position: relative;
            display: block;
            width: 540px; /* กำหนดความกว้างทั้งหมด   */
            border: 1px solid #CCC;
        }

        ul#navi_containTab {
            list-style: none;
            padding: 0;
            margin: 0;
            width: 100%;
            background-color: #FCF;
        }

            ul#navi_containTab li {
                display: block;
                float: left;
                height: 30px;
                width: 100px; /* กำหนดความกว้างทั้งหมด  หารด้วยจำนวนเมนูแท็บ  */
                border: 0px solid #CCC;
                line-height: 25px;
                cursor: pointer;
                text-align: center;
                /*border:1px solid black;*/
            }
        /* class รูปแบบของแต่ละเมนู */
        .tabNavi1 {
            background-color: #848484;
            color:#ffffff;
        }

        .tabNavi2 {
            background-color: #A4A4A4;
            color:#ffffff;
        }



        ul#detail_containTab {
            list-style: none;
            padding: 0;
            margin: 0;
            width: 100%;
        }

            ul#detail_containTab li {
                float: left;
                width: 100%;
                height: 250px;
            }
        /* class รูปแบบของแต่ละเนื้อหา */
        .detailContent1 {
            background-color: white;
            border: 1px solid black;
            display: block;
        }

        .detailContent2 {
            background-color: white;
            border: 1px solid black;
            display: none;
        }
        
        .table-subject{
        	border: 1px solid black;
        	width:100%;
        }
        
        .td-subject{
        	border: 1px solid black;
        	width:40%;
        }
        
        .td-credit{
        	border: 1px solid black;
        	width:30%;
        	text-align:center;
        }
        
        .td-delete{
        	border: 1px solid black;
        	width:30%;
        }
    </style>


</head>
<body>
    
   <div style="min-height:400px">
	    <div style="float: left; width: 25%; margin-left: 10px">
	    <br>
	        <input name="txt1" type="text"><input name="btnSubmit1" type="submit" value="Search">
	        <br>
	        <select id="subjectListID" name="sometext" size="5" style="width: 210px;height:350px" ondblclick="dblSubject()">
	            <option id="SWE601">SWE601</option>
	            <option id="SWE602">SWE602</option>
	            <option id="SWE603">SWE603</option>
	            <option id="SWE604">SWE604</option>
	            <option id="SWE605">SWE605</option>
	        </select>
	    </div>
	    <div style="float: left; width: 50%">
	    <br>
	        <select style="width: 100px">
	            <option value="1">ปี1</option>
	            <option value="2">ปี2</option>
	            <option value="3">ปี3</option>
	            <option value="4">ปี4</option>
	        </select>
	        <button onclick="reset()">Reset</button>
	         <button onclick="save()">Save</button>
	        <br><br>

	        <div>
	            <ul id="navi_containTab" onclick="clickTab()">
	                <li class="tabNavi1">เทอม 1</li>
	                <li class="tabNavi2">เทอม 2</li>
	
	            </ul>
	            <ul id="detail_containTab">
	                <li class="detailContent1">
	                    <div id="tab1" style="height:100%"> 
	                    	<div id="subjectPlan1" style="height:80%">
	                    		<table id="tableSubject1" class="table-subject">
									  <tr>
									    <td class="td-subject" style="background-color:#F2F2F2;text-align:center;"><b>Subject</b></td>
									    <td class="td-credit" style="background-color:#F2F2F2"><b>Credit</b></td>	
									    <td class="td-delete" style="background-color:#F2F2F2"></td>									  
									  </tr>
								</table>
	                    	</div>
	                    	<div id="detailPlan1" style="height:20%;padding-left:10px">
	                    		<span>Total Credits : </span>
	                    		<span id="totalCredits1"> 0 </span>
	                    		<br>
	                    		<span>All Years Credits : </span>
	                    		<span> 36 </span>
	                    	</div>
	                    </div>
	                </li>
	                <li class="detailContent2">
	                    <div id="tab2" style="height:100%"> 
	                    	<div id="subjectPlan2" style="height:80%">
	                    		<table id="tableSubject2" class="table-subject">
									  <tr>
									    <td class="td-subject" style="background-color:#F2F2F2;text-align:center;"><b>Subject</b></td>
									    <td class="td-credit" style="background-color:#F2F2F2"><b>Credit</b></td>	
									    <td class="td-delete" style="background-color:#F2F2F2"></td>									  
									  </tr>
								</table>
	                    	</div>
	                    	<div id="detailPlan2" style="height:20%;padding-left:10px">
	                    		<span>Total Credits : </span>
	                    		<span id="totalCredits2"> 0 </span>
	                    		<br>
	                    		<span>All Years Credits : </span>
	                    		<span> 36 </span>
	                    	</div>
	                    </div>
	                </li>
	            </ul>
	        </div>
	
			<br>
			<span>
				Warning!!
				<br>
				-Normal status allows 9 credits per semester
			</span>
			
	
	    </div>
   </div>

<script>

        term =  "0", //เทอม1
        
        window.onload = function () { 
			console.log("onload");
        }

        function reset() {
            //subjectPlan1.innerHTML = "";
            //subjectPlan2.innerHTML = "";
        }
        
        function save() {
            alert("save");
        }

        function clickTab() {
            var listt = document.getElementById('navi_containTab');
            //alert(listt.childNodes.toString);
        }

        function dblSubject() {
            var subject = subjectListID.value;       
            document.getElementById(subject).disabled = true;
    
            insertSubjectToTable(subject,"3");
        }
        
        function insertSubjectToTable(subject,credit) {
        	var table = null;
        	if (term == 0) {
        		table = document.getElementById("tableSubject1");
  
           	    var oldCredits = parseInt(totalCredits1.textContent);
                var credits = oldCredits + parseInt(credit);
                totalCredits1.innerHTML= credits;
            } else {
            	table = document.getElementById("tableSubject2");

           	    var oldCredits = parseInt(totalCredits2.textContent);
                var credits = oldCredits + parseInt(credit);
                totalCredits2.innerHTML= credits;
            }  	
        	
        	var btnDelete = document.createElement("BUTTON");
            var text = document.createTextNode("delete");
            btnDelete.appendChild(text);
            btnDelete.addEventListener("click", function(){
            	btnDeleteSubject_Click(subject,credit);
            });           
           
            //var table = document.getElementById("tableSubject");
            var rowCount = table.rows.length;           
            var row = table.insertRow(rowCount);          
            
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            
            cell1.innerHTML = subject;
            cell2.innerHTML = credit; //หน่วยกิต
            //cell3.innerHTML = "Delete" //ปุ่มลบ
            cell3.appendChild(btnDelete);

            cell1.className="td-subject";
            cell2.className="td-credit";
            cell3.className="td-delete";                    
        }

        function btnDeleteSubject_Click(subject,credit){
        	var table = null;
        	if (term == 0) {
        		table = document.getElementById("tableSubject1");
            } else {
            	table = document.getElementById("tableSubject2");
            }
            //var table = document.getElementById('tableSubject');
            var rowCount = table.rows.length;
            for (var i = 0; i < rowCount; i++) {
                var row = table.rows[i];
                var rowObj = row.cells[0].childNodes[0];
                
                if (rowObj.textContent == subject) { 
                	table.deleteRow(i);
                	document.getElementById(subject).disabled = false;
                    rowCount--;
                    
                    if (term == 0) {               		
                   	    var oldCredits = parseInt(totalCredits1.textContent);
                        var credits = oldCredits - parseInt(credit);
                        totalCredits1.innerHTML= credits;
                    } else {
                    	
                   	    var oldCredits = parseInt(totalCredits2.textContent);
                        var credits = oldCredits - parseInt(credit);
                        totalCredits2.innerHTML= credits;
                    } 
                }
            }
            
        }
       

        $("ul#navi_containTab > li").click(function (event) {
            //alert(1);
            var menuIndex = $(this).index();           
            term = menuIndex.toString();

            $("ul#detail_containTab > li:visible").hide();
            $("ul#detail_containTab > li").eq(menuIndex).show();
        });
    </script>
</body>
</html>