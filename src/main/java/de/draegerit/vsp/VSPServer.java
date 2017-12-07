package de.draegerit.vsp;

import de.draegerit.vsp.handler.factory.ContextHandlerFactoryImpl;

import java.util.Arrays;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VSPServer {

  private static Logger logger = LoggerFactory.getLogger("VSPServer.class");

  static final int DEFAULT_PORT = 8080;

  static final String DEFAULT_SHUTDOWN_PWD = "secretPassword";

  private Server server;

  private String customShutDownPassword = DEFAULT_SHUTDOWN_PWD;

  private VSPServer(int port) {
    initServer(port);
  }

  /**
   * Konstruktor
   */
  public VSPServer() {
    this(DEFAULT_PORT);
  }

  VSPServer(int port, String shutdownPwd) {
    this(port);
    this.setCustomShutDownPassword(
        (shutdownPwd != null) && (!shutdownPwd.isEmpty()) ? shutdownPwd : DEFAULT_SHUTDOWN_PWD);
  }

  /**
   * Initialisiert den Server.
   *
   * @param port
   *          - der Port über welchen der Server erreichbar sein soll.
   */
  private void initServer(int port) {
    server = new Server(port);

    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new ContextHandlerFactoryImpl().getAllContextHandler(this));

    server.setHandler(contexts);
  }

  /**
   * Startet den Server.
   *
   * @return liefert Boolean.TRUE wenn der Server erfolgreich gestartet wurde.
   */
  final boolean start() {
    boolean result = false;
    try {
      server.start();
      result = true;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return result;
  }

  /**
   * Liefert den Port über welchen der Server gestartet wurde.
   *
   * @return primitiver Integer Wert mit dem Port.
   */
  public final int getPort() {
    for (Connector c : Arrays.asList(server.getConnectors())) {
      if (c instanceof ServerConnector) {
        try (ServerConnector serverConnector = (ServerConnector) c;) {
          return serverConnector.getLocalPort();
        }
      }
    }
    return 0;
  }

  /**
   * Stoppt den Server, und liefert Boolean.TRUE wenn der Server erfolgreich
   * gestoppt wurde.
   *
   * @return Boolean.TRUE wenn der Server erfolgreich gestoppt wurde
   */
  public final boolean stop() {
    boolean result = false;
    try {
      server.stop();
      result = true;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
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
