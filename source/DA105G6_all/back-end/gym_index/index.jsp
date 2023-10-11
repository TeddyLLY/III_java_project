<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>WorkOut AnyWhere</title>

  <!--external css-->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style-responsive.css" rel="stylesheet">
  
<%@ include file="/back-end/gym_index/back-end-head-source.file" %>
	
	<style>
			hr{
    border: 0;
    height: 0; /* Firefox... */
    box-shadow: 0 0 10px 1px black;
}
hr{  /* Not really supposed to work, but does */
    content: "\00a0";  /* Prevent margin collapse */
}
	</style>
  
</head>

<body>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <%@ include file="/back-end/gym_index/back-end-navbar.file" %>
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
      <section class="wrapper site-min-height">
        <div class="row mt">
          <div class="col-lg-12">

 			<div class="about_banner_img"><img src="<%=request.getContextPath()%>/back-end/gym_index/img/IMG_9923.PNG" class="img-responsive" alt="" style=' border-radius: 50%; height:500px; width-max:100%; margin-top:35px; margin-left:550px; object-fit: contain'/>
 			<p style="font-size:32px; display: inline-block; margin-right:20px; color: #3f264f;"><b><%=empName%></b></p><p style="font-size:24px; display: inline-block; margin-right:55px; color:#262a4f">管理員您好，歡迎使用後台管理系統</p>
 		
 			</div>
         	
          </div>
        </div>
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->

  </section>

</body>

</html>
