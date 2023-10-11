<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<jsp:useBean  id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean  id="listMember_ByCompositeQuery" scope="session" type="java.util.List<MemberVO>" />

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>list_all_member_query</title>
</head>
<body>
<section id="container">
	<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
	<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
<!-- /////////////////////////////////// -->
<section id="main-content">

<br>
	<br><br><br>
		<a href="<%=request.getContextPath()%>/back-end/member-back/select_member.jsp">回查詢頁面</a><br><br>


<%@ include file="/back-end/member-back/pages/page1_ByCompositeQuery.file" %> <br>
	<small ><font color="gray">驗證失敗帳號可刪除</font></small>
	
		<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div style="overflow: scroll;">
		<table style="text-align: center; border: 1px #cccccc solid; min-width: 1500px;" cellpadding="10" border='1'>

			<tr style="white-space: nowrap;">
			<th>　修改　</th>
			<th>　刪除　</th>
				<th>　會員編號　</th>
				<th>　會員姓名　</th>
				<th>　會員性別　</th>
				<th>　會員手機　</th>
				<th>　會員帳號　</th>
				<th>　會員地址　</th>
				<th>　會員照片　</th>
				<th>　會員點數　</th>
				<th>　會員身高　</th>
				<th>　會員體重　</th>
				<th>　會員狀態　</th>
				<th>　會員權限　</th>
				<th>　會員體脂　</th>
			</tr>
			<c:forEach var="memberVO" items="${listMember_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

				<tr style="white-space: nowrap;" align='center' valign='middle' ${(memberVO.member_no == param.member_no) ? 'bgcolor=#282C41':''}><!--將修改的那一筆加入對比色而已-->
						<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/member_back.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改狀態"> 
							<input type="hidden" name="member_no" value="${memberVO.member_no}"> 
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="whichPage" value="<%=whichPage%>">							
							<input type="hidden" name="action" value="UpdateStatus">
						</FORM>
					</td>
					<td>
					
					<c:if test="${memberVO.member_review == 2}" scope="request" var="flag" >
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/member_back.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> 
							<input type="hidden" name="member_no" value="${memberVO.member_no}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="whichPage" value="<%=whichPage%>">			
							<input type="hidden" name="action" value="deleteMemberForStatus">
						</FORM>
					</c:if>
					</td>
					
					<td>${memberVO.member_no}</td>
					<td>${memberVO.member_name}</td>
					<td>${memberVO.member_sex}</td>
					<td>${memberVO.member_cellphone}</td>
					<td>${memberVO.member_account}</td>
					<td>${memberVO.member_address}</td>
					<td><img id="output"
						src="<%= request.getContextPath()%>/MemberReader?member_no=${memberVO.member_no}"
						style="height: 80px; width: 80px;" /></td>
					<td>${memberVO.member_points}</td>
					<td>${memberVO.member_height}</td>
					<td>${memberVO.member_weight}</td>
					<td>${member_review.get(memberVO.member_review.toString())}</td>
					<td>${member_auth.get(memberVO.member_auth.toString())}</td>
					<td>${memberVO.member_bodyfat}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<%@ include file="pages/page2_ByCompositeQuery.file" %>
<br>
<br><br>
<br>
	
</section> <!-- main content-->
<!-- /////////////////////////////////// -->
</section> <!-- container -->
</body>
</html>