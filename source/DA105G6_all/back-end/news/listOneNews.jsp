<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  NewsVO newsVO = (NewsVO) request.getAttribute("newsVO"); 
  NewsService newsSvc = new NewsService();
%>

<html>
<head>
<title>QA資料 - listOneQa.jsp</title>

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
		 <h3>QA資料 - ListOneQa.jsp</h3>
		 <h4>
		 <a href="<%=request.getContextPath()%>/back-end/qa/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/back-end/qa/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		 </h4>
	</td></tr>
</table>

<table>
	<tr>
		<td>編號</td>
		<td>標題</td>
		<td>內容</td>
		<td>時間</td>

	</tr>
	<tr>
		<td><div align="center" style="height:50px;line-height:50px;">${newsVO.news_no}</div></td>
		<td>${newsVO.news_title}</td>
		<td>${newsVO.news_content}</td>
		<td>${newsVO.news_time}</td>
	</tr>


</table>

</body>
</html>