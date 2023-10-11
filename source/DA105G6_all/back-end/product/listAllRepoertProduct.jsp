<%@page import="com.report_product.model.ReportProductService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.report_product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
// 	ProductService productSvc = new ProductService();
// 	List<ProductVO> list = productSvc.getAll();
// 	pageContext.setAttribute("list", list);
	ReportProductService reportProductSvc = new ReportProductService();
	List<ReportProductVO> reportProductList = reportProductSvc.getAll();
	pageContext.setAttribute("reportProductList", reportProductList);
	
%>
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>

<html>
<head>
<!-- import jQuery -->
<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<title>所有商品資料 - listAllReportProduct.jsp</title>

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
	width: 800px;
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
			
			
		</tr>
<%-- 		<%@ include file="pages/page1.file"%> --%>
		<c:forEach var="reportProductVO" items="${reportProductList}"> 

			<tr>
					
				
				<td>${reportProductVO.report_product_no}</td>
				<td>${reportProductVO.member_no}</td>
				<td>${reportProductVO.product_no}</td>
				<td>${reportProductVO.report_product_content}</td>
				<td>${reportProductVO.report_product_status}</td>
				<td>${reportProductVO.report_product_time}</td>
				
				<td>
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/product/product.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->

						<input type="submit" class="updatereport" value="修改"> 
						<input type="hidden" class="report_product_no" name="report_product_no" value="${reportProductVO.report_product_no}"> 
<!-- 						<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/product/product.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="product_no" value="${ProductVO.product_no}"> <input --%>
<!-- 							type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 					<td> -->
					
<!-- 						<input type="submit" class="submitExample" value="檢舉商品"> -->
<%-- 						<input type="hidden" id="product_no" name="product_no" value="${ProductVO.product_no}">  --%>
<!-- 						<input type="hidden" name="action" value="report_product"> -->
					
<!-- 					    <div class="row form-group"> -->
<!--                                 <div class="col col-md-3"><label for="auc_ven_intro" class=" form-control-label">檢舉商品原因</label></div> -->
<!--                                 <div class="col-12 col-md-9"><textarea id="area" name="report_product_content" rows="9"  class="form-control">          </textarea></div> -->
                                
<!--                             </div> -->
<!--                         </div> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>
<%-- 	<%@ include file="pages/page2.file"%> --%>

</body>
<script>
$(document).ready(function() {
    $(".updatereport").click(function() { //class 為 submitExample 的按鈕被點擊時
        $.ajax({
            type: "POST", //傳送方式
            url:  "<%=request.getContextPath()%>/ReportProduct/ReportProductServlet", //傳送目的地
//             dataType: "json", //資料格式
            data: { //傳送資料
				   'action':'getOne_For_Update',
	    		   'report_product_no':$(this).next(".report_product_no").val()
            },
            success: function(data) {
           		window.location.href="<%=request.getContextPath()%>/back-end/product/update_report_product_input.jsp";
            }
        });
    });        
});
</script>
</html>