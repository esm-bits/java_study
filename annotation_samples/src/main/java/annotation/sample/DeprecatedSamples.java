package annotation.sample;

// @Deprecated はメソッドが非推奨であることを表すためのアノテーション
public class DeprecatedSamples {
  private class AddUtil {
    @Deprecated
    public int add(int i, int j) {
      return i + j;
    }
  }
  
  public static void main(String...args) {
    // @Deprecated が付いてメソッドの呼び出しは、コンパイル時に警告となる
    // また、eclipse上などで見ると打ち消し線が入り、ひと目で非推奨であることが分かる
    System.out.println(new DeprecatedSamples().new AddUtil().add(100, 200));
  }
}
