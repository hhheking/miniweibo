import sun.misc.MessageUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/chat.html")
public class ChatEntpoint {
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatEntpoint> clientSet = new CopyOnWriteArraySet<ChatEntpoint>();
    private final String nickname;
    private Session session;

    public ChatEntpoint(){
        nickname=""+connectionIds.getAndIncrement();
    }

    @OnOpen
    public void start(Session session){
        this.session = session;
        clientSet.add(this);
        String message = String.format("[%s %s]", nickname, "加入了聊天室");
        broadcast(message);
    }

    @OnClose
    public void end(){
        clientSet.remove(this);
        String message = String.format("[%s %s]", nickname, "离开了聊天室");
        broadcast(message);
    }

    @OnMessage
    public void incoming(String message, @PathParam("userid")String user) throws IOException {
        String filteredMessage = String.format("%s: %s", nickname, filter(message));
        for(ChatEntpoint client : clientSet){
            if(client.nickname.equals(user))
            synchronized (client){
                client.session.getBasicRemote().sendText(filteredMessage+" "+user);
            }
        }
    }

    @OnError
    public void onError(Throwable t) throws Throwable{
        System.out.println("WebSocket服务器错误" + t);
    }

    private static void broadcast(String msg){
        for(ChatEntpoint client : clientSet){
            try{
                synchronized (client){
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                System.out.println("聊天错误，向客户端" + client + "发送错误出现消息。");
                clientSet.remove(client);
                try{
                    client.session.close();
                }
                catch (IOException e1){}
                String message = String.format("[%s %s]", client.nickname, "已经断开连接。");
                broadcast(message);
            }
        }
    }

    private static String filter(String message){
        if(message == null)
            return null;
        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for(int i = 0; i < content.length; i++){
            switch (content[i]){
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return (result.toString());
    }
}
