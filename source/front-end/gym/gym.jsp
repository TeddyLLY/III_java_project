<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.gym.model.*"%>
<%@ page import="java.util.*"%>

<%
	GymVO gymVO = (GymVO) request.getAttribute("gymVO");
	GymService gymSvc = new GymService();
	List<GymVO> list = gymSvc.getAll();
	pageContext.setAttribute("list", list);
// 	for (GymVO gymVO1 : list) {
// 		System.out.println(gymVO1.getGym_latitude());
// 		System.out.println(gymVO1.getGym_longitude());
// 	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Workout Anywhere</title>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>

<link href="<%=request.getContextPath()%>/front-end/gym_index/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front-end/gym_index/css/style.css" rel='stylesheet' type='text/css' />




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
<link href="<%=request.getContextPath()%>/lesson_pkg/css/style.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<style>
html{
	width: 100%;
	height: 100%;
}

#test div {
  	display:inline;
  }

#directions_panel{
	height:auto; width:300px;background-color:#fff;
}

td{
	height:51px;
}
.table tbody tr:hover td, .table tbody tr:hover th {
    background-color: #72D0F4;
}
</style>
<style type="text/css" media="screen">
*{
font-family:'Noto Sans TC', sans-serif;
}

#inputtext4 {
	margin-left: 30%;
	background-color:white;
}

div.border1 {
    border:5px solid #00BFF0;
}


.thumb-content .profile-image {
	position: absolute;
    top: -20px;
    left: 5px;
    z-index: 1;
}  
</style>
</head>
<body bgcolor='white'>

<%@ include file="/front-end/gym_index/front-end-navbar.file"%>
	
                <div class="main-heading-content text-center">                           
               				 <div class="google-map" id="map" class="embed-responsive embed-responsive-16by9" style="width: 100%; height:400px;"></div>

                </div>
	<div class="about_banner_wrap">
      	<h1 class="m_11">健身房地點</h1>
       </div>
      <div class="border1"> </div>

  <!--main content start-->

   			<div class="row justify-content-center align-items-center">
				<div class="col-md-8">
				  <div class="col-md-3">
					<div class="category-sidebar" style="margin-top:100px;">
						<div class="widget category-list">
							<h4 class="widget-header" >健身房相關</h4>
							<ul class="category-list">
								<li><a href="#">健身房總覽 <span></span></a></li>
								<li><a href="#">健身房人數 <span></span></a></li>

							</ul>
						</div>			
					</div>
				</div>
					<div class="category-search-filter">
						<div class="row justify-content-center align-items-center">
						<div class="col-md-6">
								<strong>健身房查詢</strong> 
							</div>

						</div>
					</div>
				<!-- single item -->
				        <%@ include file="pages/page1.file" %> 
					<div class="product-grid-list">
						<div class="row mt-30">						 
							<c:forEach var="gymVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="i">
							<div class="col-sm-12 col-lg-5 col-md-6">
								<!-- product card -->
								<div class="product-item bg-light">
									<div class="card">
										<div class="thumb-content">
											
										</div>										
										<div class="card-body">
				      
				            <h4 class="card-title">
								<a class="title-text" href="#">${gymVO.gym_name}</a>
							</h4><br>		
							<ul class="list-inline product-meta" id="gymPeople">			
<li class="list-inline-item"><i class="fa fa-user-o "></i><a id="gym_on${i.index}"></a> /<a id="gym_all${i.index}"></a></li>				    

<!-- 							<li class="list-inline-item" id="num1LE001"><i class="fa fa-user-o "></i> 8 / 10</li> -->
							</ul>							
							<p>地址: <em>${gymVO.gym_address}</em>
				            </p>								
					            <button type="button" class="btn btn-primary" id="${gymVO.gym_no}">位置</button>							
										</div>
									</div>
								</div>
							</div>							
				</c:forEach>
						
				</div></div>
				<div class="pagination justify-content-center">
				<%if (pageNumber > 1) {%>
					<nav aria-label="Page navigation example" style="margin-left:700px;">
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

<%@ include file="/front-end/gym_index/front-end-footer.file"%>
</body>

<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBZGVKhad8IGzpiKosnZFKHvG_hEy79v80&callback=initMap&libraries=geometry"
	async defer>
</script> 

<script>
    var map;     
 	// 載入路線服務與路線顯示圖層
    function initMap(){
    	var directionsService = new google.maps.DirectionsService();     
    	var directionsDisplay = new google.maps.DirectionsRenderer({
            	polylineOptions: {
                strokeColor: "red"
            	}
            });
    	
    	map = new google.maps.Map(document.getElementById('map'), {
    	    center: { lat: 24.967748, lng: 121.191698 },
    	    zoom: 15,
    	    styles:
    	    	[
    	    	    {
    	    	        "featureType": "administrative",
    	    	        "elementType": "labels.text.fill",
    	    	        "stylers": [
    	    	            {
    	    	                "color": "#444444"
    	    	            }
    	    	        ]
    	    	    },
    	    	    {
    	    	        "featureType": "landscape",
    	    	        "elementType": "all",
    	    	        "stylers": [
    	    	            {
    	    	                "color": "#f2f2f2"
    	    	            }
    	    	        ]
    	    	    },
    	    	    {
    	    	        "featureType": "poi",
    	    	        "elementType": "all",
    	    	        "stylers": [
    	    	            {
    	    	                "visibility": "off"
    	    	            }
    	    	        ]
    	    	    },
    	    	    {
    	    	        "featureType": "road",
    	    	        "elementType": "all",
    	    	        "stylers": [
    	    	            {
    	    	                "saturation": -100
    	    	            },
    	    	            {
    	    	                "lightness": 45
    	    	            }
    	    	        ]
    	    	    },
    	    	    {
    	    	        "featureType": "road.highway",
    	    	        "elementType": "all",
    	    	        "stylers": [
    	    	            {
    	    	                "visibility": "simplified"
    	    	            }
    	    	        ]
    	    	    },
    	    	    {
    	    	        "featureType": "road.arterial",
    	    	        "elementType": "labels.icon",
    	    	        "stylers": [
    	    	            {
    	    	                "visibility": "off"
    	    	            }
    	    	        ]
    	    	    },
    	    	    {
    	    	        "featureType": "transit",
    	    	        "elementType": "all",
    	    	        "stylers": [
    	    	            {
    	    	                "visibility": "off"
    	    	            }
    	    	        ]
    	    	    },
    	    	    {
    	    	        "featureType": "water",
    	    	        "elementType": "all",
    	    	        "stylers": [
    	    	            {
    	    	                "color": "#ee9e9e"
    	    	            },
    	    	            {
    	    	                "visibility": "on"
    	    	            }
    	    	        ]
    	    	    }
    	    	]
    	});   		 
    	
        // 放置路線圖層
        directionsDisplay.setMap(map);

    	var marker = new google.maps.Marker();
	    
    	 <c:forEach var="gymVO" items="${list}">
  	   
    	 $("#${gymVO.gym_no}").click(function (){
    	    	
    		   	marker.setMap(null);   		
    		   	
    	    	 marker = new google.maps.Marker({
    	           	 position: {lat: ${gymVO.gym_latitude}, lng: ${gymVO.gym_longitude}},
    	           	 map: map,   	        	           
    	           	 animation: google.maps.Animation.DROP
    	    	 });      	 
  	    	 	    	
    	    	 // 路線相關設定
     		    var request = {
     		        origin: { lat: 24.967748, lng: 121.191698 },
     		        destination: { lat: ${gymVO.gym_latitude}, lng: ${gymVO.gym_longitude} },
   		            travelMode: 'DRIVING'
     		   		
    	    	}; 
    	    	    	    	
//     	    	 map.panTo(marker.getPosition());	//以marker為地圖中心點  	 
   	    	    	
    	    	 directionsService.route(request, function (response, status) {
    	    	  	    		 
    	    		 	if (status == 'OK') {
    	    	            // 回傳路線上每個步驟的細節
    	    	            console.log(response.routes[0].legs[0].steps);
    	    	            directionsDisplay.setDirections(response);
    	    	            
//     	    	          	directionsDisplay.getDirections().routes[0].legs[0].start_address;
//     	    	    		directionsDisplay.getDirections().routes[0].legs[0].end_address;
//    	    	    		directionsDisplay.getDirections().routes[0].legs[0].duration.text;
//      	    	    	directionsDisplay.getDirections().routes[0].copyrights;
//      	    	    	directionsDisplay.getDirections().routes[0].legs[0].steps[0].instructions;
//   	    	    		directionsDisplay.getDirections().routes[0].legs[0].steps[0].distance.text;   	    	    		 
//     	    	    		distance = response.routes[0].legs[0].distance.text; 	//距離
//     	    	            duration = response.routes[0].legs[0].duration.text;    //時間	    	    		
//     	    	    		document.getElementById("distance_road").innerHTML = distance; 
//     	    	            document.getElementById("duration").innerHTML = duration; 
    	    	            
    	    	        } else {
    	    	            console.log(status);
    	    	        }
    	    	    });
    	    	    	 
    	 });  	
    	 </c:forEach>		
    	
     	 directionsDisplay.setPanel(document.getElementById("directions_panel")); //畫布        
    }
 	
 	
 	
</script>
<script>
	$(document).ready(function(){
		people();
		setInterval(people, 6000000);	
		function people(){		
			$.ajax({
				url: '<%=request.getContextPath()%>/ajax2/MyCrawler.do',
				type: 'POST',
				dataType: 'json',		
				success: function(jsonArray){
					console.log("ok")
							
						$.each(jsonArray,function(index,obj){						
						   						
							getGymPeople(index,obj)
						});
				},
				error:function(){
  				  console.log("no")  
  			  },
				timeout: 10000			
			});
			function getGymPeople(index,obj){		
				
				console.log(obj.gym[0]);
				console.log(obj.gym[1]);
			
				$("#gym_on"+index).html(obj.gym[0]);
				$("#gym_all"+index).html(obj.gym[1]);
			

			}
		}
	});
	
	window.onload = function init() {
		
		for(var i = 0; i<15; i++) {
			$("#gym_on"+i).html(Math.floor(Math.random() * 32)+ 6);
			$("#gym_all"+i).html(Math.floor(Math.random() * 40)+ 70);
		}
	}

	</script>
</html>