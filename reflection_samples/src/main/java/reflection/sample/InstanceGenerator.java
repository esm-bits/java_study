package reflection.sample;

import java.io.InputStream;
import java.util.Properties;

import reflection.sample.model.Animal;

// ちょっと実用的な例[WIP]
// ファイルから読み込んだ文字列からオブジェクトを生成して返す
public class InstanceGenerator {
  
  public Object generate(Properties props) throws Exception {
    String class_name = props.getProperty("CLASS_NAME");
    
    Class<?> clazz = Class.forName(class_name);
    Object obj = clazz.newInstance();
    
    return obj;
  }
  
  public static void main(String...args) throws Exception {
    Properties props = new Properties();
    try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("class_name.properties")) {
      props.load(inputStream);
    }
    
    Object obj = new InstanceGenerator().generate(props);
    
    System.out.println(((Animal)obj).bark());
  }
}
