<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="com.lesson_detail.model.*"%>
<%@ page import="com.favorite_lesson.model.*"%>
<!DOCTYPE html>
<%	LessonVO lessonVO = (LessonVO) request.getAttribute("lessonVO");
	String coachNo = lessonVO.getCoachNo();
	CoachService cSvc = new CoachService();
	CoachVO coachVO = cSvc.findOneCoach(coachNo);

LessonDetailService ldSvc = new LessonDetailService();
List<LessonDetailVO> list =ldSvc.getDetailByLesson(lessonVO.getLessonNo());

String base64Image = Base64.getEncoder().encodeToString(lessonVO.getLessonPicture());%>

<html>
<head>

<title><%=lessonVO.getLessonName()%></title>
<!-- jQuery -->
<script src="<%=request.getContextPath()%>/lesson_pkg/js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/lesson_pkg/plugins/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/lesson_pkg/plugins/bootstrap/bootstrap.min.js"></script>
<!-- Font Awesome -->
<link href="<%=request.getContextPath()%>/lesson_pkg/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- Owl Carousel -->
<link href="<%=request.getContextPath()%>/lesson_pkg/plugins/slick-carousel/slick/slick.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lesson_pkg/plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
<!-- Fancy Box -->
<link href="<%=request.getContextPath()%>/lesson_pkg/plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lesson_pkg/plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/lesson_pkg/plugins/seiyria-bootstrap-slider/dist/css/bootstrap-slider.min.css" rel="stylesheet">
<!-- CUSTOM CSS -->
<link href="<%=request.getContextPath()%>/lesson_pkg/css/style.css" rel="stylesheet">
<!-- sweetAlert2 -->
<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.js"></script>
<!-- air datePicker -->
<link href="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.min.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.zh.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/mervick-emojionearea-99129f7/dist/emojionearea.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/mervick-emojionearea-99129f7/dist/emojionearea.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/css/chat_room.css">
</head>
<style type="text/css" media="screen">
*{
font-family:'Noto Sans TC', sans-serif;
}
.card-img-top {
	width: 270px;
	height: 140px;
}

.page-search {
	background: #C6E1FF;
	padding: 20px;
}

#searchbtn {
	margin-left: 10px;
	width: 150px;
	height: 50px;
}

#inputtext4 {
	margin-left: 30%;
	background-color:white;
}

h4.card-title {
	height: 50px;
}

.card-text {
	height: 50px;
}

.product-item .product-ratings .selected i {
	color: #FDEB00;
}
a.fa.fa.fa-heart {
	margin-top: 20px;
	color:red;
	cursor: pointer;
	  transform: scale(1.2) translateZ(0);
}

a.fa.fa.fa-heart-o {
	margin-top: 20px;
	cursor: pointer;
}

div.menu{
	padding:0px;
}

div.border1 {
    border:5px solid #00BFF0;
}

a.fa.fa.fa-heart-o:hover {
  -webkit-transform: scale(1.2) translateZ(0);
  transform: scale(1.2) translateZ(0);
}
.widget.price{
background:#ff7600d1;
border-radius: 5px;
cursor: pointer;
}
.addfavorite1{
 cursor: pointer;
 background: #ffdf6f;
 padding: 10px 10px 15px;
 margin-bottom: 30px;
 border-radius: 5px;
}
#appointcoach{
float:right;
border:5px #c4ffbbc7 solid;
font-size:5px;
cursor: pointer;
background-color: #c4ffbbc7;
}
#datemy .datepicker{
 height: 270px;
 width: 190px;
}
.datepicker--cell.bussycoach{
background-color:#f1eddf;
}
/* **schedule color** */
.datepicker--cell.schedule{
color: #fff;
background-color:#c9c9c9;
}
.datepicker--cell.schedule.-other-month-{
background-color:#d5d5d5
}
.datepicker--cell.schedule.-disabled-{
background-color:#ecebeb
}
/* **lesson color** */
.datepicker--cell.lesson{
color: #fff;
background-color:#82ef5c;
}
.datepicker--cell.lesson.-other-month-{
background-color:#b5f29f
}
.datepicker--cell.lesson.-disabled-{
background-color:#b5f29f
}
/* **appointment color** */
.datepicker--cell.appointment{
color: #fff;
background-color:#ffc653;
}
.datepicker--cell.appointment.-other-month-{
background-color:#ffe4ae;
}
.datepicker--cell.appointment.-disabled-{
background-color:#ffe4ae;
}
/* ------------ */

/* The popup chat - hidden by default */
.chat-popup {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 500px;
  padding: 10px;
  background-color: white;
}

/* Full-width textarea */
.form-container .divtext {
  width: 100%;
  height:100%;
}

.form-container input {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
  resize: none;
}

/* When the textarea gets focus, do something */
.form-container input:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/send button */
.form-container .btn {
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  margin-bottom:10px;
  opacity: 0.8;
}

/* Add a red background color to the cancel button */
div .cancel {
  background:#72d0f4;	
  display: flex;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}
.chatroom_form_inputbox {
   max-width: 100%;
}

</style>
<body class="body-wrapper" onunload="disconnect();">
<!-- header -->
   <!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>
	<!-- end menu -->
<div class="about_banner_img"><img src="<%=request.getContextPath()%>/lesson_pkg/images/workoutmain1.jpg" class="img-responsive" alt="" style='height: 100%; width: 100%; object-fit: contain'/></div>
	<div class="about_banner_wrap">
      	<h1 class="m_11">Progressing</h1>
       </div>
      <div class="border1"> </div>
      
      
      
<!-- header -->
	<section class="section-sm">
		<div class="container">

			<form METHOD="post" ACTION="<%=request.getContextPath()%>/lesson/lesson.do">
			<div class="form-group row" id="searchbar">
			<input type="text" name="CONCAT(LESSON_NAME,LESSON_CONTENT)" class="form-control col-md-4" id="inputtext4" placeholder="搜尋課程">
        	<input type="hidden" name="action" value="search_lesson2">
        			
			<select id = "lesson_Class" name = "lesson_Class" >
			<option value="" >全部</option>
			<c:forEach varStatus = "varClass" begin = "0" end = "4">
			<option value ="${varClass.index}" ${(varClass.index == lessonVO.lessonClass) ? 'selected' : '' }>${lesson_class[varClass.index]} </option>
			</c:forEach>
			</select>       			
					<button type="button" class="btn btn-primary col-md-2"
						id="searchbtn" onClick='CheckForm(this.form)'>搜尋</button>
				</div>
			</form>
			
<%@ include file="left_bar.file" %>

				<div class="col-md-9">							
	<div class="container">
		<div class="row" style="background-color:white;">
			<!-- Left sidebar -->
			<div class="col-md-8" style="border-top-width: 10px;">
				<div class="product-details">
					<h1 class="product-title" style="margin-bottom: 30px;padding-top: 30px;padding-left: 15px;"><%=lessonVO.getLessonName()%></h1>

						<div class="carousel-inner">
							<div class="carousel-item active">
								<img class="d-block w-100" src="data:image/jpeg;base64,<%=base64Image%>" alt="First slide">
							</div>
						</div>
						<br>
					<div class="product-meta">	
						<ul class="list-inline">
							<li class="list-inline-item"><i class="fa fa-hourglass-half "> 報名截止日:</i> ${lessonVO.lessonEndDate}</li>
						<li class="list-inline-item" id="num1"><i class="fa fa-user-o "></i> 人數: <%=lessonVO.getLessonRegistration()%>/  <%=lessonVO.getLessonMaximumPeople()%></li>
							<li class="list-inline-item"><i class="fa fa-location-arrow "></i>地點:  ${lessonVO.lessonLocation}</li>
						</ul>
						
					</div>
				
					<div class="content" style="padding-top: 10px;">
	
						<div class="tab-content" id="pills-tabContent" style="padding-top: 10px;">
							<div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
								<h3 class="tab-title">課程介紹</h3>
								<p><%=lessonVO.getLessonContent()%></p>
								<h3 class="tab-title">上課日期</h3>
								<%for(LessonDetailVO ldVO:list){%>
									<p><%=ldVO.getLessonDate()%></p>
								<%} %>
	
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="sidebar">
					<div class="widget price text-center" id="btn${lessonVO.lessonNo}" style="margin-top: 100px;" onClick="joinLesson('${lessonVO.lessonNo}','${lessonVO.lessonPrice}')">
						<h4>參加課程</h4>
						<p>$<%=lessonVO.getLessonPrice()%></p>
					</div>
					<div class="addfavorite1" style="text-align:center"  onClick="favorite('${lessonVO.lessonNo}')"><h4><b>
					<a id="heart${lessonVO.lessonNo}" class="fa fa-heart-o fa-lg"></a> 收藏課程</b></h4>
					</div>
					
<!-- coach Profile widget -->
					<div class="widget user">
						<img onclick="openForm();connect();"  class="rounded-circle" src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=<%=coachNo%>" style=" width:200px; hight:200px; cursor:pointer;">
						<h5><a href=""><%=coachVO.getCoach_name()%></a><a id='appointcoach' onClick="appointment('${memberVO.member_no}','${lessonVO.coachNo}')">預約教練</a></h5>
						<div class="product-item bg-light">
						<div class="product-ratings">
							<ul class="list-inline">
							<%=coachVO.getCoach_average_evaluation()%>
							<%int c_ave = coachVO.getCoach_average_evaluation().intValue();
							for(int i=0;i<c_ave;i++){%>
							<li class="list-inline-item selected"><i class="fa fa-star"></i></li>	
							<%}%>
							<%for(int i=c_ave;i<5;i++){%>
							<li class="list-inline-item"><i class="fa fa-star"></i></li>
							<%}%>
							</ul><%=coachVO.getCoach_total_people_evaluation()%>人評價</div></div>
						<p class="member-time"><%=coachVO.getCoach_introduction()%></p>
					</div>					
				</div>
			</div>
			
		</div>
	</div>				
			</div>
		</div>
	</div>
</section>


<!-- footer -->
  	<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
	   
<!-- ===========modal================ -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="background-color:#00bff0;">
        <h5 class="modal-title" id="exampleModalLabel" style="color:white;">向教練提出一對一預約申請</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
<!-- -----------------預約訂單------------------------------- -->

<FORM name="form1">
<img src="<%=request.getContextPath()%>/lesson_pkg/images/appointment.png" style="width:490px;"/>
<div class="form-group" style="padding-left: 10px;">

  <label for="comment">預約<a onClick='magicbtn()'>需</a>求:</label>
  <textarea id="text1" class="form-control" style="width:400px;height:150px;" name="appointmentDemand"placeholder="詳細填寫您的健身需求，以便教練能更快地評估狀況並報價"></textarea>
  <label for="comment">預約地點:</label>
  <input id="text2" type="text" class="form-control" style="width:400px;" name="appointmentLocation" placeholder="輸入您期望的預約教學地點">
  <label for="comment">選定預約<a onClick="test()">日</a>期:</label>
  <div style="padding-left: 100px;">
  <input type='hidden' id="datepicker3" name="appointmentDate" class="datepicker-here" data-position="top top" data-multiple-dates="60" />
	<ul class="category-list " style="font-size:5px;padding-top: 10px;padding-left: 5%;">
	<li style="color:#82ef5c;" class="fa fa-bookmark"></li>我的課程
	<li style="color:#ffc653;" class="fa fa fa-bookmark"></li>我的預約
	<li style="color:#aeaeae;" class="fa fa fa-bookmark"></li>教練行程
	</ul>
</div>
</div>
<br>
<input type="hidden" name="action" value="insert">

	<!-- -----------------預約訂單------------------------------- -->
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-danger btn-ok" onClick="CheckAPForm(this.form)" >送出預約訂單</button></FORM>
      </div>
    </div>
  </div>
</div>	   
	


<div class="chat-popup" id="myForm">
<div class="cancel"><a class="x" onclick="closeForm();disconnect ()" style="cursor: pointer;margin-left: 300px;">x</a></div>
  <div class="form-container chatroom_section chatroom_form" style="width: 320px;">
  <div class="divtext " id="messagesArea"  style="list-style-type:none;overflow: scroll;overflow-x:hidden;height: 350px;"></div>
     <input id="userName"  type="hidden" value="${memberVO.member_name}" />
     <input type="text" id="emojionearea1"  class="chatroom_form_inputbox " placeholder="enter a message"/>
    <input type="submit" style="max-width: 100%;" id="sendMessage" class="chatroom_form_sendBtn btn btn-secondary" onclick="sendMessage();"value="送出"/>
  </div>
</div>	   
<!-- footer -->
</body>

<script type="text/javascript">
function test(){
	picker3.update('onRenderCell');
}


//sendAP()
function sendAP() {
	var yourNo = "<%=coachNo%>";
       var jsonObj = {"message" : "","yourNo":yourNo,"action":"sendAP"};
       ws.send(JSON.stringify(jsonObj));
}  
//chatwithCoach()
function chatwithCoach() {
	var yourNo = "<%=coachNo%>";
       var jsonObj = {"message" : "","yourNo":yourNo,"action":"chatwithCoach"};
       ws.send(JSON.stringify(jsonObj));
}  

//--
function magicbtn(){
	Swal.fire({
		  text:"發現神奇小按鈕!",
		  showConfirmButton: false,
		  timer: 700
		})
	$("#text1").val("情谷底");
	$("#text2").val("我在絕");

}
function openForm() {
	  document.getElementById("myForm").style.display = "block";
	    $.ajax({
	    	url:"<%= request.getContextPath()%>/chatRoom.do",
	    	type:"POST",
	    	data:{
	    		'action':'memberInsertOneUser',
	    		'coach_no':'<%=coachNo%>',	
	    	},
	    	success:function(e){
	    		chatwithCoach();
	    	}
	    });
	  
	}

function closeForm() {
	  document.getElementById("myForm").style.display = "none";
	}


var MyPoint = "/WebSocketChat/${memberVO.member_name}/${memberVO.member_no}<%=coachNo%>";

var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;

var statusOutput = document.getElementById("statusOutput");
var webSocket;

	  var RightHead = '<li style="margin-left:100px;" class="chatroom_history_list_item chatroom_history_list_right chatroom_history_list_item--blue" >';
	  var LeftHead = '<li style="margin-right:100px;" class="chatroom_history_list_item chatroom_history_list_left chatroom_history_list_item--grey" >';
	  var SystemHead ='<li style="margin-right:100px;" class="chatroom_history_list_item chatroom_history_list_left chatroom_history_list_item--system" >';
	  var End ='</li>';
	  var coachPhoto = '<img id="output" src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=<%=coachVO.getCoach_no()%>"style="width: 30px; height: 30px; border-radius: 50%; " />' ;
		

function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
		console.log("已上線");
	};

	webSocket.onmessage = function(event) {
		var messagesArea = document.getElementById("messagesArea");
        var jsonObj = JSON.parse(event.data);
        
        if("${memberVO.member_name }" == jsonObj.userName && "${memberVO.member_no}<%=coachNo%>" == jsonObj.room ){
        	 var message =  jsonObj.message  +"\r\n" ;
  	        messagesArea.innerHTML = messagesArea.innerHTML + RightHead + message + End;
  	      messagesArea.scrollTop = messagesArea.scrollHeight;
  	      
        }else if('<%=coachVO.getCoach_name() %>' ==  jsonObj.userName && "${memberVO.member_no}<%=coachNo%>" == jsonObj.room ){
        	 var message =  jsonObj.userName + ": " + jsonObj.message + "\r\n" ;
  	        messagesArea.innerHTML = messagesArea.innerHTML + LeftHead + coachPhoto + "  " + message + End;
  	      messagesArea.scrollTop = messagesArea.scrollHeight;
  	      
        }else if ('${memberVO.member_no}<%=coachNo%>' == jsonObj.userName && "${memberVO.member_no}<%=coachNo%>" == jsonObj.room ){
        	 var message =   jsonObj.message + "\r\n" ;
	  	        messagesArea.innerHTML = messagesArea.innerHTML + SystemHead +  message + End;
	  	      messagesArea.scrollTop = messagesArea.scrollHeight;
        }
	};
	
	webSocket.onclose = function(event) {
		 messagesArea.innerHTML = "";
	};
}
var inputUserName = document.getElementById("userName");
inputUserName.focus();



$(".chatroom_form").on('keydown', '.chatroom_form_inputbox', function (e){ 
   if (e.which === 13) {
   	console.log(13);
   	sendMessage();
		    }
	  });


function sendMessage() {
	 var userName = inputUserName.value.trim();
	    
    var inputMessage = document.getElementsByClassName("emojionearea-editor")[0];
    var message = inputMessage.innerHTML;
    
    if (message === ""){
        alert ("訊息請勿空白!");
        inputMessage.focus();	
    }else{
        var jsonObj = {"userName" : userName, "room" : "${memberVO.member_no}<%=coachNo%>" ,  "message" : message };
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
	leWebSocket.close();
}


</script>


<script type="text/javascript">
// ==============================airdatepicker============================================
var coachScheduleDates = [];var coachLessonDates = [];var coachAppointmentDates = [];
var memLessonDates = [];var memAppointmentDates = [];
var minDate3 = new Date();
minDate3.setDate(minDate3.getDate() + 3);

var picker3 = $('#datepicker3').datepicker({
	clearButton:true,
	inline:true,
	language: 'zh',
	minDate: minDate3,
    onRenderCell: function(d, type) {
    	
     var thisDate = d.getFullYear()+'-'+((d.getMonth()<10)?'0':'')+(d.getMonth()+1)+'-'+((d.getDate()<10)?'0':'')+d.getDate();
        if (memAppointmentDates.indexOf(thisDate)>-1) {
            return {
            	disabled:true,
            	classes: 'appointment'
            }
        }else if (memLessonDates.indexOf(thisDate)>-1) {
               return {
               	classes: 'lesson',
               	disabled:true,
               	}
               }else if(coachScheduleDates.indexOf(thisDate)>-1||coachLessonDates.indexOf(thisDate)>-1||coachAppointmentDates.indexOf(thisDate)>-1){
                   return {
                     classes: 'bussycoach',
                     disabled:true,
                    }    
           }        
  }
}).data('datepicker');


</script>
<script>
var memberNo = "${memberVO.member_no}";
//加入收藏 或 取消收藏
function favorite(lessonNo){
	if(memberNo.trim().length==0){
		 Swal.fire({
			 title: '登入會員',
			 icon:'warning',					
	}).then((result) => {
		  if (result.value) {
			  window.location="<%=request.getContextPath()%>/front-end/index/member_login.jsp";  
		  }})
	}
	else{	
	if($("#heart"+lessonNo).hasClass('fa-heart-o')){
	    $.ajax({
	    	url:"<%=request.getContextPath()%>/favorite_lesson/favoriteLesson.do",
	    	type:"POST",
	    	data:{
	    		'action':'insert',
	    		'lessonNo':lessonNo,
	    		'memberNo':'${memberVO.member_no}'	
	    	},
	    	success:function(){
	    		swal.fire({ 
	    			  title: '收藏成功!', 
	    			  showConfirmButton: false,
	    			  imageUrl: '<%=request.getContextPath()%>/lesson_pkg/images/heart.gif',
	    			  imageWidth: 100,
	    			  imageHeight: 100,
	    			  timer: 700,
	    		}).then(
	    			    function (dismiss) {if (dismiss === 'timer') {console.log('I was closed by the timer')} })
	    	}
	    });
	}else if($("#heart"+lessonNo).hasClass('fa-heart')){
	    $.ajax({
	    	url:"<%=request.getContextPath()%>/favorite_lesson/favoriteLesson.do",
	    	type:"POST",
	    	data:{
	    		'action':'delete',
	    		'lessonNo':lessonNo,
	    		'memberNo':'${memberVO.member_no}'
	    	},
	    	success:function(){
	    		
	    		swal.fire({ 
	    			  title: '取消收藏~', 
	    			  showConfirmButton: false,
	    			  imageUrl: '<%=request.getContextPath()%>/lesson_pkg/images/heart1.gif',
	    			  imageWidth: 100,
	    			  imageHeight: 100,
	    			  timer: 700,
	    		}).then(
	    			    function (dismiss) {
	    			        if (dismiss === 'timer') {
	    			            console.log('I was closed by the timer')
	    			        }
	    			    })
	    	
	    	}
	    });
	}
	$("#heart"+lessonNo).toggleClass('fa fa-heart fa-lg')
	$("#heart"+lessonNo).toggleClass('fa fa-heart-o fa-lg')
	}
	};
	
//input限制
function CheckForm(form)
{  
if($('#inputtext4').val().trim().length===0)   {  
	Swal.fire({
		  icon: 'error',
		  title: '請輸入關鍵字',
		})	
}else{form.submit();
}
}
function CheckAPForm(form){  
if($('#text1').val().trim().length===0)   {  
Swal.fire({icon: 'error',title: '請輸入預約需求',});	
}else if($('#text2').val().trim().length===0){
Swal.fire({icon: 'error',title: '請輸入預約地點',});	
}else if($('#datepicker3').val().trim().length===0){
Swal.fire({icon: 'error',title: '請選擇想要的預約時間',});
}else{
    $.ajax({
    	url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
    	type:"POST",
    	data:{
    		'action':'insert',
    		'coachNo':'<%=coachNo%>',
    		'memberNo':'${memberVO.member_no}',
    		'appointmentDemand':$('#text1').val(),
    		'appointmentLocation':$('#text2').val(),
    		'appointmentDate':$('#datepicker3').val()
    	},
    	success:function(){	
    		$('#myModal').modal('hide');
    		$('#text1').val('');
    		$('#text2').val('');
    		picker3.clear();
    		swal.fire({ title: '預約請求送出成功~', icon: 'success'}) ;
    		sendAP();
    	}
    });
}
}
//參加課程
function joinLesson(lessonNo,lessonPrice){
	if(memberNo.trim().length==0){
		 Swal.fire({
			 title: '登入會員',
			 icon:'warning',					
	}).then((result) => {
		  if (result.value) {
			  window.location="<%=request.getContextPath()%>/front-end/index/member_login.jsp";  
		  }})
	}
	else{	
	const swalWithBootstrapButtons = Swal.mixin({
		  customClass: {
		    confirmButton: 'btn btn-success',
		    cancelButton: 'btn btn-danger'
		  },
		})

		swalWithBootstrapButtons.fire({
		  title: '參加課程',
		  text:'課程價格:'+lessonPrice,
		  icon: 'info',
		  showCancelButton: true,
		  cancelButtonText: '再看看!',
		  confirmButtonText: '我要參加!',
		}).then((result) => {
		  if (result.value) {
			  
			    $.ajax({
			        async : false,
			        cache : false,
			    	url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
			    	type:"POST",
			    	data:{
			    		'action':'insert_Lesson_OrderxDetail',
			    		'lessonNo':lessonNo,
			    		'memberNo':'${memberVO.member_no}'
			    		},success:function(data){
			    			console.log(data);
			    			if(data=='0'){
			    			    swalWithBootstrapButtons.fire(
			    					      '參加成功!',
			    					      '已成功加入您的課程',
			    					      'success'
			    					    );
			    			    joinLessonNow();
		    			    	$("#num1").text("人數: "+<%=lessonVO.getLessonRegistration()+1%>+"/"+  <%=lessonVO.getLessonMaximumPeople()%>);
		    			    	memlessonDate();	
		    			    	picker1.update('onRenderCell');
			    			}else if(data=='1'){
			    			    swalWithBootstrapButtons.fire(
			    					      '參加失敗!',
			    					      '您的點數不足，無法順利扣款!',
			    					      'error')			}
			    			else if(data=='2'){
			    			    swalWithBootstrapButtons.fire(
			    					      '參加失敗!',
			    					      '非常抱歉，本課程的人數已滿!',
			    					      'error')			}
			    			else if(data=='3'){
			    	    		swal.fire({ 
			  	    			  title: '參加失敗', 
			  	    			  text:'本課程的開課日期與您的既有課程衝突!',
			  	    			  icon: 'warning',
			  	    			  showCancelButton: true,
			  	    			  cancelButtonText: '我知道了~',
			  	    			  confirmButtonColor: '#3085d6',
			  	    			  confirmButtonText: '查看衝堂日期',
			  	    			  cancelButtonColor: '#d33',
			  	    		}).then((result) => {
			  	    		  if (result.value) {
			  				    $.ajax({
			  				        async : false,
			  				        cache : false,
			  				    	url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
			  				    	type:"POST",
			  				    	data:{
			  				    		'action':'lesson_conflict',
			  				    		'lessonNo':lessonNo,
			  				    		'memberNo':'${memberVO.member_no}'
			  				    		},success:function(date){
			  				    		    Swal.fire({
			  				    		      title: '衝突課程',
			  				    		      html: date,
			  				    		      confirmButtonText: 'OK'
			  				    		    })
			  				    			
			  				    		}})  	    			  
			  	    		  }
			  	    		})
			    			}		
			    			else {
			    			    swalWithBootstrapButtons.fire(
			    					      '參加失敗!',
			    					      data,
			    					      'error')			}
			    			}})
		  } else if (
		    /* Read more about handling dismissals below */
		    result.dismiss === Swal.DismissReason.cancel
		  ) {			  
			  Swal.fire({
				  title: '取消參加',
				  icon: 'info',
				  focusConfirm: true,
				})				
		  }
		})	
}
}	
//查看課程明細
function alreadyjoin(lessonOrderNo){  //1
    $.ajax({	//2
    	url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
    	type:"POST",
    	data:{
    		'action':'get_lessonOrder_ajax',
    		'lessonOrderNo':lessonOrderNo,
    	},
    	success:function(lo){ //3
    		var lOrder=JSON.parse(lo);
    		var lessonOrderNo=lOrder.lessonOrderNo;
    		var lessonPrice=lOrder.lessonPrice;
    		var dateAcquisition=lOrder.dateAcquisition;
    		var lessonStatus=lOrder.lessonStatus;  		
    		console.log(lOrder);
    		
    		$.ajax({ //4
			url:"<%=request.getContextPath()%>/member_lesson_detail/memberLessonDetail.do",
			type:"POST",
			data:{
			'action':'get_MemberLessonDetail_By_LessonOrder',
			'lessonOrderNo':lessonOrderNo,
			},
			
				success:function(detail){
				var leOrderD=JSON.parse(detail);
				console.log(leOrderD);
				
				var endDate = new Date('${lessonVO.lessonEndDate}');
				var today = new Date();
				console.log('end'+endDate);
				console.log('today'+today);				
				var x ='';
				var y = 0;
				var i = 0;
				for (i = 0; i < leOrderD.length; i++){
					console.log(leOrderD[i].studentStatus);
					var memberLessonDetailNo =leOrderD[i].memberLessonDetailNo;
					switch(leOrderD[i].studentStatus){
					case 0:
						x=x+leOrderD[i].lessonDate+'<br>';
						break;
					case 1:	
						x=x+leOrderD[i].lessonDate+' <i class="fa fa-check-square"></i><br>';
						y++;
						break;	
					case 2:	
						x=x+leOrderD[i].lessonDate+`<button class="btn btn-light" onClick="finishLEDetail('`+memberLessonDetailNo+`')" style="padding:15px 25px;">報到</button><br>`;
						y++;
						break;							
    				}
				}
				if(y==i){
					  Swal.fire({
						  title: '課程明細',
						  confirmButtonText: '完成課程',
						  width: 500,
						  html:`<div><b>已完成本次課程囉~<b></div><table class="table"><thead>`+
						    `<tr><th scope="col" nowrap="nowrap">訂單編號`+lessonOrderNo+`</th></tr>
					  </thead><tbody>
					    <tr><th>加入日期</th>
						    <td>`+dateAcquisition+`</td></tr>						    
						<tr><th scope="row">課程費用</th>
					    <td>`+lessonPrice+`</td></tr>
					    <tr><th scope="row">上課日期</th>
					    <td>`+x+`</td></tr></tbody>
					</table>`,

						}).then((result) => {////===未完成===
							  if (result.value) {
									$.ajax({
										url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
										type:"POST",
										data:{
										'action':'finishLO',
										'lessonOrderNo':lessonOrderNo,
										},	
											success:function(e){
												if(e==='0'){
												  Swal.fire({
													  title: '完成本課程囉~',
													  icon:'success'
											}).then((result) => {
												  if (result.value) {
													  window.location.replace("<%=request.getContextPath()%>/front-end/lesson/lesson_page.jsp");  
													  }
												  })
												}
											}		
									}) 
							  }
							  })
				}else if(endDate>today){
					  Swal.fire({
						  title: '課程明細',
						  showCancelButton: true,
						  cancelButtonText: '退課',
						  width: 500,
						  html:`<table class="table"><thead>`+
						    `<tr><th scope="col" nowrap="nowrap">訂單編號</th>
						    	<td>`+lessonOrderNo+`</td>
						    </tr>
							  </thead><tbody>
							    <tr><th>加入日期</th>
								    <td>`+dateAcquisition+`</td></tr>						    
								<tr><th scope="row">課程費用</th>
							    <td>`+lessonPrice+`</td></tr>
							    <tr><th scope="row">上課日期</th>
							    <td>`+x+`</td></tr></tbody>
							</table>`,

						}).then((result) => {
							  if (!result.value) {
								  Swal.fire({
									  title: '退出課程',
									  imageUrl: '<%=request.getContextPath()%>/lesson_pkg/images/source.gif',
									  imageWidth: 300,
									  imageHeight: 300,
									  html:'<h4>真的確定要退出課程嗎?</h4><b><h6><li>只可在課程報名截止日前退課</li><li>退課成功將會把課程費用歸還予您</li></h6></b>',
									  showCancelButton: true,
									  confirmButtonText: '真的確定!',
									  cancelButtonText: '取消',
									  reverseButtons: true,
								  }).then((result) => {//.then((result)3
									  if (result.value) {
											$.ajax({
												url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
												type:"POST",
												data:{
												'action':'delete',
												'lessonOrderNo':lessonOrderNo,},	
													success:function(e){
														console.log(e);
														if(e=='0'){
														  Swal.fire({
															  title: '成功退出本課程了~',
															  icon:'success'
													}).then((result) => {
														  if (result.value) {window.location.replace("<%=request.getContextPath()%>/front-end/lesson/MemMyLesson.jsp")};
														  })
														}
													}		 
											}) 
									 		 }	
										  })//.then((result)3
								  } 
								})					
					
				}else{					
				  Swal.fire({
					  title: '課程明細',
					  width: 500,
					  html:`<table class="table"><thead>`+
					    `<tr><th scope="col" nowrap="nowrap">訂單編號</th>
					    	<td>`+lessonOrderNo+`</td>
					    </tr>
						  </thead><tbody>
						    <tr><th>加入日期</th>
							    <td>`+dateAcquisition+`</td></tr>						    
							<tr><th scope="row">課程費用</th>
						    <td>`+lessonPrice+`</td></tr>
						    <tr><th scope="row">上課日期</th>
						    <td>`+x+`</td></tr></tbody>
						</table>`,

					})
									  } 
									}
					}) //4
	 	}	//3	    	 	
    }); 	//2	
	} //1
	
//fisishdetail
function finishLEDetail(memberLessonDetailNo){
		console.log(memberLessonDetailNo);
	  $.ajax({
			url:"<%=request.getContextPath()%>/member_lesson_detail/memberLessonDetail.do",
			type:"POST",
			data:{
			'action':'check_memDetail_ajax',
			'memberLessonDetailNo':memberLessonDetailNo,
			},	
				success:function(e){
					console.log(e);
					if(e==='0'){
					  Swal.fire({
						  title: '簽到成功囉',
						  icon:'success'
						})
					}
	  }
})
	}
//向教練提出預約訂單
function appointment(memberNo,coachNo){
	if(memberNo.trim().length==0){
		 Swal.fire({
			 title: '登入會員',
			 icon:'warning',					
	}).then((result) => {
		  if (result.value) {
			  window.location="<%=request.getContextPath()%>/front-end/index/member_login.jsp";  
		  }})
	}
	else{		
	swal.fire({ 
		  title: '預約一對一課程', 
		  text:'按下確定並進一步提出預約需求',
		  icon: 'info',
		  showCancelButton: true,
		  cancelButtonText: '取消',
		  confirmButtonText: '確定',
	}).then((result) => {
		 if (result.value){
		$('#myModal').modal('show');
		csDate();cslessonDate();csappointmentDate();
		picker3.update('onRenderCell');}
	})	
	}
	}	

//date picker
var picker1 =$('#datepicker').datepicker({ //leftbar
	onSelect: function(dateStr,date,picker) {
		picker.update('onRenderCell');
	},
	inline:true,
	language: 'zh',
    onRenderCell: function(d, type) {
        var thisDate = d.getFullYear()+'-'+((d.getMonth()<10)?'0':'')+(d.getMonth()+1)+'-'+((d.getDate()<10)?'0':'')+d.getDate();
           if (memLessonDates.indexOf(thisDate)>-1) {
               return {
               	disabled:true,
               	classes: 'lesson'
               	}
               }else if(memAppointmentDates.indexOf(thisDate)>-1){
                   return {
                     	classes: 'appointment'
                    }    
           }
     }
}).data('datepicker');
//初始化
window.onload =function init(){
	leConnect();
	memappointmentDate();
	memlessonDate();	
	picker1.update('onRenderCell');
	joinLessonNow();

	$.ajax({
        cache : false,
    	url:"<%=request.getContextPath()%>/favorite_lesson/favoriteLesson.do",
    	type:"POST",
    	data:{
    		'action':'get_mem_favoriteLesson_ajax',
    		'memberNo':'${memberVO.member_no}'
    		},success:function(favlist){
    			var x =(favlist.toString()).split(",")
    			console.log(favlist);
    			for (var i = 0; i < x.length; i++) {
    			    a = x[i].trim();
    			    if($('#heart'+a)){
    			    	$('#heart'+a).removeClass('fa fa-heart-o fa-lg').addClass('fa fa-heart fa-lg');
    			    	}
    			    }
    			
    			}
	});	
}

//是否參與課程
var joinLessonNow = function join1(){
	$.ajax({
    cache : false,
	url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
	type:"POST",
	data:{
		'action':'get_mem_Lesson_Order_ajax',
		'memberNo':'${memberVO.member_no}'
		},success:function(lolist){
			lolist = JSON.parse(lolist);
			console.log(lolist);
			for (var i = 0; i < lolist.length; i++) {
				a = lolist[i].lessonNo;
			    if($('#btn'+a)){
			    	$('#btn'+a).html('<h4>已參加<br><br>查看明細</h4>');
			    	$('#btn'+a).attr("onClick","alreadyjoin('"+lolist[i].lessonOrderNo+"')")

			    	}
			    }
			
			}
});
}
//取得教練所有排程
var csDate=function coachSchedule(){ 
			$.ajax({
				async : false,
		        cache : false,
		    	url:"<%=request.getContextPath()%>/coach_schedule/coachSchedule.do",
		    	type:"POST",
		    	data:{
		    		'action':'get_coach_schedule_ajax',
		    		'coachNo':'<%=coachNo%>' //預設
		    		},success:function(cslist){
		    				console.log('coach_schedule:'+cslist);
		    				coachScheduleDates=cslist;	    			
		    			}
			});
		}
var cslessonDate=function coachLesson(){ //取得教練課程
			$.ajax({
				async : false,
		        cache : false,
		    	url:"<%=request.getContextPath()%>/lesson/lesson.do",
		    	type:"POST",
		    	data:{
		    		'action':'get_coach_lesson_date_ajax',
		    		'coachNo':'<%=coachNo%>' //預設
		    		},success:function(ldlist){
	    				console.log('co_lessonDate'+ldlist);
		    				coachLessonDates=ldlist;

		    			}
			});
		}
var csappointmentDate=function coachAppointment(){ //取得教練預約
	$.ajax({
		async : false,
        cache : false,
    	url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
    	type:"POST",
    	data:{
    		'action':'get_coach_appointment_date_ajax',
    		'coachNo':'<%=coachNo%>' //預設
    		},success:function(aplist){
				console.log('co_appointment:'+aplist);
				coachAppointmentDates=aplist;

    			}
	});
}

var memlessonDate=function memLesson(){ //取得會員課程
	$.ajax({
		async : false,
        cache : false,
    	url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
    	type:"POST",
    	data:{
    		'action':'get_mem_lesson_date_ajax',
    		'memberNo':'${memberVO.member_no}' //預設!!
    		},success:function(ldlist){
				console.log('memlessonDate'+ldlist);
    				memLessonDates=ldlist;

    			}
	});
}
var memappointmentDate=function memAppointment(){ //取得會員預約
$.ajax({
async : false,	
cache : false,
url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
type:"POST",
data:{
	'action':'get_mem_appointment_date_ajax',
	'memberNo':'${memberVO.member_no}'
	},success:function(aplist){
		console.log('mem_appointment:'+aplist);
		memAppointmentDates=aplist;

		}
});
}
</script>
</html>