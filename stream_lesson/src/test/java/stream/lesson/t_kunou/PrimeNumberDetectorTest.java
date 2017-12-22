package stream.lesson.t_kunou;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class PrimeNumberDetectorTest {

  private class PrimeNumberDetector {
    // int値を受け取り、Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
    public List<Integer> detect(int i) {
      return IntStream.range(2, i)
        .filter(n -> isPrime(n))
        .boxed()
        .collect(Collectors.toList());
    }

    // int値を受け取り、並列Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
    public List<Integer> detectParallel(int i) {
      return IntStream.range(2, i)
        .parallel()
        .filter(n -> isPrime(n))
        .boxed()
        .sorted()
        .collect(Collectors.toList());
    }

    public boolean isPrime(int n) {
      if (n < 2) {
        return false;
      } else if (n == 2) {
        return true;
      } else if ((n & 1) == 0) {
        return false;
      }

      return IntStream.range(3, n).allMatch(x -> n % x != 0);
    }
  }

  @Test
  public void isPrime() {
    assertThat(new PrimeNumberDetector().isPrime(14), is(false));
    assertThat(new PrimeNumberDetector().isPrime(15), is(false));
    assertThat(new PrimeNumberDetector().isPrime(16), is(false));
    assertThat(new PrimeNumberDetector().isPrime(17), is(true));
    assertThat(new PrimeNumberDetector().isPrime(18), is(false));
    assertThat(new PrimeNumberDetector().isPrime(19), is(true));
    assertThat(new PrimeNumberDetector().isPrime(20), is(false));
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
