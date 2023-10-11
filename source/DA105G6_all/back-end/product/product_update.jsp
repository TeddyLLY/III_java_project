<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductVO ProductVO = (ProductVO) request.getAttribute("ProductVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>商品修改</title>

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
        <h3><i class="fa fa-hand-o-right"></i> 商品修改管理</h3>
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
				<FORM METHOD="post" ACTION="product.do" name="form1" enctype="multipart/form-data">
              	<table
					class="table table-bordered table-condensed"
					style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
					cellpadding="10" border='1'>
					<thead>
                  <tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
				<td>商品編號:<font color=red><b>*</b></font></td>
				<td><%=ProductVO.getProduct_no()%></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="product_name" size="45"
					value="<%=ProductVO.getProduct_name()%>" /></td>
			</tr>
			<tr>
				<td>商品圖片:</td>
				<td>
					<div>
						<img id="output"
							src="<%=request.getContextPath()%>/product/DBGifReader4?product_no=<%=ProductVO.getProduct_no()%>" style="height: 150px; width: 150px;" />
					</div>
					<br>
					<label class="btn btn-info">
	                  <input type="file" name="product_photo"id="upload_img" style="display:none;" onchange="loadFile(event)">
	                  <i class="fa fa-photo"></i> 上傳圖片
	                 </label>
				</td>

			</tr>
			<tr>
				<td>商品數量:</td>
				<td><input type="TEXT" name="product_quantity" size="45" value="<%=ProductVO.getProduct_quantity()%>" /></td>

			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="product_price" size="45" value="<%=ProductVO.getProduct_price()%>" /></td>

			</tr>
			<tr>
				<td>商品銷量:</td>
				<td><input type="TEXT" name="product_sales" size="45" value="<%=ProductVO.getProduct_sales()%>" /></td>

			</tr>
			<tr>
				<td>商品內容:</td>
				<td><input type="TEXT" name="product_content" size="45"	value="<%=ProductVO.getProduct_content()%>" /></td>

			</tr>
			<tr>
				<td>商品總評價:</td>
				<td><input type="TEXT" name="product_total_evaluation" size="45" value="<%=ProductVO.getProduct_total_evaluation()%>" /></td>

			</tr>
			<tr>
				<td>商品評價總人數:</td>
				<td><input type="TEXT" name="product_people_of_evaluation" size="45" value="<%=ProductVO.getProduct_people_of_evaluation()%>" /></td>

			</tr>
			<tr>
				<td>商品狀態:</td>
				<td>
					<select name="product_status">
						 <option  size="45" value="0" <%=ProductVO.getProduct_status().equals(0)?"selected":""%>>下架</option>
						 <option  size="45" value="1" <%=ProductVO.getProduct_status().equals(1)?"selected":""%>>上架</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>商品總評價:</td>
				<td><input type="TEXT" name="product_average_evaluation" size="45" value="<%=ProductVO.getProduct_average_evaluation()%>" /></td>
			</tr>
      <tr>
      		<td></td>
      		<td>
      			<button type="submit" class="btn btn-theme" >送出修改</button>
      			<input type="hidden" name="action" value="update"> 
      	   		<input type="hidden" name="product_no" value="<%=ProductVO.getProduct_no()%>"> 
      		<button class="btn btn-theme04" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/product/product_basic.jsp'">取消</button>
      		</td>
      </tr>
      </table>
   			
      	  
      	  
	</FORM>
            </div>
          </div>
   
     </div>

        </section>
    <!-- /MAIN CONTENT -->
</section>
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
