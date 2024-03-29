<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_detail.model.*"%><!DOCTYPE html>
<html lang="en">
<head>
  <!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
  <title>運送</title>
  <link rel="stylesheet" href="css/reset.css" />
  <link rel="stylesheet" href="css/style.css" />
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
  <link rel="stylesheet" type="text/css" href="css/hover.css">  
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
</head>
<style>
html,body,.wrapper{
    background: #f7f7f7;
}
.steps {
    margin-top: -41px;
    display: inline-block;
    float: right;
    font-size: 16px
}
.step {
    float: left;
    background: white;
    padding: 7px 13px;
    border-radius: 1px;
    text-align: center;
    width: 100px;
    position: relative
}
.step_line {
    margin: 0;
    width: 0;
    height: 0;
    border-left: 16px solid #fff;
    border-top: 16px solid transparent;
    border-bottom: 16px solid transparent;
    z-index: 1008;
    position: absolute;
    left: 99px;
    top: 1px
}
.step_line.backline {
    border-left: 20px solid #f7f7f7;
    border-top: 20px solid transparent;
    border-bottom: 20px solid transparent;
    z-index: 1006;
    position: absolute;
    left: 99px;
    top: -3px
}
.step_complete {
    background: #357ebd
}
.step_complete a.check-bc, .step_complete a.check-bc:hover,.afix-1,.afix-1:hover{
    color: #eee;
}
.step_line.step_complete {
    background: 0;
    border-left: 16px solid #357ebd
}
.step_thankyou {
    float: left;
    background: white;
    padding: 7px 13px;
    border-radius: 1px;
    text-align: center;
    width: 100px;
}
.step.check_step {
    margin-left: 5px;
}
.ch_pp {
    text-decoration: underline;
}
.ch_pp.sip {
    margin-left: 10px;
}
.check-bc,
.check-bc:hover {
    color: #222;
}
.SuccessField {
    border-color: #458845 !important;
    -webkit-box-shadow: 0 0 7px #9acc9a !important;
    -moz-box-shadow: 0 0 7px #9acc9a !important;
    box-shadow: 0 0 7px #9acc9a !important;
    background: #f9f9f9 url(../images/valid.png) no-repeat 98% center !important
}

.btn-xs{
    line-height: 28px;
}

/*login form*/
.login-container{
    margin-top:30px ;
}
.login-container input[type=submit] {
  width: 100%;
  display: block;
  margin-bottom: 10px;
  position: relative;
}

.login-container input[type=text], input[type=password] {
  height: 44px;
  font-size: 16px;
  width: 100%;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}

.login-container input[type=text]:hover, input[type=password]:hover {
  border: 1px solid #b9b9b9;
  border-top: 1px solid #a0a0a0;
  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
}

.login-container-submit {
  /* border: 1px solid #3079ed; */
  border: 0px;
  color: #fff;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #357ebd;/*#4d90fe;*/
  padding: 17px 0px;
  font-family: roboto;
  font-size: 14px;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}

.login-container-submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #357ae8;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}

.login-help{
  font-size: 12px;
}

.asterix{
    background:#f9f9f9 url(../images/red_asterisk.png) no-repeat 98% center !important;
}

/* images*/
ol, ul {
  list-style: none;
}
.hand {
  cursor: pointer;
  cursor: pointer;
}
.cards{
    padding-left:0;
}
.cards li {
  -webkit-transition: all .2s;
  -moz-transition: all .2s;
  -ms-transition: all .2s;
  -o-transition: all .2s;
  transition: all .2s;
  background-image: url('//c2.staticflickr.com/4/3713/20116660060_f1e51a5248_m.jpg');
  background-position: 0 0;
  float: left;
  height: 32px;
  margin-right: 8px;
  text-indent: -9999px;
  width: 51px;
}
.cards .mastercard {
  background-position: -51px 0;
}
.cards li {
  -webkit-transition: all .2s;
  -moz-transition: all .2s;
  -ms-transition: all .2s;
  -o-transition: all .2s;
  transition: all .2s;
  background-image: url('//c2.staticflickr.com/4/3713/20116660060_f1e51a5248_m.jpg');
  background-position: 0 0;
  float: left;
  height: 32px;
  margin-right: 8px;
  text-indent: -9999px;
  width: 51px;
}
.cards .amex {
  background-position: -102px 0;
}
.cards li {
  -webkit-transition: all .2s;
  -moz-transition: all .2s;
  -ms-transition: all .2s;
  -o-transition: all .2s;
  transition: all .2s;
  background-image: url('//c2.staticflickr.com/4/3713/20116660060_f1e51a5248_m.jpg');
  background-position: 0 0;
  float: left;
  height: 32px;
  margin-right: 8px;
  text-indent: -9999px;
  width: 51px;
}
.cards li:last-child {
  margin-right: 0;
}
/* images end */



/*
 * BOOTSTRAP
 */
.container{
    border: none;
}
.panel-footer{
    background:#fff;
}
.btn{
    border-radius: 1px;
}
.btn-sm, .btn-group-sm > .btn{
    border-radius: 1px;
}
.input-sm, .form-horizontal .form-group-sm .form-control{
    border-radius: 1px;
}

.panel-info {
    border-color: #999;
}

.panel-heading {
    border-top-left-radius: 1px;
    border-top-right-radius: 1px;
}
.panel {
    border-radius: 1px;
}
.panel-info > .panel-heading {
    color: #eee;
    border-color: #999;
}
.panel-info > .panel-heading {
    background-image: linear-gradient(to bottom, #555 0px, #888 100%);
}

hr {
    border-color: #999 -moz-use-text-color -moz-use-text-color;
}

.panel-footer {
    border-bottom-left-radius: 1px;
    border-bottom-right-radius: 1px;
    border-top: 1px solid #999;
}

.btn-link {
    color: #888;
}

hr{
    margin-bottom: 10px;
    margin-top: 10px;
}

/** MEDIA QUERIES **/
@media only screen and (max-width: 989px){
    .span1{
        margin-bottom: 15px;
        clear:both;
    }
}

@media only screen and (max-width: 764px){
    .inverse-1{
        float:right;
    }
}

@media only screen and (max-width: 586px){
    .cart-titles{
        display:none;
    }
    .panel {
        margin-bottom: 1px;
    }
}

.form-control {
    border-radius: 1px;
}

@media only screen and (max-width: 486px){
    .col-xss-12{
        width:100%;
    }
    .cart-img-show{
        display: none;
    }
    .btn-submit-fix{
        width:100%;
    }
    
}
/*
@media only screen and (max-width: 777px){
    .container{
        overflow-x: hidden;
    }
}*/
</style>
<body>
<!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>
<% @SuppressWarnings("unchecked")
	Vector<ProductOrderDetailVO> buylist = (Vector<ProductOrderDetailVO>)session.getAttribute("shoppingcart"); 
ProductService productSVC = new ProductService();
String amount =  (String) session.getAttribute("amount");
%>
<jsp:useBean id="productSvc" class="com.product.model.ProductService" />
<div class="container wrapper">
            <div class="row cart-head">
                <div class="container">
                <div class="row">
                    <p></p>
                </div>
                <div class="row">
                    <p></p>
                </div>
                </div>
            </div>    
            <div class="row cart-body">
                <form class="form-horizontal" method="post" action="">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 col-md-push-6 col-sm-push-6">
                    <!--REVIEW ORDER-->
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            訂單摘要 <div class="pull-right"><small><a class="afix-1" href="#"></a></small></div>
                        </div>
                        <div class="panel-body">
                         <%	
      	 for (int index = 0; index < buylist.size(); index++) {
			 ProductOrderDetailVO order = buylist.get(index);
							%>
                            <div class="form-group">
                                <div class="col-sm-3 col-xs-3">
                                    <img class="img-responsive" src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=<%=order.getProduct_no()%>"/>
                                </div>
                                <div class="col-sm-6 col-xs-6">
                                    <div class="col-xs-12"><small>商品名稱:<span><%=productSVC.getOneProductName(order.getProduct_no()) %></span></small></div>
                                    <div class="col-xs-12"><small>數量:<span><%=order.getDetail_qty()%></span></small></div>
                                </div>
                                <div class="col-sm-3 col-xs-3 text-right">
                                    <h6><span>NT$</span> <%=order.getDetail_unit_price()%></h6>
                                </div>
                            </div>
                            <%} %>
                            <div class="form-group"><hr /></div>
                            
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <strong>總計</strong>
                                    <div class="pull-right"><span>$</span><span><%=amount%></span></div>
                                </div>
                            </div>
                            <div class="form-group"><hr /></div>
                        </div>
                    </div>
                    <!--REVIEW ORDER END-->
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 col-md-pull-6 col-sm-pull-6">
                    <!--SHIPPING METHOD-->
                    <div class="panel panel-info">
                        <div class="panel-heading">詳細資訊</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <h4>購買人資訊</h4>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>姓名:</strong></div>
                                <div class="col-md-12">
                                    <input type="text" class="order_recipient" id="order_recipient" name="order_recipient" value="" required/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12"><strong>地址:</strong></div>
                                <div class="col-md-12"><input type="text"  id="order_address" name="order_address" class="order_address" value="" required/></div>
                            </div>
                        </div>
                    <!--SHIPPING METHOD END-->
                    <!--CREDIT CART PAYMENT-->
                   <script src="<%=request.getContextPath()%>/front-end/card-master/dist/jquery.card.js"></script>
					 <div class="demo-container">
               <div class="card-wrapper"></div>
               <div class="form-container active">
               <div class="form-group">
                   <div class="col-md-12"><strong>信用卡卡號:</strong></div>
                   <div class="col-md-12"><input placeholder="信用卡卡號" type="tel"  id="number" name="number" required class="form-control"></div>
                   
                   <div class="col-md-12"><strong>持卡人:</strong></div>
                   <div class="col-md-12"><input placeholder="持卡人" type="text" name="name" id="name" required class="form-control"></div>
                   
                   <div class="col-md-12"><strong>卡片有效期限MM/YY:</strong></div>
                   <div class="col-md-12"><input placeholder="卡片有效期限MM/YY" type="tel" id="expiry" name="expiry" required class="form-control"></div>
                   
                   <div class="col-md-12"><strong>背面末3碼:</strong></div>
                   <div class="col-md-12"><input placeholder="背面末3碼" type="number" id="cvc" name="cvc" required class="form-control"></div>
                   </div>
               </div>
           </div>
                    </div>
                    <!--CREDIT CART PAYMENT END-->
                </div>
                <button  type="submit" class="checkout"><i class="fa fa-lock"></i>確認付款</button>
                <input type="button" class="magic login100-form-btn" value="神奇小按鈕">
<!--                  onclick="checkout()" -->
                </form>
            </div>
            <div class="row cart-footer">
        
            </div>
    </div>
  <%@ include file="/front-end/gym_index/front-end-footer.file" %>
</body>

<script>
$('.form-container.active').card({
    container: '.card-wrapper', // required
});
</script>
<script>
$(".checkout").click(function(){
    $.ajax({
    url: "<%=request.getContextPath()%>/Shopping/ShoppingCartServlet",
    type: "POST",
     data:{
      "action" : "Checkout",
      "order_recipient" : $(".order_recipient").val(),
      "order_address" :  $(".order_address").val()
  },
  success:function(){
	   window.location.href="<%=request.getContextPath()%>/front-end/shop/shop.jsp";
  }
  });
// alert($(".order_recipient").val());
// alert($(".order_address").val());
});

$(".magic").click(function(){
	$("#cvc").val('258');
	$("#expiry").val('12/22');
	$("#name").val('楊過');
	$("#number").val('5896-7878-0123-6480');
	$("#order_address").val('台中市霧峰區光明路27巷28號');
	$("#order_recipient").val('楊過');
})

</script>
</html>