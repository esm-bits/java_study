package stream.lesson.eishida;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.Optional;
import org.junit.Test;

public class OptionalTest {

  // 3の倍数の時"Fizz", 5の倍数の時"Buzz", 3と5の倍数の時"FizzBuzz"を返すメソッドを実装してください
  // ただし、戻り値はOptionalでwrapすること
  public Optional<String> fizzBuzz(int i) {

    // << MEMO >>
    // Optional.ofNullable で全て賄う事も可能だが、
    // 値が入る事が確実な場合には Optional.of を使用し、
    // null を返したい処理の場合は Optional.empty を使用した方が明示的で可読性も上がる。
    // Optional.ofNullable のみだと本当の異常データが見逃されてしまう。
    // Optional.of で null を与えると NullPointerException が発生する。

    Optional<String> str;

    if (i % 3 == 0) {
      if (i % 5 == 0) {
        // 3と5の倍数の時
        str = Optional.of("FizzBuzz");
      } else {
        // 3の倍数の時
        str = Optional.of("Fizz");
      }
    } else {
      if (i % 5 == 0) {
        // 5の倍数の時
        str = Optional.of("Buzz");
      } else {
        // 上記いずれでもない時
        str = Optional.empty();
      }
    }

    return str;

/*
    // Optional.ofNullable を使う場合
    String str = null;

    if (i % 3 == 0) {
      if (i % 5 == 0) {
        // 3と5の倍数の時
        str = "FizzBuzz";
      } else {
        // 3の倍数の時
        str = "Fizz";
      }
    } else if (i % 5 == 0) {
      // 5の倍数の時
      str = "Buzz";
    }

    return Optional.ofNullable(str);
*/
  }

  @Test
  public void testFizzBuzz() {
    OptionalTest optionalTest = new OptionalTest();

    assertFalse(optionalTest.fizzBuzz(1).isPresent());
    assertFalse(optionalTest.fizzBuzz(2).isPresent());
    assertTrue(optionalTest.fizzBuzz(3).isPresent());
    assertThat(optionalTest.fizzBuzz(3).get(), is("Fizz"));
    assertFalse(optionalTest.fizzBuzz(4).isPresent());
    assertTrue(optionalTest.fizzBuzz(5).isPresent());
    assertThat(optionalTest.fizzBuzz(5).get(), is("Buzz"));

    // 以下同様に、6, 7, 8, 9, 10, 15, 30 のアサーションを実装してください
    assertTrue(optionalTest.fizzBuzz(6).isPresent());
    assertThat(optionalTest.fizzBuzz(6).get(), is("Fizz"));
    assertFalse(optionalTest.fizzBuzz(7).isPresent());
    assertFalse(optionalTest.fizzBuzz(8).isPresent());
    assertTrue(optionalTest.fizzBuzz(9).isPresent());
    assertThat(optionalTest.fizzBuzz(9).get(), is("Fizz"));
    assertTrue(optionalTest.fizzBuzz(10).isPresent());
    assertThat(optionalTest.fizzBuzz(10).get(), is("Buzz"));
    assertTrue(optionalTest.fizzBuzz(15).isPresent());
    assertThat(optionalTest.fizzBuzz(15).get(), is("FizzBuzz"));

  }
}
