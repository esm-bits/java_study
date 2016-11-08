package generics.sample.model;

public class Abyssinian extends Cat implements Breedable {
  public Abyssinian(String name, int weight) {
    this.name = name;
    this.weight = weight;
  }

  public Abyssinian() {}

  @Override
  public String bark() {
    return "meow!!!";
  }
}
