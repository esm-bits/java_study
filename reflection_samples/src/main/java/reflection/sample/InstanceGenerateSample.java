package reflection.sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import reflection.sample.model.Animal;
import reflection.sample.model.Cat;

public class InstanceGenerateSample {
  private class Dog extends Animal {
    public Dog() {}
    
    @Override
    public String bark() {
      return "bow";
    }
  }
  
  public void getObjectsClassInstance() {
    // オブジェクトに対して getClassメソッドを呼ぶことでこのオブジェクトのクラスを表すClassクラスのインスタンスが取得できる
    // Classクラスのインスタンスの変数名は、classが予約後のためclazzを慣例的に使うことが多い
    Class<? extends Animal> clazz = new Cat().getClass();
   
    System.out.println(clazz.getName()); // reflection.sample.model.Cat

    // Class.classでClassオブジェクトを取得出来る
    Class<? extends Animal> clazz2 = Dog.class;
   
    System.out.println(clazz2.getName()); // reflection.sample.InstanceGenerateSample$Dog
    
    // 文字列からClassオブジェクトを作る
    try {
      System.out.println(Class.forName("reflection.sample.model.Cat").getName()); //reflection.sample.model.Cat
    } catch (ClassNotFoundException e) {
    }
  }
 
  // Classオブジェクトを使うと、そのオブジェクトが表すクラスの情報を取ることができる
  public void useClassObject() {
    Class<?> clazz = Cat.class;
    Class<?> array_clazz = Cat[].class;
    Class<?> inner_clazz = Dog.class;
    
    // String を返すもの
    System.out.println(clazz.getPackage());             // package reflection.sample.model
    System.out.println(clazz.getName());                // reflection.sample.model.Cat (限定名)
    System.out.println(array_clazz.getName());          // [Lreflection.sample.model.Cat; (限定名 配列の場合)
    System.out.println(inner_clazz.getName());          // reflection.sample.InstanceGenerateSample$Dog; (限定名)
    System.out.println(clazz.getSimpleName());          // Cat
    System.out.println(clazz.getCanonicalName());       // reflection.sample.model.Cat (正準名)
    System.out.println(inner_clazz.getCanonicalName()); // reflection.sample.InstanceGenerateSample.Dog (正準名)
    System.out.println(clazz.getTypeName());            // reflection.sample.model.Cat (通常はgetNameと同じ)
    System.out.println(array_clazz.getTypeName());      // reflection.sample.model.Cat[] (配列の場合)
    
    // Classを返すもの
    System.out.println(clazz.asSubclass(Cat.class)); // class reflection.sample.model.Cat
    System.out.println(clazz.getSuperclass());       // class reflection.sample.model.Animal
  }
  
  // 文字列からClassオブジェクトを作りメソッドを呼び出してみる
  public void generateInstance() {
    // トップレベルのクラスの場合
    try {
      Class<?> clazz = Class.forName("reflection.sample.model.Cat");
    
      Object object = clazz.newInstance();
      
      if (object instanceof Cat) {
        System.out.println(((Cat)object).bark()); // meow
      }
    } catch (ReflectiveOperationException e) {
      e.printStackTrace();
    }

    // 引数有りの場合
    try {
      Class<?> clazz = Class.forName("reflection.sample.model.Cat");
   
      // 引数がある場合は、引数の型を指定してConstructorオブジェクトを取得し、そのnewInstanceメソッドを引数を付けて呼び出す
      Constructor<?> constructor = clazz.getConstructor(String.class);
      Object object = constructor.newInstance("momo");

      Constructor<?>[] constructors = clazz.getConstructors();
      
      if (object instanceof Cat) {
        System.out.println(((Cat)object).getName()); // momo
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }

    // 内部クラスの場合
    try {
      Class<?> clazz = Class.forName("reflection.sample.InstanceGenerateSample$Dog");
 
      // 内部クラスの場合は Class#newInstanceメソッドは使えない
      // 内部クラスには暗黙的にnew Hoge(super)に相当するメソッドが内部的に作られるため、引数にスーパークラスを指定することでコンストラクタを取得できる
      // また、このとき内部クラスにコンストラクタが定義されていないと、コンストラクタが見つからなくなる
      // (おそらく、superクラスのコンストラクタを見に行くがsuperクラス側にnew Hoge(super)相当のコンストラクタが無いため)
      Constructor<?> constructor = clazz.getConstructor(InstanceGenerateSample.class);
      Object object = constructor.newInstance(this);
      
      if (object instanceof Dog) {
        System.out.println(((Dog)object).bark()); // meow
      }
   
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String...args) throws Exception {
    InstanceGenerateSample sample = new InstanceGenerateSample();
    
    //sample.getObjectsClassInstance();
    //sample.useClassObject();
    sample.generateInstance();
  }
}
