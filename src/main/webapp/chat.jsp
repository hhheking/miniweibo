<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>聊天界面</title>
    <link rel="stylesheet" type="text/css" href="chat.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/flexible.js"></script>
</head>
<body>
<div class="bian">
    <header class="header">
        <a class="back" href="javascript:history.back()"></a>
        <h5 class="tit"id="touser"><%=request.getParameter("toname")%></h5>
        <h5 id="user" style="display: none"><%=request.getParameter("fromname")%></h5>
        <h5 id="picture" style="display: none"><%=request.getParameter("picture")%></h5>
        <div class="right">资料</div>
    </header>
    <div class="message">
    </div>
    <div class="footer">
        <img src="images/hua.png" alt="" />
        <input id="shu" type="text"/>
        <p>发送</p>
    </div>
</div>
<script src="js/chat.js" type="text/javascript" charset="utf-8"></script>
</body>


