<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Student Course Planer</title>
</head>

<body>
<!--<nav class="navbar navbar-inverse navbar-fixed-top">-->
<!--<div class="container">-->
<!--<div class="navbar-header">-->
<!--<a class="navbar-brand" href="#">Student Course Planer</a>-->
<!--</div>-->
<!--</div>-->
<!--</nav>-->
<!--<hr>-->
<div class="panel panel-default">
    <div class="panel-body">

        <div class="panel panel-default">

            <div class="panel-heading">
                Report Parameters
            </div>

            <!--<div class="input-group" style="width:100%">-->
                    <!--<span class="input-group-addon" style="width:110px;padding:0px;border:none;">-->
                        <!--<select class="form-control" disabled>-->
                            <!--<option>${idOption.studentId}</option>-->
                            <!--<option>${idOption.staffId}</option>-->
                        <!--</select>-->
                    <!--</span>-->
                <!--<input type="text" class="form-control" placeholder="รหัสประจำตัว" disabled value="12345678"/>-->
            <!--</div>-->

            <div class="input-group">
                <span class="input-group-addon" id="yearLabel">ปีการศึกษา</span>
                <input type="number" class="form-control" min="0" />
                <span class="input-group-addon" id="semesterLabel">ภาคเรียนที่</span>
                <input type="number" class="form-control" min="0" />
            </div>


            <div class="input-group">
                <span class="input-group-addon" id="curriculumLabel">หลักสูตร</span>
                <select class="form-control">
                    <option value="bis">ระบบสารสนเทศทางธุรกิจ (Business Information System)</option>
                    <option value="cs">วิทยาการคอมพิวเตอร์ (Computer Science)</option>
                    <option value="it">เทคโนโลยีสารสนเทศ (Information Technology)</option>
                    <option value="sw">วิศวกรรมซอฟต์แวร์ (Software Engineering)</option>
                </select>
            </div>


            <!--<div class="input-group">-->
            <!--<span class="input-group-addon" id="yearLabel">ชั้นปีที่</span>-->
            <!--<input type="number" class="form-control" placeholder="ระบุชั้นปีระหว่าง 1-8" min="1" max="8" />-->
            <!--</div>-->
            <!--<div class="input-group">-->
            <!--<span class="input-group-addon">ชื่อรายงาน</span>-->
            <!--<input type="text" class="form-control" placeholder="ชื่อรายงาน" />-->
            <!--</div>-->

            <!--<div class="input-group">-->
            <!--<span class="input-group-addon">ประเภทรายงาน</span>-->
            <!--<select class="form-control">-->
            <!--<option>All</option>-->
            <!--<option>Normal</option>-->
            <!--<option>Statistics</option>-->
            <!--</select>-->
            <!--</div>-->

            <a href="reportModuleGenerator">click export</a>


            <!--<div class="panel-footer text-center">-->
                <!--<p>-->
                    <!--<button id="searchBtn" type="button" class="btn btn-primary btn-sm">-->
                        <!--<span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search-->
                    <!--</button>-->
                    <!--<button id="cancelBtn" type="button" class="btn btn-default btn-sm">-->
                        <!--<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Clear-->
                    <!--</button>-->
                <!--</p>-->
            <!--</div>-->
        </div>

        <div class="input-group">
            <input id="reportFilterText" type="text" class="form-control" placeholder="ค้นหารายงาน...">
            <span class="input-group-btn">
                <button id="searchReportBtn" class="btn btn-default glyphicon glyphicon-search" type="button"></button>
                <!--<span class="glyphicon glyphicon-search" aria-hidden="true"></span>-->
            </span>
        </div><!-- /input-group -->

        <!-- Table -->
        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Report Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="master" varStatus="status" items="${records}">
                <tr onclick="window.location='reportCenterGenerator?reportId=${master.reportId}'">
                    <td>
                        <c:out value="${status.count}"/>
                    </td>
                    <td>
                        <c:out value="${master.reportName}"/> <span class="badge">4</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>
</div>

<footer>
    <p>Report & Statistics Module 2016</p>
</footer>

<script type="text/javascript" src="${root}/resources/core/js/report.js" defer></script>
</body>


</html>