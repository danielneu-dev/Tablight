package main;

import api.DatabaseMealDAO;
import api.DatabaseMealInterface;
import api.Logger;
import api.Pin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuTableSession {
  private int tableIndex;
  private DatabaseMealInterface db = new DatabaseMealDAO();

  private ArrayList<Meal> bill = new ArrayList<Meal>();

  public MenuTableSession(int tableIndex) {
    this.tableIndex = tableIndex;
  }

  public void menu() {
    BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    int option = 0;

    do {
      System.out.println("\nTisch "+this.tableIndex+" - Bestellungen: " + this.bill.size() + " - Preis: " + this.getPrice() + " Euro");
      System.out.println("[1]: Bestellung aufnehmen");
      System.out.println("[2]: Tischsitzung beenden");
      System.out.print("> ");

      try {
        option = Integer.parseInt(scanner.readLine());

        switch (option) {
          case 1:
            this.order();
            break;
          case 2:
            this.printBill();
            while(!Pin.check());
            System.out.println();
            Logger.info("Tischsitzung beendet\n");
            break;
          default:
            System.out.println("Bitte geben Sie eine Zahl zwischen 1 und 2 ein\n");
            break;
        }

      } catch(NumberFormatException e) {
        System.out.println("keine gültige Eingabe, bitte geben Sie eine Zahl zwischen [1-2]");
      } catch(Exception e) {
        Logger.error(e.getMessage());
      }
      
    } while (option != 2);
  }

  private void order() {
    System.out.println("\n-- Alle Gerichte -- (V)orspeise, (H)auptspeise, (N)achspeise");
    ArrayList<Meal> meals = db.getMenuItems();

    for (int mealCount = 0; mealCount < meals.size(); mealCount++) {
      System.out.print("["+mealCount+"] ");
      System.out.println(meals.get(mealCount));
    }


    BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    int option = 0;
    System.out.print("> ");

    try {
      option = Integer.parseInt(scanner.readLine());

      if(option > meals.size() || option < 0) {
        System.out.println("\nKeine gültige Eingabe");
        return;
      }

      Meal selectedMeal = meals.get(option);
      this.bill.add(selectedMeal);
      System.out.println(selectedMeal.getName() + " wird vorraussichtlich in " + selectedMeal.getDuration() + " minuten eintreffen");

    } catch(NumberFormatException e) {
      System.out.println("Keine gültige Eingabe");
      Logger.info("Keine gültige Eingabe");
    } catch(Exception e) {
      Logger.error(e.getMessage());
    }
  }

  private int getPrice() {
    int sum = 0;

    for (int i = 0; i < this.bill.size(); i++) {
      sum += this.bill.get(i).getPrice();
    }

    return sum;
  }
  private void printBill() {
    System.out.println("\n-- Kassenzettel --");

    for (int i = 0; i < this.bill.size(); i++) {
      Meal m = this.bill.get(i);
      System.out.println(m);
    }

    System.out.println("Bestellungen: " + this.bill.size() + " - Preis: " + this.getPrice() + " Euro");
  }
}
