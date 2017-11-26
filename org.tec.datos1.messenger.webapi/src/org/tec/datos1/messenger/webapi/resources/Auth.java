package org.tec.datos1.messenger.webapi.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;

import org.tec.datos1.messenger.estructures.Network;
import org.tec.datos1.messenger.webapi.dto.User;

@Path("/auth")
public class Auth {
	private static Network users;
	/**
	 * Usuario envia un request al server generando un nuevo nodo en el grafo del server
	 * @param request
	 * @param newUser
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, User newUser) {
		String ipAddress = request.getRemoteAddr();

		if(users != null) {
			User user = users.searchByUsername(newUser.getUsername());
			if(user != null){
				return Response.status(401).build();
			}
			User ipUser = users.searchByIpAddress(ipAddress);
			if(ipUser != null) {
				return Response.status(409).build();
			}
			
		}


		newUser.setIpAddress(ipAddress);
		if(users == null) {
			users = new Network();
		}
		
		users.addVertex(newUser);
		users.reconnect();
		
		return Response.ok().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		if(users != null) {
			return Response.ok(users.getVertices().get(0)).build();
		}else {
			return Response.noContent().build();
		}
	}
	
	@DELETE
	public Response logout(@Context HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		if(users != null) {
			User ipUser = users.searchByIpAddress(ipAddress);
			if(ipUser != null) {
				users.removeVertex(ipUser);
				users.reconnect();
				return Response.ok("Logout successful").build();
			}
		}
		return Response.noContent().build();
	}
}
