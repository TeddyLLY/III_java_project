<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.reply_article.model.*"%>
<%@ page import="com.reply_article.controller.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>

<script
	src="${pageContext.request.contextPath}/front-end/ckeditor/ckeditor.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- css js header function include -->
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<!-- 新版bootstramp 看情況或許可以放 -->
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>

<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>

<script
	src="<%=request.getContextPath()%>/front-end/gym_index/js/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<!-- grid-slider -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front-end/gym_index/js/jquery.mousewheel.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front-end/gym_index/js/jquery.contentcarousel.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
	type="text/javascript"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- //grid-slider -->
<style type="text/css">
.gg li {
	margin-right: 5%;
}

.modal-body {
	position: relative;
	padding: 15px;
	width: 100px;
}
</style>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/blog/blog.css">

<title>Free Gym Website Template | Blog_single :: w3layouts</title>

</head>

<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<!-- end menu -->
	<div class="container-fuild">

		<div class="main"></div>

	</div>
	<div class="about_banner_img-fluid">
		<img
			src="<%=request.getContextPath()%>/front-end/gym_index/images/blog_single.jpg"
			class="img-responsive" alt=""
			style="height: 100%; width: 100%; object-fit: contain" />
	</div>
	<div class="about_banner_wrap">
		<h1 class="m_11">Blog</h1>
	</div>
		
		
 <div class="blog-container" style="width: 100rem;">


            <br>
        <div class="blog-body">
            <div class="blog-title">
                <h1><a href="#">New Post</a></h1>
            </div>
            
            <div class="blog-summary">
                <p>
                </p>
            </div>
            	<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/article/article.do"
					name="form1" enctype="multipart/form-data">

					<h3>
						請輸入標題:<input type="TEXT" name="article_title"  id="article_title" size="45"
							class="textarea" />
					</h3>


					<div class="textarea">

						標題圖片:<img id="output" style="height: 100px; width: auto;">
						 <input type="file" accept="image/*" name="article_picture" 
							onchange="loadFile(event)">
							
							 <br>文章內容: <textarea id="ck" name="article_content" id="article_content" > </textarea>
						
						<script>
							CKEDITOR.replace('ck');
						</script>
						文章類別:<font color=red><b>*</b></font> <select size="1"
							name="article_glass">
							<option value="重訓"
								${(articleVO.article_glass=="重訓")?'selected':'' }>重訓</option>
							<option value="有氧"
								${(articleVO.article_glass=="有氧")?'selected':'' }>有氧</option>
							<option value="閒聊"
								${(articleVO.article_glass=="閒聊")?'selected':'' }>閒聊</option>
						</select> 文章狀態:<select size="1" name="article_status">
							<option value="0"
								${(articleVO.article_status=="0")?'selected':'' }>隱藏</option>
							<option value="1"
								${(articleVO.article_status=="1")?'selected':'' }>顯示</option>
						</select> <input type="hidden" name="action" value="insert"> <input
							type="submit" value="送出新增">
							<input type="hidden" name="coach_no" value="${coachVO.coach_no}">
							<input type="hidden" name="member_no" value="${memberVO.member_no }">
					</div>
				</FORM>
				
            <div class="blog-tags">
                <ul>
                    <li><a href="#">design yourself</a></li>
                </ul>
            </div>
        </div>

        <div class="blog-footer">
				<ul class="social_blog">
					<h3>Share</h3>
					<li class="fb"><a href="#"><span> </span></a></li>
					<li class="tw"><a href="#"><span> </span></a></li>
					<li class="google_blog"><a href="#"><span> </span></a></li>
					<li class="linkedin"><a href="#"><span> </span></a></li>
					<li></li>
				</ul>
        </div>
		<br>
    </div>

<!-- 		======== -->

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>
	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">


			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body">
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
						<jsp:include
							page="/front-end/report_article/report_article_page.jsp" />
						<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
					</div>
				</div>
			</div>
		</div>

		<script>
			$("#basicModal").modal({
				show : true
			});
		</script>
	</c:if>
</body>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};

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