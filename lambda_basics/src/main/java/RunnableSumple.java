import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// ラムダ式でRunnnableを書いて並列実行する例
// 実際に動かして出力をみて、どのように動いているか考えてみて下さい
public class RunnableSumple {
  public void sleepSort() throws InterruptedException {
    List<Runnable> runnables = new ArrayList<>();
    for (int i = 10 ; i > 0 ; i--) {
      final long sleep_time = i;
      
      runnables.add(() -> { try { TimeUnit.SECONDS.sleep(sleep_time); System.out.println(sleep_time); } catch (Exception e) { e.printStackTrace(); } });
    }
    
    List<Thread> threads = new ArrayList<>();
    for (Runnable runnable : runnables) {
      Thread thread = new Thread(runnable);
      thread.start();
      threads.add(thread);
    }
    
    for (Thread thread : threads) {
      thread.join();
    }
  }
  
  public static void main(String...arg) throws InterruptedException {
    new RunnableSumple().sleepSort();
  }
}
