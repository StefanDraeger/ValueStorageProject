package de.draegerit.vsp.factory;

import static org.junit.Assert.assertTrue;

import de.draegerit.vsp.handler.CreateStorageHandler;
import de.draegerit.vsp.handler.DeleteHandler;
import de.draegerit.vsp.handler.FilterHandler;
import de.draegerit.vsp.handler.GetHandler;
import de.draegerit.vsp.handler.HelloHandler;
import de.draegerit.vsp.handler.InsertHandler;
import de.draegerit.vsp.handler.ListHandler;
import de.draegerit.vsp.handler.StopServerHandler;
import de.draegerit.vsp.handler.factory.ContextHandlerFactoryImpl;
import de.draegerit.vsp.handler.factory.IContextHandlerFactory;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.junit.Test;

public class TestContextHandlerFactory {

  @Test
  public void testFactory() {
    IContextHandlerFactory contextHandlerFactoryImpl = new ContextHandlerFactoryImpl();
    assertTrue(contextHandlerFactoryImpl != null);

    ContextHandler[] contextHandlers = contextHandlerFactoryImpl.getAllContextHandler(null);
    assertTrue(contextHandlers.length > 0);
    
    
    List<ContextHandler> ctxHandlers = Arrays.asList(contextHandlers);
    for (ContextHandler ctxHandler : ctxHandlers) {
      String contextPath = ctxHandler.getContextPath();
      Handler handler = ctxHandler.getHandler();
      switch (contextPath) {
      case ContextHandlerFactoryImpl.HELLO:
        assertTrue(handler instanceof HelloHandler);
        break;
      case ContextHandlerFactoryImpl.CREATE:
        assertTrue(handler instanceof CreateStorageHandler);
        break;
      case ContextHandlerFactoryImpl.INSERT:
        assertTrue(handler instanceof InsertHandler);
        break;
      case ContextHandlerFactoryImpl.STOP:
        assertTrue(handler instanceof StopServerHandler);
        break;
      case ContextHandlerFactoryImpl.LIST:
        assertTrue(handler instanceof ListHandler);
        break;
      case ContextHandlerFactoryImpl.DELETE:
        assertTrue(handler instanceof DeleteHandler);
        break;
      case ContextHandlerFactoryImpl.FILTER:
        assertTrue(handler instanceof FilterHandler);
        break;
      case ContextHandlerFactoryImpl.GET:
        assertTrue(handler instanceof GetHandler);
        break;
      default:
        throw new IllegalArgumentException("ContextPath [" + contextPath + "] not found!");
      }
    }

  }
}
