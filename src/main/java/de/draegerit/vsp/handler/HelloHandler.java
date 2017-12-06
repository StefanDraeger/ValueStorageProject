package de.draegerit.vsp.handler;

import de.draegerit.vsp.util.MessageUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public class HelloHandler extends AbstractVSPHandler {

  public final void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.getWriter().println(MessageUtil.getHelloMessage());
  }
}
