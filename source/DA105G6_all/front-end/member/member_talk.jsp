<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.toolclass.*"%>
<%@ page import="com.chat_room.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.coach.model.*" %>
<%
	CoachService coachSvc = new CoachService();
	CoachVO coachVO;
%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/css/chat_room.css">
<link rel="stylesheet" type="text/css" href="member_center_style.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/in/css/bg.css">


<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/mervick-emojionearea-99129f7/dist/emojionearea.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/mervick-emojionearea-99129f7/dist/emojionearea.js"></script>

<title>會員聊天室</title>

</head>

<body  onload="connect();" onunload="disconnect();">
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>
	<!-- nav_bar 放body頭 -->
	
	<div class="container">
		<div class="col-md-12">
			<div class="row">

				<!-- member center bar -->
				<%@ include file="/front-end/member/member_bar.file"%>
<%
	 ChatRoomService chatRoomSvc = new ChatRoomService();
	String chat_room_no = request.getParameter("chat_room_no");
	ChatRoomVO chatRoomVO = chatRoomSvc.findByPk(chat_room_no);
%>

				<div class="col-md-8">
  <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
			</c:if>
	<%coachVO = coachSvc.findOneCoach(chatRoomVO.getCoach_no()); %>
		
		 <h1> Chat room with <%=coachVO.getCoach_name() %></h1>	
		 <h3 id="statusOutput" class="statusOutput" style="color:gray;"></h3> 		
		 					<form method="post" action="<%= request.getContextPath()%>/chatRoom.do">
							    	<input type="hidden" name="chat_room_no" value="${chat_room_no }">
									<input type= "hidden" name="member_no" value="${memberVO.member_no }" > 						    		
						    		<input type="hidden" name="action" value="deleteMemberOneUserRoom">
						    			<button type="submit" class="btn btn-outline-danger" onclick="disconnect();">
										 	刪除聊天訊息與好友
										</button> 
							</form>		    
<div class="chatroom">
        <div class="chatroom_section chatroom_header">
            <div class="chatroom_section chatroom_header_name"><%=coachVO.getCoach_name() %>
            </div>
        </div>
        
        <div class="chatroom_section chatroom_history" >	
        	<ul class="chatroom_history_list" >
	        	<div id="messagesArea" style=" overflow: scroll;">
        	
        		</div>
    		</ul>
        </div>
        
            <div class="chatroom_history_entering">
                <div class="chatroom_history_entering_spinner"></div>
                <span class="chatroom_history_entering_label"></span>
            </div>
            
	     <div class="chatroom_section chatroom_form">
	            	<input id="userName"  type="hidden" value="${memberVO.member_name }" placeholder="使用者名稱"/>
	                <input type="text"  id="emojionearea1"  class="chatroom_form_inputbox " placeholder="enter a message"  onkeydown="if (event.keyCode == 13) sendMessage();"/>
			        <button type="submit"   id="sendMessage" class="chatroom_form_sendBtn btn btn-secondary" onclick="sendMessage();">送出</button>
	                <input type="button" id="connect"   class="btn btn-outline-success" class="button" value="連線" onclick="connect();"/>
					<input type="button" id="disconnect"  class="btn btn-outline-danger" class="button" value="離線" onclick="disconnect();"/>
	     </div>
	</div>
</div>
				<!--  -->
					</div>
				</div>
			</div>

	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>


</body>
	  
<script>
    var MyPoint = "/WebSocketChat/${memberVO.member_name}/<%= chat_room_no  %>";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
		  var RightHead = '<li class="chatroom_history_list_item chatroom_history_list_right chatroom_history_list_item--blue" >';
		  var LeftHead = '<li class="chatroom_history_list_item chatroom_history_list_left chatroom_history_list_item--grey" >';
		  var SystemHead ='<li class="chatroom_history_list_item chatroom_history_list_left chatroom_history_list_item--system" >';
		  var End ='</li>';
		  var coachPhoto = '<img id="output" src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=<%=coachVO.getCoach_no()%>"style="width: 30px; height: 30px; border-radius: 50%; " />' ;
			

	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("${memberVO.member_name} 你已上線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        
	        if("${memberVO.member_name }" == jsonObj.userName && "<%= chat_room_no  %>" == jsonObj.room ){
	        	 var message =  jsonObj.message  +"\r\n" ;
	  	        messagesArea.innerHTML = messagesArea.innerHTML + RightHead + message + End;
	  	      messagesArea.scrollTop = messagesArea.scrollHeight;
	  	      
	        }else if('<%=coachVO.getCoach_name() %>' ==  jsonObj.userName && "<%= chat_room_no  %>" == jsonObj.room ){
	        	 var message =  jsonObj.userName + ": " + jsonObj.message + "\r\n" ;
	  	        messagesArea.innerHTML = messagesArea.innerHTML + LeftHead + coachPhoto + "  " + message + End;
	  	      messagesArea.scrollTop = messagesArea.scrollHeight;
	  	      
	        }else if ('<%= chat_room_no  %>' == jsonObj.userName && "<%= chat_room_no  %>" == jsonObj.room ){
	        	 var message =   jsonObj.message + "\r\n" ;
		  	        messagesArea.innerHTML = messagesArea.innerHTML + SystemHead +  message + End;
		  	      messagesArea.scrollTop = messagesArea.scrollHeight;
	        }
		};
		
		webSocket.onclose = function(event) {
			updateStatus("${memberVO.member_name} 你已離線");
			 messagesArea.innerHTML = "";
		};
	}
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();

//////////////////////////////////// test//////////////////////////////////////////////
	function sendMessage() {
		
		 var userName = inputUserName.value.trim();
		    if (userName === ""){
		        alert ("使用者名稱請勿空白!");
		        inputUserName.focus();	
				return;
		    }
		    
	    var inputMessage = document.getElementsByClassName("emojionearea-editor")[0];
	    var message = inputMessage.innerHTML.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "room" : "<%= chat_room_no  %>" ,  "message" : message };
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.innerHTML = "";
	        inputMessage.focus();
	    }
	}
	
	$(document).ready(function() {
		$("#emojionearea1").emojioneArea({
			pickerPosition : "top",
			tonesStyle : "bullet"
		});
	});

///////////////////////////////////////////////////////////////////////////////
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	
</script>
	
</html>