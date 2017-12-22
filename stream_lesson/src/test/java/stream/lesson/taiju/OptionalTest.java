package stream.lesson.taiju;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

  // 3の倍数の時"Fizz", 5の倍数の時"Buzz", 3と5の倍数の時"FizzBuzz"を返すメソッドを実装してください
  // ただし、戻り値はOptionalでwrapすること
  public Optional<String> fizzBuzz(int i) {
    return Optional.ofNullable(i % 15 == 0 ? "FizzBuzz" :
                               i % 3  == 0 ? "Fizz" :
                               i % 5  == 0 ? "Buzz" : null);
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
    assertThat(optionalTest.fizzBuzz(6).orElse(null), is("Fizz"));
    assertThat(optionalTest.fizzBuzz(7).orElse(null), is(nullValue()));
    assertThat(optionalTest.fizzBuzz(8).orElse(null), is(nullValue()));
    assertThat(optionalTest.fizzBuzz(9).orElse(null), is("Fizz"));
    assertThat(optionalTest.fizzBuzz(10).orElse(null), is("Buzz"));
    assertThat(optionalTest.fizzBuzz(15).orElse(null), is("FizzBuzz"));
    assertThat(optionalTest.fizzBuzz(30).orElse(null), is("FizzBuzz"));
  }
}
