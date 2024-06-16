package main;

public abstract class Meal {
  private String name;
  private int price;
  private int duration;

  public Meal(String name, int price, int duration) {
    this.name = name;
    this.price = price;
    this.duration = duration;
  }

  public String getName() {
    return this.name;
  }
  public float getPrice() {
    return ((float) this.price) / 100;
  }
  public int getDuration() {
    return this.duration;
  }

  public abstract String toString();
}