package stream.lesson;

import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class PrimeNumberDetectorTest {

  private class PrimeNumberDetector {
    // int値を受け取り、Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
    public List<Integer> detect(int i) {
    	//引数で受け取ったiをrangeClosed(0, i)にてIntStream化
    	//　※ちなみに、rangeClosed(start, end)だと、end値は含まれる。rangeだとend値は含まれない。
    	//filter(this::isPrime)で引数で指定した条件に一致した(素数かどうか)モノだけのStreamにする。
    	//boxed()でボクシング　int → Integer化
    	//collect(toList())でリスト化
      return IntStream.rangeClosed(0, i).filter(this::isPrime).boxed().collect(toList());
    }

    // int値を受け取り、並列Streamを使って0からその値までの全ての素数をListにして返すメソッドを実装してください
    public List<Integer> detectParallel(int i) {
    	//parallel()メソッドを使用すると、順次ストリームを並列ストリームに変換してくれる(らしい)
    	//逆に、並列ストリームを順次ストリームに変換するためにはsequential()メソッドを使う(らしい)
    	return IntStream.rangeClosed(0, i).parallel().filter(this::isPrime).boxed().collect(toList());
    }

    /**
     * 素数判定のメソッド
     * @param n 整数
     * @return 素数：true、素数じゃない：false
     */
    private boolean isPrime(int n) {
    	//2以下の数値はfalseを返す
    	if(n < 2) {
    		return false;
    	}

    	for(int i = 2; i < n; i++) {
    		if(n % i == 0) {
    			return false;
    		}
    	}
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
