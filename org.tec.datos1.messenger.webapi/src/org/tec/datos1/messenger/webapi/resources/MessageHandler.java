package org.tec.datos1.messenger.webapi.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tec.datos1.messenger.estructures.Graph;
import org.tec.datos1.messenger.estructures.Vertex;
import org.tec.datos1.messenger.webapi.dto.Message;
import org.tec.datos1.messenger.webapi.dto.User;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

@Path("/messages")
public class MessageHandler {
	private static List<Message> messages = new ArrayList<>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages(String UserID) {

//		Vertex destinatario = """GRAFO CENTRAL""".getVertex(UserID);
		User usuario = new User();//Ser cambiado por el nodo buscado en destinatario
		return Response.ok()
				.entity(usuario.getMessages())
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMessage(Message message) {
//		Graph<User> x = new Graph<>();
//		Vertex destinatario = x.getVertex(message.getReceiver());
		User y = new User();//Ser cambiado por el nodo buscado en destinatario
		y.addMessage(message);
		return Response.ok()
				.build();
	}
	
}
