<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.system_news.model.*"%>
<%@ page import="com.toolclass.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<!-- 新版bootstramp 看情況或許可以放 -->
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/coach/coach_center_style.css">

<title>系統訊息</title>
</head>

<body>

	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>


	<div class="container">

			<!-- coach center bar -->
			<%@ include file="/front-end/coach/coach_bar.file"%>

			<%
				SystemNewsVO systemNewsVO = new SystemNewsVO();
				SystemNewsService systemNewsSvc = new SystemNewsService();
				List<SystemNewsVO> list = systemNewsSvc.findOneCoachAllSystemNews(new String(coachVO.getCoach_no()));
				pageContext.setAttribute("list", list);
			%>
			<div class="col-md-8">
				<br>
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item active" aria-current="page">系統訊息</li>
					</ol>
				</nav>

				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<%@ include file="page1.file"%>
				<br> <br>
				<ul class="list-unstyled">
							<c:forEach var="systemNewsVO" items="${list }"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

						<li class="media "><img
								<c:set value="${systemNewsVO.system_time}" var="dateString" />
								<fmt:parseDate value="${dateString}" var="dateObject"
									pattern="yyyy-MM-dd HH:mm:ss" />
								src="<%=request.getContextPath()%>/tool/images/message1.png"
								class="mr-3" alt="..." style="width: 135px; height: 135px;">
								
								<div class="media-body">
											<p>${systemNewsVO.system_content }</p>
											<span style="color: gray;">訊息編號 :
												${systemNewsVO.system_news_no }</span>
<!-- 											<button type="button" -->
<!-- 												style="border: none; color: gray; background-color: transparent;">更多 -->
<!-- 												..</button> -->
											<br>
											<c:if test="${systemNewsVO.member_no != null }">
														發送自: ${ systemNewsVO.member_no} 
<!-- 														<button type="button" -->
<!-- 													style="border: none; color: gray; background-color: transparent;">回覆</button> -->
												<br>
											</c:if>
											<span class="mt-0 mb-1" style="color: gray;"> <b>訊息發送時間
													: ${dateObject }</b>
											</span> <br>
											<form method="post"
												action="<%=request.getContextPath()%>/systemNews.do"
												name="delete_news">
												<input type="hidden" name="system_news_no"
													value="${systemNewsVO.system_news_no }"> <input
													type="hidden" name="action" value="deleteOneSystemNews">
												<input type="hidden" name="coach_news" value="coach_news">
												<button type="submit" class="btn btn-outline-info ">刪除</button>
											</form>

								</div>
						</li>
						<br>
						<br>
					</c:forEach>
				</ul>

				<%@ include file="page2.file"%>

				<br> <br>
			</div>	<!-- row -->
	</div>
				<!-- footer 放body尾 -->
				<%@ include file="/front-end/gym_index/front-end-footer.file"%>
</body>

</html>