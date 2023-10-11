<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>
<%@ page import="com.employee_function.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="employee_functionSvc" scope="page" class="com.employee_function.model.Employee_functionService" />
<jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
<jsp:useBean id="authoritySvc" scope="page" class="com.authority.model.AuthorityService" />
<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");

	List<String> authlist =(List<String>)request.getAttribute("listAuthority_ByEmployee");
//     List<AuthorityVO> authlist = authoritySvc.getOneAuthority(employeeVO.getEmployee_no());
	pageContext.setAttribute("authlist", authlist);
// 	for(AuthorityVO yy :authlist){
// 		System.out.println(yy.getFunction_no());
// 	}
	

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

  <!--external css-->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/gym_index/lib/bootstrap-datepicker/css/datepicker.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/gym_index/lib/bootstrap-daterangepicker/daterangepicker.css" />
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style-responsive.css" rel="stylesheet">

 <%@ include file="/back-end/gym_index/back-end-head-source.file" %>

  
</head>

<script>
var loadFile = function(event) {
	var output = document.getElementById('output');
	output.src = URL.createObjectURL(event.target.files[0]);
};
</script>
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
        <h3><i class="fa fa-hand-o-right"></i>員工管理</h3>
        <!-- BASIC FORM ELELEMNTS -->
        <div class="row mt">
          <div class="col-lg-12">
<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


              <form class="form-horizontal style-form"  METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do"
		name="form1" enctype="multipart/form-data">       
                <table
					class="table table-bordered table-striped table-condensed"
					style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
					cellpadding="10" border='1'>
					<thead>
					<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
						<td>員工編號:<font color=red><b>*</b></font></td>
						<td><%=employeeVO.getEmployee_no()%></td>
					</tr>			
					<tr>
					<td>員工姓名</td>
	                  <td>
	                    <input type="TEXT" size="45" name="employee_name"
	                     value="<%=employeeVO.getEmployee_name()%>">            
	                  </td>
                  </tr>
                <tr>
                   <td>職位</td>
                   <td>
	                    <input type="text" size="45" name="employee_title"
	                    value="<%=employeeVO.getEmployee_title()%>">
					</td>
				</tr>
                
                <tr>
                	<td>帳號</td>             
                    <td>
                    	<input type="text" size="45" name="employee_account"
                    	value="<%=employeeVO.getEmployee_account()%>">
                	</td>
                </tr> 
                
                <tr> 
                   <td>信箱</td>
                   <td>
	                    <input type="text" size="45" name="employee_mail"
	                    value="<%=employeeVO.getEmployee_mail()%>">
                   </td>
                </tr>
                             
               <tr>
                   <td>手機</td>
                   <td>
	                    <input type="text" size="45" name="employee_cellphone"
	                    value="<%=employeeVO.getEmployee_cellphone()%>">
               	   </td>
               </tr>
               <tr>
				  <td>照片:</td>
					<td>
	                 <div>
	                 	<img id="output" name="employee_photo" src="<%=request.getContextPath()%>/employee/DBGifReader?employee_no=${employeeVO.employee_no}" style="height: 150px; width: 150px;">
	                 </div>
	                  <br>
	                  <label class="btn btn-info">
	                  <input type="file" name="employee_photo"id="upload_img" style="display:none;" onchange="loadFile(event)">
	                  <i class="fa fa-photo"></i> 上傳圖片
	                  </label>       
					</td>
				</tr>   
              
               <tr>    					
  					<td>權限</td>
  					<td>
  					<div class="col-sm-4 text-center" style="width: 600px; margin-left:460px;">		            		            			            	
  					
	                <c:forEach var="employee_functionVO" items="${employee_functionSvc.all}">						
<label class="col-sm-3 control-label">${employee_functionVO.function_name}<input type="checkbox" data-toggle="switch" name="list" value="${employee_functionVO.function_no}" ${authlist.contains(employee_functionVO.function_no)? 'checked':''}/></label>
			         			        			
		         	</c:forEach>		         				         			         			         				         		
	         	
     			</div>               
               </td>
               </tr>      
               <tr>
               		<td></td>
               <td>        
               		<div class="col-sm-6 text-right">
        				<button type="submit" class="btn btn-theme" >送出</button> 
        				<input type="hidden" name="employee_no" value="${employeeVO.employee_no}">  
        				<input type="hidden" name="action" value="update">                           					          
                   	<button class="btn btn-theme04" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/employee/employee.jsp'">取消</button>
                    </div>
               				
		       </td>
		       </tr>
		       
             </table> 
              </form>
            </div>
          </div>


        <!-- /row -->
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
  
  </section>

  
</body>
<!--script for this page-->
  <script src="<%=request.getContextPath()%>/back-end/gym_index/lib/jquery-ui-1.9.2.custom.min.js"></script>
  <!--custom switch-->
  <script src="<%=request.getContextPath()%>/back-end/gym_index/lib/bootstrap-switch.js"></script>
  <!--custom tagsinput-->
  <script src="<%=request.getContextPath()%>/back-end/gym_index/lib/jquery.tagsinput.js"></script>
  <!--custom checkbox & radio-->
  <script type="text/javascript" src="<%=request.getContextPath()%>/back-end/gym_index/lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/back-end/gym_index/lib/bootstrap-daterangepicker/date.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/back-end/gym_index/lib/bootstrap-daterangepicker/daterangepicker.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/back-end/gym_index/lib/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
  <script src="<%=request.getContextPath()%>/back-end/gym_index/lib/form-component.js"></script>



</html>
