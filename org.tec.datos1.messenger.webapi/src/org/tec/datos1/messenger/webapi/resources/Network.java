package org.tec.datos1.messenger.webapi.resources;

import java.util.LinkedList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tec.datos1.messenger.estructures.Edge;
import org.tec.datos1.messenger.estructures.Vertex;
import org.tec.datos1.messenger.webapi.dto.Graph;
import org.tec.datos1.messenger.webapi.dto.Link;
import org.tec.datos1.messenger.webapi.dto.Node;
import org.tec.datos1.messenger.webapi.dto.User;

@Path("/network")
public class Network {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNetwork() {
		org.tec.datos1.messenger.estructures.Network users = Auth.users;
		
		if(users != null) {
			Node[] nodes = new Node[users.getVertices().size()];
			LinkedList<Link> links = new LinkedList<Link>();
			int count = 0;
			for(Vertex<User> user : users.getVertices()) {
				nodes[count] = new Node(user.getValue().getUsername() + ": " + user.getValue().getIpAddress(), 1);
				
				EdgeFor:
				for(Edge<User> edge: user.getEdges()) {
					if(links.isEmpty()) {
						links.add(new Link(nodes[count], new Node(edge.getSecondVertex().getValue().getUsername() + ": " + edge.getSecondVertex().getValue().getIpAddress(), 1), edge.getWeight()));
					}else {
						for(Link link: links) {
							if(link.getSource() == edge.getSecondVertex().getValue().getUsername() + ": " + edge.getSecondVertex().getValue().getIpAddress()) {
								continue EdgeFor;
							}
						}
						links.add(new Link(nodes[count], new Node(edge.getSecondVertex().getValue().getUsername() + ": " + edge.getSecondVertex().getValue().getIpAddress(), 1), edge.getWeight()));
						
					}
				}
				
				count++;
			}
			
			Link[] linksArray = links.toArray(new Link[links.size()]);
			
			Graph graph = new Graph(nodes, linksArray);
			
			return Response.ok(graph).build();
		}else {
			return Response.noContent().build();
		}
	}
}
