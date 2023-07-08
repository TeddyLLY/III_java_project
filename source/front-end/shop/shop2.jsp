<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%@ page import="com.favorite_product.model.*"%>
<%@ page import="javax.servlet.*"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Shopping</title>
<link href="<%=request.getContextPath()%>/front-end/gym_index/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front-end/gym_index/css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/reset.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/hover.css">
<link href="<%=request.getContextPath()%>/gym_index/css/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="js/jquery.min.js"></script>
<!-- grid-slider -->
<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="js/jquery.contentcarousel.js"></script>
<!-- //grid-slider -->
<!---calender-style---->
<link rel="stylesheet" href="css/jquery-ui.css" />
<!---//calender-style---->	
		  <!-- CUSTOM CSS -->
<link href="<%=request.getContextPath()%>/lesson_pkg/css/style.css" rel="stylesheet">
</head>
<style>
/* .gg img{ */
/* max-width:100%; */
/* } */
* {
    margin: 0;
    padding: 0;
}
a.fas.fa.fa-heart {
	margin-top: 20px;
	color:red;
	cursor: pointer;
	  transform: scale(1.2) translateZ(0);
}

a.fas.fa.fa-heart-o {
	margin-top: 20px;
	cursor: pointer;
}
.widget-header{
		display: block;
		font-size: 16px;
		font-weight: 600;
		margin-bottom: 20px;
		padding-bottom: 10px;
		border-bottom: 1px solid $border-color;
	}
.widget {
    background: #fff;
    box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.1);
    padding: 25px 30px 30px;
    margin-bottom: 30px;
    border-radius: 2px;
}

body {
    background-color: #F2EEE9;
    font: normal 13px/1.5 Georgia, Serif;
    color: #333;
}
.wrapper {
   	width: 880px; 
    margin: 20px auto;
    padding: 20px;
}
h1 {
    display: inline-block;
    background-color: #333;
    color: #fff;
    font-size: 20px;
    font-weight: normal;
    text-transform: uppercase;
    padding: 4px 20px;
    float: left;
}
.clear {
    clear: both;
}
.items {
    display: block;
    margin: 20px 0;
}
.item {
    background-color: #fff;
    float: left;
    margin: 0 10px 10px 0;
    width: 205px;
    padding: 10px;
    height: 310px;
}
.item img {
    display: block;
    margin: auto;
}
h2 {
    font-size: 16px;
    display: block;
    border-bottom: 1px solid #ccc;
    margin: 0 0 10px 0;
    padding: 0 0 5px 0;
}
button {
    border: 1px solid #722A1B;
    padding: 4px 14px;
    background-color: #fff;
    color: #722A1B;
    text-transform: uppercase;
    float: right;
    margin: 5px 0;
    font-weight: bold;
    cursor: pointer;
}
.shopping-cart {
    display: inline-block;
    background: url('http://cdn1.iconfinder.com/data/icons/jigsoar-icons/24/_cart.png') no-repeat 0 0;
    width: 24px;
    height: 24px;
    margin: 0 10px 0 0;
}


</style>
<body onload="connect();" onunload="disconnect();">
<%
	MemberVO memberVO =(MemberVO) session.getAttribute("memberVO");
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAllStatus();
	pageContext.setAttribute("list", list);
	FavoriteProductService flSvc = new FavoriteProductService();
	List<FavoriteProductVO> FavoriteProductVOlist = flSvc.findByPrimaryKey(memberVO.getMember_no());
	pageContext.setAttribute("FavoriteProductVOlist", FavoriteProductVOlist);
	
// 	HttpSession session = req.getSession();

%>

<%@ include file="/front-end/gym_index/front-end-navbar.file" %>
	<!-- 內文開始 -->
	<div class="main">
	   <img src="<%=request.getContextPath() %>/front-end/gym_index/images/about_img.jpg" class="img-responsive" alt=""style="height: 100%; width: 100%; object-fit: contain">
		 <div class="about_banner_wrap">
      	    <h1 class="m_11">商城</h1>
      	 </div>
      	 <!-- wrapper -->
				<div class="wrapper ">
<!-- 				     <h1>商品瀏覽</h1> -->
				 <a href="<%=request.getContextPath()%>/front-end/shop/cart.jsp"><span><i class="shopping-cart text-right" style="float: right;"></i></a></span>
				
				    <div class="clear"></div>
				    <!-- items -->
				    <div class="row">
				    <div class="col-md-3">
					<div class="category-sidebar">
						<div class="widget category-list">
							<h4 class="widget-header">商品相關</h4>
							<ul class="category-list">
								<li><a href="">商品總覽 <span></span></a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/favorite_lesson/my_favorite_lesson.jsp">我的收藏 <span></span></a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-9 " >
				    <div class="category-search-filter">
						<div class="row">
						<div class="col-md-6">
								<strong>歡迎購物</strong> 
<!-- 								<select> -->
<!-- 									<option value="1">依評價排序</option> -->
<!-- 									<option value="2">依日期排序</option> -->
<!-- 									<option value="3">依價格排序</option> -->
<!-- 								</select> -->
							</div>
							<div class="col-md-6">
								<div class="view">
									<strong>Views</strong>
									<ul class="list-inline view-switcher">
										<li class="list-inline-item"><a
											href="javascript:void(0);"><i class="fa fa-th-large"></i></a>
										</li>
										<li class="list-inline-item"><a
											href="javascript:void(0);"><i class="fa fa-reorder"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				        <!-- single item -->
				        <%@ include file="pages/page1.file" %> 
				        <div class="row" style="width: 700px; padding:11px;">
						<c:forEach var="ProductVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						
				        <div class="item col-md-4">
				             <img src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=${ProductVO.product_no}" style="width:130px; hight:130px;">
				            <FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/product/product.do">
				             <input  type="submit" value="${ProductVO.product_name}">
				             <input type="hidden" class="product_no" name="product_no" value="${ProductVO.product_no}"> 
				             <input type="hidden" name="action" value="getOne_For_Display">
							</FORM>
				            <p>價格: <em>NT$ ${ProductVO.product_price}</em>
				            </p>
				            <h2><%int h=0;%>
											<c:forEach var="flVO" items="${FavoriteProductVOlist}">
											<%h++;%>
											<c:if test="${ProductVO.product_no==flVO.product_no}">
											<a id="heart${ProductVO.product_no}" class="fas fa-heart" onClick="favorite('${ProductVO.product_no}')"></a>
											</c:if>
											<c:if  test="${ProductVO.product_no!=flVO.product_no}">
											<%h--; %>
											</c:if>
											</c:forEach>
											<%if(h==0){%>
											<a id="heart${ProductVO.product_no}" class="far fa-heart" onClick="favorite('${ProductVO.product_no}')"></a>
											<%} %></h2>
							數量：<input type="text" class="quantity" name="quantity" size="3" value=1>
					            <button class="add-to-cart" type="submit">Add to cart</button>
								<input type="hidden" class="product_no" name="product_no" value="${ProductVO.product_no}"> 
								<input type="hidden" class="product_price" name="product_price" value="${ProductVO.product_price}">
								<input type="hidden" name="action" value="Add">
				        </div>
				        </c:forEach>
				        </div>
				      <div clss="col-md-12 text-center">
				      <div class="col-md-4"></div>
				        <div class=" col-md-4 pagination text-center" style="padding-right: 0px;" >
				<%if (pageNumber > 1) {%>
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item">
								<a class="page-link" href="<%=request.getRequestURI()%>?whichPage=1" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
									<span class="sr-only">Previous</span>
								</a>
							</li>
										<%for (int i = 1; i <= pageNumber; i++) {
											if(whichPage!=i)
											{%>
												<li class='page-item'>
											<a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%></a></li>
											<%}else{%>
											<li class='page-item disable'><a><%=i%></a></li>
											<%}%>

											<%}%> 	
							<li class="page-item">
								<a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
									<span class="sr-only">Next</span>
								</a>
							</li>
						</ul>
					</nav>
					<%}%>
				</div>
				<div class="col-md-4"></div>
				</div>
				  </div>
				        </div>
				</div>
				
			</div>
<!--/ wrapper -->

<%@ include file="/front-end/gym_index/front-end-footer.file" %></body>
<script>
	
	$(".add-to-cart").click(function(){
	    $.ajax({
	    url: "<%=request.getContextPath()%>/Shopping/ShoppingCartServlet",
	    type: "POST",
	     data:{
	     "action" : "Add",
	     "product_no" : $(this).next(".product_no").val(),
	     "product_price" : $(this).next().next(".product_price").val(),
	     "quantity" :  $(this).prev(".quantity").val()
	   },
	   success:function(){
		   swal("恭喜!","已加入購物車", "success", {button: "完成"});
   	}
	  });
	 });
	
	
	
	<!--WS-->
	   var MyPoint = "/MyEchoServer";
	    var host = window.location.host;
	    var path = window.location.pathname;  //  /IBM_WebSocket1_ChatA/index.html
	    var webCtx = path.substring(0, path.indexOf('/', 1));  //  /IBM_WebSocket1_ChatA
	    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	//  var endPointURL = "ws://localhost:8081/IBM_WebSocket1_ChatA/MyEchoServer";
	    var statusOutput = document.getElementById("statusOutput");
		var webSocket;
		
		function connect() {
			// 建立 websocket 物件
			webSocket = new WebSocket(endPointURL);
			
			webSocket.onopen = function(event) {
				updateStatus("WebSocket 成功連線");
			};
			
			//接收到訊息事件
			webSocket.onmessage = function(event) {
				swal("恭喜!","有新品上市", "success", {button: "完成"});
				
				
				
			};
		
			webSocket.onclose = function(event) {
				updateStatus("WebSocket 已離線");
			};
		}
		
		var inputUserName = document.getElementById("userName");
		inputUserName.focus();
		
		function sendMessage() {
		}

		
		function disconnect () {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
			document.getElementById('connect').disabled = false;
			document.getElementById('disconnect').disabled = true;
		}

		
		function updateStatus(newStatus) {
			statusOutput.innerHTML = newStatus;
		}
		
		
		//加入收藏 或 取消收藏
		function favorite(productNo){
			if($("#heart"+productNo).attr("class")==='far fa-heart'){
			    $.ajax({
			    	url:"<%=request.getContextPath()%>/FavoriteProduct/FavoriteProductServlet",
			    	type:"POST",
			    	data:{
			    		'action':'Insert',
			    		'product_no':productNo,
			    		'member_no':'<%=memberVO.getMember_no()%>'	
			    	},
			    	success:function(){
						swal("恭喜!","加入收藏!!", "success", {button: "完成"});
			    	}
			    });
			}else{
			    $.ajax({
			    	url:"<%=request.getContextPath()%>/FavoriteProduct/FavoriteProductServlet",
			    	type:"POST",
			    	data:{
			    		'action':'Delete',
			    		'product_no':productNo,
			    		'member_no':'<%=memberVO.getMember_no()%>'	
			    	},
			    	success:function(){
			    		swal("恭喜!","取消收藏!!", "success", {button: "完成"});
			    	}
			    });
			}
			$("#heart"+productNo).toggleClass('fas fa-heart')
			$("#heart"+productNo).toggleClass('far fa-heart')
			};
</script> 
</html>