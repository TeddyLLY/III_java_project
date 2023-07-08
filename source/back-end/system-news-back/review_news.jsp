<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.system_news.model.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<jsp:useBean id="coachSvc" scope="page" class="com.coach.model.CoachService"/>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	SystemNewsService systemNewsSvc = new SystemNewsService();
	List<SystemNewsVO> list = systemNewsSvc.findAllSystemNews();
	pageContext.setAttribute("list", list);
%>

<% 
MemberVO memberVO = new MemberVO();
%>
	
<% 
CoachVO coachVO = new CoachVO();
%>
	

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>查詢系統訊息</title>
</head>
<body>
<section id="container">
	<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
	<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
<!-- /////////////////////////////////// -->
<section id="main-content">

<br><br>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
   <section class="wrapper">
				<h3><i class="fa fa-hand-o-right"></i>所有訊息</h3>

		<div class="row mt">
					<div class="col-md-12">
							<section id="unseen">
								<div>
									<table
										class="table table-bordered table-striped table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
				<th>系統訊息編號</th>
				<th>會員編號</th>
				<th>會員姓名</th>
				<th>教練編號</th>
				<th>教練姓名</th>
				<th>訊息內容</th>
				<th>訊息時間</th>
				<th></th><th></th>
			</tr>
			<c:forEach var="systemNewsVO" items="${list}" >
				<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
				<c:set value="${systemNewsVO.system_time}" var="dateString" />
				<fmt:parseDate value="${dateString}" var="dateObject"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<tr>
					<td>${systemNewsVO.system_news_no}</td>
					<td>${systemNewsVO.member_no}</td>
					<td>					
						<c:forEach var="memberVO" items="${memberSvc.findAllMember()}">
		                    <c:if test="${memberVO.member_no==systemNewsVO.member_no}">
			                    ${memberVO.member_name}
		                    </c:if>
	                    </c:forEach>
	                </td>  
					<td>${systemNewsVO.coach_no}</td>
					<td>
					<c:forEach var="coachVO" items="${coachSvc.findAllCoach()}">
		                    <c:if test="${coachVO.coach_no==systemNewsVO.coach_no}">
			                    ${coachVO.coach_name}
		                    </c:if>
	                    </c:forEach>
					</td>
					<td>${systemNewsVO.system_content}</td>
					<td>${dateObject}</td>
				
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/systemNews_back.do"
							style="margin-bottom: 0px;">
							<button type="submit" class="btn btn-theme" >修改</button><input type="hidden"
								name="system_news_no" value="${systemNewsVO.system_news_no}">
							<input type="hidden" name="action" value="UpdateNews">
						</FORM>
					</td>
					
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/systemNews_back.do"
							style="margin-bottom: 0px;">
							<button type="submit" class="btn btn-theme04" >刪除</button><input type="hidden"
								name="system_news_no" value="${systemNewsVO.system_news_no}">
							<input type="hidden" name="action" value="deleteOneNews">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	</section>
</div>
</div>
</section>
	
<h3>新增訊息<h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/systemNews_back.do" name="form1">
	<textarea cols="50" rows="5" name = "system_content" placeholder="輸入你想要寫的內容..." name="system_content">
	</textarea>
		<br><b>選擇會員姓名</b><br>
			<select size="1"  name="member_no">
						 			  <option>
							         <c:forEach var="memberVO" items="${memberSvc.findAllMember()}" > 
							          <option value="${memberVO.member_no}">${memberVO.member_name}
							         </c:forEach>   
			</select><br>
			
				<b>選擇教練姓名</b><br>
				<select size="1"  name="coach_no">
						 			  <option>
							        <c:forEach var="coachVO" items="${coachSvc.findAllCoach()}" > 
							          <option value="${coachVO.coach_no}">${coachVO.coach_name}
							         </c:forEach>   
				</select><br>
				
				
		<b>輸入會員編號</b><br>
		<input type="text" name="member_no" size="45"><br>
		
		<b>輸入教練編號</b><br>
			<input type="text" name="coach_no" size="45">
			
			
		<input type="hidden" value="insertNews"  name="action"><br>
		<input type="submit" value="送出"  class="btn btn-theme03"><br>
</form>

<br><br>
</section> <!-- main content-->
<!-- /////////////////////////////////// -->
</section> <!-- container -->
	
</body>
</html>