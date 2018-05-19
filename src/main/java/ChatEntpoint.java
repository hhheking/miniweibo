import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat.jsp/{touser}/{picture}")
public class ChatEntpoint {
    private String user;
    private static final Set<ChatEntpoint> clientSet = new CopyOnWriteArraySet<ChatEntpoint>();
    private Session session;
    private String picture;

    @OnOpen
    public void start(Session session,@PathParam("touser")String user,@PathParam("picture")String picture){
        this.session = session;
        clientSet.add(this);
        this.user = user;
        this.picture = picture;
        System.out.print(user+" "+picture);
    }

    @OnClose
    public void end(){
        clientSet.remove(this);
    }

    @OnMessage
    public void incoming(String message) throws IOException {
        String s[] = message.split("#");
        for(ChatEntpoint client : clientSet){
            if(client.user.equals(s[1]))
            synchronized (client){
                client.session.getBasicRemote().sendText(s[0]+"#"+picture);
            }
        }
    }

    @OnError
    public void onError(Throwable t) throws Throwable{
        System.out.println("WebSocket服务器错误" + t);
    }
}
