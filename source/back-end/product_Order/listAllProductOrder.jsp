<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product_order_detail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ProductOrderService productOrderSvc = new ProductOrderService();
	List<ProductOrderVO> list = productOrderSvc.getAll();
	pageContext.setAttribute("list", list);
// 	for(ProductOrderVO xxx : list) {
		
// 		System.out.println(xxx.getOrder_no());
// 	}
	
// 	ProductOrderDetailService productOrderDetailSvc = new ProductOrderDetailService();
// 	List<ProductOrderDetailVO> detailList = productOrderDetailSvc.getOneProductOrderDetail("20200209-P011");
// 	for (ProductOrderDetailVO aproductorderdetailvo : detailList) {
// 		System.out.println(aproductorderdetailvo.getDetail_qty());
		
// 	}
	
// 	pageContext.setAttribute("detailList", detailList);
// 	String order_no="";
// 	String product_no = "";
// 	List<String> starlist = (ArrayList<String>) session.getAttribute("commentList");
// 	for( int i =0; i<starlist.size();i++){
		
// 		if (i==0) {
// 			order_no = starlist.get(i);
// 			System.out.println(order_no);
// 			}else{ 
// 				product_no = starlist.get(i);
// 				System.out.println(product_no);
// 				}
// 	}
	
// 	pageContext.setAttribute("product_no",product_no);
// 	System.out.println("xxxpageContext");
%>
<jsp:useBean id="productOrderDetailSvc" scope="page" class="com.product_order_detail.model.ProductOrderDetailService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
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
 <title>所有商品資料 - listAllProductOrder.jsp</title>

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
			<th>商品訂單編號</th>
			<th>會員編號</th>
			<th>收件人</th>
			<th>配送地址</th>
			<th>配送日期</th>
			<th>商品訂單狀態</th>
			
		</tr>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="ProductOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>

				<td>${ProductOrderVO.order_no}</td>
				<td>${ProductOrderVO.member_no}</td>
				<td>${ProductOrderVO.order_recipient}</td>
				<td>${ProductOrderVO.order_address}</td>
				<td>${ProductOrderVO.order_date}</td>
				<td>${product_order.get(ProductOrderVO.order_status.toString())}</td>
				
				<td>
						  <a class="btn btn-primary" data-toggle="collapse" href="#A${ProductOrderVO.order_no}" role="button" aria-expanded="false" aria-controls="collapseExample">
						   			 顯示訂單明細
						  </a>
				</td>
			</tr>
			<tr class="collapse" id="A${ProductOrderVO.order_no}">
			<td colspan="6">
				<table>
					<tr>
						<th>訂單明細編號</th>
						<th>商品編號</th>
						<th>訂單單價</th>
						<th>訂單數量</th>
						<th>檢舉商品原因</th>
						<th>為商品評分</th>
					</tr>
					
				 	<c:forEach var="productOrderDetailVO" items="${productOrderDetailSvc.getOneProductOrderDetail(ProductOrderVO.order_no)}">

					<tr>
			               <td>${productSvc.getOneProduct(productOrderDetailVO.product_no).product_name} </td>
			               <td>${productOrderDetailVO.product_no}</td>
			               <td>${productOrderDetailVO.detail_unit_price}</td>
			               <td>${productOrderDetailVO.detail_qty}</td>
			               <td>
					
						<input type="submit" class="submitExample" value="檢舉商品">
						<input type="hidden" id="product_no" name="product_no" value="${productOrderDetailVO.product_no}"> 
						<input type="hidden" name="action" value="report_product">
					
								    <div class="row form-group">
			                           <div class="col col-md-3">
			                           		<label for="auc_ven_intro" class=" form-control-label"></label>
			                           </div>
			                           <div class="col-12 col-md-9">
			                           		<textarea name="report_product_content" rows="9"  class="form-control area"></textarea>
			                           	</div>
			                                
			                         </div>
			                        </div>
							</td>
							<td>
							
							<input type="button" value="我要評分" onclick="location.href='<%=request.getContextPath()%>/ProductOrder/ProductOrderServlet?order_no=${productOrderDetailVO.order_no}&product_no=${productOrderDetailVO.product_no}&action=showWindow'"/>
							
<%-- 					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/ProductOrder/ProductOrderServlet" style="margin-bottom: 0px;"> --%>
<!-- 						<input type="submit" value="我要評分"> -->
<%-- 						<input type="hidden" name="product_no" value="${productOrderDetailVO.product_no}">  --%>
<%-- 						<input type="hidden" name="order_no" value="${productOrderDetailVO.order_no}">  --%>
<!-- 						<input type="hidden" name="action" value="showWindow"> -->
						
<!-- 					</FORM> -->
							</td>
			       </tr>
					</c:forEach>
					
				</table>
			</td>
			</tr>
		</c:forEach>
	</table>


<%@ include file="pages/page2.file"%>



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
		<div>
			<input type="hidden" id="order_no" name="order_no" value="<%=order_no%>" />
			<input type="hidden" id="product_no" name="product_no" value="<%=product_no%>" />
<%-- 			<input type="hidden" id="product_average_evaluation" name="product_average_evaluation" value="${productSvc.getOneProduct(product_no).product_average_evaluation}" /> --%>
			<input type="submit" value="送出評論" id="sendComment" />
		</div>
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
</body>
			<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
		
		</div>
	</div>
</div>
		</c:if>
</body>
<script>
// $("#exampleModal").modal({show: true});
$("#basicModal").modal({show: true});
function showModal() {
    $('.btn-primary').modal('show'); 
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