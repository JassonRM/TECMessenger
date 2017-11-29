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
	public static Network users = new Network();
	/**
	 * Usuario envia un request al server generando un nuevo nodo en el grafo del server
	 * @param request
	 * @param newUser
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		String userName = request.getParameter("Name");
		System.out.println(userName);
		if(users != null) {
			User user = users.searchByUsername(userName);
			if(user != null){
				System.out.println("No usuario");
				return Response.status(401).build();
			}
			User ipUser = users.searchByIpAddress(ipAddress);
			if(ipUser != null) {
				return Response.status(409).build();
			}
			
		}
		User newUser = new User(userName,ipAddress);
		newUser.setIpAddress(ipAddress);
		if(users == null) {
			users = new Network();
		}
		
		users.addVertex(newUser);
		users.reconnect();
		GodObserver.notifyAllUsers();
		System.out.println(userName);
		return Response.ok().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		if(users != null) {
			return Response.ok(users.toArray()).build();
		}else {
			return Response.noContent().build();
		}
	}
	
	@DELETE
	public Response logout(@Context HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		if(users != null) {
			users.removeVertexByIpAddress(ipAddress);
			GodObserver.notifyAllUsers();
			return Response.ok("Logout successful").build();
		}
		return Response.noContent().build();
	}
	@GET
	@Path("/auth/cosult")
	public Response consultChanges(@Context HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		User ipUser = users.searchByIpAddress(ipAddress);
		if(ipUser.changes) {
			ipUser.swichChange();
			return Response.ok().build();
		}
		else {
			return Response.serverError().build();
		}
	}
}
