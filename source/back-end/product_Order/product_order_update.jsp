<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_order.model.*"%>
<%
ProductOrderVO productOrderVO = (ProductOrderVO) request.getAttribute("ProductOrderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>訂單狀態修改</title>

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
        <h3><i class="fa fa-hand-o-right"></i> 訂單修改管理</h3>
        <br>
        <br>
        <div class="row">
          <div class="col-md-12">
	
	<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ProductOrder/ProductOrderServlet" name="form1" >							
									<table
										class="table table-bordered table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
							<td>商品訂單編號:<font color=red><b>*</b></font></td>
							<td><%=productOrderVO.getOrder_no()%></td>
				    </tr>
		            <tr>
							<td>會員編號:<font color=red><b>*</b></font></td>
							<td><%=productOrderVO.getMember_no()%></td>
				    </tr>
		            <tr>
							<td>收件人:<font color=red><b>*</b></font></td>
							<td><%=productOrderVO.getOrder_recipient()%></td>
				    </tr>
		            <tr>
							<td>配送地址:<font color=red><b>*</b></font></td>
							<td><%=productOrderVO.getOrder_address()%></td>
				    </tr>
		            <tr>
							<td>配送日期:<font color=red><b>*</b></font></td>
							<td><%=productOrderVO.getOrder_date()%></td>
				    </tr>
			<tr>
							<td>商品內容:</td>
					<td><select name="order_status">
							 <option  size="45" value="0" <%=productOrderVO.getOrder_status().equals(0)?"selected":""%>>處理中</option>
							 <option  size="45" value="1" <%=productOrderVO.getOrder_status().equals(1)?"selected":""%>>未出貨</option>
							 <option  size="45" value="2" <%=productOrderVO.getOrder_status().equals(2)?"selected":""%>>配送中</option>
							 <option  size="45" value="3" <%=productOrderVO.getOrder_status().equals(3)?"selected":""%>>已完成</option>
						</select>
					</td>
			</tr>
			
      		<tr>
      			<td></td>
      			<td>
      				<input type="hidden" name="action" value="update"> 
		      	    <input type="hidden" name="order_no" value="<%=productOrderVO.getOrder_no()%>"> 
		      	    <input type="hidden" name="order_status" value="<%=productOrderVO.getOrder_status()%>"> 
      			    <button type="submit" class="btn btn-theme" >送出修改</button>
      				<button class="btn btn-theme04" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/product_Order/product_order_basic.jsp'">取消</button>
      			</td>    			
      		</tr>     
      </table>

      	   
	</FORM>
            </div>
          </div>
       
        </section>
    <!-- /MAIN CONTENT -->

  </section>
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
