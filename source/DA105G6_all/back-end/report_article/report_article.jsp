<%@page import="com.report_article.model.*"%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService"/>


<%
	ReportArticleService reportArticleSvc = new ReportArticleService();
	List<ReportArticleVO> list = reportArticleSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/back-end/gym_index/back-end-head-source.file"%>
<%@ include file="/back-end/gym_index/back-end-head-css.file"%>

<title>檢舉文章</title>


</head>
<body>
<section id="container">
	<%@ include file="/back-end/gym_index/back-end-navbar.file"%>
	<%@ include file="/back-end/gym_index/back-end-sidebar.file"%>
<!-- /////////////////////////////////// -->
<section id="main-content">

<br><br>
<!-- 	 錯誤表列  -->
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

		<section class="wrapper">
				<h3>
					<i class="fa fa-hand-o-right"></i> 檢舉文章待審核
				</h3>
				<div class="row mt">
					<div class="col-lg-12">
							<section id="unseen">
								<div>
									<table
										class="table table-bordered table-striped table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
				<th>審核</th>
				<th></th>
				<th>檢舉文章編號</th>
				<th>文章編號</th>
				<th>檢舉人編號</th>
				<th>檢舉類別</th>
				<th>檢舉內容</th>
				<th>檢舉時間</th>
				<th>檢舉狀態</th>
				<th>查看文章</th>
			</tr>
			<c:forEach var="reportArticleVO" items="${list}" >
                   <c:if test="${ reportArticleVO.report_article_status==0}">
                 
				<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/report_article/report_article.do"
							style="margin-bottom: 0px;">
				<tr style="white-space: nowrap;">
					<td>
							<select size="1" name="report_article_status">
								<option value="0">審核中</option>
								<option value="1">審核成功且刪除</option>
								<option value="2">審核未通過</option>
							</select>
					</td>
					<td>
					<button type="submit" class="btn btn-theme" >送出</button> 
							<input type="hidden" name="action" value="update">
					</td>
				
					<td><input type="hidden" name="report_article_no" value="${reportArticleVO.report_article_no}"/>${reportArticleVO.report_article_no}</td>
					<td><input type="hidden" name="article_no"value="${reportArticleVO.article_no}">${reportArticleVO.article_no}</td>
					<td><input type="hidden" name="member_no"value="${reportArticleVO.member_no}">${reportArticleVO.member_no}</td>
					<td><input type="hidden" name="report_article_content"value="${reportArticleVO.report_article_content}">${reportArticleVO.report_article_content}</td>
					<td><input type="hidden" name="report_article_reason"value="${reportArticleVO.report_article_reason}">${reportArticleVO.report_article_reason}</td>
 					<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
 					
 					<c:set value="${ reportArticleVO.report_article_time }" 
 						var="dateString" /> 
 					<fmt:parseDate value="${dateString}" var="dateObject" 
 						pattern="yyyy-MM-dd HH:mm:ss" /> 

 					<td>${dateObject}<input type="hidden" name="report_article_time" value="${reportArticleVO.report_article_time}"/></td> 
 					
					<td>${report_article_status.get(reportArticleVO.report_article_status.toString())}</td>
					</FORM>
				<td><a  target=_blank href="<%= request.getContextPath()%>/article/article.do?article_no=${reportArticleVO.article_no}&action=getOne_For_Display"><button type="button" class="btn btn-theme" >查看</button> </a></td>
				</tr>
				
				
				</c:if>
			</c:forEach>
		</table>
	</div>
	
		</section>
	</div>
	</div>
	</section>

	
				<h3>
					<i class="fa fa-hand-o-right"></i>已完成審核
				</h3>
				<div class="row mt">
					<div class="col-lg-12">

							<section id="no-more-tables">
								<div>
									<table
										class="table table-bordered table-striped table-condensed cf"
										style="text-align: center; border: 1px #cccccc solid; min-width: 1500px;background-color: #a7a7a7a1;"
										cellpadding="10" border='1'>
										<thead class="cf">
											<tr style="white-space: nowrap;color:white; background-color: #4a4a54;">
				<th></th>
				<th>檢舉文章編號</th>
				<th>文章編號</th>
				<th>檢舉人編號</th>
				<th>檢舉類別</th>
				<th>檢舉內容</th>
				<th>檢舉時間</th>
				<th>檢舉狀態</th>
				<th>查看文章</th>
				<th>修改</th>
				<th></th>
			</tr>
			<c:forEach var="reportArticleVO" items="${list}" >
                   <c:if test="${ reportArticleVO.report_article_status==1}">

				<tr style="white-space: nowrap;">
					
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/report_article/report_article.do"
							style="margin-bottom: 0px;">
							<button type="submit" class="btn btn-theme04" >刪除</button> <input type="hidden"
								name="report_article_no" value="${reportArticleVO.report_article_no}">
							<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/report_article/report_article.do" style="margin-bottom: 0px;">
					<td><input type="hidden" name="report_article_no" value="${reportArticleVO.report_article_no}"/>${reportArticleVO.report_article_no}</td>
					<td><input type="hidden" name="article_no"value="${reportArticleVO.article_no}">${reportArticleVO.article_no}</td>
					<td><input type="hidden" name="member_no"value="${reportArticleVO.member_no}">${reportArticleVO.member_no}</td>
					<td><input type="hidden" name="report_article_content"value="${reportArticleVO.report_article_content}">${reportArticleVO.report_article_content}</td>
					<td><input type="hidden" name="report_article_reason"value="${reportArticleVO.report_article_reason}">${reportArticleVO.report_article_reason}</td>
 					<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
 					<c:set value="${ reportArticleVO.report_article_time }" 
 						var="dateString" /> 
 					<fmt:parseDate value="${dateString}" var="dateObject" 
 						pattern="yyyy-MM-dd HH:mm:ss" /> 

 					<td>${dateObject}<input type="hidden" name="report_article_time" value="${reportArticleVO.report_article_time}"/></td> 
 					
					<td>${report_article_status.get(reportArticleVO.report_article_status.toString())}</td>
					
					
					
						<td><a  target=_blank href="<%= request.getContextPath()%>/article/article.do?article_no=${reportArticleVO.article_no}&action=getOne_For_Display"><button type="button" class="btn btn-theme" >查看</button></a></td>
						<td>
								<select size="1" name="report_article_status">
									<option value="1">審核成功且刪除</option>
									<option value="0">審核中</option>
									<option value="2">審核未通過</option>
								</select>
						</td>
						
						<td>
						<button type="submit" class="btn btn-theme" >送出</button> 
								<input type="hidden" name="report_article_no" 
								value="${reportArticleVO.report_article_no}">
								<input type="hidden" name="action" value="update">
						</td>
					</FORM>
				</tr>
				</c:if>
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
									style="text-align: center; border: 1px #cccccc solid; min-width: 1500px;background-color: #a7a7a7a1;"
									cellpadding="10" border='1'>
									<thead class="cf">
									<tr style="white-space: nowrap;color:white; background-color: #4a4a54;">
				
				<th></th>
				<th>檢舉文章編號</th>
				<th>文章編號</th>
				<th>檢舉人編號</th>
				<th>檢舉類別</th>
				<th>檢舉內容</th>
				<th>檢舉時間</th>
				<th>檢舉狀態</th>
				<th>查看文章</th>
				<th>修改</th>
				<th></th>
			</tr>
			<c:forEach var="reportArticleVO" items="${list}" >
                   <c:if test="${ reportArticleVO.report_article_status==2}">

				<tr style="white-space: nowrap;">
					
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/report_article/report_article.do"
							style="margin-bottom: 0px;">
							<button type="submit" class="btn btn-theme04" >刪除</button> <input type="hidden"
								name="report_article_no" value="${reportArticleVO.report_article_no}">
							<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/report_article/report_article.do" style="margin-bottom: 0px;">
					<td><input type="hidden" name="report_article_no" value="${reportArticleVO.report_article_no}"/>${reportArticleVO.report_article_no}</td>
					<td><input type="hidden" name="article_no"value="${reportArticleVO.article_no}">${reportArticleVO.article_no}</td>
					<td><input type="hidden" name="member_no"value="${reportArticleVO.member_no}">${reportArticleVO.member_no}</td>
					<td><input type="hidden" name="report_article_content"value="${reportArticleVO.report_article_content}">${reportArticleVO.report_article_content}</td>
					<td><input type="hidden" name="report_article_reason"value="${reportArticleVO.report_article_reason}">${reportArticleVO.report_article_reason}</td>
					<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
					<c:set value="${ reportArticleVO.report_article_time }" 
 						var="dateString" /> 
 					<fmt:parseDate value="${dateString}" var="dateObject" 
 						pattern="yyyy-MM-dd HH:mm:ss" /> 

 					<td>${dateObject}<input type="hidden" name="report_article_time" value="${reportArticleVO.report_article_time}"/></td> 
 					
					<td>${report_article_status.get(reportArticleVO.report_article_status.toString())}</td>
					<td><a  target=_blank href="<%= request.getContextPath()%>/article/article.do?article_no=${reportArticleVO.article_no}&action=getOne_For_Display"><button type="button" class="btn btn-theme" >查看</button></a></td>
					
						<td>
								<select size="1" name="report_article_status">
									<option value="1">審核成功且刪除</option>
									<option value="0">審核中</option>
									<option value="2">審核未通過</option>
								</select>
						</td>
						<td>
						<button type="submit" class="btn btn-theme" >送出</button>  
								<input type="hidden" name="report_article_no" 
								value="${reportArticleVO.report_article_no}">
								<input type="hidden" name="action" value="update">
						</td>
					</FORM>
				</tr>
				</c:if>
			</c:forEach>
		</table>	
	</div>
	</section>
	</div>
	</div>
	
	
	
	
</section> <!-- main content-->
<!-- /////////////////////////////////// -->
</section> <!-- container -->
	
</body>
</html>