<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.live.model.*"%>

<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%
	
	//LiveVO LiveVO = (LiveVO) request.getAttribute("LiveVO");
	LiveService LiveSvc = new LiveService();
	LiveVO LiveVO = LiveSvc.getOneLive("A001");
%>

<%-- ���X ������DeptVO����--%>


<html>
<head>
<title>���u��� - listOneLive.jsp</title>

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
	width: 800px;
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
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneLive.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/live_select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�����s��</th>
		<th>�����w�i</th>
		<th>�����ɶ�</th>
		<th>�������D</th>
		<th>������T</th>
		<th>�нm�s��</th>
		
	</tr>
	<tr>
		<td><%=LiveVO.getLive_no()%></td>
		<td><%=LiveVO.getLive_notice()%></td>
		<td><%=LiveVO.getLive_time()%></td>
		<td><%=LiveVO.getLive_title()%></td>
		<td><%=LiveVO.getLive_imformation()%></td>
		<td><%=LiveVO.getCoach_no()%></td>
		
		
	</tr>
</table>

</body>
</html>