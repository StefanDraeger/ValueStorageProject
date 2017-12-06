package de.draegerit.vsp.handler;

import de.draegerit.vsp.VSPServerConstants;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public abstract class AbstractVSPHandler extends AbstractHandler {

  public final void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType(VSPServerConstants.CONTENTTYPE);
    response.setStatus(HttpServletResponse.SC_OK);
    baseRequest.setHandled(true);
    handle(baseRequest, request, response);
  }

  protected final String getToken(HttpServletRequest request) {
    String[] tokens = request.getParameterMap().get(VSPServerConstants.PARAM_TOKEN);
    return tokens != null ? Arrays.asList(tokens).stream().filter(t -> !t.isEmpty()).findAny().orElse(null) : null;
  }

  public abstract void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException;
}
