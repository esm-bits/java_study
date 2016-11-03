package generics.sample.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import generics.sample.model.Abyssinian;
import generics.sample.model.Cat;
import generics.sample.model.Dog;
import generics.sample.model.GoldenRetriever;
import generics.sample.model.Somali;
import generics.sample.model.Tiger;

// 自前のクラスでジェネリクスを使う例として、Comparatorを実装する場合
public class ComparatorSamples {

  // RawTypeを使った1.4 以前の書き方
  private class RawTypeCatComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
      // weight の大きい順に並べる
      // ここではNullの場合は考えない
      // また、本当は instanceof 等でオブジェクトのクラスを調べるべきだがここでは直接キャストする
      return ((Cat) o2).getWeight() - ((Cat) o1).getWeight();
    }
  }

  public void rawTypeComparatorSample() {
    List list = new ArrayList<>();

    list.add(new Somali("Som", 3500));
    list.add(new Abyssinian("Aby", 2500));
    list.add(new Tiger("Tig", 120000));

    // Tig,Som,Aby の順に並ぶ
    list.sort(new RawTypeCatComparator());
    list.stream().map((obj) -> ((Cat)obj).getName()).forEach(System.out::println);

    list.add(new GoldenRetriever("Gold", 120000));
    // これは実行時にClassCastExceptionになる
    // list.sort(new RawTypeCatComparator());

    // これは実行できる
    new RawTypeCatComparator().compare(new Somali("Soma", 1000), new Somali("Soma", 2000));
    new RawTypeCatComparator().compare(new Somali("Soma", 1000), new Abyssinian("Aby", 2000));

    // これは実行時にClassCastExceptionになる
    // new RawTypeCatComparator().compare(new Somali("Soma", 1000), new GoldenRetriever("Gold", 2000));
  }

  // Genericsで型を指定できるようにしたComparator
  private class GenericsCatComparator<T> implements Comparator<T> {
    @Override
    public int compare(T t1, T t2) {
      // weight の大きい順に並べる
      // ここではNullの場合は考えない
      // TがCatであるかどうかは不明なため結局キャストは必要になる
      return ((Cat) t2).getWeight() - ((Cat) t1).getWeight();
    }
  }

  public void genericsComparatorSample() {
    // これは実行できる
    new GenericsCatComparator<Dog>();
    new GenericsCatComparator<GoldenRetriever>();

    // これは実行できる
    new GenericsCatComparator<Cat>().compare(new Somali("Soma", 1000), new Somali("Soma2", 2000));
    new GenericsCatComparator<Cat>().compare(new Somali("Soma", 1000), new Abyssinian("Aby", 2000));
    new GenericsCatComparator<Somali>().compare(new Somali("Soma", 1000), new Somali("soma2", 2000));

    // これはコンパイル出来ない
    // new GenericsCatComparator<Cat>().compare(new Somali("Soma", 1000), new GoldenRetriever("Gold", 2000));
    // new GenericsCatComparator<Somali>().compare(new Somali("Soma", 1000), new Abyssinian("soma2", 2000));

    // これは実行できる
    new ArrayList<Cat>().sort(new GenericsCatComparator<Cat>());
    new ArrayList<Somali>().sort(new GenericsCatComparator<Somali>());
    new ArrayList<Somali>().sort(new GenericsCatComparator<Cat>());

    // これはコンパイル出来ない
    // new ArrayList<Cat>().sort(new GenericsCatComparator<Somali>());
    // new ArrayList<Cat>().sort(new GenericsCatComparator<Dog>());
  }

  // GenericsでCatのサブクラスのみに型を指定できるようにしたComparator
  private class GenericsCatComparator2<T extends Cat> implements Comparator<T> {
    @Override
    public int compare(T t1, T t2) {
      // weight の大きい順に並べる
      // ここではNullの場合は考えない
      // TはCatのサブクラスしか入らないのでキャストは不要
      return t2.getWeight() - t1.getWeight();
    }
  }

  public void genericsComparatorSample2() {
    // これはコンパイルできない
    // new GenericsCatComparator2<Dog>();
    // new GenericsCatComparator2<GoldenRetriever>();;

    // これは実行できる
    new GenericsCatComparator2<Cat>().compare(new Somali("Soma", 1000), new Somali("Soma2", 2000));
    new GenericsCatComparator2<Cat>().compare(new Somali("Soma", 1000), new Abyssinian("Aby", 2000));
    new GenericsCatComparator<Somali>().compare(new Somali("Soma", 1000), new Somali("soma2", 2000));

    // これはコンパイル出来ない
    // new GenericsCatComparator2<Cat>().compare(new Somali("Soma", 1000), new GoldenRetriever("Gold", 2000));
    // new GenericsCatComparator<Somali>().compare(new Somali("Soma", 1000), new Abyssinian("soma2", 2000));

    // これは実行できる
    new ArrayList<Cat>().sort(new GenericsCatComparator2<Cat>());
    new ArrayList<Somali>().sort(new GenericsCatComparator2<Somali>());
    new ArrayList<Somali>().sort(new GenericsCatComparator2<Cat>());

    // これはコンパイル出来ない
    // new ArrayList<Cat>().sort(new GenericsCatComparator<Somali>());
    // new ArrayList<Cat>().sort(new GenericsCatComparator<Dog>());
  }
}
