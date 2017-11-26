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
import org.tec.datos1.messenger.estructures.NodoGrafo;
import org.tec.datos1.messenger.webapi.dto.User;

@Path("/auth")
public class Auth {
	private static Network centralNode = null;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, User newUser) {
		String ipAddress = request.getRemoteAddr();

		if(centralNode != null) {
			Network user = centralNode.searchByUsername(newUser.getUsername(), centralNode);
			if(user != null){
				return Response.status(401).build();
			}
			Network ipUser = centralNode.searchByIpAddress(ipAddress, centralNode);
			if(ipUser != null) {
				return Response.status(409).build();
			}
			
		}


		newUser.setIpAddress(ipAddress);
		Network node = new Network(newUser);
		if(centralNode == null) {
			centralNode = node;
		}else {
			centralNode.connect(node, 0); //Hay que cambiarlo para que no solo se conecte al mismo nodo
		}
		return Response.ok().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		if(centralNode != null) {
			return Response.ok(centralNode.valor).build();
		}else {
			return Response.noContent().build();
		}
	}
	
	@DELETE
	public Response logout(@Context HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		if(centralNode != null) {
			NodoGrafo<User> ipUser = centralNode.searchByIpAddress(ipAddress, centralNode);
			if(ipUser != null) {
				centralNode.delete(ipUser.valor, centralNode);
				return Response.ok("Logout successful").build();
			}
		}
		return Response.noContent().build();
	}
}
