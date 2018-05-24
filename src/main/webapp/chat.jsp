<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>聊天界面</title>
    <link rel="stylesheet" type="text/css" href="css/chat.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/flexible.js"></script>
    <script src="js/chat.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<input type="button" id="chat" value="发起聊天">
<div class="bian">
    <header class="header">
        <a class="back"></a>
        <h5 class="tit"id="touser"><s:property value="toname"/></h5>
        <span id="user" style="display: none"><s:property value="fromname"/></span>
        <span id="pic" style="display: none"><s:property value="picture"/></span>
        <div class="right">资料</div>
    </header>
    <div class="message" id="myDiv">
    </div>
    <div class="footer">
        <img src="images/hua.png" alt="" />
        <input id="shu" type="text"/>
        <p>发送</p>
    </div>
</div>
</body>


