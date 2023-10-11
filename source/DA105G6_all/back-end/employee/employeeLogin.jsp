<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>login</title>
</head>
<body>
	<%
		String message = "";
		String error = (String) request.getAttribute("error");
		if(message != null){
			message = error;
		}
	%>
	<span id="msg"><%=message %></span>
	
	
	<center>
		<form action="<%=request.getContextPath() %>/login/EmployeeLogin" method="post">

			<table border=1>
				<tr>
					<td colspan=2>
						<p align=center>
							輸入<b>(測試登入)</b>:<br> 帳號:<b>JOHN2014</b><br> 密碼:<b>UYT755</b><br>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>account:</b>
					</td>
					<td>
						<p>
							<input type=text name="employee_account" value="" size=15>
					</td>
				</tr>

				<tr>
					<td>
						<p align=right>
							<b>password:</b>
					</td>
					<td>
						<p>
							<input type=password name="employee_password" value="" size=15>
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center><input type=submit value="登入">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>