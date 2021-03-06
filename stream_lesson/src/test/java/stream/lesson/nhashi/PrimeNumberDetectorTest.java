package stream.lesson.nhashi;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class PrimeNumberDetectorTest {

  private class PrimeNumberDetector {

      // int値を受け取り、Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
      public List<Integer> detect(int i) {
        return IntStream.rangeClosed(0,i).filter(this::isPrime).boxed().collect(toList());
      }

      // int値を受け取り、並列Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
      public List<Integer> detectParallel(int i) {
        return IntStream.rangeClosed(0,i).parallel().filter(this::isPrime).boxed().collect(toList());
      }

      private boolean isPrime(int n) {
        if (n < 2) {
          return false;
        } else if (n == 2) {
          return true;
        } else if ((n & 1) == 0) {
          return false;
        } else {
          return IntStream.range(3, n).allMatch(x -> n % x != 0);
        }
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
