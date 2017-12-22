package stream.lesson.eishida;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class PrimeNumberDetectorTest {

  private class PrimeNumberDetector {
    // int値を受け取り、Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
    public List<Integer> detect(int i) {
    	List<Integer> list = IntStream.rangeClosed(2, i)
    			.filter(n -> isPrime(n))
    			.boxed()
    			.collect(Collectors.toList());
      return list;
    }

    // int値を受け取り、並列Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
    public List<Integer> detectParallel(int i) {
    	List<Integer> list = IntStream.rangeClosed(2, i)
    			.parallel()
    			.filter(n -> isPrime(n))
    			.boxed()
    			.collect(Collectors.toList());
      return list;
    }

    /*
     * 素数かどうかを判定する
     */
    public boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(number))
        		.allMatch(n -> (number % n) != 0);
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
