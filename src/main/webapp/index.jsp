<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Title</title>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>聊天界面</title>
    <link rel="stylesheet" type="text/css" href="chat.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/flexible.js"></script>
</head>
<body>
<input type="button" id="send" value="发送消息" onclick="opendiv()">
<div id="frind" class="frind">
    <header class="header">
        <a class="back" href="javascript:history.back()"></a>
        <h5 class="tit">追星星的人</h5>
        <div class="right">资料</div>
    </header>
    <div class="message">

        <div class="send">
            <div class="time">05/22 06:30</div>
            <div class="msg">
                <img src="images/touxiang.png" alt="" />
                <p><i class="msg_input"></i>你好在不在呀，妹子！</p>
            </div>
        </div>
        <div class="show">
            <div class="time">05/22 06:30</div>
            <div class="msg">
                <img src="images/touxiangm.png" alt="" />
                <p><i clas="msg_input"></i>你好你好你好</p>
            </div>
        </div>
    </div>
    <div class="footer">
        <img src="images/hua.png" alt="" />
        <img src="images/xiaolian.png" alt="" />
        <input type="text"  />
        <p>发送</p>
    </div>
    <script src="js/chat.js" type="text/javascript" charset="utf-8"></script>
</div>
</body>
</html>