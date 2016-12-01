package enum_.samples;

import java.util.Arrays;

// enumの要素ごとにメソッドが定義できることのサンプル
public class DifferentBehaviorSamples {
  // Itemizationは箇条書きのフォーマット形式の列挙型で、
  // Stringの配列を変換するformatメソッドを持っている。
  private enum Itemization {
    // この要素のformatは以下のような文字列を返す
    // <ul>
    //   <li>Java5</li>
    //   <li>Java6</li>
    //   <li>Java7</li>
    //   <li>Java8</li>
    // </ul>
    HTML {
      public String format(String...lines) {
        StringBuilder sb = new StringBuilder("<ul>").append("\n");
        for(String line : lines) {
          sb.append("  <li>").append(line).append("</li>").append("\n");
        } 
        sb.append("</ul>");
      
        return new String(sb);
      }
    },
    
    // この要素のformatは以下のような文字列を返す
    // ["Java5", "Java6", "Java7", "Java8"]
    JSON {
      public String format(String...lines) {
        StringBuilder sb = new StringBuilder("[");
        if(lines.length > 0) {
          for(String line : lines) {
            sb.append("\"").append(line).append("\", ");
          }
          sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
      
        return new String(sb);
      }
    },
   
    // この要素のformatは以下のような文字列を返す
    // - Java5
    // - Java6
    // - Java7
    // - Java8
    MARK_DOWN {
      public String format(String...lines) {
        StringBuilder sb = new StringBuilder();
        for(String line : lines) {
          sb.append("- ").append(line).append("\n");
        }
      
        return new String(sb);
      }
    };
   
    // 各要素が持つべきメソッドは抽象メソッドとして定義する
    public abstract String format(String...args);
  }
 
  // いつものおまけ
  private enum Omake {
    HTML {
      public String format(String...lines) {
        return "<ul>\n" + Arrays.asList(lines).stream().reduce("", (buf, line) -> buf + "  <li>" + line + "</li>\n") + "</ul>";
      }
    },
    
    JSON {
      public String format(String...lines) {
        return "[" + Arrays.asList(lines).stream().reduce("", (buf, line) -> buf + "\"" + line + "\", ").replaceFirst(", $", "") + "]";
      }
    },
    
    MARK_DOWN {
      public String format(String...lines) {
        return Arrays.asList(lines).stream().reduce("", (buf, line) -> buf + "- " + line + "\n");
      }
    };
    
    public abstract String format(String...args);
  }
 
  public static void main(String...args) {
    System.out.println(Itemization.HTML.format("Java5", "Java6", "Java7", "Java8"));
    System.out.println(Itemization.JSON.format("Java5", "Java6", "Java7", "Java8"));
    System.out.println(Itemization.MARK_DOWN.format("Java5", "Java6", "Java7", "Java8"));

    System.out.println(Omake.HTML.format("Java5", "Java6", "Java7", "Java8"));
    System.out.println(Omake.JSON.format("Java5", "Java6", "Java7", "Java8"));
    System.out.println(Omake.MARK_DOWN.format("Java5", "Java6", "Java7", "Java8"));
  }
}
