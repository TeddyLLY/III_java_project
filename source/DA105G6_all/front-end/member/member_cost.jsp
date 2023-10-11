<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.top_up_record.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.toolclass.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
<%@ include file="/front-end/gym_index/front-end-bs-new-version.file"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/member/card/card.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-end/member/member_center_style.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>儲值紀錄</title>
</head>

<body>
	<!-- nav_bar 放body頭 -->
	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>

	<div class="container">
		<div class="col-md-12">
			<div class="row">
				<!-- member center bar -->
				<%@ include file="/front-end/member/member_bar.file"%>
<%
	TopUpRecordVO topUpRecordVO = new TopUpRecordVO();
	TopUpRecordService topUpRecordSvc = new TopUpRecordService();

	List<TopUpRecordVO> list = topUpRecordSvc.findOneMemberRecord(new String(memberVO.getMember_no()));
	pageContext.setAttribute("list", list);
%>

				<div class="col-md-8">
					<br>
       	<%-- 錯誤表列 --%>
								<c:choose>
									<c:when test="${! empty errorMsgs }">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="errorMsgs" items="${errorMsgs}">
												<li style="color: red">${errorMsgs}</li>
											</c:forEach>
										</ul>
									</c:when>
									<c:otherwise>
										<h4 style="color: red; margin: center;">注意!! 您將儲值至此帳戶</h4>
									</c:otherwise>
								</c:choose>
								
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item active" aria-current="page">儲值紀錄</li>
						</ol>
					</nav>
					<div class="card text-center">
						<div class="card-header">Available points</div>
						<div class="card-body">
							<h4 class="card-title" style="color: green;">${memberVO.member_points }</h4>
							<p class="card-text">可用點數</p>
						</div>
						<div class="card-footer text-muted">1分鐘前更新</div>
					</div>
				
				<br> <br>

<%@ include file="page1.file"%>
<br> <br>

<ul class="list-unstyled">
<c:forEach var="topUpRecordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

			<c:set value="${topUpRecordVO.record_time} " var="dateString" />
			<fmt:parseDate value="${dateString}" var="dateObject"
				pattern="yyyy-MM-dd HH:mm:ss" />
				
						
							<li class="media "><img
								src="<%=request.getContextPath()%>/tool/images/5934c388dc7cf.png"
								class="mr-3" alt="..." style="width: 100px; height: 100px;">
								<div class="media-body">
									<p style="color: gray;"> 會員編號:${memberVO.member_no }<p>
									<h4 class="mt-0 mb-1">
										<b style="color: #077CB9;">+${topUpRecordVO.money_record }</b>
									</h4>
									<p style="color: gray; font-size:1px;">卡號 : ${ memberVO.member_card } , 付款時間 : ${dateObject}</p>
								</div></li>
</c:forEach>
</ul>
				<%@ include file="page2.file"%>	
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong1">
						 我要儲值
						</button>
			</div>
		</div>
	</div>
		<!-- footer 放body尾 -->
</div>
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>
	

</body>


	<!-- Modal -->
<div class="modal fade" id="exampleModalLong1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
								
						  <div class="checkout">
        <div class="credit-card-box">
            <div class="flip">
                <div class="front">
                    <div class="chip"></div>
                    <div class="logo">
                        <g>
                            <g>
                                <path d="M44.688,16.814h-3.004c-0.933,0-1.627,0.254-2.037,1.184l-5.773,13.074h4.083c0,0,0.666-1.758,0.817-2.143
                           c0.447,0,4.414,0.006,4.979,0.006c0.116,0.498,0.474,2.137,0.474,2.137h3.607L44.688,16.814z M39.893,26.01
                           c0.32-0.819,1.549-3.987,1.549-3.987c-0.021,0.039,0.317-0.825,0.518-1.362l0.262,1.23c0,0,0.745,3.406,0.901,4.119H39.893z
                           M34.146,26.404c-0.028,2.963-2.684,4.875-6.771,4.875c-1.743-0.018-3.422-0.361-4.332-0.76l0.547-3.193l0.501,0.228
                           c1.277,0.532,2.104,0.747,3.661,0.747c1.117,0,2.313-0.438,2.325-1.393c0.007-0.625-0.501-1.07-2.016-1.77
                           c-1.476-0.683-3.43-1.827-3.405-3.876c0.021-2.773,2.729-4.708,6.571-4.708c1.506,0,2.713,0.31,3.483,0.599l-0.526,3.092
                           l-0.351-0.165c-0.716-0.288-1.638-0.566-2.91-0.546c-1.522,0-2.228,0.634-2.228,1.227c-0.008,0.668,0.824,1.108,2.184,1.77
                           C33.126,23.546,34.163,24.783,34.146,26.404z M0,16.962l0.05-0.286h6.028c0.813,0.031,1.468,0.29,1.694,1.159l1.311,6.304
                           C7.795,20.842,4.691,18.099,0,16.962z M17.581,16.812l-6.123,14.239l-4.114,0.007L3.862,19.161
                           c2.503,1.602,4.635,4.144,5.386,5.914l0.406,1.469l3.808-9.729L17.581,16.812L17.581,16.812z M19.153,16.8h3.89L20.61,31.066
                           h-3.888L19.153,16.8z" />
                            </g>
                        </g>
                        </svg>
                    </div>
                    <div class="number"></div>
                    <div class="card-holder">
                        <label>Card holder</label>
                        <div></div>
                    </div>
                    <div class="card-expiration-date">
                        <label>Expires</label>
                        <div></div>
                    </div>
                </div>
                <div class="back">
                    <div class="strip"></div>
                    <div class="logo">
                        <svg version="1.1" id="visa" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="47.834px" height="47.834px" viewBox="0 0 47.834 47.834" style="enable-background:new 0 0 47.834 47.834;">
                        <g>
                            <g>
                                <path d="M44.688,16.814h-3.004c-0.933,0-1.627,0.254-2.037,1.184l-5.773,13.074h4.083c0,0,0.666-1.758,0.817-2.143
                           c0.447,0,4.414,0.006,4.979,0.006c0.116,0.498,0.474,2.137,0.474,2.137h3.607L44.688,16.814z M39.893,26.01
                           c0.32-0.819,1.549-3.987,1.549-3.987c-0.021,0.039,0.317-0.825,0.518-1.362l0.262,1.23c0,0,0.745,3.406,0.901,4.119H39.893z
                           M34.146,26.404c-0.028,2.963-2.684,4.875-6.771,4.875c-1.743-0.018-3.422-0.361-4.332-0.76l0.547-3.193l0.501,0.228
                           c1.277,0.532,2.104,0.747,3.661,0.747c1.117,0,2.313-0.438,2.325-1.393c0.007-0.625-0.501-1.07-2.016-1.77
                           c-1.476-0.683-3.43-1.827-3.405-3.876c0.021-2.773,2.729-4.708,6.571-4.708c1.506,0,2.713,0.31,3.483,0.599l-0.526,3.092
                           l-0.351-0.165c-0.716-0.288-1.638-0.566-2.91-0.546c-1.522,0-2.228,0.634-2.228,1.227c-0.008,0.668,0.824,1.108,2.184,1.77
                           C33.126,23.546,34.163,24.783,34.146,26.404z M0,16.962l0.05-0.286h6.028c0.813,0.031,1.468,0.29,1.694,1.159l1.311,6.304
                           C7.795,20.842,4.691,18.099,0,16.962z M17.581,16.812l-6.123,14.239l-4.114,0.007L3.862,19.161
                           c2.503,1.602,4.635,4.144,5.386,5.914l0.406,1.469l3.808-9.729L17.581,16.812L17.581,16.812z M19.153,16.8h3.89L20.61,31.066
                           h-3.888L19.153,16.8z" />
                            </g>
                        </g>
                        </svg>

                    </div>
                    <div class="ccv">
                        <label>CCV</label>
                        <div></div>
                    </div>
                </div>
            </div>
        </div>
        <form class="form" autocomplete="off" novalidate METHOD="post" ACTION="<%=request.getContextPath()%>/topUpRecord.do" >
            <fieldset>
                <label for="card-number">信用卡卡號</label>
                <input type="num" id="card-number" class="input-cart-number" maxlength="4"  placeholder=""/>
                <input type="num" id="card-number-1" class="input-cart-number" maxlength="4" placeholder=""/>
                <input type="num" id="card-number-2" class="input-cart-number" maxlength="4" placeholder=""/>
                <input type="num" id="card-number-3" class="input-cart-number" maxlength="4" placeholder=""/>
            </fieldset>
            <fieldset>
                <label for="card-holder">持卡人姓名</label>
                <input type="text" id="card-holder" placeholder="name" value=""/>
            </fieldset>
            <fieldset class="fieldset-expiration">
                <label for="card-expiration-month">到期日</label>
                <div class="select">
                    <select id="card-expiration-month">
            <option></option>
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
            <option>07</option>
            <option>08</option>
            <option>09</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
          </select>
                </div>
                <div class="select">
                    <select id="card-expiration-year">
            <option></option>
            <option>2016</option>
            <option>2017</option>
            <option>2018</option>
            <option>2019</option>
            <option>2020</option>
            <option>2021</option>
            <option>2022</option>
            <option>2023</option>
            <option>2024</option>
            <option>2025</option>
          </select>
                </div>
            </fieldset>
            <fieldset class="fieldset-ccv">
                <label for="card-ccv">安全碼</label>
                <input type="text" id="card-ccv" maxlength="3" placeholder="ex : 123"/>
            </fieldset>
										<label for="exampleInputEmail1"><br>請輸入儲值金額</label> 
										<small id="emailHelp" class="form-text text-muted">Charge</small>
				 							
				 							<input type="number" class="form-control" id="money_record"
											aria-describedby="emailHelp" placeholder="請輸入整數" 
											name="money_record" value="">
											
						<input type="hidden" name="member_no" value=${memberVO.member_no }> 					
						<input type="hidden" name="action" value="insertOneTopUpRecord"> 
           				 <button class="btn" type="submit" id="subit"><i class="fa fa-lock"></i> 送出</button>
           				 <input type="button" class="magic btn" value="神奇小按鈕">
        </form>
    </div>		
      </div>
    </div>
  </div>
</div>


	
<script>
    $('.input-cart-number').on('keyup change', function() {
        $t = $(this);

        if ($t.val().length > 3) {
            $t.next().focus();
        }

        var card_number = '';
        $('.input-cart-number').each(function() {
            card_number += $(this).val() + ' ';
            if ($(this).val().length == 4) {
                $(this).next().focus();
            }
        })

        $('.credit-card-box .number').html(card_number);
    });

    $('#card-holder').on('keyup change', function() {
        $t = $(this);
        $('.credit-card-box .card-holder div').html($t.val());
    });

    $('#card-holder').on('keyup change', function() {
        $t = $(this);
        $('.credit-card-box .card-holder div').html($t.val());
    });

    $('#card-expiration-month, #card-expiration-year').change(function() {
        m = $('#card-expiration-month option').index($('#card-expiration-month option:selected'));
        m = (m < 10) ? '0' + m : m;
        y = $('#card-expiration-year').val().substr(2, 2);
        $('.card-expiration-date div').html(m + '/' + y);
    })

    $('#card-ccv').on('focus', function() {
        $('.credit-card-box').addClass('hover');
    }).on('blur', function() {
        $('.credit-card-box').removeClass('hover');
    }).on('keyup change', function() {
        $('.ccv div').html($(this).val());
    });

    setTimeout(function() {
        $('#card-ccv').focus().delay(1000).queue(function() {
            $(this).blur().dequeue();
        });
    }, 500);
</script>

<script>
	$(".magic").click(function(){
    	$("#money_record").val('99999');
    	$("#card-ccv").val('258');
    	$("#card-expiration-year").val('2022');
    	$("#card-expiration-month").val('12');
    	$("#card-holder").val('${memberVO.member_name }');
    	$("#card-number").val('5896');
    	$("#card-number-1").val('7878');
    	$("#card-number-2").val('0123');
    	$("#card-number-3").val('6480');
    })
</script>	

<script>
	document.getElementById("subit").addEventListener("click",function(){
		swal("成功！", "已儲值至您的帳戶！")
		});
</script>

</html>