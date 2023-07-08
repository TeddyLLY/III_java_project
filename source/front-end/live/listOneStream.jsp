<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="liveStreamSvc" scope="page" class="com.livestream.model.HistoryVideoService" />
<% 
String coach_no=(String)session.getAttribute("coach_no");
String member_no=(String)session.getAttribute("member_no");
String whichCoach=request.getParameter("coach_no");
pageContext.setAttribute("whichCoach", whichCoach);
%>
<html >
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script	src="<%=request.getContextPath()%>/front-end/live/js/sweetalert2.all.min.js"></script>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<!-- 新版bootstramp 看情況或許可以放 -->
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>
<script>
   var formId = '';
   function calert(n) {
	  formId = 'form'+n;
	  Swal.fire({
		  title: '確認刪除該影片嗎?',
		  text: "確認刪除後將無法進行復原",
          type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes'
		}).then((result) => {
		  if (result.value) {
	        setTimeout("submitForm()", 1600); 
		    Swal.fire({
		    	title:  '刪除',
		    	text: "該影片已刪除完畢",
		    	type: 'success'
		    })
		  }
		});
	  
   }
   function submitForm() { 
	 document.getElementById(formId).submit()
   }
</script>

<style>
body {
	font-family: 'Open Sans', sans-serif;
	color: #353535;
}

.content h1 {
	text-align: center;
}

.content .content-footer p {
	color: #6d6d6d;
	font-size: 12px;
	text-align: center;
}

.content .content-footer p a {
	color: inherit;
	font-weight: bold;
}

/*	--------------------------------------------------
	:: Table Filter
	-------------------------------------------------- */
.panel {
	border: 1px solid #ddd;
	background-color: #fcfcfc;
}

.panel .btn-group {
	margin: 15px 0 30px;
}

.panel .btn-group .btn {
	transition: background-color .3s ease;
}

.table-filter {
	background-color: #fff;
	border-bottom: 1px solid #eee;
}

.table-filter tbody tr:hover {
	cursor: pointer;
	background-color: #eee;
}

.table-filter tbody tr td {
	padding: 10px;
	vertical-align: middle;
	border-top-color: #eee;
}

.table-filter tbody tr.selected td {
	background-color: #eee;
}

.table-filter tr td:first-child {
	width: 38px;
}

.table-filter tr td:nth-child(2) {
	width: 35px;
}

.ckbox {
	position: relative;
}

.ckbox input[type="checkbox"] {
	opacity: 0;
}

.ckbox label {
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.ckbox label:before {
	content: '';
	top: 1px;
	left: 0;
	width: 18px;
	height: 18px;
	display: block;
	position: absolute;
	border-radius: 2px;
	border: 1px solid #bbb;
	background-color: #fff;
}

.ckbox input[type="checkbox"]:checked+label:before {
	border-color: #2BBCDE;
	background-color: #2BBCDE;
}

.ckbox input[type="checkbox"]:checked+label:after {
	top: 3px;
	left: 3.5px;
	content: '\e013';
	color: #fff;
	font-size: 11px;
	font-family: 'Glyphicons Halflings';
	position: absolute;
}

.table-filter .star {
	color: #ccc;
	text-align: center;
	display: block;
}

.table-filter .star.star-checked {
	color: #F0AD4E;
}

.table-filter .star:hover {
	color: #ccc;
}

.table-filter .star.star-checked:hover {
	color: #F0AD4E;
}

#mytable {
	width: 100%;
	margin-bottom: 280px;
}
video{
z-index:-1;
}
</style>

<title>直播管理</title>
</head>
<body>
<%@ include file="/front-end/gym_index/front-end-navbar.file"%>
	<div class="container" style="width: 800px;">
		<div class="row">
			<section class="content" id="mytable">
				<div class="col-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="pull-right">
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-filter" data-target="all">所有影片</button>
								</div>
							</div>
							<div class="table-container">
								<table class="table table-filter">
									<tbody>
										<c:forEach var="historyVideoVO" items="${liveStreamSvc.getAll()}" varStatus="s">
										
										<c:if test="${historyVideoVO.coach_no==whichCoach}">
										
											<tr data-status="pagado">
												<td>
													<form id="form${s.count}" action="<%=request.getContextPath()%>/InsertOrDelete_StreamServlet" method="POST">
														<input type="hidden" name="action" id="action" value="delete">
														<input type="hidden" name="history_video_no"   value="${historyVideoVO.history_video_no}">
														<input type="button" class="btn btn-danger" value="刪除" onclick="calert(${s.count})">
													</form>
												</td>
												<td><a href="javascript:;" class="star"> <i	class="glyphicon glyphicon-star"></i></a></td>
												<td>
													<div class="media">
														<a class="pull-left">
														    <video width="240" height="160" id="${historyVideoVO.history_video_no}"
														           src="<%= request.getContextPath()%>/BlobReader?history_video_no=${historyVideoVO.history_video_no}"
																   controls="controls" muted>
															</video>
														</a>
														<div class="media-body" style=""><br><br>
															<h5 class="title" class="pull-left">
																上傳日期：	<fmt:formatDate value="${historyVideoVO.history_video_time}"	pattern="yyyy年MM月dd日 HH點mm分ss秒" /></h5>
															
														</div>
													</div>
												</td>
											</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="content-footer"><p></p>
					</div>
				</div>
			</section>

		</div>
		<br><br><br><br><br><br><br><br><br><br><br><br>
	</div>
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
</body>
</html>