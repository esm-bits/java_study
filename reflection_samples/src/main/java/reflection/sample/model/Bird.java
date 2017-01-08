package reflection.sample.model;

public class Bird extends Animal {
  private final String name;
  
  public Bird(){
    this.name = "Tama";
  };
  
  public Bird(String name) {
    this.name = name;
  }
  
  @Override
  public String bark() {
    return "chick";
  }
  
  public String getName() {
    return this.name;
  }
}
