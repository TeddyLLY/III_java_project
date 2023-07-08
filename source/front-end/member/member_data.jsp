<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.toolclass.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   

<!DOCTYPE html>
<html lang="en">

<head>

<!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>

<!-- 新版bootstramp 看情況或許可以放 -->
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>


     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/member/member_center_style.css">
    <title>會員個資</title>
</head>

<body>

<!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>


    <div class="container">
        <div class="col-md-12">
            <div class="row">
               
<!-- member center bar -->
<%@ include file="/front-end/member/member_bar.file" %>
               
                <div class="col-md-8">
                <br><br>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item active" aria-current="page">會員資料</li>
                        </ol>
                    </nav>
                    <ul class="list-group">
                        <li class="list-group-item list-group-item-info">審核狀態 : ${member_review.get(memberVO.member_review.toString()) } </li><br>
                        <li class="list-group-item ">權限狀態 : ${member_auth.get(memberVO.member_auth.toString()) }</li><br>
                    </ul>
                    
                    <ul class="list-group">
                        <br> 
                        <li class="list-group-item list-group-item-secondary">會員編號 : ${memberVO.member_no }</li>
                        <li class="list-group-item">*姓名 : ${memberVO.member_name }</li>
                        <li class="list-group-item list-group-item-secondary">*性別 : ${memberVO.member_sex }</li>
                        <li class="list-group-item ">*手機 : ${memberVO.member_cellphone } </li>
                        <li class="list-group-item list-group-item-secondary">信箱 : ${memberVO.member_account }</li>
                        <li class="list-group-item ">可用點數 : ${memberVO.member_points }</li>
                        <li class="list-group-item list-group-item-secondary">*信用卡 : ${memberVO.member_card }</li>
                        <li class="list-group-item ">*身高 : ${memberVO.member_height }</li>
                        <li class="list-group-item list-group-item-secondary">*體重 : ${memberVO.member_weight }</li>
                        <li class="list-group-item ">BMI :
							${(memberVO.member_weight / ( memberVO.member_height * memberVO.member_height))/100}
                         </li>
                        <li class="list-group-item list-group-item-secondary">*體脂 : ${memberVO.member_bodyfat }</li>
                        <li class="list-group-item ">*臂展 : ${memberVO.member_wing_span }</li>
                        <li class="list-group-item list-group-item-secondary">*地址 : ${memberVO.member_address }</li>
                        <br>
                    </ul>
                    <form action="">
                    <input type="button" class="btn btn-secondary btn-lg btn-block"  value="修改資料" onclick="location.href='<%= request.getContextPath()%>/front-end/member/member_modify.jsp'"/>
                        <small id="emailHelp" class="form-text text-muted">* 可修改</small>
                    </form><br>
                </div>
            </div>
        </div>
    </div>
    
<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>

    
</body>

</html>