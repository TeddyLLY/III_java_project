<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="com.lesson_order.model.*"%>
<%@ page import="com.lesson_detail.model.*"%>
<!DOCTYPE html>
<%	LessonVO lessonVO = (LessonVO) request.getAttribute("lessonVO");

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
<link href="<%=request.getContextPath()%>/lesson_pkg/css/animate.css" rel="stylesheet">
<!-- sweetAlert2 -->
<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.js"></script>
<!-- air datePicker -->
<link href="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.min.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/tools/datetimepicker/datepicker.zh.js"></script>

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
padding: inherit;
background: #0020ffd1;
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
						<li class="list-inline-item"><i class="fa fa-user-o "></i> 人數: ${lessonVO.lessonRegistration} / ${lessonVO.lessonMaximumPeople}</li>
							<li class="list-inline-item"><i class="fa fa-location-arrow "></i> 地點:  ${lessonVO.lessonLocation}</li>
							<li class="list-inline-item"><i class="fa fa-dollar"></i> 價格: <%=lessonVO.getLessonPrice()%></li>
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
				<jsp:useBean id="now" class="java.util.Date"/>
				<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />
				<c:if test="${lessonVO.lessonEndDate>=date}">
				
				<div class="sidebar">
					<div class="widget price text-center" style="margin-top: 100px;">
						<a href="<%=request.getContextPath()%>/lesson/lesson.do?action=getOne_For_Update&lessonNo=${lessonVO.lessonNo}"><h4>修改課程</h4></a>
					</div>
					<div class="widget price text-center" style="background:#ff4a4a; margin-top: 50px;" onClick="deleteLesson('${lessonVO.lessonNo}')">
						<h4>刪除課程</h4>
					</div>									
				</div>
				</c:if>
				<div class="sidebar">
					<div class="widget price text-center" style="background:orange; margin-top: 50px;" onClick="joinMemberList('${lessonVO.lessonNo}')">
						<h4>參與會員清單</h4>
					</div>							
				</div>
					<c:forEach var="lessondate1" items="<%=list%>">
					<c:if test="${lessondate1.lessonDate==date}">
					<div class="sidebar">
					<div class="widget price text-center" style="background:pink; margin-top: 50px;" onClick="checkMemberList('${lessonVO.lessonNo}')">
						<h4>點名簿</h4>
					</div>							
				</div></c:if></c:forEach>				
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
<!-- footer -->


<!-- -----------------會員資料------------------之後用ajax改寫------------- -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="background-color:#00bff0;">
        <h5 class="modal-title" id="exampleModalLabel" style="color:white;">會員名單</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <%LessonOrderService loSvc = new LessonOrderService(); 
		List<LessonOrderVO> lessonOrderlist = loSvc.getLessonJoinMember(lessonVO.getLessonNo());%>
      <c:forEach var="lessonOrderVO" items="<%=lessonOrderlist%>">
      <div><a href="#" class="badge badge-primary">${lessonOrderVO.memberNo}</a></div>
      </c:forEach>
      <div>
      </div>


      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
      </div>
    </div>
  </div>
</div>	   
	<!-- -----------------會員資料------------------------------- -->
 
</body>
<script type="text/javascript">

//刪除課程
function deleteLesson(lessonNo){
	    $.ajax({
	    	url:"<%=request.getContextPath()%>/lesson/lesson.do",
	    	type:"POST",
	    	data:{
	    		'action':'delete',
	    		'lessonNo':lessonNo,
	    	},
	    	success:function(e){
	    		if(e=='0'){
	    		swal.fire({ 
	    			  title: '刪除成功!', 
	    			  showConfirmButton: false,
	    			  icon:'success',
	    			  timer: 900,
	    		});
	    		setTimeout(function(){ 
	    			$(window).attr('location','<%=request.getContextPath()%>/front-end/lesson/Coach_lesson_page.jsp');
	    		  }  , 1000 );
	    		}else if(e=='1'){
		    		swal.fire({ 
		    			  title: '刪除失敗!', 
		    			  text:'由於已有會員參與，教練無法擅自刪除課程',
		    			  icon:'error'
		    		})	    			
	    		}

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
		    		'coachNo':'${coachVO.coach_no}' //預設
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
// =========會員參與資料
function joinMemberList(lessonNo){
	$('#myModal').modal('show');
}


const Toast2 = Swal.mixin({
	  toast: true,
	  position: 'top-start',
	  showConfirmButton: true,
	  confirmButtonText:'關閉',
	  timer: 300000,
	  timerProgressBar: true,

	})

function checkMemberList(lessonNo){
	$.ajax({
		async : false,
        cache : false,
    	url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
    	type:"POST",
    	data:{
    		'action':'change_memDetail_ajax',
    		'lessonNo':lessonNo
    		},success:function(e){
    			console.log(e)
    			if(e==='0'){
    				Toast2.fire({
    					  icon: 'success',
    					  title: '開放報到中',
    					  onClose: () => {
    							$.ajax({
    								async : false,
    						        cache : false,
    						    	url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
    						    	type:"POST",
    						    	data:{
    						    		'action':'stop_change_memDetail_ajax',
    						    		'lessonNo':lessonNo
    						    		},success:function(e){
    						    			console.log(e)
    						    			if(e==='0'){
    						    				  Swal.fire({
    						    					  title: '本課程已停止報到',
    						    					  showClass: {
    						    					    popup: 'animated fadeInDown faster'
    						    					  },
    						    					  hideClass: {
    						    					    popup: 'animated fadeOutUp faster'
    						    					  }
    						    					})
    						    				
    						    			}

    						    			}
    							});

    						  },
    					})
    				
    			}

    			}
	});

}

</script>
</html>