<%@page import="com.member.model.MemberVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%@ page import="com.favorite_product.model.*"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Free Gym Website Template | About :: w3layouts</title>
	
<!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- sweetAlert2 -->
<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.js"></script>

<!-- 新版bootstramp 看情況或許可以放 -->
<%-- <%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>	   --%>
</head>
<style>
/*********************************************
    			Call Bootstrap
*********************************************/

/* @import url("bootstrap/bootstrap.min.css"); */
/* @import url("bootstrap-override.css"); */
/* @import url("//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"); */

/*********************************************
        		Theme Elements
*********************************************/

.gold{
	color: #FFBF00;
}

/*********************************************
					PRODUCTS
*********************************************/

.product{
	border: 1px solid #dddddd;
	height: 321px;
}

.product>img{
	max-width: 230px;
}

.product-rating{
	font-size: 20px;
	margin-bottom: 25px;
}

.product-title{
	font-size: 20px;
}

.product-desc{
	font-size: 14px;
}

.product-price{
	font-size: 22px;
}

.product-stock{
	color: #74DF00;
	font-size: 20px;
	margin-top: 10px;
}

.product-info{
		margin-top: 50px;
}

/*********************************************
					VIEW
*********************************************/

.content-wrapper {
	max-width: 1140px;
	background: #fff;
	margin: 0 auto;
	margin-top: 25px;
	margin-bottom: 10px;
	border: 0px;
	border-radius: 0px;
}

.container-fluid{
	max-width: 1140px;
	margin: 0 auto;
}

.view-wrapper {
	float: right;
	max-width: 70%;
	margin-top: 25px;
}

.container {
	padding-left: 0px;
	padding-right: 0px;
	max-width: 100%;
}

/*********************************************
				ITEM 
*********************************************/

.service1-items {
	padding: 0px 0 0px 0;
	float: left;
	position: relative;
	overflow: hidden;
	max-width: 100%;
	height: 321px;
	width: 130px;
}

.service1-item {
	height: 107px;
	width: 120px;
	display: block;
	float: left;
	position: relative;
	padding-right: 20px;
	border-right: 1px solid #DDD;
	border-top: 1px solid #DDD;
	border-bottom: 1px solid #DDD;
}

.service1-item > img {
	max-height: 110px;
	max-width: 110px;
	opacity: 0.6;
	transition: all .2s ease-in;
	-o-transition: all .2s ease-in;
	-moz-transition: all .2s ease-in;
	-webkit-transition: all .2s ease-in;
}

.service1-item > img:hover {
	cursor: pointer;
	opacity: 1;
}

.service-image-left {
	padding-right: 50px;
}

.service-image-right {
	padding-left: 50px;
}

.service-image-left > center > img,.service-image-right > center > img{
	max-height: 155px;
}
</style>
<body>
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>	
<%
MemberVO memberVO =(MemberVO) session.getAttribute("memberVO");
  ProductVO ProductVO = (ProductVO) request.getAttribute("ProductVO"); //ProductServlet.java(Concroller), 存入req的ProductVO物件
%>
<!-- nav_bar 放body頭 -->
	
	<!-- 內文開始 -->
	
	<div class="container-fluid">
    <div class="content-wrapper">	
		<div class="item-container">	
			<div class="container">	
				<div class="col-md-12-5">
					<div class=" col-md-3 service-image-left">
                    
									<td><img src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=${ProductVO.product_no}"></td>
					</div>
				</div>
					
				<div class="col-md-7">
					<div class="product-title"><%=ProductVO.getProduct_name()%></div>
					<div class="product-desc"><%=ProductVO.getProduct_content()%></div>
					<hr>
					<div class="product-price">$ <%=ProductVO.getProduct_price()%></div>
					數量：<input type="text" class="quantity" name="quantity" size="3" value=1>
					<hr>
<!-- 					<div class="btn-group cart"> -->
						<button type="submit" class="btn btn-success add-to-cart">
							加入購物車
						</button>
						<input type="hidden" class="product_no" name="product_no" value="<%=ProductVO.getProduct_no()%>"> 
						<input type="hidden" class="product_price" name="product_price" value="<%=ProductVO.getProduct_price()%>">
<!-- 					</div> -->
<!-- 					<div class="btn-group wishlist"> -->
				<!-- Button trigger modal -->
<!-- 						<button type="button"  id="exampleModal" class="btn btn-danger btn-primary" data-toggle="modal" data-target="#exampleModal"> -->
							<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#xxx" onclick="report()">
<!-- 						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"> -->
<!-- 						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"> -->
							檢舉此商品
						</button>
						<!-- Modal -->
							<div class="modal fade" id="xxx" tabindex="-1" role="dialog" aria-labelledby="xxx" aria-hidden="true">
							  <div class="modal-dialog" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">檢舉商品</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							      
							      
							      <div class="modal-body">
							      	<div class=" col-md-3 service-image-left">
										<td><img src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=${ProductVO.product_no}"></td>
									</div>
							      <div class="product-title"><%=ProductVO.getProduct_name()%></div>
							      <textarea id="comment_product_content" name="comment_product_content" placeholder="字數限制150個">請填寫原因</textarea>
							      </div>
							      
							      
							      <div class="modal-footer">
							        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
							        <button type="submit" id="sendComment" class="btn btn-primary" data-dismiss="modal">確認送出</button>
							        <input type="hidden" id="product_no" name="product_no" value="<%=ProductVO.getProduct_no()%>" />
							      </div>
							    </div>
							  </div>
							</div>
					</div>
				</div>
			</div> 
		</div>
		
	</div>
</div>

<!-- 	       <div class="clear"></div> -->
	       
		 
		 
	     <!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
</body>
<!-- <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
<script>
var memberNo = "${memberVO.member_no}";
report = function (){
	if(memberNo.trim().length==0){
		 Swal.fire({
			 title: '登入會員',
			 icon:'warning',					
	}).then((result) => {
		  if (result.value) {
			  window.location="<%=request.getContextPath()%>/front-end/index/member_login.jsp";  
		  }})
	}
};
	$(document).ready(function() {
	    $("#sendComment").click(function() { //class 為 submitExample 的按鈕被點擊時
	        $.ajax({
	            type: "POST", //傳送方式
	            url:  "<%=request.getContextPath()%>/ReportProduct/ReportProductServlet", //傳送目的地
//	             dataType: "json", //資料格式
	            data: { //傳送資料
					   'action':'Insert',
		    		   'product_no':$(this).next("#product_no").val(),
		    		   'member_no':'${memberVO.member_no}',
		    		   'report_product_content':$(this).parent().prev().find("textarea").val()
	            },
	            success: function() {
	            	
	            	swal("恭喜!","已檢舉成功", "success", {button: "完成"});
	            }
	        });
	    
	    });        
	});
	
</script> 
</html>