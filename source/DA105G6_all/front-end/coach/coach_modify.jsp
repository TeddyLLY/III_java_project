<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html lang="en">

<head>

<!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<!-- 新版bootstramp 看情況或許可以放 -->
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/coach/coach_center_style.css">
<title>教練個資維護</title>
</head>

<body>

	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>


	<div class="container">

		<div id="4">
			<!-- member center bar -->
			<%@ include file="/front-end/coach/coach_bar.file"%>
		</div>

		<div class="col-md-8">
			<br>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item active" aria-current="page">修改資料</li>
				</ol>
			</nav>



			<form METHOD="post" ACTION="<%=request.getContextPath()%>/coach.do"
				name="form1" enctype="multipart/form-data">


				<div class="form-row">
					<div class="col-md-4">
						<label for="exampleInputEmail1">大頭貼</label> <small id="Help"
							class="form-text text-muted">Picture</small> <img id="output"
							src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=<%=coachVO.getCoach_no()%>"
							style="width: 200px; height: 150px;" /> <input type="file"
							accept="image/*" name="coach_photo" onchange="loadFile(event)"  />
					</div>


					<div class="col-md-4">
						<label for="exampleInputEmail1">證照</label> <small id="Help"
							class="form-text text-muted"> License</small> <img id="output1"
							src="<%=request.getContextPath()%>/CoachLicenseReader?coach_no=<%=coachVO.getCoach_no()%>"
							style="height: 150px; width: 200px;" /> <input type="file"
							accept="image/*" name="coach_license" onchange="loadFile1(event)" />
					</div>

				</div>
				<br>


				<div class="form-row">

					<div class="form-group col-md-2">
						<label for="exampleInputEmail1">姓名</label> <small id="emailHelp"
							class="form-text text-muted">Name</small>
							<%=coachVO.getCoach_name()%>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputEmail1">手機</label> <small id="emailHelp"
							class="form-text text-muted">Phone</small> <input type="text"
							class="form-control" id="coach_cellphone"
							name="coach_cellphone" aria-describedby="emailHelp"
							value="<%=coachVO.getCoach_cellphone()%>">
					</div>
					<div class="form-group col-md-1"></div>
					<div class="form-group col-md-5">
						<label for="exampleInputPassword1">性別</label> <small
							id="emailHelp" class="form-text text-muted">Confirm sex </small>
						<select name="coach_sex">
							<option value="<%=coachVO.getCoach_sex()%>"><%=coachVO.getCoach_sex()%></option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</div>

				</div>
				<div class="form-row">
					<div class="form-group col-md-6 ">
						<div>
							<label for="exampleInputEmail1">銀行帳號</label> <small
								id="emailHelp" class="form-text text-muted">Credit card</small>
							<input type="text" class="form-control" id="coach_bank_account"
								name="coach_bank_account" aria-describedby="emailHelp"
								value="<%=coachVO.getCoach_bank_account()%>">
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="exampleInputEmail1">地址</label> <small id="emailHelp"
							class="form-text text-muted">Address</small> <input type="text"
							class="form-control" id="coach_address" name="coach_address"
							aria-describedby="emailHelp"
							value="<%=coachVO.getCoach_address()%>">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="exampleFormControlTextarea1">個人介紹</label> <small
							id="emailHelp" class="form-text text-muted">Introduction</small>
						<textarea class="form-control" id="coach_introduction"
							rows="7" name="coach_introduction"><%=coachVO.getCoach_introduction()%> </textarea>
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">修改密碼</label> <small
							id="emailHelp" class="form-text text-muted">Password</small> <input
							type="password" class="form-control" id="exampleInputPassword1"
							name="coach_password" placeholder="如要修改密碼請在這填寫您要修改的密碼 ">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">確認密碼</label> <small
							id="emailHelp" class="form-text text-muted">Confirm
							passward </small> <input type="password" class="form-control" name="coach_password_check"
							id="exampleInputPassword1" placeholder="請再輸入一次密碼">
					</div>
				</div>

				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" id="exampleCheck1" name="check" value="check">
					<label class="form-check-label" for="exampleCheck1">
						<h6 style="color: gray;"> 　確認更改</h6>
						<br>
					</label>
				</div>

				<input type="hidden" name="coach_no" value="${coachVO.coach_no }">
				<input type="hidden" name="action" value="coachUpdate"> <input
					type="button" class="btn btn-outl ine-success  btn-lg" value="取消"
					onclick="location.href='<%=request.getContextPath()%>/front-end/coach/coach_center.jsp'">

				<button type="submit" class="btn btn-info  btn-lg">確認修改</button>
					<input type="button" class="magic btn btn-info  btn-lg" value="神奇小按鈕">
				
			</form>
		</div>
	</div>

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>


</body>

<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};

	var loadFile1 = function(event) {
		var output1 = document.getElementById('output1');
		output1.src = URL.createObjectURL(event.target.files[0]);
	};
	
	$(".magic").click(function(){
		$("#coach_introduction").val('一個完善的健身計劃包括心肺訓練、重量訓練、和培養靈活性及柔軟度。要了解如何把這些訓練結合在一起，其實不是簡單的事情。 如果您正想尋求最適合您的有效健身方式，但不知該從哪個器材或課程開始，我可為您客製化設計一個最專業的專屬健身計畫，滿足您的目標及需求，幫您掌握住時間和難易度，一步步幫助達成，不但節省很多您自己摸索的時間及避免運動傷害，又能快速地建立信心，讓自己充滿動力的正確地寵愛照顧自己');
		$("#coach_address").val('台北市北投區光明路27巷28號');
		$("#coach_bank_account").val('877-101-396369');
		$("#coach_cellphone").val('0987563215');
    })
</script>

</html>