<%@page import="com.article.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="big5"%>
<!DOCTYPE html>
<html>
<head>
<%
	ArticleVO articleVO=(ArticleVO)request.getAttribute("articleVO");
System.out.println(articleVO.getArticle_no());
%>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="js/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
 <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
 <STYLE>
 textarea{
 width:20%;
 background-color:white;
 }
 
 
 </STYLE>

</head>
<body>
	<div class="main">
		<div class="register-grids">
			<div class="container">
				<form METHOD="post"
					ACTION="<%=request.getContextPath()%>/report_article/report_article.do">

					<div class="clear"></div>
					<div class="register-bottom-grid">
						<br>
						<h3>�п�����|��]</h3>
						<select size="1" name="report_article_reason">
							<option value="�r�S">�r�S</option>
							<option value="���Z">���Z</option>
							<option value="���먥��">���먥��</option>
							<option value="���ꨥ��">���ꨥ��</option>
							<option value="�U���T��">�U���T��</option>
						</select> <br> <br>
						<h3>�Բӻ���</h3>
						<textarea name="report_article_content"></textarea>
					</div>
					<div class="clear"></div>
					<br> <br> <input type="hidden" name="member_no"
						value="M001"> <input type="hidden" name="article_no"
						value="<%=articleVO.getArticle_no()%>"> <input
						type="submit" value="�e�X�|��" > <input type="hidden"
						name="action" value="insert">
				</form>
			</div>
		</div>
	</div>
</body>
<script>


</script>
</html>