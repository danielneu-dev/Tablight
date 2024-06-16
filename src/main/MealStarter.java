package main;

public class MealStarter extends Meal {
  public MealStarter(String name, int price, int duration) {
    super(name, price, duration);
  }

  @Override
  public String toString() {
    return "(V) " + super.getName() + " ["+ super.getPrice() + " Euro]";
  }
}