<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reply_article.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<style>
textarea {
    padding: 13px 20px;
    width: 60%;
    color: #fff;
    font-size: 0.85em;
    outline: none;
    height: 150px;
    display: block;
    resize: none;
    border: 1px solid #32C5FD;
    background: #72D0F4;
    font-family: 'Open Sans', sans-serif;
}
</style>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

</head>
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
	 CKEDITOR.replace('article_content');
</script>
<script src="js/jquery.min.js"></script>
<!-- grid-slider -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>
<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="js/jquery.contentcarousel.js"></script>
<script src="${pageContext.request.contextPath}/front-end/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>
<!-- //grid-slider -->
<%
	ReplyArticleService replyArticleSvc = new ReplyArticleService();
	List<ReplyArticleVO> list = replyArticleSvc.getAllReplyByArticleNo(request.getParameter("article_no"));

	pageContext.setAttribute("list", list);
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>

<title>Free Gym Website Template | Blog_single :: w3layouts</title>
<body>
<h4><%=articleVO.getArticle_replies()%>
				Comments
			</h4>
	<c:forEach var="replyArticleVO" items="${list}">
		 <div class="register-grids">
		<ul class="comments">
			
			<li>
				<ul class="comment_head">
					<h5>${(replyArticleVO.member_no==null)? replyArticleVO.coach_no : replyArticleVO.member_no}&nbsp;&nbsp;&nbsp;
						<span class="m_21"><a href="#">${reply_articleVO.reply_time}</a></span>
					</h5>
					<div class="reply1">
						<form METHOD="post"
						ACTION="<%=request.getContextPath()%>/reply_article/reply_article.do">
						<input type="hidden" name="article_no"
							value="${replyArticleVO.article_no}">
						<input type="hidden" name="reply_no"
							value="${replyArticleVO.reply_no}"> <input type="submit"
							value="刪除"> <input type="hidden" name="action"
							value="delete">
							
					</form>
					</div>
					<div class="clear"></div>
				</ul> <i class="preview"> </i>
				<div class="data">
					<p>${replyArticleVO.reply_content}</p>
				</div>
				<div class="clear"></div>
			</li>
		</ul>
		</div>
	</c:forEach>
	<h4>回覆文章</h4>
	<form METHOD="post"ACTION="<%=request.getContextPath()%>/reply_article/reply_article.do">
		<input type="hidden" name="article_no" value="<%=articleVO.getArticle_no()%>">
		<p class="comment-form-comment">
			<label for="comment">留言</label>
			<textarea id="reply_content" name="reply_content" cols="45" rows="8" aria-required="true"></textarea>
		</p>
		<p class="form-submit">
			<input name="submit" type="submit" id="submit" value="送出回覆">
		</p>
		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="member_no" value="M001">
		<div class="clear"></div>
	</form>




	
	
</body>
<script>



</script>
</html>