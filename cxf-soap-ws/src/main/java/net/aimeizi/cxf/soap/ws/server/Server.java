package net.aimeizi.cxf.soap.ws.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public class Server {

    protected Server() throws Exception {

        System.out.println("Starting Server");

        /**
         * Important: This code simply starts up a servlet container and adds
         * the web application in src/webapp to it. Normally you would be using
         * Jetty or Tomcat and have the webapp packaged as a WAR. This is simply
         * as a convenience so you do not need to configure your servlet
         * container to see CXF in action!
         */
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server();

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);
        server.setConnectors(new Connector[] {connector});

        WebAppContext webappcontext = new WebAppContext();
        webappcontext.setContextPath("/");

        webappcontext.setWar("D:\\developer\\workspace\\maven-framework-project\\cxf-soap-ws\\target\\cxf-soap-ws.war");

        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[] {webappcontext, new DefaultHandler()});

        server.setHandler(handlers);
        server.start();
        System.out.println("Server ready...");
        server.join();
    }

    public static void main(String args[]) throws Exception {
        new Server();
    }

}
