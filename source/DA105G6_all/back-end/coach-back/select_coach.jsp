<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>

<%
	CoachVO coachVO = new CoachVO();
%>
	<jsp:useBean id="coachSvc" scope="page" class="com.coach.model.CoachService"/>
	

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>查詢教練</title>
</head>
<body>
	<section id="container">
		<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
		<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
		<!-- /////////////////////////////////// -->
		<section id="main-content">

			<br>
			<br>
			<br>
			<br>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

	<div style="font-size:2em; " >
<!-- 			list all    -->

			<form  method="post" action="<%=request.getContextPath()%>/coach_back.do">
					 <b><font color=blue>教練萬用查詢:</font></b> <br>

<input type="submit" value="查詢所有教練" class="btn btn-theme03" 
style=" background: none!important;
  border: none;
  padding: 0!important;
  /*optional*/
  font-family: arial, sans-serif;
  /*input has OS specific font-family*/
  color: #069;
  text-decoration: none;
  cursor: pointer;">		
  					
  						 <br><br>		 
					 	 <b>輸入教練姓名</b>
					 	<input type="text" name="coach_name" size="45"><br>
					
					  <b>輸入教練地址</b>
					       <input type="text" name="coach_address" size="45"><br>
					       
					       
					 <b>選擇教練性別</b>
					 	<select size="1"  name="coach_sex">
					 			  <option>
						          <option value="男">男
						          <option value="女">女
					       </select><br>
					 	
					 <b> 選擇教練編號 </b>
						 	<select size="1"  name="coach_no">
						          <option >
						         <c:forEach var="coachVO" items="${coachSvc.findAllCoach()}" > 
						          <option value="${coachVO.coach_no}">${coachVO.coach_no}
						         </c:forEach>   
					       </select><br>
			
							 <b>選擇教練權限</b>
					 	<select size="1"  name="coach_auth">
					 			  <option>
						          <option value="0">${coach_auth.get("0".toString())}
						          <option value="1">${coach_auth.get("1".toString())}
						          <option value="2">${coach_auth.get("2".toString())}
					       </select><br>
					       
					    <b>選擇教練狀態</b>
					 	<select size="1"  name="coach_review">
					 				<option>
						          <option value="0">${coach_review.get("0".toString())}
						          <option value="1">${coach_review.get("1".toString())}
						          <option value="2">${coach_review.get("2".toString())}
						          <option value="3">${coach_review.get("3".toString())}
						          <option value="4">${coach_review.get("4".toString())}
					       </select><br>
					 	
					<input type="submit" value="送出" class="btn btn-theme03">
        			<input type="hidden" name="action" value="listCoach_ByCompositeQuery">
			</form>
			
			<br><br>
			
	</section> <!-- main content-->
</section> <!-- container -->

		</section>
		<!-- main content-->
		<!-- /////////////////////////////////// -->
	</section>
	<!-- container -->

</body>
</html>