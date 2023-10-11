<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>

<%
	CoachService coachSvc = new CoachService();
	List<CoachVO> list = coachSvc.findAllCoachDesc();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>


<title>審核教練</title>

</head>
<body>
	<section id="container">
		<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
		<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
		<!-- /////////////////////////////////// -->
		<!--main content start-->
		<section id="main-content">

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

			<section class="wrapper">
				<h3>
					<i class="fa fa-hand-o-right"></i> 待處理審核
				</h3>
				<div class="row mt">
					<div class="col-lg-12">

							<section id="unseen">
								<div style="overflow:scroll;overflow-X:hidden;">
									<table
										class="table table-bordered table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
												<th>　審核　</th>
												<th></th>
												<th>　編號　</th>
												<th>　姓名　</th>
												<th>　性別　</th>
												<th>　手機　</th>
												<th>　地址　</th>
												<th>　照片　</th>
												<th>　總評價分數　</th>
												<th>　總評價人數　</th>
												<th>　平均評價分數　</th>
												<th>　證照　</th>
												<th>　教練收益　</th>
												<th>　教練介紹　</th>
											</tr>
										</thead>
										<tbody>
											<tr>

												<c:forEach var="coachVO" items="${list}">
													<c:if test="${coachVO.coach_review == 2 }">

													<tr style="white-space: nowrap;">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coach_back.do" style="margin-bottom: 0px;">
															<td>
																	<select name="coach_review">
																	  <option value="${coachVO.coach_review}">${coach_review.get(coachVO.coach_review.toString())}</option>
																	  <option value=4>${coach_review.get("4".toString())}</option>
																	   <option value=5>${coach_review.get("5".toString())}</option>
																	</select>
															</td>
														
														<td>
																<button type="submit" class="btn btn-theme"data-toggle="modal"
																data-target="#exampleModalCenter" >修改</button>
																<input type="hidden" name="coach_no" value="${coachVO.coach_no}"> 
																<input type="hidden" name="coach_name" value="${coachVO.coach_name}"> 
																<input type="hidden" name="action" value="backUpdateStatus">
														</td>
									</FORM>
														<td>${coachVO.coach_no}</td>
														<td>${coachVO.coach_name}</td>
														<td>${coachVO.coach_sex}</td>
														<td>${coachVO.coach_cellphone}</td>
														<td>${coachVO.coach_address}</td>
														<td><img id="output"
															src="<%= request.getContextPath()%>/CoachPhotoReader?coach_no=${coachVO.coach_no}"
															style="height: 80px; width: 80px;" /></td>
														<td>${coachVO.coach_total_evaluation}</td>
														<td>${coachVO.coach_total_people_evaluation}</td>
														<td>${coachVO.coach_average_evaluation}</td>
														<td><img id="output"
															src="<%= request.getContextPath()%>/CoachLicenseReader?coach_no=${coachVO.coach_no}"
															style="height: 80px; width: 80px;" /></td>
														<td>${coachVO.coach_income}</td>
														<td><button type="submit" class="btn btn-theme"data-toggle="modal"
																data-target="#exampleModalCenter" >See</button></td>
												
													</tr>
													<!-- Modal  introduction-->
													<div class="modal fade" id="exampleModalCenter"
														tabindex="-1" role="dialog"
														aria-labelledby="exampleModalCenterTitle"
														aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered"
															role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title" id="exampleModalCenterTitle">${coachVO.coach_name}
																		的自我介紹 :</h5>
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	${coachVO.coach_introduction}</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-dismiss="modal">Close</button>
																</div>
															</div>
														</div>
													</div>
													</c:if>
												</c:forEach>

											</tr>
										</tbody>
									</table>
								</div>
							</section>

					</div>
					<!-- /col-lg-4 -->
				</div>
				<!-- /row -->
				<h3>
					<i class="fa fa-hand-o-right"></i> 已完成審核
				</h3>
				<div class="row mt">
					<div class="col-lg-12">

							<section id="no-more-tables">
								<div style="overflow:scroll;overflow-X:hidden;height:400px;">
									<table
										class="table table-bordered table-condensed cf"
										style="text-align: center; border: 1px #cccccc solid; min-width: 1500px;background-color: #a7a7a7a1;"
										cellpadding="10" border='1'>
										<thead class="cf">
											<tr style="white-space: nowrap;color:white; background-color: #4a4a54;">
												<th>　編號　</th>
												<th>　姓名　</th>
												<th>　性別　</th>
												<th>　手機　</th>
												<th>　地址　</th>
												<th>　照片　</th>
												<th>　總評價分數　</th>
												<th>  總評價人數　</th>
												<th>　平均評價分數　</th>
												<th>　證照　</th>
												<th>　收益　</th>
												<th>　介紹　</th>
<!-- 												<th>　教練審核狀態　</th> -->
<!-- 												<th>　教練權限狀態　</th> -->
<!-- 												<th></th> -->
											</tr>
										</thead>
										<tbody>
											<tr>
												

												<c:forEach var="coachVO" items="${list}">
													<c:if test="${coachVO.coach_review != 2 }">

													<tr style="white-space: nowrap;">

														<td>${coachVO.coach_no}</td>
														<td>${coachVO.coach_name}</td>
														<td>${coachVO.coach_sex}</td>
														<td>${coachVO.coach_cellphone}</td>
														<td>${coachVO.coach_address}</td>
														<td><img id="output"
															src="<%= request.getContextPath()%>/CoachPhotoReader?coach_no=${coachVO.coach_no}"
															style="height: 80px; width: 80px;" /></td>
														<td>${coachVO.coach_total_evaluation}</td>
														<td>${coachVO.coach_total_people_evaluation}</td>
														<td>${coachVO.coach_average_evaluation}</td>
														<td><img id="output"
															src="<%= request.getContextPath()%>/CoachLicenseReader?coach_no=${coachVO.coach_no}"
															style="height: 80px; width: 80px;" /></td>
														<td>${coachVO.coach_income}</td>														
														<td><button type="submit" class="btn btn-theme"data-toggle="modal"
																data-target="#exampleModalCenter" >See</button></td>
<!-- 																<td> -->
<!-- 																	<select id="coach_review"> -->
<%-- 																	  <option value="${coach_review.get(coachVO.coach_review.toString())}">${coach_review.get(coachVO.coach_review.toString())}</option> --%>
<%-- 																	  <option value="0">${coach_review.get("0".toString())}</option> --%>
<%-- 																	  <option value="1">${coach_review.get("1".toString())}</option> --%>
<%-- 																	  <option value="2">${coach_review.get("2".toString())}</option> --%>
<%-- 																	   <option value="3">${coach_review.get("3".toString())}</option> --%>
<%-- 																	  <option value="4">${coach_review.get("4".toString())}</option> --%>
<%-- 																	   <option value="5">${coach_review.get("5".toString())}</option> --%>
<!-- 																	</select> -->
<!-- 																</td> -->
<!-- 															<td> -->
<!-- 																<select id="coach_auth"> -->
<%-- 																  <option value="${coach_auth.get(coachVO.coach_auth.toString())}">${coach_auth.get(coachVO.coach_auth.toString())}</option> --%>
<%-- 																  <option value="0">${coach_auth.get("0".toString())}</option> --%>
<%-- 																  <option value="1">${coach_auth.get("1".toString())}</option> --%>
<%-- 																  <option value="2">${coach_auth.get("2".toString())}</option> --%>
<!-- 																</select> -->
<!-- 															</td> -->
															
<!-- 														<td> -->
<!-- 															<FORM METHOD="post" -->
<%-- 																ACTION="<%=request.getContextPath()%>/coach_back.do" --%>
<!-- 																style="margin-bottom: 0px;"> -->
<!-- 																<input type="submit" value="修改"> <input -->
<!-- 																	type="hidden" name="coach_no" -->
<%-- 																	value="${coachVO.coach_no}"> <input --%>
<!-- 																	type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 															</FORM> -->
<!-- 														</td> -->
													</tr>
													<!-- Modal  introduction-->
													<div class="modal fade" id="exampleModalCenter"
														tabindex="-1" role="dialog"
														aria-labelledby="exampleModalCenterTitle"
														aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered"
															role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title" id="exampleModalCenterTitle">${coachVO.coach_name}
																		的自我介紹 :</h5>
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	${coachVO.coach_introduction}</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-dismiss="modal">Close</button>
																</div>
															</div>
														</div>
													</div>
													</c:if>
												</c:forEach>

											</tr>
										</tbody>
									</table>
								</div>
							</section>
						</div>

					</div>
					<!-- /col-lg-12 -->
				<!-- /row -->
			</section>
			<!-- /wrapper -->
		</section>
		<!-- /MAIN CONTENT -->
		<!--main content end-->
		<!-- /////////////////////////////////// -->
	</section>
	<!-- container -->

</body>
</html>