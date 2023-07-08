<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>

<%
	QaService qaSvc = new QaService();
    List<QaVO> list = qaSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

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
     	<h3><i class="fa fa-hand-o-right"></i>Q&A列表</h3>
       <h3><button class="btn btn-theme" type="button" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/qa/addQa.jsp'">新增Q&A</button></h3>
        <div class="row mt">
          <div class="col-md-12">

 								<table
										class="table table-bordered table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
                    <th>編號</th>
                    <th>問</th>
					<th>答</th>
                    <th></th>
                  	<th></th>
                  </tr>
                </thead>
                <tbody>
                
           <c:forEach var="qaVO" items="${list}">
                
                  <tr>
                    <td>${qaVO.qa_no}</td>
                    <td>${qaVO.q_content}</td>
					<td>${qaVO.a_content}</td>                            
                   
                    <td>
                    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/qa/qa.do">
	                      <button type="submit" class="btn btn-theme" >修改</button> 
	                      <input type="hidden" name="qa_no"  value="${qaVO.qa_no}">
	                      <input type="hidden" name="action"  value="getOne_For_Update"></FORM>
	                 </td>
	                 <td>	                    
	                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/qa/qa.do"> 
	                      <button class="btn btn-theme04" type="submit">刪除</button>
	                      <input type="hidden" name="qa_no"  value="${qaVO.qa_no}">
			     		  <input type="hidden" name="action" value="delete"></FORM>
                    </td>
                  </tr>
            
           </c:forEach>    

                </tbody> 
              </table>
            </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        <!-- /row -->
      </section>
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->

  </section>
 
  <!--script for this page-->
  
</body>

</html>
