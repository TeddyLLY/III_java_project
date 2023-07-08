<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.authority.model.*"%>
<%@ page import="com.employee_function.model.*"%>

<jsp:useBean id="employee_functionSvc" scope="page" class="com.employee_function.model.Employee_functionService" />

		
<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");

%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>新增員工</title>

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

        <h3><i class="fa fa-hand-o-right"></i>新增員工</h3> 
         <div class="row mt">
          <div class="col-lg-12">
          
   
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>       
          
              <form METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do"
		name="form1" enctype="multipart/form-data">       
                <table
					class="table table-bordered table-striped table-condensed"
					style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
					cellpadding="10" border='1'>
					<thead>
					<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">			
						<td>*</td><td></td>
					</tr>
			   
			   <tr>
                  <td>員工姓名</td>
                    <td><input type="TEXT" size="45" name="employee_name" id="employee_name" 
                     value="<%=(employeeVO == null) ? "" : employeeVO.getEmployee_name()%>"> 
                  </td>              
               </tr>
                  
                <tr>              
                   <td>職位</td>
                    <td><input type="text" size="45" name="employee_title"  id="employee_title"
                    	value="<%=(employeeVO == null) ? "" : employeeVO.getEmployee_title()%>">
					</td>
               </tr> 
                <tr>                
                   <td>帳號</td>                
                    <td>
                    	<input type="text" size="45" name="employee_account" id="employee_account"
                   		value="<%=(employeeVO == null) ? "" : employeeVO.getEmployee_account()%>">
               		</td>
                </tr>
                
               <tr>
                  <td>信箱</td>
                  <td>
                    <input type="text" size="45" name="employee_mail" id="employee_mail"
                    value="<%=(employeeVO == null) ? "" : employeeVO.getEmployee_mail()%>">
			 	  </td>
			  </tr>
                             
               <tr>
                   <td>手機</td>         
                    <td>
                   	 	<input type="text" size="45" name="employee_cellphone" id="employee_cellphone"
                    	value="<%=(employeeVO == null) ? "" : employeeVO.getEmployee_cellphone()%>">
			 		</td>
			  </tr>
              <tr>
				  <td>照片:</td>
					<td>
	                 <div>
	                 	<img id="output" name="employee_photo" src="<%=request.getContextPath()%>/employee/DBGifReader?employee_no=${employeeVO.employee_no}" style="height: 150px; width: 150px;">&emsp;&emsp;
	                 </div>
	                  <br>
	                  <label class="btn btn-info">
	                  <input type="file" name="employee_photo"id="upload_img" style="display:none;" onchange="loadFile(event)">
	                  <i class="fa fa-photo"></i> 上傳圖片
	                  </label>       
					</td>
				</tr>   
	               	<tr> 					
	  					<td>權限:<td>
	  						
  							<div class="col-sm-4 text-center" style="width: 600px; margin-left:460px;">		            	
			            	<c:forEach var="employee_functionVO" items="${employee_functionSvc.all}">
			            		<label class="col-sm-3 control-label">${employee_functionVO.function_name}<input type="checkbox" data-toggle="switch" name="list" value="${employee_functionVO.function_no}"></label>
			         		</c:forEach> 	
			        		</div>			        
			        	
			        </tr> 
               
                <tr>
                	<td></td>
        			<td>
        				<button type="submit" class="btn btn-theme" >送出</button>        				
        				<input type="hidden" name="action" value="insertWithAutjority">    
						<button class="btn btn-theme04" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/employee/employee.jsp'">取消</button>&emsp;&emsp;
											<!--         神奇小按鈕				 -->
        				<input type="button" class="magic btn btn-round btn-info" value="神奇小按鈕">           					             
               
              		</td>
               </tr>  						        
     	 
            </table>  
              </form>
            </div>
          </div>
      </section>
      <!-- /wrapper -->
    </section>
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




<script>
		$(".magic").click(function(){
			$("#employee_cellphone").val('0987855214');
			$("#employee_mail").val('gcobc31619@gmail.com');
			$("#employee_account").val('EE005');
			$("#employee_title").val('新進員工');
			$("#employee_name").val('雕弟');
		})
</script>


</html>
