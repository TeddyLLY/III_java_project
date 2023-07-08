<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	CoachService coachService = new CoachService();
	List<CoachVO> coachList = new ArrayList<>();
	coachList = coachService.findAllCoachDesc();
%>
<jsp:useBean id="productSvc" scope="session" class="com.product.model.ProductService" />
<!DOCTYPE HTML>
<html>
<head>
<title>Workout Anywhere</title>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<!-- grid-slider -->
<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="js/jquery.contentcarousel.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script src="./js/vendor/modernizr.custom.min.js"></script>
<script src="./js/vendor/jquery-1.10.2.min.js"></script>
<script src="./js/vendor/jquery-ui-1.10.3.custom.min.js"></script>
<script src="./js/main.js"></script>
<!-- //grid-slider -->
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1200);
		});
	});
</script>

<!-- old bootstrap for modal -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- introduction -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/show_coach.css">
<meta name="referrer" content="never">
<!-- introduction -->
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="covervid.min.js"></script>
<style>
/* Update this */
video {
	position: fixed;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: -1;
	object-position: center;
	object-fit: cover;
}

.header {
	height: 500px;
}

.slideshow-slides {
	opacity: 0.4;
}

.menu {
	opacity: 0.9;
}

.footer-bottom {
	opacity: 0.9;
}
.team-title span {
	color:red;
}
.team-title h5 {;
	justify-content:center
}
.content_left_text p {
	color:#8C8D95;
}
.team-title {
	text-align: center; display:block;
}
.xxx{
 text-align: center;
}
</style>
</head>
<body>
	<!-- start header_top -->
	<div class="header">

		<div class="slideshow">
			<div class="slideshow-slides ">
				<video autoplay muted loop
					src="<%=request.getContextPath()%>/tool/vedio/bg.mp4">
				</video>
			</div>
		</div>
		<div class="header-text " style="left: 27%; bottom:10%; top:-100%">
			<h1>完美健身</h1>
			<h3>　　最佳選擇的網站</h3>
			<h6><br><br><br>放棄可以找到一萬個理由，堅持只需要一個信念</h6>
		</div>

	</div>
	<br><br><br>
	<br><br><br>
	<br><br><br>
	<!-- nav_bar 放body頭 -->

	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<!-- 圖片 -->
	<div class="container">
		<!-- start content-top -->
		<div class="row content-top">
			<div class="col-md-5"></div>
			<div class="col-md-7 content_left_text">
				<h3>Workout Anywhere</h3>
				<p>每個胖子身體裡，都有一個肌肉男，你要永遠囚禁他嗎?</p>
			</div>
		</div>
	</div>
	<!-- 圖片end -->

	<div class="container">
		<br> <br>
		<div class="row content-middle">
			<!-- start content-middle -->
			<div class="col-md-4">
				<a href="pricing.html">
					<div class="view view-fifth">
						<img src="images/pic3.jpg" class="img-responsive" alt="" />
						<div class="mask">
							<div class="info">觀看直播</div>
						</div>
					</div>
					<ul class="spinning">
						<li class="live">直播 <span class="m_1">飛輪</span></li>
						<li class="room">直播間 2</li>
						<div class="clear"></div>
					</ul>
				</a>
			</div>
			<div class="col-md-4">
				<a href="pricing.html">
					<div class="view view-fifth">
						<img src="images/pic2.jpg" class="img-responsive" alt="" />
						<div class="mask">
							<div class="info">觀看直播</div>
						</div>
					</div>
					<ul class="spinning">
						<li class="live">直播 <span class="m_1">健身</span></li>
						<li class="room">直播間 1</li>
						<div class="clear"></div>
					</ul>
				</a>
			</div>
			<div class="col-md-4">
				<a href="pricing.html">
					<div class="view view-fifth">
						<img src="images/pic1.jpg" class="img-responsive" alt="" />
						<div class="mask">
							<div class="info">觀看直播</div>
						</div>
					</div>
					<ul class="spinning">
						<li class="live">直播 <span class="m_1">瑜珈</span></li>
						<li class="room">直播間 4</li>
						<div class="clear"></div>
					</ul>
				</a>
			</div>
			<div class="clear"></div>
		</div>
		<!-- end content-middle -->

		<!-- lesson -->
		<div class="row about">
			<div class="col-md-8">
				<div class="classes">
					<div class="cardio_list">
						<div class="cardio_sublist">
							<ul class="cardio">
								<li><i class="clock"> </i><span>有氧運動</span></li>
							</ul>
							<div class="social-media">
								<ul>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="timetable"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="Send to"><a href="#" target="_blank">
										</a> </span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="like it"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="share"><a href="#" target="_blank"> </a></span></li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="cardio_sublist">
							<ul class="cardio">
								<li><i class="clock"> </i><span>飛輪</span></li>
							</ul>
							<div class="social-media">
								<ul>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="timetable"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="Send to"><a href="#" target="_blank">
										</a> </span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="like it"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="share"><a href="#" target="_blank"> </a></span></li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="cardio_sublist">
							<ul class="cardio">
								<li><i class="clock"> </i><span>普拉提斯</span></li>
							</ul>
							<div class="social-media">
								<ul>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="timetable"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="Send to"><a href="#" target="_blank">
										</a> </span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="like it"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="share"><a href="#" target="_blank"> </a></span></li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="cardio_sublist">
							<ul class="cardio">
								<li><i class="clock"> </i><span>拳擊</span></li>
							</ul>
							<div class="social-media">
								<ul>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="timetable"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="Send to"><a href="#" target="_blank">
										</a> </span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="like it"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="share"><a href="#" target="_blank"> </a></span></li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="cardio_list1">
						<div class="cardio_sublist">
							<ul class="cardio">
								<li><i class="clock"> </i><span>無氧運動</span></li>
							</ul>
							<div class="social-media">
								<ul>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="timetable"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="Send to"><a href="#" target="_blank">
										</a> </span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="like it"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="share"><a href="#" target="_blank"> </a></span></li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="cardio_sublist">
							<ul class="cardio">
								<li><i class="clock"> </i><span>戰繩</span></li>
							</ul>
							<div class="social-media">
								<ul>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="timetable"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="Send to"><a href="#" target="_blank">
										</a> </span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="like it"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="share"><a href="#" target="_blank"> </a></span></li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="cardio_sublist">
							<ul class="cardio">
								<li><i class="clock"> </i><span>槓鈴</span></li>
							</ul>
							<div class="social-media">
								<ul>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="timetable"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="Send to"><a href="#" target="_blank">
										</a> </span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="like it"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="share"><a href="#" target="_blank"> </a></span></li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
						<div class="cardio_sublist">
							<ul class="cardio">
								<li><i class="clock"> </i><span>瑜珈</span></li>
							</ul>
							<div class="social-media">
								<ul>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="timetable"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="Send to"><a href="#" target="_blank">
										</a> </span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="like it"><a href="#" target="_blank">
										</a></span></li>
									<li><span class="simptip-position-bottom simptip-movable"
										data-tooltip="share"><a href="#" target="_blank"> </a></span></li>
								</ul>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="team-title">
					<h5>所有課程</h5>
					<span>All lession</span>
				</div>
			</div>
			<div class="col-md-4">
				<div class="members">
					<h4 class="m_3">${productSvc.getOneProductName("P002")}</h4>
					<div class="xxx">
						<img class="img-responsive" src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=P002" alt="prewiew" width="120" height="80">
					</div>
					<div class="btn1">
						<button class="add-to-cart" type="submit">Add to cart</button>
					</div>
				</div>
				<div class="team-title">
					<h5>推薦商品</h5>
					<span>Recommended products</span>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<!-- lesson -->
		<!-- shop -->
	</div>

	<!-- 	introduction -->

	<div class="container">
		<br> <br>
		<div class="row">
			<%
				int i = 0;
			%>
			<%
				for (CoachVO coachVO : coachList) {
			%>
			<%
				i++;
					if (i > 3)
						break;
			%>
			<div class="col-md-4 col-sm-4">
				<div class="team-member">
					<div class="team-img">
						<img
							src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=<%=coachVO.getCoach_no()%>"
							style="width: 350px; height: 350px; opacity: 1.0;">
					</div>
					<div class="team-hover">
						<div class="desk">
							<h4 style="color: gray;">教練介紹!</h4>
							<%-- 							<p><%=coachVO.getCoach_introduction()%></p> --%>
								<p>click me</p>
						</div>
						<div class="s-link">
							<a href="<%=request.getContextPath()%>/front-end/coach_no_lock/coach_show.jsp?coach_no=<%=coachVO.getCoach_no()%>"><img
								src="<%=request.getContextPath()%>/tool/images/twitter.png"
								style="height: 42px; width: 42px;"></a>
						</div>
					</div>
				</div>
				<div class="team-title">
					<h5><%=coachVO.getCoach_name()%></h5>
					<span>Coach &amp; best</span>
				</div>
			</div>
			<%
				}
			%>
			<!-- row end -->
		</div>
	</div>
	<!-- 	introduction -->

	<!-- startlesson -->
	<div class="container">
		<div class="row content_middle_bottom">

			 
     <div class="col-md-6">
     	 <div class="events">
     	 	<div class="event-top">
	     	 	<ul class="event1">
	     	 		<h4>26 April, 2014</h4>
	     	 		<img src="images/pic.jpg" alt=""/>
	     	 	</ul>
	     	 	<ul class="event1_text">
	     	 		<span class="m_5">h.12.00-h.13.00</span>
	     	 		<h4>Aerobics</h4>
	     	 		<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit,. </p>
	     	 		<div class="btn2">
					   <a href="#">Reservation</a>
					</div>
	     	 	</ul>
     	 		<div class="clear"></div>
     	 	</div>
     	 	<div class="event-bottom">
	     	 	<ul class="event1">
	     	 		<h4>26 April, 2014</h4>
	     	 		<img src="images/pic.jpg" alt=""/>
	     	 	</ul>
	     	 	<ul class="event1_text">
	     	 		<span class="m_5">h.12.00-h.13.00</span>
	     	 		<h4>Spinning</h4>
	     	 		<p>Lorem ipsum dolor sit amet. </p>
	     	 		<div class="btn2">
					   <a href="#">Reservation</a>
					</div>
	     	 	</ul>
     	 		<div class="clear"></div>
     	 	</div>
     	 </div>
     	 										<div class="team-title">
													<h5>即將開始課程</h5>
													<span>Comming lession</span>
												</div>
     </div>

     <div class="col-md-6">
     	 <div class="blog_events">
     	 	<ul class="tab-left1">
				<span class="tab1-img"><img src="images/pic7.jpg" alt=""></span>
				<div class="tab-text1">
				 <p><a href="#">nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip</a></p>
				 <span class="m_date">25 April, 2014</span>
				</div>
				<div class="clear"></div>
			</ul>
			<ul class="tab-left1">
				<span class="tab1-img"><img src="images/pic6.jpg" alt=""></span>
				<div class="tab-text1">
				 <p><a href="#">soluta nobis eleifend option congue nihil imperdiet doming id</a></p>
				 <span class="m_date">25 April, 2014</span>
				</div>
				<div class="clear"></div>
			</ul>
			<ul class="tab-last1">
				<span class="tab1-img"><img src="images/pic8.jpg" alt=""></span>
				<div class="tab-text1">
				 <p><a href="#">quod mazim placerat facer possim assum. Typi non habent</a></p>
				 <span class="m_date">25 April, 2014</span>
				</div>
				<div class="clear"></div>
			</ul>
     	 </div>
     	 								<div class="team-title">
											<h5>熱門文章</h5>
											<span>Hot &amp; article </span>
										</div>
     </div>
     <div class="clear"></div>
		</div>
	</div>
	<!-- article -->

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>

</body>


</html>