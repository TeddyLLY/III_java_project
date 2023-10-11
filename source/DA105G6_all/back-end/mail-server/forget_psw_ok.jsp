<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<link rel="stylesheet" type="text/css" href="coach_center_style.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/in/css/bg.css">

<title>ok</title>
</head>

<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="container" align="center">
				<br><br>
		<div class="row ">
			<div class="jumbotron col-md-12">
				<form>
					<h3 class="display-5">新的密碼已傳送至您註冊的email</h3>
					<p class="lead">請利用新密碼登入</p>
					<hr class="my-4">
					<p></p>
					<input
						type="button"
						onclick="location.href='<%=request.getContextPath()%>/front-end/gym_index/index.jsp'"
						class="btn btn-outline-info btn-lg" value="確認">
				</form>
			</div>
			</div>
		</div>
	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>
</body>

</html>