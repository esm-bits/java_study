import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class LambdaLesson {
  public static void lesson() {

    // 引数（Integer）を２倍にして返却するFunction
    Function<Integer,Integer> multilyFunction = v -> v * 2;
    System.out.println(multilyFunction.apply(3));

    // 引数のStringを比較するComparator
    Comparator<String> comparator = (a,b) -> a.compareTo(b);
    System.out.println(comparator.compare("aaa", "bbb"));
  }

  public static String higherOrderFunction( List<String> data, Function<List<String>, String> function) {

	// 昇順にソート
    data.sort(Comparator.naturalOrder());

    // 指定されたメソッドの実行
    return function.apply(data);
  }


  // Html形式のリスト化
  public static Function<List<String>, String> listToHtml() {
    return (list) -> {
      StringBuilder sb = new StringBuilder();
      sb.append("<ul>").append("\n");
      for (String element : list ){
        sb.append("  ").append("<li>").append(element).append("</li>").append("\n");
      }
      sb.append("</ul>").append("\n");

      return sb.toString();
    };
  }

  // Markdown形式のリスト化
  public static Function<List<String>, String> listToMarkdown() {
    return (list) -> {
    	StringBuilder sb = new StringBuilder();
    	list.forEach(element -> sb.append("* ").append(element).append("\n"));
    	return sb.toString();
    };
  }


  public static void main(String[] args) {
    lesson();

    List<String> data = Arrays.asList("lambda", "stream","Java8");

    System.out.println(higherOrderFunction(data, listToMarkdown()));
  }
}
