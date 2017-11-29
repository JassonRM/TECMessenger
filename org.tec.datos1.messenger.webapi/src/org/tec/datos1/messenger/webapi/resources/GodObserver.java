package org.tec.datos1.messenger.webapi.resources;

import java.util.LinkedList;

import org.tec.datos1.messenger.estructures.Vertex;
import org.tec.datos1.messenger.webapi.dto.User;

public class GodObserver {
public static void notifyAllUsers() {
	LinkedList<Vertex<User>> users = Auth.users.getVertices();
	for(Vertex<User> usuarios : users) {
		usuarios.getValue().swichChange();
	}
}
}
