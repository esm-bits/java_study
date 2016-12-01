package enum_.samples;

// Switch文でenumを使うサンプル
public class SwitchCaseSamples {

  // 1.4 以前のコードのサンプル
  // greetingメソッドは、出身国(のコード)を受け取りその国の言葉で挨拶を返す
  private class OldSwitchCase {
    public static final int JAPANESE = 0;
    public static final int AMERICAN = 1;
    public static final int BRITISH  = 2;
    public static final int GERMAN   = 3;
    public static final int FRENCH   = 4;
    
    public String greeting(int country) {
      String greet = null;
     
      // 1.4以前はswitch文にはプリミティブしか渡せなかった
      switch(country){
        case JAPANESE:
          greet =  "こんにちは！";
          break;
        case AMERICAN:
        case BRITISH:
          greet =  "Hello!";
          break;
        case GERMAN:
          greet =  "Guten tag!";
          break;
        case FRENCH:
          greet =  "Bonjour!";
          break;
        default:
          // 何人か分からないのでエスペラント語で
          greet =  "Bonan tagon!";
      }
      
      return greet;
    }
  }

  // 5 以降のコードのサンプル
  // greetingメソッドは、出身国(のコード)を受け取りその国の言葉で挨拶を返す
  
  // 内部クラスの中に欠けないのでここに書く (書き方知ってる方PRを……)
  // OldSwitchCaseのフィールドと違うものであることを明示するために、要素名の末尾に_を付けている
  public enum Country {
    JAPANESE_,
    AMERICAN_,
    BRITISH_,
    GERMAN_,
    FRENCH_,
    OTHER_,
    // 豆知識: 最後の要素の末尾を,で終わっても怒られない(確かJava7で入ったような…?)
    // ただし、この後に属性などを書こうとすると怒られる
  }
  
  private class NewSwitchCase {
    public String greeting(Country country) {
      String greet = null;
     
      switch(country){
        case JAPANESE_:  // 通常はCountry.JAPANESE_ の様に参照しますが、switch文の中では型を省略できる
          greet =  "こんにちは！";
          break;
        case AMERICAN_:
        case BRITISH_:
          greet =  "Hello!";
          break;
        case GERMAN_:
          greet =  "Guten tag!";
          break;
        case FRENCH_:
          greet =  "Bonjour!";
          break;
        default:
          // 何人か分からないのでエスペラント語で
          greet =  "Bonan tagon!";
      }
      
      return greet;
    }
  }
  
  // 上記はサンプルとしてSwitch文で書いているが、本来はこういうケースはenumにメソッドを持たせる
  public enum Country2 {
    JAPANESE__("こんにちは!"),
    AMERICAN__("Hello!"),
    BRITISH__(Country2.AMERICAN__.greet),
    GERMAN__("Guten tag!"),
    FRENCH__("Bonjour!"),
    OTHER__("Bonan tagon!");
   
    private String greet;
    
    private Country2(String greet) {
      this.greet = greet;
    }
    
    public String greeting() {
      return greet;
    }
  }
  
  public static void main(String...args) {
    OldSwitchCase oldCase = new SwitchCaseSamples().new OldSwitchCase();
    System.out.println(oldCase.greeting(OldSwitchCase.JAPANESE)); // こんにちは！
    System.out.println(oldCase.greeting(OldSwitchCase.AMERICAN)); // Hello!
    System.out.println(oldCase.greeting(OldSwitchCase.BRITISH));  // Hello!
    System.out.println(oldCase.greeting(OldSwitchCase.GERMAN));   // Guten tag!
    System.out.println(oldCase.greeting(OldSwitchCase.BRITISH));  // Bonjour!
    System.out.println(oldCase.greeting(100));                    // Bonan tagon!

    NewSwitchCase newCase = new SwitchCaseSamples().new NewSwitchCase();
    System.out.println(newCase.greeting(Country.JAPANESE_)); // こんにちは！
    System.out.println(newCase.greeting(Country.AMERICAN_)); // Hello!
    System.out.println(newCase.greeting(Country.BRITISH_));  // Hello!
    System.out.println(newCase.greeting(Country.GERMAN_));   // Guten tag!
    System.out.println(newCase.greeting(Country.BRITISH_));  // Bonjour!
    System.out.println(newCase.greeting(Country.OTHER_));    // Bonan tagon!

    System.out.println(Country2.JAPANESE__.greeting()); // こんにちは！
    System.out.println(Country2.AMERICAN__.greeting()); // Hello!
    System.out.println(Country2.BRITISH__.greeting());  // Hello!
    System.out.println(Country2.GERMAN__.greeting());   // Guten tag!
    System.out.println(Country2.BRITISH__.greeting());  // Bonjour!
    System.out.println(Country2.OTHER__.greeting());    // Bonan tagon!
  }
}
