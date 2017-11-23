package org.tec.datos1.messenger.webapi.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;

@Path("/hello")
public class Hello {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		return "Hello world";
	}
}
