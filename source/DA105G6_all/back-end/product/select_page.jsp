<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>IBM Product: Home</title>
<%-- <%@ include file="/back-end/gym_index/back-end-head.file" %> --%>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<%@ include file="/back-end/gym_index/back-end-navbar.file" %>

<%@ include file="/back-end/gym_index/back-end-sidebar.file" %>
<body bgcolor='white'>
<table id="table-1">
   <tr><td><h3>IBM Product: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Product: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/product/listAllProduct.jsp'>List</a> all Product.  <br><br></li>
  <li><a href='<%=request.getContextPath()%>/back-end/product_Order/listAllProductOrder.jsp'>List</a> all Product Order.  <br><br></li>
  <li><a href='<%=request.getContextPath()%>/back-end/product_Order_Detail/listAllProductOrderDetail.jsp'>List</a> all Product Order Detail.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
        <b>輸入商品編號 (如P001):</b>
        <input type="text" name="product_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/productOrderDetail/productOrderDetail.do">
        <b>輸入商品編號 (如日期-PXXX):</b>
        <input type="text" name="order_no">
        <input type="hidden" name="action" value="getOne_For_Product_Order_Detail">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>
<jsp:useBean id="ProductSvc" scope="page" class="com.product.model.ProductService" />
 <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="product_no">
         <c:forEach var="ProductVO" items="${ProductSvc.all}" > 
          <option value="${ProductVO.product_no}">${ProductVO.product_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name="product_no">
         <c:forEach var="ProductVO" items="${ProductSvc.all}" > 
          <option value="${ProductVO.product_no}">${ProductVO.product_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
<h3>商品管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/product/addProduct.jsp'>Add</a> a new Product.</li>
</ul>

<h3>檢舉管理</h3>
<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/product/listAllRepoertProduct.jsp'>查看所有檢舉</a></li>
  
</ul>
</body>

</html>