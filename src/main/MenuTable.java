package main;

import api.Logger;
import api.DatabaseTableDAO;
import api.DatabaseTableInterface;
import api.Pin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuTable {
  private ArrayList<Table> tables;
  private DatabaseTableInterface db;

  public MenuTable() {
    db = new DatabaseTableDAO();
    this.tables = db.getFreeTables();
  }

  public int getTableAmount() {
    return tables.size();
  }

  public void menu() {
    while(!Pin.check());
    BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    boolean tableFound = false;
    System.out.println("---------------------------------\n" +
        "Verfügbare Tischnummer(n):");

    for (Table t : tables) {
      System.out.println("["+t.tableIndex+"]");
    }

    
    System.out.print("> ");
    
    int tableIndex = 0;
    //TODO: Do while here
    try {
      tableIndex = Integer.parseInt(scanner.readLine());
    } catch (NumberFormatException e) {
      System.out.println("keine gültige eingabe");
    } catch (IOException e) {
      Logger.error(e.getMessage());
    }

    for (Table t : tables) {
      if (tableIndex == t.tableIndex) {
        db.setTableOccupied(tableIndex, true);

        Logger.info("Tisch " + tableIndex + " wurde belegt.");
        tableFound = true;
        break;
      }
    }

    if (tableFound) {
      MenuTableSession mts = new MenuTableSession(tableIndex);
      mts.menu();
      db.setTableOccupied(tableIndex, false);
    } else {
      Logger.info("Tisch " + tableIndex + " existiert nicht oder ist bereits belegt.");
    }
  }
}

