var webSocket = new WebSocket("ws://localhost:8080/chat.jsp");

function send(headSrc, str) {
    var html="<div class='send'><div class='msg'><img src="+headSrc+" />"+
        "<p class='msg_input'>"+str+"</p></div></div>";
    upView(html);
}
/*接受消息*/
function show(headSrc,str){
    var html="<div class='show'><div class='msg'><img src="+headSrc+" />"+
        "<p class='msg_input'>"+str+"</p></div></div>";
    upView(html);
}
/*更新视图*/
function upView(html){
	$('.message').append(html);
    var myDiv =document.getElementById('myDiv');
    myDiv.scrollTop = myDiv.scrollHeight;
}
function sj(){
	return parseInt(Math.random()*10)
}
/*接受消息*/
webSocket.onopen = function () {
    document.getElementById('shu').onkeydown = function(event) {
        if (event.keyCode == 13) {
            var message = $(this).val();
            webSocket.send(message+"#"+$("#touser").html());
            show("images/"+$("#pic").html()+".png",message);
            $(this).val("");
        }
    };
	webSocket.send("$$$#"+$("#user").html()+"#"+$("#pic").html());
    webSocket.onmessage = function (event) {
    	var pic = event.data.split("#");
        send("/images/"+pic[1]+".png",pic[0]);
    };
}
$(function(){

	$('.footer').on('keyup','input',function(){
		if($(this).val().length>0){
			$(this).next().css('background','#114F8E').prop('disabled',true);
		
		}else{
			$(this).next().css('background','#ddd').prop('disabled',false);
		}
	})
	$('.footer p').click(function(){
		var message = $(this).prev().val();
		webSocket.send(message+"#"+$("#touser").html());
		show("images/"+$("#pic").html()+".png",message);
		$(this).prev().val("");
	})
    $(".back").click(function(){
        $(".bian").hide();
    });
    $("#chat").click(function(){
        $(".bian").show();
    });
})
