package de.draegerit.vsp.handler.factory;

import de.draegerit.vsp.VSPServer;
import de.draegerit.vsp.handler.AbstractVSPHandler;

import org.eclipse.jetty.server.handler.ContextHandler;

public interface IContextHandlerFactory {

  ContextHandler[] getAllContextHandler(VSPServer server);

  ContextHandler create(String contextPath, AbstractVSPHandler handler);

  ContextHandler createFilter();

  ContextHandler createDelete();

  ContextHandler createGet();

  ContextHandler createList();

  ContextHandler createStop(VSPServer server);

  ContextHandler createInsert();

  ContextHandler createCreate();

  ContextHandler createHello();

}
