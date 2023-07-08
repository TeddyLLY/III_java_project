<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chat_room.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.coach.model.*"%>
<!-- "onkeydown='if (event.keyCode == 13) sendMessage();'  contenteditable='true'" -->
<%
	CoachService coachSvc = new CoachService();
	CoachVO coachVO;
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/member/member_center_style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/css/img.css">

<title>選擇聊天室</title>
</head>

<body>
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="container">
		
			<!-- member center bar -->
				<%@ include file="/front-end/member/member_bar.file"%>
<%
	 ChatRoomService chatRoomSvc = new ChatRoomService();
	 List<ChatRoomVO> list = chatRoomSvc.findOneMemberAllRoom(memberVO.getMember_no());
%>
				
				<div class="col-md-8">
  <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
			</c:if>
						<br>
<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h4 class="display-4">好友列表</h4><br>
  </div>
</div>

    <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="media">
                        <div class="pull-left">
                            <img src="<%= request.getContextPath()%>/MemberReader?member_no=${memberVO.member_no}" style="width:200px; height:200px;" alt="people" class="media-object img-circle">
                        </div>
                        <div class="media-body">
                            <h3 class="media-heading margin-v-5"><a href="#">你好 :  ${memberVO.member_name }</a></h3>
                            <div class="profile-icons">
                                <small><i class="fa fa-users"></i> 你的ID : ${memberVO.member_no }</small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <p class="common-friends">Friends list</p>
                    <div class="user-friend-list">
<%
	for(ChatRoomVO var : list){
%>		
<%coachVO = coachSvc.findOneCoach(var.getCoach_no()); %>
				<form method="post" action="<%= request.getContextPath()%>/chatRoom.do">
                        <a href="#"><br>
                            <img src="<%= request.getContextPath()%>/CoachPhotoReader?coach_no=<%= coachVO.getCoach_no()%>" alt="people" class="img-circle">
                        </a>
                        <h5 class="card-title"><%=coachVO.getCoach_name() %></h5>
                        <p class="card-text" style="color:gray;"> 教練編號 : <%=coachVO.getCoach_no() %>
                        <input type="hidden" name="chat_room_no" value="<%= var.getChat_room_no()%>">
		  			 	<input type="hidden" name="action" value="changeOneMemberRoom">
					    <button class="dropdown-item btn btn-outline-secondary" type="submit" value="<%= var.getCoach_no() %>"  > 聊天去</button>
					</form> <br>
					<div style="border:1px #cccccc solid;" ></div>
<%
	}
%>
						</div>
                    </div>
                </div>
										<button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#exampleModal">
										 	新增教練
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
        <h5 class="modal-title" id="exampleModalLabel">新增教練</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

<form method="post" action="<%= request.getContextPath()%>/chatRoom.do">
  <div class="form-group">
    <label for="exampleInputEmail1">輸入教練編號</label>
	<small id="emailHelp" class="form-text text-muted">輸入教練編號來跟教練對話</small>
    <input type="text" name="coach_no" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="ex: C001 ">
  </div><br>
  	<input type="hidden" name="action" value="memberInsertOneUser">
    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
  <button type="submit" class="btn btn-primary">新增教練</button>
</form>

      </div>
    </div>
  </div>
</div>
<!-- modal end -->
</body>
	  

</html>