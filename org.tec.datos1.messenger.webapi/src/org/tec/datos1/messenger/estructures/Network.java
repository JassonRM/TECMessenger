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
		
		for(Vertex<User> nodo : getVertices()) {
			if (nodo.getValue().getIpAddress().equals(ipAddress)) {
				return nodo.getValue();
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
			Vertex<User> second = getVertices().get(randomGenerator.nextInt(getVertices().size() - 1));
			while(count < 3) {
				if(second != nodo) {
					nodo.addEdge(new Edge<User>(second, randomGenerator.nextInt(20)));
				}
				count++;
			}
		}
	}
}
