package generics.sample.collection;

import java.util.ArrayList;
import java.util.List;

import generics.sample.model.Abyssinian;
import generics.sample.model.Animal;
import generics.sample.model.Breedable;
import generics.sample.model.Cat;
import generics.sample.model.GoldenRetriever;
import generics.sample.model.Somali;
import generics.sample.model.Tiger;

public class ListSamples {

  // sample for row type
  public void rowTypeSample() {
    // ジェネリクスを使わないRowTypeのリストの生成
    List list = new ArrayList();

    list.add(new Somali());
    list.add(new Abyssinian());
    list.add(new GoldenRetriever());

    // 取り出す際はキャストが必要になる。
    // キャストする型と一致しないオブジェクトが入っている場合は、実行時に例外になる
    Cat cat = (Cat)list.get(0);

    // これは実行時に失敗する
    // Cat cat (Cat)list.get(2);
  }

  // sample for simple generics
  public void sampleForSimpleGenerics() {
    // 実クラスでのジェネリクス
    List<Abyssinian> abyssinians = new ArrayList<Abyssinian>();

    // これはコンパイルエラー
    // List<Abyssinian> abyssinians2 = new ArrayList<Somali>();

    // <> ダイヤモンドオペレータを使うとコンパイラが型推論してくれるので、コンストラクタへの型の指定は不要
    List<Somali> somalies = new ArrayList<>();
    somalies.add(new Somali());

    // これはコンパイル出来ない
    // somalies.add(new Abyssinian());

    // Somaliクラスのオブジェクトしか入れられないため、Somaliクラスとして取り出すときはキャストは不要
    Somali somali = somalies.get(0);
  }

  // sample for super class generics
  public void sampleForSuperClassGenerics() {
    // スーパークラスでのジェネリクス  
    List<Cat> cats = new ArrayList<>();

    cats.add(new Somali());
    cats.add(new Abyssinian());
    cats.add(new Tiger());

    // これはコンパイルできない
    // cats.add(new GoldenRetriever());

    // Catとして取り出すことが出来る
    Cat cat = cats.get(0);

    // これは当然出来ない
    // Somali somali = cats.get(0);

    // ダウンキャストをすると、Somaliとして取り出せる
    Somali somali = (Somali)cats.get(0);
    // ……が以下は実行時に失敗する。ダウンキャストを使うとジェネリクスの意味が無いので書くべきではない
    //Somali somali2 = (Somali)cats.get(1);
  }

  // sample for interface generics
  public void sampleForInterfaceGenerics() {
    // インターフェイスでのジェネリクス。基本はスーパークラスと同じ。
    List<Breedable> breedables = new ArrayList<>();

    breedables.add(new Somali());
    breedables.add(new Abyssinian());
    breedables.add(new GoldenRetriever());

    // これはコンパイルできない
    // breedables.add(new Tiger());

    // 以下割愛
  }

  // sample for Upper Bounded Wildcards
  public void sampleForUpperBoundedWildcards() {
    List<? extends Cat> cats;

    // Catのリストは代入できる
    cats = new ArrayList<Cat>();

    // これはコンパイルエラー。ワイルドカード付きのジェネリクスの場合は変更が出来ない
    // cats.add(new Somali());
    // これもコンパイルエラー
    // cats.add(new Cat(){});

    // これはエラーにならない
    cats.add(null);

    List<Somali> somalies = new ArrayList<>();
    somalies.add(new Somali());
    somalies.add(new Somali());

    // これも出来る
    cats = somalies;

    List<GoldenRetriever> goldenRetrievers = new ArrayList<>();
    // これはコンパイルエラー
    // cats = goldenRetrievers;

    // Catとして取り出すことが出来る
    Cat cat = cats.get(0);

    // これはコンパイルエラー
    // Somali Somali = cats.get(0);
  }

  // sample for Lower Bounded Wildcards
  public void sampleForLowerBoundedWildcards() {
    List<? super Cat> superCats;

    superCats = new ArrayList<Cat>();

    List<Somali> somalies = new ArrayList<>();
    // これはコンパイルエラー
    // superSomalies = somalies;

    List<Animal> animals = new ArrayList<>();
    superCats = animals;

    List<Object> objects = new ArrayList<>();
    superCats = objects;

    // Catを継承したクラスのオブジェクトはadd出来る
    superCats.add(new Abyssinian());
    superCats.add(new Somali());
    superCats.add(new Cat(){});
    // これは失敗する
    // superCats.add(new Animal());

    // addAllも可能
    superCats.addAll(new ArrayList<Somali>());

    // 基本的にgetは出来ない
    // Cat c = superCats.get(0);
    // Somali s = superCats.get(0);
    // Abyssinian a = superCats.get(0);
    // Animal animal = superCats.get(0);

    // ただしObjectとして取り出すことは出来る
    Object o = superCats.get(0);
  }

  // sample for Wildcards
  public void sampleForWildcards() {
    List<?> list;

    // なんでも代入できる
    list = new ArrayList<Cat>();
    list = new ArrayList<Somali>();
    list = new ArrayList<GoldenRetriever>();
    list = new ArrayList<Object>();

    // addは出来ない
    // list.add(new Somali());
    // list.add(new Abyssinian());
    // list.add(new Object());

    // これは例外
    list.add(null);

    // キャストなしにgetも出来ない
    // Cat cat = list.get(0);
    // Somali somali = list.get(0);
    // GoldenRetriever goldenRetriever = list.get(0);
  }
}
