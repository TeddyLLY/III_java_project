<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file" %>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/coach_no_lock/coach_center_style.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/in/css/bg.css">

    <title>審核失敗</title>
</head>

<body>

<!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>


    <div class="jumbotron " style="background-image: <%=request.getContextPath()%>/tool/images/641.jfif;">
        <div class="container">
            <div class="col-md-12">
                <h3 class="display-4">錯誤!!</h3>
                <img src="<%=request.getContextPath()%>/tool/images/641.jfif">
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card text-center">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs">
                            <li class="nav-item">
                                <a class="nav-link active" href="#">Woops!</a>
                            </li>
                    </div>
                    <div class="card-body">
                        <h3 class="card-title"><b>審核失敗</b></h3>
                        <p class="card-text">
                            <h6 style="color: #181838">
                                親愛的教練您好<br>
                                您的審核或教練資格失效了了!<br>
                                您必須重新審核以使用本平台功能<br>
                            </h6>
                        </p><br>
                        <a href="<%=request.getContextPath() %>/front-end/gym_index/index.jsp" class="btn btn-outline-secondary btn-lg">回首頁</a>
                        <a href="<%=request.getContextPath() %>/front-end/coach_no_lock/coach_review.jsp" class="btn btn-success btn-lg">重新審核</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
    
</body>

<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>

<script>
	var loadFile1 = function(event) {
		var output1 = document.getElementById('output1');
		output1.src = URL.createObjectURL(event.target.files[0]);
	};
</script>

</html>