<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.favorite_lesson.model.*"%>



	
<html>
<head>
<title>新增教練排程</title>
<script src="<%=request.getContextPath()%>/tools/datetimepicker/jquery-3.4.1.min.js"></script>
<!-- air datepicker -->
<link href="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.min.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.zh.js"></script>

</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>新增教練排程</h3></td><td>
		 <h4><a href=""><img src="<%=request.getContextPath()%>/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<table>

	<tr>
		<td>教練編號:C001</td>
 
	</tr>
	
	<tr>
		<td >新增日期:</td>
		<td><input type='hidden'  id="datepicker3"  name="coachDate" class="datepicker-here" />
	</tr>


</table>
<br>
<input id="input1" type="text" />
</body>

<script type="text/javascript">


var MyPoint = "/LessonWS/TEST000";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
//var endPointURL = "ws://localhost:8081/IBM_WebSocket1_ChatB/MyEchoServer/peter/309";
	console.log(endPointURL);
var webSocket;

function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	console.log(endPointURL);
	
	webSocket.onopen = function(event) {
		console.log("WebSocket 成功連線");
	};

	webSocket.onmessage = function(event) {
		var messagesArea = document.getElementById("input1");
        messagesArea.value = event
	};

	webSocket.onclose = function(event) {
		console.log("WebSocket 已離線");
	};
}



function sendMessage() {
        var jsonObj = {"userName" : userName, "message" : message};
        webSocket.send(JSON.stringify(jsonObj));
        inputMessage.value = "";
        inputMessage.focus();
}


function disconnect () {
	webSocket.close();

}













// ==============================disable date 方法============================================
var coachScheduleDates = [];
var coachLessonDates = [];
var coachAppointmentDates = [];


var picker1 =$('#datepicker3').datepicker({		
		onSelect: function(dateStr,date,picker) {
	    if (coachScheduleDates.indexOf(dateStr)!=-1) {
		    $.ajax({
		    	url:"<%=request.getContextPath()%>/coach_schedule/coachSchedule.do",
		    	type:"POST",
		    	data:{
		    		'action':'delete2',
		    		'coachDate':dateStr,
		    		'coachNo':'C001' //預設
		    	},
		    	success:function(e){
		    		console.log(e);
		    		if(e=='0'){
		    		csDate();
		    		picker.clear();
		    		picker.update('onRenderCell');
			    	alert('You delete schedule' );
		    		}
		    	}
		    });
		    
	    	
	        }else if(coachLessonDates.indexOf(dateStr)!=-1){
		    	alert('coachLessonDates' );

	        }else if(coachAppointmentDates.indexOf(dateStr)!=-1){
		    	alert('coachAppointmentDates' );	
	        }	    
	    else{	    	
			    $.ajax({
			    	url:"<%=request.getContextPath()%>/coach_schedule/coachSchedule.do",
			    	type:"POST",
			    	data:{
			    		'action':'insert',
			    		'coachDate':dateStr,
			    		'coachNo':'C001'
			    	},
			    	success:function(){
			    		csDate();
			    		picker.clear();
			    		picker.update('onRenderCell');
				    	alert('You are inserting schedule'+dateStr );
			    	}
			    });
	        }
	    
	    
		},
	inline:true,
	language: 'zh',
	minDate: new Date(),
    onRenderCell: function(d, type) {   	

	var thisDate = d.getFullYear()+'-'+((d.getMonth()<10)?'0':'')+(d.getMonth()+1)+'-'+((d.getDate()<10)?'0':'')+d.getDate();

    if (coachScheduleDates.indexOf(thisDate)>-1) {
        return {
        	classes: 'schedule',
        }
      }else if(coachLessonDates.indexOf(thisDate)>-1){
         return {
          	classes: 'lesson'
         }
      }
      else if(coachAppointmentDates.indexOf(thisDate)>-1){
          return {
           	classes: 'appointment'
          }
       }
  }
		
}).data('datepicker');

var csDate=function coachSchedule(){ //取得教練行程
			$.ajax({
		        async : false,
		        cache : false,
		    	url:"<%=request.getContextPath()%>/coach_schedule/coachSchedule.do",
		    	type:"POST",
		    	data:{
		    		'action':'get_coach_schedule_ajax',
		    		'coachNo':'C001' //預設
		    		},success:function(cslist){

		    				console.log(cslist);
		    				coachScheduleDates=cslist;	    			
		    			}
			});
		}
var lessonDate=function coachLesson(){ //取得教練行程
			$.ajax({
		        async : false,
		        cache : false,
		    	url:"<%=request.getContextPath()%>/lesson/lesson.do",
		    	type:"POST",
		    	data:{
		    		'action':'get_coach_lesson_date_ajax',
		    		'coachNo':'C001' //預設
		    		},success:function(ldlist){
	    				console.log('lessonDate'+ldlist);
		    				coachLessonDates=ldlist;

		    			}
			});
		}
var appointmentDate=function coachAppointment(){ //取得教練行程
	$.ajax({
        async : false,
        cache : false,
    	url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
    	type:"POST",
    	data:{
    		'action':'get_coach_appointment_date_ajax',
    		'coachNo':'C001' //預設
    		},success:function(aplist){
				console.log(aplist);
				coachAppointmentDates=aplist;

    			}
	});
}		
//行程資料初始化
window.onload =function init(){
connect();
csDate();
lessonDate();
appointmentDate();
picker1.update('onRenderCell');
}

</script>