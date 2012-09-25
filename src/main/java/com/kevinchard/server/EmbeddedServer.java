package com.kevinchard.server;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;


public class EmbeddedServer {

	private final Server server;
	private final WebAppContext context;
	
	public EmbeddedServer(Server server, WebAppContext context) {
		this.server = server;
		this.context = context;
	}
	
	public void start() throws Exception {
		server.setHandler(context);		 
        server.start();
	}
	
	public void stop() throws Exception {
		server.stop();
	}
	
	public static EmbeddedServer create() throws IOException {
		
		Server server = new Server(8080);
		 
        WebAppContext context = new WebAppContext();
        
        context.setBaseResource(Resource.newResource(EmbeddedServer.class.getClassLoader().getResource("webapp")));
        context.setDescriptor("WEB-INF/web.xml");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
 
        return new EmbeddedServer(server, context);
	}
	
	public static void main(String[] args) throws Exception {
		EmbeddedServer.create().start();
	}
}
