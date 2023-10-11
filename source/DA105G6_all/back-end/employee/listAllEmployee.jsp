<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	EmployeeService employeeSvc = new EmployeeService();
    List<EmployeeVO> list = employeeSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<title>所有員工資料 - listAllEmployee.jsp</title>
<%@ include file="/back-end/gym_index/back-end-head-source.file" %>


</head>

<%@ include file="/back-end/gym_index/back-end-navbar.file" %>
<%@ include file="/back-end/gym_index/back-end-sidebar.file" %>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page/page1.file" %> 

	<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td><div align="center" style="height:50px;line-height:50px;">${employeeVO.employee_no}</div></td>
			<td>${employeeVO.employee_name}</td>
			<td>${employeeVO.employee_title}</td>
			<td>${employeeVO.employee_account}</td>
			<td>${employeeVO.employee_password}</td>
			<td>${employeeVO.employee_mail}</td> 
			<td>${employeeVO.employee_cellphone}</td>
			
			<td><img name="employee_photo" class="employee_photo" src="<%=request.getContextPath()%>/employee/DBGifReader?employee_no=${employeeVO.employee_no}"></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="employee_no"  value="${employeeVO.employee_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="employee_no"  value="${employeeVO.employee_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
		</tr>
	</c:forEach>

</table>
<%@ include file="page/page2.file" %>


</html>