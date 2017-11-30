package org.tec.datos1.messenger.webapi.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tec.datos1.messenger.estructures.Vertex;
import org.tec.datos1.messenger.webapi.dto.Message;
import org.tec.datos1.messenger.webapi.dto.User;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

@Path("/messages")
public class MessageHandler {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages(@Context HttpServletRequest request) {
		if(request ==null) {
			System.out.println("ERROR no");
			return null;
		}
		
		User usuario = Auth.users.searchByIpAddress(request.getRemoteAddr());
		try {
		return Response.ok()
				.entity(usuario.getMessages())
				.build();}
		catch(Exception e) {
			Response.noContent().build();
		}
		return null;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/**
	 * Recibe un mensaje, si no viene con un path se determina un path y se envia al usuario
	 * @param message
	 * @return
	 */
	public Response createMessage(Message message) {
		try {
		//Esto es cuando se recibe el mensaje al server por primera vez, se determina el path de un solo 
		if(message.getPath() == null || message.getPath().isEmpty()) {
			Auth.allMessages.add(message);
			//Determino los dos nodos que son el que recibe y el que envia
			Vertex<User> sender = Auth.users.searchByUsernameNode(message.getSender());
			Vertex<User> receiver = Auth.users.searchByUsernameNode(message.getReceiver());
			//Saco la lista de nodos a los cuales tiene que pasar hasta llegar
			LinkedList<Vertex<User>> list = Auth.users.Floyd(sender, receiver);
			ArrayList<String> names = new ArrayList<>();
			//La paso a lista de usuarios
			for(Vertex<User> usuarios : list) {
				names.add(usuarios.getValue().getUsername());
			}
			//Determino el camino
			names.remove(0);//elimina el sender
			message.setPath(names);
			
		}
		Vertex<User> receiver = Auth.users.searchByUsernameNode(message.getReceiver());
		//Determino el siguiente usuario al que debo de enviarlo
		if( (message.getAudio() != null && !message.getAudio().equals("") && message.getPath().get(0).equals(receiver.getValue().getUsername()))  ) {
			Auth.cola.queue(message.getAudio());
			
		}
		if( (message.getImage() != null && !message.getImage().equals("")) && message.getPath().get(0).equals(receiver.getValue().getUsername()))   {
			Auth.cola.queue(message.getImage());
			
		}
		if( (message.getFile() != null && !message.getFile().equals("")) && message.getPath().get(0).equals(receiver.getValue().getUsername()) ) {
			Auth.cola.queue(message.getFile());
			
		}
		
		User nextNode = Auth.users.searchByUsername(message.getPath().get(0));
		//Lo elimino
		message.getPath().remove(0);
		
		//Lo anado al usuario para que lo reenvie posteriormente al detectar que no es el usuario al que se supone le debe enviar
		nextNode.addMessage(message);
		return Response.ok()
				.build();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("No hay usuarios y no hay nada por lo que hara un catch a eso y manda error ");
			return Response.serverError().build();
		}
		
	}
	
	@Path("/messages/search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMessage(@Context HttpServletRequest request) {
		if(request ==null) {
			System.out.println("ERROR no");
			return null;
		}
		
		String buscado = request.getParameter("buscado");
		User user = Auth.users.searchByIpAddress(request.getRemoteAddr());
		ArrayList<Message> messages = user.searchMessages(buscado);
		
		try {
			return Response.ok().entity(messages).build();}
		catch(Exception e) {
			Response.noContent().build();
		}
		return null;
	} 
}
