package de.draegerit.vsp.getstorage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import de.draegerit.vsp.TestDefaultServer;
import de.draegerit.vsp.response.StorageItem;

import com.google.gson.Gson;

import org.junit.Test;

public class TestGetStorage extends TestDefaultServer {

  @Test
  public void testGetStorage() {
    String url = "http://localhost:8080/get/?token=testcase";
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    StorageItem item = new Gson().fromJson(response, StorageItem.class);
    assertTrue(item != null);
  }

  @Test
  public void testGetStorageTokenFail() {
    String url = "http://localhost:8080/get";
    String response = getServerResponse(url);
    assertFalse(response.isEmpty());
    assertFalse(response
        .equalsIgnoreCase("{\"text\":\"Parameter \\\"token\\\" nicht gefunden!\",\"code\":400,\"httpcode\":\"_200\"}"));
  }

  @Test
  public void testGetStorageTokenNotFound() {
    String url = "http://localhost:8080/get/?token=testcase";
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    assertFalse(response
        .equalsIgnoreCase("{\"text\":\"Parameter \\\"token\\\" nicht gefunden!\",\"code\":400,\"httpcode\":\"_200\"}"));
  }

}
