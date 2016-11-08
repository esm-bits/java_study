package generics.sample.model;

public abstract class Cat extends Animal {

  protected int weight;
  protected String name;

  public String bark() {
    return "meow";
  }

  @Override
  public int getWeight() {
    return weight;
  }

  public String getName() {
    return name;
  }
}
