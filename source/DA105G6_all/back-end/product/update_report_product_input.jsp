<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.report_product.model.*"%>

<%
	ReportProductVO reportProductVO = (ReportProductVO) request.getAttribute("ReportProductVO");
// 	ProductVO ProductVO = (ProductVO) request.getAttribute("ProductVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>檢舉狀態修改 - update_report_product_input.jsp</title>

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
				<h3>檢舉商品資料修改 - update_report_product_input.jsp.jsp</h3>
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
	;
		<table>
			<tr>
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
				<td>商品檢舉狀態:</td>
				<td><input type="TEXT" name="report_product_status" size="45"
					value="<%=reportProductVO.getReport_product_status()%>" /></td>

			</tr>
			<tr>
				<td>檢舉商品時間:<font color=red><b>*</b></font></td>
				<td><%=reportProductVO.getReport_product_time()%></td>

			</tr>
			
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="product_no"
			value="<%=reportProductVO.getReport_product_no()%>"> <input type="submit"
			value="送出修改">
</body>







<script>

</script>
</html>