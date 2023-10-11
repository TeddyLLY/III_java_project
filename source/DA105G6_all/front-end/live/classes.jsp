<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.live.model.*"%>
<%@ page import="com.coach.model.*"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Free Gym Website Template | Classes :: w3layouts</title>
<%
	LiveService liveSvc = new LiveService();
	List<LiveVO> list = liveSvc.getAll();
	pageContext.setAttribute("list", list);
	ServletContext context=getServletContext();
	String room_no=(String)context.getAttribute("room_no");
	context.setAttribute("room_no", room_no);
	System.out.print(room_no+"47854");
%>
<!-- metadata css js header function include -->
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<!-- 新版bootstramp 看情況或許可以放 -->
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="js/jquery.min.js"></script>
<!-- grid-slider -->
<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="js/jquery.contentcarousel.js"></script>
<!-- //grid-slider -->
<!---calender-style---->
<link rel="stylesheet" href="css/jquery-ui.css" />
<!---//calender-style---->	
<jsp:useBean id="coachSvc" scope="page" class="com.coach.model.CoachService"/>

</head>
<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="clear"></div>
	</div>
	</div>
	<div class="main">
		<div class="about_banner_img">
			<img src="images/class_img.jpg" class="img-responsive" alt="" />
		</div>
		<div class="about_banner_wrap">
			<div class="container">
			<div class="row class_box">
			<div class="col-8"><h1 class="m_11">直播列表</h1></div>
			<a href="<%=request.getContextPath() %>/front-end/live/RTCPeerConnection.jsp#C014" ><button >開啟直播</button></a>
			</div>
			</div>
		</div>
		<div class="border"></div>
		<div class="container">
			<div class="classes_wrapper">
				<div class="row class_box">
				
				<c:if test="${room_no!=null}">
					<div class="col-md-6">
						<div class="class_left">
							<a href="single_class.html"><img src="images/c7.jpg"
								class="img-responsive" alt="" / title="continue"></a>
						</div>
						<div class="class_right">
							<h3>小龍女的直播間</h3>
							<p>${liveVO.live_title}</p>
							<div class="class_img">
								<img src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=${liveVO.coach_no}" alt="" />
								<div class="class_desc">
									<h4>${coachSvc.findOneCoach(liveVO.coach_no).coach_name}</h4>
									<h5></h5>
									<p></p>
								</div>
								<div class="clear"></div>
								<ul class="buttons_class">
									<li class="btn5"><a href="<%=request.getContextPath()%>/front-end/live/RTCPeerConnection.jsp#${room_no}">前往觀看</a></li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					</c:if>
					
			</div>
			
		</div>
		
		
		
			</div>
		</div>
	</div>
	
	
<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
	
</body>
</html>