<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

<%	
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_article_input.jsp</title>
<script src="${pageContext.request.contextPath}/front-end/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 480px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>文章資料修改 - update_article_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/article_select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>文章編號:<font color=red><b>*</b></font></td>
		<td><input type="hidden" name="article_no"  value="<%=articleVO.getArticle_no()%>"/><%=articleVO.getArticle_no()%></td>
	</tr>
	<tr>
		<td>文章標題:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="article_title" size="45" value="<%=articleVO.getArticle_title()%>" /></td>
	</tr>
	<tr>
		<td>文章內容:<font color=red><b>*</b></font></td>
		<td><TEXTAREA name="article_content" id="article_content"><%=articleVO.getArticle_content() %></TEXTAREA></td>
	</tr>
	<tr>		
		<td>文章類別:<font color=red><b>*</b></font></td>
		<td><select size="1" name="article_glass">			
				<option value="重訓" ${(articleVO.article_glass=="重訓")?'selected':'' }>重訓</option>
				<option value="有氧" ${(articleVO.article_glass=="有氧")?'selected':'' }>有氧</option>
				<option value="閒聊" ${(articleVO.article_glass=="閒聊")?'selected':'' }>閒聊</option>		
		</select></td>			
	</tr>
	<tr>
		<td>觀看人數:</td>
		<td><input type="hidden" name="article_visitors" size="45" value="<%=articleVO.getArticle_visitors()%>"/><%=articleVO.getArticle_visitors()%></td>
	</tr>
	<tr>
		<td>回覆人數:</td>
		<td><input type="hidden" name="article_replies" size="45" value="<%=articleVO.getArticle_replies()%>"/><%=articleVO.getArticle_replies()%></td>
	</tr>
	<tr>
		<td>發文時間:</td>
		<td><input type="hidden" name="article_post_time" size="45" value="<%=articleVO.getArticle_post_time()%>"/><%=articleVO.getArticle_post_time()%></td>
	</tr>
	<tr>
		<td>最後修改時間:</td>
		<td><input type="hidden" name="article_editart_lasttime" size="45" value="<%=articleVO.getArticle_editart_lasttime()%>"/><%=articleVO.getArticle_editart_lasttime()%></td>
	</tr>
	<tr>		
		<td>文章狀態:</td>
		<td><select size="1" name="article_status">			
				<option value="0"  ${(articleVO.article_status=="0")?'selected':'' }>隱藏</option>
				<option value="1"  ${(articleVO.article_status=="1")?'selected':'' }>顯示</option>		
		</select></td>			
	</tr>
	

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="article_no" value="<%=articleVO.getArticle_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->



<script>
var loadFile = function(event) {
	var output = document.getElementById('output');
	output.src = URL.createObjectURL(event.target.files[0]);
};        

    CKEDITOR.replace('article_content');
    
    
      
</script>
</html>