<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>

<%
	QaVO qaVO = (QaVO) request.getAttribute("qaVO"); 
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Q&A修改</title>

  <!--external css-->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style-responsive.css" rel="stylesheet">
  
<%@ include file="/back-end/gym_index/back-end-head-source.file" %>

  
</head>

<body>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
   
     <%@ include file="/back-end/gym_index/back-end-navbar.file" %>
   
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
     
     <%@ include file="/back-end/gym_index/back-end-sidebar.file" %>
     
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <h3><i class="fa fa-hand-o-right"></i>Q&A修改管理</h3>
        <br>
        <div class="row mt">
          <div class="col-md-12">

	<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
		          
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/qa/qa.do" name="form1">
              <table
										class="table table-bordered table-striped table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
					
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
				<td>Q&A編號:<font color=red><b>*</b></font></td>
				<td><%=qaVO.getQa_no()%></td>
			</tr>
			<tr>
				<td>問:</td>
				<td><input type="TEXT" name="q_content" size="45"
					value="<%=qaVO.getQ_content()%>" /></td>
			</tr>

			<tr>
				<td>答:</td>
				<td><input type="TEXT" name="a_content" size="45" 
				value="<%=qaVO.getA_content()%>" /></td>
			</tr>
			
     		<tr>
     			<td></td>
     			<td><input type="hidden" name="action" value="update"> 
      	   			<input type="hidden" name="qa_no" value="<%=qaVO.getQa_no()%>"> 
      	   			<button type="submit" class="btn btn-theme" >送出修改</button>
      	   			<button class="btn btn-theme04" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/qa/Q&A.jsp'">取消</button>
      	   		</td>      
      			
      </table>

	</FORM>
            </div>
          </div>
  
        </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->

  </section>
 </section>
  <!--script for this page-->
  
</body>

</html>
