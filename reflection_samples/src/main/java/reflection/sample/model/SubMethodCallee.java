package reflection.sample.model;

public class SubMethodCallee extends MethodCallee {
  @Override
  protected String protectedMethod(String str) {
    return str + " protected 🐢";
  }
}
