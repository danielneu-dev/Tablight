import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.MenuAdmin;
import main.MenuTable;
import api.Logger;
import api.Pin;

public class Application {
  public static void main(String[] args) {
    while(!Pin.check());

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int option = 0;

    do {
      showMenu();
      try {
        String input = reader.readLine().trim(); // Eingabe bereinigen
        option = Integer.parseInt(input);
        Logger.info("Application.main | Input: " + option);
        
        switch (option) {
          case 1:
            while(!Pin.check());
            Logger.info("Erstelle Admin instanz");
            MenuAdmin m = new MenuAdmin();
            m.manageMenu();
            break;
          case 2:
            MenuTable mt = new MenuTable();
            if (mt.getTableAmount() == 0) {
              System.out.println("Alle Tische sind belegt.");
              break;
            }
            mt.menu();
            break;
          case 3:
            Logger.info("Das Programm ist beendet");
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

    try {
      reader.close(); // BufferedReader schließen
    } catch (IOException e) {
      Logger.error(e.getMessage());
    }
  }

  public static void showMenu() {
    System.out.println("[1]: Admin ");
    System.out.println("[2]: Tisch ");
    System.out.println("[3]: Beenden");
    System.out.print("> ");
  }
}