package generics.sample.model;

public class Somali extends Cat implements Breedable {
  public Somali(String name, int weight) {
    this.name = name;
    this.weight = weight;
  }

  public Somali() {
  }

  @Override
  public String bark() {
    return "meowmeow";
  }
}
