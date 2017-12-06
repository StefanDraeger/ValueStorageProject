package de.draegerit.vsp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestStopServer extends TestDefaultServer {

  private static final String HTTP_URL = "http://localhost:8080/stop?pwd=secretPassword";

  private static final String HTTP_URL_FAIL1 = "http://localhost:8080/stop?pwd=secretPassword123";
  private static final String HTTP_URL_FAIL2 = "http://localhost:8080/stop?pwd=";
  private static final String HTTP_URL_FAIL3 = "http://localhost:8080/stop";

  @Test
  public void shouldBeStopped() {
    String response = getServerResponse(HTTP_URL);
    assertTrue(!response.isEmpty());
    assertTrue(
        response.trim().equalsIgnoreCase("{\"text\":\"Server will be shutdown!\",\"code\":200,\"httpcode\":\"_200\"}"));
  }

  @Test
  public void shouldBeNotStopped1() {
    String response = getServerResponse(HTTP_URL_FAIL1);
    assertTrue(!response.isEmpty());
    assertTrue(response.trim().equalsIgnoreCase(
        "{\"text\":\"Password is empty or wrong, Server is still running!\",\"code\":400,\"httpcode\":\"_200\"}"));
  }

  @Test
  public void shouldBeNotStopped2() {
    String response = getServerResponse(HTTP_URL_FAIL2);
    assertTrue(!response.isEmpty());
    assertTrue(response.trim().equalsIgnoreCase(
        "{\"text\":\"Password is empty or wrong, Server is still running!\",\"code\":400,\"httpcode\":\"_200\"}"));
  }

  @Test
  public void shouldBeNotStopped3() {
    String response = getServerResponse(HTTP_URL_FAIL3);
    assertTrue(!response.isEmpty());
    assertTrue(response.trim().equalsIgnoreCase(
        "{\"text\":\"Password is empty or wrong, Server is still running!\",\"code\":400,\"httpcode\":\"_200\"}"));
  }
}
