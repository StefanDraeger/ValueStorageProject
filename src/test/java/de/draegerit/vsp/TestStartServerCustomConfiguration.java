package de.draegerit.vsp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestStartServerCustomConfiguration {

  private static final int PORT = 1234;
  private static final String SHUTDOWN_PASSWORD = "test123";

  @Test
  public void testCustomConfiguration() {
    VSPServer server = new VSPServer(PORT, SHUTDOWN_PASSWORD);
    boolean isStarted = server.start();
    assertTrue(isStarted);
    int currentServerPort = server.getPort();
    assertTrue(String.format("aktueller Server Port [%d] ist nicht [%d]", currentServerPort, PORT),
        server.getPort() == PORT);

    String currentShutdownPwd = server.getCustomShutDownPassword();
    assertTrue(String.format("aktuelles Passwort zum beenden vom Server [%s] ist nicht [%s]", currentShutdownPwd,
        SHUTDOWN_PASSWORD), server.getCustomShutDownPassword().equalsIgnoreCase(SHUTDOWN_PASSWORD));
    server.stop();
  }
}
