<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>


<% 

// 	Map<String,String> map = (HashMap<String,String>) request.getAttribute("commentMap");
// 	String order_no = map.get("order_no");
// 	String memberNo = map.get("memberNo");
// 	String productNo = map.get("productNo");
	
	String order_no="";
	String product_no = "";
	List<String> starlist = (ArrayList<String>) session.getAttribute("commentList");
	for( int i =0; i<starlist.size();i++){
		
		if (i==0) {
			order_no = starlist.get(i);
			System.out.println(order_no);
			}else{ 
				product_no = starlist.get(i);
				System.out.println(product_no);
				}
	}
	
	pageContext.setAttribute("product_no",product_no);
	System.out.println("xxxpageContext");
%>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/starstyle.css">
<meta charset="BIG5">
<style>
	
</style>

<title>Insert Product_Comment</title>
</head>
<body>
		
		<table style="border:1px black solid;width:95%;">
			<tr>
				<td>
					
				<img style="width: 150px; height: 150px;" src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=<%=product_no%>">
 				</td>
 				
			</tr>
			<tr>${productSvc.getOneProduct(product_no).product_name}</tr>
			<tr>
				
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
		</table>
			<input type="hidden" id="order_no" name="order_no" value="<%=order_no%>" />
			<input type="hidden" id="product_no" name="product_no" value="<%=product_no%>" />
			<input type="hidden" id="product_average_evaluation" name="product_average_evaluation" value="${productSvc.getOneProduct(product_no).product_average_evaluation}" />
			<input type="hidden" id="product_people_of_evaluation" name="product_people_of_evaluation" value="${productSvc.getOneProduct(product_no).product_people_of_evaluation}" />
			
			<input type="hidden" name="action" value="addComment">
			<input type="submit" value="送出評論" id="sendComment" />

 		<script>  
 		$("#sendComment").click(function(){
 			$.ajax({
				url: "<%= request.getContextPath()%>/product/product.do",
				type: "POST",
 				data:{
					"action" : "updatestar",
 					"product_no" : $("#product_no").val(),
					"product_total_evaluation" : $(this).parent().prev().find("input[class='star']:checked").val(),
					"product_average_evaluation" : $("#product_average_evaluation").val(),
 					"product_people_of_evaluation" : $("#product_people_of_evaluation").val()
			}
		});
		});
 	</script> 
</body>
</html>