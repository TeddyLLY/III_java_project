<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>
<%
	CoachVO coachVO = (CoachVO) session.getAttribute("coachVO");
%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>

<title>教練審核</title>
</head>

<body>

	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<br>
				<div class="jumbotron jumbotron-fluid">
					<div class="container">
						<h4 class="display-">
							<b>親愛的${coachVO.coach_name}教練您好</b>
						</h4>
						<p class="lead">
						<h6>
							&nbsp&nbsp您必須上傳證照或相關的工作經驗<br>
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp並且填寫您的詳細聯絡資訊<br>
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp上傳後還請靜待審核,審核後方可使用本平台的功能
						</h6>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/coach.do"
				name="form1" enctype="multipart/form-data">
			<div class="form-row">
				<div class="col-md-4">
					<small id="Help" class="form-text text-muted">請上傳相片</small><br>
					<img id="output"
					src="<%=request.getContextPath() %>/tool/images/nottomcat.gif"
<%-- 						src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=<%=coachVO.getCoach_no()%>" --%>
						style="width: 100px; height: 110px;" /> <input type="file"
						accept="image/*" name="coach_photo" onchange="loadFile(event)"
						style="width: 80px; height: auto;" />
				</div>
				<div class="col-md-2">
					<small id="Help" class="form-text text-muted">請上傳你的證照</small><br>
					<img id="output1"
					src="<%=request.getContextPath() %>/tool/images/tomcat.png"
<%-- 						src="<%=request.getContextPath()%>/CoachLicenseReader?coach_no=<%=coachVO.getCoach_no()%>" --%>
						style="height: 100px; width: 110px;" /> <input type="file"
						accept="image/*" name="coach_license" onchange="loadFile1(event)"
						style="width: 80px; height: auto;" />
				</div>
			</div>
			<br>
			<br>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="inputEmail4">手機</label> <small id="Help"
						class="form-text text-muted">ex : 0912345678</small> <input
						type="text" class="form-control" id="coach_cellphone" name="coach_cellphone"
						value="" placeholder="Phone">
				</div>
				<div class="form-group col-md-4">
					<label for="inputEmail4">銀行帳號</label> <small id="Help"
						class="form-text text-muted">ex : 123-123-123456</small> <input
						type="text" class="form-control" id="coach_bank_account" name="coach_bank_account"
						value="" placeholder="Account">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputPassword4">地址</label> 
					<small id="Help" class="form-text text-muted">ex : 320桃園市中壢區中大路300號</small>
					 <input type="text" class="form-control" id="coach_address" name="coach_address"
						value="" placeholder="Address">
				</div>
			</div>
			<br>
			<div class="form-row">
				<div class="form-group">
					<label for="inputAddress">自我介紹</label>
					<div class="form-group">
						<textarea class="form-control" id="coach_introduction"  
							name="coach_introduction"rows="5" placeholder=" 請輸入您的自我介紹"></textarea>
					</div>
					
					<small id="Help" class="form-text text-muted"> ex : <br>
						我是 Rita <br>
						畢業於xx大學營養系，對於營養和健身這方面非常有興趣。而在大學鑽研營養學以及運動營養學之後，發現台灣健身、營養知識有很多可以進步的地方。<br>
					<br>
						大部分的人對於營養的知識較缺少。且在這20年來，學術界對於營養有許多革命性的新知識，大家都不知道。台灣人對於飲食的變化太少，好像就只有金字塔飲食，才是最健康的。在一般的社交網頁上每個人都可以發表文章，發表言論，所以造成了許多錯誤資訊流傳。<br>
					<br> Rita 希望能藉由 Work out anywhere
						的教學能讓大家認識到正確的健身、減肥營養觀念，並希望能影響並且幫助更多人。<br>
					</small>
				</div>
			</div>
			<div class="form-group">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" id="gridCheck" name="check" >
					<label class="form-check-label" for="gridCheck">
						<h6 style="color: gray;">　我已填寫完成</h6>
					</label>
				</div>
			</div>
			<input type="hidden" name="coach_no" value="${coachVO.coach_no }">
				<input type="hidden" name="action" value="updateCoachDetail"> 
				
			<input 	type="button" class="btn btn-outline-success  btn-lg"  class="btn btn-outline-success" value="取消"
					onclick="location.href='<%=request.getContextPath()%>/front-end/gym_index/index.jsp'">
			<input 	type="submit"  class="btn btn-info btn-lg" value="送出">
				<input type="button" class="magic btn btn-outline-success" value="神奇小按鈕">
		</form>
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
			$("#coach_introduction").val('我是小龍女 , 畢業於古墓技校 , 希望找個學員來陪我練功抵禦金輪法王\n 拯救國家的重責大任就靠我們了 , 趕快來報名我的課程!!!');
			$("#coach_address").val('台中市霧峰區光明路27巷28號');
			$("#coach_bank_account").val('123-123-123456');
			$("#coach_cellphone").val('0912345678');
			$("#").val('');
		})
</script>

</html>