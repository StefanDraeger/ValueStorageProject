package de.draegerit.vsp.handler;

import de.draegerit.vsp.json.JSONHandler;
import de.draegerit.vsp.util.MD5Util;
import de.draegerit.vsp.util.MessageUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public class CreateStorageHandler extends AbstractVSPHandler {

  public final void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    List<String> keys = new ArrayList<>();
    String md5Hash = "";
    if (!request.getParameterMap().isEmpty()) {
      StringBuffer buffer = new StringBuffer();
      for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
        buffer.append(entry.getKey());
        for (String value : entry.getValue()) {
          buffer.append(value);
          keys.add(value);
        }
      }
      md5Hash = MD5Util.getHash(buffer.toString());
    }
    String msg = "-undefined-";
    if (md5Hash.isEmpty()) {
      msg = MessageUtil.getCreateFailedMessage();
    } else {
      JSONHandler.createFile(md5Hash, keys);
      msg = MessageUtil.getCreateSuccessfullMessage(md5Hash);
    }

    response.getWriter().println(msg);
  }

}
