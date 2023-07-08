<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
  <title>購物車 | </title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/css/reset.css" />
  <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/css/style.css" />
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/css/hover.css">
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script>
  </script>
  <script type="text/css">
	img.del_cart{
	width: 30px;
	height: 30px;
}
</script>

</head>
<style>
.quantity {
    float: left;
    margin-right: 15px;
    position: relative;
    width: 80px;
    overflow: hidden
}

.quantity input {
    margin: 0;
    text-align: center;
    width: 25px;
    height: 20px;
    padding: 0;
    float: right;
    color: #000;
    font-size: 20px;
    border: 0;
    outline: 0;
    background-color: #F6F6F6
}

.quantity input.qty {
    position: relative;
    border: 0;
    width: 100%;
    height: 40px;
    padding: 10px 25px 10px 10px;
    text-align: center;
    font-weight: 400;
    font-size: 15px;
    border-radius: 0;
    background-clip: padding-box
}

.quantity .minus, .quantity .plus {
    line-height: 0;
    background-clip: padding-box;
    -webkit-border-radius: 0;
    -moz-border-radius: 0;
    border-radius: 0;
    -webkit-background-size: 6px 30px;
    -moz-background-size: 6px 30px;
    color: #bbb;
    font-size: 20px;
    position: absolute;
    height: 50%;
    border: 0;
    right: 0;
    padding: 0;
    width: 25px;
    z-index: 3
}

.quantity .minus:hover, .quantity .plus:hover {
    background-color: #dad8da
}

.quantity .minus {
    bottom: 0
}
.shopping-cart {
    margin-top: 20px;
}
</style>
<body>
<!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>
<% @SuppressWarnings("unchecked")
	Vector<ProductOrderDetailVO> buylist = (Vector<ProductOrderDetailVO>)session.getAttribute("shoppingcart"); 

String amount =  (String) session.getAttribute("amount");
	
	

// ProductService productSvc = new ProductService();
// 	List<ProductVO> list = productSvc.getAll();
// 	pageContext.setAttribute("list", list);
// 	for(ProductVO vo:list) {
// 		System.out.println(vo.getProduct_no());}
	
// 	@SuppressWarnings("unchecked")
// 	List<ProductOrderDetailVO> ProductOrderDetailVOlist =(List<ProductOrderDetailVO>) request
// 		.getAttribute("ProductOrderDetailVO"); //ProductServlet.java(Concroller), 存入req的ProductVO物件
// 		pageContext.setAttribute("ProductOrderDetailVOlist", ProductOrderDetailVOlist);
	

		
%>

<jsp:useBean id="productOrderDetailSvc" scope="session" class="com.product_order_detail.model.ProductOrderDetailService" />
<jsp:useBean id="productSvc" scope="session" class="com.product.model.ProductService" />
  <div class="container">
   <div class="card shopping-cart">
            <div class="card-header bg-dark text-light">
                <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                	您的購物車
                <a href="<%=request.getContextPath()%>/front-end/shop/shop.jsp" class="btn btn-outline-info btn-sm pull-right">繼續購物</a>
                <div class="clearfix"></div>
            </div>
            <div class="card-body">
                    <!-- PRODUCT -->
                     <%if (buylist != null && (buylist.size() > 0)) {%>
          <%
			 for (int index = 0; index < buylist.size(); index++) {
				 ProductOrderDetailVO order = buylist.get(index);
				 pageContext.setAttribute("order", order);
			%>
                    <div class="row">
                        <div class="col-12 col-sm-12 col-md-2 text-center">
                                <img class="img-responsive" src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=${order.product_no}" alt="prewiew" width="120" height="80">
                        </div> 
                        <div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                            <h4 class="product-name"><strong>${productSvc.getOneProductName(order.product_no)}</strong></h4>
<!--                             <h4> -->
<!--                                 <small>Product description</small> -->
<!--                             </h4> -->
                        </div>
                        <div class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                            <div class="col-3 col-sm-3 col-md-6 text-md-right" style="padding-top: 5px">
                                <h6><strong>NT$ <%=order.getDetail_unit_price()%><span class="text-muted">x</span></strong></h6>
                            </div>
                            <div class="col-4 col-sm-4 col-md-4">
                                <div class="quantity">
                                    <input type="button" value="+" class="qtyplus" field="quantity<%=order.getProduct_no()%>">
<!--                                     <input type="text" class="quantity" name="quantity" size="3" value=1> -->
<!-- 					           		<button class="add-to-cart" type="submit">Add to cart</button> -->
									<input type="hidden" class="product_no" name="product_no" value="<%=order.getProduct_no()%>"> 
									<input type="hidden" class="product_price" name="product_price" value="<%=order.getDetail_unit_price()%>">
<!-- 									<input type='text' name='quantity' value='0' class='qty' /> -->
                                    <input type="text" class="quantity" name="quantity<%=order.getProduct_no()%>" value="<%=order.getDetail_qty()%>" readonly>
                                    <input type="button" value="-" class="qtyminus" field="quantity<%=order.getProduct_no()%>">
                                </div>
                            </div>
                            <div class="col-2 col-sm-2 col-md-2 text-right">
                                <button type="button" class="btn btn-outline-danger btn-xs">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </button>
                                <input type="hidden" name="action" value="DELETE">
                				<input type="hidden" id="which" name="del" value="<%=index%>">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <!-- END PRODUCT -->
               <%}%>   
             <%}%>  
            </div>
            <div class="card-footer">
                <div class="pull-right" style="margin: 10px">
                    <a class="btn btn-success pull-right" href="<%=request.getContextPath()%>/front-end/shop/checkout.jsp">結帳</a>
                    <div class="pull-right" style="margin: 5px">
                        總價格: <input type="text" id="amount" value="<%=amount%>" readonly>
                    </div>
                </div>
            </div>
        </div>
</div>
    
<!-- footer 放body尾 -->
    <%@ include file="/front-end/gym_index/front-end-footer.file" %>
  </div>
  <script src="asset/js/main.js"></script>
</body>

<script>
	
	$(".btn-xs").click(function(){
	    $.ajax({
	    url: "<%=request.getContextPath()%>/Shopping/ShoppingCartServlet",
	    type: "POST",
	     data:{
	     "action" : "DELETE",
	     "del" : $(this).next().next("#which").val()
	   },
	   success:function(){
		   window.location.href="<%= request.getContextPath() %>"+"/front-end/shop/cart.jsp";
	   }
	  });
	 });
	
// 	$(".plus").click(function(){
// 	    $.ajax({
<%-- 	    url: "<%=request.getContextPath()%>/Shopping/ShoppingCartServlet", --%>
// 	    type: "POST",
// 	     data:{
// 	    	 "action" : "Plus",
// 	    	 "product_no" : $(this).next(".product_no").val(),
// 		     "product_price" : $(this).next().next(".product_price").val(),
// 		     "quantity" :  $(this).next().next().next(".quantity").val()
// 		   },
// 	   success:function(){
<%-- 		   window.location.href="<%= request.getContextPath() %>"+"/front-end/cart.jsp"; --%>
// 	   }
// 	  });
// alert($(this).next(".product_no").val()),
// alert($(this).next().next(".product_price").val()),
// alert($(this).next().next().next(".quantity").val())
// 	 });
	
// 	$(".minus").click(function(){
// 	    $.ajax({
<%-- 	    url: "<%=request.getContextPath()%>/Shopping/ShoppingCartServlet", --%>
// 	    type: "POST",
// 	     data:{
// 	     "action" : "Minus",
// 	     "product_no" : $(this).prev().prev().prev(".product_no").val(),
// 	     "product_price" : $(this).prev().prev(".product_price").val(),
// 	     "quantity" :  $(this).prev(".quantity").val()
// 	   },
// 	   success:function(){
<%-- 		   window.location.href="<%= request.getContextPath() %>"+"/front-end/cart.jsp"; --%>
// 	   }
// 	  });
// alert($(this).prev().prev().prev(".product_no").val()),
// 		alert($(this).prev().prev(".product_price").val()),
// 				alert($(this).prev(".quantity").val())
// 	 });
$(function() {
	
	
	  // This button will increment the value
	  $('.qtyplus').click(function(e) {
// 		  alert($("#amount").val());
// 		  alert($(this).next().next(".product_price").val());
	    // Stop acting like a button
	    e.preventDefault();
	    // Get the field name
	    fieldName = $(this).attr('field');
	    // Get its current value
	    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
	    // If is not undefined
	    if (!isNaN(currentVal)) {
	      // Increment
	      $('input[name=' + fieldName + ']').val(currentVal + 1);
	      var amount = parseInt($("#amount").val());
	      var plusqty = parseInt($(this).next().next(".product_price").val());
	      amount = amount+plusqty;
	      $("#amount").val(amount);
	    } else {
	      // Otherwise put a 0 there
	      $('input[name=' + fieldName + ']').val(0);
	    }
	    $.ajax({
	    		    url: "<%=request.getContextPath()%>/Shopping/ShoppingCartServlet",
	    	 	    type: "POST",
	    	 	     data:{
	    	 	    	 "action" : "Plus",
	    	 	    	 "product_no" : $(this).next(".product_no").val(),
	    	 		     "product_price" : $(this).next().next(".product_price").val(),
	    	 		     "quantity" :  $(this).next().next().next(".quantity").val()
	    	 		   },
	    	 	   success:function(){
	    			   window.location.href="<%= request.getContextPath() %>"+"/front-end/shop/cart.jsp";
	    	 	   }
	    	 	  });
	  });
	  // This button will decrement the value till 0
	  $(".qtyminus").click(function(e) {
	    // Stop acting like a button
	    e.preventDefault();
	    // Get the field name
	    fieldName = $(this).attr('field');
	    // Get its current value
	    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
	    // If it isn't undefined or its greater than 0
	    if (!isNaN(currentVal) && currentVal > 0) {
	      // Decrement one
	      $('input[name=' + fieldName + ']').val(currentVal - 1);
	      var amount = parseInt($("#amount").val());
	      var minusqty = parseInt($(this).prev().prev(".product_price").val());
	      amount = amount-minusqty;
	      $("#amount").val(amount);
	    } else {
	      // Otherwise put a 0 there
	      $('input[name=' + fieldName + ']').val(0);
	    }
	  });
	});
</script> 

</html>