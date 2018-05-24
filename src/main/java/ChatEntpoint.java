import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat.jsp")
public class ChatEntpoint {
    private String user;
    private static final Set<ChatEntpoint> clientSet = new CopyOnWriteArraySet<ChatEntpoint>();
    private Session session;
    private String picture;

    @OnOpen
    public void start(Session session){
        this.session = session;
        clientSet.add(this);
    }

    @OnClose
    public void end(){
        clientSet.remove(this);
    }

    @OnMessage
    public void incoming(String message) throws IOException {
        if(message.startsWith("$$$#")) {
            String s[] = message.split("#");
            this.user = s[1];
            this.picture = s[2];
        }
        else {
            String s[] = message.split("#");
            for (ChatEntpoint client : clientSet) {
                if (client.user.equals(s[1]))
                    synchronized (client) {
                        client.session.getBasicRemote().sendText(s[0] + "#" + picture);
                    }
            }
        }

    }

    @OnError
    public void onError(Throwable t) throws Throwable{
    }
}
