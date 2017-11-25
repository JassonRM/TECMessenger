package org.tec.datos1.messenger.estructures;

public class Edge<T extends Comparable<T>> {
	private Vertex<T> secondVertex;
	private int weight;
	
	public Vertex<T> getSecondVertex() {
		return secondVertex;
	}

	public void setSecondVertex(Vertex<T> secondVertex) {
		this.secondVertex = secondVertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Edge(Vertex<T> secondVertex, int weight) {
		this.secondVertex = secondVertex;
		this.weight = weight;
	}
	
	public Edge(Vertex<T> secondVertex) {
		this(secondVertex,0);
	}
	
}
