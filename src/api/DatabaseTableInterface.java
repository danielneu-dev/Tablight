package api;

import java.util.ArrayList;

import main.Table;

public interface DatabaseTableInterface {
  ArrayList<Table> getTables();
  ArrayList<Table> getFreeTables();
  void setTableOccupied(int tableIndex, boolean occupied);
}
