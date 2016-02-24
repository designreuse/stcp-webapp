<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><decorator:title></decorator:title></title>

	<!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${root}/resources/core/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${root}/resources/core/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${root}/resources/core/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${root}/resources/core/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${root}/resources/core/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${root}/resources/core/plugins/datepicker/datepicker3.css">

<decorator:head></decorator:head>
</head>
<body class="hold-transition skin-red sidebar-mini">
	<div class="wrapper">

      <header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini">STCP</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg">Student Planner</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
	    	<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
	    		<span class="sr-only">Toggle navigation</span>
	    	</a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle">
                  <span class="hidden-xs"><i class="fa fa-fw fa-user"></i>
<%--                   <security:authentication property="principal.username" />  --%>
                  Sign out</span>
                </a>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
        	<ul class="sidebar-menu">
        		<li class="header"></li>
	             <li class="treeview">
	              <a href="#">
	                <i class="fa fa-edit"></i>
	                <span>Menu</span>
	              </a>
	            </li>
	        </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

	<div class="content-wrapper">
	  <section class="content">
	  	<div class="row">
          <div class="col-md-12">
          	<div class="box box-primary">
          		<div class="box-body">
			      <!-- Content Wrapper. Contains page content -->
			      <decorator:body></decorator:body>
			    </div>
		    </div>
		  </div>
	    </div>
      </section>
    </div>
      
      <footer class="main-footer">
        <div class="pull-right hidden-xs"></div>
        <strong>Student Course Planner Copyright &copy; 2016</strong>
      </footer>
    </div><!-- ./wrapper -->
    
    <!-- jQuery 2.1.4 -->
    <script src="${root}/resources/core/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${root}/resources/core/js/bootstrap.min.js"></script>
    <!-- Slimscroll -->
    <script src="${root}/resources/core/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="${root}/resources/core/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="${root}/resources/core/js/app.min.js"></script>
    <!-- AdminLTE Datepicter -->
    <script src="${root}/resources/core/plugins/datepicker/bootstrap-datepicker.js"></script>
</body>
</html>