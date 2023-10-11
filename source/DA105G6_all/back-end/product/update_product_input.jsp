<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO ProductVO = (ProductVO) request.getAttribute("ProductVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料修改 - update_emp_input.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料修改 - update_product_input.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/product/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="product.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
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
				<td><input type="file" accept="image/*" name="product_photo"
					onchange="loadFile(event)">
					<div>
						<img id="output"
							src="<%=request.getContextPath()%>/product/DBGifReader4?product_no=<%=ProductVO.getProduct_no()%>" style="height: 150px; width: 150px;" />
					</div></td>


			</tr>
			<tr>
				<td>商品數量:</td>
				<td><input type="TEXT" name="product_quantity" size="45"
					value="<%=ProductVO.getProduct_quantity()%>" /></td>

			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="product_price" size="45"
					value="<%=ProductVO.getProduct_price()%>" /></td>

			</tr>
			<tr>
				<td>商品銷量:</td>
				<td><input type="TEXT" name="product_sales" size="45"
					value="<%=ProductVO.getProduct_sales()%>" /></td>

			</tr>
			<tr>
				<td>商品內容:</td>
				<td><input type="TEXT" name="product_content" size="45"
					value="<%=ProductVO.getProduct_content()%>" /></td>

			</tr>
			<tr>
				<td>商品總評價:</td>
				<td><input type="TEXT" name="product_total_evaluation"
					size="45" value="<%=ProductVO.getProduct_total_evaluation()%>" /></td>

			</tr>
			<tr>
				<td>商品評價總人數:</td>
				<td><input type="TEXT" name="product_people_of_evaluation"
					size="45" value="<%=ProductVO.getProduct_people_of_evaluation()%>" /></td>

			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><input type="TEXT" name="product_status" size="45"
					value="<%=ProductVO.getProduct_status()%>" /></td>

			</tr>
			<tr>
				<td>商品總評價:</td>
				<td><input type="TEXT" name="product_average_evaluation"
					size="45" value="<%=ProductVO.getProduct_average_evaluation()%>" /></td>
			</tr>

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="product_no"
			value="<%=ProductVO.getProduct_no()%>"> <input type="submit"
			value="送出修改">
	</FORM>
</body>







<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>
</html>