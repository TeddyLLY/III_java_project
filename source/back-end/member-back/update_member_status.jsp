<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	MemberService memberSvc = new MemberService();
	memberVO = memberSvc.findOneMember(memberVO.getMember_no());
%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>修改狀態</title>
</head>
<body>
<section id="container">
	<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
	<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
<!-- /////////////////////////////////// -->
<section id="main-content">


	<table class="table table-striped">
		<tr>
			<td>
				<h3>會員資料修改</h3>
				<h4>
<a href="<%=request.getContextPath()%>/back-end/member-back/select_member.jsp">回查詢頁面</a><br><br>
				</h4>
			</td>
		</tr>
	</table>

	<h3>修改會員狀態:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/member_back.do" name="form1"
		enctype="multipart/form-data">
			<table
				style=" border: 1px #cccccc solid; min-width: 150px;"
				class="table table-striped" cellpadding="10" border='1'>

				<tr>
					<td>會員編號:<font color=red><b>*</b></font></td>
					<td><input type="hidden" name="member_no" size="45"
						value="<%=memberVO.getMember_no()%>" /><%=memberVO.getMember_no()%></td>
				</tr>

				<tr>
					<td>會員姓名:<font color=red><b>*</b></font></td>
					<td><%=memberVO.getMember_name()%></td>
				</tr>

				<tr>
					<td>會員性別:</td>
					<td><%=memberVO.getMember_sex()%></td>
				</tr>

				<tr>
					<td>會員手機:<font color=red><b>*</b></font></td>
					<td><%=memberVO.getMember_cellphone()%></td>
				</tr>

				<tr>
					<td>會員地址:</td>
					<td><%=memberVO.getMember_address()%></td>
				</tr>

				<tr>
					<td>會員照片:</td>
					<td>
						<div>
							<img id="output"
								src="<%=request.getContextPath()%>/MemberReader?member_no=<%=memberVO.getMember_no()%>"
								style="height: 150px; width: 150px;" />
						</div>
					</td>
				</tr>

				<tr>
					<td>會員點數:</td>
					<td><%=memberVO.getMember_points()%></td>
				</tr>

				<tr>
					<td>會員狀態:</td>
					<td><select size="1" name="member_review">
							<option value="<%=memberVO.getMember_review()%>">${member_review.get(memberVO.getMember_review().toString())}
							<option value="0">${member_review.get("0".toString())}
							<option value="1">${member_review.get("1".toString())}
							<option value="2">${member_review.get("2".toString())}
					</select></td>
				</tr>

				<tr>
					<td>會員權限:</td>
					<td><select size="1" name="member_auth">
							<option value="<%=memberVO.getMember_auth()%>">${member_auth.get(memberVO.getMember_auth().toString())}
							<option value="0">${member_auth.get("0".toString())}
							<option value="1">${member_auth.get("1".toString())}
					</select></td>
				</tr>

			</table>

		<br> 
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
		<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
		<input type="hidden" name="action"value="UpdateStatusSuccess">
		 <input type="submit" value="送出修改">
	</FORM>
</body>

<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>

	
</section> <!-- main content-->
<!-- /////////////////////////////////// -->
</section> <!-- container -->
	
</body>
</html>