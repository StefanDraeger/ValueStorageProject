package de.draegerit.vsp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;

public class TestDefaultServer {

  public static final String CHARSET = "UTF-8";

  private VSPServer server;

  @Before
  public void setup() {
    setServer(new VSPServer());
    assertNotNull(getServer());
    boolean isStarted = getServer().start();
    assertTrue(isStarted);
  }

  @After
  public void tearDown() {
    boolean isStopped = getServer().stop();
    assertTrue(isStopped);
  }

  protected final String getServerResponse(String url) {
    StringBuffer buffer = new StringBuffer();
    try {
      InputStream response = new URL(url).openStream();
      try (Scanner scanner = new Scanner(response)) {
        buffer.append(scanner.useDelimiter("\\A").next());
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }

  public final VSPServer getServer() {
    return server;
  }

  public final void setServer(VSPServer inServer) {
    this.server = inServer;
  }
}
