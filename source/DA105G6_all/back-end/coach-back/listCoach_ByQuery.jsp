<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>

<jsp:useBean id="coachSvc" scope="page" class="com.coach.model.CoachService"/>
<jsp:useBean id="listCoach_ByCompositeQuery" scope="session" type="java.util.List<CoachVO>"/>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>list all coach Query</title>
</head>
<body>
<section id="container">
	<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
	<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
<!-- /////////////////////////////////// -->
<section id="main-content">
<br><br><br><br>
	<a href="<%=request.getContextPath()%>/back-end/coach-back/select_coach.jsp">回查詢頁面</a><br><br>
	
	<br><br>
<%@ include file="/back-end/coach-back/pages/page1_ByCompositeQuery.file" %><br>
	<small><font color="gray">驗證失敗帳號可刪除</font></small>

	<c:if test="${! empty errorMsgs }">
		<ul>
			<c:forEach var="message" items="${errorMsgs }">
					<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<div style="overflow: scroll;">
		<table
			style="text-align: center; border: 1px #cccccc solid; min-width: 1500px;"
			cellpadding="10" border='1'>

			<tr style="white-space: nowrap;">
			<th>　修改　</th>
			<th>　刪除　</th>
				<th>　教練編號　</th>
				<th>　教練姓名　</th>
				<th>　教練照片　</th>
				<th>　教練證照　</th>
				<th>　教練自我介紹　</th>
				
				<th>　教練性別　</th>
				<th>　教練手機　</th>
				<th>　教練帳號　</th>
				<th>　教練地址　</th>
				<th>　教練狀態　</th>
				<th>　教練權限　</th>
				<th>　教練總評價分數　</th>
				<th>　教練總評價人數　</th>
				<th>　教練平均分數　</th>
				<th>　教練收益　</th>
				<th>　教練銀行帳號　</th>
			</tr>
<% int count=1; %>
			<c:forEach var="coachVO" items="${listCoach_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<% ++count;%>
				<tr style="white-space: nowrap;" align='center' valign='middle' ${(coachVO.coach_no == param.coach_no) ? 'bgcolor=#21344B':''}><!--將修改的那一筆加入對比色而已-->
						<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/coach_back.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改狀態"> 
							<input type="hidden" name="coach_no" value="${coachVO.coach_no}"> 
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="whichPage" value="<%=whichPage%>">	
							<input type="hidden" name="action" value="UpdateStatus">
						</FORM>
					</td>
					<td>
					
					<c:if test="${coachVO.coach_review == 5}" scope="request" var="flag" >
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/coach_back.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> 
							<input type="hidden" name="coach_no" value="${coachVO.coach_no}"> 
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="whichPage" value="<%=whichPage%>">	
							<input type="hidden" name="action" value="deleteCoachForStatus">
						</FORM>
					</c:if>
					</td>
					
				<td>${coachVO.coach_no}</td>
					<td>${coachVO.coach_name}</td>
					<td>
						<img id="output" src="<%= request.getContextPath()%>/CoachPhotoReader?coach_no=${coachVO.coach_no}" style="height: 80px; width: 80px;" />
					</td>
						<td>
							<img id="output" src="<%= request.getContextPath()%>/CoachLicenseReader?coach_no=${coachVO.coach_no}" style="height: 80px; width: 80px;" />
						</td>
					<td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModalCenter<%=count %>">See</button></td>
					<td>${coachVO.coach_sex}</td>
					<td>${coachVO.coach_cellphone}</td>
					<td>${coachVO.coach_account}</td>
					<td>${coachVO.coach_address}</td>
					<td>${coach_review.get(coachVO.coach_review.toString())}</td>
					<td>${coach_auth.get(coachVO.coach_auth.toString())}</td>
					<td>${coachVO.coach_total_evaluation}</td>
					<td>${coachVO.coach_total_people_evaluation}</td>
					<td>${coachVO.coach_average_evaluation}</td>
					<td>${coachVO.coach_income}</td>
					<td>${coachVO.coach_bank_account}</td>
				</tr>
				
<!-- Modal  introduction-->
	<div class="modal fade" id="exampleModalCenter<%=count %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalCenterTitle">${coachVO.coach_name} 的自我介紹 : </h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        ${coachVO.coach_introduction}
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
			</c:forEach>
		</table>
	</div>
<%@ include file="/back-end/coach-back/pages/page2_ByCompositeQuery.file" %>
<br>
<br><br>
<br>
</section> <!-- main content-->
<!-- /////////////////////////////////// -->
</section> <!-- container -->
	
</body>
</html>