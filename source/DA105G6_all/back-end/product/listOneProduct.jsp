<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ProductVO ProductVO = (ProductVO) request.getAttribute("ProductVO"); //ProductServlet.java(Concroller), 存入req的ProductVO物件
%>

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
	width:150px;
	height:150px;
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
	<tr><td>
		 <h3>商品資料 - ListOneProduct.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/product/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品照片</th>
		<th>商品數量</th>
		<th>商品價格</th>
		<th>商品銷量</th>
		<th>商品內容</th>
		<th>商品總評價分數</th>
		<th>商品總評價人數</th>
		<th>商品狀態</th>
		<th>商品評價分數平均</th>
	</tr>
	<tr>
		<td><%=ProductVO.getProduct_no()%></td>
		<td><%=ProductVO.getProduct_name()%></td>	
		<td><img src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=${ProductVO.product_no}"></td>
		<td><%=ProductVO.getProduct_quantity()%></td>
		<td><%=ProductVO.getProduct_price()%></td>
		<td><%=ProductVO.getProduct_sales()%></td>
		<td><%=ProductVO.getProduct_content()%></td>
		<td><%=ProductVO.getProduct_total_evaluation()%></td>
		<td><%=ProductVO.getProduct_people_of_evaluation()%></td>
<%-- 		<td><%=ProductVO.getProduct_status()%></td> --%>
		<td>${product.get(ProductVO.product_status.toString())}</td>
		<td><%=ProductVO.getProduct_status()%></td>
		<td><%=ProductVO.getProduct_average_evaluation()%></td>
		
	</tr>
</table>

</body>
</html>