package org.tec.datos1.messenger.estructures;

import org.tec.datos1.messenger.webapi.dto.User;

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
}
