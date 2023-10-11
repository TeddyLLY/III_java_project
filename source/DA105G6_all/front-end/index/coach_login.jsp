<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/fonts/iconic/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/index/in/css/main.css">
<!--===============================================================================================-->
	<title>教練登入</title>
</head>
<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('<%= request.getContextPath() %>/front-end/index/in/images/bg-01.jpg');">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="<%=request.getContextPath()%>/coach.do"
method="post" enctype="multipart/form-data">
		<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
		 <div class="container">
			<!-- start content-top -->
			<div class="row content-top">
				 <div class="col-md-5">
				  <img src="<%=request.getContextPath()%>/front-end/gym_index/images/pic.png" class="img-responsive" alt=""/> 
			     </div>
				 <div class="col-md-7 content_left_text">
				   <h3>Workout Anywhere</h3>
				 </div>
            </div>
		 </div>
	
					<span class="login100-form-title p-b-34 p-t-27">
						教練登入
					</span>
					
					<div class="wrap-input100 validate-input" data-validate = "Enter username">
																<!-- // -->
						 <input type="email" class="input100" id="member_email" 
							 placeholder=" email" name="coach_account" value="">
						<span class="focus-input100" data-placeholder="&#xf207;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
															<!-- // -->
							<input type="password" class="input100" id="member_psw" aria-describedby="Enter name" placeholder="passward"
										 name="coach_password" value="">
							<span class="focus-input100" data-placeholder="&#xf191;"></span>
					</div>
					<br>
					<div class="container-login100-form-btn">
						<input type="hidden" name="action" value="findOneCoachAccount">
						<button class="login100-form-btn"  type="submit" id="subit">
							送出
						</button>
							<input type="button" class="magic login100-form-btn" value="神奇小按鈕">
					</div>

					<div class="text-center p-t-90">
						<a class="txt1" href="<%=request.getContextPath()%>/back-end/mail-server/coach_forget_psw.jsp">
							忘記密碼?
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
<!--===============================================================================================-->
	<script src="<%= request.getContextPath() %>/front-end/index/in/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/index/in/vendor/animsition/js/animsition.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/index/in/vendor/bootstrap/js/popper.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/index/in/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/index/in/vendor/select2/select2.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/index/in/vendor/daterangepicker/moment.min.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/index/in/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/index/in/vendor/countdowntime/countdowntime.js"></script>
	<script src="<%= request.getContextPath() %>/front-end/index/in/js/main.js"></script>
<!--===============================================================================================-->
<%@ include file="/front-end/gym_index/front-end-footer.file"%>

</body>

<script>
		$(".magic").click(function(){
			$("#member_psw").val('123');
			$("#member_email").val('gcobc31619@gmail.com');
		})
		
</script>

</html>