package de.draegerit.vsp.handler.factory;

import de.draegerit.vsp.VSPServer;
import de.draegerit.vsp.handler.AbstractVSPHandler;
import de.draegerit.vsp.handler.CreateStorageHandler;
import de.draegerit.vsp.handler.DeleteHandler;
import de.draegerit.vsp.handler.FilterHandler;
import de.draegerit.vsp.handler.GetHandler;
import de.draegerit.vsp.handler.HelloHandler;
import de.draegerit.vsp.handler.InsertHandler;
import de.draegerit.vsp.handler.ListHandler;
import de.draegerit.vsp.handler.StopServerHandler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.handler.ContextHandler;

public final class ContextHandlerFactoryImpl implements IContextHandlerFactory {

  public static final String HELLO = "/hello";
  public static final String CREATE = "/create";
  public static final String INSERT = "/insert";
  public static final String STOP = "/stop";
  public static final String LIST = "/list";
  public static final String GET = "/get";
  public static final String DELETE = "/delete";
  public static final String FILTER = "/filter";

  @Override
  public ContextHandler create(String contextPath, AbstractVSPHandler handler) {
    ContextHandler contextHandler = new ContextHandler();
    contextHandler.setContextPath(contextPath);
    contextHandler.setHandler(handler);
    return contextHandler;
  }

  public ContextHandler createFilter() {
    return create(FILTER, new FilterHandler());
  }

  @Override
  public ContextHandler createDelete() {
    return create(DELETE, new DeleteHandler());
  }

  @Override
  public ContextHandler createGet() {
    return create(GET, new GetHandler());
  }

  @Override
  public ContextHandler createList() {
    return create(LIST, new ListHandler());
  }

  @Override
  public ContextHandler createStop(VSPServer server) {
    return create(STOP, new StopServerHandler(server));
  }

  @Override
  public ContextHandler createInsert() {
    return create(INSERT, new InsertHandler());
  }

  @Override
  public ContextHandler createCreate() {
    return create(CREATE, new CreateStorageHandler());
  }

  @Override
  public ContextHandler createHello() {
    return create(HELLO, new HelloHandler());
  }

  /**
   * Liefert alle ContextHandler für die Anwendung.
  * @param server
  * @return
   */
  @Override
  public ContextHandler[] getAllContextHandler(VSPServer server) {
    List<ContextHandler> contextHandlers = new ArrayList<>();
    contextHandlers.add(createHello());
    contextHandlers.add(createGet());
    contextHandlers.add(createDelete());
    contextHandlers.add(createList());
    contextHandlers.add(createInsert());
    contextHandlers.add(createCreate());
    contextHandlers.add(createFilter());
    contextHandlers.add(createStop(server));
    return contextHandlers.toArray(new ContextHandler[contextHandlers.size()]);
  }

}
