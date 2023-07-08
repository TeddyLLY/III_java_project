<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.system_news.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<jsp:useBean id="coachSvc" scope="page" class="com.coach.model.CoachService"/>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>update news</title>
</head>
<body>
<section id="container">
	<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
	<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
<!-- /////////////////////////////////// -->
<section id="main-content">

<br>
<br>
      <section class="wrapper">
     	 <div class="row mt">
        <h3><i class="fa fa-hand-o-right"></i>資料修改</h3>
          <div class="col-md-12">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="errorMsgs" items="${errorMsgs}">
				<li style="color: red">${errorMsgs}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/systemNews_back.do"
		name="form1">

			<table
					class="table table-bordered table-condensed"
					style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
					cellpadding="10" border='1'>
					<thead>
                  <tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
					<td>系統訊息編號:<font color=red><b>*</b></font></td>
					<td><input type="hidden" name="system_news_no"
						value="${systemNewsVO.system_news_no}">${systemNewsVO.system_news_no}</td>
				</tr>
				<tr>
					<td>會員編號:</td>
					<td><input type="TEXT" name="member_no" size="45"
						value="${systemNewsVO.member_no}" /></td>
				</tr>
				
				<tr>
					<td>會員姓名:</td>
					<td>					
						<c:forEach var="memberVO" items="${memberSvc.findAllMember()}">
		                    <c:if test="${memberVO.member_no==systemNewsVO.member_no}">
			                    ${memberVO.member_name}
		                    </c:if>
	                    </c:forEach>
	                </td>  
				</tr>
				
				<tr>
					<td>教練編號:</td>
					<td><input type="TEXT" name="coach_no" size="45"
						value="${systemNewsVO.coach_no}" /></td>
				</tr>
				
				<tr>
					<td>教練姓名:</td>
					<td>
					<c:forEach var="coachVO" items="${coachSvc.findAllCoach()}">
		                    <c:if test="${coachVO.coach_no==systemNewsVO.coach_no}">
			                    ${coachVO.coach_name}
		                    </c:if>
	                    </c:forEach>
					</td>
				</tr>
				
				<tr>
					<td>訊息內容:</td>
					<td>
					<textarea cols="50" rows="5" name="system_content">${systemNewsVO.system_content}</textarea>
					</td>
				</tr>
				<tr>
					<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
					<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
					<c:set value="${systemNewsVO.system_time}" var="dateString" />
					<fmt:parseDate value="${dateString}" var="dateObject"
						pattern="yyyy-MM-dd HH:mm:ss" />
					<td>訊息時間</td>
					<td>${dateObject}</td>
				</tr>
			
			<tr>
				<td></td>
				<td>
					<input type="hidden" name="action"
					value="updateOneNew"> <input type="hidden"
					name="system_news_no" value="${systemNewsVO.system_news_no}">
					<button type="submit" class="btn btn-theme" >送出修改</button>
					<button class="btn btn-theme04" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/system-news-back/review_news.jsp'">取消</button>
				</td>
			
			</tr>
	
			</table>
	</FORM>
</div>
</div>

</section> <!-- main content-->
</section>
<!-- /////////////////////////////////// -->
</section> <!-- container -->
	
</body>
</html>