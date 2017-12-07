package de.draegerit.vsp.json;

import de.draegerit.vsp.response.StorageItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JSONHandler {

  private static Logger logger = LoggerFactory.getLogger("JSONHandler.class");

  private static final String LINE_BREAK = "\r\n";

  public static final String JSON_SUFFIX = ".json";
  public static final String OUTPUT_DIR = "./output/";

  private JSONHandler() {
  }

  public static void createFile(String token, List<String> keys) {
    File file = new File(OUTPUT_DIR.concat(token).concat(JSON_SUFFIX));
    try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw);) {
      StorageItem item = new StorageItem();
      item.setToken(token);
      item.setExpectedKeys(keys);
      bw.write(new Gson().toJson(item));
      bw.flush();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }

  public static StorageItem getFromJson(String token) {
    File file = new File(OUTPUT_DIR.concat(token).concat(JSON_SUFFIX));
    StorageItem item = null;
    if (file.exists()) {
      item = new Gson().fromJson(readJsonFromFile(file), StorageItem.class);
    }
    return item;
  }

  private static String readJsonFromFile(File file) {
    StringBuffer json = new StringBuffer();
    try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
      String line = "";
      while ((line = br.readLine()) != null) {
        json.append(line);
        json.append(LINE_BREAK);
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return json.toString();
  }

  public static void storeItem(StorageItem item) {
    File file = new File(OUTPUT_DIR.concat(item.getToken()).concat(JSON_SUFFIX));
    try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw);) {
      bw.write(new Gson().toJson(item));
      bw.flush();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }
}
