package reflection.sample.model;

import java.util.Arrays;

public class MethodCallee {

  public static String publicStaticMethod(String str) {
    return str + " public static 🐶";
  }

  public String publicMethod(String str) {
    return str + " public 🐑";
  }

  static String packagePrivateStaticMethod(String str) {
    return str + " package private static 🐰";
  }

  String packagePrivateMethod(String str) {
    return str + " package private 🐠";
  }

  protected static String protectedStaticMethod(String str) {
    return str + " protected static 🐴";
  }

  protected String protectedMethod(String str) {
    return str + " protected 🐘";
  }

  @SuppressWarnings("unused")
  private static String privateStaticMethod(String str) {
    return str + " protected static 🐮";
  }

  @SuppressWarnings("unused")
  private String privateMethod(String str) {
    return str + " private 😺";
  }

  public String noArgsMethod() {
    return "No args 🐭";
  }

  public String variableLengthArgsMethod(String arg, String... args) {
    return arg + " : " + Arrays.stream(args).map(x -> x + " 🐾 ").reduce("", (accum, string) -> accum + string);
  }
}
