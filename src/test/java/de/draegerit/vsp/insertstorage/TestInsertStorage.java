package de.draegerit.vsp.insertstorage;

import static org.junit.Assert.assertTrue;

import de.draegerit.vsp.TestDefaultServer;

import org.junit.Test;

public class TestInsertStorage extends TestDefaultServer {

  private static final String HTTP_URL = "http://localhost:8080/insert?token=0321c4";

  @Test
  public void testReachability() {
    String response = getServerResponse(HTTP_URL);
    assertTrue(!response.isEmpty());
  }

  @Test
  public void testInsert() {
    String url = "http://localhost:8080/insert?token=0321c4&k1=123&k2=345";
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    assertTrue(
        response.trim().equalsIgnoreCase("{\"text\":\"Insert successfull!\",\"code\":200,\"httpcode\":\"_200\"}"));
  }

}
