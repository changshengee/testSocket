

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author changshengee
 * @date 2019/05/10
 **/
@ServerEndpoint("/ws/bitCoinServer")
public class BitCoinServer {
  private Session session;

  @OnOpen
  public void open(Session session) {
    this.session = session;
    ServerManager.add(this);
  }

  public void sendMessage(String mes) throws IOException {
    this.session.getBasicRemote().sendText(mes);
  }

  @OnClose
  public void close() {
    ServerManager.remove(this);
  }

  @OnMessage
  public void message(String mes, Session session) {
    System.out.println("来自客户端的消息:" + mes);
  }

  @OnError
  public void error(Session session, Throwable err) {
    System.out.println("发生错误");
    err.printStackTrace();

  }
}
