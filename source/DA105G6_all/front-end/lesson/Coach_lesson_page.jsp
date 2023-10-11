<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="com.lesson_order.model.*"%>
<!DOCTYPE html>

<% 
	CoachVO coachVO1 = (CoachVO)session.getAttribute("coachVO");
	LessonService lSvc = new LessonService();
	List<LessonVO> list = lSvc.getLessonByCoach(coachVO1.getCoach_no());
	pageContext.setAttribute("list", list);
	%> 
	
<html>
<head>

<title>教練當前課程</title>
<!-- jQuery -->
<script src="<%=request.getContextPath()%>/lesson_pkg/js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/lesson_pkg/plugins/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/lesson_pkg/plugins/bootstrap/bootstrap.min.js"></script>
<!-- Font Awesome -->
<link
	href="<%=request.getContextPath()%>/lesson_pkg/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- Owl Carousel -->
<link
	href="<%=request.getContextPath()%>/lesson_pkg/plugins/slick-carousel/slick/slick.css" rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/lesson_pkg/plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
<!-- Fancy Box -->
<link
	href="<%=request.getContextPath()%>/lesson_pkg/plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/lesson_pkg/plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/lesson_pkg/plugins/seiyria-bootstrap-slider/dist/css/bootstrap-slider.min.css" rel="stylesheet">
<!-- CUSTOM CSS -->
<link href="<%=request.getContextPath()%>/lesson_pkg/css/style.css" rel="stylesheet">
<!-- sweetAlert2 -->
<script src="<%=request.getContextPath()%>/tools/sweetalert2/dist/sweetalert2.all.js"></script>
<!-- air datePicker -->
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
a{
font-family:'Noto Sans TC', sans-serif;}
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

.coachIMG {
    border-radius: 50%;
    overflow: hidden;
    height: 70px;
    border: 3px solid white;
    display: inline-block;
    box-shadow: 0 0 2px rgba(0,0,0,0.24);  
}
.thumb-content .profile-image {
	position: absolute;
    top: -20px;
    left: 5px;
    z-index: 1;
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

			
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>			
</c:if>	
			<%@ include file="page3.file" %>
			
			<div class="row">
<%@ include file="C_left_bar.file" %>
				<div class="col-md-9">
					<div class="category-search-filter">
						<div class="row">
							<div class="col-md-6">
								<h5><b>當前課程</b></h5>
							</div>
							<div class="col-md-6">
								<div class="view" style="color:dodgerblue;font-family:'Noto Sans TC', sans-serif;">
									<strong>新增課程</strong>
									<ul class="list-inline view-switcher" >
										<li class="list-inline-item"><a href="<%=request.getContextPath()%>/front-end/lesson/Coach_add_lesson.jsp"><i class="fa fa-plus fa-lg"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="product-grid-list">
						<div class="row mt-30">				 
							<c:forEach var="lessonVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<jsp:useBean id="now" class="java.util.Date"/>
							<c:if test="${lessonVO.lessonEndDate>=now}">
							<%
							LessonVO lessonVO = (LessonVO)pageContext.getAttribute("lessonVO");
							CoachService coachSvc = new CoachService();
							CoachVO coachVO = coachSvc.findOneCoach(lessonVO.getCoachNo());							
							%>
							<div class="col-sm-12 col-lg-4 col-md-6">
								<!-- product card -->
								<div class="product-item bg-light">
									<div class="card">
										<div class="thumb-content">
											<a href="<%=request.getContextPath()%>/lesson/lesson.do?action=C_get_One_Lesson&lessonNo=${lessonVO.lessonNo}"> <img class="card-img-top img-fluid"
												src="<%=request.getContextPath()%>/DBImgReader.do?value=${lessonVO.getLessonNo()}" 
												alt="Card image cap">
											</a>
												<div class="profile-image coachIMG">
												<a class="" href="">
												<img src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=<%=lessonVO.getCoachNo()%>" width="70px"></a>
												</div>												
										</div>
										
										<div class="card-body">
											<h4 class="card-title">
												<a class="title-text" href="<%=request.getContextPath()%>/lesson/lesson.do?action=C_get_One_Lesson&lessonNo=${lessonVO.lessonNo}">${lessonVO.lessonName}</a>
											</h4>
											<ul class="list-inline product-meta">
												<li class="list-inline-item"><i class="fa fa-user-o "></i> ${lessonVO.lessonRegistration} / ${lessonVO.lessonMaximumPeople}</li>
												<li class="list-inline-item"><i class="fa fa-hourglass-half "></i> ${lessonVO.lessonEndDate}</li>
											</ul>
											<div class="card-text"><p>${lessonVO.lessonPoint}</p></div>
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
												</ul>	
											</div>
											<button style="margin-right: 30px;" type="button" class="btn btn-light pull-right" onClick='location.href="<%=request.getContextPath()%>/lesson/lesson.do?action=C_get_One_Lesson&lessonNo=${lessonVO.lessonNo}"'>查看課程明細</button>

										</div>
									</div>
								</div>
							</div>					
		</c:if></c:forEach>
							
												</div>
				</div>
				<div class="pagination justify-content-center">
				<%if (pageNumber > 1) {%>
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item">
								<a class="page-link" href="<%=request.getRequestURI()%>?whichPage=1" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
									<span class="sr-only">Previous</span>
								</a>
							</li>
										<%for (int i = 1; i <= pageNumber; i++) {
											if(whichPage!=i)
											{%>
												<li class='page-item'>
											<a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%></a></li>
											<%}else{%>
											<li class='page-item disable'><a><%=i%></a></li>
											<%}%>

											<%}%> 	
							<li class="page-item">
								<a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
									<span class="sr-only">Next</span>
								</a>
							</li>
						</ul>
					</nav>
					<%}%>
				</div>
				
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
    		    		'coachNo':'${coachVO.coach_no}' //預設
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
        		'coachNo':'${coachVO.coach_no}' //預設
        		},success:function(aplist){
    				console.log('co_appointment:'+aplist);
    				coachAppointmentDates=aplist;

        			}
    	});
    }

</script> 
</html>