package generics.sample.collection;

import org.junit.Test;

// 実行時に例外にならないことだけ確認する
public class MapSamplesTest {
  @Test
  public void testRawTypeSample() { new MapSamples().rawTypeSample(); }

  @Test
  public void testSampleForSimpleGenerics() { new MapSamples().sampleForSimpleGenerics(); }

  @Test
  public void testSampleForSuperClassGenerics() { new MapSamples().sampleForSuperClassGenerics(); }

  @Test
  public void testSampleForInterfaceGenerics() { new MapSamples().sampleForInterfaceGenerics(); }

  @Test
  public void testSampleForUpperBoundedWildcards() { new MapSamples().sampleForUpperBoundedWildcards(); }

  @Test
  public void testSampleForLowerBoundedWildcards() { new MapSamples().sampleForLowerBoundedWildcards(); }

  @Test
  public void testSampleForWildcards() { new MapSamples().sampleForWildcards(); }
}
