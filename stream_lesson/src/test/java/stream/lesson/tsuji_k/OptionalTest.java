package stream.lesson.tsuji_k;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

  // 3の倍数の時"Fizz", 5の倍数の時"Buzz", 3と5の倍数の時"FizzBuzz"を返すメソッドを実装してください
  // ただし、戻り値はOptionalでwrapすること
  public Optional<String> fizzBuzz(int i) {
	  String ret = "";

	  //iが3の倍数の時には“Fizz”を、5の倍数の時には“Buzz”を連結する。
	  if(i % 3 == 0) {
		  ret += "Fizz";
	  }
	  if(i % 5 == 0) {
		  ret += "Buzz";
	  }

	  //retの文字列長が0の場合には空のOptionalを返す。
	  if(ret.isEmpty()) {
		  return Optional.empty();
	  } else {
		  return Optional.ofNullable(ret);
	  }
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
    assertTrue(optionalTest.fizzBuzz(30).isPresent());
    assertThat(optionalTest.fizzBuzz(30).get(), is("FizzBuzz"));
  }
}
