<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.top_up_record.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	ArrayList<TopUpRecordVO> list = (ArrayList<TopUpRecordVO>) request.getAttribute("list"); //EmpServlet.java(Concroller), 存入req的empVO物件
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

<%@ include file="/back-end/member-back/pages/page1.file" %> <br>
			<table
				style="text-align: center; border: 1px #cccccc solid; min-width: 1500px;"
				cellpadding="10" border='1' class="table table-striped">

				<tr style="white-space: nowrap;">
					<th>儲值編號</th>
					<th>會員編號</th>
					<th>儲值時間</th>
					<th>儲值金額</th>
					<th></th>
					<th></th>
				</tr>
				
			<c:forEach var="topUpRecordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			
				<tr>
					<c:set value="${topUpRecordVO.record_time} " var="dateString" />
					<fmt:parseDate value="${dateString}" var="dateObject"
						pattern="yyyy-MM-dd HH:mm:ss" />
					<td>${topUpRecordVO.top_up_record_no}</td>
					<td>${topUpRecordVO.member_no}</td>
					<td>${dateObject}</td>
					<td>${topUpRecordVO.money_record}</td>
				</tr>
			</c:forEach>
			
			</table>
<%@ include file="/back-end/member-back/pages/page2.file" %>



		</section>
		<!-- main content-->
		<!-- /////////////////////////////////// -->
	</section>
	<!-- container -->

</body>
</html>