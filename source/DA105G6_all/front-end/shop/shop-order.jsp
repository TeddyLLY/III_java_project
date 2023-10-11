<%@page import="com.product_order.model.ProductOrderService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%@ page import="com.favorite_product.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Free Gym Website Template | About :: w3layouts</title>
	
<!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 新版bootstramp 看情況或許可以放 -->
<%-- <%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>	   --%>
</head>
<style>
body {
  font: 13px/20px 'Helvetica Neue', Helvetica, Arial, sans-serif;
  color: #404040;
  background: #eeebe4;
}

.plans {
  width: 836px;
  margin: 50px auto;
  overflow: hidden;
}

.plan {
  float: left;
  width: 650px;
  margin: 20px 2px;
  padding: 15px 25px;
  text-align: center;
  background: white;
  background-clip: padding-box;
  border: 2px solid #e5ded6;
  border-color: rgba(black, .1);
  border-radius: 5px;
}

.plan-title {
  margin-bottom: 12px;
  font-size: 24px;
  color: #36bce6;
}

.plan-price {
  margin-bottom: 20px;
  line-height: 1;
  font-size: 28px;
  font-weight: bold;
  color: #fd935a;
}

.plan-unit {
  display: block;
  margin-top: 5px;
  font-size: 13px;
  font-weight: normal;
  color: #aaa;
}

.plan-features {
  width: 120px;
  margin: 20px auto 15px;
  padding: 15px 0 0 15px;
  border-top: 1px solid #e5ded6;
  text-align: left;
}

.plan-feature {
  line-height: 20px;
  font-size: 24px;
  font-weight: 500;
  color: #333;

  & + & { margin-top: 5px; }
}

.plan-feature-unit {
  margin-left: 2px;
  font-size: 16px;
}

.plan-feature-name {
  font-size: 13px;
  font-weight: normal;
  color: #aaa;
}

.plan-button {
  position: relative;
  display: block;
  line-height: 40px;
  font-size: 16px;
  font-weight: 500;
  color: white;
  text-align: center;
  text-decoration: none;
  text-shadow: 0 1px rgba(black, .1);
  background: #fd935c;
  border-bottom: 2px solid #cf7e3b;
  border-color: rgba(black, .15);
  border-radius: 4px;

  &:active {
    top: 2px;
    margin-bottom: 2px;
    border-bottom: 0;
  }
}

.plan-highlight {
  margin-top: 0;
  margin-bottom: 0;
  padding-left: 15px;
  padding-right: 15px;
  width: 170px;
  border: 4px solid #37bbe6;

  .plan-button {
    font-size: 18px;
    line-height: 49px;
    background: #37bce5;
    border-color: #3996b3;
    border-color: rgba(black, .15);
  }
}

.plan-recommended {
  width: 160px;
  margin: -15px auto 15px;
  padding-bottom: 2px;
  line-height: 22px;
  font-size: 14px;
  font-weight: bold;
  color: white;
  text-shadow: 0 1px rgba(black, .05);
  background: #37bbe6;
  border-radius: 0 0 4px 4px;
}
*{
    margin: 0;
    padding: 0;
}
#commentContext{
		width:200px;
		heigh:70px;
	}
	
	.rating {
    margin: 0px auto;
    width: 300px;
}
.rate {
    float: left;
    height: 46px;
    padding: 0 10px;
}
.rate:not(:checked) > input {
    position:absolute;
    top:-9999px;
}
.rate:not(:checked) > label {
    float:right;
    width:1em;
    overflow:hidden;
    white-space:nowrap;
    cursor:pointer;
    font-size:30px;
    color:#ccc;
}
.rate:not(:checked) > label:before {
    content: '★ ';
}
.rate > input:checked ~ label {
    color: #ffc700;    
}
.rate:not(:checked) > label:hover,
.rate:not(:checked) > label:hover ~ label {
    color: #deb217;  
}
.rate > input:checked + label:hover,
.rate > input:checked + label:hover ~ label,
.rate > input:checked ~ label:hover,
.rate > input:checked ~ label:hover ~ label,
.rate > label:hover ~ input:checked ~ label {
    color: #c59b08;
}

 
</style>
<body>
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>	
<%
  ProductVO ProductVO = (ProductVO) request.getAttribute("ProductVO"); //ProductServlet.java(Concroller), 存入req的ProductVO物件
  ProductOrderService productOrderSvc = new ProductOrderService();
	List<ProductOrderVO> list = productOrderSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="productOrderDetailSvc" scope="page" class="com.product_order_detail.model.ProductOrderDetailService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!-- nav_bar 放body頭 -->
	
	<!-- 內文開始 -->
	
<!-- 	<div class="container" ng-app="xmasApp" ng-controller="xmasCtrl"> -->
  <h1>🎄個人商品訂單歷史紀錄 </h1>

<div class="plans">
<c:forEach var="ProductOrderVO" items="${list}">

    <div class="plan">
      <h3 class="plan-title">訂單編號</h3>
      <span class="plan-unit">${ProductOrderVO.order_no}</span>
      <p class="plan-price">訂單狀態 <span class="plan-unit">${product_order.get(ProductOrderVO.order_status.toString())}</span></p>
      
<!--       <a href="#" class="plan-button">查看訂單明細</a> -->
<%--       <input type="button" class="plan-button" value="查看訂單明細" onclick="location.href='<%=request.getContextPath()%>/ProductOrder/ProductOrderServlet?order_no=${productOrderDetailVO.order_no}&product_no=${productOrderDetailVO.product_no}&action=showWindow'"/> --%>
    
    <a class="btn btn-primary" data-toggle="collapse" href="#A${ProductOrderVO.order_no}" role="button" aria-expanded="false" aria-controls="collapseExample">
						   			 顯示訂單明細
						  </a>
						  
		<div class="collapse" id="A${ProductOrderVO.order_no}">
			<td  colspan="6">
				<table align="center" style="border:3px #cccccc solid;" cellpadding="10" border='1'>
					<tr>
						<th>訂單明細編號</th>
						<th>商品編號</th>
						<th>訂單單價</th>
						<th>訂單數量</th>
<!-- 						<th>檢舉商品原因</th> -->
						<th>為商品評分</th>
					</tr>
				 	<c:forEach var="productOrderDetailVO" items="${productOrderDetailSvc.getOneProductOrderDetail(ProductOrderVO.order_no)}">

					<tr>
			               <td>${productSvc.getOneProduct(productOrderDetailVO.product_no).product_name} </td>
			               <td>${productOrderDetailVO.product_no}</td>
			               <td>${productOrderDetailVO.detail_unit_price}</td>
			               <td>${productOrderDetailVO.detail_qty}</td>
							<td>
							
							<input type="button" value="我要評分" onclick="location.href='<%=request.getContextPath()%>/ProductOrder/ProductOrderServlet?order_no=${productOrderDetailVO.order_no}&product_no=${productOrderDetailVO.product_no}&action=showWindow'" />
							
							</td>
							  </tr>
							
			     
					</c:forEach>
					
				</table>
			</td>
			</div>
    </div>
    </c:forEach>
   	
    </div>
    <%-- 	--${openModal}-- --%>
		<c:if test="${openModal!=null}">
<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">評分</h3>
             </div>
			
			<div class="modal-body">
			<!--  =========================================以下為原listOneEmp.jsp的內容========================================== -->
	<body>
		<%String order_no="";
		String product_no = "";
		List<String> starlist = (ArrayList<String>) session.getAttribute("commentList");
		for( int i =0; i<starlist.size();i++){
			
			if (i==0) {
				order_no = starlist.get(i);
				System.out.println(order_no);
				}else{ 
					product_no = starlist.get(i);
					System.out.println("ABC");
					System.out.println(product_no);
					}
		}
		
		pageContext.setAttribute("product_no",product_no);
		System.out.println("xxxpageContext");
	%> 
		<table style="border:1px black solid;width:95%;">
			<tr>
				<td>
					
				<img style="width: 150px; height: 150px;" src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=<%=product_no%>">
 				</td>
 				
			</tr>
			<tr>${productSvc.getOneProduct(product_no).product_name}</tr>
			<tr>
				<td>
					<textarea id="comment_product_content" name="comment_product_content" placeholder="字數限制150個">請對商品做出評論</textarea>
				</td>
				<td>	
					<div class="rate">

 						<input type="radio" class="star" id="star5" name="rate" value="5" />
									    <label for="star5" title="text">5 </label>
									    <input type="radio" class="star" id="star4" name="rate" value="4" />
									    <label for="star4" title="text">4 </label>
									    <input type="radio" class="star" id="star3" name="rate" value="3" />
									    <label for="star3" title="text">3 </label>
									    <input type="radio" class="star" id="star2" name="rate" value="2" />
									    <label for="star2" title="text">2 </label>
									    <input type="radio" class="star" id="star1" name="rate" value="1" />
									    <label for="star1" title="text">1 </label>
					</div>
				</td>
			</tr>
			<tr>
		</table>
<!-- 		<div> -->
<%-- 			<input type="hidden" id="order_no" name="order_no" value="<%=order_no%>" /> --%>
<%-- 			<input type="hidden" id="product_no" name="product_no" value="<%=product_no%>" /> --%>
<%-- 			<input type="hidden" id="product_average_evaluation" name="product_average_evaluation" value="${productSvc.getOneProduct(product_no).product_average_evaluation}" /> --%>
<!-- 			<input type="submit" value="送出評論" id="sendComment" /> -->
<!-- 		</div> -->
 		
</body>
			<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <input type="hidden" id="order_no" name="order_no" value="<%=order_no%>" />
			    <input type="hidden" id="product_no" name="product_no" value="<%=product_no%>" />
                <button type="button" class="btn btn-primary" id="sendComment"data-dismiss="modal">確認送出</button>
            </div>
		
		</div>
	</div>
</div>
		</c:if>
  
    <script>  
 		$("#sendComment").click(function(){
 			$.ajax({
				url: "<%= request.getContextPath()%>/product/product.do",
				type: "POST",
 				data:{
					"action" : "updatestar",
					"member_no" : "M001",
 					"product_no" : $(this).prev("#product_no").val(),
					"product_total_evaluation" : $(this).parent().prev().find("input[class='star']:checked").val(),
//  					"product_people_of_evaluation" : $("#product_people_of_evaluation").val()
					"comment_product_content": $(this).parent().prev().find("#comment_product_content").val()
			}
		});
//  			alert($(this).parent().prev().find("#comment_product_content").val());
		});
 	</script> 
    

 


	
<!-- 	       <div class="clear"></div> -->
	       
		 
		 
	     <!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
</body>
<!-- <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
<script>$("#basicModal").modal({show: true});
function showModal() {
    $('.btn-primary').modal('show'); 
}
</script>
</html>