package generics.sample.comparator;

import org.junit.Test;

// 実行時に例外にならないことだけ確認する
public class ComparatorSamplesTest {
  @Test
  public void testRawTypeComparatorSample() { new ComparatorSamples().rawTypeComparatorSample(); }

  @Test
  public void testGenericsComparatorSample() { new ComparatorSamples().genericsComparatorSample(); }

  @Test
  public void testGenericsComparatorSample2() { new ComparatorSamples().genericsComparatorSample2(); }
}
