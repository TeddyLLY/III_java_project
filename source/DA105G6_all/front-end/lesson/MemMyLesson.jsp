<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="com.lesson_order.model.*"%>
<!DOCTYPE html>
<%
	LessonService lessonSvc = new LessonService();
	List<LessonVO> list1 = lessonSvc.getAll();
	pageContext.setAttribute("list1", list1);
%>

<%
	MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
	LessonOrderService flSvc = new LessonOrderService();
	List<LessonOrderVO> list = flSvc.getMemberAllLessonOrder(memVO.getMember_no());
	pageContext.setAttribute("list", list);
	%>
<html>
<head>

<title>當前課程</title>
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
<body class="body-wrapper">
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
					<button type="submit" class="btn btn-primary col-md-2"
						id="searchbtn" onClick='CheckForm(this.form)'>搜尋</button>
				</div>
			</form>

<%@ include file="page3.file" %>
<%@ include file="left_bar.file" %>

				<div class="col-md-9">
					<div class="category-search-filter">
						<div class="row">
							<div class="col-md-6">
								<h5><b>當前課程</b></h5>
							</div>
							<div class="col-md-6">
								<div class="view">
									<strong>Views</strong>
									<ul class="list-inline view-switcher">
										<li class="list-inline-item"><a
											href="javascript:void(0);"><i class="fa fa-th-large"></i></a>
										</li>
										<li class="list-inline-item"><a
											href="javascript:void(0);"><i class="fa fa-reorder"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="product-grid-list">
						<div class="row mt-30">				 
							<c:forEach var="lessonVO" items="${list1}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<c:forEach var="lodVO" items="${list}">
							<c:if test="${lessonVO.lessonNo==lodVO.lessonNo}">
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
											<a href="<%=request.getContextPath()%>/lesson/lesson.do?action=get_One_Lesson&lessonNo=${lessonVO.lessonNo}"> <img class="card-img-top img-fluid"
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
												<a class="title-text" href="<%=request.getContextPath()%>/lesson/lesson.do?action=get_One_Lesson&lessonNo=${lessonVO.lessonNo}">${lessonVO.lessonName}</a>
											</h4>
											<ul class="list-inline product-meta">
												<li class="list-inline-item"><i class="fa fa-dollar "></i> ${lodVO.lessonPrice}</li>
												<li class="list-inline-item"><i class="fa fa-calendar-check-o "></i> <fmt:formatDate pattern="yyyy-MM-dd" value="${lodVO.dateAcquisition}" /></li>
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
											<button style="margin-right: 30px;" type="button" class="btn btn-light pull-right" onClick='location.href="<%=request.getContextPath()%>/lesson/lesson.do?action=get_One_Lesson&lessonNo=${lessonVO.lessonNo}"'>查看課程明細</button>

										</div>
									</div>
								</div>
							</div>					
		</c:if></c:forEach></c:forEach>
							
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
//加入收藏 或 取消收藏
function favorite(lessonNo){
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
	};
	
//搜尋欄位限制
function CheckForm(form)
{  
if($('#inputtext4').val().trim().length===0)   {  
alert("請輸入關鍵字");
}else{form.submit();
}
}
//參加課程
function myLessonDetail(lessonOrderNo){
    $.ajax({
    	url:"<%=request.getContextPath()%>/lesson/favoriteLesson.do",
    	type:"POST",
    	data:{
    		'action':'delete',
    		'lessonNo':lessonNo,
    		'memberNo':'${memberVO.member_no}'
    	},
    	success:function(){
	

		  Swal.fire({
		  title: '參加課程',
		  text:'課程價格:'+lessonPrice,
		  icon: 'info',
		  showCancelButton: true,
		  cancelButtonText: '再看看!',
		  confirmButtonText: '我要參加!',
		})
		  }
    	
    })}

//date picker
var memLessonDates = [];var memAppointmentDates = [];
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
	$.ajax({
        cache : false,
    	url:"<%=request.getContextPath()%>/lesson_order/lessonOrder.do",
    	type:"POST",
    	data:{
    		'action':'get_mem_Lesson_Order_ajax',
    		'memberNo':'${memberVO.member_no}'
    		},success:function(lolist){
    			var x =(lolist.toString()).split(",")
    			for (var i = 1; i < x.length; i++) {
    			    a = x[i].trim();
    			    if($('#btn'+a)){
    			    	$('#btn'+a).html('<b style="color: #666;">已參加</b>');
    			    	$('#btn'+a).attr("onClick","alreadyjoin()")
	
    			    	}
    			    }
    			
    			}
	});

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
    			for (var i = 1; i < x.length; i++) {
    			    a = x[i].trim();
    			    if($('#heart'+a)){
    			    	$('#heart'+a).toggleClass('fa fa-heart fa-lg');
						$("#heart"+a).toggleClass('fa fa-heart-o fa-lg');

    			    	}
    			    }
    			
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
    		'memberNo':'${memberVO.member_no}' 
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