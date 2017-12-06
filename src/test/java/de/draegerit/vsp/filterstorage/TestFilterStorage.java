package de.draegerit.vsp.filterstorage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.draegerit.vsp.TestDefaultServer;
import de.draegerit.vsp.response.Item;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

public class TestFilterStorage extends TestDefaultServer {

  @Test
  public void testFilterStorageTimeFromAndTimeTo() {
    String url = "http://localhost:8080/filter?token=0321c4&timeFrom=1&timeTo=1512376345048";
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    assertFalse(getListFromResponse(response).isEmpty());
  }

  @Test
  public void testFilterStorageTimeFrom() {
    String url = "http://localhost:8080/filter?token=0321c4&timeFrom=1512376345049";
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    assertFalse(getListFromResponse(response).isEmpty());
  }

  @Test
  public void testFilterStorageTimeTo() {
    String url = "http://localhost:8080/filter?token=0321c4&timeTo=1512376345049";
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    assertFalse(getListFromResponse(response).isEmpty());
  }

  private List<Item> getListFromResponse(String response) {
    Type listType = new TypeToken<ArrayList<Item>>() {
    }.getType();
    return new Gson().fromJson(response, listType);
  }
}
