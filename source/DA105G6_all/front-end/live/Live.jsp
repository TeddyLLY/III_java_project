<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+TC|Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/68f8681dba.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/style.css">
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<link rel="stylesheet" href="<%= request.getContextPath()%>/mervick-emojionearea-99129f7/dist/emojionearea.min.css">
<script type="text/javascript" src="<%= request.getContextPath()%>/mervick-emojionearea-99129f7/dist/emojionearea.min.js"></script>
    <title>WorkoutAnywhere</title>
    <style type="text/css">
    #navbar {

        padding-top: 30px;
    }

    #btn {
        width: 150px;
    }

    #live {
        margin: 0px;
        padding: 0px;
        height: 80%;
        width: 100%;
        border: 1px solid;
    }

    #messagesArea {
        height: 80%;
        width: 100%;
        border: 1px solid;
    }

    #chatnav {
        height: 5%;
        border: 1px solid;
    }

    #chat {
        margin: 0px;
        padding: 0px;
        height: 15%;
        width: 100%;

    }

    #title {
        width: 100%;
        height: 20%;
        text-align: left;
        font-size: 50px;
        margin: 0px;
        padding: 0px;
    }

    #chatsubmit {
        width: 100%;
        height: 100%;
        margin: 0px;
        padding: 0px;

    }

    #Chat {

        margin: 0px;
        width: 0px;
    }

    #emojionearea1 {
        margin: 0px;
        padding: 0px;

        width: 100%;
        height: 100%;

    }
    #sbm{
    	width: 100%;
    	height: 100%;
    }

    
    </style>
    


<script src="js/jquery.min.js"></script>
<!-- grid-slider -->
<script type="text/javascript" src="<%= request.getContextPath()%>/front-end/gym_index/js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/front-end/gym_index/js/jquery.contentcarousel.js"></script>
</head>
<%@ include file="/front-end/gym_index/front-end-head.file"%>
 <body onload="connect();" onunload="disconnect();">
<%@ include file="/front-end/gym_index/front-end-navbar.file"%>
    <div class="container" >
        <div class="row clearfix">
            <div class="col-md-1 column">
            </div>
           
        </div>
        <div class="col-md-2 column">
        </div>
        <div class="row">
            <div class="col-md-2 column">
                
            </div>
            <div class="col-5">
            </div>
            <div>
                <i class='far fa-eye' id="count" style='font-size:24px text-align: right;'>0</i>
            </div>
            <div>
                <p>&hearts; 666<p>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-9 column">
            <div class="" id="live">
                <video id="live" src="movie.mp4" loop autoplay muted controls></video>
            </div>
            <div class="jumbotron" id="title">
                TITLE
            </div>
        </div>
        <div class="col-md-3 column" id="Chat">
           <textarea id="messagesArea" class="panel message-area" readonly ></textarea>
            
            <div class="row" id="chat">
                <div class="col-md-10" id="chatcontent">
                    <input id="emojionearea1" style="width:50px;" class="text-field" type="text" placeholder="訊息" onkeydown="if(event.keyCode == 13) sendMessage();"/>
                </div>
                <div class="col-md-2 " id="chatsubmit">
                	<input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
                    
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div id="videonav">
            <button class="btn btn-default" type="button" ><em class="glyphicon glyphicon-align-left"></em>教練資訊</button>
            <button class="btn btn-default" type="button"><em class="glyphicon glyphicon-align-left"></em>歷史直播影片</button>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-3 column" id="photo">
            <img src="凱鈞.png" width="100%" height="100%" />
        </div>
        <div class="col-md-9 column">
            <h2>
                he
            </h2>
            <p>
                Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
            </p>
            <p>
                <a class="btn" href="#">View details »</a>
            </p>
        </div>
    </div>
    <%@ include file="/front-end/gym_index/front-end-footer.file"%>
</body>
<script>
    
    var MyPoint = "/MyEchoServer/peter/309";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
//  var endPointURL = "ws://localhost:8081/IBM_WebSocket1_ChatB/MyEchoServer/peter/309";
	var count=document.getElementById('count');
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			
			
			
			
			var name=prompt("請輸入暱稱");
			var jsonObj = {
					"userName" : name,
					"message" : "已加入直播間",
					"count"   : count.innerText
				};
			webSocket.send(JSON.stringify(jsonObj));
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        messagesArea.value = messagesArea.value + message;
	        messagesArea.scrollTop = messagesArea.scrollHeight;
	        count.innerText=jsonObj.count;
	        
		};

		webSocket.onclose = function(event) {
			
		};
	}
	
	
	
	
	function sendMessage() {
	    var inputMessage = document.getElementById("emojionearea1");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = { "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        document.getElementsByClassName("emojionearea-editor")[0].innerText = "";
	        document.getElementById("emojionearea1").innerText="";
	        inputMessage.focus();  
	    }
	    
	    
	}
	
	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
	
	

	$(document).ready(function() {
	$("#emojionearea1").emojioneArea({
  	pickerPosition: "top",
    tonesStyle: "bullet"
  });
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</script>
</html>