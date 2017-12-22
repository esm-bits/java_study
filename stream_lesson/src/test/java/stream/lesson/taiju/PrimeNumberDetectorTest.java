package stream.lesson.taiju;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class PrimeNumberDetectorTest {

  private class PrimeNumberDetector {
    public List<Integer> detect(int i) {
      // [[3], [2]], [[4], [2, 3]], [[5], [2, 3]], [[6], [2, 3, 5]] みたいなリストを
      // 永遠に返す stream からひとつ選んで二つ目の要素を返す
      return i <= 1 ? emptyList() :
             i == 2 ? Arrays.asList(2) :
             // otherwise
             Stream.iterate(Arrays.asList(Arrays.asList(3), Arrays.asList(2)), l -> {
               final Integer n = l.get(0).get(0);
               final List<Integer> primes = l.get(1);
               return primes.stream().allMatch(m -> n % m != 0) ?
                       Arrays.asList(Arrays.asList(n + 1), Stream.of(primes, Arrays.asList(n))
                                                             .flatMap(List<Integer>::stream)
                                                             .collect(toList())
                       ) : Arrays.asList(Arrays.asList(n + 1), primes);
      }).filter(l -> l.get(0).get(0) >= i + 1).findFirst().get().get(1);
    }

    // 他の人の回答を拝借
    public List<Integer> detectParallel(int i) {
      return IntStream.range(2, i)
                      .parallel()
                      .filter(PrimeNumberDetectorTest.this::isPrime)
                      .boxed()
                      .collect(toList());
    }
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

  @Test
  public void 素数判定_2以下() {
    List<Integer> ps1 = new PrimeNumberDetector().detect(0);
    assertThat(ps1, hasSize(0));
    List<Integer> ps2 = new PrimeNumberDetector().detect(1);
    assertThat(ps2, hasSize(0));
    List<Integer> ps3 = new PrimeNumberDetector().detect(2);
    assertThat(ps3, hasSize(1));
    assertThat(ps3.get(0), is(2));
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
