<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Qa: Home</title>

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
   <tr><td><h3>IBM Qa: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Qa: Home</p>

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
	<input type="button" value="多筆查詢" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/qa/listAllQa.jsp'">
<br>
<br>
  </li>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/qa/qa.do" >
        <b>輸入QA編號 (如Q001):</b>
        <input type="text" name="qa_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="qaSvc" scope="page" class="com.qa.model.QaService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/qa/qa.do" >
       <b>選擇QA編號:</b>
       <select size="1" name="qa_no">
         <c:forEach var="qaVO" items="${qaSvc.all}" > 
          <option value="${qaVO.qa_no}">${qaVO.qa_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>QA管理:</h3>

<ul>
  <li><input type="button" value="新增QA" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/qa/addQa.jsp'"></li>
</ul>

</body>
</html>