<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gym.model.*"%>

<%
	GymService gymSvc = new GymService();
	List<GymVO> list = gymSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

  <!--external css-->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style-responsive.css" rel="stylesheet">
  
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
     	<h3><i class="fa fa-hand-o-right"></i>健身房列表</h3>
     <h3><button class="btn btn-theme" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/gym/addGym.jsp'">新增健身房</button></h3>
        <div class="row mt">
          <div class="col-md-12">           
              							<div>
									<table
										class="table table-bordered table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
                   
                    <th>健身房名稱</th>
                    <th>地址</th>
                    <th>緯度</th>
                    <th>經度</th>
                    <th>開館時間</th>
                    <th></th>
                  	<th></th>
                  </tr>
                </thead>
                <tbody>
                
           <c:forEach var="gymVO" items="${list}">
                
                  <tr>
                    <td>${gymVO.gym_name}</td>
                    <td>${gymVO.gym_address}</td>
                    <td>${gymVO.gym_latitude}</td>
                    <td>${gymVO.gym_longitude}</td>
                    <td>${gymVO.gym_content}</td>      
                   
                    <td>
                    	  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gym/gym.do">
	                      <button type="submit" class="btn btn-theme" >修改</button> 
	                      <input type="hidden" name="gym_no"  value="${gymVO.gym_no}">
	                      <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
	                </td>	                
	                 <td> 
	                      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gym/gym.do"> 
	                      <button type="submit" class="btn btn-theme04" >刪除</button> 
	                      <input type="hidden" name="gym_no"  value="${gymVO.gym_no}">
			     		  <input type="hidden" name="action" value="delete"></FORM>
                    </td>
                  </tr>
            
           </c:forEach>    
              
                </tbody>
              </table>
            </div>
            <!-- /content-panel -->
          </div>
 
          <!-- /col-md-12 -->
</div>
        <!-- /row -->
      </section>

    </section>


  </section>
 
  <!--script for this page-->
  
</body>

</html>
