package main;

public class MealMain extends Meal {
  public MealMain(String name, int price, int duration) {
    super(name, price, duration);
  }

  @Override
  public String toString() {
    return "(H) " + super.getName() + " ["+ super.getPrice() + " Euro]";
  }
}