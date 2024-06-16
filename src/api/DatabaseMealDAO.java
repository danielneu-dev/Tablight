package api;

import java.sql.*;
import java.util.ArrayList;

import main.Meal;
import main.MealDessert;
import main.MealMain;
import main.MealStarter;

public class DatabaseMealDAO extends DatabaseDAO implements DatabaseMealInterface {
  public ArrayList<Meal> getMenuItems() {
    ArrayList<Meal> menuItems = new ArrayList<Meal>();

    try {
      Statement a = conn.createStatement();
      ResultSet select = a.executeQuery("SELECT * FROM menuItem INNER JOIN menuItemTypes ON menuItem.type = menuItemTypes.name ORDER BY menuItemId ASC");

      while (select.next()) {
        String name = select.getString("name");
        int price = select.getInt("price");
        int duration = select.getInt("duration");
        String type = select.getString("type");

        Meal tempMenuItem;

        if(type.equals("vorspeise")) {
          tempMenuItem = new MealStarter(name, price, duration);
        }
        else if(type.equals("hauptspeise"))
          tempMenuItem = new MealMain(name, price, duration);
        else
          tempMenuItem = new MealDessert(name, price, duration);

        menuItems.add(tempMenuItem);
      }
    } catch (SQLException e) {
      Logger.error("SQL Exception: " + e.getMessage());
    }

    return menuItems;
  }
}