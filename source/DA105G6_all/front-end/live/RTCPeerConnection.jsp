<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
	
%>
<jsp:useBean id="coachSvc" scope="page" class="com.coach.model.CoachService"/>
<%-- 模擬登入的coach_no(直播主ID)為peter  --%>
<%
CoachVO coachVO = (CoachVO) session.getAttribute("coachVO");
String coach_no=null;
String coach_name=null;
if(coachVO!=null){
	ServletContext context=getServletContext();
	coach_name=coachVO.getCoach_name();
	coach_no=coachVO.getCoach_no();
	context.setAttribute("room_no",coach_no);
	session.setAttribute("coach_no",coach_no);
	session.setAttribute("room_no",coach_no);
	pageContext.setAttribute("coach_no", coach_no);
	pageContext.setAttribute("coach_name", coach_name);
}
MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");	
MemberService memberSvc=new MemberService();	

%>

<%-- 模擬登入的clientID(觀眾ID)為Anonymous  --%>
<%!int count = 0;%>
<%

   String member_name=null;
	if (memberVO == null)
		member_name = "Anonymous" + (++count);
	else
		member_name = memberVO.getMember_name();
	session.setAttribute("member_name", member_name);
	pageContext.setAttribute("member_name", member_name);
%>

<!DOCTYPE html>
<html>
<head>
<title>小龍女的直播間</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome-4.5.0/css/font-awesome.css">
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/getHTMLMediaElement.css">
<script>
            if(!location.hash.replace('#', '').length) {
                location.href = location.href.split('#')[0] + "#" + "C014";
                location.reload();
            }

        </script>


<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap-4.3.1.min.css">


<script
	src="<%=request.getContextPath()%>/front-end/live/js/jquery-1.11.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/live/js/sweetalert2.all.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/live/js/getHTMLMediaElement.js"></script>
	
<%@ include file="/front-end/gym_index/front-end-head.file"%>

<style>


.flex-column {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
	z-index:1;
}

.flex-row {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}

.warp {
	overflow: hidden;
	width: 70%;
	height: 500px;
	margin: 100px auto;
	padding: 20px;
	background-color: transparent;
	box-shadow: 0 0 9px #222222;
	border-radius: 20px;
}

.wrap .media-box {
	position: relative;
	width: 100%;
	height: 90%;
	background-color: rgba(214, 224, 226, 0.2);
	border: 0px;
}

.wrap .media-box span {
	position: absolute;
	top: 10px;
	left: 20px;
	display: block;
	padding: 10px;
	color: #336688;
}

.wrap .send {
	display: flex;
	width: 120%;
	height: 10%;
	background-color: #000000;
	border-radius: 8px;
}

.wrap .send input {
	width: 40%;
	height: 60%;
	border: 0;
	outline: 0;
	border-radius: 5px 0 0 5px;
	box-shadow: 0 0 5px #d9d9d9;
	text-indent: 1em;
}

.wrwap .send .send-btn {
	width: 100px;
	height: 60%;
	background-color: #fe943b;
	color: #ffffff;
	text-align: center;
	border-radius: 0 5px 5px 0;
	line-height: 30px;
	cursor: pointer;
}

.wrap .send .send-btn:hover {
	background-color: #4cacdc;
}

</style>

<style>
.CustomCard {
	padding-top: 20px;
	margin: 10px 0 20px 0;
	background-color: rgba(214, 224, 226, 0.2);
	border-top-width: 0;
	border-bottom-width: 2px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 15px;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.CustomCard.hoverCustomCard {
	position: relative;
	padding-top: 0;
	overflow: hidden;
	text-align: center;
}

.CustomCard.hoverCustomCard .CustomCardheader {
	background-size: cover;
	height: 85px;
}

.CustomCard.hoverCustomCard .avatar {
	position: relative;
	top: -50px;
	margin-bottom: -50px;
}

.CustomCard.hoverCustomCard .avatar img {
	width: 100px;
	height: 100px;
	max-width: 100px;
	max-height: 100px;
	-webkit-border-radius: 50%;
	-moz-border-radius: 50%;
	border-radius: 50%;
	border: 5px solid rgba(255, 255, 255, 0.5);
}

.CustomCard.hoverCustomCard .info {
	padding: 4px 8px 10px;
}

.CustomCard.hoverCustomCard .info .desc {
	overflow: hidden;
	font-size: 12px;
	line-height: 20px;
	color: #737373;
	text-overflow: ellipsis;
}

.CustomCard.hoverCustomCard .bottom {
	padding: 20px 5px;
	margin-bottom: -6px;
	text-align: center;
}

.btn {
	width: 100px;
	height: 30px;
	line-height: 0px;
}

html {
	background: #f4f9f4;
}

.message-area {
	height: 55%;
	resize: none;
	box-sizing: border-box;
	overflow: auto;
	width: 100%;
	background-color: #D1C7C5;
}

#webSocket-submit {
	background-color: #D1C7C5;
	border-radius: 4px;
}
.media-box{
overflow:hidden; 
height:460px;
z-index:0;

}



/* 開關 */
.switch_demo {
position: relative;
width: 150px;
-webkit-user-select: none;
-moz-user-select: none;
-ms-user-select: none;
}
.switch_demo-checkbox {
display: none;
}
.switch_demo-label {
display: block;
overflow: hidden;
cursor: pointer;
border: 2px solid #999999;
border-radius: 20px;
}
.switch_demo-inner {
display: block;
width: 200%;
margin-left: -100%;
transition: margin 0.3s ease-in 0s;
}
.switch_demo-inner:before,
.switch_demo-inner:after {
display: block;
float: left;
width: 50%;
height: 30px;
padding: 0;
line-height: 30px;
font-size: 14px;
color: white;
font-family: Trebuchet, Arial, sans-serif;
font-weight: bold;
box-sizing: border-box;
}
.switch_demo-inner:before {
content: "開啟彈幕";
padding-left: 10px;
background-color: #34A7C1;
color: #FFFFFF;
text-align: left;

}
.switch_demo-inner:after {
content: "關閉彈幕";
padding-right: 10px;
background-color: #EEEEEE;
color: #999999;
text-align: right;

}
.switch_demo-switch {
display: block;
width: 18px;
margin: 6px;
background: #FFFFFF;
position: absolute;
top: 0;
bottom: 0;
right: 116px;
border: 2px solid #999999;
border-radius: 20px;
transition: all 0.3s ease-in 0s;
}
.switch_demo-checkbox:checked + .switch_demo-label .switch_demo-inner {
margin-left: 0;
}
.switch_demo-checkbox:checked + .switch_demo-label .switch_demo-switch {
right: 0px;
}
.emojioneemoji{
width:20px;
height:auto;
}
</style>

<!-- This Library is used to detect WebRTC features -->
<script
	src="<%=request.getContextPath()%>/front-end/live/js/webrtc/DetectRTC.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/live/js/webrtc/socket.io.js"> </script>
<script
	src="<%=request.getContextPath()%>/front-end/live/js/webrtc/adapter-latest.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/live/js/webrtc/IceServersHandler.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/live/js/webrtc/CodecsHandler.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/live/js/webrtc/RTCPeerConnection-v1.5.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/live/js/webrtc/broadcast.js"></script>




<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mervick-emojionearea-99129f7/dist/emojionearea.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/mervick-emojionearea-99129f7/dist/emojionearea.js"></script>
</head>

<body>

	<%@ include file="/front-end/gym_index/front-end-navbar.file"%>
	<div class="container">
		<div class="row">
			<div class="col col-sm-12">
				<div class="CustomCard hoverCustomCard">
					<div id="CustomCardheader"
						class="CustomCardheader text-white btn-warning">
						<input type="hidden" id="coach_no" value="${coach_no}" /> <input
							type="hidden" id="lsViewNum" value="0" />
						<%
							if (coach_no != null) {
						%>
						<h5 id="subTitle" class="col pt-2">
							<strong>這是小龍女的直播間</strong>
						</h5>
						<%
							} else {
						%>
						<h5 id="subTitle" class="col pt-2">
							<strong>您已經進入直播間</strong>
						</h5>
						<%
							}
						%>

						<i id="WebSocket-count" class="far pt-2 pr-3 float-right"
							style="position: absolute; right: 0; top: 0px"></i> <i
							id="WebRTC-count" class="far pt-2 pr-3 float-right"
							style="position: absolute; right: 0; top: 20px"></i></i>
					</div>


					<div class="bottom mx-auto">
						<button class="btn-3d-can" id="record" style="display: none"
							disabled="disabled">開始錄影</button>
						<button class="btn-3d-can" id="download" style="display: none"
							disabled="disabled">儲存錄影</button>
						<button id="play" class="btn btn-success" style="display: none">播放</button>
						<div style="display: none">
							<p>
								Echo cancellation: <input type="checkbox" id="echoCancellation">
							</p>
							<div id="errorMsg"></div>
						</div>
					</div>
				</div>
			</div>

			<div class="col col-sm-9">
				<article>
					<section id="session1" class="experiment">

						<!-- list of all available broadcasting rooms -->
						<br>

						<!-- local/remote videos container -->
						<div id="videos-container" style="width: 100%;">
							<div class="media-controls" id="media-controls"></div>
							<div class="volume-control" id="volume-control">
								<div class="control stop-recoeding-video selected" id="recoed-video"></div>
							</div>
							<div class="wrap flex-column" >
							<div class="media-box" id="media-box">
								<video autoplay playsinline controls id="video"
									style="height: 460px;width:811px; z-index:-1;border-radius:20px;overflow:hidden;"></video>
							</div>
							</div>
						</div>
						<div class="switch_demo">
<input type="checkbox" name="switch_demo" class="switch_demo-checkbox" id="switch_demo" value="關閉彈幕" >
<label class="switch_demo-label" for="switch_demo">
<span class="switch_demo-inner" id="switch_demo-inner" ></span>
<span class="switch_demo-switch"></span>
</label>
</div>
						<table style="width: 100%;" id="rooms-list"></table>
						

						<div class="visible">
							<div style="text-align: center;">
								<div class="center">
</div>
							</div>
						</div>
						<section>
							<section
								<%=(coach_no == null) ? "style='visibility: hidden;'" : ""%>>
								<select id="broadcasting-option" class="broadcasting-option">
									<option>Audio + Video</option>
									<option>Only Audio</option>
								</select> <input type="hidden" id="broadcast-name" class="broadcast-name"
									value="${coach_no}">
								<button id="setup-new-broadcast">啟動新視頻</button>
							</section>
						</section>
					</section>
				</article>
			</div>
			<script>
			 var visibleElements = document.getElementsByClassName('visible'),
             length = visibleElements.length;
             for (var i = 0; i < length; i++) {
                visibleElements[i].style.display = 'none';
             }
			</script>

			<div class="col col-sm-3" style="height: 590px">
<br>
				<div id="messagesArea" class="message-area" ></div>
				<div class="panel input-area">
					<div id="webSocket-submit" class="g1">
						<input id="emojionearea1" style="width: 50px;" class="text-field"
							type="text" placeholder="訊息"
							onkeydown="if(event.keyCode == 13) sendMessage();" /><br> <input
							id="userName" class="panel input-default" type="text"
							placeholder="暱稱" value="${(coach_name!=null)? coach_name :member_name}"
							readonly="readonly" /> <input type="submit" id="sendMessage"
							class="btn btn-danger" value="送出訊息" onclick="sendMessage();" />
					</div>
				</div>
				</div>
	</div>
			</div>
			<div class="row clearfix">
			<div class="col-1"></div>
            <a href="<%=request.getContextPath() %>/front-end/live/listOneStream.jsp?coach_no=C014"><button style="margin-left:15px;">歷史直播影片</button></a>
            
            
        </div>
    
    <div class="row clearfix">
        <div class="col-sm-1 col" ></div>
        <div class="col-sm-2 col" id="photo">
            <img src="<%=request.getContextPath()%>/CoachPhotoReader?coach_no=C014" width="auto" height="200px;" />
        </div>
        <div class="col-sm-8 col" style="margin-top:-20px;">
                <div  style="width:100%;height:180px;">
               <h2>小龍女</h2></n>
               <p>古墓派第三代掌門人</p>
               <p>容貌秀美若仙、冰肌玉骨，明艷絕倫</p>
               <p>外表柔弱內心堅強，寬容豁達中具有包容性</p> 
               <p>專長:玉女劍法、銀索金鈴索法、天羅地網式、玉女心經</p>
               <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;玉蜂針、左右互搏</p>
                </div>
        </div>
        <div class="col-sm-1 col" ></div>
    </div>
		
	<script>
	
		$("#emojionearea1").emojioneArea({
	  	pickerPosition: "top",
	    tonesStyle: "bullet"
	  });
		
	</script>
	<%@ include file="/front-end/gym_index/front-end-footer.file"%>
</body>

<script>
                var config = {
                    openSocket: function(config) {
//                      var SIGNALING_SERVER = 'https://socketio-over-nodejs2.herokuapp.com:443/';
                        var SIGNALING_SERVER = 'https://54.148.206.111:9001/';

                        config.channel = config.channel || location.href.replace(/\/|:|#|%|\.|\[|\]/g, '');
                        var sender = Math.round(Math.random() * 999999999) + 999999999;
                        io.connect(SIGNALING_SERVER).emit('new-channel', {
                            channel: config.channel,
                            sender: sender
                        });

                        var socket = io.connect(SIGNALING_SERVER + config.channel);
                        socket.channel = config.channel;
                        socket.on('connect', function () {
                            if (config.callback) config.callback(socket);
                        });

                        socket.send = function (message) {
                            socket.emit('message', {
                                sender: sender,
                                data: message
                            });
                        };

                        socket.on('message', config.onmessage);
                    },
                    onRemoteStream: function(htmlElement) {
                    	document.getElementById('video').srcObject=htmlElement.srcObject;
                        rotateInCircle(htmlElement);
                    },
                    onRoomFound: function(room) {
                        document.getElementById("subTitle").innerHTML = "<strong>您正準備觀看 " + room.roomName + " 的直播</strong>";
                        var alreadyExist = document.querySelector('button[data-broadcaster="' + room.broadcaster + '"]');
                        if (alreadyExist) return;

                        if (typeof roomsList === 'undefined') roomsList = document.body;
                        var tr = document.createElement('tr');
                        tr.innerHTML = '<td><strong>' + room.roomName + '</strong> is broadcasting his media!</td>' +
                            '<td><button class="join">加入直播</button></td>';
                        roomsList.appendChild(tr);
 
                        var joinRoomButton = tr.querySelector('.join');
                        joinRoomButton.setAttribute('data-broadcaster', room.broadcaster);
                        
                        joinRoomButton.setAttribute('data-roomToken', room.broadcaster);
                        joinRoomButton.onclick = function() {
//點擊後加入直播	
                        	document.getElementById("CustomCardheader").className = "CustomCardheader text-white btn-success";
                        	document.getElementById("webSocket-submit").className = "g2";
                        	document.getElementById("subTitle").innerHTML = "<strong2>您正在觀看小龍女的直播</strong2>";
                        	document.getElementById('broadcast-name').value = room.roomName;
                        	videosContainer.className = "videosContainer"; 
                        	document.getElementById("WebRTC-count").innerHTML = "";
                            this.disabled = true;
//                          this.style.display = 'none';
                            
                            var broadcaster = this.getAttribute('data-broadcaster');
                            var roomToken = this.getAttribute('data-roomToken');
                            broadcastUI.joinRoom({
                                roomToken: broadcaster,
                                joinUser: roomToken
                            });
                            hideUnnecessaryStuff2();
                        };
                    },
                    onNewParticipant: function(numberOfViewers) {
                        document.title = 'Viewers: ' + numberOfViewers;
                        document.getElementById("WebRTC-count").innerHTML  = "WebRTC 累計觀看人數"+" "+numberOfViewers;
                        document.getElementById("lsViewNum").value  = numberOfViewers;
                    },
                    onReady: function() {
                        console.log('now you can open or join rooms');
                    }
                };
//開始直播 觸發後跳出畫面
                function setupNewBroadcastButtonClickHandler() {
                	document.getElementById("subTitle").innerHTML = "<strong>${coach_no}: 您正在直播</strong>"
                    $('#record').show();
  	                $('#download').show();
                    DetectRTC.load(function() {
                        captureUserMedia(function() {
                            var shared = 'video';
                            if (window.option == 'Only Audio') {
                                shared = 'audio';
                            }
                            if (window.option == 'Screen') {
                                shared = 'screen';
                            }

                            broadcastUI.createRoom({
                                roomName: (document.getElementById('broadcast-name') || { }).value || 'Anonymous',
                                isAudio: shared === 'audio'
                            });
                        });
                        hideUnnecessaryStuff();
                    });
                }

                function captureUserMedia(callback) {
                    var constraints = null;
                    window.option = broadcastingOption ? broadcastingOption.value : '';
                    if (option === 'Only Audio') {
                        constraints = {
                            audio: true,
                            video: false
                        };

                        if(DetectRTC.hasMicrophone !== true) {
                            alert('DetectRTC library is unable to find microphone; maybe you denied microphone access once and it is still denied or maybe microphone device is not attached to your system or another app is using same microphone.');
                        }
                    }
                    if (option === 'Screen') {
                        var video_constraints = {
                            mandatory: {
                                chromeMediaSource: 'screen',
                                zIndex:-1
                            },
                            optional: []
                        };
                        constraints = {
                            audio: false,
                            video: video_constraints
                        };

                        if(DetectRTC.isScreenCapturingSupported !== true) {
                           alert('DetectRTC library is unable to find screen capturing support. You MUST run chrome with command line flag "chrome --enable-usermedia-screen-capturing"');
                        }
                    }

                    if (option != 'Only Audio' && option != 'Screen' && DetectRTC.hasWebcam !== true) {
                        alert('DetectRTC library is unable to find webcam; maybe you denied webcam access once and it is still denied or maybe webcam device is not attached to your system or another app is using same webcam.');
                    }

//                     var htmlElement = document.createElement(option === 'Only Audio' ? 'audio' : 'video');
                       var htmlElement = document.getElementById('video');
                    htmlElement.muted = true;
                    htmlElement.volume = 0;

//                     try {
//                         htmlElement.setAttributeNode(document.createAttribute('autoplay'));
//                         htmlElement.setAttributeNode(document.createAttribute('playsinline'));
//                         htmlElement.setAttributeNode(document.createAttribute('controls'));
//                     } catch (e) {
//                         htmlElement.setAttribute('autoplay', true);
//                         htmlElement.setAttribute('playsinline', true);
//                         htmlElement.setAttribute('controls', true);
//                     }
var mediaElement = getHTMLMediaElement(htmlElement, {
buttons: ['record-video']
});
                    var mediaConfig = {
                        video: htmlElement,
                        onsuccess: function(stream) {
                            config.attachStream = stream;
                            
//                             videosContainer.appendChild(mediaElement);
//                             rotateInCircle(htmlElement);
                            
                            callback && callback();
                        },
                        onerror: function() {
                            if (option === 'Only Audio') alert('unable to get access to your microphone');
                            else if (option === 'Screen') {
                                if (location.protocol === 'http:') alert('Please test this WebRTC experiment on HTTPS.');
                                else alert('Screen capturing is either denied or not supported. Are you enabled flag: "Enable screen capture support in getUserMedia"?');
                            } else alert('unable to get access to your webcam');
                        }
                    };
                    if (constraints) mediaConfig.constraints = constraints;
                    getUserMedia(mediaConfig);
                }

                var broadcastUI = broadcast(config);

                /* UI specific */
                var videosContainer = document.getElementById('videos-container') || document.body;
                var mediaBox = document.getElementById('media-box') ;
                var setupNewBroadcast = document.getElementById('setup-new-broadcast');
                var roomsList = document.getElementById('rooms-list');

                var broadcastingOption = document.getElementById('broadcasting-option');
//點擊後開始直播
                if (setupNewBroadcast) setupNewBroadcast.onclick = setupNewBroadcastButtonClickHandler;

                function hideUnnecessaryStuff() {
                	connect();

                    var visibleElements = document.getElementsByClassName('visible'),
                        length = visibleElements.length;
                    for (var i = 0; i < length; i++) {
                        visibleElements[i].style.display = 'inline';
                    }
                }
                
                function hideUnnecessaryStuff2() {
                	connect();
                }
//旋轉
                function rotateInCircle(video) {
                    video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(0deg)';
                    setTimeout(function() {
                        video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(360deg)';
                    }, 1000);
                    document.querySelector('button#record').disabled = false;
                	document.getElementById("session1").className = "experiment2";
                 }
                
                
                

            </script>

<!-- =============================================以下為錄製、下載、與上傳============================================= -->
<script>
        'use strict';
        const mediaSource = new MediaSource();
        mediaSource.addEventListener('sourceopen', handleSourceOpen, false);
        let mediaRecorder;
        let recordedBlobs;  //錄製成功的Blob
        let sourceBuffer;

        const errorMsgElement = document.querySelector('span#errorMsg'); 
        const recordedVideo = document.querySelector('video#recorded');
        const recordButton = document.querySelector('button#record');
        recordButton.addEventListener('click', () => {
          if (recordButton.textContent === '開始錄影') {
adjustControls();
volumeControl.style.opacity = 1;
recordVideo.className = 'control record-video';
            startRecording();
          } else {
recordVideo.className = 'stop-recording-video selected';
            stopRecording();
            recordButton.textContent = '開始錄影';
volumeControl.style.opacity = 1;
            playButton.disabled = false;
            //downloadButton.disabled = false;
          }
        });
 
        const playButton = document.querySelector('button#play');
        playButton.addEventListener('click', () => {
          const superBuffer = new Blob(recordedBlobs, {type: 'video/webm'});
          recordedVideo.src = null;
          recordedVideo.srcObject = null;
          recordedVideo.src = window.URL.createObjectURL(superBuffer);
          recordedVideo.controls = true;
          recordedVideo.play();
        });

        const downloadButton = document.querySelector('button#download');
        downloadButton.addEventListener('click', () => {
              document.querySelector('button#record').disabled = false;
              document.querySelector('button#download').disabled = true;
              const blob = new Blob(recordedBlobs, {type: 'video/webm'});	 
        	  var xhr = new XMLHttpRequest();
         	  xhr.open('POST', '<%=request.getContextPath()%>/Update_StreamServlet', true);
        	  xhr.onload = function(e) { console.log("loaded"); };
        	  xhr.onreadystatechange = function(){
        	      console.log("state: " + xhr.readyState);
        	  };
        	  // Listen to the upload progress.
        	  xhr.upload.onprogress = function(e) { console.log("uploading..."); };
        	  xhr.setRequestHeader("Content-Type", "video/webm");
        	  xhr.send(blob);
        	  swal(
            		  '你已儲存影片！',
            		  '可以去直播管理 listAllStream.jsp 確認',
            		  'success'
            	  );
volumeControl.style.opacity = 0;
        });

        function handleSourceOpen(event) {
          console.log('MediaSource opened');
          sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"');
          console.log('Source buffer: ', sourceBuffer);
        }

        function handleDataAvailable(event) {
          if (event.data && event.data.size > 0) {
            recordedBlobs.push(event.data);
          }
        }

        function startRecording() {
          recordedBlobs = [];
          let options = {mimeType: 'video/webm;codecs=vp9'};
          if (!MediaRecorder.isTypeSupported(options.mimeType)) {
            console.error(`${options.mimeType} is not Supported`);
            errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
            options = {mimeType: 'video/webm;codecs=vp8'};
            if (!MediaRecorder.isTypeSupported(options.mimeType)) {
              console.error(`${options.mimeType} is not Supported`);
              errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
              options = {mimeType: 'video/webm'};
              if (!MediaRecorder.isTypeSupported(options.mimeType)) {
                console.error(`${options.mimeType} is not Supported`);
                errorMsgElement.innerHTML = `${options.mimeType} is not Supported`;
                options = {mimeType: ''};
              }
            }
          }

          try {
            mediaRecorder = new MediaRecorder(window.stream, options);
          } catch (e) {
            console.error('Exception while creating MediaRecorder:', e);
            errorMsgElement.innerHTML = `Exception while creating MediaRecorder: ${JSON.stringify(e)}`;
            return;
          }

          console.log('Created MediaRecorder', mediaRecorder, 'with options', options);
          recordButton.textContent = '結束錄影';
          playButton.disabled = true;
          downloadButton.disabled = true;
          mediaRecorder.onstop = (event) => {
            console.log('Recorder stopped: ', event);
          };
          mediaRecorder.ondataavailable = handleDataAvailable;
          mediaRecorder.start(10); // collect 10ms of data
          console.log('MediaRecorder started', mediaRecorder);
        }
        function stopRecording() {
          mediaRecorder.stop();
          console.log('Recorded Blobs: ', recordedBlobs);

        	 $.ajax({
        		 type: "POST",
        		 url: "<%=request.getContextPath()%>/InsertOrDelete_StreamServlet",
        		 data: creatQueryString($(this).val(), ""),
        		 dataType: "json",
        		 success: function (data){
        			alert("成功送資料庫囉");
        	     },
                 error: function(){
                	    swal(
                		  '您已完成錄影',
                		  '記得要按儲存影片',
                		  'success'
                		);
                	    downloadButton.disabled = false;
volumeControl.style.opacity = 1;
recordVideo.className = "stop-recording-video2";
                 }
             })
             
             function creatQueryString(paramGrade, paramClass){
                    document.querySelector('button#record').disabled = true;       		 	
        		    var coach_no=$("#coach_no").val();
        		 	var lsViewNum=$("#lsViewNum").val();
        			var queryString= {"action":"insert", "coach_no":coach_no, "lsViewNum":lsViewNum};
        			return queryString;
        	 }

        }

        function handleSuccess(stream) {
          recordButton.disabled = false;
          downloadButton.disabled = true;
          console.log('getUserMedia() got stream:', stream);
          window.stream = stream;

        }

        async function init(constraints) {
            const stream = await navigator.mediaDevices.getUserMedia(constraints);
            handleSuccess(stream);
        }

        document.querySelector('#setup-new-broadcast').addEventListener('click', async () => {
          const hasEchoCancellation = document.querySelector('#echoCancellation').checked;
          const constraints = {
            audio: {
              echoCancellation: {exact: hasEchoCancellation}
            },
            video: {
              width: 1280, height: 720
            }
          };
          console.log('Using media constraints:', constraints);
          await init(constraints);
        });
</script>

<!-- =============================================以下為webSocket聊天室============================================= -->
<script>
    
var MyPoint = "/MyEchoServer";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "wss://" + "da105g6.ml" + webCtx + MyPoint;   <%--http上線請使用https , webSocket請使用wss--%>
var webSocket;

function connect() {
	var rtcroomName = document.getElementById('broadcast-name').value;
	alert(rtcroomName);
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL+"/"+rtcroomName);
	document.getElementById('broadcasting-option').style.display = 'none';
    document.getElementById('broadcast-name').style.display = 'none';
    document.getElementById('setup-new-broadcast').style.display = 'none';
	webSocket.onopen = function(event) {
		document.getElementById('sendMessage').disabled = false;
	};

	webSocket.onmessage = function(event) {
	  if (event.data.indexOf('count=') == 0) {
		document.getElementById("WebSocket-count").innerHTML  = "目前在線人數"+" "+event.data.substring(6);
	  } else {
		
		var messagesArea = document.getElementById("messagesArea");
        var jsonObj = JSON.parse(event.data);
        var message = jsonObj.userName + ": " + jsonObj.message + "<br>";
        var showCount = jsonObj.showCount;
        messagesArea.innerHTML = messagesArea.innerHTML + message;
        messagesArea.scrollTop = messagesArea.scrollHeight;
       
        
        
        
        
        var Barrage=document.getElementById("switch_demo");
       if(Barrage.value=="開啟彈幕"){
        var oBox=document.querySelector('.media-box');
        var cW=oBox.offsetWidth;//取得BOX的寬度
        var cH=oBox.offsetHeight;//取得BOX的高度
        
        

        var oMessage = document.createElement("span");
        oMessage.innerHTML = jsonObj.message;
        // 定義隨機字型大小
        var oFontSize  = parseInt(Math.random()*16+16);
        // 建立隨機顏色
        var oFontColor = '#'+(~~(Math.random()*(1<<24))).toString(16);
        // 隨機高度
        var oMax = oBox.offsetHeight - oFontSize;
        var oMin = oBox.offsetTop;
        var oHeight = Math.floor(Math.random() * (oMax-oMin));
        oMessage.style.color = oFontColor;
        oMessage.style.fontSize = oFontSize+"px";
        oMessage.style.marginTop = oHeight+"px";
        // Move
        var variable = oBox.offsetWidth; //800是mainScreen的寬度，也可寫成：oDan1.offsetLeft
        var timer = setInterval(function () {
        	oMessage.style.marginLeft = variable+"px";
                    //如果沒有超出邊界就將span動態新增到oScreen
        if (variable > -oMessage.offsetWidth){
        variable-=2;
        oBox.appendChild(oMessage);
        }
        else {
        clearInterval(timer);
        // 當顯示超出範圍就刪除節點，這裡我之前用display:none不管用
        oMessage.parentNode.removeChild(oMessage);
        }
        }, 1);
        }
        }
	}
	  
	
	
	webSocket.onclose = function(event) {
		var coach_no = document.getElementById("messagesArea");
	     var jsonObj = {"coach_no" : userName, "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	};
}


function sendMessage() {
var inputMessage =  document.getElementsByClassName("emojionearea-editor")[0];
    var message = inputMessage.innerHTML.trim();
    var inputUserName = document.getElementById("userName");
    var userName=inputUserName.value.trim();

    if (message === ""){
        alert ("訊息請勿空白!");
        inputMessage.focus();	
    }else{
    	var jsonObj = { "message" : message,"userName":userName};
        webSocket.send(JSON.stringify(jsonObj));
        document.getElementsByClassName("emojionearea-editor")[0].innerHTML = "";
        document.getElementById("emojionearea1").innerText="";
        inputMessage.focus();  
    }
}
var Barrage=document.getElementById("switch_demo");
$("#switch_demo").click(function(){
	if(Barrage.value=="關閉彈幕"){
		Barrage.value="開啟彈幕"
	}else{
		Barrage.value="關閉彈幕"
	}
})




</script>
</html>
