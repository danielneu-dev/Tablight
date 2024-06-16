package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import api.DatabaseMealDAO;
import api.DatabaseTableDAO;
import api.DatabaseTableInterface;
import api.DatabaseMealInterface;
import api.Logger;

public class MenuAdmin {
  private DatabaseMealInterface dbm = new DatabaseMealDAO();
  private DatabaseTableInterface dbt = new DatabaseTableDAO();
  private ArrayList<Meal> mealList;;
  private ArrayList<Table>tableList;

  public MenuAdmin() {
    mealList = dbm.getMenuItems();
    tableList = dbt.getTables();
  }
  
  public void addMeal(Meal m) {
    mealList.add(m);
  }
  public void removeMeal(int pos) {
    mealList.remove(pos);
  }
  public void printMeals() {
    for(Meal m:mealList){
      System.out.println(m);
    }
    System.out.println();
  }
  
  //TODO: change into db
  public void addTable(Table t) {
    tableList.add(t);
  }
  public void removeTable(int pos) {
   tableList.remove(pos);
  }

  public void makeTablefree() {
    for(Table t : tableList) {
      t.occupied = false;
    }
  }
  

  public void manageMenu() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int option = 0;

    do {
      showTasks();
      try {
        String input = reader.readLine().trim(); // Eingabe bereinigen
        option = Integer.parseInt(input);
        Logger.info("MenuAdmin.manageMenu | Input: " + option);
        
        switch (option) {
          case 1:
            System.out.println("\n-- Gericht verwalten --");
            ManageMeal m = new ManageMeal();
            m.manageDishes();
            break;
          case 2:
            System.out.println("Tische Verwalten");
            //TODO: mach
            break;
          case 3:
            break;
          default:
            System.out.println("Bitte geben Sie eine Zahl zwischen 1 und 2\n" +
                "Um das Programm zu beenden, drücken Sie 3");
            break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (NumberFormatException e) {
        System.out.println("Ungültige Eingabe. Bitte geben Sie eine ganze Zahl ein.");
      }
    } while (option != 3);
  }
  
  public static void showTasks() {
    System.out.println("[1]: Gerichte verwalten ");
    System.out.println("[2]: Tische verwalten");
    System.out.println("[3]: Zurück");
    System.out.print("> ");
  }
}
