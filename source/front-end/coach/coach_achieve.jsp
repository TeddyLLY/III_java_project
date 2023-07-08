<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.toolclass.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/coach/coach_center_style.css">
<title>教練收益</title>
</head>

<body>

	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="container">
		<div class="row">

			<!-- coach center bar -->
			<%@ include file="/front-end/coach/coach_bar.file"%>


			<div class="col-md-8">
				<br>

				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item active" aria-current="page">成就</li>
					</ol>
				</nav>

				<div class="card text-center">
					<div class="card-header">Income</div>
					<div class="card-body">
						<h4 class="card-title" style="color: #19247D;">$${coachVO.coach_income }</h4>
						<p class="card-text">總收入</p>

					</div>
					<div class="card-footer text-muted">1天前更新</div>
				</div>
				<br>
				<br>

				<ul class="list-unstyled">

						<li class="media ">
						<li class="media "><img src="<%=request.getContextPath()%>/tool/images/證照4.JPG"
							class="mr-3" alt="..." style="width: 100px; height: 100px;">
							<div class="media-body">
								<h4>
									<b style="color: #12A012;">教練總評分數</b>
								</h4>
								<p style="color: gray;">
									${coachVO.coach_name }獲得了 ${ coachVO.coach_total_evaluation } 個漢堡
								</p>

							</div></li>
					<br>
					<br>

							<li class="media ">
							<li class="media "><img src="<%=request.getContextPath()%>/tool/images/證照4.JPG"
							class="mr-3" alt="..." style="width: 100px; height: 100px;">
							<div class="media-body">
								<h4>
									<b style="color: #12A012;">滿意度</b>
								</h4>
								<p style="color: gray;">
									${coachVO.coach_name }獲得了 ${ coachVO.coach_average_evaluation } 顆星
								</p>

					<br>
					<br><br>
					

							<li class="media "><img src="<%=request.getContextPath()%>/tool/images/證照4.JPG"
							class="mr-3" alt="..." style="width: 100px; height: 100px;">
							<div class="media-body">
								<h4>
									<b style="color: #12A012;">總評價人數</b>
								</h4>
								<p style="color: gray;">
									共有 ${ coachVO.coach_total_people_evaluation } 按了你的讚
								</p>

							</div></li>
					<br>
					<br><br>
					

				</ul>
				<br>
				</div>
			</div>
		</div>
		
<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>

		
</body>

</html>