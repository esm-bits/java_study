package try_with_resources.sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TryWithResourcesSample {

  // Java6以前の書き方
  // finallyでのcloseの呼び出しを書かなければいけなかった
  public void copy(InputStream is, OutputStream os) throws IOException {
    BufferedReader br = null;
    BufferedWriter bw = null;
    
    try {
      br = new BufferedReader(new InputStreamReader(is));
      bw = new BufferedWriter(new OutputStreamWriter(os));
      
      bw.write(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
        throw e;
      } finally {
        try {
          if (bw != null) {
            bw.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
          throw e;
        }
      }
    }
  }

  // try-with-resources文を使った例
  // Closableインターフェイスを実装したクラスのオブジェクトに対しては、finallyでのcloseの呼び出しを明示しなくて良くなった
  public void copy2(InputStream is, OutputStream os) throws IOException {
    try (
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os))
    ) {
      bw.write(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
  }
  
  public static void main(String...args) throws Exception {
    {
      ByteArrayInputStream is = new ByteArrayInputStream("test".getBytes());
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      new TryWithResourcesSample().copy(is, os);
      
      System.out.println(os.toString());
    }

    {
      ByteArrayInputStream is = new ByteArrayInputStream("test".getBytes());
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      new TryWithResourcesSample().copy2(is, os);
      
      System.out.println(os.toString());
    }
  }
}
