package de.draegerit.vsp.handler;

import de.draegerit.vsp.json.JSONHandler;
import de.draegerit.vsp.response.Message;
import de.draegerit.vsp.util.HttpCode;
import de.draegerit.vsp.util.MessageUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Joiner;

import org.eclipse.jetty.server.Request;

public class ListHandler extends AbstractVSPHandler {

  private static final int NUM200 = 200;

  public final void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    List<String> filenames = getCurrentFilenames();
    StringBuffer buffer = new StringBuffer();
    if (filenames.isEmpty()) {
      buffer.append("Es sind keine Dateien abgelegt!");
    } else {
      buffer.append("Folgende Dateien sind abgelegt: [");
      Joiner joiner = Joiner.on(", ").skipNulls();
      buffer.append(joiner.join(filenames));
      buffer.append("]");
    }

    Message msg = new Message(buffer.toString(), NUM200, HttpCode._200);
    response.getWriter().println(MessageUtil.messageToJson(msg));
  }

  private List<String> getCurrentFilenames() {
    return Arrays.asList(new File(JSONHandler.OUTPUT_DIR).listFiles()).stream().map(file -> file.getName())
        .collect(Collectors.toList());
  }

}
