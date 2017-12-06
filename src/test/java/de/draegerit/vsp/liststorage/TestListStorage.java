package de.draegerit.vsp.liststorage;

import static org.junit.Assert.assertTrue;

import de.draegerit.vsp.TestDefaultServer;
import de.draegerit.vsp.response.Message;
import de.draegerit.vsp.util.HttpCode;
import de.draegerit.vsp.util.MessageUtil;

import org.junit.Test;

public class TestListStorage extends TestDefaultServer {

  @Test
  public void shouldBeRetrieveFileList() {
    String url = "http://localhost:8080/list";
    String response = getServerResponse(url);
    assertTrue(!response.isEmpty());
    Message msg = MessageUtil.jsonToMessage(response);
    assertTrue(msg.getHttpcode().equals(HttpCode._200));
  }
}
