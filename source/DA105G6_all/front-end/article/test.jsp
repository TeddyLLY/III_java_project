<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<!DOCTYPE HTML>
<html>
<head>
<link href="https://i2.bahamut.com.tw/css/forum.css?v=1582009491"
	rel="stylesheet" type="text/css">
<title>Free Gym Website Template | Blog :: w3layouts</title>
<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<style>
#content {
	height: 150px;
	width: 365px;
	overflow: hidden;
	text-overflow: ellipsis;
	border: 1px #666666 solid;
	white-space: nowrap;
	border: 0px;
}

}
.about_banner_img {
	margin: 0px;
}

.box {
	width: 300px;
	padding: 10px;
	height: 100px;
	overflow: hidden;
}

.ellipsis {
	display: -webkit-box;
	margin-bottom: 10px;
	color: rgba(26, 26, 26, 0.6);
	font-size: 13px;
	line-height: 1.5;
	max-height: 3em;
	text-align: left;
	overflow: hidden;
	text-overflow: ellipsis;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}
.b-list__head{
background-color:#72D0F4;
height:40px;
}
</style>

<!-- metadata css js header function include -->
<%@ include file="/front-end/gym_index/front-end-head.file"%>

<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>

<script src="js/jquery.min.js"></script>
<!-- grid-slider -->
<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="js/jquery.contentcarousel.js"></script>
<!-- //grid-slider -->

	<!-- old bootstrap for modal -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	
	<!-- end menu -->
	<div class="main">
		<div class="about_banner_img">
			<img src="<%=request.getContextPath()%>/front-end/gym_index/images/blog_img1.jpg" class="img-responsive" alt=""
				style="height: 100%; width: 100%; object-fit: contain" />
		</div>
		<div class="about_banner_wrap">
			<h1 class="m_11">討論區</h1>
		</div>
		<div class="border"></div>
		<div class="container">
		<br>
							
			<div class="row single-top" style="padding:0px">
				<div class="col-md-9">
					<table class="b-list">
						<tr class="b-list__head">

							<td>文章類別</td>
							<td colspan="2">文章標題</td>
							<td>回覆 / 觀看</td>
							<td>發文時間/作者</td>
						</tr>
					
						<c:forEach var="articleVO" items="${list}" >
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/article/article.do">
								<tr class="b-list__row b-list-item b-imglist-item" id="in">
									<td class="b-list__summary" style="padding-left: 0px;">

										<p class="b-list__summary__sort">
											<a href="B.php?bsn=23805&subbsn=1" data-subbsn="1">${articleVO.article_glass}</a>
										</p> 

									</td>
									<td><img src="<%=request.getContextPath() %>/DBGifReader3?article_no=${articleVO.article_no}"style="width:90px;height:auto;"></td>
									<td class="b-list__main">

										<div class="b-list__img">
											<canvas width="120" height="25" data-image="1" data-text="問題"></canvas>
										</div>

										<div class="b-list__tile">

											<p data-gtm="B頁文章列表-縮圖" class="b-list__main__title">${articleVO.article_title}</p>
											<div style="width:120px;height:25px;" data-image="1" data-text="問題">
											<input type="submit" value="查看"> <input type="hidden" name="article_no" value="${articleVO.article_no}"> <input type="hidden" name="action" value="getOne_For_Display">
											</div>
										</div>

									</td>
									<td class="b-list__count">
										<p class="b-list__count__number">
											<span title="回覆">${articleVO.article_replies}/</span> <span
												title="人氣">${articleVO.article_visitors}</span>
										</p>
										<p class="b-list__count__user">
											<a href="https://home.gamer.com.tw/b120523855g"
												target="_blank"></a>
										</p>
									</td>
									<td class="b-list__time">
										<p class="b-list__time__edittime">
											${articleVO.article_post_time}
										</p>
										<p class="b-list__count__user" style="color:#72D0F4;">
											${(articleVO.member_no == null) ? articleVO.coach_no : articleVO.member_no}
										</p>
									</td>
								</tr>
							</FORM>
						</c:forEach>
					</table>
					
					<div style="align:center;">
						<button ><a href='<%=request.getContextPath()%>/front-end/article/NewAddArticle.jsp'>發表文章</a></button><br>
					</div>

				</div>
				<div class="col-md-3 ">
				
					<div class="course_demo">
						<ul id="flexiselDemo3">
							<li><img src="images/pic4.jpg" />
								<div class="desc">
									<h3>
										Lorem Ipsum<br> <span class="m_text">Spinning</span>
									</h3>
									<p>
										Lorem ipsum dolor<br> sit amet, consectetuer.
									</p>
									<div class="coursel_list">
										<i class="heart1"> </i> <i class="like"> </i>
									</div>
									<div class="coursel_list1">
										<i class="twt"> </i> <i class="fb"> </i>
									</div>
									<div class="clear"></div>
								</div></li>
							<li><img src="images/pic5.jpg" />
								<div class="desc">
									<h3>
										Lorem Ipsum<br> <span class="m_text">Kik Boxing</span>
									</h3>
									<p>
										Lorem ipsum dolor<br> sit amet, consectetuer.
									</p>
									<div class="coursel_list">
										<i class="heart2"> </i> <i class="like1"> </i>
									</div>
									<div class="coursel_list1">
										<i class="twt"> </i> <i class="fb"> </i>
									</div>
									<div class="clear"></div>
								</div></li>
							<li><img src="images/pic4.jpg" />
								<div class="desc">
									<h3>
										Lorem Ipsum<br> <span class="m_text">Spinning</span>
									</h3>
									<p>
										Lorem ipsum dolor<br> sit amet, consectetuer.
									</p>
									<div class="coursel_list">
										<i class="heart2"> </i> <i class="like1"> </i>
									</div>
									<div class="coursel_list1">
										<i class="twt"> </i> <i class="fb"> </i>
									</div>
									<div class="clear"></div>
								</div></li>
							<li><img src="images/pic5.jpg" />
								<div class="desc">
									<h3>
										Lorem Ipsum<br> <span class="m_text">Kik Boxing</span>
									</h3>
									<p>
										Lorem ipsum dolor<br> sit amet, consectetuer.
									</p>
									<div class="coursel_list">
										<i class="heart2"> </i> <i class="like1"> </i>
									</div>
									<div class="coursel_list1">
										<i class="twt"> </i> <i class="fb"> </i>
									</div>
									<div class="clear"></div>
								</div></li>
							<li><img src="images/pic4.jpg" />
								<div class="desc">
									<h3>
										Lorem Ipsum<br> <span class="m_text">Spinning</span>
									</h3>
									<p>
										Lorem ipsum dolor<br> sit amet, consectetuer.
									</p>
									<div class="coursel_list">
										<i class="heart2"> </i> <i class="like1"> </i>
									</div>
									<div class="coursel_list1">
										<i class="twt"> </i> <i class="fb"> </i>
									</div>
									<div class="clear"></div>
								</div></li>
						</ul>

						<script type="text/javascript" src="js/jquery.flexisel.js"></script>
					</div>
					<h3 class="m_14">商品推薦</h3>
					<div class="members">
						<h4 class="m_3">25% Discount</h4>
						<p>
							Discount on services and <br>treatments at the GymBase for<br>
							all membership cards holders.
						</p>
						<div class="btn1">
							<a href="#">More</a>
						</div>
					</div>
					
						<h4>文章分類</h4>
					<ul class="archive-list">
						<li><a href="#">閒聊</a></li>
						<li><a href="#">健身</a></li>
						<li><a href="#">2012</a></li>
					</ul>
					<h3 class="m_15">討論區精選文章</h3>
					<ul class="partner_blog">
						<li><img src="images/p6.png" alt="" /></li>
						<li><img src="images/p5.png" alt="" /></li>
						<li><img src="images/p4.png" alt="" /></li>
						<li><img src="images/p3.png" alt="" /></li>
						<li><img src="images/p2.png" alt="" /></li>
						<li><img src="images/p1.png" alt="" /></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>

</body>
<script type="text/javascript">
	$(window).load(function() {
		$("#flexiselDemo3").flexisel({
			visibleItems : 4,
			animationSpeed : 1000,
			autoPlay : true,
			autoPlaySpeed : 3000,
			pauseOnHover : true,
			enableResponsiveBreakpoints : true,
			responsiveBreakpoints : {
				portrait : {
					changePoint : 480,
					visibleItems : 1
				},
				landscape : {
					changePoint : 640,
					visibleItems : 2
				},
				tablet : {
					changePoint : 768,
					visibleItems : 2
				}
			}
		});

	});
</script>
</html>