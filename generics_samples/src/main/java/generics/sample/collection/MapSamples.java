package generics.sample.collection;

import java.util.HashMap;
import java.util.Map;

import generics.sample.model.Abyssinian;
import generics.sample.model.Animal;
import generics.sample.model.Breedable;
import generics.sample.model.Cat;
import generics.sample.model.GoldenRetriever;
import generics.sample.model.Somali;
import generics.sample.model.Tiger;

public class MapSamples {
  // sample for raw type
  public void rawTypeSample() {
    // ジェネリクスを使わないRowMapのリストの生成
    Map map = new HashMap();
    map.put("Somali", new Somali());
    map.put("Abyssinian", new Abyssinian());
    map.put("GoldenRetriever", new GoldenRetriever());

    // key, value ともにObjectならなんでも入れられる
    map.put(new Tiger(), "Tiger");

    // 取り出す際はキャストが必要になる。
    // キャストする型と一致しないオブジェクトが入っている場合は、実行時に例外になる
    Cat cat = (Cat) map.get("Somali");

    // これは実行時に失敗する
    // Cat cat = (Cat)map.get("GoldenRetriever");
  }

  // sample for simple generics
  public void sampleForSimpleGenerics() {
    // 実クラスでのジェネリクス
    Map<String, Abyssinian> abyssinians = new HashMap<String, Abyssinian>();

    // これはコンパイルエラー
    // Map<String, Somali> somalies = new HashMap<String, Abyssinian>();

    // <> ダイヤモンドオペレータを使うとコンパイラが型推論してくれるので、コンストラクタへの型の指定は不要
    Map<String, Somali> somalies = new HashMap<>();
    somalies.put("somali", new Somali());

    // Mapを入れ子にするときなど、ダイヤモンドオペレータがあると楽
    Map<String, Map<String, ? extends Cat>> noDiamond = new HashMap<String, Map<String, ? extends Cat>>();
    Map<String, Map<String, ? extends Cat>> diamond = new HashMap<>();

    // これはコンパイル出来ない
    // somalies.put("abyssinian", new Abyssinian());

    // Somaliクラスのオブジェクトしか入れられないため、Somaliクラスとして取り出すときはキャストは不要
    Somali somali = somalies.get("somali");
  }

  // sample for super class generics
  public void sampleForSuperClassGenerics() {
    // スーパークラスでのジェネリクス
    Map<String, Cat> cats = new HashMap<>();

    cats.put("somali", new Somali());
    cats.put("abyssinian", new Abyssinian());
    cats.put("tiger", new Tiger());

    // これはコンパイルできない
    // cats.put("goldenRetriever", new GoldenRetriever());

    // Catとして取り出すことが出来る
    Cat cat = cats.get("somali");

    // これは当然出来ない
    // Somali somali = cats.get("somali");

    // ダウンキャストをすると、Somaliとして取り出せる
    Somali somali = (Somali) cats.get("somali");
    // List同様に以下は実行時に失敗するので、書かないほうが良い
    // Somali somali2 = (Somali)cats.get("abyssinian");
  }

  // sample for interface generics
  public void sampleForInterfaceGenerics() {
    // インターフェイスでのジェネリクス。基本はスーパークラスと同じ。
    Map<String, Breedable> breedables = new HashMap<>();

    breedables.put("somali", new Somali());
    breedables.put("abyssinian", new Abyssinian());
    breedables.put("goldenRetriever", new GoldenRetriever());

    // これはコンパイルできない
    // breedables.put("Tiger", new Tiger());

    // 以下割愛
  }

  // sample for Upper Bounded Wildcards
  public void sampleForUpperBoundedWildcards() {
    Map<String, ? extends Cat> cats;

    // CatのMapは代入できる
    cats = new HashMap<String, Cat>();

    // これはコンパイルエラー。ワイルドカード付きのジェネリクスの場合は変更が出来ない
    // cats.put("somali", new Somali());
    // これもコンパイルエラー
    // cats.put("somali", new Cat(){});
    // cats.put("cat", new Cat(){});

    // これはエラーにならない
    cats.put("null", null);

    Map<String, Somali> somalies = new HashMap<>();
    somalies.put("somali", new Somali());
    somalies.put("somali", new Somali());

    // これも出来る
    cats = somalies;

    Map<String, GoldenRetriever> goldenRetrievers = new HashMap<>();
    // これはコンパイルエラー
    // cats = goldenRetrievers;

    // Catとして取り出すことが出来る
    Cat cat = cats.get("somali");

    // これはコンパイルエラー
    // Somali Somali = cats.get("somali");

    // key に使うことも出来る…があまり使わない
    Map<? extends Cat, String> map = new HashMap<Somali, String>();
  }

  // sample for Lower Bounded Wildcards
  public void sampleForLowerBoundedWildcards() {
    Map<String, ? super Cat> superCats;

    superCats = new HashMap<String, Cat>();
    superCats = new HashMap<String, Animal>();
    superCats = new HashMap<String, Object>();

    // これはコンパイルエラー
    // superCats = new HashMap<String, Somali>();

    // Catを継承したクラスのオブジェクトはput出来る
    superCats.put("abyssinian", new Abyssinian());
    superCats.put("somali", new Somali());
    superCats.put("cat", new Cat() {
    });
    // これは失敗する
    // superCats.put("animal", new Animal());

    // putAllも可能
    superCats.putAll(new HashMap<String, Somali>());

    // 基本的にgetは出来ない
    // Cat c = superCats.get("cat");
    // Somali s = superCats.get("somali");
    // Abyssinian a = superCats.get("abyssinian");
    // Animal animal = superCats.get("animal");

    // ただしObjectとして取り出すことは出来る
    Object o = superCats.get("cat");
  }

  // sample for Wildcards
  public void sampleForWildcards() {
    Map<?, ?> map;

    // なんでも代入できる
    map = new HashMap<String, Cat>();
    map = new HashMap<String, Somali>();
    map = new HashMap<String, GoldenRetriever>();
    map = new HashMap<String, Object>();
    map = new HashMap<Object, Object>();

    // putは出来ない
    // map.put("somali", new Somali());
    // map.put("abyssinian", new Abyssinian());
    // map.put("object", new Object());

    // これは例外
    map.put(null, null);

    // キャストなしにgetも出来ない
    // Cat cat = map.get("cat");
    // Somali somali = map.get("somali");
    // GoldenRetriever goldenRetriever = map.get("goldenRetriever");

    // ただしObjectとして取り出すことは出来る
    Object object = map.get("object");
  }
}
