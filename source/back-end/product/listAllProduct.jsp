<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>

<html>
<head>
<!-- <script src="https://code.jquery.com/jquery-2.2.4.js"></script> -->
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
 <title>所有商品資料 - listAllEmp.jsp</title>

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
	width: 1500px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}
img {
	width:150px;
	height:150px;
}
table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
*{
    margin: 0;
    padding: 0;
}

</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有商品資料 - listAllProduct.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/product/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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
		<%@ include file="pages/page1.file"%>
		<c:forEach var="ProductVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${ProductVO.product_no}</td>
				<td>${ProductVO.product_name}</td>
				<td><img src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=${ProductVO.product_no}"></td>
				<td>${ProductVO.product_quantity}</td>
				<td>${ProductVO.product_price}</td>
				<td>${ProductVO.product_sales}</td>
				<td>${ProductVO.product_content}</td>
				<td>${ProductVO.product_total_evaluation}</td>
				<td>${ProductVO.product_people_of_evaluation}</td>
				<td>${product.get(ProductVO.product_status.toString())}</td>
				<td>${ProductVO.product_average_evaluation}</td>
				
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="product_no" value="${ProductVO.product_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> 
						<input type="hidden" name="product_no" value="${ProductVO.product_no}"> 
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
				
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>

</body>
<script>

function showModal() {
    $('btn-primary').modal('show'); 
}



$(document).ready(function() {
    $(".submitExample").click(function() { //class 為 submitExample 的按鈕被點擊時
        $.ajax({
            type: "POST", //傳送方式
            url:  "<%=request.getContextPath()%>/ReportProduct/ReportProductServlet", //傳送目的地
//             dataType: "json", //資料格式
            data: { //傳送資料
				   'action':'Insert',
	    		   'product_no':$(this).next("#product_no").val(),
	    		   'member_no':'M002',
	    		   'report_product_content':$(this).siblings(".form-group").find("textarea").val()
            },
            success: function() {
            	
            
            }
        });
//     	alert($(this).siblings(".form-group").find("textarea").val());
    
    
    });        
});

$(document).ready(function() {
    $(".getstar").click(function() { //class 為 submitExample 的按鈕被點擊時
//         $.ajax({
//             type: "POST", //傳送方式
<%--             url:  "<%=request.getContextPath()%>/product/product.do", //傳送目的地 --%>
// //             dataType: "json", //資料格式
//             data: { //傳送資料
         	       
// 				   'action':'Update',
// 				   'product_total_evaluation':$(this).parent().prev().find("input[class='star']:checked").val(),
// 	    		   'product_no':$(this).parent().prev().find("#product_no").val(),
// 	    		   'member_no':'M002',
// 	    		   'report_product_content':$(this).siblings(".form-group").find("textarea").val()
//             },
//             success: function() {
            	 
            
//             }
//         });
    
// 			alert($(this).parent().prev().find("input[class='star']:checked").val());
			$( "[id='product_no']" ) .each(function(){
			alert($(this).parent().prev().find(".product_no").val());
			})
    
    
    });        
});
</script>
</html>