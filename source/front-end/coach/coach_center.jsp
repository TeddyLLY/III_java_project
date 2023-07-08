<!-- jsp header -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>

<title>教練中心</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/coach/coach_center_style.css">
</head>

<body>

<!-- nav_bar 放body頭 -->
<%@ include file="/front-end/gym_index/front-end-navbar.file" %>

	
			  <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/gym_index/js/nav.js"></script>
		  </div><!-- end h_menu4 -->
		 <div class="clear"></div>
		 
	  </div>
	</div>
	<!-- end menu -->
	<br><br><br>
	<div class="container">
		<div class="col-md-12">
			<div class="row">

				<!-- coach center bar -->
				<%@ include file="/front-end/coach/coach_bar.file"%>

				<form class="col-md-8">
					<div class="title">
						<h1 class="green" id="calendar-title">Month</h1>
						<h2 class="green small" id="calendar-year">pYear</h2>
						<a href="" id="prev">上月</a> <a href="" id="next">下月</a>
					</div>
					<div class="body">
						<div class="lightgrey body-list">
							<ul>
								<li>星期一</li>
								<li>星期二</li>
								<li>星期三</li>
								<li>星期四</li>
								<li>星期五</li>
								<li>星期六</li>
								<li>星期日</li>
							</ul>
						</div>
						<div class="darkgrey body-list">
							<ul id="days">
							</ul>
						</div>
					</div>
				</form>
				<script type="text/javascript">
					$('#myList a').on('click', function(e) {
						e.preventDefault()
						$(this).tab('show')
					})

					var month_olympic = [ 31, 29, 31, 30, 31, 30, 31, 31, 30,
							31, 30, 31 ];
					var month_normal = [ 31, 28, 31, 30, 31, 30, 31, 31, 30,
							31, 30, 31 ];
					var month_name = [ "一月", "二月", "三月", "四月", "五月", "六月",
							"七月", "八月", "九月", "十月", "十一月", "十二月" ];

					var holder = document.getElementById("days");
					var prev = document.getElementById("prev");
					var next = document.getElementById("next");
					var ctitle = document.getElementById("calendar-title");
					var cyear = document.getElementById("calendar-year");

					var my_date = new Date();
					var my_year = my_date.getFullYear();
					var my_month = my_date.getMonth();
					var my_day = my_date.getDate();

					//获取某年某月第一天是星期几
					function dayStart(month, year) {
						var tmpDate = new Date(year, month, 1);
						return (tmpDate.getDay());
					}

					//计算某年是不是闰年，通过求年份除以4的余数即可
					function daysMonth(month, year) {
						var tmp = year % 4;
						if (tmp == 0) {
							return (month_olympic[month]);
						} else {
							return (month_normal[month]);
						}
					}

					function refreshDate() {
						var str = "";
						var totalDay = daysMonth(my_month, my_year); //获取该月总天数
						var firstDay = dayStart(my_month, my_year); //获取该月第一天是星期几
						var myclass;
						for (var i = 1; i < firstDay; i++) {
							str += "<li></li>"; //为起始日之前的日期创建空白节点
						}
						for (var i = 1; i <= totalDay; i++) {
							if ((i < my_day && my_year == my_date.getFullYear() && my_month == my_date
									.getMonth())
									|| my_year < my_date.getFullYear()
									|| (my_year == my_date.getFullYear() && my_month < my_date
											.getMonth())) {
								myclass = " class='lightgrey'"; //当该日期在今天之前时，以浅灰色字体显示
							} else if (i == my_day
									&& my_year == my_date.getFullYear()
									&& my_month == my_date.getMonth()) {
								myclass = " class='green greenbox'"; //当天日期以绿色背景突出显示
							} else {
								myclass = " class='darkgrey'"; //当该日期在今天之后时，以深灰字体显示
							}
							str += "<li" + myclass + ">" + i + "</li>"; //创建日期节点
						}
						holder.innerHTML = str; //设置日期显示
						ctitle.innerHTML = month_name[my_month]; //设置英文月份显示
						cyear.innerHTML = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
								+ my_year; //设置年份显示
					}
					refreshDate(); //执行该函数

					prev.onclick = function(e) {
						e.preventDefault();
						my_month--;
						if (my_month < 0) {
							my_year--;
							my_month = 11;
						}
						refreshDate();
					}
					next.onclick = function(e) {
						e.preventDefault();
						my_month++;
						if (my_month > 11) {
							my_year++;
							my_month = 0;
						}
						refreshDate();
					}
				</script>
			</div>
		</div>
	</div>


	<!-- footer 放body尾 -->
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>

</body>

</html>