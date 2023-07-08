<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); //EmpServlet.java(Concroller), 存入req的employeeVO物件
  EmployeeService employeeSvc = new EmployeeService();
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  img.employee_photo {
  	width:100px;
  	height:100px;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4>
		 <a href="<%=request.getContextPath()%>/back-end/employee/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/back-end/employee/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		 </h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>職位</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>信箱</th>
		<th>手機</th>
		<th>照片</th>
	</tr>
	<tr>
		<td><%=employeeVO.getEmployee_no()%></td>
		<td><%=employeeVO.getEmployee_name()%></td>
		<td><%=employeeVO.getEmployee_title()%></td>
		<td><%=employeeVO.getEmployee_account()%></td>
		<td><%=employeeVO.getEmployee_password()%></td>
		<td><%=employeeVO.getEmployee_mail()%></td>
		<td><%=employeeVO.getEmployee_cellphone()%></td>
		<td><img class="employee_photo" src="<%=request.getContextPath() %>/employee/DBGifReader?employee_no=${employeeVO.employee_no}"></td>
	</tr>
</table>

</body>
</html>