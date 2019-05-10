import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Random;

/**
 * @author changshengee
 * @date 2019/05/10
 **/
@WebServlet(name="BitCoinDataCenter",urlPatterns = "/BitCoinDataCenter",loadOnStartup=1)
public class BitCoinDataCenter extends HttpServlet implements Runnable {

  @Override
  public void init(ServletConfig config) {
    startUp();
  }

  public void startUp() {
    new Thread(this).start();
  }

  @Override
  public void run() {
    int price = 10000;
    while (true) {
      int duration = 1000 + new Random().nextInt(2000);
      try {
        Thread.sleep(duration);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      float random = 1 + (float) (Math.random() - 0.5);
      int newPrice = (int) (price * random);
      int total = ServerManager.getTotal();
      newPrice = newPrice * total;
      String msgFormat = "{\"price\":\"%d\",\"total\":%d}";
      String msg = String.format(msgFormat, newPrice, total);
      ServerManager.broadCast(msg);
    }
  }
}
