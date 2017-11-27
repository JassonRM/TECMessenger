package org.tec.datos1.messenger.estructures;

import org.tec.datos1.messenger.webapi.dto.User;
import java.util.Random;

public class Network extends Graph<User> {
	public User searchByUsername(String username) {
		
		for(Vertex<User> nodo : getVertices()) {
			if (nodo.getValue().getUsername().equals(username)) {
				return nodo.getValue();
			}
		}
		return null;
	}
	
	public User searchByIpAddress(String ipAddress) {
		Vertex<User> node = searchNodeByIpAddress(ipAddress);
		if(node != null) {
			return node.getValue();
		}else {
			return null;
		}
	}
	
	
	private Vertex<User> searchNodeByIpAddress(String ipAddress){
		for(Vertex<User> nodo : getVertices()) {
			if (nodo.getValue().getIpAddress().equals(ipAddress)) {
				return nodo;
			}
		}
		return null;
	}
	
	/**
	 * Reconecta un camino que a sido cortado en la eliminacion de algun nodo.
	 */
	public void reconnect() {
		Random randomGenerator = new Random();
		for(Vertex<User> nodo : getVertices()) {
			int count = 0;
			Vertex<User> second = getVertices().get(randomGenerator.nextInt(Math.max(getVertices().size() - 1, 1)));
			while(count < 3) {
				if(second != nodo) {
					nodo.addEdge(new Edge<User>(second, randomGenerator.nextInt(20)));
				}
				count++;
			}
		}
	}
	
	public User[] toArray() {
		User[] users = new User[getVertices().size()];
		int index = 0;
		for(Vertex<User> vertex : getVertices()) {
			users[index] = vertex.getValue();
			index++;
		}
		return users;
	}
	
	public void removeVertexByIpAddress(String ipAddress) {
		vertices.remove(searchNodeByIpAddress(ipAddress));
		reconnect();
	}
}
