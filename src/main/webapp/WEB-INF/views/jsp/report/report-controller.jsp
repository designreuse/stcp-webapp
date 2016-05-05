<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- SweetAlert (message alert -->
    <script src="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/1.3.2/sweetalert2.min.css">

    <title>Report & Statistics</title>
</head>`

<body>
<div class="panel panel-default">
    <div class="panel-body">

        <div class="panel panel-primary">

            <div class="panel-heading">
                เงื่อนไขรายงาน
            </div>

            <div class="input-group">
                <span class="input-group-addon" id="curriculumLabeld">หลักสูตร</span>
                <!--<div class="input-group-btn">-->
                <!--&lt;!&ndash; Button and dropdown menu &ndash;&gt;-->
                <!--</div>-->
                <!--<input id="reportYear" type="text" class="form-control" aria-label="..." placeholder="ปี">-->

                <select id="reportYearOption" class="form-control">
                    <c:forEach var="optYear" items="${curriculumYearList}">
                        <option value="${optYear.startYear}">${optYear.startYear}</option>
                    </c:forEach>
                </select>

                <select id="reportNameOption" class="form-control">
                    <c:forEach var="optName" items="${curriculumNameList}">
                        <option value="${optName.name}">${optName.name}</option>
                    </c:forEach>
                    <!--<option value="bis">ระบบสารสนเทศทางธุรกิจ (Business Information System)</option>-->
                    <!--<option value="cs">วิทยาการคอมพิวเตอร์ (Computer Science)</option>-->
                    <!--<option value="it">เทคโนโลยีสารสนเทศ (Information Technology)</option>-->
                    <!--<option value="sw">วิศวกรรมซอฟต์แวร์ (Software Engineering)</option>-->
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

            <!--<a href="reportModuleGenerator?reportId=0">click export</a>-->


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


        <div class="panel panel-default">

            <div class="panel-heading">
                <div class="input-group">
                    <input id="reportFilterText" type="text" class="form-control" placeholder="ค้นหารายงาน...">
            <span class="input-group-btn">
                <button id="searchReportBtn" class="btn btn-primary glyphicon glyphicon-search" type="button"></button>
                <!--<span class="glyphicon glyphicon-search" aria-hidden="true"></span>-->
            </span>
                </div><!-- /input-group -->
            </div>


            <!-- Table -->
            <table id="reportCenterTable" class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Report Name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="report" varStatus="status" items="${reportList}">
                    <!--<tr onclick="window.location='reportCenterGenerator?reportId=${report.reportId}'">-->
                    <tr>
                        <td>
                            <c:out value="${status.count}"/>
                        </td>
                        <td>
                            <c:out value="${report.reportName}"/>
                            <input type="hidden" value="${report.reportId}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="panel-footer">
                <span class="label label-warning glyphicon glyphicon-alert"> คลิกเลือกรายการเพื่อออกรายงาน</span>
                <!--<h6></h6>-->
            </div>
        </div>

    </div>
</div>

<!-- Loading Modal -->
<div id="loadingModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
     aria-labelledby="reportModalTitle">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">Loading</h4>
            </div>
            <div class="modal-body">
                <div class="progress">
                    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100"
                         aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                        <span class="sr-only">45% Complete</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${root}/resources/core/js/report.js" defer></script>
</body>


</html>