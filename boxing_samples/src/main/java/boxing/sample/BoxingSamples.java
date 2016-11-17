package boxing.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BoxingSamples {

  @SuppressWarnings("unused")
  public void simpleOldCode() {
    byte b = 1;
    Byte byte_ = new Byte(b);
    byte b2 = byte_.byteValue();

    char c = 'c';
    Character char_ = new Character(c);
    char c2 = char_.charValue();

    short s = 1;
    Short short_ = new Short(s);
    short s2 = short_.shortValue();

    int i = 1;
    Integer integer_ = new Integer(i);
    int i2 = integer_.intValue();

    long l = 1;
    Long long_ = new Long(i);
    long l2 = long_.longValue();

    float f = 0.1F;
    Float float_ = new Float(f);
    float f2 = float_.floatValue();

    double d = 0.1;
    Double double_ = new Double(d);
    double d2 = double_.doubleValue();

    boolean bool = true;
    Boolean boolean_ = new Boolean(bool);
    boolean bool2 = boolean_.booleanValue();
  }

  @SuppressWarnings("unused")
  public void simpleNewCode() {
    byte b = 1;
    Byte byte_ = b;
    byte b2 = byte_;

    char c = 'c';
    Character char_ = c;
    char c2 = char_;

    short s = 1;
    Short short_ = s;
    short s2 = short_;

    int i = 1;
    Integer integer_ = i;
    int i2 = integer_;

    long l = 1;
    Long long_ = l;
    long l2 = long_;

    float f = 0.1F;
    Float float_ = f;
    float f2 = float_;

    double d = 0.1;
    Double double_ = d;
    double d2 = double_;

    boolean bool = true;
    Boolean boolean_ = bool;
    boolean bool2 = boolean_;
    
    // オブジェクト型の変数にはnullを入れることが出来るが、
    // null値にオートボクシングをかけようとすると実行時に例外になる
    // Integer null_value = null;
    // int null_v = null_value;
  }

  // 実践的な例として、数値の入ったListをつくり
  // 偶数のみを取り出して降順でソートする場合を1.4以前の書き方で書きます
  @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
  public List practicalOldCode() {
    List list = new ArrayList();

    // 1.4以前はこれはコンパイルできなかった
    // なぜなら、プリミティブ型のintはObjectのサブクラスではないため
    // list.add(1);

    list.add(Integer.valueOf(1));
    list.add(Integer.valueOf(2));
    list.add(Integer.valueOf(3));
    list.add(Integer.valueOf(4));
    list.add(Integer.valueOf(5));
    list.add(Integer.valueOf(6));

    // あるいはArrays.asListを使う
    List list2 = Arrays.asList(new Integer[] { new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5), new Integer(6) });

    List evens = new ArrayList();
    for (Iterator iter = list.iterator(); iter.hasNext();) {
      Integer integer = (Integer) iter.next();

      // ここではnullの場合は考慮しない
      if (integer.intValue() % 2 == 0) {
        evens.add(integer);
      }
    }

    evens.sort(new Comparator() {
      public int compare(Object o1, Object o2) {
        // ここではnullの場合は考慮しない
        // IntegerにダウンキャストしてintValueメソッドを呼び、int同士を-演算子で計算している
        return ((Integer) o2).intValue() - ((Integer) o1).intValue();
      }
    });

    return evens; 
    //evens.stream().forEach(System.out::println);
    // 6, 4, 2 の順に表示される
  }

  // 実践的な例として、数値の入ったListをつくり
  // 偶数のみを取り出して降順でソートする場合を5以降の書き方で書きます
  @SuppressWarnings({ "unused" })
  public List<Integer> practicalNewCode() {
    List<Integer> list = new ArrayList<>();

    // オートボクシングされるので、これはコンパイル出来る
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);

    // あるいはArrays.asListを使う
    List<Integer> list2 = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6 });

    List<Integer> evens = new ArrayList<>();
    for (Integer integer : list) {
        // unboxing が効くため直接%演算子にかける事ができる
      if (integer % 2 == 0) {
        evens.add(integer);
      }
    }

    evens.sort(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        // unboxing & genericsが効くため直接-演算子にかける事ができる
        return o2 - o1;
      }
    });

    return evens;
    //evens.stream().forEach(System.out::println);
    // 6, 4, 2 の順に表示される
  }

  // おまけ
  // Java8だと実質１行で書ける
  public List<Integer> omake() {
    return Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6 }).stream().
        filter(n -> n % 2 == 0).
        sorted((n, m) -> m - n).
        collect(Collectors.toList());
  }
}
