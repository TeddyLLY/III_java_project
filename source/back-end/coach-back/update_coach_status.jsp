<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%
	CoachVO coachVO = (CoachVO) request.getAttribute("coachVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	CoachService coachSvc = new CoachService();
	coachVO = coachSvc.findOneCoach(coachVO.getCoach_no());
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
				<h3>教練資料修改</h3>
				<h4>
<a href="<%=request.getContextPath()%>/back-end/coach-back/select_coach.jsp">回查詢頁面</a><br><br>
				</h4>
			</td>
		</tr>
	</table>

	<h3>教練狀態:</h3>

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
		ACTION="<%=request.getContextPath()%>/coach_back.do" name="form1"
		enctype="multipart/form-data">
			<table
				style=" border: 1px #cccccc solid; min-width: 150px;"
				class="table table-striped" cellpadding="10" border='1'>

				<tr>
					<td>教練編號:<font color=red><b>*</b></font></td>
					<td><%=coachVO.getCoach_no()%></td>
				</tr>

				<tr>
					<td>教練姓名:<font color=red><b>*</b></font></td>
					<td><%=coachVO.getCoach_name()%></td>
				</tr>

				<tr>
					<td>教練性別:</td>
					<td><%=coachVO.getCoach_sex()%></td>
				</tr>

				<tr>
					<td>教練手機:<font color=red><b>*</b></font></td>
					<td><%=coachVO.getCoach_cellphone()%></td>
				</tr>

				<tr>
					<td>教練地址:</td>
					<td><%=coachVO.getCoach_address()%></td>
				</tr>

				<tr>
					<td>教練照片:<br><br>教練證照:</td>
					<td>
								<img id="output"
									src="<%= request.getContextPath()%>/CoachPhotoReader?coach_no=${coachVO.coach_no}"
									style="height: 150px; width: 150px;" />　
								<img id="output"
									src="<%= request.getContextPath()%>/CoachLicenseReader?coach_no=${coachVO.coach_no}"
									style="height: 150px; width: 150px;" />
					</td>
				</tr>

				<tr>
					<td>教練收益:</td>
					<td><%=coachVO.getCoach_income()%></td>
				</tr>

				<tr>
					<td>會員狀態:</td>
					<td><select size="1" name="coach_review">
							<option value="<%=coachVO.getCoach_review()%>">${coach_review.get(coachVO.getCoach_review().toString())}
							<option value="0">${coach_review.get("0".toString())}
							<option value="1">${coach_review.get("1".toString())}
							<option value="2">${coach_review.get("2".toString())}
							<option value="3">${coach_review.get("3".toString())}
							<option value="4">${coach_review.get("4".toString())}
							<option value="5">${coach_review.get("5".toString())}
					</select></td>
				</tr>

				<tr>
					<td>教練權限:</td>
					<td><select size="1" name="coach_auth">
							<option value="<%=coachVO.getCoach_auth()%>">${coach_auth.get(coachVO.getCoach_auth().toString())}
							<option value="0">${coach_auth.get("0".toString())}
							<option value="1">${coach_auth.get("1".toString())}
							<option value="2">${coach_auth.get("2".toString())}
					</select></td>
				</tr>
			</table>

		<br> 
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
		<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
		<input type="hidden" name="action" value="UpdateStatusSuccess"> 
		<input type="hidden" name="coach_no" size="45" value="<%=coachVO.getCoach_no()%>" />	
		<input type="submit" value="送出修改">
	</FORM>
</body>
</section> <!-- main content-->
<!-- /////////////////////////////////// -->
</section> <!-- container -->
	
</body>
</html>