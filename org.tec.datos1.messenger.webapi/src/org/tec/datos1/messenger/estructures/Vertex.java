package org.tec.datos1.messenger.estructures;

import java.util.LinkedList;

public class Vertex<T extends Comparable<T>> {
	private T value;
	private LinkedList<Edge<T>> edges;
	
	public Vertex(T value) {
		this.value = value;
		edges = new LinkedList<>();
	}

	public T getValue() {
		return value;
	}

	public void addEdge(Edge<T> edge) {
		edges.add(0,edge);
	}
	
	public LinkedList<Edge<T>> getEdges(){
		return edges;
	}
	
	@Override
	public String toString() {
		String result = value.toString();
		for (Edge<T> edge : edges) {
			result += " =" + edge.getWeight() + "=> ";
			result += edge.getSecondVertex().getValue();
		}
		result += "\n";
		return result;
	}
	
	public void disconnect() {
		edges = new LinkedList<>();
	}
	
}
