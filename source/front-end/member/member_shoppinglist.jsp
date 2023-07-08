<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%@ page import="com.favorite_product.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>


<jsp:useBean id="productOrderDetailSvc" scope="page" class="com.product_order_detail.model.ProductOrderDetailService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/member/member_center_style.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/muscle_group.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/col.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/js/col.js">
<meta charset="utf-8">
<title>會員訓練</title>
</head>
<style>
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
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="container">
		<div class="col-md-12">
			<div class="row">

				<!-- member center bar -->
				<%@ include file="/front-end/member/member_bar.file"%>
				<%

  ProductVO ProductVO = (ProductVO) request.getAttribute("ProductVO"); //ProductServlet.java(Concroller), 存入req的ProductVO物件
  ProductOrderService productOrderSvc = new ProductOrderService();
	List<ProductOrderVO> list = productOrderSvc.findByMemberKey(memberVO.getMember_no());
	System.out.println(12345);
	for (ProductOrderVO xxx:list){
		System.out.println(xxx.getOrder_no());
		
	}
	System.out.println(90);
	pageContext.setAttribute("list", list);
	System.out.println(92);
	ProductOrderVO productorderVO= productOrderSvc.findByMemberOrderComplete(memberVO.getMember_no());
	System.out.println(94);
	if(productorderVO !=null){
	Integer order_status = (Integer)productorderVO.getOrder_status();
	}
	System.out.println(95);
%> 
				<div class="col-md-8">
					<div class="category-search-filter">
						<div class="row">
							<div class="col-md-6">
								<h5><b>購買紀錄</b></h5>
							</div>
							
						</div>
					</div>
					<div class="product-grid-list">
						<div class="row mt-30">				 
							<div class="col-lg-12">
								<!-- product card -->
								<div class="product-item bg-light">
									<div class="card widget">
								<c:forEach var="ProductOrderVO" items="${list}">
									<table class="table">
  <thead>
    <tr>
      <td scope="col">訂單編號</td>
      <td scope="col">訂單狀態</td>
      <td scope="col"></td>

    </tr>
       <tr>
      <td scope="col">${ProductOrderVO.order_no}</td>
      <td scope="col">${product_order.get(ProductOrderVO.order_status.toString())}</td>
      <td scope="col"><a class="btn btn-primary" data-toggle="collapse" href="#A${ProductOrderVO.order_no}" role="button" aria-expanded="false" aria-controls="collapseExample">顯示訂單明細</a></td>

    </tr>
    
  </thead>  
  <tbody>	
			<td  colspan="6">
										<div class="collapse" id="A${ProductOrderVO.order_no}">
				<table align="center" style="border:3px #cccccc solid;" cellpadding="10" border='1'>
					<tr>
						<th>訂單明細編號</th>
						<th>商品編號</th>
						<th>訂單單價</th>
						<th>訂單數量</th>
						<th>為商品評分</th>
					</tr>
				 	<c:forEach var="productOrderDetailVO" items="${productOrderDetailSvc.getOneProductOrderDetail(ProductOrderVO.order_no)}">

					<tr>
			               <td>${productSvc.getOneProduct(productOrderDetailVO.product_no).product_name} </td>
			               <td>${productOrderDetailVO.product_no}</td>
			               <td>${productOrderDetailVO.detail_unit_price}</td>
			               <td>${productOrderDetailVO.detail_qty}</td>
			               
							<td>
							<c:if test="${(ProductOrderVO.order_status)==3}">
							<input type="button" id="commentbutton" class="btn btn-primary" data-toggle="modal" data-target="#basicModal" value="我要評分" onclick="location.href='<%=request.getContextPath()%>/ProductOrder/ProductOrderServlet?order_no=${productOrderDetailVO.order_no}&product_no=${productOrderDetailVO.product_no}&action=showWindow'" />
							</c:if>
							</td>
							
							  </tr>
							
			     
					</c:forEach>
					
				</table>
			</div>
			</td>
										  </tbody>
						</table>																						
						</c:forEach>
						</div>
						</div>
							</div>
							</div>
							</div>	
			</div>
			<!--  -->
		</div>
	</div>
	</div>
	
	
	
	
	
<%-- 		--${openModal}-- --%>
		<c:if test="${openModal!=null}">
<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	 <div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel"></h3>
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
		<table >
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

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>

</body>

 <script>  
 		$("#sendComment").click(function(){
 			$.ajax({
				url: "<%= request.getContextPath()%>/product/product.do",
				type: "POST",
 				data:{
					"action" : "updatestar",
					"member_no" : "<%=memberVO.getMember_no()%>",
 					"product_no" : $(this).prev("#product_no").val(),
					"product_total_evaluation" : $(this).parent().prev().find("input[class='star']:checked").val(),
//  					"product_people_of_evaluation" : $("#product_people_of_evaluation").val()
					"comment_product_content": $(this).parent().prev().find("#comment_product_content").val()
			},
 			success:function(){
 			   swal("謝謝!","已完成評論", "success", {button: "完成"});
 	   	}
		});
//  			alert($(this).parent().prev().find("#comment_product_content").val());
		});
 		
 		
 		$("#basicModal").modal({show: true});
 		function showModal() {
 		    $('.btn-primary').modal('show'); 
 		}
 		
//  		if(${productorderVO.order_status}=='3'){
// 			　document.getElementById('commentbutton').disabled=false;　// 變更欄位為可用
// 			　}else{
// 			　document.getElementById('commentbutton').disabled=true;　// 變更欄位為禁用
// 			　}
 		
 		
 		
 	</script> 
</html>