package api;

import java.sql.*;
import java.util.ArrayList;

import main.Table;

public class DatabaseTableDAO extends DatabaseDAO implements DatabaseTableInterface {
  public ArrayList<Table> getTables() {
    ArrayList<Table> tables = new ArrayList<Table>();

    try {
      Statement a = conn.createStatement();
      ResultSet select = a.executeQuery("SELECT * FROM tableList");

      while (select.next()) {
        int tableIndex = select.getInt("tableIndex");
        boolean occupied = select.getBoolean("occupied");

        Table tempTable = new Table(tableIndex, occupied);

        tables.add(tempTable);
      }
    } catch (SQLException e) {
      Logger.error("SQL Exception: " + e.getMessage());
    }

    return tables;
  }

  public ArrayList<Table> getFreeTables() {
    ArrayList<Table> tables = new ArrayList<Table>();

    try {
      Statement a = conn.createStatement();
      ResultSet select = a.executeQuery("SELECT * FROM tableList WHERE occupied = 0");

      while (select.next()) {
        int tableIndex = select.getInt("tableIndex");
        boolean occupied = select.getBoolean("occupied");

        Table tempTable = new Table(tableIndex, occupied);

        tables.add(tempTable);
      }
    } catch (SQLException e) {
      Logger.error("SQL Exception: " + e.getMessage());
    }

    return tables;
  }

  public void setTableOccupied(int tableIndex, boolean occupied) {
    try {
      PreparedStatement update = conn.prepareStatement("UPDATE tableList SET occupied = ? WHERE tableIndex = ?");
      update.setBoolean(1, occupied);
      update.setInt(2, tableIndex);
      update.executeUpdate();
    } catch (SQLException e) {
      System.err.println("SQL Exception: " + e.getMessage());
    }
  }

}