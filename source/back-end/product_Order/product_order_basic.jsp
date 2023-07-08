<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%
	ProductOrderService productOrderSvc = new ProductOrderService();
	List<ProductOrderVO> list = productOrderSvc.getAll();
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
        <div class="row mt">
        <h3><i class="fa fa-hand-o-right"></i> 訂單管理</h3> 
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
             
               <div>
					<table
					class="table table-bordered table-striped table-condensed"
					style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
					cellpadding="10" border='1'>
					<thead>
					
					<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
                    <th>商品訂單編號</th>
					<th>會員編號</th>
					<th>收件人</th>
					<th>配送地址</th>
					<th>配送日期</th>
					<th>商品訂單狀態</th>
                 	<th>修改</th>                 	
                  </tr>
          
					<c:forEach var="ProductOrderVO" items="${list}">

			<tr style="white-space: nowrap;">
				<td>${ProductOrderVO.order_no}</td>
				<td>${ProductOrderVO.member_no}</td>
				<td>${ProductOrderVO.order_recipient}</td>
				<td>${ProductOrderVO.order_address}</td>
				<td>${ProductOrderVO.order_date}</td>
				<td>${product_order.get(ProductOrderVO.order_status.toString())}</td>
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/ProductOrder/ProductOrderServlet" style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-theme" >修改訂單狀態</button>
						<input type="hidden" name="order_no" value="${ProductOrderVO.order_no}"> 
						<input type="hidden" name="action" value="get_One_For_Update_Status">
					</FORM>
				</td>
			</tr>
		</c:forEach>
      </table>
      </div>            
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
