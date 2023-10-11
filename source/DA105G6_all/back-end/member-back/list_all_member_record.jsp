<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.top_up_record.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	TopUpRecordService topUpRecordSvc = new TopUpRecordService();
	List<TopUpRecordVO> list = topUpRecordSvc.findAllTopUpRecord();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>sample</title>
</head>
<body>
<section id="container">
	<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
	<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
<!-- /////////////////////////////////// -->
<section id="main-content">
<br><br><br><br><br><br><br>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div style="overflow: scroll;">
		<table
			style="text-align: center; border: 1px #cccccc solid; min-width: 1000px;"
			cellpadding="10" border='1' class="table table-striped">
			<tr style="white-space: nowrap;">
				<th>儲值編號</th>
				<th>會員編號</th>
				<th>會員姓名</th>
				<th>儲值金額</th>
				<th>儲值時間</th>
			</tr>

			<%@ include file="/back-end/member-back/pages/page1.file"%>

			<c:forEach var="topUpRecordVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				
				<c:set value="${topUpRecordVO.record_time} " var="dateString" />
				<fmt:parseDate value="${dateString}" var="dateObject"
					pattern="yyyy-MM-dd HH:mm:ss" />

				<tr>
					<td>${topUpRecordVO.top_up_record_no}</td>
					<td>${topUpRecordVO.member_no}</td>
					
					<td>
						<c:forEach var="memberVO" items="${memberSvc.findAllMember()}">
	                    <c:if test="${memberVO.member_no==topUpRecordVO.member_no}">
		                    ${memberVO.member_name}
	                    </c:if>
	                    </c:forEach>
                    </td>
                    
                    <td>${topUpRecordVO.money_record}</td>
					<td>${dateObject}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="/back-end/member-back/pages/page2.file"%>


	
</section> <!-- main content-->
<!-- /////////////////////////////////// -->
</section> <!-- container -->
	
</body>
</html>