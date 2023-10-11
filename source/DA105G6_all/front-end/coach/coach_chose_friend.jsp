<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chat_room.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>
<%
	MemberService memberSvc = new MemberService();
	MemberVO memberVO;
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/coach/coach_center_style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/css/img.css">

<title>選擇聊天室</title>
</head>	

<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="container">

				<!-- coach center bar -->
				<%@ include file="/front-end/coach/coach_bar.file"%>
<%
	 ChatRoomService chatRoomSvc = new ChatRoomService();
	 List<ChatRoomVO> list = chatRoomSvc.findOneCoachAllRoom(coachVO.getCoach_no());
%>
				
				<div class="col-md-8">
  <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
						
  <br>
<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h4 class="display-4">Friend list</h4>
  </div>
</div>
		  <!--  -->		
		      <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="media">
                        <div class="pull-left">
                            <img src="<%= request.getContextPath()%>/CoachPhotoReader?coach_no=${coachVO.coach_no}" style="width:200px; height:200px;" alt="people" class="media-object img-circle">
                        </div>
                        <div class="media-body">
                            <h3 class="media-heading margin-v-5"><a href="#">你好 : ${coachVO.coach_name }</a></h3>
                            <div class="profile-icons">
                                <small><i class="fa fa-users"></i> 你的ID : ${coachVO.coach_no }</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <p class="common-friends">好友列表</p><br>
                    <div class="user-friend-list">

<%
	for(ChatRoomVO var : list){
%>		
<%memberVO = memberSvc.findOneMember(var.getMember_no()); %>
	<form method="post" action="<%= request.getContextPath()%>/chatRoom.do">
                        <a href="#"><br>
                            <img src="<%= request.getContextPath()%>/MemberReader?member_no=${memberVO.member_no}" alt="people" class="img-circle">
                        </a>
                        <h5 class="card-title"><%=memberVO.getMember_name() %></h5>
                        <p class="card-text" style="color:gray;"> 會員編號 : <%=memberVO.getMember_no() %>
                        <input type="hidden" name="chat_room_no" value="<%= var.getChat_room_no()%>">
	  		 			<input type="hidden" name="action" value="changeOneCoachRoom">
		   				 <button class="dropdown-item btn btn-outline-secondary" type="submit" value="<%= memberVO.getMember_no() %>" > 聊天去</button>
					</form> <br>
					<div style="border:1px #cccccc solid;" ></div>
<%
	}
%>
							</div>
						</div>
					</div>
		   <!-- Button trigger modal -->
										<button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#exampleModal">
										 	新增會員
										</button><br>
					</div>
				</div>
				
	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">新增會員</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	<br>

<form method="post" action="<%= request.getContextPath()%>/chatRoom.do">
  <div class="form-group">
    <label for="exampleInputEmail1">輸入會員編號</label>
	<small id="emailHelp" class="form-text text-muted">輸入會員編號來跟會員對話</small>
    <input type="text" name="member_no" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="ex: M001 ">
  </div><br>
  	<input type="hidden" name="action" value="coachInsertOneUser">
    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
  <button type="submit" class="btn btn-primary">新增會員</button>
</form>

      </div>
    </div>
  </div>
</div>
<!-- modal end -->
</body>
	  

</html>