<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gym.model.*"%>

<%
	GymVO gymVO = (GymVO) request.getAttribute("gymVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>健身房修改</title>

  <!--external css-->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style-responsive.css" rel="stylesheet">
  
<%@ include file="/back-end/gym_index/back-end-head-source.file" %>
  
  <script src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.0/dist/tw-city-selector.min.js"></script>
  <script src="jquery-tw.js"></script>
  
  <style>
  	.input{
  		 width:215px;
  	}
  </style>
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
        <h3><i class="fa fa-hand-o-right"></i>健身房修改管理</h3>
        <div class="row mt">
          <div class="col-md-12">
			<br>
	<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gym/gym.do" name="form1">
             <table
			class="table table-bordered table-condensed"
			style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
			cellpadding="10" border='1'>
			<thead>
	    	<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
			<td>*</td><td></td>
			</tr>
			<tr>	
				<td>健身房名稱:</td>
				<td><input type="TEXT" name="gym_name" size="45"
					value="<%=(gymVO == null) ? "台南國民健身房" : gymVO.getGym_name()%>" /></td>
			</tr>

			<tr>
				<td>緯度:</td>
				<td><input type="TEXT" name="gym_latitude" size="45"
					value="<%=(gymVO == null) ? "25.078123" : gymVO.getGym_latitude()%>" /></td>

			</tr>
			<tr>
				<td>經度:</td>
				<td><input type="TEXT" name="gym_longitude" size="45"
					value="<%=(gymVO == null) ? "121.575204" : gymVO.getGym_longitude()%>" /></td>

			</tr>
			<tr>
				<td>地址:</td>
				<td><input id="address" name="gym_address" size="45" value="台南市歸仁區" class="twaddress"  /></td>
			</tr>
			
			<tr>
				<td>開館時間:</td>
				<td><input type="TEXT" name="gym_content" size="45"
					value="<%=(gymVO == null) ? "06:00-22:00" : gymVO.getGym_content()%>" /></td>

			</tr>
						
      		<tr>
      			<td></td>
      			<td>
      				<input type="hidden" name="action" value="insert">
      				<button type="submit" class="btn btn-theme" >送出新增</button>
			    		<button class="btn btn-theme04" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/gym/gym.jsp'">取消</button>
			    </td>
      			
      		
                      
               
      </table>
      
      	   
	</FORM>
            </div>
          </div>

        </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->

  </section>
</section> 
  <!--script for this page-->
  
</body>

</html>
