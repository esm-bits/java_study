package stream.lesson.potatosh;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

  // 3の倍数の時"Fizz", 5の倍数の時"Buzz", 3と5の倍数の時"FizzBuzz"を返すメソッドを実装してください
  // ただし、戻り値はOptionalでwrapすること
  public Optional<String> fizzBuzz(int i) {
    String s = "";
    s = s.concat(i % 3 == 0 ? "Fizz" : "");
    s = s.concat(i % 5 == 0 ? "Buzz" : "");
    return s.isEmpty() ? Optional.empty() : Optional.of(s);
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
    assertTrue(optionalTest.fizzBuzz(30).isPresent());
    assertThat(optionalTest.fizzBuzz(30).get(), is("FizzBuzz"));
  }
}
