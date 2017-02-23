package reflection.sample;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import reflection.sample.model.Annotated;

public class AnnotatedSamples {
  public void getFiledAnnotation() throws Exception {
    Class<?> clazz = Annotated.class;
    
    Field field = clazz.getField("foo");
    Annotation[] anos = field.getAnnotations();
    
    for(Annotation ano : anos) {
      System.out.println(ano); 
    }
  }
  
  public static void main(String...args) throws Exception{
    new AnnotatedSamples().getFiledAnnotation();
  }
}
