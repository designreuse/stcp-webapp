<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Student Course Planer</title>

	<spring:url value="/resources/core/css/main.css" var="coreCss" />
	<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${coreCss}" rel="stylesheet" />
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
                    Filter
                </div>

                <div class="input-group">
                    <div class="input-group-btn">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">Action <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li role="presentation"><a href="#">${idOption.studentId}</a></li>
                            <li><a href="#">${idOption.staffId}</a></li>
                        </ul>
                    </div>
                    <input type="text" class="form-control" aria-label="..."/>
                </div>


                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        ReportType
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                        <li><a href="#">Normal</a></li>
                        <li><a href="#">Statistics</a></li>
                    </ul>
                </div>
                <a href="reportModuleGenerator">click export</a>
                <br/>
                <a href="reportCenterGenerator">click row</a>
                <div class="panel-footer text-center">
                    <p>
                        <button type="button" class="btn btn-primary btn-sm">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                        </button>
                        <button type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Clear
                        </button>
                    </p>
                </div>
            </div>

            <!-- Table -->
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Report Name</th>
                    <th>Type</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="master" varStatus="status" items="${records}">
                    <tr>
                        <td>
                            <c:out value="${status.count}"/>
                        </td>
                        <td>
                            <c:out value="${master.reportName}"/>
                        </td>
                        <td>
                            <c:out value="${master.reportType}"/>
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


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <spring:url value="/resources/core/css/main.js" var="coreJs"/>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>

    <script src="${coreJs}"></script>
    <script src="${bootstrapJs}"></script>

</body>

</html>