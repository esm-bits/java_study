package stream.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BasicSample {
  public static void main(String[] args) {
    List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    System.out.println(filterMultiplesOf3WithForEach(l));
    System.out.println(filterMultiplesOf3WithStream(l));
    System.out.println(mapSquaredWithForEach(l));
    System.out.println(mapSquaredWithStream(l));

    List<String> ll = Arrays.asList("Pen", "Pineapple", "Apple", "Pen");

    System.out.println(uniqAndReverseSortWithForEach(ll));
    System.out.println(uniqAndReverseSortWithStream(ll));
  }

  /*
   * 重複する値を除去し、辞書の逆順にソートする（従来のやり方）
   */
  private static List<String> uniqAndReverseSortWithForEach(List<String> l) {
    List<String> l2 = new ArrayList<>();
    for (String s : l) {
      if (!l2.contains(s)) {
        l2.add(s);
      }
    }
    Collections.sort(l2, Collections.reverseOrder());
    return l2;
  }

  /*
   * 重複する値を除去し、辞書の逆順にソートする（Streamを用いたやり方）
   */
  private static List<String> uniqAndReverseSortWithStream(List<String> l) {
    return l.stream().distinct().sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
  }

  /*
   * 各要素を2乗したリストを作る（従来のやり方）
   */
  private static List<Integer> mapSquaredWithForEach(List<Integer> l) {
    List<Integer> l2 = new ArrayList<>();
    for (Integer i : l) {
      l2.add(i * i);
    }
    return l2;
  }

  /*
   * 各要素を2乗したリストを作る（Streamを用いたやり方）
   */
  private static List<Integer> mapSquaredWithStream(List<Integer> l) {
    return l.stream().map(i -> i * i).collect(Collectors.toList());
  }

  /*
   * 3の倍数だけを取り出して出力する（従来のやり方）
   */
  private static List<Integer> filterMultiplesOf3WithForEach(List<Integer> l) {
    List<Integer> l2 = new ArrayList<>();
    for (Integer i : l) {
      if (i % 3 == 0) {
        l2.add(i);
      }
    }
    return l2;
  }

  /*
   * 3の倍数だけを取り出して出力する（Streamを用いたやり方）
   */
  private static List<Integer> filterMultiplesOf3WithStream(List<Integer> l) {
    return l.stream().filter(i -> i % 3 == 0).collect(Collectors.toList());
  }
}
