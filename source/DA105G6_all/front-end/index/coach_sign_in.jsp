<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>

<%
	CoachVO coachVO = (CoachVO) request.getAttribute("coachVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/in/css/bg.css">

<title>註冊教練</title>
</head>

<body>

	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>


	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">註冊教練</h1>
			<p class="lead" style="font-size: 15px; color: gray;">一起加入健身教練的行列吧</p>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-5">
			<br>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/coach.do"
					name="form1" enctype="multipart/form-data">
					<br>
					<div class="coach_email" style="margin: 0 auto;">
						<label for="exampleInputPassword1">教練帳號/電子郵件</label>
						 <small id="Help" class="form-text text-muted">請以半形輸入，e-mail不能重複註冊。</small>
						 
						<input type="email" id="coach_account" class="form-control" name="coach_account" placeholder="Enter email"
							value="<%=(coachVO != null) ? coachVO.getCoach_account() : ""%>">
<!-- 						<button type="button" class="btn btn-outline-info" name="check">檢查</button> -->
					</div>
					<div class="form-group">
						<br> <label for="exampleInputPassword1">姓名</label> <small
							id="Help" class="form-text text-muted">Name</small> <input
							type="text" class="form-control" id="coach_name"
							name="coach_name" placeholder="name"
							value="<%=(coachVO != null) ? coachVO.getCoach_name() : ""%>">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">密碼</label> <small id="Help"
							class="form-text text-muted">Password</small>
							 <input type="password" class="form-control" name="coach_password"
							aria-describedby="Enter name" placeholder="passward" id="coach_password" value="">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">密碼確認</label>
						 <small id="Help" class="form-text text-muted"> Comfirm Password</small> 
							<input type="password" class="form-control" name="coach_psw_check"
							id="coach_psw_check" value = "" placeholder="password">
					</div>
					
					性別 <small id="passwordHelp" class="form-text text-muted">
						Gender</small><br>

					<div class="form-check-inline">
						<input class="form-check-input" type="radio" name="coach_sex"
							value="女" id="exampleRadios2" value="option2"
							<%=(coachVO != null) ? ((coachVO.getCoach_sex()).equals("女")) ? "checked" : "" : ""%> />
						 <label class="form-check-label" for="exampleRadios2"> 女<br></label>
					</div>
					<div class="form-check-inline">
						<input class="form-check-input" type="radio" name="coach_sex"
							value="男" id="exampleRadios1" value="option1" 
							<%=(coachVO != null) ? ((coachVO.getCoach_sex()).equals("男")) ? "checked" : "" : "checked"%> />
						<label class="form-check-label" for="exampleRadios1"> 男 </label>
					</div>


					<br> <br> <br> 
<%-- 					<img src="<%=request.getContextPath()%>/tool/images/驗證碼.jpg" style="height: auto; width: 320px;"> --%>
					 <br> <br>
						<input type="hidden" name="action" value="coachInsert">
					<button type="submit" class="btn btn-info btn-lg btn-block">送出</button>
					<input type="button" class="magic btn btn-outline-success" value="神奇小按鈕">
			</form>
			</div>

			<div class="col-md-7">
				<img src="<%=request.getContextPath()%>/tool/images/0.jfif"
					style="height: auto; weight: auto;">
			</div>
		</div>
	</div>

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>

</body>
	
	<script>
		$(".magic").click(function(){
			$("#coach_psw_check").val('123');
			$("#coach_password").val('123');
			$("#coach_name").val('小龍女');
			$("#coach_account").val('gcobc31619@gmail.com');
		})
	</script>
	
</html>