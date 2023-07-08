<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>

<%
	NewsService newsSvc = new NewsService();
    List<NewsVO> list = newsSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE HTML>
<html>
<head>
<title>Free Gym Website Template | Classes :: w3layouts</title>

<!-- 新版bootstramp 看情況或許可以放 -->
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>



<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="<%=request.getContextPath()%>/front-end/gym_index/js/jquery.min.js"></script>



     <link rel="icon" href="<%=request.getContextPath()%>/front-end/news/images/favicon.ico" >
     <link rel="shortcut icon" href="<%=request.getContextPath()%>/front-end/news/images/favicon.ico"  />
     <link  rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/front-end/news/css/style.css">
     <link  rel="stylesheet" href="<%=request.getContextPath()%>/front-end/news/css/font-awesome.css">
     <script src="<%=request.getContextPath()%>/front-end/news/js/jquery.js"></script>
     <script src="<%=request.getContextPath()%>/front-end/news/js/jquery-migrate-1.1.1.js"></script>
     <script src="<%=request.getContextPath()%>/front-end/news/js/script.js"></script> 
     <script src="<%=request.getContextPath()%>/front-end/news/js/jquery.equalheights.js"></script>
     <script src="<%=request.getContextPath()%>/front-end/news/js/superfish.js"></script>
     <script  src="<%=request.getContextPath()%>/front-end/news/js/jquery.responsivemenu.js"></script>
     <script  src="<%=request.getContextPath()%>/front-end/news/js/jquery.mobilemenu.js"></script>
<link href="<%=request.getContextPath()%>/front-end/gym_index/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front-end/gym_index/css/style.css" rel='stylesheet' type='text/css' />


<style>
ul.list li a {
    color: #ffffff;
}

</style>
</head>
<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="clear"></div>
	
	<div class="main">
		<div class="about_banner_img">
			<img src="<%=request.getContextPath()%>/front-end/gym_index/images/class_img.jpg" class="img-responsive" alt="" style="height: 100%; width: 100%; object-fit: contain" />
		</div>

	
<!--=======content================================-->
<div class="content projects">
  <div class="container_12">
   <div class="grid_12">
    <h3 class="text-center" style="font-size: 36px">最新消息</h3>
   </div>

  <c:forEach var="newsVO" items="${list}">  

    <div class="grid_3">
      <div class="box ic7"><div class="maxheight">
        <h3>${newsVO.news_title}  <br>
		</h3><i class=" icon-book"></i>
        <p>${newsVO.news_time}</p>
        <p>${newsVO.news_content}</p>
<!--       <a href="#" class="btn">read more</a>-->
       </div> 
      </div>
    </div>
<!--     <div class="grid_3"> -->
<!--       <div class="box ic8"><div class="maxheight"> -->
<!--         <h3>project  <br> -->
<!-- name</h3><i class=" icon-pencil"></i> -->
<!--         <p>Folern aditaut onsequ untur magni dolores eoquie voluptate msequi nesciunt. Nique porro quisquam est qui dolorem ipsumquia dolor sitamet consectet.</p> -->
<!--         <a href="#" class="btn">read more</a></div> -->
<!--       </div> -->
<!--     </div> -->
<!--     <div class="clear cl2"></div> -->
   </c:forEach>	 
  </div>
</div>
	
		
		
	</div>
	
	
<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
	
</body>
</html>