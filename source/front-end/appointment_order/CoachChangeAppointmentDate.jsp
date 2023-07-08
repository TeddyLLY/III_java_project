<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.appointment_order.model.*"%>
<!DOCTYPE html>

<% AppointmentOrderVO apVO = (AppointmentOrderVO)request.getAttribute("apOrderVO");
	String appointmentOrderNo = apVO.getAppointmentOrderNo();
	String coachNo = apVO.getCoachNo();
	String memberNo = apVO.getMemberNo();
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>預約調課</title>
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
<!--	 ckeditor   	-->
<script src="<%=request.getContextPath()%>/tools//ckeditor/ckeditor.js"></script>
<!-- air datepicker -->
<link href="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.min.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.zh.js"></script>
</head>
<style type="text/css" media="screen">
*{
font-family:'Noto Sans TC', sans-serif;}
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

/* datepicker9 */
#card_picker nav.datepicker--nav{
background-color:#feffdc;
}
.datepicker--cell{
height: 50px;
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
/* **CHANGE DATE color** */
.datepicker--cell.changedate{
color: #fff;
background-color:#88e6ff;
}
.datepicker--cell.changedate.-other-month-{
background-color:#e7faff;
}
.datepicker--cell.changedate.-disabled-{
background-color:#e7faff;
}
/* **bussystudent color** */
.datepicker--cell.bussystudent{
color: #fff;
background-color:#cdc4c4;
}
.datepicker--cell.bussystudent.-other-month-{
background-color:#e7e7e7;
}
.datepicker--cell.bussystudent.-disabled-{
background-color:#e7e7e7;
}
.datepicker{
 width: 665px;
 font-size: 16px;
 color: #1d1d1d;
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
			<div class="row">
			
				<div class="col-md-2">

				</div>

				<div class="col-md-8">
					<div class="category-search-filter" style="color:dodgerblue;">
						<div class="row">
							<div class="col-md-6">
								<h5><b>預約日期調整</b></h5>
							</div>
						</div>
					</div>
					<div class="product-grid-list">
						<div class="row mt-30">				 

							<div class="col-lg-12">
								<!-- product card -->
								<div class="product-item bg-light">
								
									<div class="card widget" id="card_picker">
<input type='hidden'  id="datepicker9"  name="coachDate" class="datepicker-here" />
	<ul class="category-list " style="font-size:5px;padding-top: 15px;padding-left: 5%;">
	<li style="color:#82ef5c;" class="fa fa-bookmark"></li>我的課程
	<li style="color:#ffc653;" class="fa fa fa-bookmark"></li>我的預約
	<li style="color:#aeaeae;" class="fa fa fa-bookmark"></li>會員行程
	</ul>
<div class="col-md-12" style="margin-top:10px;">
<span class="col-md-2"></span>
<button class="btn btn-light col-md-5" onClick="getAPDetail('<%=appointmentOrderNo %>')" style="padding:15px 25px;">查看內容</button>
<span class="col-md-2"></span>
<button class="btn btn-light col-md-5" onClick="changedatedone()" >完成調課</button>
</div>																	
</div></div></div></div></div></div>
				<div class="col-md-2"></div>
				<div class="pagination justify-content-center">
				</div>				
			</div>
		</div>
	
</section>


<!-- footer -->
  	<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
<!-- footer -->
</body>

				<script>
function disconnect () {
	ws.close();
}
            var MyPoint = "/LessonWS/";
            var host = window.location.host;
            var path = window.location.pathname;
            var webCtx = path.substring(0, path.indexOf('/', 1));        
            
            var ws;
           var connectWS= function connect(){
            	var myNo ='${coachVO.coach_no}';
                var endPointURL = "wss://" + window.location.host + webCtx + MyPoint+myNo;
                ws = new WebSocket(endPointURL);
                ws.onopen = function() {
                	consloe.log('Connection opened!'+endPointURL);
                };
                ws.onmessage = function(event) {
                    var message = JSON.parse(event.data).message;
                    var action = JSON.parse(event.data).action;
                    var memName = JSON.parse(event.data).memName;
                    if(action==="sendAP"){
               		 Swal.fire({
            			 title: '收到一筆來自 '+memName+' 的預約訂單',
            			 icon:'info',					
            					})
                    }else if(action==="chatwithCoach"){
                  		 Swal.fire({
                			 title: '會員'+memName+'來找你聊天囉~',
                					})
                        }
                };
                ws.onclose = function() {
                	consloe.log('WebSocket closed'+endPointURL);
                };
                ws.onerror = function(err) {
                	consloe.log(err);
                };
            };
 				
				</script>				
<script>

//============================== date ============================================
const Toast =Swal.mixin({
	  toast: true,
	  position: 'bottom-start',
	  showConfirmButton: false,
	  timer: 1000,
	  timerProgressBar: true,
	  onOpen: (toast) => {
	    toast.addEventListener('mouseenter', Swal.stopTimer),
	    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
	
var coachScheduleDates = [];
var coachLessonDates = [];
var coachAppointmentDates = [];
var memAppointmentDates = [];
var memLessonDates = [];
var changeDates = [];

var picker9 =$('#datepicker9').datepicker({		
		onSelect: function(dateStr,date,picker) {

	    if (changeDates.indexOf(dateStr)!=-1) {

		    $.ajax({
		        async : false,
		        cache : false,
		    	url:"<%=request.getContextPath()%>/AppointmentOrderDetailServlet",
		    	type:"POST",
		    	data:{
		    		'action':'deleteAPdetailDATE',
		    		'appointmentDate':dateStr,
		    		'appointmentOrderNo':'<%=appointmentOrderNo%>'
		    	},
		    	success:function(e){
		    		if(e=='0'){
		    		changeDate();memappointmentDate();
		    		picker.clear();
					Toast.fire({
		    			  icon: 'success',
		    			  title: '調整日期',
		    			   });
		    		picker.update('onRenderCell');
		    	}
		    	}
		    });		    
	    	
	        }else{	    	
			    $.ajax({
			        async : false,
			        cache : false,
			    	url:"<%=request.getContextPath()%>/AppointmentOrderDetailServlet",
			    	type:"POST",
			    	data:{
			    		'action':'insertDATE',
			    		'appointmentDate':dateStr,
			    		'appointmentOrderNo':'<%=appointmentOrderNo%>'
			    	},
			    	success:function(e){
			    		if(e=='0'){
			    		changeDate();
			    		memappointmentDate();
			    		picker.update('onRenderCell');
			    		picker.clear();
						Toast.fire({
			    			  icon: 'success',
			    			  title: '調整日期',
			    			   });
			    	}
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
        	disabled:true,
        }
      }else if(coachLessonDates.indexOf(thisDate)>-1){
         return {
          	classes: 'lesson',
            disabled:true,
         }
      }
      else if(coachAppointmentDates.indexOf(thisDate)>-1&&changeDates.indexOf(thisDate)==-1){
          return {
           	classes: 'appointment',
           	disabled:true,
          }
       }

      else if(changeDates.indexOf(thisDate)>-1){
          return {
           	classes: 'changedate',
           	disabled:false,
          }
       }
      else if(memLessonDates.indexOf(thisDate)>-1||memAppointmentDates.indexOf(thisDate)>-1){
			if((coachLessonDates.indexOf(thisDate)==-1||coachScheduleDates.indexOf(thisDate)==-1)){
				if(coachAppointmentDates.indexOf(thisDate)==-1||changeDates.indexOf(thisDate)==-1){
				return {
              	classes: 'bussystudent',
              	disabled:true,
             }}}
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
		    		'coachNo':'<%=coachNo%>' //預設
		    		},success:function(cslist){
		    			console.log("coachScheduleDates"+cslist);
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
		    		'coachNo':'<%=coachNo%>' //預設
		    		},success:function(ldlist){
		    			console.log("coachLessonDates"+ldlist);
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
    		'coachNo':'<%=coachNo%>' //預設
    		},success:function(aplist){
				console.log("coachAppointmentDates"+aplist);
				coachAppointmentDates=aplist;

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
		'memberNo':'<%=memberNo%>' 
		},success:function(aplist){
			console.log('mem_appointment:'+aplist);
			memAppointmentDates=aplist;

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
    		'memberNo':'<%=memberNo%>' //預設!!
    		},success:function(ldlist){
				console.log('memlessonDate'+ldlist);
    				memLessonDates=ldlist;

    			}
	});
}

var changeDate=function memchangeDate(){ //取得調課日程
		$.ajax({
	        async : false,
	        cache : false,
			url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
			type:"POST",
			data:{
			'action':'get_appointment_detail_ajax',
			'appointmentOrderNo':'<%=appointmentOrderNo%>',
			},
				success:function(detail){
				var apOrder=JSON.parse(detail);
				var x ='[';
				for (var i = 0; i < apOrder.length; i++){
					if(i < (apOrder.length-1)){
						x=x+"'"+apOrder[i].appointmentDate+"'"+',';
					}else{
						x=x+"'"+apOrder[i].appointmentDate+"'"+']';
						}
    			}
				changeDates =x;
				console.log("changedate"+x);
				}	    	
				});
}	
//行程資料初始化
window.onload =function init(){
connectWS();	
memappointmentDate();
memlessonDate();
csDate();
lessonDate();
appointmentDate();
changeDate();
picker9.update('onRenderCell');

}

//changedatedone
function changedatedone(){
	$.ajax({
        async : false,
        cache : false,
		url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
		type:"POST",
		data:{
		'action':'changeDateDone',
		'appointmentOrderNo':'<%=appointmentOrderNo%>',
		},
			success:function(detail){
				  Swal.fire({
					  title: '調課成功',
					  icon:'success'
			}).then((result) => {
				  if (result.value) {
							var yourNo = "<%=memberNo%>";
						    var jsonObj = {"message" : "","yourNo":yourNo,"action":"changeDateSuccess"};
						    ws.send(JSON.stringify(jsonObj));

					  window.location.replace("<%=request.getContextPath()%>/front-end/appointment_order/CoachProgressingAppointment.jsp#currentAP");  
				  }})
			}	    	
			});
}	

//getAPdetail
function getAPDetail(apOrderNo){
    $.ajax({
    	url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
    	type:"POST",
    	data:{
    		'action':'get_appointment_ajax',
    		'appointmentOrderNo':apOrderNo,
    	},
    	success:function(apo){
    		var apOrder=JSON.parse(apo);
    		var appointmentOrderNo=apOrder.appointmentOrderNo;
    		var memberNo=apOrder.memberNo;
    		var orderTime=apOrder.orderTime;
    		var appointmentLocation=apOrder.appointmentLocation;
    		var appointmentDemand=apOrder.appointmentDemand;
    		var appointmentPrice=apOrder.appointmentPrice;
    		
    		console.log(apOrder);
    		$.ajax({
			url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
			type:"POST",
			data:{
			'action':'get_appointment_detail_ajax',
			'appointmentOrderNo':apOrderNo,
			},
			
				success:function(detail){
				var apOrder=JSON.parse(detail);
				var x ='';
				for (var i = 0; i < apOrder.length; i++){
					switch(apOrder[i].studentStatus){
					case 0:
						x=x+apOrder[i].appointmentDate+'		<br>';
						break;
					case 1:	
						x=x+apOrder[i].appointmentDate+' <i class=\"fa fa-check-square\"></i><br>';
						break;								
    		}
				}
				  Swal.fire({
					  title: '預約內容',
					  width: 600,
					  html:`<table class="table">
						  <thead>
						    <tr><th scope="col" nowrap="nowrap">訂單編號`+appointmentOrderNo+`</th>
					  		    <th scope="col" nowrap="nowrap">會員編號`+memberNo+`</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr><th scope="row">申請時間</th>
							    <td>`+orderTime+`</td>					    				    				    
						    </tr>
						    <tr><th scope="row">預約價格</th>
						    	<td>`+appointmentPrice+`</td>					    				    				    
						    </tr>						    
						    <tr><th scope="row">預約地點</th>
						    	<td>`+appointmentLocation+`</td>
						    </tr>						    	
						    <tr>
						    <th scope="row">預約需求</th>
						    <td>`+appointmentDemand+`</td>
						    </tr>
						    <tr><th scope="row">預約時間</th>
						    <td>`+x+`</td>
						    </tr>
						    </tbody>
						</table>`,

					})
	 		 							}		    	
				});
		  }		    	
    }); 	
}

</script>
</html>