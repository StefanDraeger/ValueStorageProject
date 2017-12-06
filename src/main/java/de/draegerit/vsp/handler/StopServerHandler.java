package de.draegerit.vsp.handler;

import de.draegerit.vsp.VSPServer;
import de.draegerit.vsp.util.MessageUtil;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public class StopServerHandler extends AbstractVSPHandler {

  private static final int NUM_SLEEP = 3500;
  private VSPServer server;

  public StopServerHandler(VSPServer inServer) {
    this.server = inServer;
  }

  public final void handle(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String shutdownPwd = request.getParameter("pwd");
    if (shutdownPwd != null && shutdownPwd.equalsIgnoreCase(server.getCustomShutDownPassword())) {
      response.getWriter().println(MessageUtil.getStopMessage());
      startShutdownTimer();
    } else {
      response.getWriter().println(MessageUtil.getStopFailedPwdMessage());
    }

  }

  private void startShutdownTimer() {
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        server.stop();
        System.exit(-1);
      }
    };

    timer.schedule(task, NUM_SLEEP);

  }
}
