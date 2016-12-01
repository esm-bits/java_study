package boxing.sample;

//import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class BoxingSampleTest {

  // エラーにならないことを確認する
  @Test
  public void testSimpleOldCode() {
    new BoxingSamples().simpleOldCode();
  }

  @Test
  public void testSimpleNewCode() {
    new BoxingSamples().simpleNewCode();
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void testPracticalOldCode() {
    List actual = new BoxingSamples().practicalOldCode();

    assertThat(actual.get(0), equalTo(6));
    assertThat(actual.get(1), equalTo(4));
    assertThat(actual.get(2), equalTo(2));
  }

  @Test
  public void testPracticalNewCode() {
    List<Integer> actual = new BoxingSamples().practicalNewCode();

    assertThat(actual.get(0), equalTo(6));
    assertThat(actual.get(1), equalTo(4));
    assertThat(actual.get(2), equalTo(2));
  }

  @Test
  public void testOmake() {
    List<Integer> actual = new BoxingSamples().omake();

    assertThat(actual.get(0), equalTo(6));
    assertThat(actual.get(1), equalTo(4));
    assertThat(actual.get(2), equalTo(2));
  }
}
