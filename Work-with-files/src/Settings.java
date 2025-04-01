import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

enum Resolution {
  _1280x720,
  _1920x1080,
  _2560x1440,
  _3840x2160
}

public class Settings {
  private boolean fullscreen = false;
  private Resolution resolution = Resolution._1920x1080;

  public boolean isFullscreen() {
    return fullscreen;
  }

  public void setFullscreen(boolean fullscreen) {
    this.fullscreen = fullscreen;
  }

  public Resolution getResolution() {
    return resolution;
  }

  public void setResolution(Resolution resolution) {
    this.resolution = resolution;
  }

  public Settings(String jsonString) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Settings settings = mapper.readValue(jsonString, Settings.class);

    this.fullscreen = settings.isFullscreen();
    this.resolution = settings.getResolution();
  }

  public Settings() {
  }

  public Settings(boolean fullscreen) {
    this.fullscreen = fullscreen;
  }

  public Settings(boolean fullscreen, Resolution resolution) {
    this.fullscreen = fullscreen;
    this.resolution = resolution;
  }

  public String toJSON() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

  public void saveToFile() {
    try {
      String jsonData = this.toJSON();

      try (PrintWriter writer = new PrintWriter("settings.json", StandardCharsets.UTF_8);) {
        writer.println(jsonData);
      }

    } catch (Exception e) {
      System.out.println("Error while saving to file:");
      System.out.println(e.getMessage());
    }
  }
}
