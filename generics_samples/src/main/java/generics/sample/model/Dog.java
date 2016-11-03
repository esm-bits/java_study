package generics.sample.model;

public abstract class Dog extends Animal {
  protected int weight;
  protected String name;

  @Override
  public int getWeight() {
    return weight;
  }

  public String getName() {
    return name;
  }
}
