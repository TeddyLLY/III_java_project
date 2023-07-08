<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
	pageContext.setAttribute("list", list);

	List<ProductOrderDetailVO> ProductOrderDetailVOlist =(List<ProductOrderDetailVO>) request
			.getAttribute("ProductOrderDetailVO"); //ProductServlet.java(Concroller), 存入req的ProductVO物件
			pageContext.setAttribute("ProductOrderDetailVOlist", ProductOrderDetailVOlist);
			for(ProductOrderDetailVO vo:ProductOrderDetailVOlist) {
				System.out.println(vo.getOrder_no());}
%>
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<html>
<head>
<title>商品資料 - listOneProduct.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

img {
	width: 150px;
	height: 150px;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料 - ListOneProduct.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/product/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>訂單明細編號</th>
			<th>商品編號</th>
			<th>訂單單價</th>
			<th>訂單數量</th>
			<th>商品名稱</th>
			<th>商品圖片</th>
		</tr>
		<c:forEach var="ProductOrderDetailVO" items="${ProductOrderDetailVOlist}">
			<tr>
				<td>${ProductOrderDetailVO.order_no}</td>
				<td>${ProductOrderDetailVO.product_no}</td>
				<td>${ProductOrderDetailVO.detail_unit_price}</td>
				<td>${ProductOrderDetailVO.detail_qty}</td>
				<td><c:forEach var="ProductVO" items="${list}">
						<c:if test="${ProductVO.product_no == ProductOrderDetailVO.product_no}">
					${ProductVO.product_name} 
				</c:if>
					</c:forEach>
				</td>
				<td><c:forEach var="ProductVO" items="${list}">
				<c:if test="${ProductVO.product_no == ProductOrderDetailVO.product_no}">
					<img src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=${ProductVO.product_no}">
				</c:if>
				</c:forEach>
				</td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>