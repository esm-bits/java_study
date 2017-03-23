import java.util.Comparator;
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

  public static void main(String[] args) {
    lesson();
  }
}
