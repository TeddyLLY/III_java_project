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
<title>教練驗證</title>
</head>

<body>

	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if><br><br>
				<div class="alert alert-success" role="alert">
					<h4 class="alert-heading">Well done!</h4>
					<p>
						親愛的教練您好:註冊信箱信件:請點擊按鈕按鈕已獲得驗證信<br> 請至您註冊的信箱內收取驗證信,並且填寫驗證碼<br>
					</p>
					<hr>
					<form method="post"
						action="<%=request.getContextPath()%>/MailServer.do">
						<p class="mb-0">
							<input type="hidden" name="action" value="sendOneUserMail">
							<button type="submit" class="btn btn-success" id="subit">發送</button>
							Please check your email
						</p>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<h5 class="card-header">教練驗證</h5>
					<div class="card-body">

						<p class="card-text"></p>


						<form method="post"
							action="<%=request.getContextPath()%>/MailServer.do">
							<div class="form-group">
								<small id="Help" class="form-text text-muted">
									&nbsp輸入驗證碼</small> <input type="text" class="form-control"
									id="exampleInputEmail1" aria-describedby="emailHelp"
									name="coach_passRandom" placeholder="ex : 857102">

							</div>
							<a
								href="<%=request.getContextPath()%>/front-end/gym_index/index.jsp"
								class="btn btn-outline-success">取消</a> 
								<input type="hidden" name="action" value="coach_passRandom"> 
								<input type="submit" class=" btn btn-info" value="確認" >
						</form>
					</div>
				</div>

			</div>

		</div>
	</div>

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>


</body>

<script>
	document.getElementById("subit").addEventListener("click",function(){
		  swal({
			  title: "Good job!",
			  text: "5秒后送出驗證信至您的信箱。",
			  timer: 5000,
			  showConfirmButton: false
			});
		});
</script>

</html>