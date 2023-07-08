<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.lesson.model.*"%>
<%@ page import="com.lesson_order.model.*"%>
<!DOCTYPE html>

<%
  LessonVO lessonVO = (LessonVO) request.getAttribute("lessonVO");
  String lessonNo = lessonVO.getLessonNo();
  String coachNo  = lessonVO.getCoachNo();
  String base64Image = Base64.getEncoder().encodeToString(lessonVO.getLessonPicture());
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>教練修改課程</title>
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
			
<%@ include file="C_left_bar.file" %>

				<div class="col-md-9">
					<div class="category-search-filter" style="color:dodgerblue;">
						<div class="row">
							<div class="col-md-6">
								<h5><b>修改課程</b></h5>
							</div>
						</div>
					</div>
					<div class="product-grid-list">
						<div class="row mt-30">				 

							<div class="col-lg-12">
								<!-- product card -->
								<div class="product-item bg-light">
									<div class="card">
  										<div><img id="imgPreview" src="data:image/jpeg;base64,<%=base64Image%>" style="height:60%;width:60%;"/></div>												
										<div class="card-body">
										<div class="form-group" style="padding-left: 10px;">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lesson/lesson.do" name="form1" enctype="multipart/form-data">
  <label for="comment">課程圖片:<font color=red>*${errorMsgs.lessonPicture}</font></label><br>									
  <input type="file" name="lessonPicture" accept="image/*" onchange="PreviewImage(this)" /><br>
  <label for="comment" style="margin-top: 8px;">課程名稱:<font color=red>*${errorMsgs.lessonName}</font></label>
  <input id="text1" class="form-control"  name="lessonName"placeholder="" value="${lessonVO.lessonName}"></input>
  <label for="comment" style="margin-top: 8px;">課程簡介:<font color=red>*${errorMsgs.lessonPoint}</font></label>
  <input id="text1" class="form-control" name="lessonPoint" value="${lessonVO.lessonPoint}"></input>
  <label for="comment" style="margin-top: 8px;">課程價格:<font color=red>*${errorMsgs.lessonPrice}</font></label>
  <input id="text2"  type="number" min="0" step="any" class="form-control"  name="lessonPrice" value="${lessonVO.lessonPrice}">
  <label for="comment" style="margin-top: 8px;">課程內容:<font color=red>*${errorMsgs.lessonContent}</font></label>
  <textarea id="text2" class="form-control" style="height:1000px;" name="lessonContent" >${lessonVO.lessonContent}</textarea>
<%--   <label for="comment" style="margin-top: 8px;">報名起始日期:<font color=red>*${errorMsgs.lessonStartDate}</font></label><br> --%>
<!--   <input type='hidden' id="datepicker1" name="lessonStartDate" class="datepicker-here col-lg-5" /> -->
  <label for="comment" style="margin-top: 8px;">報名截止日期:<font color=red>*${errorMsgs.lessonEndDate}</font></label><br>
  <div style="padding-left: 50px;"> <input type='hidden' id="datepicker2" name="lessonEndDate" class="datepicker-here col-lg-5" /></div>
  <label for="comment" style="margin-top: 8px;">人數上限:<font color=red>*${errorMsgs.lessonMaximumPeople}</font></label>
  <input id="text2"  type="number" min="0" step="any" class="form-control"  name="lessonMaximumPeople" value="${lessonVO.lessonMaximumPeople}">
  <label for="comment" style="margin-top: 8px;">開課地點:<font color=red>*${errorMsgs.lessonLocation}</font></label>
  <input type='text' id="text1" class="form-control"  name="lessonLocation" value="${lessonVO.lessonLocation}"></input>
  <label for="comment" style="margin-top: 8px;">上課日期:<font color=red>*${errorMsgs.lessonDate}</font></label>
  <div style="padding-left: 50px;" id="divdate3"><input type='hidden' id="datepicker3" name="lessonDate" class="datepicker-here"  data-multiple-dates="60" ></div>
  <br><label for="comment" style="margin-top: 8px;">課程種類:<font color=red>*${errorMsgs.lessonName}</font></label>
  <select id = "lessonClass" name = "lessonClass" ><c:forEach varStatus = "varClass" begin = "0" end = "4">
  <option value = "${varClass.index}" ${(varClass.index == lessonVO.lessonClass) ? 'selected' : '' }>${lesson_class[varClass.index]} </option>
  </c:forEach></select>
  <button style="margin-right: 30px;" type="button" class="btn btn-light pull-right" onClick='submit()'>更新課程</button>

<input type="hidden" name="lessonStatus" value=1>
<input type="hidden" name="action" value="update">
<input type="hidden" name="coachNo" value="<%=coachNo%>">
<input type="hidden" name="lessonNo" value="${lessonVO.lessonNo}">
</FORM></div></div></div></div></div></div></div></div>
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
function PreviewImage(imgFile) {
	var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.PNG$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
	if (!pattern.test(imgFile.value)) {
		alert("只支援jpg/jpeg/png/gif/bmp之格式檔案");
		imgFile.focus();
	} else {
		 var imgPreview = document.getElementById('imgPreview');
		 imgPreview.src = URL.createObjectURL(event.target.files[0]);
		 $('#imgPreview').css("height","519.98px");
		 $('#imgPreview').css("width","825px");
		};
   }

CKEDITOR.replace("lessonContent", {height: 500});

//datepicker
// $('#datepicker1').datepicker({
//     language: 'zh',
//     minDate: new Date(), // Now can select only dates, which goes after today
// 	inline:true
// });

var coachScheduleDates = [];var coachLessonDates = [];var coachAppointmentDates = [];var leDates=[];
var minDate = new Date();
minDate.setDate(minDate.getDate() + 1);

var picker2 = $('#datepicker2').datepicker({
    language: 'zh',
    minDate: minDate, // Now can select only dates, which goes after today
	inline:true,
	onSelect: function(dateStr,date,picker) {
	picker3.update('minDate',date);
	},
}).data('datepicker');

var picker3 =$('#datepicker3').datepicker({		
	inline:true,
	language: 'zh',
	minDate: minDate,
    onRenderCell: function(d, type) { 

	var thisDate = d.getFullYear()+'-'+((d.getMonth()<10)?'0':'')+(d.getMonth()+1)+'-'+((d.getDate()<10)?'0':'')+d.getDate();
	
    if (coachScheduleDates.indexOf(thisDate)>-1) {
        return {
        	classes: 'schedule',
        	disabled:true,
        }
      }else if(coachLessonDates.indexOf(thisDate)>-1&&leDates.indexOf(thisDate)<0){
         return {
          	classes: 'lesson',
            disabled:true,
         }
      }
      else if(coachAppointmentDates.indexOf(thisDate)>-1){
          return {
           	classes: 'appointment',
           	disabled:true,
          }
       }      
  }		
}).data('datepicker');


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
	leDate();
	csDate();
	cslessonDate();
	csappointmentDate();
	picker2.selectDate(new Date('<%=lessonVO.getLessonEndDate()%>'));
	picker1.update('onRenderCell');
	picker3.update('onRenderCell');

}
//取得本課程日期
var leDate=function leDate(){ 
			$.ajax({
				async : false,
		        cache : false,
		    	url:"<%=request.getContextPath()%>/lesson_detail/lessonDetail.do",
		    	type:"POST",
		    	data:{
		    		'action':'get_one_lesson_All_date_ajax',
		    		'lessonNo':'<%=lessonNo%>',
		    		},success:function(list){
		    				leDates =list;
		    				ldlist = JSON.parse(list);
		    				console.log(ldlist);
		    				for (var i = 0; i < ldlist.length; i++){
		    					picker3.selectDate(new Date(ldlist[i].lessonDate));
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



</script> 
</html>