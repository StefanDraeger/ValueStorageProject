package de.draegerit.vsp.createstorage;

import static org.junit.Assert.assertTrue;

import de.draegerit.vsp.TestDefaultServer;

import java.io.File;

import org.junit.Test;

public class TestCreateStorage extends TestDefaultServer {

  private static final String HTTP_URL = "http://localhost:8080/create";

  @Test
  public void testReachability() {
    String response = getServerResponse(HTTP_URL);
    assertTrue(!response.isEmpty());
    assertTrue(response.trim().equalsIgnoreCase("{\"text\":\"Create failed!\",\"code\":400,\"httpcode\":\"_200\"}"));
  }

  @Test
  public void testParams() {
    String url = HTTP_URL.concat("?key1=k1&key2=k2");
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    assertTrue(response.trim().startsWith("{\"text\":\"Create successfull"));    
  }
  
  @Test
  public void testForTestCaseStorage() {
    File file = new File("./output/testcase.json");
    assertTrue(file.exists());
  }

}
