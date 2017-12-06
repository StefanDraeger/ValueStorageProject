package de.draegerit.vsp;

import de.draegerit.vsp.handler.CreateStorageHandler;
import de.draegerit.vsp.handler.DeleteHandler;
import de.draegerit.vsp.handler.FilterHandler;
import de.draegerit.vsp.handler.GetHandler;
import de.draegerit.vsp.handler.HelloHandler;
import de.draegerit.vsp.handler.InsertHandler;
import de.draegerit.vsp.handler.ListHandler;
import de.draegerit.vsp.handler.StopServerHandler;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class VSPServer {

  static final int DEFAULT_PORT = 8080;

  static final String DEFAULT_SHUTDOWN_PWD = "secretPassword";

  private Server server;

  private String customShutDownPassword = DEFAULT_SHUTDOWN_PWD;

  private VSPServer(int port) {
    initServer(port);
  }

  public VSPServer() {
    this(DEFAULT_PORT);
  }

  VSPServer(int port, String shutdownPwd) {
    this(port);
    this.setCustomShutDownPassword(
        (shutdownPwd != null) && (!shutdownPwd.isEmpty()) ? shutdownPwd : DEFAULT_SHUTDOWN_PWD);
  }

  private void initServer(int port) {
    server = new Server(port);

    ContextHandler contextHello = new ContextHandler();
    contextHello.setContextPath("/hello");
    contextHello.setHandler(new HelloHandler());

    ContextHandler contextCreate = new ContextHandler();
    contextCreate.setContextPath("/create");
    contextCreate.setHandler(new CreateStorageHandler());

    ContextHandler contextInsert = new ContextHandler();
    contextInsert.setContextPath("/insert");
    contextInsert.setHandler(new InsertHandler());

    ContextHandler contextStop = new ContextHandler();
    contextStop.setContextPath("/stop");
    contextStop.setHandler(new StopServerHandler(this));

    ContextHandler contextList = new ContextHandler();
    contextList.setContextPath("/list");
    contextList.setHandler(new ListHandler());

    ContextHandler contextGet = new ContextHandler();
    contextGet.setContextPath("/get");
    contextGet.setHandler(new GetHandler());

    ContextHandler contextDelete = new ContextHandler();
    contextDelete.setContextPath("/delete");
    contextDelete.setHandler(new DeleteHandler());

    ContextHandler contextFilter = new ContextHandler();
    contextFilter.setContextPath("/filter");
    contextFilter.setHandler(new FilterHandler());

    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[] {contextHello, contextCreate, contextInsert, contextStop, contextList,
        contextGet, contextDelete, contextFilter });

    server.setHandler(contexts);
  }

  final boolean start() {
    boolean result = false;
    try {
      server.start();
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public final int getPort() {
    List<Connector> connectors = Arrays.asList(server.getConnectors());
    for (Connector c : connectors) {
      if (c instanceof ServerConnector) {
        ServerConnector serverConnector = (ServerConnector) c;
        return serverConnector.getLocalPort();
      }
    }
    return 0;
  }

  public final boolean stop() {
    boolean result = false;
    try {
      server.stop();
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public final String getCustomShutDownPassword() {
    return customShutDownPassword;
  }

  public final void setCustomShutDownPassword(String inCustomShutdownPassword) {
    this.customShutDownPassword = inCustomShutdownPassword;
  }

}
