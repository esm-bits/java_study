package functionalinterface.sample;

public class HandmadeFunctionalSample {

  public static void main(String...arg) {
    TriFunction<Integer, Integer, Integer, Integer> addTriple = (x, y, z) -> x + y + z;
    System.out.println(addTriple.apply(1, 2, 3));
    System.out.println(addTriple.apply(4, 5, 6));

    TriFunction<String, String, Integer, String> triFunction = ((String arg1, String arg2, Integer arg3) -> {
      StringBuilder sb = new StringBuilder();
        sb.append(arg1 + arg2);
        for(int i = 0; i < arg3; i++ ) {
          sb.append("!");
        }
        return sb.toString();
      });
    System.out.println(triFunction.apply("@nhashiさん", "サンプル書くの手伝ってください", 10));
  }
}
