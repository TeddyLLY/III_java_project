<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
	<!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>

<!-- 新版bootstramp 看情況或許可以放 -->
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/coach/coach_center_style.css">
    <title>教練個資</title>
</head>

<body>
<!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>

    <div class="container">
        <div class="row">

<!-- coach center bar -->
<%@ include file="/front-end/coach/coach_bar.file" %>

            <div class="col-md-8"><br><br>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item active" aria-current="page">教練資料</li><br>
                    </ol>
                </nav>
                <ul class="list-group">
                    <li class="list-group-item list-group-item-info">審核狀態 : ${coach_review.get(coachVO.coach_review.toString())} </li><br>
                    <li class="list-group-item ">權限狀態 : ${coach_auth.get(coachVO.coach_auth.toString())}</li><br>
                </ul>
                <ul class="list-group">
                    <br> 
                    <li class="list-group-item list-group-item-secondary">教練編號 : ${coachVO.coach_no }</li>
                    <li class="list-group-item">*姓名 : ${coachVO.coach_name }</li>
                    <li class="list-group-item list-group-item-secondary">*性別 : ${coachVO.coach_sex }</li>
                    <li class="list-group-item ">*手機 : ${coachVO.coach_cellphone }</li>
                    <li class="list-group-item list-group-item-secondary"> 信箱 : ${coachVO.coach_account }</li>
                    <li class="list-group-item "> 總收益 : ${coachVO.coach_income }</li>
                    <li class="list-group-item list-group-item-secondary">*銀行帳號 : ${coachVO.coach_bank_account }</li>
                    <li class="list-group-item ">*地址 : ${coachVO.coach_address }</li>
                    <li class="list-group-item list-group-item-secondary">*自我介紹 : <br>${coachVO.coach_introduction }</li>
                   	<td>*照片 : <img id="output"
				src="<%= request.getContextPath()%>/CoachPhotoReader?coach_no=${coachVO.coach_no}"
				style="height: 50px; width: 50px;" />
                    *證照 : <img id="output"
				src="<%= request.getContextPath()%>/CoachLicenseReader?coach_no=${coachVO.coach_no}"
				style="height: 50px; width: 50px;" /></td><br>
                    <br>
                </ul>
                 <input type="button" class="btn btn-secondary btn-lg btn-block"  value="修改資料" onclick="location.href='<%= request.getContextPath()%>/front-end/coach/coach_modify.jsp'"/>
                    <small id="emailHelp" class="form-text text-muted">* 可修改</small>
                <br>
            </div>
        </div>
    </div>
    
<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
    
</body>

</html>