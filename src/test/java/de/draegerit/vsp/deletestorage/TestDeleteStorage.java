package de.draegerit.vsp.deletestorage;

import static org.junit.Assert.assertTrue;

import de.draegerit.vsp.TestDefaultServer;

public class TestDeleteStorage extends TestDefaultServer {

  // @Test
  public final void testDeleteStorage() {
    String url = "http://localhost:8080/delete/?token=0321c4";
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    assertTrue(response.trim().contains("successfull"));
  }
}
