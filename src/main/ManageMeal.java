package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import api.DatabaseMealDAO;
import api.DatabaseMealInterface;
import api.Logger;

public class ManageMeal {
    private DatabaseMealInterface dbm = new DatabaseMealDAO();
    private ArrayList<Meal> mealList;

    public ManageMeal() {
      mealList = dbm.getMenuItems();
    }

    public void addMeal(Meal m) {
      mealList.add(m);
      Logger.info("Gericht hinzugefügt: " + m); // Debug-Ausgabe
    }

    public void removeMealByPos(int pos) {
      if (pos >= 0 && pos < mealList.size()) {
        Meal removedMeal = mealList.remove(pos);
        System.out.println("Gericht entfernt: " + removedMeal);
      } else {
        System.out.println("Ungültige Position: " + pos);
      }
    }

    public void removeMealByName(String name) {
      Meal aktuell = null;
      for (Meal m : mealList) {
        if (m.getName().equalsIgnoreCase(name)) {
          aktuell = m;
          mealList.remove(m);
          break; // Stop the loop after finding the first match
        }
      }
      if (aktuell != null) {
        System.out.println("\n" + aktuell.getName() + " wurde gelöscht");
      } else {
        System.out.println(name + " ist nicht gefunden");
      }
    }

    public void printMeals() {
      if (mealList.isEmpty()) {
        System.out.println("Keine Gerichte vorhanden.");
        return;
      }

      for (Meal m : mealList) {
        System.out.println(m);
      }

      System.out.println();
    }

    public void manageDishes() {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      int option = 0;

      do {
        show();
        try {
          String input = reader.readLine().trim(); // Eingabe bereinigen
          option = Integer.parseInt(input);
          Logger.info("AdminToMeal | Input: " + option);

          switch (option) {
            case 1:
              String type = "";
              while (true) {
                System.out.println("\nBitte geben Sie den Typ ein (v für Vorspeise, h für Hauptspeise, n für Nachspeise):");
                type = reader.readLine().trim();

                if (type.equalsIgnoreCase("v") || type.equalsIgnoreCase("h") || type.equalsIgnoreCase("n")) {
                  break;
                } else {
                  System.out.println("Ungültiger Typ. Bitte geben Sie eine dieser Optionen ein: v, h, n");
                }
              }

              System.out.print("\nName: ");
              String name = reader.readLine().trim();

              System.out.print("Preis (cent): ");
              int price;
              while (true) {
                try {
                  price = Integer.parseInt(reader.readLine().trim());
                  break;
                } catch (NumberFormatException e) {
                  System.out.println("Ungültiger Preis. Bitte eine gültige Zahl eingeben.");
                }
              }

              System.out.print("Dauer in Minuten: ");
              int duration;
              while (true) {
                try {
                  duration = Integer.parseInt(reader.readLine().trim());
                  break;
                } catch (NumberFormatException e) {
                  System.out.println("Ungültige Dauer. Bitte eine gültige Zahl eingeben.");
                }
              }

              switch (type.toLowerCase()) {
                case "v":
                  Logger.info("Vorspeise hinzugefügt");
                  addMeal(new MealStarter(name, price, duration));
                  break;
                case "h":
                  Logger.info("Hauptspeise hinzugefügt");
                  addMeal(new MealMain(name, price, duration));
                  break;
                case "n":
                  Logger.info("Nachspeise hinzugefügt");
                  addMeal(new MealDessert(name, price, duration)); // Corrected class type
                  break;
              }
              System.out.println("\n" + name + " wurde zum Buffet hinzugefügt.");
              break;

            case 2:
                System.out.print("\nGericht löschen\nName: ");
                String nameToDelete = reader.readLine().trim();
                removeMealByName(nameToDelete);
                break;

            case 3:
                System.out.println("\n-- Alle Gerichte anzeigen --");
                printMeals();
                break;

            case 4:
                Logger.info("Das Programm ist beendet");
                break;

            default:
                    System.out.println("Bitte geben Sie eine Zahl zwischen 1 und 3\n" +
                            "Um das Programm zu beenden, drücken Sie 4");
                    break;
          }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe. Bitte geben Sie eine ganze Zahl ein.");
        }
      } while (option != 4);
    }

    public static void show() {
      System.out.println("[1]: Gericht hinzufügen ");
      System.out.println("[2]: Gericht entfernen ");
      System.out.println("[3]: Alle Gerichte anzeigen ");
      System.out.println("[4]: Zurück");
      System.out.print("> ");
    }
}
