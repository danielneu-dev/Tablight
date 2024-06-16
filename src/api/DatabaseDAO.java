package api;

import java.sql.*;

public class DatabaseDAO {
  private String url = PropertiesInfo.getProperties("databaseUrl");
  private String user = PropertiesInfo.getProperties("databaseUser");
  private String password = PropertiesInfo.getProperties("databasePassword");

  protected static Connection conn = null;

  public DatabaseDAO() {
    if (conn == null) {
      try {
        conn = DriverManager.getConnection(url, user, password);
        if (conn == null) {
          Logger.error("Failed database connection");
          return;
        }

        Logger.log("Connected to database!");

      } catch (SQLException e) {
        Logger.error("Error whilst connection to database: " + e.getMessage());
      }

    }

    Logger.log("Created new Database instance");
  }
}