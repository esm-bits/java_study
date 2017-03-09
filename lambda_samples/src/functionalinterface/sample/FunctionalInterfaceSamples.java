package functionalinterface.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.UnaryOperator;

public class FunctionalInterfaceSamples {

  public static void main(String[] args) {
    // testSupplier();
    // testConsumer();
    // testPredicate();
    // testFunction();
    // testUnaryOperator();
    testBinaryOperator();
  }

  /*
   * Supplier: 引数を受け取らず、値を返す関数型インターフェイス
   */
  private static void testSupplier() {
    // オブジェクト型向け
    Supplier<String> random4Digit = () -> String.valueOf(Math.random())
        .substring(2, 6);
    System.out.println(random4Digit.get());
    System.out.println(random4Digit.get());
    System.out.println(random4Digit.get());

    // プリミティブ型向け(他にもboolean, long, double向けの物が用意されている)
    IntSupplier dice = () -> new Random().nextInt(6) + 1;
    System.out.println(dice.getAsInt());
    System.out.println(dice.getAsInt());
    System.out.println(dice.getAsInt());
  }

  /*
   * Comsumer: 引数を受け取り、値を返さない関数型インターフェイス
   */
  private static void testConsumer() {
    // オブジェクト型向け
    Consumer<String> hello = (name) -> System.out.println("Hello, " + name);
    hello.accept("Eiwakun");

    // プリミティブ型向け(他にもlong, double向けの物が用意されている)
    IntConsumer looper = (i) -> {
      for (int j = 0; j < i; j++) {
        System.out.println("hi");
      }
    };
    looper.accept(5);

    // 引数を2つ受け取るConsumerもある
    BiConsumer<String, String> ppap = (s1, s2) -> System.out
        .println(s2 + " " + s1);
    ppap.accept("Pen", "Apple");
  }

  /*
   * Predicate: 引数を受け取って判定を行い、真偽値(boolean)を返す
   */
  private static void testPredicate() {
    // オブジェクト型向け
    Predicate<String> palindrome = (s) -> s
        .equals(new StringBuffer(s).reverse().toString());
    System.out.println(palindrome.test("rank"));
    System.out.println(palindrome.test("level"));

    // プリミティブ型向け(他にもlong, double向けの物が用意されている)
    IntPredicate dividableBy3 = (i) -> i % 3 == 0;
    System.out.println(dividableBy3.test(98));
    System.out.println(dividableBy3.test(123));

    // 引数を2つ受け取るPredicateもある
    BiPredicate<String, String> equals = (s1, s2) -> s1.equals(s2);
    System.out.println(equals.test("AAA", "BBB"));
    System.out.println(equals.test("AAA", "AAA"));
  }

  /*
   * Function: 引数を受け取り、変換して別の値を返す
   */
  private static void testFunction() {
    // オブジェクト型向け
    Function<List<String>, String> tableRow = (l) -> "|" + String.join("|", l)
        + "|";
    System.out
        .println(tableRow.apply(Arrays.asList("国語", "社会", "数学", "理科", "英語")));
    System.out.println(tableRow.apply(Arrays.asList("A", "A", "B", "A", "C")));

    // プリミティブ型の引数向け(他にもlong, double向けの物が用意されている)
    IntFunction<List<Integer>> sequentialList = (i) -> {
      List<Integer> l = new ArrayList<>();
      for (int j = 1; j <= i; j++) {
        l.add(j);
      }
      return l;
    };
    System.out.println(sequentialList.apply(10));

    // プリミティブ型の返り値向け(他にもint, long向けの物が用意されている)
    ToDoubleFunction<List<Double>> average = (l) -> {
      double sum = 0.0;
      for (Double d : l) {
        sum += d;
      }
      return sum / l.size();
    };
    System.out
        .println(average.applyAsDouble(Arrays.asList(5.0, 12.3, 3.14, 33.4)));

    // プリミティブ型同士の変換向けFunctionも用意されている(int, long, doubleの相互変換が可能)
    IntToDoubleFunction sqrt = (i) -> Math.sqrt(i);
    System.out.println(sqrt.applyAsDouble(2));

    // 引数を2つ受け取るFunctionもある
    BiFunction<String, String, String> ppap = (s1, s2) -> s2 + " " + s1;
    System.out.println(ppap.apply("Pen", "Apple"));
  }

  /*
   * UnaryOperator: 引数を1つ受け取り、演算した同じ型の値を返す
   */
  private static void testUnaryOperator() {
    // オブジェクト型向け
    UnaryOperator<String> reverse = (s) -> new StringBuffer(s).reverse()
        .toString();
    System.out.println(reverse.apply("animation"));

    // プリミティブ型向け(他にもlong, double向けの物が用意されている)
    IntUnaryOperator square = (i) -> i * i;
    System.out.println(square.applyAsInt(24));
  }

  /*
   * BinaryOperator: 引数を2つ受け取り、演算した同じ型の値を返す
   */
  private static void testBinaryOperator() {
    // オブジェクト型向け
    BinaryOperator<String> ppap = (s1, s2) -> s2 + " " + s1;
    System.out.println(ppap.apply("Pen", "Apple"));

    // プリミティブ型向け
    IntBinaryOperator factorial = (i, j) -> {
      int result = 1;
      for (int k = 0; k < j; k++) {
        result *= i;
      }
      return result;
    };
    System.out.println(factorial.applyAsInt(4, 5));
  }
}
