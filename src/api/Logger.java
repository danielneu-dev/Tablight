package api;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Logger {

  private static final String filePath = "log.txt";
  private static final boolean debug = false;

  private Logger() {}

  public static void log(String str) {
    writeFile("🟩 [LOG] " + str);
  }
  public static void error(String str) {
    writeFile("🟥 [ERROR] " + str);
  }
  public static void info(String str) {
    writeFile("🟦 [INFO] " + str);
  }
  public static void warn(String str) {
    writeFile("🟨 [WARN] " + str);
  }

  private static void writeFile(String str) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
      bufferedWriter.write(str);
      bufferedWriter.newLine();
      if(debug) System.out.println(str);
    } catch (IOException e) {
      System.err.println("Error writing to the file: " + e.getMessage());
    }
  }
}