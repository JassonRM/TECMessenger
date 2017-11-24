package org.tec.datos1.messenger.estructures;

import java.util.ArrayList;
import java.util.List;

public class Cola<T> {
public List<T> lista;
public Cola() {
	this.lista= new ArrayList<T>();
}
public void queue(T elemento) {
	lista.add(elemento);
}
public T dequeue() {
	if(lista.isEmpty()) {
		return null;
	}
	T elemento = lista.get(0);
	lista.remove(0);
	return elemento;
}
public T peek() {
	if(lista.isEmpty()) {
		return null;
	}
	T elemento = lista.get(0);
	return elemento;
}
}
