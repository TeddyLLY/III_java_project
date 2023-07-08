<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="yangshi.css" type="text/css" />
<script src="newindex.js"></script>
<title>彈幕</title>
<style>
*{
margin: 0;
padding: 0;
}
html,body{

width: 100%;
height: 100%;
}
#main{
width: 100%;
height: 100%;
   
background: -webkit-gradient(linear, 0% 0%, 0% 100%,from(#ADD8E6), to(#f6f6f8));
overflow: hidden;
}
span{

white-space: nowrap;
position:absolute;
}
#mainScreen{
width: 800px;
height: 600px;
margin: 8px auto;
border: 1px solid gray;
background-color: white;
/*隱藏span標籤超出oScreen螢幕範圍的內容*/
overflow: hidden;
position: relative;
}
#bottom{
width: 800px;
height: 32px;
margin: 5px auto;
}
#txt{
width: 240px;
height: 30px;
line-height: 30px;
font-family: 微軟雅黑;
padding-left: 8px;
border: 1px solid lightslategrey;
float: left;
}
#btn{
    width: 60px;
    height: 30px;
    line-height: 30px;
    margin-left: 30px;
    margin-top: 2px;
    border-radius: 4px;
    float: left;
    }
</style>
</head>
<body>
<div id="main">
<div id="mainScreen">
</div>
<div id="bottom">
<input id="txt" type="text" value="say some thing..." />
<input id="btn" type="button" value="Send" />
</div>
</div>
</body>
<script>

window.onload = function(){
var oBtn = document.getElementById("btn");
var oText = document.getElementById("txt");
var oScreen = document.getElementById("mainScreen");
oBtn.onclick = sendMessage;
// 每次點選清空輸入框
oText.onclick = function(){
oText.value = "";
};
    //新增回車提交事件
document.onkeydown = function(evt){
var event = evt || window.event;//相容IE
if(event.keyCode == 13){
sendMessage();
}
};
function sendMessage(){
        //如果輸入為空則彈出對話方塊
if(oText.value.trim() == ""){
alert("請正確輸入");
}
else{
        //如果有輸入則動態建立span並將內容新增到span中，然後再將span新增到mainScreen中
var oDan1 = document.createElement("span");
oDan1.innerText = oText.value;
// 定義隨機字型大小
var oFontSize  = parseInt(Math.random()*16+16);
// 建立隨機顏色
var oFontColor =  '#' Math.floor(Math.random()*16777215).toString(16);
// 隨機高度
var oMax = oScreen.offsetHeight - oFontSize;
var oMin = oScreen.offsetTop;
var oHeight = Math.floor(Math.random() * (oMax-oMin)+oMin);
oDan1.style.color = oFontColor;
oDan1.style.fontSize = oFontSize   "px";
oDan1.style.marginTop = oHeight   "px";
// Move
var variable = 800; //800是mainScreen的寬度，也可寫成：oDan1.offsetLeft
var timer = setInterval(function () {
oDan1.style.marginLeft = variable   "px";
            //如果沒有超出邊界就將span動態新增到oScreen
if (variable > -oDan1.offsetWidth){
variable-=2;
oScreen.appendChild(oDan1);
}
else {
clearInterval(timer);
// 當顯示超出範圍就刪除節點，這裡我之前用display:none不管用
oDan1.parentNode.removeChild(oDan1);
}
}, 1);
}
}
}
</script>
</html>