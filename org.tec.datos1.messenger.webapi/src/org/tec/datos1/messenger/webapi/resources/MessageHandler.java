package org.tec.datos1.messenger.webapi.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tec.datos1.messenger.webapi.dto.Message;

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
	public Response getMessages() {
		return Response.ok()
				.entity(messages)
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMessage(Message message) {
		messages.add(message);
		return Response.ok()
				.build();
	}
	
}
