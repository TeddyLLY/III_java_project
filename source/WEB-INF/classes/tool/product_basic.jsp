<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  <!--external css-->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/back-end/gym_index/css/style-responsive.css" rel="stylesheet">
  
<%@ include file="/back-end/gym_index/back-end-head-source.file" %>

<style>
		.table{
			table-layout: fixed;
			
		}
		
</style>
</head>

<body onload="connect();" onunload="disconnect();">
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
   
     <%@ include file="/back-end/gym_index/back-end-navbar.file" %>
   
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
     
     <%@ include file="/back-end/gym_index/back-end-sidebar.file" %>
     
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <h3><i class="fa fa-hand-o-right"></i>商品管理</h3>
<!--         <input type="submit" id="sendMessage" class="button" value="新品上市" onclick="sendMessage();"/> -->
           <button type="submit" id="sendMessage" class="btn btn-theme" onclick="sendMessage();">新品上市</button> 
              
	<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<div class="row mt">
					<div class="col-lg-12">
							
								<div style="overflow:scroll;overflow-X:hidden;height:550px;">
									<table
										class="table table-bordered table-striped table-condensed"
										style="text-align: center;min-width: 1500px;background-color: #a7a7a7a1"
										cellpadding="10" border='1'>
										<thead>
											<tr style="white-space: nowrap; color:white; background-color: #4a4a54;">
                    <th>商品編號</th>
					<th>名稱</th>
					<th>照片</th>
					<th>數量</th>
					<th>價格</th>
					<th>銷量</th>
					<th>內容</th>
					<th>總評價分數</th>
					<th>總評價人數</th>
					<th>狀態</th>
					<th>評價分數平均</th>
                  	<th></th>
                  	<th></th>
                  	
                  </tr>
  
					<c:forEach var="ProductVO" items="${list}">

			<tr>

				<td>${ProductVO.product_no}</td>
				<td>${ProductVO.product_name}</td>
				<td><img src="<%=request.getContextPath() %>/product/DBGifReader4?product_no=${ProductVO.product_no}"style="width:100PX; heigth:100px"></td>
				<td>${ProductVO.product_quantity}</td>
				<td>${ProductVO.product_price}</td>
				<td>${ProductVO.product_sales}</td>
				<td>${ProductVO.product_content}</td>
				<td>${ProductVO.product_total_evaluation}</td>
				<td>${ProductVO.product_people_of_evaluation}</td>
				<td>${product.get(ProductVO.product_status.toString())}</td>
				<td>${ProductVO.product_average_evaluation}</td>
				
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-theme" >修改</button>
						<input type="hidden" name="product_no" value="${ProductVO.product_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				
				<td>
					<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-theme04" >刪除</button>
						<input type="hidden" name="product_no" value="${ProductVO.product_no}"> 
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
      </table>
 
            </div>
          </div>

        </div>
        </section>
  
    <!-- /MAIN CONTENT -->
    <!--main content end-->
  
  </section>
</section> 
  <!--script for this page-->
  
</body>
<script>
    
    var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
//  var endPointURL = "ws://localhost:8081/IBM_WebSocket1_ChatA/MyEchoServer";
    var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("WebSocket 成功連線");
		};

		webSocket.onmessage = function(event) {
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
	   var message = "成功推播";
	   webSocket.send(message);
	   return swal("恭喜!","成功推播", "success", {button: "完成"});
	}

	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
    
</script>
</html>
