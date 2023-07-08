<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>
<%@ page import="javax.activation.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.toolclass.*"%>
<%
	CoachVO coachVO = (CoachVO) session.getAttribute("coachVO");
%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/in/css/bg.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>coachResetPsw</title>
</head>

<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="row text-center">
		<div class="col-md-12">
			<div class="jumbotron jumbotron-fluid">
				<div class="container">
					<h4>忘記密碼?</h4>
					<p class="lead">Please enter your e-mail.</p>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
		
			<form method="post"
				action="<%=request.getContextPath()%>/MailServer.do">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<h2 style="color: #283747;">請輸入教練帳戶資料</h2>
				<br> <br>
				<h3>請輸入 Email</h3>
				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label> <input
						type="email" class="form-control" id="exampleInputEmail1"
						name="coach_account" aria-describedby="emailHelp"
						placeholder="Enter email"> <small id="emailHelp"
						class="form-text text-muted">請輸入您註冊的Email <br>我們將傳送新密碼至您的信箱
					</small>
				</div>
				<div>
					<input type="hidden" name="action" value="CoachNewPsw"> <input
						type="button"
						onclick="location.href='<%=request.getContextPath()%>/front-end/gym_index/index.jsp'"
						class="btn btn-outline-info btn-lg" value="取消"> <input
						type="submit" class="btn btn-info btn-lg" value="確認" id="subit">
				</div>
			</form>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div>
				<br> <br> <img
					src="<%=request.getContextPath()%>/tool/images/eo7DM9N.jpg"
					style="height: 300px"> <img
					src="<%=request.getContextPath()%>/tool/images/1200px-Me_gusta_perverted_by_rober_raik-d4clzqf.webp"
					style="height: 100px">
			</div>
		</div>
	</div>

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>

</body>

<script>
	document.getElementById("subit").addEventListener("click",function(){
		swal("成功！", "已送出新密碼！","success")
		});
</script>

</html>