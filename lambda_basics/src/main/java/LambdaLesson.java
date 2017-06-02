import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class LambdaLesson {
  public static void lesson() {

    // 引数（Integer）を２倍にして返却するFunction
    Function<> function = ...;
    System.out.println(function.apply(3));

    // 引数のStringを比較するComparator
    Comparator<String> comparator = ...;
    System.out.println(comparator.compare("aaa", "bbb"));
  }

  public static String higherOrderFunction(Function<List<String>, String> function) {
    List<String> data = Arrays.asList("Java8", "lambda", "stream");

    return function.apply(data);
  }

  public static Function<List<String>, String> listToHtml() {
    return (list) -> {
      StringBuilder sb =new StringBuilder();
      sb.append("<ul>").append("\n");
      for (String element : list ){
        sb.append("  ").append("<li>").append(element).append("</li>").append("\n");
      }
      sb.append("</ul>").append("\n");

      return sb.toString();
    };
  }

  public static Function<List<String>, String> listToMarkdown() {
    return null; // FIXME
  }

  public static void main(String[] args) {
    lesson();

    System.out.println(higherOrderFunction(listToHtml()));
  }
}
