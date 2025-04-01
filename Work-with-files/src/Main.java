import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
  public static void main(String[] args) {
    try (BufferedReader reader = new BufferedReader(new FileReader("settings.json"));) {
      String jsonString = reader.readLine();
      Settings settings = new Settings(jsonString);
      settings.setFullscreen(!settings.isFullscreen());
      settings.saveToFile();
    } catch (Exception e) {
      System.out.println("Error while reading from file:");
      System.out.println(e.getMessage());
    }
  }
}