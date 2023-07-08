<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Employee: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Employee: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Employee: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>

  <li>
	<input type="button" value="多筆查詢" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/employee/listAllEmployee.jsp'">
<br>
<br>
  </li>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" >
        <b>輸入員工編號 (如E001):</b>
        <input type="text" name="employee_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="employeeSvc" scope="page" class="com.employee.model.EmployeeService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="employee_no">
         <c:forEach var="employeeVO" items="${employeeSvc.all}" > 
          <option value="${employeeVO.employee_no}">${employeeVO.employee_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="employee_no">
         <c:forEach var="employeeVO" items="${employeeSvc.all}" > 
          <option value="${employeeVO.employee_no}">${employeeVO.employee_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><input type="button" value="新增員工" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/employee/addEmployee.jsp'"></li>
</ul>

</body>
</html>