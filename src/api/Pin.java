package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pin {
  private Pin() {}

  public static boolean check() {
    BufferedReader pass = new BufferedReader(new InputStreamReader(System.in));
    String storedPin = PropertiesInfo.getProperties("pin");

    String enteredPin = "";
      
    try {
      System.out.print("PIN > ");
      enteredPin = pass.readLine();
  
      if (enteredPin.equals(storedPin)) return true;

      return false;

    } catch (IOException e) {
      Logger.error(e.getMessage());
      return false;
    }
  }
}