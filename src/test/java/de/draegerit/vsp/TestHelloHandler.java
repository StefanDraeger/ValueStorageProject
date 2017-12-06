package de.draegerit.vsp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestHelloHandler extends TestDefaultServer {

  private static final String HTTP_URL = "http://localhost:8080/hello";

  @Test
  public void testReachability() {
    String response = getServerResponse(HTTP_URL);
    assertTrue(!response.isEmpty());
    assertTrue(
        response.trim().equalsIgnoreCase("{\"text\":\"Server is running!\",\"code\":200,\"httpcode\":\"_200\"}"));
  }
}
