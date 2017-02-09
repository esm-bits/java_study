package reflection.sample;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import reflection.sample.model.FieldOperationTarget;

public class FieldOperationSamples {
  public void operation_instance_fields() throws Exception {
    Class<?> clazz = FieldOperationTarget.class;
    
    { // public
      Object instance = clazz.newInstance();
      Field field = clazz.getField("public_field");
      System.out.println(field.get(instance));
      field.set(instance, "public field!");
      System.out.println(field.get(instance));
    }

    { // package private
      Object instance = clazz.newInstance();
      Field field = clazz.getDeclaredField("package_private_field");
      field.setAccessible(true);
      field.setAccessible(false);
      Field field2 = clazz.getDeclaredField("package_private_field");
      System.out.println(field2.get(instance));
      field2.set(instance, "package private field!");
      System.out.println(field2.get(instance));
    }

    { // protected
      Object instance = clazz.newInstance();
      Field field = clazz.getDeclaredField("protected_field");
      field.setAccessible(true);
      System.out.println(field.get(instance));
      field.set(instance, "protected field!");
      System.out.println(field.get(instance));
    }

    { // private
      Object instance = clazz.newInstance();
      Field field = clazz.getDeclaredField("private_field");
      field.setAccessible(true);
      System.out.println(field.get(instance));
      field.set(instance, "private field!");
      System.out.println(field.get(instance));
    }
  }

  public void operation_static_fields() throws Exception {
    Class<?> clazz = FieldOperationTarget.class;
    
    { // public
      Field field = clazz.getField("public_static_field");
      System.out.println(field.get(null));
      field.set(null, "public static field!");
      System.out.println(field.get(null));
    }

    { // package private
      Field field = clazz.getDeclaredField("package_private_static_field");
      field.setAccessible(true);
      Field field2 = clazz.getDeclaredField("package_private_static_field");
      System.out.println(field2.get(null));
      field2.set(null, "package private static field!");
      System.out.println(field2.get(null));
    }

    { // protected
      Field field = clazz.getDeclaredField("protected_static_field");
      field.setAccessible(true);
      System.out.println(field.get(null));
      field.set(null, "protected static field!");
      System.out.println(field.get(null));
    }

    { // private
      Field field = clazz.getDeclaredField("private_static_field");
      field.setAccessible(true);
      System.out.println(field.get(null));
      field.set(null, "private staitc field!");
      System.out.println(field.get(null));
    }
  }
  
  public void operation_instance_final_fields() throws Exception {
    Class<?> clazz = FieldOperationTarget.class;
    
    { // public
      Object instance = clazz.newInstance();
      Field field = clazz.getField("public_final_field");
      System.out.println(field.get(instance));
      // 代入する前にAccessibleフラグを立てる必要がある
      field.setAccessible(true);
      
      System.out.println(Integer.toBinaryString(field.getModifiers()));
      System.out.println(Modifier.isFinal(field.getModifiers()));
      
      // 通常finalなフィールドへ再代入は出来ない
      // ((FieldOperationTarget)instance).public_final_field = "hoge"; // コンパイルエラーになる
      // リフレクションをつかえばfinalなフィールドの値を書き換えることが可能
      field.set(instance, "public final field!");
      System.out.println(field.get(instance));
    }

    { // package private
      Object instance = clazz.newInstance();
      Field field = clazz.getDeclaredField("package_private_final_field");
      field.setAccessible(true);
      System.out.println(field.get(instance));
      field.set(instance, "package private final field!");
      System.out.println(field.get(instance));
    }

    { // protected
      Object instance = clazz.newInstance();
      Field field = clazz.getDeclaredField("protected_final_field");
      field.setAccessible(true);
      System.out.println(field.get(instance));
      field.set(instance, "protected final field!");
      System.out.println(field.get(instance));
    }

    { // private
      Object instance = clazz.newInstance();
      Field field = clazz.getDeclaredField("private_final_field");
      field.setAccessible(true);
      System.out.println(field.get(instance));
      field.set(instance, "private final field!");
      System.out.println(field.get(instance));
    }
  }
  
  public void operation_static_final_fields() throws Exception {
    Class<?> clazz = FieldOperationTarget.class;
    
    { // public
      Object instance = clazz.newInstance();
      Field field = clazz.getField("public_static_final_field");
      System.out.println(field.get(instance));
      field.setAccessible(true);
      // setAccessibleをしても、ここで失敗する
      // 通常、Refrectionを使ってもstatic finalなフィールドは変更できない
      // field.set(null, "public static final field!");
      // System.out.println(field.get(instance));
    }

    // おまけ
    // エキセントリックな方法でstatic finalの更新をする例
    // 実際のところこの方法のお世話になることはほとんど無い筈…
    {
      Object instance = clazz.newInstance();
      Field field = clazz.getField("public_static_final_field");
      System.out.println(field.get(instance));
      field.setAccessible(true);
      
      Field modifiers = Field.class.getDeclaredField("modifiers");
      modifiers.setAccessible(true);
      // public_static_final_fieldをさすFiledインスタンスが持つmodifiersフィールドが、
      // int型のフラグ列となっているので、そのうちのFinalであることを示すフラグを落として、finalではない扱いにする。
      modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
      
      field.set(null, "public static final field!");
      System.out.println(field.get(instance));
    }
  }
  
  public static void main(String...args) throws Exception {
    //new FieldOperationSamples().operation_instance_fields();
    new FieldOperationSamples().operation_static_fields();
    //new FieldOperationSamples().operation_instance_final_fields();
    //new FieldOperationSamples().operation_static_final_fields();
  }
}
