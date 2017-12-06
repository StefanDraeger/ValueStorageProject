package de.draegerit.vsp.handler;

import de.draegerit.vsp.VSPServerConstants;
import de.draegerit.vsp.json.JSONHandler;
import de.draegerit.vsp.response.Message;
import de.draegerit.vsp.response.StorageItem;
import de.draegerit.vsp.util.HttpCode;
import de.draegerit.vsp.util.MessageUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.eclipse.jetty.server.Request;

public class GetHandler extends AbstractVSPHandler {

  private static final int NUM400 = 400;

  public final void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String token = getToken(request);
    String message = VSPServerConstants.UNDEFINED;
    if (token != null) {
      StorageItem item = JSONHandler.getFromJson(token);
      if (item != null) {
        message = new Gson().toJson(item);
      } else {
        Message msg = new Message("Token nicht gefunden!", NUM400, HttpCode._200);
        message = MessageUtil.messageToJson(msg);
      }
    } else {
      Message msg = new Message("Parameter \"token\" nicht gefunden!", NUM400, HttpCode._200);
      message = MessageUtil.messageToJson(msg);
    }
    response.getWriter().println(message);
  }
}
