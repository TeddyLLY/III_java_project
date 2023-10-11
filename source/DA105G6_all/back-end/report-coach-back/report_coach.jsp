<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report_coach.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<jsp:useBean id="coachSvc" scope="page" class="com.coach.model.CoachService"/>

<%
	ReportCoachService reportCoachSvc = new ReportCoachService();
	List<ReportCoachVO> list = reportCoachSvc.findAllSameStatus(0);
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>檢舉教練</title>

<style>

</style>

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
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<section class="wrapper">
				<h3>
					<i class="fa fa-hand-o-right"></i> 檢舉教練待審核
				</h3>
				<div class="row mt">
					<div class="col-lg-12">

							<section id="unseen">
								<div>
									<table
										class="table table-bordered table-striped table-condensed"
										style="text-align: center;mix-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
				<th>修改</th>
				<th></th>
				<th>教練名稱</th>
				<th>會員名稱</th>
				<th>檢舉內容</th>
				<th>檢舉時間</th>
				<th>檢舉狀態</th>
			</tr>
			<c:forEach var="reportCoachVO" items="${list}" >

				<tr style="white-space: nowrap;">
				
					<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/reportCoach_back.do"
							style="margin-bottom: 0px;">
					
					<td>
							<select size="1" name="report_coach_status">
								<option value="0">審核中</option>
								<option value="1">審核成功且停權</option>
								<option value="2">審核未通過</option>
							</select>
					</td>
					<td>
					<button type="submit" class="btn btn-theme" >送出</button> 
							<input type="hidden" name="report_coach_no" 
							value="${reportCoachVO.report_coach_no}">
							<input type="hidden" name="action" value="updateStatus">
					</td>
					
					</FORM>

					<td>
						<c:forEach var="coachVO" items="${coachSvc.findAllCoach()}">
		                    <c:if test="${coachVO.coach_no==reportCoachVO.coach_no}">
			                    ${coachVO.coach_name}
		                    </c:if>
	                    </c:forEach>
                    </td>
					<td>					
						<c:forEach var="memberVO" items="${memberSvc.findAllMember()}">
		                    <c:if test="${memberVO.member_no==reportCoachVO.member_no}">
			                    ${memberVO.member_name}
		                    </c:if>
	                    </c:forEach>
	                </td>    
					<td>${reportCoachVO.report_coach_content}</td>
					<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
					<c:set value="${ reportCoachVO.report_coach_time }"
						var="dateString" />
					<fmt:parseDate value="${dateString}" var="dateObject"
						pattern="yyyy-MM-dd HH:mm:ss" />

					<td>${dateObject}</td>
					<td>${report_coach_status.get(reportCoachVO.report_coach_status.toString())}</td>
				</tr>
			</c:forEach>
		</table>
	 </div>
	 </section>
	 </div>
	</div>
	</section>

<h3><i class="fa fa-hand-o-right"></i>已完成審核</h3>
<%
	List<ReportCoachVO> list2 = reportCoachSvc.findAllSameStatus(1);
	pageContext.setAttribute("list2", list2);
%>
<%
	List<ReportCoachVO> list3 = reportCoachSvc.findAllSameStatus(2);
	pageContext.setAttribute("list3", list3);
%>
	<div class="row mt">
					<div class="col-lg-12">
					<section id="no-more-tables">
								<div>
								
								<table
									class="table table-bordered table-condensed cf"
									style="text-align: center; border: 1px #cccccc solid; mix-width: 1500px; background-color: #a7a7a7a1;"
									cellpadding="10" border='1'>
									<thead class="cf">
									<tr style="white-space: nowrap;color:white; background-color: #4a4a54;">
				<th></th>
				<th>教練名稱</th>
				<th>會員名稱</th>
				<th>檢舉內容</th>
				<th>檢舉時間</th>
				<th>檢舉狀態</th>
				<th>修改</th>
				<th></th>
			</tr>
			<c:forEach var="reportCoachVO" items="${list2}" >

				<tr style="white-space: nowrap;">
					
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/reportCoach_back.do"
							style="margin-bottom: 0px;">
							<button type="submit" class="btn btn-theme04" >刪除</button> 
							<input type="hidden"
								name="report_coach_no" value="${reportCoachVO.report_coach_no}">
							<input type="hidden" name="action" value="deleteOkReport">
						</FORM>
					</td>
					
					<td>
						<c:forEach var="coachVO" items="${coachSvc.findAllCoach()}">
		                    <c:if test="${coachVO.coach_no==reportCoachVO.coach_no}">
			                    ${coachVO.coach_name}
		                    </c:if>
	                    </c:forEach>
                    </td>

					<td>					
						<c:forEach var="memberVO" items="${memberSvc.findAllMember()}">
		                    <c:if test="${memberVO.member_no==reportCoachVO.member_no}">
			                    ${memberVO.member_name}
		                    </c:if>
	                    </c:forEach>
	                </td>    
					<td>${reportCoachVO.report_coach_content}</td>
					<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
					<c:set value="${ reportCoachVO.report_coach_time }"
						var="dateString" />
					<fmt:parseDate value="${dateString}" var="dateObject"
						pattern="yyyy-MM-dd HH:mm:ss" />

					<td>${dateObject}</td>
					<td>${report_coach_status.get(reportCoachVO.report_coach_status.toString())}</td>
					
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportCoach_back.do" style="margin-bottom: 0px;">
						<td>
								<select size="1" name="report_coach_status">
									<option value="1">審核成功且停權</option>
									<option value="0">審核中</option>
									<option value="2">審核未通過</option>
								</select>
						</td>
						<td>
						<button type="submit" class="btn btn-theme" >送出</button>
						<input type="hidden" name="report_coach_no" 
								value="${reportCoachVO.report_coach_no}">
								<input type="hidden" name="action" value="updateStatus">
						</td>
					</FORM>
				</tr>
			</c:forEach>
		</table>
	</div>
	</section>
	</div>
	</div>
				<h3>
					<i class="fa fa-hand-o-right"></i> 審核未通過
				</h3>
		<div class="row mt">
					<div class="col-lg-12">
					<section id="no-more-tables">
								<div>
								
								<table
									class="table table-bordered table-condensed cf"
									style="text-align: center; border: 1px #cccccc solid; mix-width: 1500px; background-color: #a7a7a7a1;"
									cellpadding="10" border='1'>
									<thead class="cf">
									<tr style="white-space: nowrap;color:white; background-color: #4a4a54;">
				<th></th>
				<th>教練名稱</th>
				<th>會員名稱</th>
				<th>檢舉內容</th>
				<th>檢舉時間</th>
				<th>檢舉狀態</th>
				<th>修改</th>
				<th></th>
			</tr>
			<c:forEach var="reportCoachVO" items="${list3}" >

				<tr style="white-space: nowrap;">					
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/reportCoach_back.do"
							style="margin-bottom: 0px;">
							<button type="submit" class="btn btn-theme04" >刪除</button>
							<input type="hidden"
								name="report_coach_no" value="${reportCoachVO.report_coach_no}">
							<input type="hidden" name="action" value="deleteOkReport">
						</FORM>
					</td>		
					<td>
						<c:forEach var="coachVO" items="${coachSvc.findAllCoach()}">
		                    <c:if test="${coachVO.coach_no==reportCoachVO.coach_no}">
			                    ${coachVO.coach_name}
		                    </c:if>
	                    </c:forEach>
                    </td>
					<td>					
						<c:forEach var="memberVO" items="${memberSvc.findAllMember()}">
		                    <c:if test="${memberVO.member_no==reportCoachVO.member_no}">
			                    ${memberVO.member_name}
		                    </c:if>
	                    </c:forEach>
	                </td>    
					<td>${reportCoachVO.report_coach_content}</td>
					<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
					<c:set value="${ reportCoachVO.report_coach_time }"
						var="dateString" />
					<fmt:parseDate value="${dateString}" var="dateObject"
						pattern="yyyy-MM-dd HH:mm:ss" />

					<td>${dateObject}</td>
					<td>${report_coach_status.get(reportCoachVO.report_coach_status.toString())}</td>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportCoach_back.do" style="margin-bottom: 0px;">
						<td>
								<select size="1" name="report_coach_status">
									<option value="2">審核未通過</option>
									<option value="1">審核成功且停權</option>
									<option value="0">審核中</option>
								</select>
						</td>
						<td>
						<button type="submit" class="btn btn-theme" >送出</button>
						<input type="hidden" name="report_coach_no" 
								value="${reportCoachVO.report_coach_no}">
								<input type="hidden" name="action" value="updateStatus">
						</td>
					</FORM>
				</tr>
			</c:forEach>
		</table>
	</div>
	</section>
	</div>

	</div>
</section>	
	
	
</section> <!-- main content-->
<!-- /////////////////////////////////// -->

	
</body>
</html>