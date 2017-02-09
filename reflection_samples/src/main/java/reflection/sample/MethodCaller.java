package reflection.sample;

import java.lang.reflect.Method;

import reflection.sample.model.MethodCallee;
import reflection.sample.model.SubMethodCallee;

public class MethodCaller {
  // publicなインスタンスメソッドの実行
  public void invokePublicMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    Method method = clazz.getMethod("publicMethod", String.class);
    Object result = method.invoke(clazz.newInstance(), "Invoke");

    System.out.println(result);
  }

  // publicなクラスメソッドの実行
  public void invokePublicStaticMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    Method method = clazz.getMethod("publicStaticMethod", String.class);
    Object result = method.invoke(null, "Invoke");

    System.out.println(result);
  }

  // package privateなインスタンスメソッドの実行
  public void invokePackagePrivateMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    // MethodCalleeの非publicなメソッドはgetMethodできない
    //Method method = clazz.getMethod("packagePrivateMethod", String.class);

    Method method = clazz.getDeclaredMethod("packagePrivateMethod", String.class);

    // package private
    // なメソッドを実行する時はsetAccessibleメソッドを呼び出して、インスタンスのaccessibleフラグを立てる必要がある
    // また、SecurityManagerが設定されている場合は適宜ポリシーファイルを編集する必要がある
    // System.setSecurityManager(new SecurityManager());
    method.setAccessible(true);

    Object result = method.invoke(clazz.newInstance(), "Invoke");

    System.out.println(result);
  }

  String packagePrivateMethod(String str) {
    return str + " package private 🐳";
  }

  // package privateなインスタンスメソッドの実行
  public void invokePackagePrivateMethod2() throws Exception {
    Class<?> clazz = this.getClass();

    Method method = clazz.getDeclaredMethod("packagePrivateMethod", String.class);

    // 自クラスのpackage privateメソッドのため、setAccessibleを使わずにinvoke出来る
    Object result = method.invoke(this, "Invoke");

    System.out.println(result);
  }

  // package privateなクラスソッドの実行
  public void invokePackagePrivateStaticMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    Method method = clazz.getDeclaredMethod("packagePrivateStaticMethod", String.class);

    method.setAccessible(true);

    Object result = method.invoke(null, "Invoke");

    System.out.println(result);
  }

  // protectedなインスタンスメソッドの実行
  public void invokeProtectedMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    Method method = clazz.getDeclaredMethod("protectedMethod", String.class);

    method.setAccessible(true);

    Object result = method.invoke(clazz.newInstance(), "Invoke");

    System.out.println(result);
  }

  // 継承関係がある時子供のprotectedMethodを実行する
  public void invokeProtectedMethod2() throws Exception {
    Class<?> clazz = SubMethodCallee.class;

    Method method = clazz.getDeclaredMethod("protectedMethod", String.class);

    method.setAccessible(true);

    Object result = method.invoke(clazz.newInstance(), "Invoke");

    System.out.println(result);
  }

  // protectedなクラスメソッドの実行
  public void invokeProtectedStaticMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    Method method = clazz.getDeclaredMethod("protectedStaticMethod", String.class);

    method.setAccessible(true);

    Object result = method.invoke(null, "Invoke");

    System.out.println(result);
  }

  // privateなインスタンスメソッドの実行
  public void invokePrivateMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    Method method = clazz.getDeclaredMethod("privateMethod", String.class);

    method.setAccessible(true);

    Object result = method.invoke(clazz.newInstance(), "Invoke");

    System.out.println(result);
  }

  // privateなクラスメソッドの実行
  public void invokePrivateStaticMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    Method method = clazz.getDeclaredMethod("privateStaticMethod", String.class);

    method.setAccessible(true);

    Object result = method.invoke(null, "Invoke");

    System.out.println(result);
  }

  // 引数の無いインスタンスメソッドの実行
  public void invokeNoArgsMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    // 引数がない場合はgetMethod第二引数は不要
    Method method = clazz.getMethod("noArgsMethod");
    // または空のClass配列を指定する(昔の書き方)
    // Method method = clazz.getMethod("noArgsMethod", new Class<?>[] {});
    // nullでも良い
    // Method method = clazz.getMethod("noArgsMethod", (Class<?>[])null);

    // 引数が無い場合はinvokeの第二引数は不要
    Object result = method.invoke(clazz.newInstance());
    // または空のObject配列を指定する(昔の書き方)
    // Object result = method.invoke(clazz.newInstance(), new Object[] {});
    // nullでも可
    // Object result = method.invoke(clazz.newInstance(), (Object[])null);

    System.out.println(result);
  }

  // 可変長引数を持つインスタンスメソッドの実行
  public void invokeVariableLengthArgsMethod() throws Exception {
    Class<?> clazz = MethodCallee.class;

    Method method = clazz.getMethod("variableLengthArgsMethod", String.class, String[].class);
    // 可変長引数の場合は、引数の型を配列で指定する
    //Method method = clazz.getMethod("variableLengthArgsMethod", new Class<?>[] { String.class, String[].class });

    Object result = method.invoke(clazz.newInstance(), "A", new String[] { "B", "C" });

    System.out.println(result);
  }

  public static void main(String... args) throws Exception {
    new MethodCaller().invokePublicMethod();
    new MethodCaller().invokePublicStaticMethod();

    new MethodCaller().invokePackagePrivateMethod();
    new MethodCaller().invokePackagePrivateMethod2();
    new MethodCaller().invokePackagePrivateStaticMethod();

    new MethodCaller().invokeProtectedMethod();
    new MethodCaller().invokeProtectedMethod2();
    new MethodCaller().invokeProtectedStaticMethod();

    new MethodCaller().invokePrivateMethod();
    new MethodCaller().invokePrivateStaticMethod();

    new MethodCaller().invokeNoArgsMethod();
    new MethodCaller().invokeVariableLengthArgsMethod();
  }
}
