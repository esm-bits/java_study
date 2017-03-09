package lambda.sample;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaSamples {

  /*
   * ラムダを使用しない例(Java7以前)
   */
  private static void sortWithoutLambda(List<Integer> list) {
    list.sort(new Comparator<Integer>() {

      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
  }

  /*
   * ラムダ式を使用した例
   */
  private static void sortWithLambda(List<Integer> list) {
    list.sort((o1, o2) -> {
      return o1 - o2;
    });
  }

  /*
   * 処理本体に文が1個しか存在しない場合は波括弧・セミコロンが省略できる また、return文のみの場合はreturnも省略できる
   */
  private static void sortWithShorterLambda(List<Integer> list) {
    list.sort((o1, o2) -> o1 - o2);
  }

  /*
   * ラムダ式は引数に指定することもできる(mainメソッドを参照)
   */
  private static void sortWithLambdaFromArgument(List<Integer> list, Comparator<Integer> comparator) {
    list.sort(comparator);
  }

  private static void sortWithDefaultComparator(List<Integer> list) {
    list.sort(Comparator.naturalOrder());
  }

  /*
   * 数列(list)をソートする
   */
  public static void main(String args[]) {
    List<Integer> list = Arrays.asList(11, 42, 37, -45, 9);

    // sortWithoutLambda(list);
    // sortWithLambda(list);
    // sortWithShorterLambda(list);
    // sortWithLambdaFromArgument(list, (o1, o2) -> o1 - o2);
    sortWithDefaultComparator(list);

    System.out.println(list);
  }
}
