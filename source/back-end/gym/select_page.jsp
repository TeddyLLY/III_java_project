<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Gym: Home</title>

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

img {
	width:150px;
	hright:150px;
}
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Gym: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Gym: Home</p>

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
	<input type="button" value="多筆查詢" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/gym/listAllGym.jsp'">
<br>
<br>
  </li>

  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gym/gym.do" >
        <b>輸入健身房編號 (如G001):</b>
        <input type="text" name="gym_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="gymSvc" scope="page" class="com.gym.model.GymService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gym/gym.do" >
       <b>選擇健身房編號:</b>
       <select size="1" name="gym_no">
         <c:forEach var="gymVO" items="${gymSvc.all}" > 
          <option value="${gymVO.gym_no}">${gymVO.gym_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/gym/gym.do" >
       <b>選擇健身房:</b>
       <select size="1" name="gym_no">
         <c:forEach var="gymVO" items="${gymSvc.all}" > 
          <option value="${gymVO.gym_no}">${gymVO.gym_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>健身房管理</h3>

<ul>
  <li><input type="button" value="新增健身房" onclick="javascript:location.href='<%=request.getContextPath()%>/back-end/gym/addGym.jsp'"></li>
</ul>

</body>
</html>