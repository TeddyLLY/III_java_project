<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

 
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">

 <%@ include file="/back-end/gym_index/back-end-head-source.file" %>

  
</head>

<body>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
   
     <%@ include file="/back-end/gym_index/back-end-navbar.file" %>
   
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
     
     <%@ include file="/back-end/gym_index/back-end-sidebar.file" %>
     
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <h3><i class="fa fa-angle-right"></i> Grid: Stacked Horizontal</h3>
        <div class="row show-grid mt">
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
          <div class="col-md-1">.col-md-1</div>
        </div>
        <div class="row show-grid">
          <div class="col-md-8">.col-md-8</div>
          <div class="col-md-4">.col-md-4</div>
        </div>
        <div class="row show-grid">
          <div class="col-md-4">.col-md-4</div>
          <div class="col-md-4">.col-md-4</div>
          <div class="col-md-4">.col-md-4</div>
        </div>
        <div class="row show-grid">
          <div class="col-md-6">.col-md-6</div>
          <div class="col-md-6">.col-md-6</div>
        </div>
        <h3><i class="fa fa-angle-right"></i> Example: Mobile and Desktop</h3>
        <!-- Stack the columns on mobile by making one full-width and the other half-width -->
        <div class="row show-grid mt">
          <div class="col-xs-12 col-md-8">.col-xs-12 .col-md-8</div>
          <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
        </div>
        <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
        <div class="row show-grid">
          <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
          <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
          <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
        </div>
        <!-- Columns are always 50% wide, on mobile and desktop -->
        <div class="row show-grid">
          <div class="col-xs-6">.col-xs-6</div>
          <div class="col-xs-6">.col-xs-6</div>
        </div>
        <h3><i class="fa fa-angle-right"></i> Example: Mobile, Tablets & Desktop</h3>
        <div class="row show-grid mt">
          <div class="col-xs-12 col-sm-6 col-md-8">.col-xs-12 .col-sm-6 .col-md-8</div>
          <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
        </div>
        <div class="row show-grid">
          <div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
          <div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
          <!-- Optional: clear the XS cols if their content doesn't match in height -->
          <div class="clearfix visible-xs"></div>
          <div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
        </div>
        <h3><i class="fa fa-angle-right"></i> Example: Offsetting Columns</h3>
        <div class="row show-grid mt">
          <div class="col-md-4">.col-md-4</div>
          <div class="col-md-4 col-md-offset-4">.col-md-4 .col-md-offset-4</div>
        </div>
        <div class="row show-grid">
          <div class="col-md-3 col-md-offset-3">.col-md-3 .col-md-offset-3</div>
          <div class="col-md-3 col-md-offset-3">.col-md-3 .col-md-offset-3</div>
        </div>
        <div class="row show-grid">
          <div class="col-md-6 col-md-offset-3">.col-md-6 .col-md-offset-3</div>
        </div>
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
      <div class="text-center">
        <p>
          &copy; Copyrights <strong>Dashio</strong>. All Rights Reserved
        </p>
        <div class="credits">
          More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
        </div>
        <a href="grid.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
      </div>
    </footer>
    <!--footer end-->
  </section>

  <!--script for this page-->

</body>

</html>
