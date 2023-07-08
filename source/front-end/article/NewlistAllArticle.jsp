<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<!DOCTYPE HTML>
<html>
<head>
<!-- metadata css js header function include -->
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<!-- 新版bootstramp 看情況或許可以放 -->
	<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
	
<link href="https://i2.bahamut.com.tw/css/forum.css?v=1582009491" rel="stylesheet" type="text/css">
<title>Free Gym Website Template | Blog :: w3layouts</title>
<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll();
	pageContext.setAttribute("list", list);
%>
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

<link href="<%=request.getContextPath() %>/front-end/article/css/block.css" rel="stylesheet" type="text/css">

<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	
	<!-- end menu -->
	<div class="main">
		<div class="about_banner_img">
			<img src="<%=request.getContextPath()%>/front-end/gym_index/images/blog_img1.jpg" class="img-responsive" alt="" style="height: 100%; width: 100%; object-fit: contain" />
		</div>
		<div class="about_banner_wrap">
			<h1 class="m_11">Blog</h1>
		</div>
		<div class="border"></div>
		
		<div class="container-fluid">
							
			<div class="row single-top" style="padding:0px">
				<div class="col-md-8">
				<br>
				<button style="margin-left:500px;"><a href='<%=request.getContextPath()%>/front-end/article/NewAddArticle.jsp'>發表文章</a></button><br>
						
	<c:forEach var="articleVO" items="${list}" >
<!-- 	============================= -->
<div class="panel box-panel img-opac text-justify scale1">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do">
		<img src="<%=request.getContextPath() %>/DBGifReader3?article_no=${articleVO.article_no}" style="width:100px; height:100px;"> 
							<span>${articleVO.article_title} <br></span>
  <h4>
    ${articleVO.article_glass}
    <input type="submit" value="查看"> 
  	<input type="hidden" name="article_no" value="${articleVO.article_no}"> 
	<input type="hidden" name="action" value="getOne_For_Display">
  </h4>
  <br><br> <br><br>
		<p style="font-size:0.5px;">回覆  : ${articleVO.article_replies} 觀看 : ${articleVO.article_visitors}    發文時間 :  ${articleVO.article_post_time} 作者 : <span style="color:blue">${(articleVO.member_no == null) ? articleVO.coach_no : articleVO.member_no}</span> </p>
	
  
	
</div>
		</FORM>
<br>
<!-- == -->
						</c:forEach>
				</div>

<!--            col-4				 -->
				<div class="col-md-4 ">
					
					<ul class="archive-list">
						<h4>文章分類</h4>
						<li><a href="#">2014</a></li>
						<li><a href="#">2013</a></li>
						<li><a href="#">2012</a></li>
					</ul>
					
					<h3 class="m_15">文章照片</h3>
					<ul class="partner_blog">
						<li><img src="images/p6.png" alt="" /></li>
						<li><img src="images/p5.png" alt="" /></li>
						<li><img src="images/p4.png" alt="" /></li>
						<li><img src="images/p3.png" alt="" /></li>
						<li><img src="images/p2.png" alt="" /></li>
						<li><img src="images/p1.png" alt="" /></li>
						<div class="clear"></div>
					</ul>
					
					<ul class="recent-list">
						<h4>Recent Posts</h4>
						<li><a href="#">aliquam erat volutpat</a><br> <span>25
								April 2014</span></li>
						<li><a href="#">aliquam erat volutpat</a><br> <span>25
								April 2014</span></li>
						<li><a href="#">aliquam erat volutpat</a><br> <span>25
								April 2014</span></li>
						<li><a href="#">aliquam erat volutpat</a><br> <span>25
								April 2014</span></li>
					</ul>
	
					<div class="course_demo">

						<script type="text/javascript" src="js/jquery.flexisel.js"></script>
					</div>
					<h3 class="m_14">商品推薦</h3>
					<div class="members">
						<h4 class="m_3">25% Discount of </h4>
						<p>
							Discount <br>
							all membership cards holders.
						</p>
						<div class="btn1">
							<a href="#">More</a>
						</div>
					</div>
			
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