package de.draegerit.vsp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestStartServer extends TestDefaultServer {

  @Test
  public void testStartServer() {
    boolean isStarted = getServer().start();
    assertTrue(isStarted);
  }

  @Test
  public void testPortConfiguration() {
    getServer().start();
    int currentServerPort = getServer().getPort();
    assertFalse(String.format("aktueller Server Port [%d] ist nicht [%d]", currentServerPort, VSPServer.DEFAULT_PORT),
        getServer().getPort() == VSPServer.DEFAULT_PORT);
  }
}
