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
<title>健身房資料 - listOneGym.jsp</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
html, body {
	width: 100%;
	height: 100%;
}

#test div {
  	display:inline;
  }
</style>



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
						   						
							getGymPeople(obj)
						});
				},
				error:function(){
  				  console.log("no")  
  			  },
				timeout: 10000			
			});
			function getGymPeople(obj){		
				
				console.log(obj.gym[0]);
				console.log(obj.gym[1]);
			
				var people = 
							`
							<ul>
								<li id="gym" style="display: inline-block;">
									<b>健身房 </b>
									<big id="gym_on">\${obj.gym[0]}</big><b> 人 </b>
								<i> 容留<b id="gym_all">\${obj.gym[1]}</b>人</i></li>
							</ul>
							`;
			
			$("div.col-md-12").append(people);
			}
		}
	});
</script>

</head>

<body bgcolor='white'>
		
	<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
		</div>
	</div>
</div>
	
	

</body>


</html>