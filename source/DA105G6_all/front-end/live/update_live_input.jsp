<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.live.model.*"%>

<%	
	LiveVO liveVO = (LiveVO) request.getAttribute("liveVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>直播資料修改 - update_live_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 480px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>直播資料修改 - update_live_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/live_select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/live/live.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>直播編號:<font color=red><b>*</b></font></td>
		<td><input type="hidden" name="live_no"  value="<%=liveVO.getLive_no()%>"/><%=liveVO.getLive_no()%></td>
	</tr>
	<tr>
		<td>直播編號:<font color=red><b>*</b></font></td>
		<td><input type="text" name="live_no"  value="<%=liveVO.getLive_no()%>"/><%=liveVO.getLive_no()%></td>
	</tr>
	<tr>
		<td>直播編號:<font color=red><b>*</b></font></td>
		<td><input type="hidden" name="live_no"  value="<%=liveVO.getLive_no()%>"/><%=liveVO.getLive_no()%></td>
	</tr>
	<tr>
		<td>直播編號:<font color=red><b>*</b></font></td>
		<td><input type="hidden" name="live_no"  value="<%=liveVO.getLive_no()%>"/><%=liveVO.getLive_no()%></td>
	</tr>
	<tr>
		<td>直播編號:<font color=red><b>*</b></font></td>
		<td><input type="hidden" name="live_no"  value="<%=liveVO.getLive_no()%>"/><%=liveVO.getLive_no()%></td>
	</tr>
	
	

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="live_no" value="<%=liveVO.getLive_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->



<script>
var loadFile = function(event) {
	var output = document.getElementById('output');
	output.src = URL.createObjectURL(event.target.files[0]);
};        
        
</script>
</html>