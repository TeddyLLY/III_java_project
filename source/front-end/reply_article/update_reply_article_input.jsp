<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reply_article.model.*"%>
<%
ReplyArticleVO reply_articleVO=(ReplyArticleVO) request.getAttribute("reply_articleVO");



%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_article_input.jsp</title>

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
		 <h3>修改回覆 - update_reply_article_input.jsp</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reply_article/reply_article.do" name="form1" >
<table>
	<tr>
		<td>回覆編號:<font color=red><b>*</b></font></td>
		<td><input type="hidden" name="reply_no"  value="<%=reply_articleVO.getReply_no()%>"/><%=reply_articleVO.getReply_no()%></td>
	</tr>
	<tr>
		<td>回覆內容:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="reply_content" size="40"	value="<%=reply_articleVO.getReply_content()%>" /></td>
	</tr>
	<tr>		
		<td>回覆時間:</td>
		<td><input type="hidden" name="reply_time"	value="<%=reply_articleVO.getReply_time()%>" /><%=reply_articleVO.getReply_time()%></td>
	</tr>
	<tr>
		<td>修改時間:</td>
		<td><input type="hidden" name="last_updated" size="45" value="<%=reply_articleVO.getLast_updated()%>"/><%=reply_articleVO.getLast_updated()%></td>
	</tr>
	<tr>
		<td>文章編號:</td>
		<td><input type="hidden" name="article_no" size="45" value="<%=reply_articleVO.getArticle_no()%>"/><%=reply_articleVO.getArticle_no()%></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="member_no" size="45" value="<%=reply_articleVO.getMember_no()%>"/>
<input type="hidden" name="coach_no" size="45" value="<%=reply_articleVO.getCoach_no()%>"/>
<input type="hidden" name="action" value="update">

<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->



<script>
        
</script>
</html>