<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO ProductVO = (ProductVO) request.getAttribute("ProductVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料新增</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

  <!--external css-->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style-responsive.css" rel="stylesheet">
  
<%@ include file="/back-end/gym_index/back-end-head-source.file" %>

  
</head>
<body>
 <section id="container">
  <%@ include file="/back-end/gym_index/back-end-navbar.file" %>
   <%@ include file="/back-end/gym_index/back-end-sidebar.file" %>
<!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <div class="row mt">
        <h3><i class="fa fa-hand-o-right"></i> 商品新增</h3>
      
          <div class="col-md-12">
    


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
  
	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1" enctype="multipart/form-data">
	 <table
		class="table table-bordered table-condensed"
		style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
		cellpadding="10" border='1'>
		<thead>
     <tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
		<td>*</td><td></td>
			</tr>
			<tr>
		
		<td>商品名稱:</td>
		<td><input type="TEXT" name="product_name" size="45"  id="product_name"
			 value="<%= (ProductVO==null)? "" : ProductVO.getProduct_name()%>" /></td>
	</tr>
	<tr>
		<td>商品圖片:</td>
  <td>
	  <div>
	  	<img id="output"src="<%= request.getContextPath()%>/product/DBGifReader4" style="height:150px;width:150px;"/>
	  </div>  
	  <br>
	   	<label class="btn btn-info">
 		 <input type="file" accept="image/*" name="product_photo" id="upload_img" style="display:none;" onchange="loadFile(event)" > 
  		 <i class="fa fa-photo"></i> 上傳圖片
	    </label>  
  </td> 
	</tr>
	<tr>
	<tr>
		<td>商品數量:</td>
		<td><input type="TEXT" name="product_quantity"  id="product_quantity" size="45" 
			 value="<%= (ProductVO==null)? "" : ProductVO.getProduct_quantity()%>" /></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="product_price"  id="product_price" size="45" 
			 value="<%= (ProductVO==null)? "" : ProductVO.getProduct_price()%>" /></td>
	</tr>
	<tr>
		<td>商品銷量:</td>
		<td><input type="TEXT" name="product_sales"  id="product_sales" size="45" 
			 value="<%= (ProductVO==null)? "" : ProductVO.getProduct_sales()%>" /></td>
	</tr>
	<tr>
		<td>商品內容:</td>
		<td><input type="TEXT" name="product_content" size="45"  id="product_content"
			 value="<%= (ProductVO==null)? "" : ProductVO.getProduct_content()%>" /></td>
	</tr>
	<tr>
		<td>商品總評價:</td>
		<td><input type="TEXT" name="product_total_evaluation"  id="product_total_evaluation" size="45" 
			 value="<%= (ProductVO==null)? "" : ProductVO.getProduct_total_evaluation()%>" /></td>
	</tr>
	<tr>
		<td>商品評價總人數:</td>
		<td><input type="TEXT" name="product_people_of_evaluation" size="45"  id="product_people_of_evaluation"
			 value="<%= (ProductVO==null)? "" : ProductVO.getProduct_people_of_evaluation()%>" /></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
  <td>
	<select name="product_status" size="1">
		 <option value="0">${product.get("0")}</option>
         <option value="1">${product.get("1")}</option>
     </select>
  </td>
		
			 
	</tr>
		
	<tr>
		<td>商品總評價:</td>
		<td><input type="TEXT" name="product_average_evaluation" id="product_average_evaluation" size="45"
			 value="<%= (ProductVO==null)? "" : ProductVO.getProduct_average_evaluation()%>" /></td>
	</tr>
	
	<tr>
		<td></td>
		<td><input type="hidden" name="action" value="insert">
			<button type="submit" class="btn btn-theme" >送出新增</button>
            <button class="btn btn-theme04" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/product/product_basic.jsp'">取消</button>&emsp;&emsp;

			<input type="button" class="magic btn btn-round btn-info" value="神奇小按鈕"></td>
			
	<tr>
</table>
</FORM>
</div>
          </div>
  
        </section>

  </section>
</section>
</body>


<script>
var loadFile = function(event) {
	 var output = document.getElementById('output');
	 output.src = URL.createObjectURL(event.target.files[0]);
	};
			$(".magic").click(function(){
				$("#product_name").val('放鬆滾筒');
				$("#product_quantity").val('1');
				$("#product_price").val('2000');
				$("#product_sales").val('10');
				$("#product_content").val('粗且香又甜');
				$("#product_total_evaluation").val('0');
				$("#product_people_of_evaluation").val('0');
				$("#product_average_evaluation").val('0');
			})

</script>

</html>