<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.appointment_order.model.*"%>
<!DOCTYPE html>

<%
	CoachVO coachVO = (CoachVO)session.getAttribute("coachVO");
	AppointmentOrderService apmtSvc = new AppointmentOrderService() ;
    List<AppointmentOrderVO> list = apmtSvc.getCoachAll(coachVO.getCoach_no());
   
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>教練當前預約</title>
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
			
<%@ include file="C_left_bar.file" %>

				<div class="col-md-9">
					<div class="category-search-filter" style="color:dodgerblue;">
						<div class="row">
							<div class="col-md-6">
								<h5 id="currentAP"><b>當前預約</b></h5>
							</div>
						</div>
					</div>
					<div class="product-grid-list">
						<div class="row mt-30">				 

							<div class="col-lg-12">
								<!-- product card -->
								<div class="product-item bg-light">
								
									<div class="card widget">
									<table class="table">
  <thead>
    <tr>
      <th scope="col">會員</th>
      <th scope="col">申請時間</th>
      <th scope="col">預約狀態</th>
      <th scope="col" style="padding-left: 40px;">明細</th>
      <th scope="col" style="padding-left: 40px;">動作</th>

    </tr>
  </thead>  <tbody>											
										<c:forEach var="aptmtVO" items="<%=list%>" >
										<c:if test="${aptmtVO.appointmentStatus==1}" >										

    <tr>
      <th scope="row">${aptmtVO.memberNo}</th>
      <td><fmt:formatDate value="${aptmtVO.orderTime}" pattern="yyyy/MM/dd HH:mm"/></td>
      <td>${appointment_status[aptmtVO.appointmentStatus]}</td>
      <td><button class="btn btn-light" onClick="getAPDetail('${aptmtVO.appointmentOrderNo}')" style="padding:15px 25px;">詳細內容</button></td>
    </tr>	
										</c:if></c:forEach>
										  </tbody>
</table>																						
</div></div></div></div></div></div>
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

//==============================airdatepicker============================================
const Toast =Swal.mixin({
	  toast: true,
	  position: 'bottom-start',
	  showConfirmButton: false,
	  timer: 1300,
	  timerProgressBar: true,
	  onOpen: (toast) => {
	    toast.addEventListener('mouseenter', Swal.stopTimer),
	    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
	
var coachScheduleDates = [];var coachLessonDates = [];var coachAppointmentDates = [];

var picker1 =$('#datepicker').datepicker({
	onSelect: function(dateStr,date,picker) {
	    if (coachScheduleDates.indexOf(dateStr)!=-1) {
		    $.ajax({
		    	url:"<%=request.getContextPath()%>/coach_schedule/coachSchedule.do",
		    	type:"POST",
		    	data:{
		    		'action':'delete2',
		    		'coachDate':dateStr,
		    		'coachNo':'${coachVO.coach_no}' //預設
		    	},
		    	success:function(e){
		    		console.log(e);
		    		if(e=='0'){
		    		csDate();
		    		picker.clear();
		    		picker.update('onRenderCell');
					Toast.fire({
		    			  icon: 'success',
		    			  title: '個人排程移除成功',
		    			   });
					coachScheduleUpdate();
		    		}
		    	}
		    });
		    
	    	
	        }else if(coachLessonDates.indexOf(dateStr)!=-1){
	    			$.ajax({
	    				async : false,
	    		        cache : false,
	    		    	url:"<%=request.getContextPath()%>/lesson/lesson.do",
	    		    	type:"POST",
	    		    	data:{
	    		    		'action':'get_coach_lesson_by_date_ajax',
	    		    		'coachNo':'${coachVO.coach_no}',
	    		    		'lessonDate':String(dateStr),
	    		    		},success:function(e){
	    		    			console.log(e);
	    						Toast.fire({
	    			    			  text:"課程: "+e,
	    			    			  showConfirmButton: true,
	    			    			  timer: 5000,
	    			    			  timerProgressBar: false,
	    			    			   });

	    		    			}
	    			});
		    	
	        }else if(coachAppointmentDates.indexOf(dateStr)!=-1){
	        	
    			$.ajax({
    				async : false,
    		        cache : false,
    		    	url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
    		    	type:"POST",
    		    	data:{
    		    		'action':'get_appointment_memName_ajax',
    		    		'coachNo':'${coachVO.coach_no}',
    		    		'lessonDate':String(dateStr),
    		    		},success:function(e){
    		    			console.log(e);
    						Toast.fire({
    			    			  text:"與 "+e+" 的預約課",
    			    			  showConfirmButton: true,
    			    			  timer: 5000,
    			    			  timerProgressBar: false,
    			    			   });

    		    			}
    			});
	        }	    
	    else{	    	
			    $.ajax({
			    	url:"<%=request.getContextPath()%>/coach_schedule/coachSchedule.do",
			    	type:"POST",
			    	data:{
			    		'action':'insert',
			    		'coachDate':dateStr,
			    		'coachNo':'${coachVO.coach_no}'
			    	},
			    	success:function(){
			    		csDate();
			    		picker.clear();
			    		picker.update('onRenderCell');
						Toast.fire({
			    			  icon: 'success',
			    			  title: '個人排程加入成功',
			    			   });
						coachScheduleUpdate();
			    	}
			    });
	        }
	    
	    
		},
	inline:true,
	language: 'zh',
    onRenderCell: function(d, type) {
        var thisDate = d.getFullYear()+'-'+((d.getMonth()<10)?'0':'')+(d.getMonth()+1)+'-'+((d.getDate()<10)?'0':'')+d.getDate();
        if (coachAppointmentDates.indexOf(thisDate)>-1) {
            return {
            	classes: 'appointment'
            }
        }else if (coachLessonDates.indexOf(thisDate)>-1) {
               return {
               	classes: 'lesson',
               	}
               }else if(coachScheduleDates.indexOf(thisDate)>-1){
                   return {
                     classes: 'schedule',
                    }    
           } 
     }
}).data('datepicker');

//初始化
window.onload =function init(){
	connectWS();
	csDate();
	cslessonDate();
	csappointmentDate();
	picker1.update('onRenderCell');

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
		    		'coachNo':'${coachVO.coach_no}'
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
		    		'coachNo':'${coachVO.coach_no}' 
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
    		'coachNo':'${coachVO.coach_no}'
    		},success:function(aplist){
				console.log('co_appointment:'+aplist);
				coachAppointmentDates=aplist;

    			}
	});
}

</script> 
</html>