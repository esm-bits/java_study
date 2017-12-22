package stream.lesson.fossamagna;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class PrimeNumberDetectorTest {

  private class PrimeNumberDetector {
    // int値を受け取り、Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
    // ※ まだ答えを書いていません…
    public List<Integer> detect(int i) {
      return IntStream.range(0, i).filter(this::isPrimeNumber).boxed().collect(toList());
    }

    // int値を受け取り、並列Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
    // ※ まだ答えを書いていません…
    public List<Integer> detectParallel(int i) {
      return IntStream.range(0, i).parallel().filter(this::isPrimeNumber).boxed().collect(toList());
    }

    boolean isPrimeNumber(int num) {
      // 1以下は素数ではない
      if ( 1 >= num ) {
        return false;
      }
      // 2の場合は素数
      if ( 2 == num ) {
        return true;
      }

      // 素数判定
      int n = (int)Math.sqrt(num);
      for (int i = 2; i <= n; ++ i) {
        // 余り0で割り切れるかを判定
        if (0 == (num % i)) {
          return false;
        }
      }

      // numが2～nで割り切れなかったので素数
      return true;
    }
  }

  @Test
  public void 素数判定() {
    List<Integer> primeNumbers = new PrimeNumberDetector().detect(20);

    assertThat(primeNumbers.size(), is(8));
    assertThat(primeNumbers.get(0), is(2));
    assertThat(primeNumbers.get(1), is(3));
    assertThat(primeNumbers.get(2), is(5));
    assertThat(primeNumbers.get(3), is(7));
    assertThat(primeNumbers.get(4), is(11));
    assertThat(primeNumbers.get(5), is(13));
    assertThat(primeNumbers.get(6), is(17));
    assertThat(primeNumbers.get(7), is(19));
  }

  @Test
  public void 素数判定並列版() {
    List<Integer> primeNumbers = new PrimeNumberDetector().detectParallel(20);

    assertThat(primeNumbers.size(), is(8));
    assertThat(primeNumbers.get(0), is(2));
    assertThat(primeNumbers.get(1), is(3));
    assertThat(primeNumbers.get(2), is(5));
    assertThat(primeNumbers.get(3), is(7));
    assertThat(primeNumbers.get(4), is(11));
    assertThat(primeNumbers.get(5), is(13));
    assertThat(primeNumbers.get(6), is(17));
    assertThat(primeNumbers.get(7), is(19));
  }
}
