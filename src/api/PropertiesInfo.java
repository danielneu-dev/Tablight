package api;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesInfo {
  private static final String FILE_NAME = "env.properties";
  private static Properties pr;

  private PropertiesInfo() {}

  public static String getProperties(String key) {
    pr = new Properties();
    try (InputStream input = PropertiesInfo.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
      if (input == null) {
        throw new FileNotFoundException("Property file '" + FILE_NAME + "' not found in the classpath");
      }
      pr.load(input);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pr.getProperty(key);
  }
}
