<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>

<%
	EmployeeService employeeSvc = new EmployeeService();
	List<EmployeeVO> list = employeeSvc.getAll();
	pageContext.setAttribute("list", list);

%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>員工管理</title>

  <!--external css-->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style-responsive.css" rel="stylesheet">
  
<%@ include file="/back-end/gym_index/back-end-head-source.file" %>


<style>
		img.employee_photo {
			width:80px;
			height:80px;
		}

		.table-striped>tbody>tr:nth-of-type(odd) {
/*     	background-color: #f9f9f900; */
		}
		.table{
			table-layout: fixed;
			
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
     	<h3><i class="fa fa-hand-o-right"></i>員工列表</h3>
       <h3><button class="btn btn-theme" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/employee/addEmployeeWithAuthority.jsp'">新增員工</button></h3>
        <!-- row -->
       <div class="row mt">
					<div class="col-md-12">

							<section id="unseen">
								<div>
									<table
										class="table table-bordered table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
                            	             
                    <th>員工姓名</th>
                    <th>照片</th>                  
                    <th>職位</th>
                    <th>帳號</th>
                    <th>信箱</th>
                    <th>手機</th>
                    <th>修改</th>
                  </tr>
                </thead>    
                <tbody>	
          
           <c:forEach var="employeeVO" items="${list}">
                
                  <tr>
                    <td>${employeeVO.employee_name}</td>
                    
                    <td><img name="employee_photo" class="employee_photo" src="<%=request.getContextPath()%>/employee/DBGifReader?employee_no=${employeeVO.employee_no}"></td>
                  
                    <td>${employeeVO.employee_title}</td>
                    <td>${employeeVO.employee_account}</td>
                    <td>${employeeVO.employee_mail}</td>
                    <td>${employeeVO.employee_cellphone}</td>
                  
                    <td>
                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do">
	                      <button type="submit" class="btn btn-theme" >修改員工資料</button> 
	                      <input type="hidden" name="employee_no"  value="${employeeVO.employee_no}">
	                      <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
                    </td>
                  </tr>        
           </c:forEach>    
          
                </tbody>           
                
              </table>
          	</div>
</section>
          </div>
          <!-- /col-md-12 -->
        </div>
        <!-- /row -->
      </section>
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->

    <!--footer end-->
  </section>
 
  <!--script for this page-->
  
</body>

</html>
