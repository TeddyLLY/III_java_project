<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report_product.model.*"%>
<%
ReportProductVO reportProductVO = (ReportProductVO) request.getAttribute("ReportProductVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>檢舉商品狀態修改</title>

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
        <h3><i class="fa fa-hand-o-right"></i> 檢舉商品修改管理</h3>
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
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ReportProduct/ReportProductServlet" name="form1" >
              <table
		class="table table-bordered table-condensed"
		style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
		cellpadding="10" border='1'>
		<thead>
     <tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
							<td>商品檢舉編號:<font color=red><b>*</b></font></td>
							<td><%=reportProductVO.getReport_product_no()%></td>
				    </tr>
		            <tr>
							<td>商品編號:<font color=red><b>*</b></font></td>
							<td><%=reportProductVO.getProduct_no()%></td>
				    </tr>
		            <tr>
							<td>會員編號:<font color=red><b>*</b></font></td>
							<td><%=reportProductVO.getMember_no()%></td>
				    </tr>
		            <tr>
							<td>檢舉商品內容:<font color=red><b>*</b></font></td>
							<td><%=reportProductVO.getReport_product_content()%></td>
				    </tr>
		            <tr>
							<td>檢舉商品時間:<font color=red><b>*</b></font></td>
							<td><%=reportProductVO.getReport_product_time()%></td>
				    </tr>
			<tr>
							<td>檢舉狀態:</td>
					<td><select name="report_product_status">
							 <option  size="45" value="0" <%=reportProductVO.getReport_product_status().equals(0)?"selected":""%>>審核中</option>
							 <option  size="45" value="1" <%=reportProductVO.getReport_product_status().equals(1)?"selected":""%>>審核成功</option>
							 <option  size="45" value="2" <%=reportProductVO.getReport_product_status().equals(2)?"selected":""%>>審核未通過</option>
						</select>
					</td>
			</tr>
			
			<tr>
				<td></td>
				<td>
					<input type="hidden" name="action" value="update"> 
	      	   		<input type="hidden" name="report_product_no" value="<%=reportProductVO.getReport_product_no()%>"> 
	      	   		<input type="hidden" name="report_product_status" value="<%=reportProductVO.getReport_product_status()%>"> 
					<button type="submit" class="btn btn-theme" >送出修改</button>
				</td>		
			</tr>
      </table>
    	   
	</FORM>
            </div>
          </div>

        </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->

  </section>
 
  <!--script for this page-->
  
</body>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>
</html>
