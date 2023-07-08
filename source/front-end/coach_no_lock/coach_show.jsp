<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coach.model.*" %>
<%@ page import="com.report_coach.model.*" %>

<%
	ReportCoachVO reportCoachVO = new ReportCoachVO();
	String showCoach_no = request.getParameter("coach_no");
	CoachVO showCoachVO = new CoachVO();
	CoachService coachSvc = new CoachService();
	showCoachVO = coachSvc.findOneCoach(showCoach_no);
%>

<!DOCTYPE html>
<html>

<head>
<title>精選教練</title>

<!--  header  !! metadata css js function include -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %>
<!-- old bootstrap for modal -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/coach_introduction.css">
    <script>
        // Carousel Auto-Cycle
        $(document).ready(function() {
            $('.carousel').carousel({
                interval: 6000
            })
        });
    </script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/in/css/bg.css">
    
</head>

<body>
	<!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>
	
	
  <div class="container-fluid"  style="box-shadow: 12px 12px 7px rgba(0, 0, 0, 0.7);">
        <div class="row-fluid">
            <div class="span12">

                <div class="page-header">
								<c:if test="${not empty errorMsgs}">
									<ul>
										<c:forEach var="errorMsgs" items="${errorMsgs}">
											<li style="color: red">${errorMsgs}</li>
										</c:forEach>
									</ul>
								</c:if>
						
                    <h3>精選教練</h3>
                    <p></p>
                </div>

                <div class="carousel slide" id="myCarousel">
                    <div class="carousel-inner">

                        <div class="item active">

                            <div class="bannerImage">
								 <img src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=<%=showCoachVO.getCoach_no()%>" style="border-radius: 50px; width:auto; height:405px;" class="img-responsive center-block">
                            </div>

                            <div class="caption row-fluid">
                                <div class="span4">
                                    <h3>Coach Data</h3>
<!--                                     <a class="btn btn-mini" href="#">&raquo; Read More</a> -->
                                </div>
                                <div class="span8">
                                   	<h3>
	                                   	教練總評價分數 : <%= showCoachVO.getCoach_total_evaluation() %> &emsp; 
	                                   	教練平均分數 : <%= showCoachVO.getCoach_average_evaluation() %>
	                                   	 &emsp; 教練總評價人數 : <%= showCoachVO.getCoach_total_people_evaluation() %>
                                   	</h3>
                                    <p>
                                    	教練姓名 : <%= showCoachVO.getCoach_name() %>  &emsp;
                                    	教練編號 : <%= showCoachVO.getCoach_no() %>  &emsp;
                                    	教練性別 : <%= showCoachVO.getCoach_sex() %>  &emsp; <br>
                                    </p>
                                </div>
                            </div>

                        </div>
                        <!-- /Slide1 -->

                        <div class="item">

                            <div class="bannerImage" >
                               <img src="<%= request.getContextPath()%>/CoachLicenseReader?coach_no=<%=showCoachVO.getCoach_no()%>" style="border-radius: 50px; width:auto; height:405px;" class="img-responsive center-block">
                            </div>

                            <div class="caption row-fluid">
                                <div class="span4">
                                    <h3>Coach introduction</h3>
<!--                                     <a class="btn btn-mini" href="#">&raquo; Read More</a> -->
                                </div>
                                <div class="span8">
                                    <p><%= showCoachVO.getCoach_introduction() %></p>
                                </div>
                            </div>

                        </div>
                        <!-- /Slide2 -->

                        <div class="item">

                            <div class="bannerImage">
                           			 <img src="<%= request.getContextPath()%>/CoachPhotoReader?coach_no=<%=showCoachVO.getCoach_no()%>" style="width:auto; height:405px; border-radius: 50px;" class="img-responsive center-block">
                            </div>

                            <div class="caption row-fluid">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reportCoach.do" name="form1">
                                <div class="span4">
                                    <h3>檢舉教練 </h3>
									
                                </div>
                                <div class="span8">
                                
														<input type="hidden" name="coach_no" size="45" 	value="<%=showCoachVO.getCoach_no()%>" />
														<input type="hidden" name="member_no" size="45" value="${memberVO.member_no}" />
														
														<p>檢舉內容: </p>
														<input type="hidden" name="report_coach_status" value="0">
													<textarea name="report_coach_content" style=" float:right; width:700px; "></textarea>
														
											<p>教練編號: <%=showCoachVO.getCoach_no()%>
											<input type="hidden" name="action" value="insertOneReportCoach"> 
													<input type="submit" class="btn btn-mini" value="送出"></p>
											
                                </div>
				</FORM>
                            </div>

                        </div>
                        <!-- /Slide2 -->

                    </div>

                    <div class="control-box">
                        <a data-slide="prev" href="#myCarousel" class="carousel-control left">‹</a>
                        <a data-slide="next" href="#myCarousel" class="carousel-control right">›</a>
                    </div>
                    <!-- /.control-box -->

                </div>
                <!-- /#myCarousel -->

            </div>
            <!-- /.span12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->

    	
	
<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
	
</body>


</html>