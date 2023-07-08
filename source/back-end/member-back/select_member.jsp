<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<% 
MemberVO memberVO = new MemberVO();
%>
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
	
	
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>

<title>查詢會員</title>
</head>
<body>
<!-- /////////////////////////////////// -->
<section id="container">
	<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
	<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
<!-- /////////////////////////////////// -->
<section id="main-content">


          		<br><br><br><br><%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div style="font-size:2em; " >
<!-- 			list all    -->

			<form  method="post" action="<%=request.getContextPath()%>/member_back.do">
					 <b><font color=deeppink>會員萬用查詢:</font></b> <br>
<input type="submit" value="查詢所有會員" class="btn btn-theme03" 
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
					 	 <b>輸入會員姓名</b>
					 	<input type="text" name="member_name" class="form-control">
					
					  <b>輸入會員地址</b>
					       <input type="text" name="member_address" class="form-control">
					       
					       
					 <b>選擇會員性別</b>
					 	<select size="1"  name="member_sex">
					 			  <option>
						          <option value="男">男
						          <option value="女">女
					       </select><br>
					 	
					 <b> 選擇會員編號 </b>
						 	<select size="1"  name="member_no">
						          <option >
						         <c:forEach var="memberVO" items="${memberSvc.findAllMember()}" > 
						          <option value="${memberVO.member_no}">${memberVO.member_no}
						         </c:forEach>   
					       </select><br>
			
					    <b>選擇會員狀態</b>
					 	<select size="1"  name="member_review">
					 				<option>
						          <option value="0">${member_review.get("0".toString())}
						          <option value="1">${member_review.get("1".toString())}
						          <option value="2">${member_review.get("2".toString())}
					       </select><br>
					       
					 	 <b>選擇會員權限</b>
					 	<select size="1"  name="member_auth">
					 			  <option>
						          <option value="0">${member_auth.get("0".toString())}
						          <option value="1">${member_auth.get("1".toString())}
					       </select><br>
					 	
					<input type="submit" value="送出" class="btn btn-theme03">
        			<input type="hidden" name="action" value="listMember_ByCompositeQuery">
			</form>
			<br>
			<b><font color=deepgreen>會員儲值紀錄:</font></b> <br>
						<a href='<%=request.getContextPath()%>/back-end/member-back/list_all_member_record.jsp' style="font-size:1.5px;">所有儲值紀錄</a><br><br>
						
 查詢會員儲值紀錄  <br>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/topUpRecord_back.do">
				<b>輸入儲值編號 (如T001):</b> <input type="text" name="top_up_record_no">
				<input type="hidden" name="action" value="findRecordByNum">
				<input type="submit" value="送出" class="btn btn-theme02">
			</FORM>
		
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/topUpRecord_back.do">
				<b>輸入會員編號(如M001):</b> 
				<input type="text" name="member_no" value="M001">
				<input type="hidden" name="action" value="findRecordByMember">
				<input type="submit" value="送出" class="btn btn-theme03">
			</FORM>
			
		<jsp:useBean id="topUpRecordSvc" scope="page"
			class="com.top_up_record.model.TopUpRecordService" />
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/topUpRecord_back.do">
				<b>選擇儲值編號:</b> <select size="1" name="top_up_record_no">
					<c:forEach var="topUpRecordVO"
						items="${topUpRecordSvc.findAllTopUpRecord()}">
						<option value="${topUpRecordVO.top_up_record_no}">${topUpRecordVO.top_up_record_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="findRecordByNum">
				<input type="submit" value="送出" class="btn btn-theme02">
			</FORM>
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/topUpRecord_back.do">
				<b>選擇會員姓名:</b> <select size="1" name="member_no">
					<c:forEach var="memberVO" items="${memberSvc.findAllMember()}">
						<option value="${memberVO.member_no}">${memberVO.member_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="findRecordByMember">
				<input type="submit" value="送出" class="btn btn-theme03"><br><br><br>
			</FORM>
			
	</section> <!-- main content-->
</section> <!-- container -->


</body>
</html>