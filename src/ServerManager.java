import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author changshengee
 * @date 2019/05/10
 **/
public class ServerManager {
  private static Collection<BitCoinServer> servers = Collections.synchronizedCollection(new ArrayList<>());

  public static void broadCast(String msg) {
    for (BitCoinServer server : servers) {
      try {
        server.sendMessage(msg);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static int getTotal() {
    return servers.size();
  }

  public static void add(BitCoinServer server) {
    servers.add(server);
    System.out.println("有新链接加入，当前总连接数是：" + servers.size());
  }

  public static void remove(BitCoinServer server) {
    servers.remove(server);
    System.out.println("有连接退出，当前总连接数是 ：" + servers.size());
  }
}

