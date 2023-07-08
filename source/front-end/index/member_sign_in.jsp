<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.toolclass.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/in/css/bg.css">

<title>註冊會員</title>
</head>

<body>

	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>


	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">註冊會員</h1>
			<p class="lead" style="font-size: 15px; color: gray;">加入我們以使用最新功能</p>
		</div>
	</div>

	<%-- 錯誤表列 --%>
	<br>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="errorMsgs" items="${errorMsgs}">
				<li style="color: red">${errorMsgs}</li>
			</c:forEach>
		</ul>
	</c:if>


	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<br>
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/member.do"
					name="form1" enctype="multipart/form-data">
					<div class="coach_email" style="margin: 0 auto;">
						<label for="exampleInputPassword1">會員帳號/電子郵件</label> <small
							id="Help" class="form-text text-muted">請以半形輸入，e-mail不能重複註冊。</small>
						<input type="text" class="form-control" id="member_account"
							name="member_account"
							value="<%=(memberVO != null) ? memberVO.getMember_account() : ""%>"
							placeholder="Enter email">
<!-- 						<button type="button" class="btn btn-outline-info">檢查</button> -->
					</div>
					<div class="form-group">
						<br> <label for="exampleInputPassword1">姓名</label>
						 <small id="Help" class="form-text text-muted">Name</small>
							 <input type="text" class="form-control" id="member_name" name="member_name"
							value="<%=(memberVO != null) ? memberVO.getMember_name() : ""%>"
							placeholder="name">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">密碼</label> <small id="Help"
							class="form-text text-muted">Password</small> <input
							name="member_password" value=""
							type="password" class="form-control" id="member_password"
							aria-describedby="Enter name" placeholder="passward">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">密碼確認</label> <small id="Help"
							class="form-text text-muted"> Comfirm Password</small>
							 <input type="password" class="form-control"  value=""
							id="member_psw_check" name="member_psw_check" placeholder="password">
					</div>

					性別 <small id="passwordHelp" class="form-text text-muted">
						Gender</small><br>

					<div class="form-check-inline">
						<input class="form-check-input" type="radio" name="member_sex"
							value="女" id="exampleRadios2" value="option2"
							<%=(memberVO != null) ? ((memberVO.getMember_sex()).equals("女")) ? "checked" : "" : ""%> />
						<label class="form-check-label" for="exampleRadios2"> 女<br></label>
					</div>
					<div class="form-check-inline">
						<input class="form-check-input" type="radio" name="member_sex"
							value="男" id="exampleRadios1" value="option1"
							<%=(memberVO != null) ? ((memberVO.getMember_sex()).equals("男")) ? "checked" : "" : "checked"%> />
						<label class="form-check-label" for="exampleRadios1"> 男 </label>
					</div>
					<br> <br> <br> 
<%-- 					<img src="<%=request.getContextPath()%>/tool/images/驗證碼.jpg" style="height: auto; width: 320px;"> --%>
						 <br> <br> 
						<input type="hidden" name="action" value="memberInsert">
					<button type="submit" class="btn btn-info btn-lg btn-block">送出</button>
					<input type="button" class="magic btn btn-outline-success" value="神奇小按鈕">
				</form>
			</div>

			<div class="col-md-7">
				<img
					src="<%=request.getContextPath()%>/tool/images/1544429795-BSuHawWikx.jpg"
					style="height: 800px">
			</div>
		</div>
	</div>

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>

</body>

	<script>
		$(".magic").click(function(){
			$("#member_psw_check").val('123');
			$("#member_password").val('123');
			$("#member_name").val('楊過');
			$("#member_account").val('yangood@gmail.com');
		})
	</script>

</html>