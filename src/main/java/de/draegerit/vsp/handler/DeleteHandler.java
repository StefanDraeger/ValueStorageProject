package de.draegerit.vsp.handler;

import de.draegerit.vsp.VSPServerConstants;
import de.draegerit.vsp.json.JSONHandler;
import de.draegerit.vsp.util.MessageUtil;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public class DeleteHandler extends AbstractVSPHandler {

  public final void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String token = getToken(request);
    String message = VSPServerConstants.UNDEFINED;
    boolean isDeleted = false;
    if (token != null) {
      File file = new File(JSONHandler.OUTPUT_DIR + token + JSONHandler.JSON_SUFFIX);
      if (file.exists()) {
        isDeleted = file.delete();
      }
    } else {
      message = MessageUtil.getDeleteFailedMessage(token);
    }

    if (isDeleted) {
      message = MessageUtil.getDeleteSuccessfullMessage(token);
    }

    response.getWriter().println(message);
  }
}
