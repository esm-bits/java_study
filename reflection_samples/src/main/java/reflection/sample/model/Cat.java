package reflection.sample.model;

public class Cat extends Animal {
  private final String name;
  
  public Cat(){
    this.name = "Tama";
  };
  
  public Cat(String name) {
    this.name = name;
  }
  
  @Override
  public String bark() {
    return "meow";
  }
  
  public String getName() {
    return this.name;
  }
}
