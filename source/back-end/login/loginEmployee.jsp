<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V12</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--============== =================================================================================-->	
	<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/back-end/login/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/login/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/login/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/login/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/login/vendor/select2/select2.min.cs">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/login/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('<%=request.getContextPath()%>/back-end/gym_index/login/images/img-01.jpg');">
			<div class="wrap-login100 p-t-190 p-b-30">
				<form class="login100-form validate-form" action="<%=request.getContextPath() %>/login/EmployeeLogin" method="post">
					<div class="login100-form-avatar">
						<img src="<%=request.getContextPath()%>/back-end/login/images/avatar-01.jpg" alt="AVATAR">
					</div>

					<span class="login100-form-title p-t-20 p-b-45">
						<input type="button" class="magic btn btn-round btn-info" value="神奇小按鈕">
							
					</span>

					<div class="wrap-input100 validate-input m-b-10" data-validate = "Username is required">
						<input class="input100" type="text" name="employee_account" placeholder="Username" id="employee_account">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input m-b-10" data-validate = "Password is required">
						<input class="input100" type="password" name="employee_password" placeholder="Password" id="employee_password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock"></i>
						</span>
					</div>

					<div class="container-login100-form-btn p-t-10">
		
						<button class="login100-form-btn" type="submit">
							Login
						</button>
						<br>
					</div>					

					<div class="text-center w-full p-t-25 p-b-230">
						<a href="#" class="txt1">
					
						</a>
					</div>

					<div class="text-center w-full">
						<a class="txt1" href="#">
							
							<i class="fa"></i>						
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="<%=request.getContextPath()%>/back-end/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/back-end/login/vendor/bootstrap/js/popper.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/back-end/login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/back-end/login/js/main.js"></script>

</body>

<script>
		$(".magic").click(function(){
			$("#employee_password").val('123');
			$("#employee_account").val('EE001');
		})
</script>

</html>