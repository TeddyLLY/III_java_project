<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.reply_article.model.*"%>
<%@ page import="com.reply_article.controller.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
<jsp:useBean id="articleSvc" scope="page"
	class="com.article.model.ArticleService" />
<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
	request.setAttribute("articleVO", articleVO);
	if (articleVO == null) {
		articleVO = articleSvc.getOneArticle(request.getParameter("article_no"));
	}
	String sweetalert = request.getParameter("sweetalert");
%>
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
	
<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/article/blog/blog.css">

<title>查看文章</title>
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

.textarea {
	display: none;
}
</style>

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

   <svg display="none" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
        <defs>
        <symbol id="icon-bubble" viewBox="0 0 1024 1024">
            <title>bubble</title>
            <path class="path1" d="M512 224c8.832 0 16 7.168 16 16s-7.2 16-16 16c-170.464 0-320 89.728-320 192 0 8.832-7.168 16-16 16s-16-7.168-16-16c0-121.408 161.184-224 352-224zM512 64c-282.784 0-512 171.936-512 384 0 132.064 88.928 248.512 224.256 317.632 0 0.864-0.256 1.44-0.256 2.368 0 57.376-42.848 119.136-61.696 151.552 0.032 0 0.064 0 0.064 0-1.504 3.52-2.368 7.392-2.368 11.456 0 16 12.96 28.992 28.992 28.992 3.008 0 8.288-0.8 8.16-0.448 100-16.384 194.208-108.256 216.096-134.88 31.968 4.704 64.928 7.328 98.752 7.328 282.72 0 512-171.936 512-384s-229.248-384-512-384zM512 768c-29.344 0-59.456-2.24-89.472-6.624-3.104-0.512-6.208-0.672-9.28-0.672-19.008 0-37.216 8.448-49.472 23.36-13.696 16.672-52.672 53.888-98.72 81.248 12.48-28.64 22.24-60.736 22.912-93.824 0.192-2.048 0.288-4.128 0.288-5.888 0-24.064-13.472-46.048-34.88-56.992-118.592-60.544-189.376-157.984-189.376-260.608 0-176.448 200.96-320 448-320 246.976 0 448 143.552 448 320s-200.992 320-448 320z"></path>
        </symbol>
        <symbol id="icon-star" viewBox="0 0 1024 1024">
            <title>star</title>
            <path class="path1" d="M1020.192 401.824c-8.864-25.568-31.616-44.288-59.008-48.352l-266.432-39.616-115.808-240.448c-12.192-25.248-38.272-41.408-66.944-41.408s-54.752 16.16-66.944 41.408l-115.808 240.448-266.464 39.616c-27.36 4.064-50.112 22.784-58.944 48.352-8.8 25.632-2.144 53.856 17.184 73.12l195.264 194.944-45.28 270.432c-4.608 27.232 7.2 54.56 30.336 70.496 12.704 8.736 27.648 13.184 42.592 13.184 12.288 0 24.608-3.008 35.776-8.992l232.288-125.056 232.32 125.056c11.168 5.984 23.488 8.992 35.744 8.992 14.944 0 29.888-4.448 42.624-13.184 23.136-15.936 34.88-43.264 30.304-70.496l-45.312-270.432 195.328-194.944c19.296-19.296 25.92-47.52 17.184-73.12zM754.816 619.616c-16.384 16.32-23.808 39.328-20.064 61.888l45.312 270.432-232.32-124.992c-11.136-6.016-23.424-8.992-35.776-8.992-12.288 0-24.608 3.008-35.744 8.992l-232.32 124.992 45.312-270.432c3.776-22.56-3.648-45.568-20.032-61.888l-195.264-194.944 266.432-39.68c24.352-3.616 45.312-18.848 55.776-40.576l115.872-240.384 115.84 240.416c10.496 21.728 31.424 36.928 55.744 40.576l266.496 39.68-195.264 194.912z"></path>
        </symbol>
        </defs>
        </svg>

    <div class="blog-container">

        <div class="blog-header">
            <div class="blog-cover">
<!--                 <div class="blog-author"> -->
<%--                     <h3><%=(articleVO.getMember_no() == null) ? articleVO.getCoach_no() : articleVO.getMember_no()%></h3> --%>
<!--                 </div> -->
            </div>
        </div>

        <div class="blog-body">
          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" name="form1" enctype="multipart/form-data">
					
            <div class="blog-title">
							 <h1><a href="blog_single.html" class="content"></a><%=articleVO.getArticle_title()%></h1>
            </div>
            <div class="blog-summary">
                
						<h3> 
						<img src="<%=request.getContextPath() %>/DBGifReader3?article_no=${articleVO.article_no}" style="width:100px; height:100px;"> 
						
							 <input type="TEXT" name="article_title" size="45" class="textarea" value="<%=articleVO.getArticle_title()%>" />
						</h3>
						<div class="blog_single_desc content" id="content">
							<%=articleVO.getArticle_content()%>
						</div>
					
					<div class="textarea">
						<input type="file" accept="image/*" name="article_picture" onchange="loadFile(event)"> 標題圖片:
						<img src="<%=request.getContextPath() %>/DBGifReader4?article_no=${articleVO.article_no}" id="output" style="height: 100px; width: auto;">
						
						<br>文章內容:<textarea id="ck" name="article_content" contenteditable="true">
				              <%=articleVO.getArticle_content()%>
				            </textarea>
				            
					  		<script>
				           		 CKEDITOR.disableAutoInline = true;
								CKEDITOR.inline('ck');
							</script>
						
						文章類別:<font color=red><b>*</b></font>
						 <select size="1" name="article_glass">
							<option value="重訓" ${(articleVO.article_glass=="重訓")?'selected':'' }>重訓</option>
							<option value="有氧" ${(articleVO.article_glass=="有氧")?'selected':'' }>有氧</option>
							<option value="閒聊" ${(articleVO.article_glass=="閒聊")?'selected':'' }>閒聊</option>
						</select> <input type="hidden" name="article_visitors" size="45" value="<%=articleVO.getArticle_visitors()%>" />
						
						 <input type="hidden" name="article_replies" size="45" value="<%=articleVO.getArticle_replies()%>" /> 
						 <input type="hidden" name="article_post_time" size="45" value="<%=articleVO.getArticle_post_time()%>" /> 
						 <input type="hidden" name="article_editart_lasttime" size="45" value="<%=articleVO.getArticle_editart_lasttime()%>" /><br>
						 
						文章狀態:
						<select size="1" name="article_status">
							<option value="0"
								${(articleVO.article_status=="0")?'selected':'' }>隱藏</option>
							<option value="1"
								${(articleVO.article_status=="1")?'selected':'' }>顯示</option>
						</select> 
						
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="article_no" value="<%=articleVO.getArticle_no()%>">
						<input type="hidden" name="member_no" value="<%=articleVO.getMember_no()%>">
						<input type="hidden" name="coach_no" value="<%=articleVO.getCoach_no()%>">
						 <input type="submit" value="送出修改">
						 
					</div>
				</FORM>
            </div>
            <div class="blog-tags">
                <ul>
                    <li><a href="#">作者</a> </li>
                    
                     <span><%=(articleVO.getMember_no() == null) ? articleVO.getCoach_no() : articleVO.getMember_no()%></span>
                </ul>
            </div>
        </div>

        <div class="blog-footer">
            <ul>
                <li class="published-date"><span><%=articleVO.getArticle_post_time()%></span></li>
                
                <li class="comments" style="margin-top:-10px;"><a href="#"><span><%=articleVO.getArticle_replies()%></span><use xlink:href="#icon-bubble"></use></svg><span class="numero"><i class="blog_icon8 mr-0"> </i></span></a></li>
                
		                <li class="shares">
		                   <img src="<%=request.getContextPath()%>/front-end/gym_index/images/eye.png" width="30px" height="30px"><span><%=articleVO.getArticle_visitors()%></span>
		                 </li>
						
						<li>
							<button style="border: 0px;" type="button" class="pen" title="編輯">
								<img src="<%=request.getContextPath()%>/front-end/gym_index/images/pen.png">
							</button>
						</li>
						
						<li>
						<a href="<%=request.getContextPath()%>/report_article/report_article.do?article_no=<%=articleVO.getArticle_no()%>&action=getReportModal">
								<button type="button" style="border: 0px; width:30px; height:30px;" title="舉報">
									<img src="<%=request.getContextPath()%>/front-end/gym_index/images/alert.png">
								</button>
						</a>
						</li>
                 
            </ul>
        </div>

    </div>

    <div class="blog-container" >

        <div class="blog-body">
            <div class="blog-summary">
            <br>
               <table>
                <ul class="social_blog">
					<h3>Share</h3>
					<li class="fb"><a href="#"><span> </span></a></li>
					<li class="tw"><a href="#"><span> </span></a></li>
					<li class="google_blog"><a href="#"><span> </span></a></li>
					<li class="linkedin"><a href="#"><span> </span></a></li>
				</ul>
				</table>
				
				<jsp:include page="/front-end/reply_article/NewlistAllReplyArticle.jsp" />
			</div>
            </div>
        </div>

        <div class="blog-footer">
        </div>

    
    
	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>
	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">


			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body">
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
						<jsp:include page="report_article_page.jsp" />
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
	$(".pen").click(function() {
		$(".textarea").toggle();
		$(".content").toggle();
	});

	if (
<%=sweetalert%>
	== true) {
		swal({
			title : "已提交審核",
			text : "兩秒後自動關閉",
			timer : 2000,
			showConfirmButton : false
		});
	}
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