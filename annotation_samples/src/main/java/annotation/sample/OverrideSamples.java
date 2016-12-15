package annotation.sample;

public class OverrideSamples {
  private interface Breedable {
    default public String breed(String food){
      return "I like " + food;
    };
  }

  // オーバーライドする例
  // 1.4 以前は、名前と引数を一致させてオーバラードしていた
  private class Cat implements Breedable{
    public String breed(String food){
      return  food + " is good!";
    }
  }

  // オーバーライド出来ていない例
  // ここではメソッド名をtypoしているため、オーバーライドせずに新しいメソッドを作ってしまっている
  private class Cat2 implements Breedable{
    public String bread(String food){
      return  food + " is good!";
    }
  }

  // オーバーライド出来ていない例
  // ここではメソッド名をtypoしているため、オーバーライドせずに新しいメソッドを作ってしまっている
  private class Cat3 implements Breedable{
    public String bread(String food, int num){ // 実はinterface側が変わったのかもしれない
      return  food + " is good!";
    }
  }

  // アノテーション付きでオーバーライドする例
  private class Cat4 implements Breedable{
    // @Overrideアノテーションを付けて、直後のメソッド定義がオーバーライドであることをコンパイラに教えている
    // @Overrideアノテーションが付いているにも関わらず、インターフェイスやスーパークラスに該当するメソッドがない場合はコンパイルエラーになる
    @Override
    public String breed(String food){
      return  food + " is good!";
    }
  }

  // これはコンパイルエラーになる
  // private class Cat5 implements Breedable{
  //   @Override
  //   public String bread(String food){
  //     return  food + " is good!";
  //   }
  // }

  // これもコンパイルエラーになる
  // private class Cat6 implements Breedable{
  //   @Override
  //   public String breed(String food, int num){
  //     return  food + " is good!";
  //   }
  // }

  public static void main(String...args) {
    System.out.println(((Breedable)new OverrideSamples().new Cat()).breed("Fish"));
    System.out.println(((Breedable)new OverrideSamples().new Cat2()).breed("Fish"));
    System.out.println(((Breedable)new OverrideSamples().new Cat3()).breed("Fish"));
    System.out.println(((Breedable)new OverrideSamples().new Cat4()).breed("Fish"));
  }
}
