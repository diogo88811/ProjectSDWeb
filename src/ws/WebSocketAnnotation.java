package ws;

import hey.configurator.getHttpConfig;
import hey.model.ServerRmiBean;
import rmiserver.InterfaceServerRMI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws", configurator = getHttpConfig.class)
public class WebSocketAnnotation {
    private static final AtomicInteger sequence = new AtomicInteger(1);
    private String username;
    private Session session;
    private HttpSession httpSession;
    private ServerRmiBean server;
    private static final Set<WebSocketAnnotation> users = new CopyOnWriteArraySet<>();

    public WebSocketAnnotation() {
        username = "User" + sequence.getAndIncrement();
    }

    @OnOpen
    public void start(Session session, EndpointConfig config) throws RemoteException {
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        server = (ServerRmiBean) this.httpSession.getAttribute("heyBean");
        users.add(this);
        username = server.getUsername();
        String message = "*" + server.getUsername() + "* Logged In.";
        System.out.println(server.getUsername() + "Encontra-se na eleicao: "+server.getElectionSelectedToVote());
        if(!server.getIsAdmin()){
            if(server.getElectionSelectedToVote() != null){
                sendMessage(server.getUsername() + "Encontra-se na eleicao: "+server.getElectionSelectedToVote());
                sendMessage(server.realTimeData());
            }
            else{
                sendMessage(message);
            }
        }


    }

    @OnClose
    public void end() {
        // clean up once the WebSocket connection is closed
        users.remove(this);
    }

    @OnMessage
    public void receiveMessage(String message) {
        // one should never trust the client, and sensitive HTML
        // characters should be replaced with &lt; &gt; &quot; &amp;
        String upperCaseMessage = message.toUpperCase();
        sendMessage("[" + username + "] " + upperCaseMessage);
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

    private void sendMessage(String text) {
        // uses *this* object's session to call sendText()
        try {
            for(WebSocketAnnotation ws :users){
                synchronized (ws){
                    ws.session.getBasicRemote().sendText(text);
                }
            }
            //this.session.getBasicRemote().sendText(text);
        } catch (IOException e) {
            // clean up once the WebSocket connection is closed
            users.remove(this);
            try {
                this.session.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
