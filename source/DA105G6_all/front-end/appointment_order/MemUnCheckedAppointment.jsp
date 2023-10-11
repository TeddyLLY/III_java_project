<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.appointment_order.model.*"%>
<!DOCTYPE html>
<%
   MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
   AppointmentOrderService apmtSvc = new AppointmentOrderService() ;
   List<AppointmentOrderVO> list = apmtSvc.getMemAll(memVO.getMember_no());
   
%>
<html>
<head>

<title>待確認預約</title>
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
			
<%@ include file="left_bar.file" %>		

				<div class="col-md-9">
					<div class="category-search-filter">
						<div class="row">
							<div class="col-md-6">
								<h5><b>待確認預約</b></h5>
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
							<div class="col-lg-12">
								<!-- product card -->
								<div class="product-item bg-light">
								
									<div class="card widget">
									<table class="table">
  <thead>
    <tr>
      <th scope="col">教練</th>
      <th scope="col" style="padding-left:40px">申請時間</th>
      <th scope="col">預約狀態</th>
      <th scope="col">價格</th>
      <th scope="col" style="padding-left: 40px;">明細</th>
      <th scope="col" style="padding-left: 40px;">動作</th>

    </tr>
  </thead>  <tbody>	
										<c:forEach var="aptmtVO" items="<%=list%>" >
										<c:if test="${aptmtVO.appointmentStatus==4}" >										
										
    <tr  id='tr${aptmtVO.appointmentOrderNo}'>
      <th scope="row">${aptmtVO.coachNo}</th>
      <td><fmt:formatDate value="${aptmtVO.orderTime}" pattern="yyyy/MM/dd HH:mm"/></td>
      <td>${appointment_status[aptmtVO.appointmentStatus]}</td>
      <td>${aptmtVO.appointmentPrice}</td>
      <td><button class="btn btn-light" style="padding:15px 25px;" onClick="getAPDetail('${aptmtVO.appointmentOrderNo}')">詳細內容</button></td>
      <td></td>
    </tr>	
										</c:if></c:forEach>    
										<c:forEach var="aptmtVO" items="<%=list%>" >
										<c:if test="${aptmtVO.appointmentStatus==3}" >										
										
    <tr  id='tr${aptmtVO.appointmentOrderNo}'>
      <th scope="row">${aptmtVO.coachNo}</th>
      <td><fmt:formatDate value="${aptmtVO.orderTime}" pattern="yyyy/MM/dd HH:mm"/></td>
      <td>${appointment_status[aptmtVO.appointmentStatus]}</td>
      <td> </td>
      <td><button class="btn btn-light" style="padding:15px 25px;" onClick="getAPDetail('${aptmtVO.appointmentOrderNo}')">詳細內容</button></td>
            <td><button class="btn btn-light" onClick="cancelAP('${aptmtVO.appointmentOrderNo}')" style="padding:15px 25px;">取消訂單</button></td>
    </tr>	
										</c:if></c:forEach>  
										<c:forEach var="aptmtVO" items="<%=list%>" >
										<c:if test="${aptmtVO.appointmentStatus==2}" >										

    <tr id='tr${aptmtVO.appointmentOrderNo}'>
      <th scope="row">${aptmtVO.coachNo}</th>
      <td><fmt:formatDate value="${aptmtVO.orderTime}" pattern="yyyy/MM/dd HH:mm"/></td>
      <td>${appointment_status[aptmtVO.appointmentStatus]}</td>
      <td>${aptmtVO.appointmentPrice}</td>
      <td><button class="btn btn-light" onClick="getAPDetail('${aptmtVO.appointmentOrderNo}')" style="padding:15px 25px;">詳細內容</button></td>
      <td><button class="btn btn-light" onClick="payforAP('${aptmtVO.appointmentOrderNo}','${aptmtVO.appointmentPrice}','${aptmtVO.memberNo}')" style="padding:15px 25px;">確認付款</button></td>
    </tr>	
										</c:if></c:forEach>
										  </tbody>
</table>																						
</div></div>
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
</body>

<script>

//搜尋欄位限制
function CheckForm(form)
{  
if($('#inputtext4').val().trim().length===0)   {  
alert("請輸入關鍵字");
}else{form.submit();
}
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
    		var coachNo=apOrder.coachNo;
    		var orderTime=apOrder.orderTime;
    		var appointmentLocation=apOrder.appointmentLocation;
    		var appointmentDemand=apOrder.appointmentDemand;
    		var appointmentPrice=apOrder.appointmentPrice;
    		if(appointmentPrice==0)
    			appointmentPrice='';
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
						x=x+apOrder[i].appointmentDate+'<br>';
						break;
					case 1:	
						x=x+apOrder[i].appointmentDate+' <i class=\"fa fa-check-square\"></i><br>';
						break;								
    		}
				}
				  Swal.fire({
					  title: '預約內容',
					  width: 500,
					  html:`<table class="table"><thead>`+
						    `<tr><th scope="col" nowrap="nowrap">訂單編號`+appointmentOrderNo+`</th>
					  		    <th scope="col" nowrap="nowrap">教練編號`+coachNo+`</th></tr>
						  </thead><tbody>
						    <tr><th>申請時間</th>
							    <td>`+orderTime+`</td></tr>
						    <tr><th scope="row">預約價格</th>
						    	<td>`+appointmentPrice+`</td>					    				    				    
						    </tr>						    
						    <tr><th scope="row">預約地點</th>
						    	<td>`+appointmentLocation+`</td>
						    </tr><tr>
						    <th scope="row">預約需求</th>
						    <td>`+appointmentDemand+`</td></tr>
						    <tr><th scope="row">預約時間</th>
						    <td>`+x+`</td></tr></tbody>
						</table>`,

					})
	 		 							}		    	
				});
		  }		    	
    }); 	
}

//payforAP
function payforAP(apNo,apPrice,memNo){
	Swal.fire({
		  title: '確認付款',
		  icon:'warning',
		  showCancelButton: true,
		  confirmButtonText: '確定',
		  cancelButtonText: '取消',  
		}).then((result) => {
		  if (result.value) {
				$.ajax({
					url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
					type:"POST",
					data:{
					'action':'payforAppointment',
					'appointmentOrderNo':apNo,
					'appointmentPrice':apPrice,
					'memberNo':memNo,
					},	
						success:function(e){
							if(e=='1'){
							 Swal.fire({
								 title: '點數不足',
								 icon:'error'})						
							}else if(e=='0'){
								
							  Swal.fire({
								  title: '付款成功',
								  icon:'success'
						}).then((result) => {
							  if (result.value) {
								  window.location.replace("<%=request.getContextPath()%>/front-end/appointment_order/MemProgressingAppointment.jsp");  
							  }})
						}else{console.log(e)}
						}		
				}) 
		  }
		  })
}
//cancelAP
function cancelAP(apNo){
	Swal.fire({
		  title: '取消訂單',
		  icon:'warning',
		  showCancelButton: true,
		  confirmButtonText: '是',
		  cancelButtonText: '否',
		  confirmButtonColor:'#d33',
		  cancelButtonColor:'#3085d6',		  
		}).then((result) => {
		  if (result.value) {
				$.ajax({
					url:"<%=request.getContextPath()%>/appointment_order/appointmentOrder.do",
					type:"POST",
					data:{
					'action':'delete',
					'appointmentOrderNo':apNo,
					},	
						success:function(){
							  Swal.fire({
								  title: '成功取消訂單',
								  icon:'success'
						}).then((result) => {
							  if (result.value) {
								  $('#tr'+apNo).fadeOut('fast');
								  }
							  })
						}		
				}) 
		  }
		  })
}

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