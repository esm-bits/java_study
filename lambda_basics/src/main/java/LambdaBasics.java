import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Java8ラムダ式の基本を示す
 */
public class LambdaBasics {

  /**
   * ラムダ式の基本形と省略形
   */
  public void lambdaStyles() {
    // 基本形
    //                                               通常の関数と同じく、仮引数を
    //                                                型＋変数名
    //                                               で定義し、全体を丸カッコで囲む
    BiFunction<Integer, String, Boolean> function1 = (Integer a, String b) -> { // 仮引数部分に続いて、'->' の後に関数の本体を定義する
      // ラムダ式の関数本体は通常の関数と同じく波カッコで囲む
      // ラムダ内のreturnはラムダから抜ける
      return true;
    };

    // 省略形その１
    //                                               function2の型宣言より引数の型が推論可能であるため
    //                                               仮引数の型を省略できる
    BiFunction<Integer, String, Boolean> function2 = (a, b) -> {
      return true;
    };

    // 省略形その２
    //                                               関数本体が１つの式の場合は波カッコとreturnを省略できる
    BiFunction<Integer, String, Boolean> function3 = (a, b) -> true;

    //                                void型の関数も式１つなら省略可能
    Consumer<Integer> consumer = v -> System.out.println(v);

    // 省略形その３
    //                                    引数が１つの場合は仮引数の丸カッコを省略できる
    Function<Integer, Boolean> function4 = v -> true;

    //                           引数がない場合は空の丸カッコが必要
    Supplier<Integer> supplier = () -> 1;
  }

  // 型推論
  public void typeInference() {

    // callBoolFunction()の型パラメータTは実引数100によりIntegerと推論される
    //                         このvもIntegerとなる
    callBoolFunction(100, v -> v > 50);

    List<String> stringList = new ArrayList<>();

    // Comparator.comparingのシグネチャは以下
    //  public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor)
    // List<String>からTがStringに、length()からUがIntegerに推論される
    stringList.sort(Comparator.comparing(s -> s.length()));

    // ちなみに上記は以下のようにも書ける
    //                                   この書き方をメソッド参照という
    stringList.sort(Comparator.comparing(String::length));
  }

  private <T> Boolean callBoolFunction(T arg, Function<T, Boolean> function) {
    return function.apply(arg);
  }

  // キャプチャ
  public void capture(int arg) {
    final String str = "abc";

    Runnable proc1 = () -> {
      // ラムダ式の外側にあるfinal変数を参照できる
      System.out.println(str);

      // 明示的にfinalを付けていない変数も参照可能（実質的final）
      System.out.println(arg);

      // ラムダ内で外側の変数を変更することはできない
      // arg += 2;
    };

    // ラムダ内で参照している変数をラムダの外側で変更すると実質的finalでなくなるので
    // ラムダ内で参照できなくなる
    // arg += 5;
  }
}
