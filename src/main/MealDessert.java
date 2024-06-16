package main;

public class MealDessert extends Meal {
  public MealDessert(String name, int price, int duration) {
    super(name, price, duration);
  }
  
  @Override
  public String toString() {
    return "(N) " + super.getName() + " ["+ super.getPrice() + " Euro]";
  }
}