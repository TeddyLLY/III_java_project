<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>front-end jsp 請都要有這些</title>

<!--  gym_index 裡面的 bootstrap and style and metadata header -->
<%@ include file="/front-end/gym_index/front-end-head.file" %>
<%-- <%@ include file="/front-end/gym_index/front-end-bs-new-version.file" %> --%>
<!-- old bootstrap for modal -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
	<!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>
	
	
    <div class="container">   
        <div class="row">
            <div class="col-md-12">
            
            </div>
        </div>
    </div>
    	
	
<!-- footer 放body尾 -->
<%@ include file="/front-end/gym_index/front-end-footer.file" %>
	
</body>


</html>