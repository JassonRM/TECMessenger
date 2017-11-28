package org.tec.datos1.messenger.webapi.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tec.datos1.messenger.estructures.Graph;
import org.tec.datos1.messenger.estructures.Vertex;
import org.tec.datos1.messenger.webapi.dto.Message;
import org.tec.datos1.messenger.webapi.dto.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.glassfish.jersey.media.multipart.*;

@Path("/messages")
public class MessageHandler {
	private static List<Message> messages = new ArrayList<>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
//	public Response getMessages(String UserID) {
//	public Response getMessages(@Context HttpServletRequest request) {

			
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
	public Response createMessage(@FormDataParam("Message") Message message) {
		try {
		//Esto es cuando se recibe el mensaje al server por primera vez, se determina el path de un solo 
		if(message.getPath().isEmpty()) {
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
			message.setPath(names);
		}
		//Determino el siguiente usuario al que debo de enviarlo
		User nextNode = Auth.users.searchByUsername(message.getPath().get(0));
		//Lo elimino
		message.getPath().remove(0);
		//Lo anado al usuario para que lo reenvie posteriormente al detectar que no es el usuario al que se supone le debe enviar
		nextNode.addMessage(message);
		return Response.ok()
				.build();
		}catch(Exception e) {
			System.out.println("No hay usuarios y no hay nada por lo que hara un catch a eso i manda error ");
			return Response.serverError().build();
		}
		
	}
	
}
