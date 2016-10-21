package generics.sample.collection;

import org.junit.Test;

// 実行時に例外にならないことだけ確認する
public class ListSamplesTest {
  @Test
  public void testRowTypeSample() { new ListSamples().rowTypeSample(); }

  @Test
  public void testSampleForSimpleGenerics() { new ListSamples().sampleForSimpleGenerics(); }

  @Test
  public void testSampleForSuperClassGenerics() { new ListSamples().sampleForSuperClassGenerics(); }

  @Test
  public void testSampleForInterfaceGenerics() { new ListSamples().sampleForInterfaceGenerics(); }

  @Test
  public void testSampleForUpperBoundedWildcards() { new ListSamples().sampleForUpperBoundedWildcards(); }

  @Test
  public void testSampleForLowerBoundedWildcards() { new ListSamples().sampleForLowerBoundedWildcards(); }

  @Test
  public void testSampleForWildcards() { new ListSamples().sampleForWildcards(); }
}
