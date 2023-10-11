<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addEmployee.jsp</title>

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
	width: 450px;
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

img {
	width: 100px;
	height: 100px;
}
</style>

<script>
var loadFile = function(event) {
	var output = document.getElementById('output');
	output.src = URL.createObjectURL(event.target.files[0]);
};
</script>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料新增 - addEmployee.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do"
		name="form1" enctype="multipart/form-data">
		
		
		<table>
			<tr>
				<td>員工姓名:</td>
				<td><input type="TEXT" name="employee_name" size="45"
					value="<%=(employeeVO == null) ? "kuwajima" : employeeVO.getEmployee_name()%>" /></td>
			</tr>
			
			<tr>
				<td>職位</td>
				<td><select name="employee_title">
						
							<option value="主管理員" selected>主管理員</option>
					
							<option value="客服人員" selected>客服人員</option>

							<option value="後臺管理員" selected>後臺管理員</option>

							<option value="直播管理員" selected>直播管理員</option>
				
				</select></td>
			</tr>
			<tr>
				<td>帳號:</td>
				<td><input type="TEXT" name="employee_account" size="45"
					value="<%=(employeeVO == null) ? "k5548" : employeeVO.getEmployee_account()%>" /></td>
			</tr>
			<!-- 	<tr> -->
			<!-- 		<td>密碼:</td> -->
			<!-- 		<td><input type="TEXT" name="employee_password" size="45" -->
			<%-- 			 value="<%= (employeeVO==null)? "REFDHN" : employeeVO.getEmployee_password()%>" /></td> --%>
			<!-- 	</tr> -->
			<tr>
				<td>信箱:</td>
				<td><input type="TEXT" name="employee_mail" size="45"
					value="<%=(employeeVO == null) ? "kevinrockpig300@gmail.com" : employeeVO.getEmployee_mail()%>" /></td>
			</tr>

			<tr>
				<td>手機:</td>
				<td><input type="TEXT" name="employee_cellphone" size="45"
					value="<%=(employeeVO == null) ? "0965045859" : employeeVO.getEmployee_cellphone()%>" /></td>
			</tr>
			
			<tr>
			<td>照片:</td>
				<td>
				<input type="file" name="employee_photo" onchange="loadFile(event)">
				<img id="output" name="employee_photo" src="<%=request.getContextPath()%>/employee/DBGifReader?employee_no=${employeeVO.employee_no}">	
			<br><br>
				</td>
			</tr>

		</table>
		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>

</html>