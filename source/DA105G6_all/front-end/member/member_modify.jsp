<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.toolclass.*"%>



<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/member/member_center_style.css">
<title>會員個資維護</title>
</head>

<body>

	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="container">

		<!-- member center bar -->
		<%@ include file="/front-end/member/member_bar.file"%>

		<div class="col-md-8">
			<br>
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item active" aria-current="page">修改資料</li>
				</ol>
			</nav>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<form METHOD="post" ACTION="<%=request.getContextPath()%>/member.do"
				name="form1" enctype="multipart/form-data">


				<div class="form-row">
					<div class="col-md-2">
						<label for="exampleInputEmail1">大頭貼</label>
						 <small id="Help" class="form-text text-muted">Picture</small> 
						 <img id="output" src="<%=request.getContextPath()%>/MemberReader?member_no=<%=memberVO.getMember_no()%>"
						 style="width: 200px; height: auto;" /><br>
						 <input type="file" accept="image/*" name="member_photo" onchange="loadFile(event)" >
					</div>
				</div>
				<br>

				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="exampleInputEmail1">姓名</label> <small id="emailHelp"
							class="form-text text-muted">Name</small> <%= memberVO.getMember_name() %>
					</div>
					<div class="form-group col-md-4">
						<label for="exampleInputEmail1">手機</label> <small id="emailHelp"
							class="form-text text-muted">Phone</small> <input type="text"
							class="form-control" id="member_cellphone"
							name="member_cellphone" aria-describedby="emailHelp"
							value="${memberVO.member_cellphone }">
					</div>
					<div class="form-group col-md-6">
						<label for="exampleInputEmail1">信用卡</label> <small id="emailHelp"
							class="form-text text-muted">Credit card</small> <input
							type="text" class="form-control" id="member_card"
							name="member_card" aria-describedby="emailHelp"
							value="${memberVO.member_card }">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="exampleInputEmail1">身高</label> <small id="emailHelp"
							class="form-text text-muted">Height</small> <input type="number"
							class="form-control" id="member_height" name="member_height"
							aria-describedby="emailHelp" value="${memberVO.member_height }">
					</div>
					<div class="form-group col-md-3">
						<label for="exampleInputEmail1">體重</label> <small id="emailHelp"
							class="form-text text-muted">Weight</small> <input type="number"
							class="form-control" id="member_weight" name="member_weight"
							aria-describedby="emailHelp" value="${memberVO.member_weight }">
					</div>
					<div class="form-group col-md-3">
						<label for="exampleInputEmail1">體脂</label> <small id="emailHelp"
							class="form-text text-muted">Body fat</small> <input type="number"
							class="form-control" name="member_bodyfat" step="0.01"
							id="member_bodyfat" aria-describedby="emailHelp"
							value="${memberVO.member_bodyfat }">
					</div>
					<div class="form-group col-md-3">
						<label for="exampleInputEmail1">臂展</label> <small id="emailHelp"
							class="form-text text-muted">Wing span</small> <input type="number"
							class="form-control" name="member_wing_span"
							id="member_wing_span" aria-describedby="emailHelp"
							value="${memberVO.member_wing_span }">
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">地址</label> <small id="emailHelp"
						class="form-text text-muted">Address</small> <input type="text"
						class="form-control" id="member_address" name="member_address"
						aria-describedby="emailHelp" value="${memberVO.member_address }">
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">修改密碼</label> <small
							id="emailHelp" class="form-text text-muted">Passward</small> <input
							type="password" class="form-control" id="exampleInputPassword1"
							name=member_password placeholder="如要修改密碼請在這填寫您的密碼">
					</div>
					<div class="form-group col-md-1"></div>
					<div class="form-group col-md-5">
						<label for="exampleInputPassword1">性別</label> <small
							id="emailHelp" class="form-text text-muted">Confirm sex </small>
						<select name="member_sex">
							<option value="<%=memberVO.getMember_sex()%>"><%=memberVO.getMember_sex()%></option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">確認密碼</label> <small
							id="emailHelp" class="form-text text-muted">Confirm
							passward </small> <input type="password" name="member_password_check"
							class="form-control" id="exampleInputPassword1"
							placeholder="請再輸入一次密碼">
					</div>

				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" id="exampleCheck1" name="check" value="check">
					<label class="form-check-label" for="exampleCheck1">
						<h6 style="color: gray;">　確認更改</h6>
						<br>
					</label>
				</div>



				<input type="hidden" name="action" value="MemberUpdate"> <input
					type="button" class="btn btn-outline-success  btn-lg" value="取消"
					onclick="location.href='<%=request.getContextPath()%>/front-end/member/member_center.jsp'">
				<button type="submit" class="btn btn-info  btn-lg">確認</button>
				<input type="button" class="magic btn btn-info  btn-lg" value="神奇小按鈕">
				
		</div>
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
	
	$(".magic").click(function(){
    	$("#member_address").val('台中市霧峰區光明路27巷28號');
    	$("#member_wing_span").val('88');
    	$("#member_bodyfat").val('10.1');
    	$("#member_weight").val('88');
    	$("#member_height").val('188');
    	$("#member_card").val('5896-7878-0123-6480');
    	$("#member_cellphone").val('0945389631');
    })
</script>
</html>