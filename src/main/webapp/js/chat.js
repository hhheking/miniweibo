var webSocket = WebSocket("ws://localhost:8080/chat.jsp/"+$("#user").html()+"/"+$("#picture").html());

function send(headSrc, str) {
	var html = "<div class='send'><div class='msg'><img src=" + headSrc + " />" +
		"<p><i class='msg_input'></i>" + str + "</p></div></div>";
	upView(html);
}
/*接受消息*/
function show(headSrc,str){
	var html="<div class='show'><div class='msg'><img src="+headSrc+" />"+
	"<p><i class='msg_input'></i>"+str+"</p></div></div>";
	upView(html);
}
/*更新视图*/
function upView(html){
	$('.message').append(html);
	$('body,html').animate({scrollTop:$('.message').outerHeight()-window.innerHeight},200)
}
function sj(){
	return parseInt(Math.random()*10)
}
/*接受消息*/
webSocket.onopen = function () {
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
		webSocket.send(message + "#"+ $('#touser').html());
		show("images/"+$("#picture").html()+".png",message);
		$(this).prev().val("");
	})
})
